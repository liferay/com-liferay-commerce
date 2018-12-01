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
import com.liferay.commerce.organization.test.util.OrganizationTypeSetUp;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.OrganizationConstants;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.OrganizationTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerTestRule;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.osgi.service.cm.ConfigurationAdmin;

/**
 * @author Zoltán Takács
 */
@RunWith(Arquillian.class)
public class CommerceAddressNestedCollectionResourceTest
	extends PortalContextProvider {

	public static final String COMMERCE_ACCOUNT_RESOURCE_NAME =
		"commerceAccounts";

	public static final String COMMERCE_ADDRESS_RESOURCE_NAME =
		"commerceAddresses";

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerTestRule.INSTANCE);

	@BeforeClass
	public static void setUpClass() throws Exception {
		_organizationTypeSetUp = new OrganizationTypeSetUp(_configurationAdmin);

		_organizationTypeSetUp.setUpEnvironment();
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		_organizationTypeSetUp.tearDownEnvironment();
	}

	@Before
	public void setUp() throws Exception {
		_parentOrganization = OrganizationTestUtil.addOrganization(
			OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID,
			_TEST_PARENT_ORGANIZATION_NAME, true);

		CommerceTestSiteActivator.initialize(_parentOrganization.getGroupId());
	}

	@After
	public void tearDown() throws PortalException {
		if (_parentOrganization != null) {
			for (Organization organization :
					_parentOrganization.getDescendants()) {

				organizationLocalService.deleteOrganization(organization);
			}
		}

		organizationLocalService.deleteOrganization(_parentOrganization);
	}

	@Test
	public void testAddCollectionItem() throws IOException, PortalException {
		String accountExternalReferenceCode = _addCommerceAccount(
			RandomTestUtil.randomString());

		_addCommerceAddress(
			RandomTestUtil.randomString(), accountExternalReferenceCode);
	}

	@Test
	public void testCollectionPostOperationPresent() throws IOException {
		ApioResourceCollection commerceAccountApioResourceCollection =
			getSiteRelatedApioResourceCollection(
				COMMERCE_ADDRESS_RESOURCE_NAME, _TEST_PARENT_ORGANIZATION_NAME);

		Operation resourceOperation = ApioUtils.getResourceOperationByMethod(
			commerceAccountApioResourceCollection, Method.POST);

		Assert.assertThat(resourceOperation, notNullValue());
	}

	@Test
	public void testDeleteCollectionItem() throws IOException, PortalException {
		String accountExternalReferenceCode = _addCommerceAccount(
			RandomTestUtil.randomString());

		ApioResourceCollection commerceAddressApioResourceCollection =
			getSiteRelatedApioResourceCollection(
				COMMERCE_ADDRESS_RESOURCE_NAME, _TEST_PARENT_ORGANIZATION_NAME);

		int totalItems1 = commerceAddressApioResourceCollection.getTotalItems();

		String commerceAddressId = _addCommerceAddress(
			RandomTestUtil.randomString(), accountExternalReferenceCode);

		commerceAddressApioResourceCollection =
			getSiteRelatedApioResourceCollection(
				COMMERCE_ADDRESS_RESOURCE_NAME, _TEST_PARENT_ORGANIZATION_NAME);

		int totalItems2 = commerceAddressApioResourceCollection.getTotalItems();

		Assert.assertThat(totalItems1 + 1, equalTo(totalItems2));

		try (RESTClient restClient = new RESTClient()) {
			restClient.executeDeleteRequest(commerceAddressId);
		}

		commerceAddressApioResourceCollection =
			getSiteRelatedApioResourceCollection(
				COMMERCE_ADDRESS_RESOURCE_NAME, _TEST_PARENT_ORGANIZATION_NAME);

		int totalItems3 = commerceAddressApioResourceCollection.getTotalItems();

		Assert.assertThat(totalItems1, equalTo(totalItems3));
	}

	@Test
	public void testUpdateCollectionItem() throws IOException, PortalException {
		String accountExternalReferenceCode = _addCommerceAccount(
			RandomTestUtil.randomString());

		String addressName = RandomTestUtil.randomString();

		_addCommerceAddress(addressName, accountExternalReferenceCode);

		ApioResourceCollection commerceAddressApioResourceCollection =
			getSiteRelatedApioResourceCollection(
				COMMERCE_ADDRESS_RESOURCE_NAME, _TEST_PARENT_ORGANIZATION_NAME);

		ApioSingleModel actualAddressApioSingleModel = getSingleResourceByField(
			commerceAddressApioResourceCollection,
			SchemaOrgConstants.Property.NAME, addressName);

		String newAddressName = RandomTestUtil.randomString();
		JsonNode actualAddressIdJsonNode =
			actualAddressApioSingleModel.getIdJsonNode();

		Map<String, String> addressPropertiesMap =
			new HashMap<String, String>() {
				{
					put(SchemaOrgConstants.Property.NAME, newAddressName);
					put(
						"accountExternalReferenceCode",
						accountExternalReferenceCode);
					put("countryTwoLettersISOCode", "HU");
				}
			};

		ObjectNode expectedObjectNode = constructExpectedObjectNode(
			actualAddressApioSingleModel, Method.PUT, addressPropertiesMap);

		try (RESTClient restClient = new RESTClient()) {
			restClient.executePutRequest(
				actualAddressIdJsonNode.asText(), expectedObjectNode);
		}

		commerceAddressApioResourceCollection =
			getSiteRelatedApioResourceCollection(
				COMMERCE_ADDRESS_RESOURCE_NAME, _TEST_PARENT_ORGANIZATION_NAME);

		ApioSingleModel updatedAddressApioSingleModel =
			getSingleResourceByField(
				commerceAddressApioResourceCollection,
				SchemaOrgConstants.Property.NAME, newAddressName);

		JsonNode updatedAddressIdJsonNode =
			updatedAddressApioSingleModel.getIdJsonNode();

		Assert.assertThat(
			actualAddressIdJsonNode.asText(),
			equalTo(updatedAddressIdJsonNode.asText()));
	}

	private String _addCommerceAccount(String name)
		throws IOException, PortalException {

		ApioResourceCollection commerceAccountApioResourceCollection =
			getSiteRelatedApioResourceCollection(
				COMMERCE_ACCOUNT_RESOURCE_NAME, _TEST_PARENT_ORGANIZATION_NAME);

		int totalItems = commerceAccountApioResourceCollection.getTotalItems();

		Map<String, String> accountPropertiesMap =
			new HashMap<String, String>() {
				{
					put(SchemaOrgConstants.Property.NAME, name);
				}
			};

		ObjectNode expectedObjectNode = constructExpectedObjectNode(
			commerceAccountApioResourceCollection, Method.POST,
			accountPropertiesMap);

		JsonNode commerceAccountIdJsonNode =
			commerceAccountApioResourceCollection.getIdJsonNode();

		String messageEntity = null;

		try (RESTClient restClient = new RESTClient()) {
			messageEntity = restClient.executePostRequest(
				commerceAccountIdJsonNode.asText(), expectedObjectNode);
		}

		JsonNode jsonNode = objectMapper.readTree(messageEntity);

		JsonNode externalReferenceCodeJsonNode = jsonNode.path(
			"externalReferenceCode");

		ApioResourceCollection actualCommerceAccountApioResourceCollection =
			getSiteRelatedApioResourceCollection(
				COMMERCE_ACCOUNT_RESOURCE_NAME, _TEST_PARENT_ORGANIZATION_NAME);

		int actualTotalItems =
			actualCommerceAccountApioResourceCollection.getTotalItems();

		Assert.assertThat(totalItems + 1, equalTo(actualTotalItems));

		return externalReferenceCodeJsonNode.asText();
	}

	private String _addCommerceAddress(
			String name, String accountExternalReferenceCode)
		throws IOException, PortalException {

		ApioResourceCollection commerceAddressApioResourceCollection =
			getSiteRelatedApioResourceCollection(
				COMMERCE_ADDRESS_RESOURCE_NAME, _TEST_PARENT_ORGANIZATION_NAME);

		int totalItems = commerceAddressApioResourceCollection.getTotalItems();

		Map<String, String> commerceAddressPropertiesMap = new HashMap<>(
			_propertiesMap);

		commerceAddressPropertiesMap.put(
			SchemaOrgConstants.Property.NAME, name);
		commerceAddressPropertiesMap.put(
			"accountExternalReferenceCode", accountExternalReferenceCode);

		ObjectNode expectedObjectNode = constructExpectedObjectNode(
			commerceAddressApioResourceCollection, Method.POST,
			commerceAddressPropertiesMap);

		JsonNode commerceAddressIdJsonNode =
			commerceAddressApioResourceCollection.getIdJsonNode();

		String messageEntity = null;

		try (RESTClient restClient = new RESTClient()) {
			messageEntity = restClient.executePostRequest(
				commerceAddressIdJsonNode.asText(), expectedObjectNode);
		}

		JsonNode jsonNode = objectMapper.readTree(messageEntity);

		ApioSingleModel apioSingleModel = new ApioSingleModel(jsonNode);

		JsonNode apioSingleModelIdJsonNode = apioSingleModel.getIdJsonNode();

		ApioResourceCollection actualCommerceAddressApioResourceCollection =
			getSiteRelatedApioResourceCollection(
				COMMERCE_ADDRESS_RESOURCE_NAME, _TEST_PARENT_ORGANIZATION_NAME);

		int actualTotalItems =
			actualCommerceAddressApioResourceCollection.getTotalItems();

		Assert.assertThat(totalItems + 1, equalTo(actualTotalItems));

		return apioSingleModelIdJsonNode.asText();
	}

	private static final String _TEST_PARENT_ORGANIZATION_NAME =
		RandomTestUtil.randomString();

	@Inject
	private static ConfigurationAdmin _configurationAdmin;

	private static OrganizationTypeSetUp _organizationTypeSetUp;
	private static Organization _parentOrganization;
	private static final Map<String, String> _propertiesMap =
		new HashMap<String, String>() {
			{
				put(
					SchemaOrgConstants.Property.NAME,
					RandomTestUtil.randomString());
				put("countryTwoLettersISOCode", "HU");
			}
		};

}