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

import static org.hamcrest.CoreMatchers.containsString;
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
import com.liferay.commerce.data.integration.apio.internal.client.RESTClient;
import com.liferay.commerce.organization.constants.CommerceOrganizationConstants;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ListTypeConstants;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.OrganizationConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.OrganizationTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerTestRule;

import java.io.IOException;

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
public class CommerceAccountNestedCollectionResourceTest
	extends PortalContextProvider {

	public static final String COMMERCE_ACCOUNT_RESOURCE_NAME =
		"commerceAccounts";

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_parentOrganization = OrganizationTestUtil.addOrganization(
			OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID,
			_TEST_PARENT_ORGANIZATION_NAME, true);
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
	public void testAddAccountWithAPI() throws IOException, PortalException {
		ApioResourceCollection commerceAccountApioResourceCollection =
			_getCommerceAccountApioResourceCollection(
				_TEST_PARENT_ORGANIZATION_NAME);

		int numberOfItems =
			commerceAccountApioResourceCollection.getNumberOfItems();

		_setupCommerceAccount();

		ApioResourceCollection actualCommerceAccountApioResourceCollection =
			_getCommerceAccountApioResourceCollection(
				_TEST_PARENT_ORGANIZATION_NAME);

		int actualNumberOfItems =
			actualCommerceAccountApioResourceCollection.getNumberOfItems();

		Assert.assertThat(numberOfItems + 1, equalTo(actualNumberOfItems));
	}

	@Test
	public void testAddCollectionItem1() throws IOException {
		ApioResourceCollection commerceAccountApioResourceCollection =
			_getCommerceAccountApioResourceCollection(
				BasicEndToEndTest.DEFAULT_SITE_NAME);

		ObjectNode expectedObjectNode = constructExpectedObjectNode(
			commerceAccountApioResourceCollection, Method.POST);

		JsonNode commerceAccountIdJsonNode =
			commerceAccountApioResourceCollection.getIdJsonNode();

		try (RESTClient restClient = new RESTClient()) {
			restClient.executePostRequest(
				commerceAccountIdJsonNode.asText(), expectedObjectNode);
		}
		catch (IOException ioe) {
			Assert.assertThat(
				ioe.getMessage(),
				containsString("Unable to add new account to the website"));
		}
	}

	@Test
	public void testAddCollectionItem2() throws IOException, PortalException {
		_addAccount(RandomTestUtil.randomString());
	}

	@Test
	public void testCollectionPostOperationPresent() throws IOException {
		ApioResourceCollection commerceAccountApioResourceCollection =
			_getCommerceAccountApioResourceCollection(
				BasicEndToEndTest.DEFAULT_SITE_NAME);

		Operation resourceOperation = ApioUtils.getResourceOperationByMethod(
			commerceAccountApioResourceCollection, Method.POST);

		Assert.assertThat(resourceOperation, notNullValue());
	}

	@Test
	public void testDeleteCollectionItem() throws IOException, PortalException {
		ApioResourceCollection commerceAccountApioResourceCollection =
			_getCommerceAccountApioResourceCollection(
				_TEST_PARENT_ORGANIZATION_NAME);

		int numberOfItems1 =
			commerceAccountApioResourceCollection.getNumberOfItems();

		String accountId = _addAccount(RandomTestUtil.randomString());

		commerceAccountApioResourceCollection =
			_getCommerceAccountApioResourceCollection(
				_TEST_PARENT_ORGANIZATION_NAME);

		int numberOfItems2 =
			commerceAccountApioResourceCollection.getNumberOfItems();

		Assert.assertThat(numberOfItems1 + 1, equalTo(numberOfItems2));

		try (RESTClient restClient = new RESTClient()) {
			restClient.executeDeleteRequest(accountId);
		}

		commerceAccountApioResourceCollection =
			_getCommerceAccountApioResourceCollection(
				_TEST_PARENT_ORGANIZATION_NAME);

		int numberOfItems3 =
			commerceAccountApioResourceCollection.getNumberOfItems();

		Assert.assertThat(numberOfItems1, equalTo(numberOfItems3));
	}

	@Test
	public void testUpdateCollectionItem() throws IOException, PortalException {
		String accountName = RandomTestUtil.randomString();

		_addAccount(accountName);

		ApioResourceCollection commerceAccountApioResourceCollection =
			_getCommerceAccountApioResourceCollection(
				_TEST_PARENT_ORGANIZATION_NAME);

		ApioSingleModel actualAccountApioSingleModel =
			getResourceJsonNodeByField(
				commerceAccountApioResourceCollection,
				SchemaOrgConstants.Property.NAME, accountName);

		String newAccountName = RandomTestUtil.randomString();
		JsonNode actualAccountIdJsonNode =
			actualAccountApioSingleModel.getIdJsonNode();

		ObjectNode expectedObjectNode = constructExpectedObjectNode(
			actualAccountApioSingleModel, Method.PUT,
			SchemaOrgConstants.Property.NAME, newAccountName);

		try (RESTClient restClient = new RESTClient()) {
			restClient.executePutRequest(
				actualAccountIdJsonNode.asText(), expectedObjectNode);
		}

		commerceAccountApioResourceCollection =
			_getCommerceAccountApioResourceCollection(
				_TEST_PARENT_ORGANIZATION_NAME);

		ApioSingleModel updatedAccountApioSingleModel =
			getResourceJsonNodeByField(
				commerceAccountApioResourceCollection,
				SchemaOrgConstants.Property.NAME, newAccountName);

		JsonNode updatedAccountIdJsonNode =
			updatedAccountApioSingleModel.getIdJsonNode();

		Assert.assertThat(
			actualAccountIdJsonNode.asText(),
			equalTo(updatedAccountIdJsonNode.asText()));
	}

	private String _addAccount(String name)
		throws IOException, PortalException {

		ApioResourceCollection commerceAccountApioResourceCollection =
			_getCommerceAccountApioResourceCollection(
				_TEST_PARENT_ORGANIZATION_NAME);

		int numberOfItems =
			commerceAccountApioResourceCollection.getNumberOfItems();

		ObjectNode expectedObjectNode = constructExpectedObjectNode(
			commerceAccountApioResourceCollection, Method.POST,
			SchemaOrgConstants.Property.NAME, name);

		JsonNode commerceAccountIdJsonNode =
			commerceAccountApioResourceCollection.getIdJsonNode();

		String messageEntity = null;

		try (RESTClient restClient = new RESTClient()) {
			messageEntity = restClient.executePostRequest(
				commerceAccountIdJsonNode.asText(), expectedObjectNode);
		}

		JsonNode jsonNode = objectMapper.readTree(messageEntity);

		ApioSingleModel apioSingleModel = new ApioSingleModel(jsonNode);

		JsonNode apioSingleModelIdJsonNode = apioSingleModel.getIdJsonNode();

		ApioResourceCollection actualCommerceAccountApioResourceCollection =
			_getCommerceAccountApioResourceCollection(
				_TEST_PARENT_ORGANIZATION_NAME);

		int actualNumberOfItems =
			actualCommerceAccountApioResourceCollection.getNumberOfItems();

		Assert.assertThat(numberOfItems + 1, equalTo(actualNumberOfItems));

		return apioSingleModelIdJsonNode.asText();
	}

	private ApioResourceCollection _getCommerceAccountApioResourceCollection(
			String webSiteName)
		throws IOException {

		Map.Entry<String, String> webSiteEntry = getWebSiteByName(webSiteName);

		String guestSiteIdURL = webSiteEntry.getKey();

		String messageEntity = null;

		try (RESTClient restClient = new RESTClient()) {
			messageEntity = restClient.executeGetRequest(guestSiteIdURL);

			JsonNode webSiteJsonNode = objectMapper.readTree(messageEntity);

			JsonNode commerceAccountsIdJsonNode = webSiteJsonNode.path(
				COMMERCE_ACCOUNT_RESOURCE_NAME);

			messageEntity = restClient.executeGetRequest(
				commerceAccountsIdJsonNode.asText());
		}

		JsonNode commerceAccountJsonNode = objectMapper.readTree(messageEntity);

		return new ApioResourceCollection(commerceAccountJsonNode);
	}

	private Organization _setupCommerceAccount() throws PortalException {
		User user = TestPropsValues.getUser();

		return erOrganizationLocalService.addOrUpdateOrganization(
			RandomTestUtil.randomString(), user.getUserId(),
			_parentOrganization.getOrganizationId(),
			_TEST_ORGANIZATION_ACCOUNT_NAME,
			CommerceOrganizationConstants.TYPE_ACCOUNT, 0, 0,
			ListTypeConstants.ORGANIZATION_STATUS_DEFAULT, StringPool.BLANK,
			false, false, null,
			ServiceContextTestUtil.getServiceContext(
				_parentOrganization.getGroupId(), user.getUserId(),
				user.getCompanyId()));
	}

	private static final String _TEST_ORGANIZATION_ACCOUNT_NAME =
		RandomTestUtil.randomString();

	private static final String _TEST_PARENT_ORGANIZATION_NAME =
		RandomTestUtil.randomString();

	private static Organization _parentOrganization;

}