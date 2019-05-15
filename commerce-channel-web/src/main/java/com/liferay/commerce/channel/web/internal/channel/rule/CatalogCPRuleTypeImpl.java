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

package com.liferay.commerce.channel.web.internal.channel.rule;

import com.liferay.commerce.product.catalog.rule.CPRuleType;
import com.liferay.commerce.product.constants.CPRuleConstants;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPRule;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.service.CommerceCatalogLocalService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alec Sloan
 */
@Component(
	immediate = true,
	property = {
		"commerce.product.rule.type.key=" + CatalogCPRuleTypeImpl.KEY,
		"commerce.product.rule.type.order:Integer=300"
	},
	service = CPRuleType.class
)
public class CatalogCPRuleTypeImpl implements CPRuleType {

	public static final String KEY = "catalogs";

	@Override
	public void contributeDocument(Document document, CPDefinition cpDefinition)
		throws PortalException {

		Set<CommerceCatalog> catalogs = _getCatalogs();

		long[] catalogIds = new long[catalogs.size()];

		int i = 0;

		for (CommerceCatalog catalog : catalogs) {
			catalogIds[i] = catalog.getCommerceCatalogId();

			i++;
		}

		document.addKeyword(_FIELD_CP_RULE_COMMERCE_CATALOG_IDS, catalogIds);
	}

	@Override
	public String getKey() {
		return CatalogCPRuleTypeImpl.KEY;
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, getKey());
	}

	@Override
	public int getScope() {
		return CPRuleConstants.SCOPE_COMPANY;
	}

	@Override
	public UnicodeProperties getTypeSettingsProperties(
		HttpServletRequest httpServletRequest) {

		UnicodeProperties typeSettingsProperties = new UnicodeProperties(true);

		long[] catalogIds = ParamUtil.getLongValues(
			httpServletRequest,
			"CommerceChannelCatalogsSearchContainerPrimaryKeys");

		typeSettingsProperties.put("catalogIds", StringUtil.merge(catalogIds));

		return typeSettingsProperties;
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
	public void update(CPRule cpRule, HttpServletRequest httpServletRequest)
		throws PortalException {
	}

	private Set<CommerceCatalog> _getCatalogs() throws PortalException {
		List<CommerceCatalog> commerceCatalogs =
			_commerceCatalogLocalService.getCommerceCatalogs(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		return SetUtil.fromList(commerceCatalogs);
	}

	private static final String _FIELD_CP_RULE_COMMERCE_CATALOG_IDS =
		"cpRuleCommerceCatalogIds";

	@Reference
	private CommerceCatalogLocalService _commerceCatalogLocalService;

}