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

package com.liferay.commerce.theme.minium.api.internal.product.model;

/**
 * @author Marco Leo
 */
public class ProductCompareModel {

	public ProductCompareModel(long id, String thumbnail, String visibility) {
		_id = id;
		_thumbnail = thumbnail;
		_visibility = visibility;
	}

	public long getId() {
		return _id;
	}

	public String getThumbnail() {
		return _thumbnail;
	}

	public String getVisibility() {
		return _visibility;
	}

	private final long _id;
	private final String _thumbnail;
	private final String _visibility;

}