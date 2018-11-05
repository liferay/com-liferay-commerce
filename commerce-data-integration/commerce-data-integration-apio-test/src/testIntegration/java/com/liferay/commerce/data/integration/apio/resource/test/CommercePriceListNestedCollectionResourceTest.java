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
public class CommercePriceListNestedCollectionResourceTest
	extends PortalContextProvider {

	public static final String COMMERCE_PRICE_LIST_RESOURCE_NAME =
		"commercePriceLists";

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
		_addPriceList(RandomTestUtil.randomString());
	}

	@Test
	public void testCollectionPostOperationPresent()
		throws IOException, PortalException {

		ApioResourceCollection commerceAccountApioResourceCollection =
			getSiteRelatedApioResourceCollection(
				COMMERCE_PRICE_LIST_RESOURCE_NAME, _group.getDescriptiveName());

		Operation resourceOperation = ApioUtils.getResourceOperationByMethod(
			commerceAccountApioResourceCollection, Method.POST);

		Assert.assertThat(resourceOperation, notNullValue());
	}

	@Test
	public void testDeleteCollectionItem() throws IOException, PortalException {
		ApioResourceCollection commercePriceListApioResourceCollection =
			getSiteRelatedApioResourceCollection(
				COMMERCE_PRICE_LIST_RESOURCE_NAME, _group.getDescriptiveName());

		int numberOfItems1 =
			commercePriceListApioResourceCollection.getNumberOfItems();

		String priceListId = _addPriceList(RandomTestUtil.randomString());

		commercePriceListApioResourceCollection =
			getSiteRelatedApioResourceCollection(
				COMMERCE_PRICE_LIST_RESOURCE_NAME, _group.getDescriptiveName());

		int numberOfItems2 =
			commercePriceListApioResourceCollection.getNumberOfItems();

		Assert.assertThat(numberOfItems1 + 1, equalTo(numberOfItems2));

		try (RESTClient restClient = new RESTClient()) {
			restClient.executeDeleteRequest(priceListId);
		}

		commercePriceListApioResourceCollection =
			getSiteRelatedApioResourceCollection(
				COMMERCE_PRICE_LIST_RESOURCE_NAME, _group.getDescriptiveName());

		int numberOfItems3 =
			commercePriceListApioResourceCollection.getNumberOfItems();

		Assert.assertThat(numberOfItems1, equalTo(numberOfItems3));
	}

	@Test
	public void testUpdateCollectionItem() throws IOException, PortalException {
		String priceListName = RandomTestUtil.randomString();

		_addPriceList(priceListName);

		ApioResourceCollection commercePriceListApioResourceCollection =
			getSiteRelatedApioResourceCollection(
				COMMERCE_PRICE_LIST_RESOURCE_NAME, _group.getDescriptiveName());

		ApioSingleModel actualPriceListApioSingleModel =
			getSingleResourceByField(
				commercePriceListApioResourceCollection,
				SchemaOrgConstants.Property.NAME, priceListName);

		String newPriceListName = RandomTestUtil.randomString();
		JsonNode actualPriceListIdJsonNode =
			actualPriceListApioSingleModel.getIdJsonNode();

		Map<String, String> priceListPropertiesMap = new HashMap<>(
			_propertiesMap);

		priceListPropertiesMap.put(
			SchemaOrgConstants.Property.NAME, newPriceListName);

		ObjectNode expectedObjectNode = constructExpectedObjectNode(
			actualPriceListApioSingleModel, Method.PUT, priceListPropertiesMap);

		try (RESTClient restClient = new RESTClient()) {
			restClient.executePutRequest(
				actualPriceListIdJsonNode.asText(), expectedObjectNode);
		}

		commercePriceListApioResourceCollection =
			getSiteRelatedApioResourceCollection(
				COMMERCE_PRICE_LIST_RESOURCE_NAME, _group.getDescriptiveName());

		ApioSingleModel updatedPriceListApioSingleModel =
			getSingleResourceByField(
				commercePriceListApioResourceCollection,
				SchemaOrgConstants.Property.NAME, newPriceListName);

		JsonNode updatedPriceListIdJsonNode =
			updatedPriceListApioSingleModel.getIdJsonNode();

		Assert.assertThat(
			actualPriceListIdJsonNode.asText(),
			equalTo(updatedPriceListIdJsonNode.asText()));
	}

	private String _addPriceList(String name)
		throws IOException, PortalException {

		ApioResourceCollection commercePriceListApioResourceCollection =
			getSiteRelatedApioResourceCollection(
				COMMERCE_PRICE_LIST_RESOURCE_NAME, _group.getDescriptiveName());

		int numberOfItems =
			commercePriceListApioResourceCollection.getNumberOfItems();

		Map<String, String> priceListPropertiesMap = new HashMap<>(
			_propertiesMap);

		priceListPropertiesMap.put(SchemaOrgConstants.Property.NAME, name);

		ObjectNode expectedObjectNode = constructExpectedObjectNode(
			commercePriceListApioResourceCollection, Method.POST,
			priceListPropertiesMap);

		JsonNode commercePriceListIdJsonNode =
			commercePriceListApioResourceCollection.getIdJsonNode();

		String messageEntity = null;

		try (RESTClient restClient = new RESTClient()) {
			messageEntity = restClient.executePostRequest(
				commercePriceListIdJsonNode.asText(), expectedObjectNode);
		}

		JsonNode jsonNode = objectMapper.readTree(messageEntity);

		ApioSingleModel apioSingleModel = new ApioSingleModel(jsonNode);

		JsonNode apioSingleModelIdJsonNode = apioSingleModel.getIdJsonNode();

		ApioResourceCollection actualCommercePriceListApioResourceCollection =
			getSiteRelatedApioResourceCollection(
				COMMERCE_PRICE_LIST_RESOURCE_NAME, _group.getDescriptiveName());

		int actualNumberOfItems =
			actualCommercePriceListApioResourceCollection.getNumberOfItems();

		Assert.assertThat(numberOfItems + 1, equalTo(actualNumberOfItems));

		return apioSingleModelIdJsonNode.asText();
	}

	private static final Map<String, String> _propertiesMap =
		new HashMap<String, String>() {
			{
				put(
					SchemaOrgConstants.Property.NAME,
					RandomTestUtil.randomString());
				put("active", Boolean.TRUE.toString());
				put("currency", "USD");
				put("neverExpire", Boolean.TRUE.toString());
				put("priority", "0");
			}
		};

	@DeleteAfterTestRun
	private Group _group;

}