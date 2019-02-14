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

package com.liferay.commerce.openapi.admin.model.v2_0;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import com.liferay.commerce.openapi.core.annotation.Nullable;

import java.util.Date;

import javax.annotation.Generated;

/**
 * @author Igor Beslic
 */
@Generated(value = "OSGiRESTModuleGenerator")
@JacksonXmlRootElement(localName = "User")
public class UserDTO {

	@Nullable
	public String getAdditionalName() {
		return _additionalName;
	}

	@Nullable
	public String getAlternateName() {
		return _alternateName;
	}

	@Nullable
	public Date getBirthDate() {
		return _birthDate;
	}

	@Nullable
	public Long[] getCommerceAccountIds() {
		return _commerceAccountIds;
	}

	@Nullable
	public String getDashboardURL() {
		return _dashboardURL;
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

	@Nullable
	public String getGender() {
		return _gender;
	}

	public String getGivenName() {
		return _givenName;
	}

	@Nullable
	public Long getId() {
		return _id;
	}

	@Nullable
	public String getImage() {
		return _image;
	}

	@Nullable
	public String getJobTitle() {
		return _jobTitle;
	}

	@Nullable
	public String getName() {
		return _name;
	}

	@Nullable
	public String getProfileURL() {
		return _profileURL;
	}

	@Nullable
	public String[] getRoleNames() {
		return _roleNames;
	}

	public void setAdditionalName(String additionalName) {
		_additionalName = additionalName;
	}

	public void setAlternateName(String alternateName) {
		_alternateName = alternateName;
	}

	public void setBirthDate(Date birthDate) {
		_birthDate = birthDate;
	}

	public void setCommerceAccountIds(Long[] commerceAccountIds) {
		_commerceAccountIds = commerceAccountIds;
	}

	public void setDashboardURL(String dashboardURL) {
		_dashboardURL = dashboardURL;
	}

	public void setEmail(String email) {
		_email = email;
	}

	public void setExternalReferenceCode(String externalReferenceCode) {
		_externalReferenceCode = externalReferenceCode;
	}

	public void setFamilyName(String familyName) {
		_familyName = familyName;
	}

	public void setGender(String gender) {
		_gender = gender;
	}

	public void setGivenName(String givenName) {
		_givenName = givenName;
	}

	public void setId(Long id) {
		_id = id;
	}

	public void setImage(String image) {
		_image = image;
	}

	public void setJobTitle(String jobTitle) {
		_jobTitle = jobTitle;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setProfileURL(String profileURL) {
		_profileURL = profileURL;
	}

	public void setRoleNames(String[] roleNames) {
		_roleNames = roleNames;
	}

	@Nullable
	private String _additionalName;

	@Nullable
	private String _alternateName;

	@Nullable
	private Date _birthDate;

	@Nullable
	private Long[] _commerceAccountIds;

	@Nullable
	private String _dashboardURL;

	private String _email;
	private String _externalReferenceCode;
	private String _familyName;

	@Nullable
	private String _gender;

	private String _givenName;

	@Nullable
	private Long _id;

	@Nullable
	private String _image;

	@Nullable
	private String _jobTitle;

	@Nullable
	private String _name;

	@Nullable
	private String _profileURL;

	@Nullable
	private String[] _roleNames;

}