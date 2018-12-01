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

package com.liferay.commerce.data.integration.apio.internal.form;

import com.liferay.apio.architect.form.Form;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Instances of this class represent the values extracted from a product form.
 *
 * @author Zoltán Takács
 * @review
 */
public class CPDefinitionUpserterForm {

	/**
	 * Builds a {@code Form} that generates {@code CPDefinitionUpserterForm}
	 * depending on the HTTP body.
	 *
	 * @param  formBuilder the {@code Form} builder
	 * @return a product creator form
	 * @review
	 */
	public static Form<CPDefinitionUpserterForm> buildForm(
		Form.Builder<CPDefinitionUpserterForm> formBuilder) {

		return formBuilder.title(
			__ -> "The product upserter form"
		).description(
			__ ->
				"This form can be used to create or update a product definition"
		).constructor(
			CPDefinitionUpserterForm::new
		).addOptionalLongList(
			"assetCategoryIds", CPDefinitionUpserterForm::setAssetCategoryIds
		).addOptionalString(
			"defaultSku", CPDefinitionUpserterForm::setDefaultSku
		).addOptionalString(
			"description", CPDefinitionUpserterForm::setDescription
		).addOptionalString(
			"shortDescription", CPDefinitionUpserterForm::setShortDescription
		).addRequiredBoolean(
			"active", CPDefinitionUpserterForm::setActive
		).addRequiredString(
			"externalReferenceCode",
			CPDefinitionUpserterForm::setExternalReferenceCode
		).addRequiredString(
			"productTypeName", CPDefinitionUpserterForm::setProductTypeName
		).addRequiredString(
			"title", CPDefinitionUpserterForm::setTitle
		).build();
	}

	public Boolean getActive() {
		return _active;
	}

	public List<Long> getAssetCategoryIds() {
		if (_assetCategoryIds == null) {
			return new ArrayList<>();
		}

		return _assetCategoryIds;
	}

	public String getDefaultSku() {
		return _defaultSku;
	}

	public Map<Locale, String> getDescriptionMap() {
		return Collections.singletonMap(LocaleUtil.getDefault(), _description);
	}

	public String getExternalReferenceCode() {
		return _externalReferenceCode;
	}

	public String getProductTypeName() {
		return _productTypeName;
	}

	public Map<Locale, String> getShortDescriptionMap() {
		return Collections.singletonMap(
			LocaleUtil.getDefault(), _shortDescription);
	}

	public Map<Locale, String> getTitleMap() {
		return Collections.singletonMap(LocaleUtil.getDefault(), _title);
	}

	public void setActive(Boolean active) {
		_active = active;
	}

	public void setAssetCategoryIds(List<Long> assetCategoryIds) {
		_assetCategoryIds = assetCategoryIds;
	}

	public void setDefaultSku(String defaultSku) {
		_defaultSku = defaultSku;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public void setExternalReferenceCode(String externalReferenceCode) {
		_externalReferenceCode = externalReferenceCode;
	}

	public void setProductTypeName(String productTypeName) {
		_productTypeName = productTypeName;
	}

	public void setShortDescription(String shortDescription) {
		_shortDescription = shortDescription;
	}

	public void setTitle(String title) {
		_title = title;
	}

	private Boolean _active;
	private List<Long> _assetCategoryIds;
	private String _defaultSku;
	private String _description;
	private String _externalReferenceCode;
	private String _productTypeName;
	private String _shortDescription;
	private String _title;

}