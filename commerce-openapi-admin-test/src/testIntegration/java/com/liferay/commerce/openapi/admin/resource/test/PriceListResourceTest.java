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

package com.liferay.commerce.openapi.admin.resource.test;

import static org.hamcrest.CoreMatchers.equalTo;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.commerce.openapi.admin.resource.test.utils.CommerceTestSiteActivator;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Zoltán Takács
 */
@RunWith(Arquillian.class)
public class PriceListResourceTest extends PortalContextProvider {

	public static final String PRICE_LIST = "priceList";

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();

		CommerceTestSiteActivator.initialize(_group.getGroupId());
	}

	@Test
	public void testAddResourceItem() {
		JSONObject jsonObject = _jsonFactory.createJSONObject();

		jsonObject.put("active", true);
		jsonObject.put("currency", "USD");
		jsonObject.put("name", RandomTestUtil.randomString());
		jsonObject.put("neverExpire", true);
		jsonObject.put("priority", 0);

		RestAssured.given(
		).request(
		).auth(
		).preemptive(
		).basic(
			USER, PASSWORD
		).accept(
			ContentType.JSON
		).contentType(
			ContentType.JSON
		).body(
			jsonObject.toString()
		).queryParam(
			"groupId", _group.getGroupId()
		).when(
		).post(
			_getPriceListResourceURL()
		).then(
		).statusCode(
			Response.Status.CREATED.getStatusCode()
		).assertThat(
		).body(
			"name", equalTo(jsonObject.getString("name"))
		);
	}

	private String _getPriceListResourceURL() {
		StringBundler sb = new StringBundler(3);

		sb.append(getRootEndpointURL());
		sb.append(_API_VERSION);
		sb.append("priceList");

		return sb.toString();
	}

	private static final String _API_VERSION = "v1.0/";

	@DeleteAfterTestRun
	private Group _group;

	@Inject
	private JSONFactory _jsonFactory;

}