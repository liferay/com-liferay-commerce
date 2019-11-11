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
import com.liferay.headless.commerce.bom.dto.v1_0.Position;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = "model.class.name=commerceBOMEntryPosition",
	service = {DTOConverter.class, PositionDTOConverter.class}
)
public class PositionDTOConverter implements DTOConverter {

	@Override
	public String getContentType() {
		return Position.class.getSimpleName();
	}

	public Position toDTO(DTOConverterContext dtoConverterContext)
		throws Exception {

		CommerceBOMEntry commerceBOMEntry =
			_commerceBOMEntryService.getCommerceBOMEntry(
				dtoConverterContext.getResourcePrimKey());

		return new Position() {
			{
				x = commerceBOMEntry.getPositionX();
				y = commerceBOMEntry.getPositionY();
			}
		};
	}

	@Reference
	private CommerceBOMEntryService _commerceBOMEntryService;

}