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

package com.liferay.headless.commerce.admin.catalog.internal.dto.v1_0.converter;

import com.liferay.commerce.media.CommerceMediaResolver;
import com.liferay.commerce.product.model.CPAttachmentFileEntry;
import com.liferay.commerce.product.service.CPAttachmentFileEntryService;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Attachment;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterContext;
import com.liferay.headless.commerce.core.util.LanguageUtils;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.util.File;
import com.liferay.portal.kernel.util.Validator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 * @author Igor Beslic
 */
@Component(
	property = "model.class.name=com.liferay.commerce.product.model.CPAttachmentFileEntry",
	service = {AttachmentDTOConverter.class, DTOConverter.class}
)
public class AttachmentDTOConverter implements DTOConverter {

	@Override
	public String getContentType() {
		return Attachment.class.getSimpleName();
	}

	public Attachment toDTO(DTOConverterContext dtoConverterContext)
		throws Exception {

		CPAttachmentFileEntry cpAttachmentFileEntry =
			_cpAttachmentFileEntryService.getCPAttachmentFileEntry(
				dtoConverterContext.getResourcePrimKey());

		Company company = _companyLocalService.getCompany(
			cpAttachmentFileEntry.getCompanyId());

		String portalURL = company.getPortalURL(0);

		return new Attachment() {
			{
				displayDate = cpAttachmentFileEntry.getDisplayDate();
				expirationDate = cpAttachmentFileEntry.getExpirationDate();
				externalReferenceCode =
					cpAttachmentFileEntry.getExternalReferenceCode();
				id = cpAttachmentFileEntry.getCPAttachmentFileEntryId();
				options = _getAttachmentOptions(cpAttachmentFileEntry);
				priority = cpAttachmentFileEntry.getPriority();
				src =
					portalURL +
						_commerceMediaResolver.getDownloadUrl(
							cpAttachmentFileEntry.getCPAttachmentFileEntryId());
				title = LanguageUtils.getLanguageIdMap(
					cpAttachmentFileEntry.getTitleMap());
				type = cpAttachmentFileEntry.getType();
			}
		};
	}

	private Map<String, String> _getAttachmentOptions(
			CPAttachmentFileEntry cpAttachmentFileEntry)
		throws JSONException {

		String json = cpAttachmentFileEntry.getJson();

		if (Validator.isNull(json)) {
			return Collections.emptyMap();
		}

		Map<String, String> options = new HashMap<>();

		JSONArray jsonArray = _jsonFactory.createJSONArray(json);

		for (Object element : jsonArray) {
			JSONObject jsonObject = (JSONObject)element;

			if (!jsonObject.has("key")) {
				continue;
			}

			options.put(
				jsonObject.getString("key"), jsonObject.getString("value"));
		}

		return options;
	}

	@Reference
	private CommerceMediaResolver _commerceMediaResolver;

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference
	private CPAttachmentFileEntryService _cpAttachmentFileEntryService;

	@Reference
	private File _file;

	@Reference
	private JSONFactory _jsonFactory;

}