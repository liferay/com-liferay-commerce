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

package com.liferay.commerce.data.integration.headless.compat.apio.internal.util;

import com.liferay.commerce.data.integration.apio.identifier.ClassPKExternalReferenceCode;
import com.liferay.commerce.data.integration.headless.compat.apio.util.UserHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.UserLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eduardo V. Bruno
 * @author Zoltán Takács
 */
@Component(immediate = true, service = UserHelper.class)
public class CommerceUserHelper implements UserHelper {

	@Override
	public User getUser(ClassPKExternalReferenceCode commerceUserCPKERC)
		throws PortalException {

		return getUser(commerceUserCPKERC, CompanyThreadLocal.getCompanyId());
	}

	@Override
	public User getUser(
			ClassPKExternalReferenceCode commerceUserCPKERC, long companyId)
		throws PortalException {

		long classPK = commerceUserCPKERC.getClassPK();

		if (classPK > 0) {
			return _userLocalService.getUserById(classPK);
		}

		return _userLocalService.fetchUserByReferenceCode(
			companyId, commerceUserCPKERC.getExternalReferenceCode());
	}

	@Override
	public ClassPKExternalReferenceCode userIdToClassPKExternalReferenceCode(
		long userId) {

		User user = _userLocalService.fetchUser(userId);

		return userToClassPKExternalReferenceCode(user);
	}

	@Override
	public ClassPKExternalReferenceCode userToClassPKExternalReferenceCode(
		User user) {

		if (user == null) {
			return null;
		}

		return ClassPKExternalReferenceCode.create(
			user.getUserId(), user.getExternalReferenceCode());
	}

	@Reference
	private UserLocalService _userLocalService;

}