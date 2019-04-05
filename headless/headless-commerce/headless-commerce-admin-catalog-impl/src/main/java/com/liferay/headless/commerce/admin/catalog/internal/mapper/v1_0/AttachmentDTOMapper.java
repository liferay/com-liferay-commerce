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

package com.liferay.headless.commerce.admin.catalog.internal.mapper.v1_0;

import com.liferay.commerce.media.CommerceMediaResolver;
import com.liferay.commerce.product.model.CPAttachmentFileEntry;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Attachment;
import com.liferay.headless.commerce.core.util.LanguageUtils;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.File;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = AttachmentDTOMapper.class)
public class AttachmentDTOMapper {

	public Attachment toAttachment(
		CPAttachmentFileEntry cpAttachmentFileEntry) {

		Attachment attachment = new Attachment();

		if (cpAttachmentFileEntry == null) {
			return attachment;
		}

		try {
			FileEntry fileEntry = cpAttachmentFileEntry.getFileEntry();

			byte[] bytes = _file.getBytes(fileEntry.getContentStream());

			attachment.setAttachment(Base64.encode(bytes));

			attachment.setOptions(_getAttachmentOptions(cpAttachmentFileEntry));
			attachment.setSrc(
				_commerceMediaResolver.getDownloadUrl(
					cpAttachmentFileEntry.getCPAttachmentFileEntryId()));
		}
		catch (Exception e) {
			_log.error("Cannot instantiate Attachment ", e);

			throw new RuntimeException(e);
		}

		attachment.setDisplayDate(cpAttachmentFileEntry.getDisplayDate());
		attachment.setExpirationDate(cpAttachmentFileEntry.getExpirationDate());
		attachment.setExternalReferenceCode(
			cpAttachmentFileEntry.getExternalReferenceCode());
		attachment.setId(cpAttachmentFileEntry.getCPAttachmentFileEntryId());
		attachment.setPriority(cpAttachmentFileEntry.getPriority());
		attachment.setTitle(
			LanguageUtils.getLanguageIdMap(
				cpAttachmentFileEntry.getTitleMap()));
		attachment.setType(cpAttachmentFileEntry.getType());

		return attachment;
	}

	public Attachment[] toAttachments(
		List<CPAttachmentFileEntry> cpAttachmentFileEntries) {

		if (cpAttachmentFileEntries == null) {
			return null;
		}

		List<Attachment> attachments = new ArrayList<>();

		for (CPAttachmentFileEntry cpAttachmentFileEntry :
				cpAttachmentFileEntries) {

			attachments.add(toAttachment(cpAttachmentFileEntry));
		}

		Stream<Attachment> stream = attachments.stream();

		return stream.toArray(Attachment[]::new);
	}

	private Map<String, String> _getAttachmentOptions(
			CPAttachmentFileEntry cpAttachmentFileEntry)
		throws JSONException {

		Map<String, String> options = new HashMap<>();

		JSONObject jsonObject = _jsonFactory.createJSONObject(
			cpAttachmentFileEntry.getJson());

		Iterator<String> keys = jsonObject.keys();

		while (keys.hasNext()) {
			String key = keys.next();

			options.put(key, jsonObject.getString(key));
		}

		return options;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AttachmentDTOMapper.class);

	@Reference
	private CommerceMediaResolver _commerceMediaResolver;

	@Reference
	private File _file;

	@Reference
	private JSONFactory _jsonFactory;

}