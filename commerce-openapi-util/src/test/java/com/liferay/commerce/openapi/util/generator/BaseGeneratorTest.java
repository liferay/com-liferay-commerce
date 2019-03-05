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

package com.liferay.commerce.openapi.util.generator;

/**
 * @author Igor Beslic
 */
public class BaseGeneratorTest {

	protected boolean containsOnlyOne(String source, String term) {
		int count = 0;
		int indexOf = -1;

		do {
			indexOf = source.indexOf(term, indexOf + 1);

			if (indexOf >= 0) {
				count++;
			}
		} while (indexOf != -1);

		if (count == 1) {
			return true;
		}

		return false;
	}

}