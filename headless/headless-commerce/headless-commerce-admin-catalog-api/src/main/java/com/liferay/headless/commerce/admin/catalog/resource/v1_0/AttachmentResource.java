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

package com.liferay.headless.commerce.admin.catalog.resource.v1_0;

import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Attachment;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import javax.annotation.Generated;

import javax.ws.rs.core.Response;

/**
 * To access this resource, run:
 *
 *     curl -u your@email.com:yourpassword -D - http://localhost:8080/o/headless-commerce-admin-catalog/v1.0
 *
 * @author Zoltán Takács
 * @generated
 */
@Generated("")
public interface AttachmentResource {

	public Response deleteAttachment(Long id) throws Exception;

	public Attachment getAttachment(Long id) throws Exception;

	public Response patchAttachment(Long id, Attachment attachment)
		throws Exception;

	public Response deleteAttachmentByExternalReferenceCode(
			String externalReferenceCode)
		throws Exception;

	public Attachment getAttachmentByExternalReferenceCode(
			String externalReferenceCode)
		throws Exception;

	public Response patchAttachmentByExternalReferenceCode(
			String externalReferenceCode, Attachment attachment)
		throws Exception;

	public Page<Attachment> getProductIdAttachmentsPage(
			Long id, Pagination pagination)
		throws Exception;

	public Page<Attachment>
			getProductByExternalReferenceCodeexternalReferenceCodeAttachmentsPage(
				String externalReferenceCode, Pagination pagination)
		throws Exception;

	public Attachment postProductIdAttachment(Long id, Attachment attachment)
		throws Exception;

	public Attachment postProductByExternalReferenceCodeAttachment(
			String externalReferenceCode, Attachment attachment)
		throws Exception;

	public Page<Attachment> getProductIdImagesPage(
			Long id, Pagination pagination)
		throws Exception;

	public Page<Attachment> getProductByExternalReferenceCodeImagesPage(
			String externalReferenceCode, Pagination pagination)
		throws Exception;

	public Attachment postProductIdImage(Long id, Attachment attachment)
		throws Exception;

	public Attachment postProductByExternalReferenceCodeImage(
			String externalReferenceCode, Attachment attachment)
		throws Exception;

	public void setContextCompany(Company contextCompany);

}