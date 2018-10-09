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

/**
 * @author Zoltán Takács
 */
public class RESTClientConfig {

	public RESTClientConfig() {
		this(_USER_ID, _PASSWORD);
	}

	public RESTClientConfig(String userId, String password) {
		_userId = userId;
		_password = password;
	}

	public int getConnectTimeout() {
		return _connectTimeout;
	}

	public String getPassword() {
		return _password;
	}

	public int getReadTimeout() {
		return _readTimeout;
	}

	public String getUserId() {
		return _userId;
	}

	public boolean isFollowRedirects() {
		return _followRedirects;
	}

	public boolean isForceHttps() {
		return _forceHttps;
	}

	public void setConnectTimeout(int connectTimeout) {
		_connectTimeout = connectTimeout;
	}

	public void setFollowRedirects(boolean followRedirects) {
		_followRedirects = followRedirects;
	}

	public void setForceHttps(boolean forceHttps) {
		_forceHttps = forceHttps;
	}

	public void setReadTimeout(int readTimeout) {
		_readTimeout = readTimeout;
	}

	private static final String _PASSWORD = "test";

	private static final String _USER_ID = "test@liferay.com";

	private int _connectTimeout = 30;
	private boolean _followRedirects;
	private boolean _forceHttps;
	private String _password;
	private int _readTimeout = 60;
	private String _userId;

}