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
public class CPDefinitionNestedCollectionResourceTest
	extends PortalContextProvider {

	public static final String COMMERCE_PRODUCT_DEFINITION_RESOURCE_NAME =
		"commerceProductDefinitions";

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
		_addCPDefinition(RandomTestUtil.randomString());
	}

	@Test
	public void testCollectionPostOperationPresent()
		throws IOException, PortalException {

		ApioResourceCollection cPDefinitionApioResourceCollection =
			getSiteRelatedApioResourceCollection(
				COMMERCE_PRODUCT_DEFINITION_RESOURCE_NAME,
				_group.getDescriptiveName());

		Operation resourceOperation = ApioUtils.getResourceOperationByMethod(
			cPDefinitionApioResourceCollection, Method.POST);

		Assert.assertThat(resourceOperation, notNullValue());
	}

	@Test
	public void testDeleteCollectionItem() throws IOException, PortalException {
		ApioResourceCollection cpDefinitionApioResourceCollection =
			getSiteRelatedApioResourceCollection(
				COMMERCE_PRODUCT_DEFINITION_RESOURCE_NAME,
				_group.getDescriptiveName());

		int numberOfItems1 =
			cpDefinitionApioResourceCollection.getNumberOfItems();

		String cpDefinitionId = _addCPDefinition(RandomTestUtil.randomString());

		cpDefinitionApioResourceCollection =
			getSiteRelatedApioResourceCollection(
				COMMERCE_PRODUCT_DEFINITION_RESOURCE_NAME,
				_group.getDescriptiveName());

		int numberOfItems2 =
			cpDefinitionApioResourceCollection.getNumberOfItems();

		Assert.assertThat(numberOfItems1 + 1, equalTo(numberOfItems2));

		try (RESTClient restClient = new RESTClient()) {
			restClient.executeDeleteRequest(cpDefinitionId);
		}

		cpDefinitionApioResourceCollection =
			getSiteRelatedApioResourceCollection(
				COMMERCE_PRODUCT_DEFINITION_RESOURCE_NAME,
				_group.getDescriptiveName());

		int numberOfItems3 =
			cpDefinitionApioResourceCollection.getNumberOfItems();

		Assert.assertThat(numberOfItems1, equalTo(numberOfItems3));
	}

	@Test
	public void testUpdateCollectionItem() throws IOException, PortalException {
		String cpDefinitionTitle = RandomTestUtil.randomString();

		_addCPDefinition(cpDefinitionTitle);

		ApioResourceCollection cpDefinitionApioResourceCollection =
			getSiteRelatedApioResourceCollection(
				COMMERCE_PRODUCT_DEFINITION_RESOURCE_NAME,
				_group.getDescriptiveName());

		ApioSingleModel actualCpDefinitionApioSingleModel =
			getSingleResourceByField(
				cpDefinitionApioResourceCollection, _TITLE, cpDefinitionTitle);

		String newCpDefinitionTitle = RandomTestUtil.randomString();
		JsonNode actualCpDefinitionIdJsonNode =
			actualCpDefinitionApioSingleModel.getIdJsonNode();

		Map<String, String> cpDefinitionPropertiesMap = new HashMap<>(
			_propertiesMap);

		cpDefinitionPropertiesMap.put(_TITLE, newCpDefinitionTitle);

		ObjectNode expectedObjectNode = constructExpectedObjectNode(
			actualCpDefinitionApioSingleModel, Method.PUT,
			cpDefinitionPropertiesMap);

		try (RESTClient restClient = new RESTClient()) {
			restClient.executePutRequest(
				actualCpDefinitionIdJsonNode.asText(), expectedObjectNode);
		}

		cpDefinitionApioResourceCollection =
			getSiteRelatedApioResourceCollection(
				COMMERCE_PRODUCT_DEFINITION_RESOURCE_NAME,
				_group.getDescriptiveName());

		ApioSingleModel updatedCpDefinitionApioSingleModel =
			getSingleResourceByField(
				cpDefinitionApioResourceCollection, _TITLE,
				newCpDefinitionTitle);

		JsonNode updatedCpDefinitionIdJsonNode =
			updatedCpDefinitionApioSingleModel.getIdJsonNode();

		Assert.assertThat(
			actualCpDefinitionIdJsonNode.asText(),
			equalTo(updatedCpDefinitionIdJsonNode.asText()));
	}

	private String _addCPDefinition(String title)
		throws IOException, PortalException {

		ApioResourceCollection cpDefinitionApioResourceCollection =
			getSiteRelatedApioResourceCollection(
				COMMERCE_PRODUCT_DEFINITION_RESOURCE_NAME,
				_group.getDescriptiveName());

		int numberOfItems =
			cpDefinitionApioResourceCollection.getNumberOfItems();

		Map<String, String> cpDefinitionPropertiesMap = new HashMap<>(
			_propertiesMap);

		cpDefinitionPropertiesMap.put(_TITLE, title);

		ObjectNode expectedObjectNode = constructExpectedObjectNode(
			cpDefinitionApioResourceCollection, Method.POST,
			cpDefinitionPropertiesMap);

		JsonNode cpDefinitionIdJsonNode =
			cpDefinitionApioResourceCollection.getIdJsonNode();

		String messageEntity = null;

		try (RESTClient restClient = new RESTClient()) {
			messageEntity = restClient.executePostRequest(
				cpDefinitionIdJsonNode.asText(), expectedObjectNode);
		}

		JsonNode jsonNode = objectMapper.readTree(messageEntity);

		ApioSingleModel apioSingleModel = new ApioSingleModel(jsonNode);

		JsonNode apioSingleModelIdJsonNode = apioSingleModel.getIdJsonNode();

		ApioResourceCollection actualCpDefinitionApioResourceCollection =
			getSiteRelatedApioResourceCollection(
				COMMERCE_PRODUCT_DEFINITION_RESOURCE_NAME,
				_group.getDescriptiveName());

		int actualNumberOfItems =
			actualCpDefinitionApioResourceCollection.getNumberOfItems();

		Assert.assertThat(numberOfItems + 1, equalTo(actualNumberOfItems));

		return apioSingleModelIdJsonNode.asText();
	}

	private static final String _TITLE = "title";

	private static final Map<String, String> _propertiesMap =
		new HashMap<String, String>() {
			{
				put(_TITLE, RandomTestUtil.randomString());
				put("active", Boolean.TRUE.toString());
				put("productTypeName", "simple");
			}
		};

	@DeleteAfterTestRun
	private Group _group;

}