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

import com.liferay.apio.architect.credentials.Credentials;
import com.liferay.apio.architect.function.throwable.ThrowableBiFunction;
import com.liferay.apio.architect.functional.Try;

import java.util.function.BiFunction;

/**
 * Provides utility functions for API permission checkers. This class should not
 * be instantiated.
 *
 * @author Sarai Díaz
 */
public class HasPermissionUtil {

	/**
	 * Executes the received function; if any exceptions occur, a boolean {@code
	 * false} is returned.
	 *
	 * @param  throwableBiFunction the function
	 * @return the function's results, or {@code false} if an exception occurs
	 */
	public static BiFunction<Credentials, Long, Boolean> failOnException(
		ThrowableBiFunction<Credentials, Long, Boolean> throwableBiFunction) {

		return (credentials, id) -> Try.fromFallible(
			() -> throwableBiFunction.apply(credentials, id)
		).orElse(
			false
		);
	}

}