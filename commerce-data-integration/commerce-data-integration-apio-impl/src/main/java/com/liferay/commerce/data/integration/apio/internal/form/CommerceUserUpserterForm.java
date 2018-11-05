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
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author Alejandro Hernández
 * @author Rodrigo Guedes de Souza
 * @author Zoltán Takács
 */
public class CommerceUserUpserterForm {

	public static Form<CommerceUserUpserterForm> buildForm(
		Form.Builder<CommerceUserUpserterForm> formBuilder) {

		return formBuilder.title(
			__ -> "The commerce user upserter form"
		).description(
			__ -> "This form can be used to create or update a commerce user"
		).constructor(
			CommerceUserUpserterForm::new
		).addOptionalDate(
			"birthDate", CommerceUserUpserterForm::setBirthDate
		).addOptionalLongList(
			"commerceAccountIds",
			CommerceUserUpserterForm::setCommerceAccountIds
		).addOptionalString(
			"alternateName", CommerceUserUpserterForm::setAlternateName
		).addOptionalString(
			"externalReferenceCode",
			CommerceUserUpserterForm::setExternalReferenceCode
		).addOptionalString(
			"gender", CommerceUserUpserterForm::setGender
		).addOptionalString(
			"honorificPrefix", CommerceUserUpserterForm::setHonorificPrefix
		).addOptionalString(
			"honorificSuffix", CommerceUserUpserterForm::setHonorificSuffix
		).addOptionalString(
			"jobTitle", CommerceUserUpserterForm::setJobTitle
		).addOptionalString(
			"roleNames", CommerceUserUpserterForm::setRoleNames
		).addRequiredString(
			"email", CommerceUserUpserterForm::setEmail
		).addRequiredString(
			"familyName", CommerceUserUpserterForm::setFamilyName
		).addRequiredString(
			"givenName", CommerceUserUpserterForm::setGivenName
		).build();
	}

	/**
	 * Returns the person's alternate name.
	 *
	 * @return the person's alternate name
	 */
	public String getAlternateName() {
		return _alternateName;
	}

	/**
	 * Returns the person's birth day.
	 *
	 * @return the person's birth day
	 */
	public int getBirthdayDay() {
		if (_birthdayDay != null) {
			return _birthdayDay;
		}

		return 1;
	}

	/**
	 * Returns the person's birth day.
	 *
	 * @return the person's birth day
	 */
	public Optional<Integer> getBirthdayDayOptional() {
		return Optional.ofNullable(_birthdayDay);
	}

	/**
	 * Returns the person's birth month.
	 *
	 * @return the person's birth month
	 */
	public int getBirthdayMonth() {
		if (_birthdayMonth != null) {
			return _birthdayMonth;
		}

		return 0;
	}

	/**
	 * Returns the person's birth month.
	 *
	 * @return the person's birth month
	 */
	public Optional<Integer> getBirthdayMonthOptional() {
		return Optional.ofNullable(_birthdayMonth);
	}

	/**
	 * Returns the person's birth year.
	 *
	 * @return the person's birth year
	 */
	public int getBirthdayYear() {
		if (_birthdayYear != null) {
			return _birthdayYear;
		}

		return 1970;
	}

	/**
	 * Returns the person's birth year.
	 *
	 * @return the person's birth year
	 */
	public Optional<Integer> getBirthdayYearOptional() {
		return Optional.ofNullable(_birthdayYear);
	}

	public long[] getCommerceAccountIds() {
		if (_commerceAccountIds == null) {
			return new long[0];
		}

		return ArrayUtil.toLongArray(_commerceAccountIds);
	}

	/**
	 * Returns the person's email address.
	 *
	 * @return the person's email address
	 */
	public String getEmail() {
		return _email;
	}

	public String getExternalReferenceCode() {
		return _externalReferenceCode;
	}

	/**
	 * Returns the person's family name.
	 *
	 * @return the person's family name
	 */
	public String getFamilyName() {
		return _familyName;
	}

	/**
	 * Returns the person's given name.
	 *
	 * @return the person's given name
	 */
	public String getGivenName() {
		return _givenName;
	}

	public String getHonorificPrefix() {
		return _honorificPrefix;
	}

	public String getHonorificSuffix() {
		return _honorificSuffix;
	}

	/**
	 * Returns the person's job title.
	 *
	 * @return the person's job title
	 */
	public String getJobTitle() {
		return Optional.ofNullable(
			_jobTitle
		).orElse(
			""
		);
	}

	/**
	 * Returns the person's password.
	 *
	 * @return the person's password
	 */
	public String getPassword() {
		return _password;
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

	/**
	 * Returns {@code true} if the person is male.
	 *
	 * @return {@code true} if the person is a male; {@code false} otherwise
	 */
	public boolean isMale() {
		return Optional.ofNullable(
			_male
		).orElse(
			true
		);
	}

	public Optional<Boolean> isMaleOptional() {
		return Optional.ofNullable(_male);
	}

	/**
	 * Returns {@code true} if the person has an alternate name.
	 *
	 * @return {@code true} if the person has an alternate name; {@code false}
	 *         otherwise
	 */
	public boolean needsAlternateName() {
		return Validator.isNull(_alternateName);
	}

	public void setAlternateName(String alternateName) {
		_alternateName = alternateName;
	}

	public void setBirthDate(Date birthDate) {
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(birthDate);

		_birthdayMonth = calendar.get(Calendar.MONTH);
		_birthdayDay = calendar.get(Calendar.DATE);
		_birthdayYear = calendar.get(Calendar.YEAR);
	}

	public void setCommerceAccountIds(List<Long> commerceAccountIds) {
		_commerceAccountIds = commerceAccountIds;
	}

	public void setEmail(String emailAddress) {
		_email = emailAddress;
	}

	public void setExternalReferenceCode(String externalReferenceCode) {
		_externalReferenceCode = externalReferenceCode;
	}

	public void setFamilyName(String lastName) {
		_familyName = lastName;
	}

	public void setGender(String gender) {
		_male = "male".equals(gender);
	}

	public void setGivenName(String givenName) {
		_givenName = givenName;
	}

	public void setHonorificPrefix(String honorificPrefix) {
		_honorificPrefix = honorificPrefix;
	}

	public void setHonorificSuffix(String honorificSuffix) {
		_honorificSuffix = honorificSuffix;
	}

	public void setJobTitle(String jobTitle) {
		_jobTitle = jobTitle;
	}

	public void setPassword(String password) {
		_password = password;
	}

	public void setRoleNames(String roleNames) {
		_roleNames = roleNames;
	}

	private String _alternateName;
	private Integer _birthdayDay;
	private Integer _birthdayMonth;
	private Integer _birthdayYear;
	private List<Long> _commerceAccountIds;
	private String _email;
	private String _externalReferenceCode;
	private String _familyName;
	private String _givenName;
	private String _honorificPrefix;
	private String _honorificSuffix;
	private String _jobTitle;
	private Boolean _male;
	private String _password;
	private String _roleNames;

}