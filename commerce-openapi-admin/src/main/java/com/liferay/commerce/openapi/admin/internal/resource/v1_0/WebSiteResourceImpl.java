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

package com.liferay.commerce.openapi.admin.internal.resource.v1_0;

import com.liferay.commerce.openapi.admin.internal.util.v1_0.DTOUtils;
import com.liferay.commerce.openapi.admin.model.v1_0.WebSiteDTO;
import com.liferay.commerce.openapi.admin.resource.v1_0.WebSiteResource;
import com.liferay.commerce.openapi.core.context.Language;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;
import com.liferay.commerce.openapi.core.util.IdUtils;
import com.liferay.oauth2.provider.scope.RequiresScope;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupService;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
 * @author Zoltán Takács
 */
@Component(
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_SELECT + "=(osgi.jaxrs.name=CommerceOpenApiAdmin.Rest)",
		JaxrsWhiteboardConstants.JAX_RS_RESOURCE + "=true", "api.version=v1.0"
	},
	scope = ServiceScope.PROTOTYPE, service = WebSiteResource.class
)
public class WebSiteResourceImpl implements WebSiteResource {

	@Override
	@RequiresScope("CommerceOpenApiAdmin.read")
	public WebSiteDTO getWebSite(String id, Language language) {
		Group group = _getGroupById(id);

		return DTOUtils.modelToDTO(group, language.getLanguageId());
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.read")
	public CollectionDTO<WebSiteDTO> getWebSites(
		Language language, Pagination pagination) {

		final int totalItems;
		List<Group> groups = null;

		try {
			groups = _groupService.getGroups(
				_company.getCompanyId(), 0, true, pagination.getStartPosition(),
				pagination.getEndPosition());

			totalItems = _groupService.getGroupsCount(
				_company.getCompanyId(), 0, true);
		}
		catch (PortalException pe) {
			throw new ServerErrorException(
				"Unable to fetch web sites",
				Response.Status.INTERNAL_SERVER_ERROR, pe);
		}

		Stream<Group> stream = groups.stream();

		return stream.map(
			group -> DTOUtils.modelToDTO(group, language.getLanguageId())
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

	@Context
	private Company _company;

	@Reference
	private GroupService _groupService;

}