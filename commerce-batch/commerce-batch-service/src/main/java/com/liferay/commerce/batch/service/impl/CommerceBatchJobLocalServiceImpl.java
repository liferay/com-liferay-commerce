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

package com.liferay.commerce.batch.service.impl;

import com.liferay.commerce.batch.engine.api.job.BatchStatus;
import com.liferay.commerce.batch.exception.NoSuchBatchJobException;
import com.liferay.commerce.batch.model.CommerceBatchJob;
import com.liferay.commerce.batch.service.base.CommerceBatchJobLocalServiceBaseImpl;

/**
 * @author Matija Petanjek
 */
public class CommerceBatchJobLocalServiceImpl
	extends CommerceBatchJobLocalServiceBaseImpl {

	@Override
	public CommerceBatchJob addCommerceBatchJob(String key, String name) {
		CommerceBatchJob commerceBatchJob = commerceBatchJobPersistence.create(
			counterLocalService.increment(CommerceBatchJob.class.getName()));

		commerceBatchJob.setKey(key);
		commerceBatchJob.setName(name);
		commerceBatchJob.setStatus(BatchStatus.UNKNOWN.toString());

		return commerceBatchJobPersistence.update(commerceBatchJob);
	}

	@Override
	public String getStatus(String key) throws NoSuchBatchJobException {
		CommerceBatchJob commerceBatchJob =
			commerceBatchJobPersistence.findByKey(key);

		return commerceBatchJob.getStatus();
	}

}