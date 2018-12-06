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

package com.liferay.commerce.data.integration.headless.compat.apio.util;

import com.liferay.commerce.data.integration.apio.identifier.ClassPKExternalReferenceCode;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;

/**
 * @author Zoltán Takács
 */
public interface UserHelper {

	public User getUser(ClassPKExternalReferenceCode commerceUserCPKERC)
		throws PortalException;

	public User getUser(
			ClassPKExternalReferenceCode commerceUserCPKERC, long companyId)
		throws PortalException;

	public ClassPKExternalReferenceCode userIdToClassPKExternalReferenceCode(
		long userId);

	public ClassPKExternalReferenceCode userToClassPKExternalReferenceCode(
		User user);

}