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
import com.liferay.apio.architect.form.Form.Builder;

import java.util.List;

/**
 * @author Rodrigo Guedes de Souza
 */
public class CommerceAccountUpserterForm {

	public static Form<CommerceAccountUpserterForm> buildForm(
		Builder<CommerceAccountUpserterForm> formBuilder) {

		return formBuilder.title(
			__ -> "The account creator form"
		).description(
			__ -> "This form can be used to create an account"
		).constructor(
			CommerceAccountUpserterForm::new
		).addRequiredString(
			"externalReferenceCode",
			CommerceAccountUpserterForm::_setExternalReferenceCode
		).addOptionalLong(
			"countryId", CommerceAccountUpserterForm::_setCountryId
		).addOptionalLong(
			"regionId", CommerceAccountUpserterForm::_setRegionId
		).addRequiredString(
			"name", CommerceAccountUpserterForm::_setName
		).addOptionalLongList(
			"commerceUserIds", CommerceAccountUpserterForm::_setCommerceUserIds
		).build();
	}

	public List<Long> getCommerceUserIds() {
		return _commerceUserIds;
	}

	public long getCountryId() {
		return _countryId;
	}

	public String getExternalReferenceCode() {
		return _externalReferenceCode;
	}

	public String getName() {
		return _name;
	}

	public long getRegionId() {
		return _regionId;
	}

	private void _setCommerceUserIds(List<Long> commerceUserIds) {
		_commerceUserIds = commerceUserIds;
	}

	private void _setCountryId(long countryId) {
		_countryId = countryId;
	}

	private void _setExternalReferenceCode(String externalReferenceCode) {
		_externalReferenceCode = externalReferenceCode;
	}

	private void _setName(String name) {
		_name = name;
	}

	private void _setRegionId(long regionId) {
		_regionId = regionId;
	}

	private List<Long> _commerceUserIds;
	private long _countryId;
	private String _externalReferenceCode;
	private String _name;
	private long _regionId;

}