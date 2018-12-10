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

package com.liferay.commerce.frontend.internal;

import com.liferay.commerce.frontend.ClayTableSchemaBuilder;
import com.liferay.commerce.frontend.ClayTableSchemaBuilderFactory;

import org.osgi.service.component.annotations.Component;

/**
 * @author Marco Leo
 */
@Component(service = ClayTableSchemaBuilderFactory.class)
public class ClayTableSchemaBuilderFactoryImpl
	implements ClayTableSchemaBuilderFactory {

	public ClayTableSchemaBuilder clayTableSchemaBuilder() {
		return new ClayTableSchemaBuilderImpl();
	}

}