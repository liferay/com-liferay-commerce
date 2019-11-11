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

package com.liferay.commerce.bom.model.impl;

import com.liferay.commerce.bom.model.CommerceBOMFolder;
import com.liferay.commerce.bom.model.CommerceBOMFolderConstants;
import com.liferay.commerce.bom.service.CommerceBOMFolderLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceBOMFolderImpl extends CommerceBOMFolderBaseImpl {

	public CommerceBOMFolderImpl() {
	}

	@Override
	public List<Long> getAncestorCommerceBOMFolderIds() throws PortalException {
		List<Long> ancestorFolderIds = new ArrayList<>();

		CommerceBOMFolder commerceBOMFolder = this;

		while (!commerceBOMFolder.isRoot()) {
			commerceBOMFolder = commerceBOMFolder.getParentCommerceBOMFolder();

			ancestorFolderIds.add(commerceBOMFolder.getCommerceBOMFolderId());
		}

		return ancestorFolderIds;
	}

	@Override
	public List<CommerceBOMFolder> getAncestors() throws PortalException {
		List<CommerceBOMFolder> ancestors = new ArrayList<>();

		CommerceBOMFolder commerceBOMFolder = this;

		while (!commerceBOMFolder.isRoot()) {
			commerceBOMFolder = commerceBOMFolder.getParentCommerceBOMFolder();

			ancestors.add(commerceBOMFolder);
		}

		return ancestors;
	}

	@Override
	public CommerceBOMFolder getParentCommerceBOMFolder()
		throws PortalException {

		if (getParentCommerceBOMFolderId() ==
				CommerceBOMFolderConstants.DEFAULT_COMMERCE_BOM_FOLDER_ID) {

			return null;
		}

		return CommerceBOMFolderLocalServiceUtil.getCommerceBOMFolder(
			getParentCommerceBOMFolderId());
	}

	@Override
	public boolean isRoot() {
		if (getParentCommerceBOMFolderId() ==
				CommerceBOMFolderConstants.DEFAULT_COMMERCE_BOM_FOLDER_ID) {

			return true;
		}

		return false;
	}

}