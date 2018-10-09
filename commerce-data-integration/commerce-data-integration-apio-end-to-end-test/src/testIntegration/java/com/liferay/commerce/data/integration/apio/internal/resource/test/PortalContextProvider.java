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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.commerce.apio.jsonld.representation.util.ApioEntryPoint;
import com.liferay.commerce.apio.jsonld.representation.util.ApioForm;
import com.liferay.commerce.apio.jsonld.representation.util.ApioResourceCollection;
import com.liferay.commerce.apio.jsonld.representation.util.ApioResponse;
import com.liferay.commerce.apio.jsonld.representation.util.ApioSingleModel;
import com.liferay.commerce.apio.jsonld.representation.util.ApioUtils;
import com.liferay.commerce.apio.jsonld.representation.util.constants.JSONLDConstants;
import com.liferay.commerce.apio.jsonld.representation.util.constants.SchemaOrgConstants;
import com.liferay.commerce.apio.jsonld.representation.util.form.Property;
import com.liferay.commerce.apio.jsonld.representation.util.operation.Method;
import com.liferay.commerce.apio.jsonld.representation.util.operation.Operation;
import com.liferay.commerce.data.integration.apio.internal.client.RESTClient;
import com.liferay.external.reference.service.EROrganizationLocalService;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.io.IOException;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang.StringUtils;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.runner.RunWith;

/**
 * @author Zoltán Takács
 */
@RunWith(Arquillian.class)
public abstract class PortalContextProvider {

	public static final String WEBSITE = "WebSite";

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	public static String getWebSiteEndpointURL(ApioEntryPoint apioEntryPoint) {
		Map<String, String> rootEndpointMap =
			apioEntryPoint.getRootEndpointMap();

		Set<Map.Entry<String, String>> entries = rootEndpointMap.entrySet();

		Stream<Map.Entry<String, String>> stream = entries.stream();

		return stream.filter(
			mapEntry -> {
				String resourceType = mapEntry.getValue();

				return resourceType.equals(WEBSITE);
			}
		).findFirst(
		).map(
			Map.Entry::getKey
		).orElseThrow(
			() -> new NoSuchElementException(
				"Unable to fetch web site endpoint URL")
		);
	}

	public ObjectNode constructExpectedObjectNode(
			ApioResponse apioResponse, Method method)
		throws IOException {

		return constructExpectedObjectNode(apioResponse, method, null, null);
	}

	public ObjectNode constructExpectedObjectNode(
			ApioResponse apioResponse, Method method, String fieldName,
			String fieldValue)
		throws IOException {

		Operation resourceOperation = ApioUtils.getResourceOperationByMethod(
			apioResponse, method);

		String expectsFormId = resourceOperation.getExpects();

		String messageEntity = null;

		try (RESTClient restClient = new RESTClient()) {
			messageEntity = restClient.executeGetRequest(expectsFormId);
		}

		JsonNode formJsonNode = objectMapper.readTree(messageEntity);

		ApioForm apioForm = new ApioForm(formJsonNode);

		List<Property> supportedProperties = apioForm.getSupportedProperties();

		Stream<Property> stream = supportedProperties.stream();

		ObjectNode expectedObjectNode = objectMapper.createObjectNode();

		stream.filter(
			Property::isRequired
		).filter(
			Property::isWriteable
		).collect(
			Collectors.toList()
		).forEach(
			property -> {
				String actualFieldName = property.getName();

				if ((fieldName != null) && fieldName.equals(actualFieldName)) {
					expectedObjectNode.put(actualFieldName, fieldValue);
				}
				else {
					expectedObjectNode.put(
						actualFieldName, RandomTestUtil.randomString());
				}
			}
		);

		return expectedObjectNode;
	}

	public ApioEntryPoint getApioEntryPoint() throws IOException {
		if (_apioEntryPoint != null) {
			return _apioEntryPoint;
		}

		try (RESTClient restClient = new RESTClient()) {
			String messageEntity = restClient.executeGetRequest(
				getRootEndpointURL());

			JsonNode jsonNode = objectMapper.readTree(messageEntity);

			_apioEntryPoint = new ApioEntryPoint(jsonNode);
		}

		return _apioEntryPoint;
	}

	/**
	 * Gets the available sites from Liferay
	 *
	 * @return The available websites with map entry of URL / Website name
	 * @throws IOException
	 */
	public Map<String, String> getAvailableWebSites() throws IOException {
		try (RESTClient restClient = new RESTClient()) {
			String webSitesEndpointURL = null;
			Map<String, String> webSiteURLWithNameMap = new TreeMap<>();

			try {
				webSitesEndpointURL = getWebSiteEndpointURL(
					getApioEntryPoint());
			}
			catch (NoSuchElementException nsee) {
				return webSiteURLWithNameMap;
			}

			String messageEntity = restClient.executeGetRequest(
				webSitesEndpointURL);

			JsonNode resourceCollectionJsonNode = objectMapper.readTree(
				messageEntity);

			ApioResourceCollection webSitesApioResourceCollection =
				new ApioResourceCollection(resourceCollectionJsonNode);

			String actualPage =
				webSitesApioResourceCollection.getResourceActualPage();
			String nextPage =
				webSitesApioResourceCollection.getResourceNextPage();
			String lastPage =
				webSitesApioResourceCollection.getResourceLastPage();

			do {
				JsonNode webSitesJsonNode =
					webSitesApioResourceCollection.getMemberJsonNode();

				for (JsonNode jsonNode : webSitesJsonNode) {
					JsonNode webSiteURLJsonNode = jsonNode.path(
						JSONLDConstants.ID);
					JsonNode webSiteNameJsonNode = jsonNode.path(
						SchemaOrgConstants.Property.NAME);

					webSiteURLWithNameMap.put(
						webSiteURLJsonNode.asText(),
						webSiteNameJsonNode.asText());
				}

				actualPage =
					webSitesApioResourceCollection.getResourceActualPage();
				nextPage = webSitesApioResourceCollection.getResourceNextPage();

				if (StringUtils.isNotBlank(nextPage)) {
					String nextPageResources = restClient.executeGetRequest(
						nextPage);

					webSitesApioResourceCollection = new ApioResourceCollection(
						objectMapper.readTree(nextPageResources));
				}
			}
			while (StringUtils.isNotBlank(nextPage) &&
				   !lastPage.equals(actualPage));

			return webSiteURLWithNameMap;
		}
	}

	public String getPortalURL() {
		try {
			Company company = companyLocalService.getCompanyByWebId(
				"liferay.com");

			Group companyGroup = groupLocalService.getCompanyGroup(
				company.getCompanyId());

			return company.getPortalURL(companyGroup.getGroupId());
		}
		catch (Exception e) {
			throw new RuntimeException(
				"Unexpected error: " + e.getMessage(), e);
		}
	}

	public ApioSingleModel getResourceJsonNodeByField(
			ApioResourceCollection apioResourceCollection, String fieldName,
			String fieldValue)
		throws IOException {

		try (RESTClient restClient = new RESTClient()) {
			String actualPage = apioResourceCollection.getResourceActualPage();
			String nextPage = apioResourceCollection.getResourceNextPage();
			String lastPage = apioResourceCollection.getResourceLastPage();

			do {
				JsonNode jsonNodes = apioResourceCollection.getMemberJsonNode();

				for (JsonNode jsonNode : jsonNodes) {
					JsonNode fieldJsonNode = jsonNode.path(fieldName);

					if (fieldValue.equals(fieldJsonNode.asText())) {
						JsonNode idJsonNode = new ApioSingleModel(
							jsonNode).getIdJsonNode();

						String messageEntity = restClient.executeGetRequest(
							idJsonNode.asText());

						JsonNode rawJsonNode = objectMapper.readTree(
							messageEntity);

						return new ApioSingleModel(rawJsonNode);
					}
				}

				actualPage = apioResourceCollection.getResourceActualPage();
				nextPage = apioResourceCollection.getResourceNextPage();

				if (StringUtils.isNotBlank(nextPage)) {
					String nextPageResources = restClient.executeGetRequest(
						nextPage);

					apioResourceCollection = new ApioResourceCollection(
						objectMapper.readTree(nextPageResources));
				}
			}
			while (StringUtils.isNotBlank(nextPage) &&
				   !lastPage.equals(actualPage));

			throw new NoSuchElementException(
				String.format(
					"Unable to find resource with field name: \"%s\"," +
						"value: \"%s\"",
					fieldName, fieldValue));
		}
	}

	public String getRootEndpointURL() {
		return getPortalURL().concat(_ROOT_END_POINT_SUFFIX);
	}

	public Map.Entry<String, String> getWebSiteByName(String siteName)
		throws IOException {

		Map<String, String> availableWebSites = getAvailableWebSites();

		Set<Map.Entry<String, String>> entries = availableWebSites.entrySet();

		Stream<Map.Entry<String, String>> stream = entries.stream();

		return stream.filter(
			currentWebSiteEntry -> {
				String webSiteName = currentWebSiteEntry.getValue();

				return webSiteName.equals(siteName);
			}
		).findFirst(
		).orElseThrow(
			() -> new NoSuchElementException(
				"Unable to find website with name: " + siteName)
		);
	}

	@Inject
	protected CompanyLocalService companyLocalService;

	@Inject
	protected EROrganizationLocalService erOrganizationLocalService;

	@Inject
	protected GroupLocalService groupLocalService;

	protected final ObjectMapper objectMapper = new ObjectMapper();

	@Inject
	protected OrganizationLocalService organizationLocalService;

	@Inject
	protected RoleLocalService roleLocalService;

	@Inject
	protected UserLocalService userLocalService;

	private static final String _ROOT_END_POINT_SUFFIX = "/o/api";

	private static ApioEntryPoint _apioEntryPoint;

}