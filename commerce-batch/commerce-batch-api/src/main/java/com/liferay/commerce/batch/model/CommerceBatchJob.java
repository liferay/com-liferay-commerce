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
 * The extended model interface for the CommerceBatchJob service. Represents a row in the &quot;CommerceBatchJob&quot; database table, with each column mapped to a property of this class.
 *
 * @author Matija Petanjek
 * @see CommerceBatchJobModel
 * @see com.liferay.commerce.batch.model.impl.CommerceBatchJobImpl
 * @see com.liferay.commerce.batch.model.impl.CommerceBatchJobModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.commerce.batch.model.impl.CommerceBatchJobImpl")
@ProviderType
public interface CommerceBatchJob extends CommerceBatchJobModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.commerce.batch.model.impl.CommerceBatchJobImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CommerceBatchJob, Long> COMMERCE_BATCH_JOB_ID_ACCESSOR =
		new Accessor<CommerceBatchJob, Long>() {
			@Override
			public Long get(CommerceBatchJob commerceBatchJob) {
				return commerceBatchJob.getCommerceBatchJobId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<CommerceBatchJob> getTypeClass() {
				return CommerceBatchJob.class;
			}
		};
}