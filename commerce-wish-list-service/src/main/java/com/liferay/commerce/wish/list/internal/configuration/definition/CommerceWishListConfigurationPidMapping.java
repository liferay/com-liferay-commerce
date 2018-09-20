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

package com.liferay.commerce.wish.list.internal.configuration.definition;

import com.liferay.commerce.wish.list.constants.CommerceWishListConstants;
import com.liferay.commerce.wish.list.internal.configuration.CommerceWishListConfiguration;
import com.liferay.portal.kernel.settings.definition.ConfigurationPidMapping;

import org.osgi.service.component.annotations.Component;

/**
 * @author Alessio Antonio Rendina
 */
@Component(service = ConfigurationPidMapping.class)
public class CommerceWishListConfigurationPidMapping
	implements ConfigurationPidMapping {

	@Override
	public Class<?> getConfigurationBeanClass() {
		return CommerceWishListConfiguration.class;
	}

	@Override
	public String getConfigurationPid() {
		return CommerceWishListConstants.WISH_LIST_SERVICE_NAME;
	}

}