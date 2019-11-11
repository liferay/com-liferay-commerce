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

import com.liferay.commerce.bom.model.CommerceBOMFolder;
import com.liferay.commerce.bom.service.CommerceBOMFolderService;
import com.liferay.headless.commerce.bom.dto.v1_0.Breadcrumb;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = "model.class.name=breadcrumb",
	service = {BreadcrumbDTOConverter.class, DTOConverter.class}
)
public class BreadcrumbDTOConverter implements DTOConverter {

	@Override
	public String getContentType() {
		return Breadcrumb.class.getSimpleName();
	}

	public Breadcrumb toDTO(DTOConverterContext dtoConverterContext)
		throws Exception {

		CommerceBOMFolder commerceBOMFolder =
			_commerceBOMFolderService.getCommerceBOMFolder(
				dtoConverterContext.getResourcePrimKey());

		return new Breadcrumb() {
			{
				label = commerceBOMFolder.getName();
				url = "/folders/" + commerceBOMFolder.getCommerceBOMFolderId();
			}
		};
	}

	@Reference
	private CommerceBOMFolderService _commerceBOMFolderService;

}