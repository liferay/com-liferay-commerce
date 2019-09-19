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

package com.liferay.commerce.frontend.model;

import com.liferay.portal.kernel.util.Validator;

/**
 * @author Fabio Diego Mastrorilli
 */
public class SummaryElement {

	public SummaryElement() {
	}

	public String getLabel() {
		return _label;
	}

	public String getStyle() {
		return _style;
	}

	public String getValue() {
		return _value;
	}

	public void setLabel(String label) {
		_label = label;
	}

	public void setStyle(String style) {
		_style = style;
	}

	public void setValue(String value) {
		_value = value;
	}

	public void setValue(String value, String defaultValue) {
		if (Validator.isNull(value)) {
			_value = defaultValue;
		}
		else {
			_value = value;
		}
	}

	private String _label;
	private String _style;
	private String _value;

}