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

package com.liferay.commerce.data.integration.apio.internal.form;

import com.liferay.apio.architect.form.Form;
import com.liferay.apio.architect.form.Form.Builder;
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
	 * Builds a {@code Form} that generates {@code CPDefinitionUpserterForm} depending
	 * on the HTTP body.
	 *
	 * @param  formBuilder the {@code Form} builder
	 * @return a product creator form
	 * @review
	 */
	public static Form<CPDefinitionUpserterForm> buildForm(
		Builder<CPDefinitionUpserterForm> formBuilder) {

		return formBuilder.title(
			__ -> "The product upserter form"
		).description(
			__ -> "This form can be used to upsert a product"
		).constructor(
			CPDefinitionUpserterForm::new
		).addOptionalLongList(
			"assetCategoryIds", CPDefinitionUpserterForm::_setAssetCategoryIds
		).addOptionalString(
			"description", CPDefinitionUpserterForm::_setDescription
		).addOptionalString(
			"shortDescription", CPDefinitionUpserterForm::_setShortDescription
		).addRequiredString(
			"productTypeName", CPDefinitionUpserterForm::_setProductTypeName
		).addRequiredString(
			"title", CPDefinitionUpserterForm::_setTitle
		).addRequiredString(
			"externalReferenceCode",
			CPDefinitionUpserterForm::_setExternalReferenceCode
		).addOptionalString(
			"defaultSku", CPDefinitionUpserterForm::_setDefaultSku
		).addRequiredBoolean(
			"active", CPDefinitionUpserterForm::_setActive
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

	private void _setActive(Boolean active) {
		_active = active;
	}

	private void _setAssetCategoryIds(List<Long> assetCategoryIds) {
		_assetCategoryIds = assetCategoryIds;
	}

	private void _setDefaultSku(String defaultSku) {
		_defaultSku = defaultSku;
	}

	private void _setDescription(String description) {
		_description = description;
	}

	private void _setExternalReferenceCode(String externalReferenceCode) {
		_externalReferenceCode = externalReferenceCode;
	}

	private void _setProductTypeName(String productTypeName) {
		_productTypeName = productTypeName;
	}

	private void _setShortDescription(String shortDescription) {
		_shortDescription = shortDescription;
	}

	private void _setTitle(String title) {
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