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

package com.liferay.headless.commerce.bom.internal.dto.v1_0.converter.util;

import com.liferay.commerce.bom.model.CommerceBOMFolder;
import com.liferay.headless.commerce.bom.dto.v1_0.Breadcrumb;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DefaultDTOConverterContext;
import com.liferay.portal.kernel.language.LanguageUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

/**
 * @author Alessio Antonio Rendina
 */
public class BreadcrumbDTOConverterUtil {

	public static Breadcrumb[] getBreadcrumbs(
			DTOConverter dtoConverter, CommerceBOMFolder commerceBOMFolder,
			Locale locale)
		throws Exception {

		List<Breadcrumb> breadcrumbs = new ArrayList<>();

		breadcrumbs.add(
			new Breadcrumb() {
				{
					label = LanguageUtil.get(locale, "home");
					url = "/folders/0";
				}
			});

		if (commerceBOMFolder != null) {
			List<CommerceBOMFolder> ancestorCommerceBOMFolders =
				commerceBOMFolder.getAncestors();

			Collections.reverse(ancestorCommerceBOMFolders);

			for (CommerceBOMFolder ancestorCommerceBOMFolder :
					ancestorCommerceBOMFolders) {

				breadcrumbs.add(
					(Breadcrumb)dtoConverter.toDTO(
						new DefaultDTOConverterContext(
							locale,
							ancestorCommerceBOMFolder.
								getCommerceBOMFolderId())));
			}
		}

		Stream<Breadcrumb> stream = breadcrumbs.stream();

		return stream.toArray(Breadcrumb[]::new);
	}

}