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

package com.liferay.commerce.openapi.admin.internal.resource;

import com.liferay.commerce.openapi.admin.internal.util.DTOUtils;
import com.liferay.commerce.openapi.admin.internal.util.IdUtils;
import com.liferay.commerce.openapi.admin.model.CollectionDTO;
import com.liferay.commerce.openapi.admin.model.WebSiteDTO;
import com.liferay.commerce.openapi.admin.resource.WebSiteResource;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupService;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
 * @author Zoltán Takács
 */
@Component(
	immediate = true,
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_SELECT + "=(osgi.jaxrs.name=CommerceOpenApiAdmin.Rest)",
		JaxrsWhiteboardConstants.JAX_RS_RESOURCE + "=true", "api.version=1.0"
	},
	service = WebSiteResource.class
)
public class WebSiteResourceImpl implements WebSiteResource {

	@Override
	public WebSiteDTO getWebSite(String id, Locale locale) {
		Group group = _getGroupById(id);

		return DTOUtils.modelToDTO(group, locale);
	}

	@Override
	public CollectionDTO<WebSiteDTO> getWebSites(
		Locale locale, Company company, Pagination pagination) {

		final int totalItems;
		List<Group> groups = null;

		try {
			groups = _groupService.getGroups(
				company.getCompanyId(), 0, true, pagination.getStartPosition(),
				pagination.getEndPosition());

			totalItems = _groupService.getGroupsCount(
				company.getCompanyId(), 0, true);
		}
		catch (PortalException pe) {
			throw new ServerErrorException(
				"Unable to fetch web sites",
				Response.Status.INTERNAL_SERVER_ERROR, pe);
		}

		Stream<Group> stream = groups.stream();

		return stream.map(
			group -> DTOUtils.modelToDTO(group, locale)
		).collect(
			Collectors.collectingAndThen(
				Collectors.toList(),
				webSiteDTOs ->
					new CollectionDTO<>(webSiteDTOs, totalItems))
		);
	}

	private Group _getGroupById(String id) {
		if (IdUtils.isLocalPK(id)) {
			try {
				return _groupService.getGroup(GetterUtil.getLong(id));
			}
			catch (PortalException pe) {
				throw new ServerErrorException(
					"Unable to find Price List with ID: " + id,
					Response.Status.INTERNAL_SERVER_ERROR, pe);
			}
		}

		throw new NotFoundException("Unable to find web site with ID " + id);
	}

	@Reference
	private GroupService _groupService;

}