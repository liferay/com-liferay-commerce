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

package com.liferay.commerce.openapi.admin.model;

import java.util.Date;

import javax.annotation.Generated;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Igor Beslic
 */
@Generated(value = "OSGiRESTModuleGenerator")
@XmlRootElement(name = "User")
public class UserDTO {

	public String getAdditionalName() {
		return _additionalName;
	}

	public String getAlternateName() {
		return _alternateName;
	}

	public Date getBirthDate() {
		return _birthDate;
	}

	public long[] getCommerceAccountIds() {
		return _commerceAccountIds;
	}

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

	public String getGender() {
		return _gender;
	}

	public String getGivenName() {
		return _givenName;
	}

	public long getId() {
		return _id;
	}

	public String getImage() {
		return _image;
	}

	public String getJobTitle() {
		return _jobTitle;
	}

	public String getName() {
		return _name;
	}

	public String getProfileURL() {
		return _profileURL;
	}

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

	public void setCommerceAccountIds(long[] commerceAccountIds) {
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

	public void setId(long id) {
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

	private String _additionalName;
	private String _alternateName;
	private Date _birthDate;
	private long[] _commerceAccountIds;
	private String _dashboardURL;
	private String _email;
	private String _externalReferenceCode;
	private String _familyName;
	private String _gender;
	private String _givenName;
	private long _id;
	private String _image;
	private String _jobTitle;
	private String _name;
	private String _profileURL;
	private String[] _roleNames;

}