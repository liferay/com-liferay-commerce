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

package com.liferay.commerce.openapi.util.config;

import java.util.List;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Igor Beslic
 */
public class ConfigurationFactoryTest {

	@Test
	public void testGetConfigurations() throws Exception {
		List<Properties> configurations =
			ConfigurationFactory.getConfigurations(
				ConfigurationFactory.getPath(getClass()));

		Assert.assertEquals(
			"detect all configurations", 2, configurations.size());

		String hostName = "host.name.";
		String authorizationKey = "KEY-";
		String url = "/test_api_url_";

		for (Properties properties : configurations) {
			String moduleName = properties.getProperty("osgi.module.name");

			Assert.assertNotNull("osgi.module.name key expected", moduleName);
			Assert.assertEquals(
				"key openapi.host.name", hostName + moduleName,
				properties.getProperty("openapi.host.name"));
			Assert.assertEquals(
				"key openapi.authorization.key", authorizationKey + moduleName,
				properties.getProperty("openapi.authorization.key"));
			Assert.assertEquals(
				"key openapi.url", url + moduleName,
				properties.getProperty("openapi.url"));
		}
	}

}