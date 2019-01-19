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

package com.liferay.commerce.product.content.web.internal.util;

import com.liferay.commerce.media.CommerceMediaResolverUtil;
import com.liferay.commerce.product.catalog.CPMedia;
import com.liferay.commerce.product.model.CPAttachmentFileEntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.theme.ThemeDisplay;

/**
 * @author Marco Leo
 */
public class CPMediaImpl implements CPMedia {

	public CPMediaImpl(
			CPAttachmentFileEntry cpAttachmentFileEntry,
			ThemeDisplay themeDisplay)
		throws PortalException {

		FileEntry fileEntry = cpAttachmentFileEntry.getFileEntry();

		_downloadUrl = CommerceMediaResolverUtil.getDownloadUrl(
			cpAttachmentFileEntry.getCPAttachmentFileEntryId());
		_id = cpAttachmentFileEntry.getCPAttachmentFileEntryId();
		_mimeType = fileEntry.getMimeType();
		_url = CommerceMediaResolverUtil.getUrl(
			cpAttachmentFileEntry.getCPAttachmentFileEntryId());
		_title = cpAttachmentFileEntry.getTitle(themeDisplay.getLanguageId());
		_thumbnailUrl = CommerceMediaResolverUtil.getThumbnailUrl(
			cpAttachmentFileEntry.getCPAttachmentFileEntryId());
	}

	@Override
	public String getDownloadUrl() {
		return _downloadUrl;
	}

	@Override
	public long getId() {
		return _id;
	}

	@Override
	public String getThumbnailUrl() {
		return _thumbnailUrl;
	}

	@Override
	public String getTitle() {
		return _title;
	}

	@Override
	public String getUrl() {
		return _url;
	}

	@Override
	public String mimeType() {
		return _mimeType;
	}

	private final String _downloadUrl;
	private final long _id;
	private final String _mimeType;
	private final String _thumbnailUrl;
	private final String _title;
	private final String _url;

}