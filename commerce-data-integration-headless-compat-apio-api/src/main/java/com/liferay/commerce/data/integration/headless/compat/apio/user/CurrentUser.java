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

package com.liferay.commerce.data.integration.headless.compat.apio.user;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserWrapper;

/**
 * Serves as an alias for the current authenticated {@code User}.
 *
 * <p>
 * To use this class, add it as a parameter to route builder methods.
 * </p>
 *
 * @author Alejandro Hernández
 * @author Carlos Sierra Andrés
 * @author Jorge Ferrer
 */
public class CurrentUser extends UserWrapper {

	public CurrentUser(User user) {
		super(user);
	}

}