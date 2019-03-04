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
 * The extended model interface for the CommerceBatchJobExecution service. Represents a row in the &quot;CommerceBatchJobExecution&quot; database table, with each column mapped to a property of this class.
 *
 * @author Matija Petanjek
 * @see CommerceBatchJobExecutionModel
 * @see com.liferay.commerce.batch.model.impl.CommerceBatchJobExecutionImpl
 * @see com.liferay.commerce.batch.model.impl.CommerceBatchJobExecutionModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.commerce.batch.model.impl.CommerceBatchJobExecutionImpl")
@ProviderType
public interface CommerceBatchJobExecution
	extends CommerceBatchJobExecutionModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.commerce.batch.model.impl.CommerceBatchJobExecutionImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CommerceBatchJobExecution, Long> COMMERCE_BATCH_JOB_EXECUTION_ID_ACCESSOR =
		new Accessor<CommerceBatchJobExecution, Long>() {
			@Override
			public Long get(CommerceBatchJobExecution commerceBatchJobExecution) {
				return commerceBatchJobExecution.getCommerceBatchJobExecutionId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<CommerceBatchJobExecution> getTypeClass() {
				return CommerceBatchJobExecution.class;
			}
		};
}