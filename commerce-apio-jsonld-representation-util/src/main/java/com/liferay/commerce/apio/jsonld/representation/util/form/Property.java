/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.commerce.apio.jsonld.representation.util.form;

/**
 * Represents the Apio form's property
 *
 * @author Zoltán Takács
 */
public class Property {

	public Property(
		String name, boolean required, boolean readable, boolean writeable) {

		if (name == null) {
			throw new IllegalArgumentException("Name is NULL");
		}

		_name = name;
		_required = required;
		_readable = readable;
		_writeable = writeable;
	}

	public String getName() {
		return _name;
	}

	public boolean isReadable() {
		return _readable;
	}

	public boolean isRequired() {
		return _required;
	}

	public boolean isWriteable() {
		return _writeable;
	}

	private final String _name;
	private final boolean _readable;
	private final boolean _required;
	private final boolean _writeable;

}