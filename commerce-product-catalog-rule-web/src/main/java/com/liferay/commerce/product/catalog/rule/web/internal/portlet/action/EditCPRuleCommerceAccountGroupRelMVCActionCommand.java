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

package com.liferay.commerce.product.catalog.rule.web.internal.portlet.action;

import com.liferay.commerce.product.constants.CPPortletKeys;
import com.liferay.commerce.product.exception.NoSuchCPRuleCommerceAccountGroupRelException;
import com.liferay.commerce.product.exception.NoSuchCPRuleException;
import com.liferay.commerce.product.model.CPRuleCommerceAccountGroupRel;
import com.liferay.commerce.product.service.CPRuleCommerceAccountGroupRelService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + CPPortletKeys.CP_CATALOG_RULE,
		"mvc.command.name=editCPRuleCommerceAccountGroupRel"
	},
	service = MVCActionCommand.class
)
public class EditCPRuleCommerceAccountGroupRelMVCActionCommand
	extends BaseMVCActionCommand {

	protected void addCPRuleCommerceAccountGroupRels(
			ActionRequest actionRequest)
		throws Exception {

		long[] addcommerceAccountGroupIds = null;

		long cpRuleId = ParamUtil.getLong(actionRequest, "cpRuleId");

		long commerceAccountGroupId = ParamUtil.getLong(
			actionRequest, "commerceAccountGroupId");

		if (commerceAccountGroupId > 0) {
			addcommerceAccountGroupIds = new long[] {commerceAccountGroupId};
		}
		else {
			addcommerceAccountGroupIds = StringUtil.split(
				ParamUtil.getString(actionRequest, "commerceAccountGroupIds"),
				0L);
		}

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			CPRuleCommerceAccountGroupRel.class.getName(), actionRequest);

		for (long addcommerceAccountGroupId : addcommerceAccountGroupIds) {
			_cpRuleCommerceAccountGroupRelService.
				addCPRuleCommerceAccountGroupRel(
					cpRuleId, addcommerceAccountGroupId, serviceContext);
		}
	}

	protected void deleteCPRuleCommerceAccountGroupRels(
			ActionRequest actionRequest)
		throws PortalException {

		long[] deleteCPRuleCommerceAccountGroupRelIds = null;

		long cpRuleCommerceAccountGroupRelId = ParamUtil.getLong(
			actionRequest, "cpRuleCommerceAccountGroupRelId");

		if (cpRuleCommerceAccountGroupRelId > 0) {
			deleteCPRuleCommerceAccountGroupRelIds = new long[] {
				cpRuleCommerceAccountGroupRelId
			};
		}
		else {
			deleteCPRuleCommerceAccountGroupRelIds = StringUtil.split(
				ParamUtil.getString(
					actionRequest, "deleteCPRuleCommerceAccountGroupRelIds"),
				0L);
		}

		for (long deleteCPRuleCommerceAccountGroupRelId :
				deleteCPRuleCommerceAccountGroupRelIds) {

			_cpRuleCommerceAccountGroupRelService.
				deleteCPRuleCommerceAccountGroupRel(
					deleteCPRuleCommerceAccountGroupRelId);
		}
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			if (cmd.equals(Constants.ADD)) {
				addCPRuleCommerceAccountGroupRels(actionRequest);
			}
			else if (cmd.equals(Constants.DELETE)) {
				deleteCPRuleCommerceAccountGroupRels(actionRequest);
			}
		}
		catch (Exception e) {
			if (e instanceof NoSuchCPRuleException ||
				e instanceof NoSuchCPRuleCommerceAccountGroupRelException ||
				e instanceof PrincipalException) {

				SessionErrors.add(actionRequest, e.getClass());

				actionResponse.setRenderParameter("mvcPath", "/error.jsp");
			}
			else {
				throw e;
			}
		}
	}

	@Reference
	private CPRuleCommerceAccountGroupRelService
		_cpRuleCommerceAccountGroupRelService;

}