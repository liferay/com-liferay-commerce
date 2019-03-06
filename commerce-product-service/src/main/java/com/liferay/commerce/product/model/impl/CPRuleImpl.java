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

package com.liferay.commerce.product.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.product.model.CPRuleAssetCategoryRel;
import com.liferay.commerce.product.model.CPRuleUserSegmentRel;
import com.liferay.commerce.product.service.CPRuleAssetCategoryRelLocalServiceUtil;
import com.liferay.commerce.product.service.CPRuleUserSegmentRelLocalServiceUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.util.List;

/**
 * @author Marco Leo
 * @author Ethan Bustad
 */
@ProviderType
public class CPRuleImpl extends CPRuleBaseImpl {

	public CPRuleImpl() {
	}

	@Override
	public List<CPRuleAssetCategoryRel> getCPRuleAssetCategoryRels() {
		return CPRuleAssetCategoryRelLocalServiceUtil.
			getCPRuleAssetCategoryRels(getCPRuleId());
	}

	@Override
	public List<CPRuleUserSegmentRel> getCPRuleUserSegmentRels() {
		return CPRuleUserSegmentRelLocalServiceUtil.getCPRuleUserSegmentRels(
			getCPRuleId());
	}

	@Override
	public UnicodeProperties getTypeSettingsProperties() {
		if (_typeSettingsProperties == null) {
			_typeSettingsProperties = new UnicodeProperties(true);

			_typeSettingsProperties.fastLoad(getTypeSettings());
		}

		return _typeSettingsProperties;
	}

	@Override
	public void setTypeSettings(String typeSettings) {
		super.setTypeSettings(typeSettings);

		_typeSettingsProperties = null;
	}

	@Override
	public void setTypeSettingsProperties(
		UnicodeProperties typeSettingsProperties) {

		_typeSettingsProperties = typeSettingsProperties;

		if (_typeSettingsProperties == null) {
			_typeSettingsProperties = new UnicodeProperties();
		}

		super.setTypeSettings(_typeSettingsProperties.toString());
	}

	private UnicodeProperties _typeSettingsProperties;

}