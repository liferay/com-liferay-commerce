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

package com.liferay.commerce.data.integration.headless.compat.apio.permission;

import com.liferay.apio.architect.alias.routes.permission.HasNestedAddingPermissionFunction;
import com.liferay.apio.architect.credentials.Credentials;
import com.liferay.apio.architect.identifier.Identifier;

/**
 * Contains the information necessary to calculate permissions for the models
 * exposed in Liferay Portal's Apio APIs.
 *
 * <p>
 * The methods that this interface exposes are intended to be used only in the
 * {@code Routes.Builder} methods that need to check permissions. Since the
 * signatures of this interface's methods apply exactly, the methods can be used
 * as method references (e.g., {@code _hasPermission::forAdding}).
 * </p>
 *
 * @author Alejandro Hernández
 * @author Javier Gamarra
 * @param  <T> the type of the model's identifier (e.g., {@code Long}, {@code
 *         String}, etc.)
 */
@SuppressWarnings("RedundantThrows")
public interface HasPermission<T> {

	/**
	 * Returns {@code true} if the current user has permission to perform the
	 * add action.
	 *
	 * <p>
	 * This method is intended to be used for providing a function to check
	 * permissions in {@code
	 * com.liferay.apio.architect.routes.CollectionRoutes.Builder#addCreator}
	 * methods.
	 * </p>
	 *
	 * @param  credentials the current request's credentials
	 * @return {@code true} if the current user can perform the action; {@code
	 *         false} otherwise
	 */
	public default Boolean forAdding(Credentials credentials) throws Exception {
		return false;
	}

	/**
	 * Returns {@code true} if the current user has permission to perform the
	 * add action in a specific location.
	 *
	 * <p>
	 * This method is intended to be used for providing a function to check
	 * permissions in {@code
	 * com.liferay.apio.architect.routes.NestedCollectionRoutes.Builder#addCreator}
	 * methods.
	 * </p>
	 *
	 * @param  identifierClass the class of the parent resource's identifier.
	 *         This must be a subclass of {@code Identifier<S>}.
	 * @return {@code true} if the current user can perform the action; {@code
	 *         false} otherwise
	 */
	public default <S> HasNestedAddingPermissionFunction<S> forAddingIn(
		Class<? extends Identifier<S>> identifierClass) {

		return (credentials, s) -> false;
	}

	/**
	 * Returns {@code true} if the current user has permission to perform the
	 * delete action.
	 *
	 * <p>
	 * This method is intended to be used for providing a function to check
	 * permissions in {@code
	 * com.liferay.apio.architect.routes.ItemRoutes.Builder#addRemover} methods.
	 * </p>
	 *
	 * @param  credentials the current request's credentials
	 * @param  id the model's ID
	 * @return {@code true} if the current user can perform the action; {@code
	 *         false} otherwise
	 */
	public default Boolean forDeleting(Credentials credentials, T id)
		throws Exception {

		return false;
	}

	/**
	 * Returns {@code true} if the current user has permission to perform the
	 * update action.
	 *
	 * <p>
	 * This method is intended to be used for providing a function to check
	 * permissions in {@code
	 * com.liferay.apio.architect.routes.ItemRoutes.Builder#addUpdater} methods.
	 * </p>
	 *
	 * @param  credentials the current request's credentials
	 * @param  id the model's ID
	 * @return {@code true} if the current user can perform the action; {@code
	 *         false} otherwise
	 */
	public default Boolean forUpdating(Credentials credentials, T id)
		throws Exception {

		return false;
	}

}