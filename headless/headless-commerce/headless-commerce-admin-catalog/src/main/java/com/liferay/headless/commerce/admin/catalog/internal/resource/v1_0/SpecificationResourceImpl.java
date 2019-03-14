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

package com.liferay.headless.commerce.admin.catalog.internal.resource.v1_0;

import com.liferay.commerce.openapi.core.annotation.AsyncSupported;
import com.liferay.commerce.openapi.core.annotation.Status;
import com.liferay.commerce.openapi.core.context.Async;
import com.liferay.commerce.openapi.core.context.Language;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;
import com.liferay.headless.commerce.admin.catalog.internal.resource.util.v1_0.SpecificationHelper;
import com.liferay.headless.commerce.admin.catalog.internal.resource.util.v1_0.SpecificationValueHelper;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.SpecificationDTO;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.SpecificationValueDTO;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.SpecificationResource;
import com.liferay.oauth2.provider.scope.RequiresScope;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import javax.annotation.Generated;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_SELECT + "=(osgi.jaxrs.name=HeadlessCommerceAdminCatalog.Rest)",
		JaxrsWhiteboardConstants.JAX_RS_RESOURCE + "=true", "api.version=v1.0"
	},
	scope = ServiceScope.PROTOTYPE, service = SpecificationResource.class
)
@Generated(value = "OSGiRESTModuleGenerator")
public class SpecificationResourceImpl implements SpecificationResource {

	@Override
	@RequiresScope("HeadlessCommerceAdminCatalog.write")
	public Response deleteSpecification(Long id) throws Exception {
		_specificationHelper.deleteSpecification(id);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	@RequiresScope("HeadlessCommerceAdminCatalog.read")
	public SpecificationDTO getSpecification(Long id) throws Exception {
		return _specificationHelper.getSpecification(id);
	}

	@Override
	@RequiresScope("HeadlessCommerceAdminCatalog.read")
	public CollectionDTO<SpecificationValueDTO> getSpecificationValues(
			Long id, Language language, Pagination pagination)
		throws Exception {

		return _specificationValueHelper.getSpecificationValues(
			id, language, pagination);
	}

	@AsyncSupported
	@Override
	@RequiresScope("HeadlessCommerceAdminCatalog.write")
	public Response updateSpecification(
			Long id, SpecificationDTO specificationDTO)
		throws Exception {

		if (_async.isEnabled()) {
			new Thread(
				() -> {
					try {
						_specificationHelper.updateSpecification(
							id, specificationDTO);
					}
					catch (PortalException pe) {
						_log.error(pe, pe);
					}
				}
			).start();

			return null;
		}

		_specificationHelper.updateSpecification(id, specificationDTO);

		Response.ResponseBuilder responseBuilder = Response.accepted();

		return responseBuilder.build();
	}

	@AsyncSupported
	@Override
	@RequiresScope("HeadlessCommerceAdminCatalog.write")
	@Status(Response.Status.CREATED)
	public SpecificationValueDTO upsertSpecificationValue(
			Long id, SpecificationValueDTO specificationValueDTO,
			Language language)
		throws Exception {

		if (_async.isEnabled()) {
			new Thread(
				() -> {
					try {
						_specificationValueHelper.upsertSpecificationValue(
							id, specificationValueDTO, language);
					}
					catch (PortalException pe) {
						_log.error(pe, pe);
					}
				}
			).start();

			return null;
		}

		return _specificationValueHelper.upsertSpecificationValue(
			id, specificationValueDTO, language);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SpecificationResourceImpl.class);

	@Context
	private Async _async;

	@Reference
	private SpecificationHelper _specificationHelper;

	@Reference
	private SpecificationValueHelper _specificationValueHelper;

}