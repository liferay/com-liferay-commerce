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
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;

import java.net.URI;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.junit.Assert;
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
		JSONObject jsonObject = _getResourceDTO(_PRICE_LIST_NAME);

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

	@Test
	public void testDeleteResourceItem() {
		testAddResourceItem();

		ValidatableResponse resourceValidatableResponse =
			_getValidatedResourceCollection();

		resourceValidatableResponse.assertThat(
		).body(
			"totalItems", equalTo(1)
		);

		JsonPath jsonPath = resourceValidatableResponse.extract().jsonPath();

		long resourceId = _getIdByName(jsonPath, _PRICE_LIST_NAME);

		RestAssured.given(
		).request(
		).auth(
		).preemptive(
		).basic(
			USER, PASSWORD
		).accept(
			ContentType.JSON
		).when(
		).delete(
			_getPriceListResourceURL(resourceId)
		).then(
		).statusCode(
			Response.Status.NO_CONTENT.getStatusCode()
		);

		resourceValidatableResponse = _getValidatedResourceCollection();

		resourceValidatableResponse.assertThat(
		).body(
			"totalItems", equalTo(0)
		);
	}

	@Test
	public void testGetResourceItems() {
		testAddResourceItem();

		JsonPath jsonPath = _getResourceCollectionJsonPath();

		String priceListName = jsonPath.param(
			"priceListName", _PRICE_LIST_NAME
		).get(
			"items.find { item -> item.name == priceListName }.name"
		);

		Assert.assertEquals(_PRICE_LIST_NAME, priceListName);
	}

	@Test
	public void testUpdateResourceItem() {
		testAddResourceItem();
		JsonPath jsonPath = _getResourceCollectionJsonPath();

		long resourceId = _getIdByName(jsonPath, _PRICE_LIST_NAME);

		JSONObject jsonObject = _getResourceDTO(_UPDATED_PRICE_LIST_NAME);

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
		).put(
			_getPriceListResourceURL(resourceId)
		).then(
		).statusCode(
			Response.Status.NO_CONTENT.getStatusCode()
		);

		// Verify if it's updated

		jsonPath = _getResourceCollectionJsonPath();

		String priceListName = jsonPath.param(
			"priceListName", _UPDATED_PRICE_LIST_NAME
		).get(
			"items.find { item -> item.name == priceListName }.name"
		);

		Assert.assertEquals(_UPDATED_PRICE_LIST_NAME, priceListName);

		long updatedItemId = _getIdByName(jsonPath, _UPDATED_PRICE_LIST_NAME);

		Assert.assertEquals(resourceId, updatedItemId);
	}

	private long _getIdByName(JsonPath jsonPath, String priceListName) {
		return jsonPath.param(
			"priceListName", priceListName
		).getLong(
			"items.find { item -> item.name == priceListName }.id"
		);
	}

	private String _getPriceListResourceURL() {
		return _getPriceListResourceURL(null);
	}

	private String _getPriceListResourceURL(Long id) {
		UriBuilder uriBuilder = UriBuilder.fromPath(getRootEndpointURL());

		URI uri = uriBuilder.path(
			_API_VERSION
		).path(
			"priceList"
		).path(
			(id == null) ? "" : String.valueOf(id)
		).build();

		return uri.toString();
	}

	private JsonPath _getResourceCollectionJsonPath() {
		ValidatableResponse validatableResponse =
			_getValidatedResourceCollection();

		return validatableResponse.extract().jsonPath();
	}

	private JSONObject _getResourceDTO(String name) {
		JSONObject jsonObject = _jsonFactory.createJSONObject();

		jsonObject.put("active", true);
		jsonObject.put("currency", "USD");
		jsonObject.put("name", name);
		jsonObject.put("neverExpire", true);
		jsonObject.put("priority", 0);

		return jsonObject;
	}

	private ValidatableResponse _getValidatedResourceCollection() {
		return RestAssured.given(
		).request(
		).auth(
		).preemptive(
		).basic(
			USER, PASSWORD
		).accept(
			ContentType.JSON
		).queryParam(
			"groupId", _group.getGroupId()
		).when(
		).get(
			_getPriceListResourceURL()
		).then(
		).statusCode(
			Response.Status.OK.getStatusCode()
		);
	}

	private static final String _API_VERSION = "v1.0/";

	private static final String _PRICE_LIST_NAME =
		RandomTestUtil.randomString();

	private static final String _UPDATED_PRICE_LIST_NAME =
		RandomTestUtil.randomString();

	@DeleteAfterTestRun
	private Group _group;

	@Inject
	private JSONFactory _jsonFactory;

}