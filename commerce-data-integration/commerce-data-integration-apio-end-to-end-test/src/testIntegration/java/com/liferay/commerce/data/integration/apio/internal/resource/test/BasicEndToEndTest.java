/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.commerce.data.integration.apio.internal.resource.test;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.commerce.apio.jsonld.representation.util.ApioEntryPoint;
import com.liferay.commerce.data.integration.apio.internal.client.RESTClient;
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