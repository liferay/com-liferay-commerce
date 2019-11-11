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
 * The extended model interface for the CommerceBOMEntry service. Represents a row in the &quot;CommerceBOMEntry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Luca Pellizzon
 * @see CommerceBOMEntryModel
 * @see com.liferay.commerce.bom.model.impl.CommerceBOMEntryImpl
 * @see com.liferay.commerce.bom.model.impl.CommerceBOMEntryModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.commerce.bom.model.impl.CommerceBOMEntryImpl")
@ProviderType
public interface CommerceBOMEntry extends CommerceBOMEntryModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.commerce.bom.model.impl.CommerceBOMEntryImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CommerceBOMEntry, Long> COMMERCE_BOM_ENTRY_ID_ACCESSOR =
		new Accessor<CommerceBOMEntry, Long>() {
			@Override
			public Long get(CommerceBOMEntry commerceBOMEntry) {
				return commerceBOMEntry.getCommerceBOMEntryId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<CommerceBOMEntry> getTypeClass() {
				return CommerceBOMEntry.class;
			}
		};
}