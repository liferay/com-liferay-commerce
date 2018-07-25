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

package com.liferay.commerce.product.starterkit.data.creator;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.product.model.CPAttachmentFileEntry;
import com.liferay.portal.kernel.exception.PortalException;

import java.io.InputStream;

import java.util.Locale;
import java.util.Map;

/**
 * @author Alessio Antonio Rendina
 * @author Daniel de Francisco
 */
@ProviderType
public interface CPAttachmentFileEntryDataCreator {

	public void addAssetCategoryAttachmentFileEntry(
			long userId, long groupId, long categoryId, String fileName,
			InputStream inputStream)
		throws Exception;

	public void addCPDefinitionAttachmentFileEntry(
			long userId, long groupId, long cpDefinitionId, String fileName,
			InputStream inputStream, int priority)
		throws Exception;

	public CPAttachmentFileEntry createCPAttachmentFileEntry(
			long userId, long groupId, long classNameId, long classPK,
			long fileEntryId, Map<Locale, String> titleMap, String json,
			int priority, int type)
		throws PortalException;

}