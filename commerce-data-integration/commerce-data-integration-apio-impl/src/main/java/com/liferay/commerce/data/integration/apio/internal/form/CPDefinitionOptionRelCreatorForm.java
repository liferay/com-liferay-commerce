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

/**
 * @author Rodrigo Guedes de Souza
 */
public class CPDefinitionOptionRelCreatorForm {

	public static Form<CPDefinitionOptionRelCreatorForm> buildForm(
		Form.Builder<CPDefinitionOptionRelCreatorForm> formBuilder) {

		return formBuilder.title(
			__ -> "The product option creator form"
		).description(
			__ -> "This form can be used to create a product option"
		).constructor(
			CPDefinitionOptionRelCreatorForm::new
		).addRequiredLong(
			"commerceProductOptionId",
			CPDefinitionOptionRelCreatorForm::_setCommerceProductOptionId
		).build();
	}

	public Long getCommerceProductOptionId() {
		return _commerceProductOptionId;
	}

	private void _setCommerceProductOptionId(long commerceProductOptionId) {
		_commerceProductOptionId = commerceProductOptionId;
	}

	private long _commerceProductOptionId;

}