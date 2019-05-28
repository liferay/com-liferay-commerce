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

package com.liferay.commerce.discount.web.internal.portlet.action;

import com.liferay.commerce.discount.constants.CommerceDiscountPortletKeys;
import com.liferay.commerce.discount.exception.CommerceDiscountCouponCodeException;
import com.liferay.commerce.discount.exception.NoSuchDiscountException;
import com.liferay.commerce.discount.model.CommerceDiscount;
import com.liferay.commerce.discount.model.CommerceDiscountCommerceAccountGroupRel;
import com.liferay.commerce.discount.model.CommerceDiscountConstants;
import com.liferay.commerce.discount.service.CommerceDiscountCommerceAccountGroupRelService;
import com.liferay.commerce.discount.service.CommerceDiscountService;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.service.CommerceCatalogService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.TransactionConfig;
import com.liferay.portal.kernel.transaction.TransactionInvokerUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;

import java.math.BigDecimal;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Callable;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + CommerceDiscountPortletKeys.COMMERCE_DISCOUNT,
		"mvc.command.name=editCommerceDiscount"
	},
	service = MVCActionCommand.class
)
public class EditCommerceDiscountMVCActionCommand extends BaseMVCActionCommand {

	protected void deleteCommerceDiscounts(ActionRequest actionRequest)
		throws PortalException {

		long[] deleteCommerceDiscountIds = null;

		long commerceDiscountId = ParamUtil.getLong(
			actionRequest, "commerceDiscountId");

		if (commerceDiscountId > 0) {
			deleteCommerceDiscountIds = new long[] {commerceDiscountId};
		}
		else {
			deleteCommerceDiscountIds = StringUtil.split(
				ParamUtil.getString(actionRequest, "deleteCommerceDiscountIds"),
				0L);
		}

		for (long deleteCommerceDiscountId : deleteCommerceDiscountIds) {
			_commerceDiscountService.deleteCommerceDiscount(
				deleteCommerceDiscountId);
		}
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			if (cmd.equals(Constants.ADD) || cmd.equals(Constants.UPDATE)) {
				Callable<CommerceDiscount> commerceDiscountCallable =
					new CommerceDiscountCallable(actionRequest);

				CommerceDiscount commerceDiscount =
					TransactionInvokerUtil.invoke(
						_transactionConfig, commerceDiscountCallable);

				String redirect = getSaveAndContinueRedirect(
					actionRequest, commerceDiscount);

				sendRedirect(actionRequest, actionResponse, redirect);
			}
			else if (cmd.equals(Constants.DELETE)) {
				deleteCommerceDiscounts(actionRequest);
			}
		}
		catch (Throwable t) {
			if (t instanceof CommerceDiscountCouponCodeException) {
				hideDefaultErrorMessage(actionRequest);

				SessionErrors.add(actionRequest, t.getClass());

				actionResponse.setRenderParameter(
					"mvcRenderCommandName", "editCommerceDiscount");
			}
			else if (t instanceof NoSuchDiscountException ||
					 t instanceof PrincipalException) {

				SessionErrors.add(actionRequest, t.getClass());

				actionResponse.setRenderParameter("mvcPath", "/error.jsp");
			}
			else {
				_log.error(t, t);
			}
		}
	}

	protected String getSaveAndContinueRedirect(
		ActionRequest actionRequest, CommerceDiscount commerceDiscount) {

		PortletURL portletURL = _portal.getControlPanelPortletURL(
			actionRequest, CommerceDiscountPortletKeys.COMMERCE_DISCOUNT,
			PortletRequest.RENDER_PHASE);

		if (commerceDiscount != null) {
			portletURL.setParameter(
				"mvcRenderCommandName", "editCommerceDiscount");
			portletURL.setParameter(
				"commerceDiscountId",
				String.valueOf(commerceDiscount.getCommerceDiscountId()));

			String redirect = ParamUtil.getString(actionRequest, "redirect");

			portletURL.setParameter("redirect", redirect);
		}

		return portletURL.toString();
	}

	protected CommerceDiscount updateCommerceDiscount(
			ActionRequest actionRequest)
		throws Exception {

		long commerceDiscountId = ParamUtil.getLong(
			actionRequest, "commerceDiscountId");

		String title = ParamUtil.getString(actionRequest, "title");
		String target = ParamUtil.getString(actionRequest, "target");
		boolean useCouponCode = ParamUtil.getBoolean(
			actionRequest, "useCouponCode");
		String couponCode = ParamUtil.getString(actionRequest, "couponCode");
		boolean usePercentage = ParamUtil.getBoolean(
			actionRequest, "usePercentage");
		BigDecimal maximumDiscountAmount = (BigDecimal)ParamUtil.getNumber(
			actionRequest, "maximumDiscountAmount", BigDecimal.ZERO);
		BigDecimal level1 = (BigDecimal)ParamUtil.getNumber(
			actionRequest, "level1", BigDecimal.ZERO);
		BigDecimal level2 = (BigDecimal)ParamUtil.getNumber(
			actionRequest, "level2", BigDecimal.ZERO);
		BigDecimal level3 = (BigDecimal)ParamUtil.getNumber(
			actionRequest, "level3", BigDecimal.ZERO);
		BigDecimal level4 = (BigDecimal)ParamUtil.getNumber(
			actionRequest, "level4", BigDecimal.ZERO);
		boolean active = ParamUtil.getBoolean(actionRequest, "active");

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
			CommerceDiscount.class.getName(), actionRequest);

		CommerceDiscount commerceDiscount = null;

		if (commerceDiscountId <= 0) {

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

			// Commerce discount

			commerceDiscount = _commerceDiscountService.addCommerceDiscount(
				commerceCatalog.getCommerceCatalogGroupId(),
				serviceContext.getUserId(), title, target, useCouponCode,
				couponCode, usePercentage, maximumDiscountAmount, level1,
				level2, level3, level4,
				CommerceDiscountConstants.LIMITATION_TYPE_UNLIMITED, 0, active,
				displayDateMonth, displayDateDay, displayDateYear,
				displayDateHour, displayDateMinute, expirationDateMonth,
				expirationDateDay, expirationDateYear, expirationDateHour,
				expirationDateMinute, neverExpire, serviceContext);
		}
		else {
			commerceDiscount = _commerceDiscountService.updateCommerceDiscount(
				commerceDiscountId, title, target, useCouponCode, couponCode,
				usePercentage, maximumDiscountAmount, level1, level2, level3,
				level4, CommerceDiscountConstants.LIMITATION_TYPE_UNLIMITED, 0,
				active, displayDateMonth, displayDateDay, displayDateYear,
				displayDateHour, displayDateMinute, expirationDateMonth,
				expirationDateDay, expirationDateYear, expirationDateHour,
				expirationDateMinute, neverExpire, serviceContext);
		}

		if (commerceDiscount != null) {
			updatecommerceDiscountCommerceAccountGroupRels(
				actionRequest, commerceDiscount);
		}

		return commerceDiscount;
	}

	protected void updatecommerceDiscountCommerceAccountGroupRels(
			ActionRequest actionRequest, CommerceDiscount commerceDiscount)
		throws PortalException {

		long[] addcommerceAccountGroupIds = ParamUtil.getLongValues(
			actionRequest, "addcommerceAccountGroupIds");

		long[] deletecommerceDiscountCommerceAccountGroupRelIds =
			ParamUtil.getLongValues(
				actionRequest,
				"deletecommerceDiscountCommerceAccountGroupRelIds");

		if (deletecommerceDiscountCommerceAccountGroupRelIds.length > 0) {
			for (long deletecommerceDiscountCommerceAccountGroupRelId :
					deletecommerceDiscountCommerceAccountGroupRelIds) {

				_commerceDiscountCommerceAccountGroupRelService.
					deleteCommerceDiscountCommerceAccountGroupRel(
						deletecommerceDiscountCommerceAccountGroupRelId);
			}
		}

		if (addcommerceAccountGroupIds.length > 0) {
			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				CommerceDiscountCommerceAccountGroupRel.class.getName(),
				actionRequest);

			for (long addcommerceAccountGroupId : addcommerceAccountGroupIds) {
				_commerceDiscountCommerceAccountGroupRelService.
					addCommerceDiscountCommerceAccountGroupRel(
						commerceDiscount.getCommerceDiscountId(),
						addcommerceAccountGroupId, serviceContext);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EditCommerceDiscountMVCActionCommand.class);

	private static final TransactionConfig _transactionConfig =
		TransactionConfig.Factory.create(
			Propagation.REQUIRED, new Class<?>[] {Exception.class});

	@Reference
	private CommerceCatalogService _commerceCatalogService;

	@Reference
	private CommerceDiscountCommerceAccountGroupRelService
		_commerceDiscountCommerceAccountGroupRelService;

	@Reference
	private CommerceDiscountService _commerceDiscountService;

	@Reference
	private Portal _portal;

	private class CommerceDiscountCallable
		implements Callable<CommerceDiscount> {

		@Override
		public CommerceDiscount call() throws Exception {
			return updateCommerceDiscount(_actionRequest);
		}

		private CommerceDiscountCallable(ActionRequest actionRequest) {
			_actionRequest = actionRequest;
		}

		private final ActionRequest _actionRequest;

	}

}