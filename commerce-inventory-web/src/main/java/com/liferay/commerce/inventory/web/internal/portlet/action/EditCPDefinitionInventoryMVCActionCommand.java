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

package com.liferay.commerce.inventory.web.internal.portlet.action;

import com.liferay.commerce.exception.NoSuchCPDefinitionInventoryException;
import com.liferay.commerce.model.CPDefinitionInventory;
import com.liferay.commerce.product.constants.CPPortletKeys;
import com.liferay.commerce.service.CPDAvailabilityEstimateService;
import com.liferay.commerce.service.CPDefinitionInventoryService;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;

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
		"javax.portlet.name=" + CPPortletKeys.CP_DEFINITIONS,
		"mvc.command.name=editProductDefinitionInventory"
	},
	service = MVCActionCommand.class
)
public class EditCPDefinitionInventoryMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			if (cmd.equals(Constants.ADD) || cmd.equals(Constants.UPDATE)) {
				updateCPDefinitionInventory(actionRequest);
			}
		}
		catch (Exception e) {
			if (e instanceof NoSuchCPDefinitionInventoryException ||
				e instanceof NumberFormatException ||
				e instanceof PrincipalException) {

				hideDefaultErrorMessage(actionRequest);
				hideDefaultSuccessMessage(actionRequest);

				SessionErrors.add(actionRequest, e.getClass());

				String redirect = ParamUtil.getString(
					actionRequest, "redirect");

				sendRedirect(actionRequest, actionResponse, redirect);
			}
			else {
				throw e;
			}
		}
	}

	protected void updateCPDefinitionInventory(ActionRequest actionRequest)
		throws Exception {

		long cpDefinitionInventoryId = ParamUtil.getLong(
			actionRequest, "cpDefinitionInventoryId");

		long cpdAvailabilityEstimateEntryId = ParamUtil.getLong(
			actionRequest, "cpdAvailabilityEstimateEntryId");

		long cpDefinitionId = ParamUtil.getLong(
			actionRequest, "cpDefinitionId");

		String cpDefinitionInventoryEngine = ParamUtil.getString(
			actionRequest, "CPDefinitionInventoryEngine");
		String lowStockActivity = ParamUtil.getString(
			actionRequest, "lowStockActivity");
		long commerceAvailabilityEstimateId = ParamUtil.getLong(
			actionRequest, "commerceAvailabilityEstimateId");
		boolean displayAvailability = ParamUtil.getBoolean(
			actionRequest, "displayAvailability");
		boolean displayStockQuantity = ParamUtil.getBoolean(
			actionRequest, "displayStockQuantity");
		boolean backOrders = ParamUtil.getBoolean(actionRequest, "backOrders");
		String minStockQuantityString = ParamUtil.getString(
			actionRequest, "minStockQuantity");
		String minOrderQuantityString = ParamUtil.getString(
			actionRequest, "minOrderQuantity");
		String maxOrderQuantityString = ParamUtil.getString(
			actionRequest, "maxOrderQuantity");
		String multipleOrderQuantityString = ParamUtil.getString(
			actionRequest, "multipleOrderQuantity");
		int minStockQuantity = Integer.valueOf(minStockQuantityString);
		int minOrderQuantity = Integer.valueOf(minOrderQuantityString);
		int maxOrderQuantity = Integer.valueOf(maxOrderQuantityString);
		int multipleOrderQuantity = Integer.valueOf(
			multipleOrderQuantityString);
		String allowedOrderQuantities = ParamUtil.getString(
			actionRequest, "allowedOrderQuantities");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			CPDefinitionInventory.class.getName(), actionRequest);

		if (cpDefinitionInventoryId <= 0) {
			_cpDefinitionInventoryService.addCPDefinitionInventory(
				cpDefinitionId, cpDefinitionInventoryEngine, lowStockActivity,
				displayAvailability, displayStockQuantity, minStockQuantity,
				backOrders, minOrderQuantity, maxOrderQuantity,
				allowedOrderQuantities, multipleOrderQuantity, serviceContext);
		}
		else {
			_cpDefinitionInventoryService.updateCPDefinitionInventory(
				cpDefinitionInventoryId, cpDefinitionInventoryEngine,
				lowStockActivity, displayAvailability, displayStockQuantity,
				minStockQuantity, backOrders, minOrderQuantity,
				maxOrderQuantity, allowedOrderQuantities, multipleOrderQuantity,
				serviceContext);
		}

		_cpdAvailabilityEstimateService.updateCPDAvailabilityEstimate(
			cpdAvailabilityEstimateEntryId, cpDefinitionId,
			commerceAvailabilityEstimateId, serviceContext);
	}

	@Reference
	private CPDAvailabilityEstimateService _cpdAvailabilityEstimateService;

	@Reference
	private CPDefinitionInventoryService _cpDefinitionInventoryService;

}