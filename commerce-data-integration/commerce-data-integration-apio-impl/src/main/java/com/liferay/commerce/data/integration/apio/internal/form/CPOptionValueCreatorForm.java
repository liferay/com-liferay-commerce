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

package com.liferay.commerce.data.integration.apio.internal.form;

import com.liferay.apio.architect.form.Form;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.Collections;
import java.util.Locale;
import java.util.Map;

/**
 * @author Rodrigo Guedes de Souza
 */
public class CPOptionValueCreatorForm {

	public static Form<CPOptionValueCreatorForm> buildForm(
		Form.Builder<CPOptionValueCreatorForm> formBuilder) {

		return formBuilder.title(
			__ -> "The option value form"
		).description(
			__ -> "This form can be used to create an option value"
		).constructor(
			CPOptionValueCreatorForm::new
		).addRequiredString(
			"name", CPOptionValueCreatorForm::_setName
		).addRequiredString(
			"key", CPOptionValueCreatorForm::_setKey
		).build();
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

	private void _setKey(String key) {
		_key = key;
	}

	private void _setName(String name) {
		_name = name;
	}

	private String _key;
	private String _name;

}