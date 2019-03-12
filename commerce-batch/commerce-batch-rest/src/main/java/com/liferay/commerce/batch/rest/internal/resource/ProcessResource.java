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

package com.liferay.commerce.batch.rest.internal.resource;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.commerce.batch.engine.api.batch.BatchFileProcessor;
import com.liferay.commerce.batch.exception.NoSuchBatchJobException;
import com.liferay.oauth2.provider.scope.RequiresScope;

import java.util.Map;

import javax.activation.DataHandler;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
 * @author Ivica Cardic
 */
@Component(
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_SELECT + "=(osgi.jaxrs.name=CommerceBatch.Rest)",
		JaxrsWhiteboardConstants.JAX_RS_RESOURCE + "=true"
	},
	scope = ServiceScope.PROTOTYPE, service = ProcessResource.class
)
@Path("/process")
public class ProcessResource {

	@DELETE
	@Path("")
	@RequiresScope("CommerceBatch.write")
	public Response delete(MultipartBody multipartBody) throws Exception {
		return _process(multipartBody.getRootAttachment());
	}

	@GET
	@Path("/{key}")
	@RequiresScope("CommerceBatch.read")
	public Response getBatchExecutionStatus(@PathParam("key") String key)
		throws NoSuchBatchJobException {

		Response.ResponseBuilder responseBuilder = Response.ok();

		String status = _batchFileProcessor.getBatchExecutionStatus(key);

		responseBuilder.entity(status);

		return responseBuilder.build();
	}

	@Path("")
	@POST
	@RequiresScope("CommerceBatch.write")
	public Response post(MultipartBody multipartBody) throws Exception {
		return _process(multipartBody.getRootAttachment());
	}

	@Path("")
	@PUT
	@RequiresScope("CommerceBatch.write")
	public Response put(MultipartBody multipartBody) throws Exception {
		return _process(multipartBody.getRootAttachment());
	}

	private Response _process(Attachment attachment) throws Exception {
		DataHandler dataHandler = attachment.getDataHandler();

		Map<String, String> jobIdNameMap = _batchFileProcessor.process(
			dataHandler.getName(), dataHandler.getInputStream());

		Response.ResponseBuilder responseBuilder = Response.accepted();

		responseBuilder.entity(_OBJECT_MAPPER.writeValueAsString(jobIdNameMap));

		return responseBuilder.build();
	}

	private static final ObjectMapper _OBJECT_MAPPER = new ObjectMapper();

	@Reference
	private BatchFileProcessor _batchFileProcessor;

}