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

package com.liferay.commerce.batch.engine.api.item;

import java.io.File;

/**
 * @author Ivica Cardic
 */
public abstract class BaseFileItemReader<T>
	extends BaseItemReader<T> implements FileAwareItemReader<T> {

	@Override
	public void setFile(File file) {
		this.file = file;
	}

	@Override
	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}

	protected File file;
	protected String originalFilename;

}