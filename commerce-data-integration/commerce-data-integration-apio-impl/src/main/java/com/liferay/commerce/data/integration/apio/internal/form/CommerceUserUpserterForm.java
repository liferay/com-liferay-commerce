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
import com.liferay.portal.kernel.util.ArrayUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Rodrigo Guedes de Souza
 */
public class CommerceUserUpserterForm {

	public static Form<CommerceUserUpserterForm> buildForm(
		Form.Builder<CommerceUserUpserterForm> formBuilder) {

		return formBuilder.title(
			__ -> "The user creator form"
		).description(
			__ -> "This form can be used to create a user"
		).constructor(
			CommerceUserUpserterForm::new
		).addRequiredString(
			"externalReferenceCode",
			CommerceUserUpserterForm::_setExternalReferenceCode
		).addRequiredString(
			"email", CommerceUserUpserterForm::_setEmail
		).addOptionalString(
			"familyName", CommerceUserUpserterForm::_setFamilyName
		).addOptionalString(
			"givenName", CommerceUserUpserterForm::_setGivenName
		).addRequiredString(
			"jobTitle", CommerceUserUpserterForm::_setJobTitle
		).addOptionalLongList(
			"commerceAccountIds",
			CommerceUserUpserterForm::_setCommerceAccountIds
		).addOptionalString(
			"roleNames", CommerceUserUpserterForm::_setRoleNames
		).addOptionalString(
			"accountExternalReferenceCode",
			CommerceUserUpserterForm::_setAccountIdExternalReferenceCode
		).build();
	}

	public String getAccountExternalReferenceCode() {
		return _accountExternalReferenceCode;
	}

	public long[] getCommerceAccountIds() {
		if (_commerceAccountIds == null) {
			return new long[0];
		}

		return ArrayUtil.toLongArray(_commerceAccountIds);
	}

	public String getEmail() {
		return _email;
	}

	public String getExternalReferenceCode() {
		return _externalReferenceCode;
	}

	public String getFamilyName() {
		return _familyName;
	}

	public String getGivenName() {
		return _givenName;
	}

	public String getJobTitle() {
		return _jobTitle;
	}

	public String[] getRoleNames() {
		if ((_roleNames == null) || _roleNames.isEmpty()) {
			return new String[0];
		}

		Stream<String> roleStream = Arrays.stream(_roleNames.split(","));

		return roleStream.map(
			String::trim
		).toArray(
			String[]::new
		);
	}

	private void _setAccountIdExternalReferenceCode(
		String accountExternalReferenceCode) {

		_accountExternalReferenceCode = accountExternalReferenceCode;
	}

	private void _setCommerceAccountIds(List<Long> commerceAccountIds) {
		_commerceAccountIds = commerceAccountIds;
	}

	private void _setEmail(String emailAddress) {
		_email = emailAddress;
	}

	private void _setExternalReferenceCode(String externalReferenceCode) {
		_externalReferenceCode = externalReferenceCode;
	}

	private void _setFamilyName(String familyName) {
		_familyName = familyName;
	}

	private void _setGivenName(String givenName) {
		_givenName = givenName;
	}

	private void _setJobTitle(String jobTitle) {
		_jobTitle = jobTitle;
	}

	private void _setRoleNames(String roleNames) {
		_roleNames = roleNames;
	}

	private String _accountExternalReferenceCode;
	private List<Long> _commerceAccountIds;
	private String _email;
	private String _externalReferenceCode;
	private String _familyName;
	private String _givenName;
	private String _jobTitle;
	private String _roleNames;

}