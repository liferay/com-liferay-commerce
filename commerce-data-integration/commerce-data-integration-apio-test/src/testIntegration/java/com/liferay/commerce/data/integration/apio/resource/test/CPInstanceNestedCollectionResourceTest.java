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

package com.liferay.commerce.data.integration.apio.resource.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.commerce.apio.jsonld.representation.util.ApioResourceCollection;
import com.liferay.commerce.apio.jsonld.representation.util.ApioSingleModel;
import com.liferay.commerce.apio.jsonld.representation.util.ApioUtils;
import com.liferay.commerce.apio.jsonld.representation.util.operation.Method;
import com.liferay.commerce.apio.jsonld.representation.util.operation.Operation;
import com.liferay.commerce.data.integration.apio.client.RESTClient;
import com.liferay.commerce.data.integration.apio.resource.test.utils.CommerceTestSiteActivator;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

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
public class CPInstanceNestedCollectionResourceTest
	extends PortalContextProvider {

	public static final String COMMERCE_PRODUCT_INSTANCE_RESOURCE_NAME =
		"commerceProductInstances";

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
	public void testAddCollectionItem() throws IOException, PortalException {
		_addCPInstance(RandomTestUtil.randomString());
	}

	@Test
	public void testCollectionPostOperationPresent()
		throws IOException, PortalException {

		ApioResourceCollection cpInstanceApioResourceCollection =
			getSiteRelatedApioResourceCollection(
				COMMERCE_PRODUCT_INSTANCE_RESOURCE_NAME,
				_group.getDescriptiveName());

		Operation resourceOperation = ApioUtils.getResourceOperationByMethod(
			cpInstanceApioResourceCollection, Method.POST);

		Assert.assertThat(resourceOperation, notNullValue());
	}

	@Test
	public void testDeleteCollectionItem() throws IOException, PortalException {
		ApioResourceCollection cpInstanceApioResourceCollection =
			getSiteRelatedApioResourceCollection(
				COMMERCE_PRODUCT_INSTANCE_RESOURCE_NAME,
				_group.getDescriptiveName());

		int numberOfItems1 =
			cpInstanceApioResourceCollection.getNumberOfItems();

		String cpInstanceId = _addCPInstance(RandomTestUtil.randomString());

		cpInstanceApioResourceCollection = getSiteRelatedApioResourceCollection(
			COMMERCE_PRODUCT_INSTANCE_RESOURCE_NAME,
			_group.getDescriptiveName());

		int numberOfItems2 =
			cpInstanceApioResourceCollection.getNumberOfItems();

		Assert.assertThat(numberOfItems1 + 1, equalTo(numberOfItems2));

		try (RESTClient restClient = new RESTClient()) {
			restClient.executeDeleteRequest(cpInstanceId);
		}

		cpInstanceApioResourceCollection = getSiteRelatedApioResourceCollection(
			COMMERCE_PRODUCT_INSTANCE_RESOURCE_NAME,
			_group.getDescriptiveName());

		int numberOfItems3 =
			cpInstanceApioResourceCollection.getNumberOfItems();

		Assert.assertThat(numberOfItems1, equalTo(numberOfItems3));
	}

	@Test
	public void testUpdateCollectionItem() throws IOException, PortalException {
		String cpInstanceTitle = RandomTestUtil.randomString();

		_addCPInstance(cpInstanceTitle);

		ApioResourceCollection cpInstanceApioResourceCollection =
			getSiteRelatedApioResourceCollection(
				COMMERCE_PRODUCT_INSTANCE_RESOURCE_NAME,
				_group.getDescriptiveName());

		ApioSingleModel actualcpInstanceApioSingleModel =
			getSingleResourceByField(
				cpInstanceApioResourceCollection, _SKU, cpInstanceTitle);

		String newcpInstanceTitle = RandomTestUtil.randomString();
		JsonNode actualcpInstanceIdJsonNode =
			actualcpInstanceApioSingleModel.getIdJsonNode();

		Map<String, String> cpInstancePropertiesMap = new HashMap<>(
			_propertiesMap);

		cpInstancePropertiesMap.put(_SKU, newcpInstanceTitle);

		ObjectNode expectedObjectNode = constructExpectedObjectNode(
			actualcpInstanceApioSingleModel, Method.PUT,
			cpInstancePropertiesMap);

		try (RESTClient restClient = new RESTClient()) {
			restClient.executePutRequest(
				actualcpInstanceIdJsonNode.asText(), expectedObjectNode);
		}

		cpInstanceApioResourceCollection = getSiteRelatedApioResourceCollection(
			COMMERCE_PRODUCT_INSTANCE_RESOURCE_NAME,
			_group.getDescriptiveName());

		ApioSingleModel updatedcpInstanceApioSingleModel =
			getSingleResourceByField(
				cpInstanceApioResourceCollection, _SKU, newcpInstanceTitle);

		JsonNode updatedcpInstanceIdJsonNode =
			updatedcpInstanceApioSingleModel.getIdJsonNode();

		Assert.assertThat(
			actualcpInstanceIdJsonNode.asText(),
			equalTo(updatedcpInstanceIdJsonNode.asText()));
	}

	private String _addCPInstance(String sku)
		throws IOException, PortalException {

		ApioResourceCollection cpInstanceApioResourceCollection =
			getSiteRelatedApioResourceCollection(
				COMMERCE_PRODUCT_INSTANCE_RESOURCE_NAME,
				_group.getDescriptiveName());

		int numberOfItems = cpInstanceApioResourceCollection.getNumberOfItems();

		Map<String, String> cpInstancePropertiesMap = new HashMap<>(
			_propertiesMap);

		cpInstancePropertiesMap.put(_SKU, sku);

		ObjectNode expectedObjectNode = constructExpectedObjectNode(
			cpInstanceApioResourceCollection, Method.POST,
			cpInstancePropertiesMap);

		JsonNode cpInstanceIdJsonNode =
			cpInstanceApioResourceCollection.getIdJsonNode();

		String messageEntity = null;

		try (RESTClient restClient = new RESTClient()) {
			messageEntity = restClient.executePostRequest(
				cpInstanceIdJsonNode.asText(), expectedObjectNode);
		}

		JsonNode jsonNode = objectMapper.readTree(messageEntity);

		ApioSingleModel apioSingleModel = new ApioSingleModel(jsonNode);

		JsonNode apioSingleModelIdJsonNode = apioSingleModel.getIdJsonNode();

		ApioResourceCollection actualcpInstanceApioResourceCollection =
			getSiteRelatedApioResourceCollection(
				COMMERCE_PRODUCT_INSTANCE_RESOURCE_NAME,
				_group.getDescriptiveName());

		int actualNumberOfItems =
			actualcpInstanceApioResourceCollection.getNumberOfItems();

		Assert.assertThat(numberOfItems + 1, equalTo(actualNumberOfItems));

		return apioSingleModelIdJsonNode.asText();
	}

	private static final String _SKU = "sku";

	private static final Map<String, String> _propertiesMap =
		new HashMap<String, String>() {
			{
				put("active", Boolean.TRUE.toString());
				put("productTypeName", "simple");
				put(
					"upsertProductDefinitionIfNotExist",
					Boolean.TRUE.toString());
			}
		};

	@DeleteAfterTestRun
	private Group _group;

}