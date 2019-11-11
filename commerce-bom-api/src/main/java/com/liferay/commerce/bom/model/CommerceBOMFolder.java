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

package com.liferay.commerce.bom.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.TreeModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the CommerceBOMFolder service. Represents a row in the &quot;CommerceBOMFolder&quot; database table, with each column mapped to a property of this class.
 *
 * @author Luca Pellizzon
 * @see CommerceBOMFolderModel
 * @see com.liferay.commerce.bom.model.impl.CommerceBOMFolderImpl
 * @see com.liferay.commerce.bom.model.impl.CommerceBOMFolderModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.commerce.bom.model.impl.CommerceBOMFolderImpl")
@ProviderType
public interface CommerceBOMFolder extends CommerceBOMFolderModel, PersistedModel,
	TreeModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.commerce.bom.model.impl.CommerceBOMFolderImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CommerceBOMFolder, Long> COMMERCE_BOM_FOLDER_ID_ACCESSOR =
		new Accessor<CommerceBOMFolder, Long>() {
			@Override
			public Long get(CommerceBOMFolder commerceBOMFolder) {
				return commerceBOMFolder.getCommerceBOMFolderId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<CommerceBOMFolder> getTypeClass() {
				return CommerceBOMFolder.class;
			}
		};

	public java.util.List<Long> getAncestorCommerceBOMFolderIds()
		throws com.liferay.portal.kernel.exception.PortalException;

	public java.util.List<CommerceBOMFolder> getAncestors()
		throws com.liferay.portal.kernel.exception.PortalException;

	public CommerceBOMFolder getParentCommerceBOMFolder()
		throws com.liferay.portal.kernel.exception.PortalException;

	public boolean isRoot();
}