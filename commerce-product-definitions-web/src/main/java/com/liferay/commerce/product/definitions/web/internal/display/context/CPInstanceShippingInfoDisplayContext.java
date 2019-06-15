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

package com.liferay.commerce.product.definitions.web.internal.display.context;

import com.liferay.commerce.currency.util.CommercePriceFormatter;
import com.liferay.commerce.product.definitions.web.portlet.action.ActionHelper;
import com.liferay.commerce.product.model.CPMeasurementUnit;
import com.liferay.commerce.product.service.CPDefinitionOptionRelService;
import com.liferay.commerce.product.service.CPInstanceService;
import com.liferay.commerce.product.service.CPMeasurementUnitLocalService;
import com.liferay.commerce.product.util.CPInstanceHelper;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alessio Antonio Rendina
 */
public class CPInstanceShippingInfoDisplayContext
	extends CPInstanceDisplayContext {

	public CPInstanceShippingInfoDisplayContext(
			ActionHelper actionHelper, HttpServletRequest httpServletRequest,
			CommercePriceFormatter commercePriceFormatter,
			CPDefinitionOptionRelService cpDefinitionOptionRelService,
			CPInstanceService cpInstanceService,
			CPInstanceHelper cpInstanceHelper,
			CPMeasurementUnitLocalService cpMeasurementUnitLocalService)
		throws PortalException {

		super(
			actionHelper, httpServletRequest, commercePriceFormatter,
			cpDefinitionOptionRelService, cpInstanceService, cpInstanceHelper);

		_cpMeasurementUnitLocalService = cpMeasurementUnitLocalService;
	}

	public String getCPMeasurementUnitName(int type) {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		CPMeasurementUnit cpMeasurementUnit =
			_cpMeasurementUnitLocalService.fetchPrimaryCPMeasurementUnit(
				themeDisplay.getCompanyId(), type);

		if (cpMeasurementUnit != null) {
			return cpMeasurementUnit.getName(themeDisplay.getLanguageId());
		}

		return StringPool.BLANK;
	}

	private final CPMeasurementUnitLocalService _cpMeasurementUnitLocalService;

}