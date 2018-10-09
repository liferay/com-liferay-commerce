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

package com.liferay.commerce.data.integration.apio.end.to.end.resource.test;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.commerce.apio.jsonld.representation.util.ApioEntryPoint;
import com.liferay.commerce.data.integration.apio.end.to.end.client.RESTClient;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.io.IOException;

import java.util.Map;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Zoltán Takács
 */
@RunWith(Arquillian.class)
public class BasicEndToEndTest extends PortalContextProvider {

	public static final String DEFAULT_SITE_NAME = "Global";

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void testDefaultSiteExistance() throws IOException {
		Map<String, String> availableWebSites = getAvailableWebSites();

		Assert.assertThat(
			availableWebSites.values(), hasItems(DEFAULT_SITE_NAME));
	}

	@Test
	public void testPortalURL() throws IOException {
		String portalURL = getPortalURL();

		try (RESTClient restClient = new RESTClient()) {
			restClient.executeGetRequest(portalURL);
		}

		Assert.assertThat(portalURL, is(notNullValue()));
	}

	@Test
	public void testRootEndpoints() throws IOException {
		ApioEntryPoint apioEntryPoint = getApioEntryPoint();

		Map<String, String> rootEndpointMap =
			apioEntryPoint.getRootEndpointMap();

		String[] expectedResources = {"Person", WEBSITE};

		Assert.assertThat(
			rootEndpointMap.values(), hasItems(expectedResources));
	}

}