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

import com.liferay.commerce.product.exception.NoSuchCPAttachmentFileEntryException;
import com.liferay.commerce.product.model.CPAttachmentFileEntry;
import com.liferay.commerce.product.model.CPAttachmentFileEntryConstants;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.service.CPAttachmentFileEntryService;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Attachment;
import com.liferay.headless.commerce.admin.catalog.internal.mapper.v1_0.AttachmentDTOMapper;
import com.liferay.headless.commerce.admin.catalog.internal.util.DateConfigUtil;
import com.liferay.headless.commerce.admin.catalog.internal.util.v1_0.AttachmentUtil;
import com.liferay.headless.commerce.admin.catalog.internal.util.v1_0.ProductUtil;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.AttachmentResource;
import com.liferay.headless.commerce.core.util.DateConfig;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.upload.UniqueFileNameProvider;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Zoltán Takács
 * @author Alessio Antonio Rendina
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/attachment.properties",
	scope = ServiceScope.PROTOTYPE, service = AttachmentResource.class
)
public class AttachmentResourceImpl extends BaseAttachmentResourceImpl {

	@Override
	public Response deleteAttachment(Long id) throws Exception {
		_cpAttachmentFileEntryService.deleteCPAttachmentFileEntry(id);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	public Response deleteAttachmentByExternalReferenceCode(
			String externalReferenceCode)
		throws Exception {

		CPAttachmentFileEntry cpAttachmentFileEntry =
			_cpAttachmentFileEntryService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (cpAttachmentFileEntry == null) {
			throw new NoSuchCPAttachmentFileEntryException(
				"Unable to find Attachment with externalReferenceCode: " +
					externalReferenceCode);
		}

		_cpAttachmentFileEntryService.deleteCPAttachmentFileEntry(
			cpAttachmentFileEntry.getCPAttachmentFileEntryId());

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	public Attachment getAttachment(Long id) throws Exception {
		return _attachmentDTOMapper.toAttachment(
			_cpAttachmentFileEntryService.getCPAttachmentFileEntry(id));
	}

	@Override
	public Attachment getAttachmentByExternalReferenceCode(
			String externalReferenceCode)
		throws Exception {

		return _attachmentDTOMapper.toAttachment(
			_cpAttachmentFileEntryService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode));
	}

	@Override
	public Page<Attachment>
			getProductByExternalReferenceCodeexternalReferenceCodeAttachmentsPage(
				String externalReferenceCode, Pagination pagination)
		throws Exception {

		CPDefinition cpDefinition =
			ProductUtil.getProductByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		List<CPAttachmentFileEntry> cpAttachmentFileEntries =
			_cpAttachmentFileEntryService.getCPAttachmentFileEntries(
				_classNameLocalService.getClassNameId(
					cpDefinition.getModelClass()),
				cpDefinition.getCPDefinitionId(),
				CPAttachmentFileEntryConstants.TYPE_OTHER,
				WorkflowConstants.STATUS_APPROVED,
				pagination.getStartPosition(), pagination.getEndPosition());

		int totalItems =
			_cpAttachmentFileEntryService.getCPAttachmentFileEntriesCount(
				_classNameLocalService.getClassNameId(
					cpDefinition.getModelClass()),
				cpDefinition.getCPDefinitionId(),
				CPAttachmentFileEntryConstants.TYPE_OTHER,
				WorkflowConstants.STATUS_APPROVED);

		return Page.of(
			_toAttachments(cpAttachmentFileEntries), pagination, totalItems);
	}

	@Override
	public Page<Attachment> getProductByExternalReferenceCodeImagesPage(
			String externalReferenceCode, Pagination pagination)
		throws Exception {

		CPDefinition cpDefinition =
			ProductUtil.getProductByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		List<CPAttachmentFileEntry> cpAttachmentFileEntries =
			_cpAttachmentFileEntryService.getCPAttachmentFileEntries(
				_classNameLocalService.getClassNameId(
					cpDefinition.getModelClass()),
				cpDefinition.getCPDefinitionId(),
				CPAttachmentFileEntryConstants.TYPE_IMAGE,
				WorkflowConstants.STATUS_APPROVED,
				pagination.getStartPosition(), pagination.getEndPosition());

		int totalItems =
			_cpAttachmentFileEntryService.getCPAttachmentFileEntriesCount(
				_classNameLocalService.getClassNameId(
					cpDefinition.getModelClass()),
				cpDefinition.getCPDefinitionId(),
				CPAttachmentFileEntryConstants.TYPE_IMAGE,
				WorkflowConstants.STATUS_APPROVED);

		return Page.of(
			_toAttachments(cpAttachmentFileEntries), pagination, totalItems);
	}

	@Override
	public Page<Attachment> getProductIdAttachmentsPage(
			Long id, Pagination pagination)
		throws Exception {

		CPDefinition cpDefinition = ProductUtil.getProductById(id);

		List<CPAttachmentFileEntry> cpAttachmentFileEntries =
			_cpAttachmentFileEntryService.getCPAttachmentFileEntries(
				_classNameLocalService.getClassNameId(
					cpDefinition.getModelClass()),
				cpDefinition.getCPDefinitionId(),
				CPAttachmentFileEntryConstants.TYPE_OTHER,
				WorkflowConstants.STATUS_APPROVED,
				pagination.getStartPosition(), pagination.getEndPosition());

		int totalItems =
			_cpAttachmentFileEntryService.getCPAttachmentFileEntriesCount(
				_classNameLocalService.getClassNameId(
					cpDefinition.getModelClass()),
				cpDefinition.getCPDefinitionId(),
				CPAttachmentFileEntryConstants.TYPE_OTHER,
				WorkflowConstants.STATUS_APPROVED);

		return Page.of(
			_toAttachments(cpAttachmentFileEntries), pagination, totalItems);
	}

	@Override
	public Page<Attachment> getProductIdImagesPage(
			Long id, Pagination pagination)
		throws Exception {

		CPDefinition cpDefinition = ProductUtil.getProductById(id);

		List<CPAttachmentFileEntry> cpAttachmentFileEntries =
			_cpAttachmentFileEntryService.getCPAttachmentFileEntries(
				_classNameLocalService.getClassNameId(
					cpDefinition.getModelClass()),
				cpDefinition.getCPDefinitionId(),
				CPAttachmentFileEntryConstants.TYPE_IMAGE,
				WorkflowConstants.STATUS_APPROVED,
				pagination.getStartPosition(), pagination.getEndPosition());

		int totalItems =
			_cpAttachmentFileEntryService.getCPAttachmentFileEntriesCount(
				_classNameLocalService.getClassNameId(
					cpDefinition.getModelClass()),
				cpDefinition.getCPDefinitionId(),
				CPAttachmentFileEntryConstants.TYPE_IMAGE,
				WorkflowConstants.STATUS_APPROVED);

		return Page.of(
			_toAttachments(cpAttachmentFileEntries), pagination, totalItems);
	}

	@Override
	public Response patchAttachment(Long id, Attachment attachment)
		throws Exception {

		CPAttachmentFileEntry cpAttachmentFileEntry =
			_cpAttachmentFileEntryService.getCPAttachmentFileEntry(id);

		_updateAttachment(cpAttachmentFileEntry, attachment);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	public Response patchAttachmentByExternalReferenceCode(
			String externalReferenceCode, Attachment attachment)
		throws Exception {

		CPAttachmentFileEntry cpAttachmentFileEntry =
			_cpAttachmentFileEntryService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (cpAttachmentFileEntry == null) {
			throw new NoSuchCPAttachmentFileEntryException(
				"Unable to find Attachment with externalReferenceCode: " +
					externalReferenceCode);
		}

		_updateAttachment(cpAttachmentFileEntry, attachment);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	public Attachment postProductByExternalReferenceCodeAttachment(
			String externalReferenceCode, Attachment attachment)
		throws Exception {

		CPDefinition cpDefinition =
			ProductUtil.getProductByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		return _upsertProductAttachment(cpDefinition, attachment);
	}

	@Override
	public Attachment postProductByExternalReferenceCodeImage(
			String externalReferenceCode, Attachment attachment)
		throws Exception {

		CPDefinition cpDefinition =
			ProductUtil.getProductByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		return _upsertProductImage(cpDefinition, attachment);
	}

	@Override
	public Attachment postProductIdAttachment(Long id, Attachment attachment)
		throws Exception {

		CPDefinition cpDefinition = ProductUtil.getProductById(id);

		return _upsertProductAttachment(cpDefinition, attachment);
	}

	@Override
	public Attachment postProductIdImage(Long id, Attachment attachment)
		throws Exception {

		CPDefinition cpDefinition = ProductUtil.getProductById(id);

		return _upsertProductImage(cpDefinition, attachment);
	}

	private List<Attachment> _toAttachments(
		List<CPAttachmentFileEntry> cpAttachmentFileEntries) {

		List<Attachment> attachments = new ArrayList<>();

		for (CPAttachmentFileEntry cpAttachmentFileEntry :
				cpAttachmentFileEntries) {

			attachments.add(
				_attachmentDTOMapper.toAttachment(cpAttachmentFileEntry));
		}

		return attachments;
	}

	private Attachment _updateAttachment(
			CPAttachmentFileEntry cpAttachmentFileEntry, Attachment attachment)
		throws Exception {

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			cpAttachmentFileEntry.getGroupId());

		Calendar displayCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		if (attachment.getDisplayDate() != null) {
			displayCalendar = DateConfigUtil.convertDateToCalendar(
				attachment.getDisplayDate());
		}

		DateConfig displayDateConfig = new DateConfig(displayCalendar);

		Calendar expirationCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		expirationCalendar.add(Calendar.MONTH, 1);

		if (attachment.getExpirationDate() != null) {
			expirationCalendar = DateConfigUtil.convertDateToCalendar(
				attachment.getExpirationDate());
		}

		DateConfig expirationDateConfig = new DateConfig(expirationCalendar);

		FileEntry fileEntry = AttachmentUtil.addFileEntry(
			attachment, serviceContext.getScopeGroupId(),
			serviceContext.getUserId(), _uniqueFileNameProvider);

		if (fileEntry == null) {
			fileEntry = cpAttachmentFileEntry.getFileEntry();
		}

		cpAttachmentFileEntry =
			_cpAttachmentFileEntryService.updateCPAttachmentFileEntry(
				cpAttachmentFileEntry.getCPAttachmentFileEntryId(),
				fileEntry.getFileEntryId(), displayDateConfig.getMonth(),
				displayDateConfig.getDay(), displayDateConfig.getYear(),
				displayDateConfig.getHour(), displayDateConfig.getMinute(),
				expirationDateConfig.getMonth(), expirationDateConfig.getDay(),
				expirationDateConfig.getYear(), expirationDateConfig.getHour(),
				expirationDateConfig.getMinute(),
				GetterUtil.get(
					attachment.getNeverExpire(),
					cpAttachmentFileEntry.getExpirationDate() == null),
				AttachmentUtil.getTitleMap(cpAttachmentFileEntry, attachment),
				GetterUtil.get(
					attachment.getOptions(), cpAttachmentFileEntry.getJson()),
				GetterUtil.get(
					attachment.getPriority(),
					cpAttachmentFileEntry.getPriority()),
				attachment.getType(), serviceContext);

		return _attachmentDTOMapper.toAttachment(cpAttachmentFileEntry);
	}

	private Attachment _upsertAttachment(
			CPDefinition cpDefinition, int type, Attachment attachment)
		throws Exception {

		CPAttachmentFileEntry cpAttachmentFileEntry =
			AttachmentUtil.upsertCPAttachmentFileEntry(
				_cpAttachmentFileEntryService, _uniqueFileNameProvider,
				attachment,
				_classNameLocalService.getClassNameId(
					cpDefinition.getModelClassName()),
				cpDefinition.getCPDefinitionId(), type,
				_serviceContextHelper.getServiceContext(
					cpDefinition.getGroupId()));

		return _attachmentDTOMapper.toAttachment(cpAttachmentFileEntry);
	}

	private Attachment _upsertProductAttachment(
			CPDefinition cpDefinition, Attachment attachment)
		throws Exception {

		return _upsertAttachment(
			cpDefinition, CPAttachmentFileEntryConstants.TYPE_OTHER,
			attachment);
	}

	private Attachment _upsertProductImage(
			CPDefinition cpDefinition, Attachment attachment)
		throws Exception {

		return _upsertAttachment(
			cpDefinition, CPAttachmentFileEntryConstants.TYPE_IMAGE,
			attachment);
	}

	@Reference
	private AttachmentDTOMapper _attachmentDTOMapper;

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private CPAttachmentFileEntryService _cpAttachmentFileEntryService;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

	@Reference
	private UniqueFileNameProvider _uniqueFileNameProvider;

}