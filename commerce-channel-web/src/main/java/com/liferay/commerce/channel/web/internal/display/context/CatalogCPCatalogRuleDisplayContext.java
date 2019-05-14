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

package com.liferay.commerce.channel.web.internal.display.context;

import com.liferay.commerce.product.model.CPRule;
import com.liferay.commerce.product.service.CPRuleService;
import com.liferay.commerce.product.service.CommerceCatalogService;
import com.liferay.item.selector.ItemSelector;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alec Sloan
 */
public class CatalogCPCatalogRuleDisplayContext
	extends BaseCatalogJSPContributorDisplayContext {

	public CatalogCPCatalogRuleDisplayContext(
			CommerceCatalogService commerceCatalogService,
			CPRuleService cpRuleService, HttpServletRequest httpServletRequest,
			ItemSelector itemSelector,
			PortletResourcePermission portletResourcePermission)
		throws PortalException {

		super(
			commerceCatalogService, httpServletRequest, itemSelector,
			portletResourcePermission);

		_cpRuleService = cpRuleService;
		_httpServletRequest = httpServletRequest;
	}

	@Override
	public long[] getCheckedCatalogIds() throws PortalException {
		CPRule cpRule = getCPRule();

		if (cpRule == null) {
			return new long[0];
		}

		UnicodeProperties typeSettingsProperties =
			cpRule.getTypeSettingsProperties();

		return StringUtil.split(
			typeSettingsProperties.getProperty("catalogIds"), 0L);
	}

	protected CPRule getCPRule() throws PortalException {
		CPRule cpRule = null;

		long cpRuleId = ParamUtil.getLong(_httpServletRequest, "cpRuleId");

		if (cpRuleId > 0) {
			cpRule = _cpRuleService.getCPRule(cpRuleId);
		}

		return cpRule;
	}

	private final CPRuleService _cpRuleService;
	private final HttpServletRequest _httpServletRequest;

}