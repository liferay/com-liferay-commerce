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

import com.liferay.commerce.batch.engine.api.exception.NoSuchItemException;

/**
 * @author Ivica Cardic
 */
public interface ItemRegistry {

	public <T> T get(
			String type, String version, Operation operation,
			String contentType, Class<T> clazz)
		throws NoSuchItemException;

	public void unget(Object service);

}