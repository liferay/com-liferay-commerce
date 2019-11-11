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

package com.liferay.headless.commerce.bom.internal.dto.v1_0.converter;

import com.liferay.commerce.bom.model.CommerceBOMEntry;
import com.liferay.commerce.bom.service.CommerceBOMEntryService;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPInstanceService;
import com.liferay.headless.commerce.bom.dto.v1_0.Position;
import com.liferay.headless.commerce.bom.dto.v1_0.Spot;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterContext;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterRegistry;
import com.liferay.petra.string.StringPool;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = "model.class.name=com.liferay.commerce.bom.model.CommerceBOMEntry",
	service = {DTOConverter.class, SpotDTOConverter.class}
)
public class SpotDTOConverter implements DTOConverter {

	@Override
	public String getContentType() {
		return Spot.class.getSimpleName();
	}

	public Spot toDTO(DTOConverterContext dtoConverterContext)
		throws Exception {

		CommerceBOMEntry commerceBOMEntry =
			_commerceBOMEntryService.getCommerceBOMEntry(
				dtoConverterContext.getResourcePrimKey());

		DTOConverter positionDTOConverter =
			_dtoConverterRegistry.getDTOConverter("commerceBOMEntryPosition");

		CPInstance cpInstance = _cpInstanceService.fetchCProductInstance(
			commerceBOMEntry.getCProductId(),
			commerceBOMEntry.getCPInstanceUuid());

		return new Spot() {
			{
				id = commerceBOMEntry.getCommerceBOMEntryId();
				number = commerceBOMEntry.getNumber();
				position = (Position)positionDTOConverter.toDTO(
					dtoConverterContext);
				productId = commerceBOMEntry.getCPInstanceUuid();

				if (cpInstance == null) {
					sku = StringPool.BLANK;
				}
				else {
					sku = cpInstance.getSku();
				}
			}
		};
	}

	@Reference
	private CommerceBOMEntryService _commerceBOMEntryService;

	@Reference
	private CPInstanceService _cpInstanceService;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

}