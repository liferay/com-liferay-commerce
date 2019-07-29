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

/**
 * @author Marco Leo
 */
public class HeaderButtonModel {

	public HeaderButtonModel() {
	}

	public String getType() {
		return _type;
	}

	public String getLabel() {
		return _label;
	}

	public String getStyle() {
		return _style;
	}

	public void setType(String type) {
		_type = type;
	}

	public void setLabel(String label) {
		_label = label;
	}

	public void setStyle(String style) {
		_style = style;
	}

	private String _type;
	private String _label;
	private String _style;

}