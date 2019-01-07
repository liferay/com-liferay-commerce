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

package com.liferay.commerce.frontend;

import com.liferay.petra.string.StringPool;

import java.util.Map;

/**
 * @author Marco Leo
 */
public interface ClayTable {

	public default String getActionsMenuVariant() {
		return StringPool.BLANK;
	}

	public ClayTableSchema getClayTableSchema();

	public default String getElementClasses() {
		return StringPool.BLANK;
	}

	public String getId();

	public default Map<String, Object> getProperties() {
		return null;
	}

	public default ResponsiveSize getResponsiveSize() {
		return ResponsiveSize.MD;
	}

	public default String getTableVariant() {
		return StringPool.BLANK;
	}

	public default boolean isSelectable() {
		return false;
	}

	public default boolean isShowActionsMenu() {
		return false;
	}

	public enum ResponsiveSize {

		LG, MD, SM, XL

	}

}