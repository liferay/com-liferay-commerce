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

package com.liferay.commerce.data.integration.apio.end.to.end.client;

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