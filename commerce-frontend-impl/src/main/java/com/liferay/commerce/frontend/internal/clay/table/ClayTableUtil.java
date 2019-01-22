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

package com.liferay.commerce.frontend.internal.clay.table;

import com.liferay.commerce.frontend.ClayTableAction;
import com.liferay.commerce.frontend.ClayTableActionProvider;
import com.liferay.commerce.frontend.ClayTableActionProviderRegistry;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 */
@Component(service = ClayTableUtil.class)
public class ClayTableUtil {

	public List<ClayTableRow> getClayTableRows(
			List<Object> items, String tableName,
			HttpServletRequest httpServletRequest, long groupId)
		throws PortalException {

		List<ClayTableRow> clayTableRows = new ArrayList<>();

		List<ClayTableActionProvider> clayTableActionProviders =
			_clayTableActionProviderRegistry.getClayTableActionProviders(
				tableName);

		for (Object item : items) {
			ClayTableRow clayTableRow = new ClayTableRow(item);

			if (clayTableActionProviders != null) {
				for (ClayTableActionProvider clayTableActionProvider :
						clayTableActionProviders) {

					List<ClayTableAction> clayTableActions =
						clayTableActionProvider.clayTableActions(
							httpServletRequest, groupId, item);

					if (clayTableActions != null) {
						clayTableRow.addActionItems(clayTableActions);
					}
				}
			}

			clayTableRows.add(clayTableRow);
		}

		return clayTableRows;
	}

	@Reference
	private ClayTableActionProviderRegistry _clayTableActionProviderRegistry;

}