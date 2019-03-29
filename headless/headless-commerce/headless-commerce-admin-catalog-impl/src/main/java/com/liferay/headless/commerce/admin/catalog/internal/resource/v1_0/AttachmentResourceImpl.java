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

import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Attachment;
import com.liferay.headless.commerce.admin.catalog.internal.util.v1_0.AttachmentHelper;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.AttachmentResource;

import javax.validation.constraints.NotNull;

import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Zoltán Takács
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/attachment.properties",
	scope = ServiceScope.PROTOTYPE, service = AttachmentResource.class
)
public class AttachmentResourceImpl extends BaseAttachmentResourceImpl {

	@Override
	public Response deleteAttachment(@NotNull String id) throws Exception {
		_attachmentHelper.deleteAttachment(id, contextCompany);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	public Attachment getAttachment(@NotNull String id) throws Exception {
		return _attachmentHelper.getAttachment(id, contextCompany);
	}

	@Override
	public Response updateAttachment(@NotNull String id, Attachment attachment)
		throws Exception {

		_attachmentHelper.updateAttachment(id, attachment, contextCompany);

		Response.ResponseBuilder responseBuilder = Response.accepted();

		return responseBuilder.build();
	}

	@Reference
	private AttachmentHelper _attachmentHelper;

}