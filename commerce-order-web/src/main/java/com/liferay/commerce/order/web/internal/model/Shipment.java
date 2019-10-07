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

package com.liferay.commerce.order.web.internal.model;

import java.util.Date;

/**
 * @author Alessio Antonio Rendina
 */
public class Shipment {

	public Shipment(
		long shipmentId, String panelUrl, String panelTarget, String address,
		Date createDate, String statusLabel, String statusStyle,
		String tracking) {

		_shipmentId = shipmentId;
		_panelUrl = panelUrl;
		_panelTarget = panelTarget;
		_address = address;
		_createDate = createDate;
		_statusLabel = statusLabel;
		_statusStyle = statusStyle;
		_tracking = tracking;
	}

	public String getAddress() {
		return _address;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public String getPanelTarget() {
		return _panelTarget;
	}

	public String getPanelUrl() {
		return _panelUrl;
	}

	public long getShipmentId() {
		return _shipmentId;
	}

	public String getStatusLabel() {
		return _statusLabel;
	}

	public String getStatusStyle() {
		return _statusStyle;
	}

	public String getTracking() {
		return _tracking;
	}

	private final String _address;
	private final Date _createDate;
	private final String _panelTarget;
	private final String _panelUrl;
	private final long _shipmentId;
	private final String _statusLabel;
	private final String _statusStyle;
	private final String _tracking;

}