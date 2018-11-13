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
	}

	@Test
	public void testAddCollectionItem() throws IOException, PortalException {
		_addCountry(RandomTestUtil.randomString());
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

		int numberOfItems1 =
			commerceCountryApioResourceCollection.getNumberOfItems();

		String countryId = _addCountry(RandomTestUtil.randomString());

		commerceCountryApioResourceCollection =
			getSiteRelatedApioResourceCollection(
				COMMERCE_COUNTRY_RESOURCE_NAME, _group.getDescriptiveName());

		int numberOfItems2 =
			commerceCountryApioResourceCollection.getNumberOfItems();

		Assert.assertThat(numberOfItems1 + 1, equalTo(numberOfItems2));

		try (RESTClient restClient = new RESTClient()) {
			restClient.executeDeleteRequest(countryId);
		}

		commerceCountryApioResourceCollection =
			getSiteRelatedApioResourceCollection(
				COMMERCE_COUNTRY_RESOURCE_NAME, _group.getDescriptiveName());

		int numberOfItems3 =
			commerceCountryApioResourceCollection.getNumberOfItems();

		Assert.assertThat(numberOfItems1, equalTo(numberOfItems3));
	}

	@Test
	public void testUpdateCollectionItem() throws IOException, PortalException {
		String countryName = RandomTestUtil.randomString();

		_addCountry(countryName);

		ApioResourceCollection commerceCountryApioResourceCollection =
			getSiteRelatedApioResourceCollection(
				COMMERCE_COUNTRY_RESOURCE_NAME, _group.getDescriptiveName());

		ApioSingleModel actualCountryApioSingleModel = getSingleResourceByField(
			commerceCountryApioResourceCollection,
			SchemaOrgConstants.Property.NAME, countryName);

		String newCountryName = RandomTestUtil.randomString();
		JsonNode actualCountryIdJsonNode =
			actualCountryApioSingleModel.getIdJsonNode();

		Map<String, String> countryPropertiesMap = new HashMap<>(
			_propertiesMap);

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

	private String _addCountry(String name)
		throws IOException, PortalException {

		ApioResourceCollection commerceCountryApioResourceCollection =
			getSiteRelatedApioResourceCollection(
				COMMERCE_COUNTRY_RESOURCE_NAME, _group.getDescriptiveName());

		int numberOfItems =
			commerceCountryApioResourceCollection.getNumberOfItems();

		Map<String, String> countryPropertiesMap = new HashMap<>(
			_propertiesMap);

		countryPropertiesMap.put(SchemaOrgConstants.Property.NAME, name);

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

		int actualNumberOfItems =
			actualCommerceCountryApioResourceCollection.getNumberOfItems();

		Assert.assertThat(numberOfItems + 1, equalTo(actualNumberOfItems));

		return apioSingleModelIdJsonNode.asText();
	}

	private static final Map<String, String> _propertiesMap =
		new HashMap<String, String>() {
			{
				long randomInt = RandomTestUtil.randomInt();
				put(
					SchemaOrgConstants.Property.NAME,
					RandomTestUtil.randomString());
				put("numericISOCode", Long.valueOf(randomInt).toString());
				put("threeLettersISOCode", RandomTestUtil.randomString(3));
				put("twoLettersISOCode", RandomTestUtil.randomString(2));
			}
		};

	@DeleteAfterTestRun
	private Group _group;

}