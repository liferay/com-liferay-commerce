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

package com.liferay.commerce.batch.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the CommerceBatchEntry service. Represents a row in the &quot;CommerceBatchEntry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Matija Petanjek
 * @see CommerceBatchEntryModel
 * @see com.liferay.commerce.batch.model.impl.CommerceBatchEntryImpl
 * @see com.liferay.commerce.batch.model.impl.CommerceBatchEntryModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.commerce.batch.model.impl.CommerceBatchEntryImpl")
@ProviderType
public interface CommerceBatchEntry extends CommerceBatchEntryModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.commerce.batch.model.impl.CommerceBatchEntryImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CommerceBatchEntry, Long> COMMERCE_BATCH_ENTRY_ID_ACCESSOR =
		new Accessor<CommerceBatchEntry, Long>() {
			@Override
			public Long get(CommerceBatchEntry commerceBatchEntry) {
				return commerceBatchEntry.getCommerceBatchEntryId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<CommerceBatchEntry> getTypeClass() {
				return CommerceBatchEntry.class;
			}
		};
}