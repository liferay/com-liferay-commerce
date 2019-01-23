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

package com.liferay.commerce.account.web.internal.frontend;

import com.liferay.commerce.frontend.Filter;
import com.liferay.commerce.frontend.FilterFactory;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = {
		"commerce.data.provider.key=" + CommerceAccountClayTable.NAME,
		"commerce.data.provider.key=" + CommerceAccountOrganizationClayTable.NAME,
		"commerce.data.provider.key=" + CommerceAccountUserClayTable.NAME
	},
	service = FilterFactory.class
)
public class AccountFilterFactoryImpl implements FilterFactory {

	@Override
	public Filter create(HttpServletRequest httpServletRequest) {
		AccountFilterImpl accountFilter = new AccountFilterImpl();

		long commerceAccountId = ParamUtil.getLong(
			httpServletRequest, "commerceAccountId");

		accountFilter.setAccountId(commerceAccountId);

		String keywords = ParamUtil.getString(httpServletRequest, "q");

		accountFilter.setKeywords(keywords);

		return accountFilter;
	}

	@Reference
	private Portal _portal;

}