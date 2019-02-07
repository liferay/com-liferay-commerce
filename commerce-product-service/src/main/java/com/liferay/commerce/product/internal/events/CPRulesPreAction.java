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

package com.liferay.commerce.product.internal.events;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.util.CommerceAccountHelper;
import com.liferay.commerce.product.catalog.rule.CPRuleHelper;
import com.liferay.commerce.product.util.CPRulesThreadLocal;
import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Andrea Di Giorgi
 */
@Component(
	property = {
		"key=servlet.service.events.pre", "service.ranking:Integer=100"
	},
	service = LifecycleAction.class
)
public class CPRulesPreAction extends Action {

	@Override
	public void run(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws ActionException {

		try {
			long groupId = ParamUtil.getLong(httpServletRequest, "groupId", -1);

			if (groupId == -1) {
				groupId = _portal.getScopeGroupId(httpServletRequest);
			}

			long commerceAccountId = ParamUtil.getLong(
				httpServletRequest, "commerceAccountId", -99);

			if (commerceAccountId == -99) {
				CommerceAccount commerceAccount =
					_commerceAccountHelper.getCurrentCommerceAccount(
						groupId, httpServletRequest);

				if (commerceAccount == null) {
					CPRulesThreadLocal.setCPRules(Collections.emptyList());

					return;
				}

				commerceAccountId = commerceAccount.getCommerceAccountId();
			}

			_cpRuleHelper.initializeCPRules(
				_portal.getUserId(httpServletRequest), commerceAccountId,
				groupId);
		}
		catch (PortalException pe) {
			throw new ActionException(pe);
		}
	}

	@Reference
	private CommerceAccountHelper _commerceAccountHelper;

	@Reference
	private CPRuleHelper _cpRuleHelper;

	@Reference
	private Portal _portal;

}