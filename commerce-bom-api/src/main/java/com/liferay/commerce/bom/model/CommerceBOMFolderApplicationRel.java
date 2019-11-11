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
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the CommerceBOMFolderApplicationRel service. Represents a row in the &quot;CBOMFolderApplicationRel&quot; database table, with each column mapped to a property of this class.
 *
 * @author Luca Pellizzon
 * @see CommerceBOMFolderApplicationRelModel
 * @see com.liferay.commerce.bom.model.impl.CommerceBOMFolderApplicationRelImpl
 * @see com.liferay.commerce.bom.model.impl.CommerceBOMFolderApplicationRelModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.commerce.bom.model.impl.CommerceBOMFolderApplicationRelImpl")
@ProviderType
public interface CommerceBOMFolderApplicationRel
	extends CommerceBOMFolderApplicationRelModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.commerce.bom.model.impl.CommerceBOMFolderApplicationRelImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CommerceBOMFolderApplicationRel, Long> COMMERCE_BOM_FOLDER_APPLICATION_REL_ID_ACCESSOR =
		new Accessor<CommerceBOMFolderApplicationRel, Long>() {
			@Override
			public Long get(
				CommerceBOMFolderApplicationRel commerceBOMFolderApplicationRel) {
				return commerceBOMFolderApplicationRel.getCommerceBOMFolderApplicationRelId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<CommerceBOMFolderApplicationRel> getTypeClass() {
				return CommerceBOMFolderApplicationRel.class;
			}
		};

	public com.liferay.commerce.application.model.CommerceApplicationModel getCommerceApplicationModel()
		throws com.liferay.portal.kernel.exception.PortalException;

	public CommerceBOMFolder getCommerceBOMFolder()
		throws com.liferay.portal.kernel.exception.PortalException;
}