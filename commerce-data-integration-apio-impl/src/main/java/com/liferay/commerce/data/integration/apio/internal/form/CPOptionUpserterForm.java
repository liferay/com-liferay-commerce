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

package com.liferay.commerce.data.integration.apio.internal.form;

import com.liferay.apio.architect.form.Form;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.Collections;
import java.util.Locale;
import java.util.Map;

/**
 * @author Rodrigo Guedes de Souza
 * @author Zoltán Takács
 */
public class CPOptionUpserterForm {

	public static Form<CPOptionUpserterForm> buildForm(
		Form.Builder<CPOptionUpserterForm> formBuilder) {

		return formBuilder.title(
			__ -> "The product option upserter form"
		).description(
			__ -> "This form can be used to create or update a product option"
		).constructor(
			CPOptionUpserterForm::new
		).addOptionalString(
			"description", CPOptionUpserterForm::setDescription
		).addRequiredString(
			"fieldType", CPOptionUpserterForm::setFieldType
		).addRequiredString(
			"key", CPOptionUpserterForm::setKey
		).addRequiredString(
			"name", CPOptionUpserterForm::setName
		).build();
	}

	public String getDescription() {
		return _description;
	}

	public Map<Locale, String> getDescriptionMap() {
		return Collections.singletonMap(LocaleUtil.getDefault(), _description);
	}

	public String getFieldType() {
		return _fieldType;
	}

	public String getKey() {
		return _key;
	}

	public String getName() {
		return _name;
	}

	public Map<Locale, String> getNameMap() {
		return Collections.singletonMap(LocaleUtil.getDefault(), _name);
	}

	public void setDescription(String description) {
		_description = description;
	}

	public void setFieldType(String fieldType) {
		_fieldType = fieldType;
	}

	public void setKey(String key) {
		_key = key;
	}

	public void setName(String name) {
		_name = name;
	}

	private String _description;
	private String _fieldType;
	private String _key;
	private String _name;

}