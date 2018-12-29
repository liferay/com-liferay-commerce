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

package com.liferay.commerce.frontend.internal.clay.table;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import com.liferay.commerce.frontend.ClayTableAction;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marco Leo
 */
public class ClayTableRow {

	public ClayTableRow(Object item) {
		_item = item;
		_actionItems = new ArrayList<>();
	}

	public void addActionItems(List<ClayTableAction> actionItems) {
		this._actionItems.addAll(actionItems);
	}

	public List<ClayTableAction> getActionItems() {
		return _actionItems;
	}

	@JsonUnwrapped
	public Object getItem() {
		return _item;
	}

	private final List<ClayTableAction> _actionItems;
	private final Object _item;

}