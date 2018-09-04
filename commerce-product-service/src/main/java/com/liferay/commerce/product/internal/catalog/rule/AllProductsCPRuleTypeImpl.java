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

package com.liferay.commerce.product.internal.catalog.rule;

import com.liferay.commerce.product.catalog.rule.CPRuleType;
import com.liferay.commerce.product.constants.CPRuleConstants;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPRule;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

/**
 * @author Marco Leo
 */
@Component(
	immediate = true,
	property = {
		"commerce.product.rule.type.key=" + CPRuleConstants.TYPE_ALL_PRODUCTS,
		"commerce.product.rule.type.order:Integer=100"
	},
	service = CPRuleType.class
)
public class AllProductsCPRuleTypeImpl implements CPRuleType {

	@Override
	public void contributeDocument(Document document, CPDefinition cpDefinition)
		throws PortalException {
	}

	@Override
	public String getKey() {
		return CPRuleConstants.TYPE_ALL_PRODUCTS;
	}

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(resourceBundle, "all-products");
	}

	@Override
	public UnicodeProperties getTypeSettingsProperties(
		HttpServletRequest httpServletRequest) {

		return null;
	}

	@Override
	public boolean isSatisfied(CPDefinition cpDefinition, CPRule cpRule)
		throws PortalException {

		return true;
	}

	@Override
	public void postProcessContextBooleanFilter(
			BooleanFilter booleanFilter, CPRule cpRule)
		throws PortalException {
	}

	@Override
	public void update(CPRule cpRule, HttpServletRequest httpServletRequest) {
	}

}