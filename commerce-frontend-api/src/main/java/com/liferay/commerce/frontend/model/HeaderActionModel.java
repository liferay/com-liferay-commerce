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

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
public class HeaderActionModel {

	public HeaderActionModel() {
	}

	public String getHref() {
		return _href;
	}

	public String getId() {
		return _id;
	}

	public String getLabel() {
		return _label;
	}

	public String getStyle() {
		return _style;
	}

	public void setHref(String href) {
		if (Validator.isNull(href)) {
			href = StringPool.POUND;
		}

		_href = href;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setLabel(String label) {
		_label = label;
	}

	public void setStyle(String style) {
		if (Validator.isNull(style)) {
			style = "primary";
		}

		_style = style;
	}

	private String _href = StringPool.POUND;
	private String _id;
	private String _label;
	private String _style = "primary";

}