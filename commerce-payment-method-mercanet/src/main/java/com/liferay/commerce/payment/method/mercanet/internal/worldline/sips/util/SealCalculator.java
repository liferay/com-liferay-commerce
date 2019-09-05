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

package com.liferay.commerce.payment.method.mercanet.internal.worldline.sips.util;

import com.liferay.commerce.payment.method.mercanet.internal.worldline.sips.exception.SealCalculationException;
import com.liferay.commerce.payment.method.mercanet.internal.worldline.sips.helper.AlphabeticalReflectionToStringBuilder;
import com.liferay.commerce.payment.method.mercanet.internal.worldline.sips.helper.SealStringStyle;
import com.liferay.commerce.payment.method.mercanet.internal.worldline.sips.model.InitializationResponse;
import com.liferay.commerce.payment.method.mercanet.internal.worldline.sips.model.PaymentRequest;

import java.nio.charset.StandardCharsets;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * @author Luca Pellizzon
 */
public class SealCalculator {

	/**
	 * Calculate the encrypted seal for a {@link PaymentRequest} based on a given seal string.
	 *
	 * @param sealString the seal string for the {@link PaymentRequest} that needs to be signed
	 * @param key        the merchant's secret key
	 * @return the encrypted seal for the request
	 * @throws SealCalculationException when the encryption fails (e.g. algorithm missing, invalid key specified)
	 * @see #getSealString(PaymentRequest)
	 */
	public static String calculate(String sealString, String key)
		throws SealCalculationException {

		try {
			Mac hmacSHA256 = Mac.getInstance("HmacSHA256");
			SecretKeySpec secretKeySpec = new SecretKeySpec(
				key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");

			hmacSHA256.init(secretKeySpec);

			return Hex.encodeHexString(
				hmacSHA256.doFinal(
					sealString.getBytes(StandardCharsets.UTF_8)));
		}
		catch (InvalidKeyException | NoSuchAlgorithmException e) {
			throw new SealCalculationException(
				"Seal could not be calculated!", e);
		}
	}

	/**
	 * Sort &amp; concatenate the fields of a given {@link PaymentRequest}, needed to correctly verify a response.
	 *
	 * @param initializationResponse the response that's needs to be verified
	 * @return a String, formatted as described in the API docs.
	 */
	public static String getSealString(
		InitializationResponse initializationResponse) {

		ReflectionToStringBuilder reflectionToStringBuilder =
			new AlphabeticalReflectionToStringBuilder(
				initializationResponse, new SealStringStyle());

		reflectionToStringBuilder.setExcludeFieldNames("seal");
		reflectionToStringBuilder.setExcludeNullValues(true);

		return reflectionToStringBuilder.toString();
	}

	/**
	 * Sort &amp; concatenate the fields of a given {@link PaymentRequest}, needed to correctly sign the request.
	 *
	 * @param paymentRequest the request that's needs to be signed
	 * @return a String, formatted as described in the API docs.
	 */
	public static String getSealString(PaymentRequest paymentRequest) {
		ReflectionToStringBuilder reflectionToStringBuilder =
			new AlphabeticalReflectionToStringBuilder(
				paymentRequest, new SealStringStyle());

		reflectionToStringBuilder.setExcludeFieldNames("keyVersion");
		reflectionToStringBuilder.setExcludeNullValues(true);
		reflectionToStringBuilder.setAppendStatics(true);

		return reflectionToStringBuilder.toString();
	}

	private SealCalculator() {

		// Nothing to see here

	}

}