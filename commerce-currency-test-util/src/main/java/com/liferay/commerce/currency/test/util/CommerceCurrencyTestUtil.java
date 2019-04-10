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

package com.liferay.commerce.currency.test.util;

import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.model.CommerceCurrencyConstants;
import com.liferay.commerce.currency.service.CommerceCurrencyLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.math.BigDecimal;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author Luca Pellizzon
 */
public class CommerceCurrencyTestUtil {

	public static CommerceCurrency addCommerceCurrency(long groupId)
		throws PortalException {

		return addCommerceCurrency(groupId, RandomTestUtil.randomString());
	}

	public static CommerceCurrency addCommerceCurrency(
			long groupId, String code)
		throws PortalException {

		Map<Locale, String> formatPatternMap = new HashMap();

		formatPatternMap.put(
			LocaleUtil.US, CommerceCurrencyConstants.DEFAULT_FORMAT_PATTERN);

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(groupId);

		return CommerceCurrencyLocalServiceUtil.addCommerceCurrency(
			code, RandomTestUtil.randomLocaleStringMap(), BigDecimal.ONE,
			formatPatternMap, 2, 2, "", false, RandomTestUtil.randomDouble(),
			true, serviceContext);
	}

}