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

package com.liferay.commerce.order;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.util.ResourceBundle;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceOrderValidatorResult implements Serializable {

	public CommerceOrderValidatorResult(boolean valid) {
		this(0, valid, null, StringPool.BLANK, StringPool.BLANK);
	}

	public CommerceOrderValidatorResult(
		boolean valid, ResourceBundle resourceBundle, String messageKey) {

		this(0, valid, resourceBundle, messageKey, StringPool.BLANK);
	}

	public CommerceOrderValidatorResult(
		boolean valid, ResourceBundle resourceBundle, String messageKey,
		String argument) {

		this(0, valid, resourceBundle, messageKey, argument);
	}

	public CommerceOrderValidatorResult(
		long commerceOrderItemId, boolean valid, ResourceBundle resourceBundle,
		String messageKey) {

		this(
			commerceOrderItemId, valid, resourceBundle, messageKey,
			StringPool.BLANK);
	}

	public CommerceOrderValidatorResult(
		long commerceOrderItemId, boolean valid, ResourceBundle resourceBundle,
		String messageKey, String argument) {

		_commerceOrderItemId = commerceOrderItemId;
		_valid = valid;
		_resourceBundle = resourceBundle;
		_messageKey = messageKey;
		_argument = argument;
	}

	public String getArgument() {
		return _argument;
	}

	public long getCommerceOrderItemId() {
		return _commerceOrderItemId;
	}

	public String getMessage() {
		return _messageKey;
	}

	public ResourceBundle getResourceBundle() {
		return _resourceBundle;
	}

	public boolean hasArgument() {
		if (Validator.isNotNull(getArgument())) {
			return true;
		}

		return false;
	}

	public boolean hasMessageResult() {
		if (Validator.isNotNull(getMessage())) {
			return true;
		}

		return false;
	}

	public boolean isValid() {
		return _valid;
	}

	private String _argument;
	private long _commerceOrderItemId;
	private String _messageKey;
	private ResourceBundle _resourceBundle;
	private boolean _valid;

}