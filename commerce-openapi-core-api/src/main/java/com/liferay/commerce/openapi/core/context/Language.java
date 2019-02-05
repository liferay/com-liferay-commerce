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

package com.liferay.commerce.openapi.core.context;

import java.util.Locale;
import java.util.Objects;

/**
 * @author Zoltán Takács
 */
public class Language {

	public Language(Locale locale) {
		this(locale.getLanguage());
	}

	public Language(String languageId) {
		Objects.requireNonNull(languageId);

		_languageId = languageId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}

		Language language = (Language)o;

		return _languageId.equals(language._languageId);
	}

	public String getLanguageId() {
		return _languageId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_languageId);
	}

	@Override
	public String toString() {
		return _languageId;
	}

	private String _languageId;

}