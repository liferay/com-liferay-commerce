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
 * The extended model interface for the CommerceBOMDefinition service. Represents a row in the &quot;CommerceBOMDefinition&quot; database table, with each column mapped to a property of this class.
 *
 * @author Luca Pellizzon
 * @see CommerceBOMDefinitionModel
 * @see com.liferay.commerce.bom.model.impl.CommerceBOMDefinitionImpl
 * @see com.liferay.commerce.bom.model.impl.CommerceBOMDefinitionModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.commerce.bom.model.impl.CommerceBOMDefinitionImpl")
@ProviderType
public interface CommerceBOMDefinition extends CommerceBOMDefinitionModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.commerce.bom.model.impl.CommerceBOMDefinitionImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CommerceBOMDefinition, Long> COMMERCE_BOM_DEFINITION_ID_ACCESSOR =
		new Accessor<CommerceBOMDefinition, Long>() {
			@Override
			public Long get(CommerceBOMDefinition commerceBOMDefinition) {
				return commerceBOMDefinition.getCommerceBOMDefinitionId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<CommerceBOMDefinition> getTypeClass() {
				return CommerceBOMDefinition.class;
			}
		};

	public CommerceBOMFolder fetchCommerceBOMFolder();

	public com.liferay.commerce.product.model.CPAttachmentFileEntry fetchCPAttachmentFileEntry();
}