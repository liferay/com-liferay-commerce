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

package com.liferay.commerce.price.list.web.internal.portlet.action;

import com.liferay.commerce.price.list.constants.CommercePriceListPortletKeys;
import com.liferay.commerce.price.list.exception.CommercePriceListCurrencyException;
import com.liferay.commerce.price.list.exception.CommercePriceListParentPriceListGroupIdException;
import com.liferay.commerce.price.list.exception.NoSuchPriceListException;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.model.CommercePriceListAccountRel;
import com.liferay.commerce.price.list.model.CommercePriceListCommerceAccountGroupRel;
import com.liferay.commerce.price.list.service.CommercePriceListAccountRelService;
import com.liferay.commerce.price.list.service.CommercePriceListCommerceAccountGroupRelService;
import com.liferay.commerce.price.list.service.CommercePriceListService;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.service.CommerceCatalogService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Calendar;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + CommercePriceListPortletKeys.COMMERCE_PRICE_LIST,
		"mvc.command.name=editCommercePriceList"
	},
	service = MVCActionCommand.class
)
public class EditCommercePriceListMVCActionCommand
	extends BaseMVCActionCommand {

	protected void deleteCommercePriceLists(ActionRequest actionRequest)
		throws Exception {

		long[] deleteCommercePriceListIds = null;

		long commercePriceListId = ParamUtil.getLong(
			actionRequest, "commercePriceListId");

		if (commercePriceListId > 0) {
			deleteCommercePriceListIds = new long[] {commercePriceListId};
		}
		else {
			deleteCommercePriceListIds = StringUtil.split(
				ParamUtil.getString(
					actionRequest, "deleteCommercePriceListIds"),
				0L);
		}

		for (long deleteCommercePriceListId : deleteCommercePriceListIds) {
			_commercePriceListService.deleteCommercePriceList(
				deleteCommercePriceListId);
		}
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			if (cmd.equals(Constants.ADD) || cmd.equals(Constants.UPDATE)) {
				CommercePriceList commercePriceList = updateCommercePriceList(
					actionRequest);

				String redirect = getSaveAndContinueRedirect(
					actionRequest, commercePriceList);

				sendRedirect(actionRequest, actionResponse, redirect);
			}
			else if (cmd.equals(Constants.DELETE)) {
				deleteCommercePriceLists(actionRequest);
			}
		}
		catch (Exception e) {
			if (e instanceof NoSuchPriceListException ||
				e instanceof PrincipalException) {

				SessionErrors.add(actionRequest, e.getClass());

				actionResponse.setRenderParameter("mvcPath", "/error.jsp");
			}
			else if (e instanceof CommercePriceListCurrencyException ||
					 e instanceof
						 CommercePriceListParentPriceListGroupIdException) {

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

	protected String getSaveAndContinueRedirect(
			ActionRequest actionRequest, CommercePriceList commercePriceList)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PortletURL portletURL = PortletProviderUtil.getPortletURL(
			actionRequest, themeDisplay.getScopeGroup(),
			CommercePriceList.class.getName(), PortletProvider.Action.EDIT);

		portletURL.setParameter(
			"mvcRenderCommandName", "editCommercePriceList");
		portletURL.setParameter(
			"commercePriceListId",
			String.valueOf(commercePriceList.getCommercePriceListId()));

		return portletURL.toString();
	}

	protected CommercePriceList updateCommercePriceList(
			ActionRequest actionRequest)
		throws Exception {

		long commercePriceListId = ParamUtil.getLong(
			actionRequest, "commercePriceListId");

		long parentCommercePriceListId = ParamUtil.getLong(
			actionRequest, "parentCommercePriceListId");

		long commerceCurrencyId = ParamUtil.getLong(
			actionRequest, "commerceCurrencyId");

		String name = ParamUtil.getString(actionRequest, "name");
		double priority = ParamUtil.getDouble(actionRequest, "priority");

		int displayDateMonth = ParamUtil.getInteger(
			actionRequest, "displayDateMonth");
		int displayDateDay = ParamUtil.getInteger(
			actionRequest, "displayDateDay");
		int displayDateYear = ParamUtil.getInteger(
			actionRequest, "displayDateYear");
		int displayDateHour = ParamUtil.getInteger(
			actionRequest, "displayDateHour");
		int displayDateMinute = ParamUtil.getInteger(
			actionRequest, "displayDateMinute");
		int displayDateAmPm = ParamUtil.getInteger(
			actionRequest, "displayDateAmPm");

		if (displayDateAmPm == Calendar.PM) {
			displayDateHour += 12;
		}

		int expirationDateMonth = ParamUtil.getInteger(
			actionRequest, "expirationDateMonth");
		int expirationDateDay = ParamUtil.getInteger(
			actionRequest, "expirationDateDay");
		int expirationDateYear = ParamUtil.getInteger(
			actionRequest, "expirationDateYear");
		int expirationDateHour = ParamUtil.getInteger(
			actionRequest, "expirationDateHour");
		int expirationDateMinute = ParamUtil.getInteger(
			actionRequest, "expirationDateMinute");
		int expirationDateAmPm = ParamUtil.getInteger(
			actionRequest, "expirationDateAmPm");

		if (expirationDateAmPm == Calendar.PM) {
			expirationDateHour += 12;
		}

		boolean neverExpire = ParamUtil.getBoolean(
			actionRequest, "neverExpire");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			CommercePriceList.class.getName(), actionRequest);

		CommercePriceList commercePriceList;

		if (commercePriceListId <= 0) {

			// Commerce catalog

			long commerceCatalogId = ParamUtil.getLong(
				actionRequest, "commerceCatalogId");

			CommerceCatalog commerceCatalog =
				_commerceCatalogService.fetchCommerceCatalog(commerceCatalogId);

			if (commerceCatalog == null) {
				List<CommerceCatalog> commerceCatalogs =
					_commerceCatalogService.getCommerceCatalogs(
						_portal.getCompanyId(actionRequest), true);

				commerceCatalog = commerceCatalogs.get(0);
			}

			// Commerce price list

			commercePriceList = _commercePriceListService.addCommercePriceList(
				commerceCatalog.getCommerceCatalogGroupId(),
				serviceContext.getUserId(), commerceCurrencyId,
				parentCommercePriceListId, name, priority, displayDateMonth,
				displayDateDay, displayDateYear, displayDateHour,
				displayDateMinute, expirationDateMonth, expirationDateDay,
				expirationDateYear, expirationDateHour, expirationDateMinute,
				neverExpire, serviceContext);
		}
		else {
			commercePriceList =
				_commercePriceListService.updateCommercePriceList(
					commercePriceListId, commerceCurrencyId,
					parentCommercePriceListId, name, priority, displayDateMonth,
					displayDateDay, displayDateYear, displayDateHour,
					displayDateMinute, expirationDateMonth, expirationDateDay,
					expirationDateYear, expirationDateHour,
					expirationDateMinute, neverExpire, serviceContext);
		}

		if (commercePriceList != null) {
			updateCommercePriceListAccountRels(
				actionRequest, commercePriceList);
			updateCommercePriceListCommerceAccountGroupRels(
				actionRequest, commercePriceList);
		}

		return commercePriceList;
	}

	protected void updateCommercePriceListAccountRels(
			ActionRequest actionRequest, CommercePriceList commercePriceList)
		throws PortalException {

		long[] deleteCommercePriceListAccountRelIds = ParamUtil.getLongValues(
			actionRequest, "deleteCommercePriceListAccountRelIds");

		if (deleteCommercePriceListAccountRelIds.length > 0) {
			for (long deleteCommercePriceListAccountRelId :
					deleteCommercePriceListAccountRelIds) {

				_commercePriceListAccountRelService.
					deleteCommercePriceListAccountRel(
						deleteCommercePriceListAccountRelId);
			}
		}

		long[] addCommerceAccountIds = ParamUtil.getLongValues(
			actionRequest, "addCommerceAccountIds");

		if (addCommerceAccountIds.length > 0) {
			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				CommercePriceListAccountRel.class.getName(), actionRequest);

			for (long addCommerceAccountId : addCommerceAccountIds) {
				CommercePriceListAccountRel commercePriceListAccountRel =
					_commercePriceListAccountRelService.
						fetchCommercePriceListAccountRel(
							commercePriceList.getCommercePriceListId(),
							addCommerceAccountId);

				if (commercePriceListAccountRel == null) {
					_commercePriceListAccountRelService.
						addCommercePriceListAccountRel(
							commercePriceList.getCommercePriceListId(),
							addCommerceAccountId, 0, serviceContext);
				}
			}
		}
	}

	protected void updateCommercePriceListCommerceAccountGroupRels(
			ActionRequest actionRequest, CommercePriceList commercePriceList)
		throws PortalException {

		long[] addcommerceAccountGroupIds = ParamUtil.getLongValues(
			actionRequest, "addcommerceAccountGroupIds");

		long[] deleteCommercePriceListCommerceAccountGroupRelIds =
			ParamUtil.getLongValues(
				actionRequest,
				"deleteCommercePriceListCommerceAccountGroupRelIds");

		if (deleteCommercePriceListCommerceAccountGroupRelIds.length > 0) {
			for (long deleteCommercePriceListCommerceAccountGroupRelId :
					deleteCommercePriceListCommerceAccountGroupRelIds) {

				_commercePriceListCommerceAccountGroupRelService.
					deleteCommercePriceListCommerceAccountGroupRel(
						deleteCommercePriceListCommerceAccountGroupRelId);
			}
		}

		if (addcommerceAccountGroupIds.length > 0) {
			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				CommercePriceListCommerceAccountGroupRel.class.getName(),
				actionRequest);

			for (long addcommerceAccountGroupId : addcommerceAccountGroupIds) {
				CommercePriceListCommerceAccountGroupRel
					commercePriceListAccountGroupEntryRel =
						_commercePriceListCommerceAccountGroupRelService.
							fetchCommercePriceListCommerceAccountGroupRel(
								commercePriceList.getCommercePriceListId(),
								addcommerceAccountGroupId);

				if (commercePriceListAccountGroupEntryRel == null) {
					_commercePriceListCommerceAccountGroupRelService.
						addCommercePriceListCommerceAccountGroupRel(
							commercePriceList.getCommercePriceListId(),
							addcommerceAccountGroupId, 0, serviceContext);
				}
			}
		}
	}

	@Reference
	private CommerceCatalogService _commerceCatalogService;

	@Reference
	private CommercePriceListAccountRelService
		_commercePriceListAccountRelService;

	@Reference
	private CommercePriceListCommerceAccountGroupRelService
		_commercePriceListCommerceAccountGroupRelService;

	@Reference
	private CommercePriceListService _commercePriceListService;

	@Reference
	private Portal _portal;

}