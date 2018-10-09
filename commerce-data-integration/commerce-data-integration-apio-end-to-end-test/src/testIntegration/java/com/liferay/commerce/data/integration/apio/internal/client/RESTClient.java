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

package com.liferay.commerce.data.integration.apio.internal.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.StatusType;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

/**
 * @author Zoltán Takács
 */
public class RESTClient implements AutoCloseable {

	/**
	 * A {@link MediaType} constant representing JSON-LD media type.
	 */
	public static final MediaType APPLICATION_JSON_LD = new MediaType(
		"application", "ld+json");

	/**
	 * A {@code String} constant representing JSON-LD media type.
	 */
	public static final String JSON_LD = "application/ld+json";

	public RESTClient() {
		this(new RESTClientConfig());
	}

	public RESTClient(RESTClientConfig restClientConfig) {
		_password = restClientConfig.getPassword();
		_userId = restClientConfig.getUserId();
		_restClientConfig = restClientConfig;

		_client = _getClient();
	}

	@Override
	public void close() {
		_client.close();
	}

	public String executeDeleteRequest(String url) throws IOException {
		_endpoint = url;

		WebTarget webTarget = _client.target(getEndpointURI());

		if (_log.isDebugEnabled()) {
			_log.debug("Target: " + getEndpoint());
		}

		Invocation.Builder builder = webTarget.request(APPLICATION_JSON_LD);

		return _invokeBuilder(HttpMethod.DELETE, builder);
	}

	public String executeGetRequest(String url) throws IOException {
		_endpoint = url;

		URI endpointURI = getEndpointURI();

		WebTarget webTarget = _client.target(endpointURI);

		if (_log.isDebugEnabled()) {
			_log.debug("Target: " + endpointURI.toASCIIString());
		}

		Invocation.Builder builder = webTarget.request(APPLICATION_JSON_LD);

		return _invokeBuilder(HttpMethod.GET, builder);
	}

	public String executePostRequest(String url, JsonNode payloadJsonNode)
		throws IOException {

		_endpoint = url;

		WebTarget webTarget = _client.target(getEndpointURI());

		if (_log.isDebugEnabled()) {
			_log.debug("Target: " + getEndpoint());
		}

		Invocation.Builder builder = webTarget.request(APPLICATION_JSON_LD);

		Entity<String> entity = Entity.json(
			_jsonNodeToPrettyString(payloadJsonNode));

		return _invokeBuilder(HttpMethod.POST, builder, entity);
	}

	public String executePutRequest(String url, JsonNode payloadJsonNode)
		throws IOException {

		_endpoint = url;

		WebTarget webTarget = _client.target(getEndpointURI());

		if (_log.isDebugEnabled()) {
			_log.debug("Target: " + getEndpoint());
		}

		Invocation.Builder builder = webTarget.request(APPLICATION_JSON_LD);

		Entity<String> entity = Entity.json(
			_jsonNodeToPrettyString(payloadJsonNode));

		return _invokeBuilder(HttpMethod.PUT, builder, entity);
	}

	public String getEndpoint() {
		boolean forceHttps = _restClientConfig.isForceHttps();

		if (forceHttps) {
			return _replaceHttpSchemeWithHttps(_endpoint);
		}

		return _endpoint;
	}

	public URI getEndpointURI() {
		try {
			return new URI(getEndpoint());
		}
		catch (URISyntaxException urise) {
			throw new RuntimeException(
				String.format(
					"Unable to parse %s as a URI reference", getEndpoint()),
				urise);
		}
	}

	@Override
	public String toString() {
		return String.format("REST API Client [%s].", getEndpoint());
	}

	protected static final String HTTP = "http://";

	protected static final String HTTPS = "https://";

	private Response _follow3Redirects(Response currentResponse) {
		StatusType statusType = currentResponse.getStatusInfo();

		if (statusType.getFamily() != Response.Status.Family.REDIRECTION) {
			return currentResponse;
		}

		AtomicInteger counter = new AtomicInteger();
		Response response = currentResponse;

		while ((statusType.getFamily() ==
					Response.Status.Family.REDIRECTION) &&
			   (counter.incrementAndGet() <= 3)) {

			String location = response.getHeaderString(HttpHeaders.LOCATION);

			if ((location == null) || location.isEmpty()) {
				return response;
			}

			if (_log.isDebugEnabled()) {
				_log.debug(
					String.format(
						"Redirect location %d#: %s", counter.get(), location));
			}

			response.close();

			WebTarget webTarget = _client.target(location);

			Invocation.Builder builder = webTarget.request(APPLICATION_JSON_LD);

			response = builder.get();
		}

		return response;
	}

	private Client _getClient() {
		return ClientBuilder.newClient(_getClientConfig());
	}

	private ClientConfig _getClientConfig() {
		ClientConfig clientConfig = _setCredentials(_userId, _password);

		clientConfig = clientConfig.property(
			ClientProperties.CONNECT_TIMEOUT,
			_restClientConfig.getConnectTimeout() * 1000);

		clientConfig = clientConfig.property(
			ClientProperties.READ_TIMEOUT,
			_restClientConfig.getReadTimeout() * 1000);

		return clientConfig;
	}

	private Response _handleResponse(
		String httpMethod, Invocation.Builder builder, Entity<String> entity) {

		boolean followRedirects = _restClientConfig.isFollowRedirects();
		Response response;

		if (entity == null) {
			if (followRedirects) {
				response = _follow3Redirects(builder.method(httpMethod));
			}
			else {
				response = builder.method(httpMethod);
			}
		}
		else {
			if (followRedirects) {
				response = _follow3Redirects(
					builder.method(httpMethod, entity));
			}
			else {
				response = builder.method(httpMethod, entity);
			}
		}

		return response;
	}

	private String _invokeBuilder(String httpMethod, Invocation.Builder builder)
		throws IOException {

		return _invokeBuilder(httpMethod, builder, null);
	}

	private String _invokeBuilder(
			String httpMethod, Invocation.Builder builder,
			Entity<String> entity)
		throws IOException {

		Response response = _handleResponse(httpMethod, builder, entity);

		String messageEntity = response.readEntity(String.class);
		int statusCode = response.getStatus();
		StatusType statusType = response.getStatusInfo();

		if (statusType.getFamily() == Response.Status.Family.SUCCESSFUL) {
			return messageEntity;
		}

		throw new IOException(
			String.format(
				"Request failed with status code: %s\nBody: %s", statusCode,
				messageEntity));
	}

	private String _jsonNodeToPrettyString(JsonNode jsonNode)
		throws IOException {

		String json;

		try {
			ObjectWriter objectWriter =
				_objectMapper.writerWithDefaultPrettyPrinter();

			json = objectWriter.writeValueAsString(jsonNode);
		}
		catch (JsonProcessingException jpe) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to convert JsonNode to a String representation");
			}

			throw new IOException(jpe);
		}

		return json;
	}

	private String _replaceHttpSchemeWithHttps(String url) {
		String lowerCasedUrl = url.toLowerCase(Locale.getDefault());

		if (lowerCasedUrl.startsWith(HTTP)) {
			return HTTPS.concat(url.substring(HTTP.length()));
		}

		return url;
	}

	private ClientConfig _setCredentials(String userId, String password) {
		HttpAuthenticationFeature httpAuthenticationFeature =
			HttpAuthenticationFeature.basic(userId, password);

		ClientConfig clientConfig = new ClientConfig();

		clientConfig.register(httpAuthenticationFeature);

		return clientConfig;
	}

	private static final Log _log = LogFactoryUtil.getLog(RESTClient.class);

	private final Client _client;
	private String _endpoint;
	private final ObjectMapper _objectMapper = new ObjectMapper();
	private String _password;
	private final RESTClientConfig _restClientConfig;
	private String _userId;

}