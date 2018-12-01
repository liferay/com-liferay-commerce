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
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.io.IOException;

import java.util.HashMap;
import java.util.Locale;
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
public class CommerceUserCollectionResourceTest extends PortalContextProvider {

	public static final String COMMERCE_USER_RESOURCE_TYPE = "Person";

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void testAddCollectionItem() throws IOException, PortalException {
		String emailAddress = RandomTestUtil.randomString() + _EMAIL_DOMAIN;

		_addCommerceUser(emailAddress);

		User user = _userLocalService.getUserByEmailAddress(
			getCompany().getCompanyId(), emailAddress);

		_userLocalService.deleteUser(user);
	}

	@Test
	public void testCollectionPostOperationPresent() throws IOException {
		ApioResourceCollection commerceUserResourceCollection =
			getRootApioResourceCollection(COMMERCE_USER_RESOURCE_TYPE);

		Operation resourceOperation = ApioUtils.getResourceOperationByMethod(
			commerceUserResourceCollection, Method.POST);

		Assert.assertThat(resourceOperation, notNullValue());
	}

	@Test
	public void testDeleteCollectionItem() throws IOException, PortalException {
		String emailAddress = RandomTestUtil.randomString() + _EMAIL_DOMAIN;

		ApioResourceCollection commerceUserResourceCollection =
			getRootApioResourceCollection(COMMERCE_USER_RESOURCE_TYPE);

		int numberOfItems1 = commerceUserResourceCollection.getNumberOfItems();

		String commerceUserId = _addCommerceUser(emailAddress);

		commerceUserResourceCollection = getRootApioResourceCollection(
			COMMERCE_USER_RESOURCE_TYPE);

		int numberOfItems2 = commerceUserResourceCollection.getNumberOfItems();

		Assert.assertThat(numberOfItems1 + 1, equalTo(numberOfItems2));

		try (RESTClient restClient = new RESTClient()) {
			restClient.executeDeleteRequest(commerceUserId);
		}

		commerceUserResourceCollection = getRootApioResourceCollection(
			COMMERCE_USER_RESOURCE_TYPE);

		int numberOfItems3 = commerceUserResourceCollection.getNumberOfItems();

		Assert.assertThat(numberOfItems1, equalTo(numberOfItems3));
	}

	@Test
	public void testUpdateCollectionItem() throws IOException, PortalException {
		String emailAddress = RandomTestUtil.randomString() + _EMAIL_DOMAIN;

		_addCommerceUser(emailAddress);

		ApioResourceCollection commerceUserApioResourceCollection =
			getRootApioResourceCollection(COMMERCE_USER_RESOURCE_TYPE);

		ApioSingleModel actualCommerceUserApioSingleModel =
			getSingleResourceByField(
				commerceUserApioResourceCollection, _EMAIL,
				emailAddress.toLowerCase(Locale.getDefault()));

		String newEmailAddress = RandomTestUtil.randomString() + _EMAIL_DOMAIN;
		JsonNode actualCommerceUserIdJsonNode =
			actualCommerceUserApioSingleModel.getIdJsonNode();

		Map<String, String> commerceUserPropertiesMap =
			new HashMap<String, String>() {
				{
					put(_EMAIL, newEmailAddress);
				}
			};

		ObjectNode expectedObjectNode = constructExpectedObjectNode(
			actualCommerceUserApioSingleModel, Method.PUT,
			commerceUserPropertiesMap);

		try (RESTClient restClient = new RESTClient()) {
			restClient.executePutRequest(
				actualCommerceUserIdJsonNode.asText(), expectedObjectNode);
		}

		commerceUserApioResourceCollection = getRootApioResourceCollection(
			COMMERCE_USER_RESOURCE_TYPE);

		ApioSingleModel updatedCommerceUserApioSingleModel =
			getSingleResourceByField(
				commerceUserApioResourceCollection, _EMAIL,
				newEmailAddress.toLowerCase(Locale.getDefault()));

		JsonNode updatedCommerceUserIdJsonNode =
			updatedCommerceUserApioSingleModel.getIdJsonNode();

		Assert.assertThat(
			actualCommerceUserIdJsonNode.asText(),
			equalTo(updatedCommerceUserIdJsonNode.asText()));

		User user = _userLocalService.getUserByEmailAddress(
			getCompany().getCompanyId(), newEmailAddress);

		_userLocalService.deleteUser(user);
	}

	private String _addCommerceUser(String emailAddress)
		throws IOException, PortalException {

		ApioResourceCollection commerceUserResourceCollection =
			getRootApioResourceCollection(COMMERCE_USER_RESOURCE_TYPE);

		int numberOfItems = commerceUserResourceCollection.getNumberOfItems();

		Map<String, String> commerceUserPropertiesMap =
			new HashMap<String, String>() {
				{
					put(_EMAIL, emailAddress);
				}
			};

		ObjectNode expectedObjectNode = constructExpectedObjectNode(
			commerceUserResourceCollection, Method.POST,
			commerceUserPropertiesMap);

		JsonNode commerceUserIdJsonNode =
			commerceUserResourceCollection.getIdJsonNode();

		String messageEntity = null;

		try (RESTClient restClient = new RESTClient()) {
			messageEntity = restClient.executePostRequest(
				commerceUserIdJsonNode.asText(), expectedObjectNode);
		}

		JsonNode jsonNode = objectMapper.readTree(messageEntity);

		ApioSingleModel apioSingleModel = new ApioSingleModel(jsonNode);

		JsonNode apioSingleModelIdJsonNode = apioSingleModel.getIdJsonNode();

		ApioResourceCollection actualCommerceUserApioResourceCollection =
			getRootApioResourceCollection(COMMERCE_USER_RESOURCE_TYPE);

		int actualNumberOfItems =
			actualCommerceUserApioResourceCollection.getNumberOfItems();

		Assert.assertThat(numberOfItems + 1, equalTo(actualNumberOfItems));

		return apioSingleModelIdJsonNode.asText();
	}

	private static final String _EMAIL = "email";

	private static final String _EMAIL_DOMAIN = "@liferay.com";

	@Inject
	private UserLocalService _userLocalService;

}