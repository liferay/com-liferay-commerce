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
import com.liferay.commerce.apio.jsonld.representation.util.constants.SchemaOrgConstants;
import com.liferay.commerce.apio.jsonld.representation.util.operation.Method;
import com.liferay.commerce.apio.jsonld.representation.util.operation.Operation;
import com.liferay.commerce.data.integration.apio.client.RESTClient;
import com.liferay.commerce.data.integration.apio.resource.test.utils.CommerceTestSiteActivator;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.test.randomizerbumpers.UniqueStringRandomizerBumper;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
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
public class CommerceCountryNestedCollectionResourceTest
	extends PortalContextProvider {

	public static final String COMMERCE_COUNTRY_RESOURCE_NAME =
		"commerceCountries";

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();

		CommerceTestSiteActivator.initialize(_group.getGroupId());

		_randomizerBumper.reset();
	}

	@After
	public void tearDown() throws Exception {
		_randomizerBumper.reset();
	}

	@Test
	public void testAddCollectionItem() throws IOException, PortalException {
		_addCountry(_getCountryPropertiesMap(RandomTestUtil.randomString()));
	}

	@Test
	public void testCollectionPostOperationPresent()
		throws IOException, PortalException {

		ApioResourceCollection commerceCountryApioResourceCollection =
			getSiteRelatedApioResourceCollection(
				COMMERCE_COUNTRY_RESOURCE_NAME, _group.getDescriptiveName());

		Operation resourceOperation = ApioUtils.getResourceOperationByMethod(
			commerceCountryApioResourceCollection, Method.POST);

		Assert.assertThat(resourceOperation, notNullValue());
	}

	@Test
	public void testDeleteCollectionItem() throws IOException, PortalException {
		ApioResourceCollection commerceCountryApioResourceCollection =
			getSiteRelatedApioResourceCollection(
				COMMERCE_COUNTRY_RESOURCE_NAME, _group.getDescriptiveName());

		int totalItems1 = commerceCountryApioResourceCollection.getTotalItems();

		String countryId = _addCountry(
			_getCountryPropertiesMap(RandomTestUtil.randomString()));

		commerceCountryApioResourceCollection =
			getSiteRelatedApioResourceCollection(
				COMMERCE_COUNTRY_RESOURCE_NAME, _group.getDescriptiveName());

		int totalItems2 = commerceCountryApioResourceCollection.getTotalItems();

		Assert.assertThat(totalItems2, equalTo(totalItems1 + 1));

		try (RESTClient restClient = new RESTClient()) {
			restClient.executeDeleteRequest(countryId);
		}

		commerceCountryApioResourceCollection =
			getSiteRelatedApioResourceCollection(
				COMMERCE_COUNTRY_RESOURCE_NAME, _group.getDescriptiveName());

		int totalItems3 = commerceCountryApioResourceCollection.getTotalItems();

		Assert.assertThat(totalItems1, equalTo(totalItems3));
	}

	@Test
	public void testUpdateCollectionItem() throws IOException, PortalException {
		String countryName = RandomTestUtil.randomString();

		Map<String, String> countryPropertiesMap = _getCountryPropertiesMap(
			countryName);

		_addCountry(countryPropertiesMap);

		ApioResourceCollection commerceCountryApioResourceCollection =
			getSiteRelatedApioResourceCollection(
				COMMERCE_COUNTRY_RESOURCE_NAME, _group.getDescriptiveName());

		ApioSingleModel actualCountryApioSingleModel = getSingleResourceByField(
			commerceCountryApioResourceCollection,
			SchemaOrgConstants.Property.NAME, countryName);

		String newCountryName = RandomTestUtil.randomString();
		JsonNode actualCountryIdJsonNode =
			actualCountryApioSingleModel.getIdJsonNode();

		countryPropertiesMap.put(
			SchemaOrgConstants.Property.NAME, newCountryName);

		ObjectNode expectedObjectNode = constructExpectedObjectNode(
			actualCountryApioSingleModel, Method.PUT, countryPropertiesMap);

		try (RESTClient restClient = new RESTClient()) {
			restClient.executePutRequest(
				actualCountryIdJsonNode.asText(), expectedObjectNode);
		}

		commerceCountryApioResourceCollection =
			getSiteRelatedApioResourceCollection(
				COMMERCE_COUNTRY_RESOURCE_NAME, _group.getDescriptiveName());

		ApioSingleModel updatedCountryApioSingleModel =
			getSingleResourceByField(
				commerceCountryApioResourceCollection,
				SchemaOrgConstants.Property.NAME, newCountryName);

		JsonNode updatedCountryIdJsonNode =
			updatedCountryApioSingleModel.getIdJsonNode();

		Assert.assertThat(
			actualCountryIdJsonNode.asText(),
			equalTo(updatedCountryIdJsonNode.asText()));
	}

	private String _addCountry(Map<String, String> countryPropertiesMap)
		throws IOException, PortalException {

		ApioResourceCollection commerceCountryApioResourceCollection =
			getSiteRelatedApioResourceCollection(
				COMMERCE_COUNTRY_RESOURCE_NAME, _group.getDescriptiveName());

		int totalItems = commerceCountryApioResourceCollection.getTotalItems();

		ObjectNode expectedObjectNode = constructExpectedObjectNode(
			commerceCountryApioResourceCollection, Method.POST,
			countryPropertiesMap);

		JsonNode commerceCountryIdJsonNode =
			commerceCountryApioResourceCollection.getIdJsonNode();

		String messageEntity = null;

		try (RESTClient restClient = new RESTClient()) {
			messageEntity = restClient.executePostRequest(
				commerceCountryIdJsonNode.asText(), expectedObjectNode);
		}

		JsonNode jsonNode = objectMapper.readTree(messageEntity);

		ApioSingleModel apioSingleModel = new ApioSingleModel(jsonNode);

		JsonNode apioSingleModelIdJsonNode = apioSingleModel.getIdJsonNode();

		ApioResourceCollection actualCommerceCountryApioResourceCollection =
			getSiteRelatedApioResourceCollection(
				COMMERCE_COUNTRY_RESOURCE_NAME, _group.getDescriptiveName());

		int actualTotalItems =
			actualCommerceCountryApioResourceCollection.getTotalItems();

		Assert.assertThat(actualTotalItems, equalTo(totalItems + 1));

		return apioSingleModelIdJsonNode.asText();
	}

	private Map<String, String> _getCountryPropertiesMap(String name) {
		Map<String, String> countryPropertiesMap = new HashMap<>();

		countryPropertiesMap.put(SchemaOrgConstants.Property.NAME, name);
		countryPropertiesMap.put(
			"numericISOCode", String.valueOf(RandomTestUtil.randomInt()));
		countryPropertiesMap.put(
			"threeLettersISOCode", RandomTestUtil.randomString(3));
		countryPropertiesMap.put(
			"twoLettersISOCode",
			RandomTestUtil.randomString(2, _randomizerBumper));

		return countryPropertiesMap;
	}

	@DeleteAfterTestRun
	private Group _group;

	private final UniqueStringRandomizerBumper _randomizerBumper =
		new UniqueStringRandomizerBumper();

}