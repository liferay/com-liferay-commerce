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

package com.liferay.commerce.organization.web.internal.frontend;

import com.liferay.commerce.frontend.ClayTableAction;
import com.liferay.commerce.frontend.ClayTableActionProvider;
import com.liferay.commerce.organization.web.internal.model.Account;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = "commerce.table.name=" + CommerceOrganizationClayTable.NAME,
	service = ClayTableActionProvider.class
)
public class CommerceOrganizationClayTableActionProvider
	implements ClayTableActionProvider {

	@Override
	public List<ClayTableAction> clayTableActions(
			HttpServletRequest httpServletRequest, long groupId, Object model)
		throws PortalException {

		List<ClayTableAction> clayTableActions = new ArrayList<>();

		Account account = (Account)model;

		PortletURL viewURL = PortletProviderUtil.getPortletURL(
			httpServletRequest, Account.class.getName(),
			PortletProvider.Action.VIEW);

		viewURL.setParameter(
			"organizationId", String.valueOf(account.getAccountId()));

		ClayTableAction clayTableAction = new ClayTableAction(
			viewURL.toString(), StringPool.BLANK, "view", false, false);

		clayTableActions.add(clayTableAction);

		return clayTableActions;
	}

}