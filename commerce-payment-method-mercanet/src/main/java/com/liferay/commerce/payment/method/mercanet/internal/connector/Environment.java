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

package com.liferay.commerce.payment.method.mercanet.internal.connector;

/**
 * @author Luca Pellizzon
 */
public enum Environment {

	PROD(
		"https://payment-webinit.mercanet.bnpparibas.net/rs-services/v2" +
			"/paymentInit"),
	SIMU(
		"https://payment-webinit.simu.mercanet.bnpparibas.net/rs-services/v2" +
			"/paymentInit"),
	TEST(
		"https://payment-webinit-mercanet.test.sips-atos.com/rs-services/v2" +
			"/paymentInit");

	public String getUrl() {
		return _url;
	}

	private Environment(String url) {
		_url = url;
	}

	private final String _url;

}