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

import com.liferay.commerce.batch.rest.internal.resource.util.JobHelper;
import com.liferay.oauth2.provider.scope.RequiresScope;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

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
	public Response delete(MultipartBody multipartBody) {
		return _process(multipartBody.getRootAttachment());
	}

	@GET
	@Path("/{jobId}")
	@RequiresScope("CommerceBatch.read")
	public Response getBatchExecutionStatus(@PathParam("jobId") String jobId) {
		Response.ResponseBuilder responseBuilder = Response.ok();

		boolean active = _jobHelper.getBatchExecutionStatus(jobId);

		responseBuilder.entity(active);

		return responseBuilder.build();
	}

	@Path("")
	@POST
	@RequiresScope("CommerceBatch.write")
	public Response post(MultipartBody multipartBody) {
		return _process(multipartBody.getRootAttachment());
	}

	@Path("")
	@PUT
	@RequiresScope("CommerceBatch.write")
	public Response put(MultipartBody multipartBody) {
		return _process(multipartBody.getRootAttachment());
	}

	private Response _process(Attachment attachment) {
		try {
			String jobId = _jobHelper.run(attachment);

			Response.ResponseBuilder responseBuilder = Response.accepted();

			responseBuilder.entity(jobId);

			return responseBuilder.build();
		}
		catch (Exception e) {
			_log.error(e.getMessage(), e);

			Response.ResponseBuilder responseBuilder = Response.serverError();

			return responseBuilder.build();
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ProcessResource.class);

	@Reference
	private JobHelper _jobHelper;

}