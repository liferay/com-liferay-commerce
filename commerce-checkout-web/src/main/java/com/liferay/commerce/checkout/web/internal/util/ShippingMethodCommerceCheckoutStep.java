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

package com.liferay.commerce.checkout.web.internal.util;

import com.liferay.commerce.checkout.web.internal.display.context.ShippingMethodCheckoutStepDisplayContext;
import com.liferay.commerce.constants.CommerceCheckoutWebKeys;
import com.liferay.commerce.constants.CommerceWebKeys;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.currency.util.CommercePriceFormatter;
import com.liferay.commerce.exception.CommerceOrderShippingMethodException;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceShippingEngine;
import com.liferay.commerce.model.CommerceShippingMethod;
import com.liferay.commerce.model.CommerceShippingOption;
import com.liferay.commerce.service.CommerceOrderLocalService;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.commerce.service.CommerceShippingMethodLocalService;
import com.liferay.commerce.util.BaseCommerceCheckoutStep;
import com.liferay.commerce.util.CommerceCheckoutStep;
import com.liferay.commerce.util.CommerceShippingEngineRegistry;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.math.BigDecimal;

import java.util.List;
import java.util.Locale;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Andrea Di Giorgi
 * @author Alessio Antonio Rendina
 * @author Luca Pellizzon
 */
@Component(
	immediate = true,
	property = {
		"commerce.checkout.step.name=" + ShippingMethodCommerceCheckoutStep.NAME,
		"commerce.checkout.step.order:Integer=20"
	},
	service = CommerceCheckoutStep.class
)
public class ShippingMethodCommerceCheckoutStep
	extends BaseCommerceCheckoutStep {

	public static final char COMMERCE_SHIPPING_OPTION_KEY_SEPARATOR =
		CharPool.POUND;

	public static final String NAME = "shipping-method";

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public boolean isActive(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {

		return _commerceCheckoutStepHelper.
			isActiveShippingMethodCommerceCheckoutStep(httpServletRequest);
	}

	@Override
	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			updateCommerceOrderShippingMethod(actionRequest);
		}
		catch (Exception e) {
			if (e instanceof CommerceOrderShippingMethodException) {
				SessionErrors.add(actionRequest, e.getClass());

				return;
			}

			throw e;
		}
	}

	@Override
	public void render(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {

		ShippingMethodCheckoutStepDisplayContext
			shippingMethodCheckoutStepDisplayContext =
				new ShippingMethodCheckoutStepDisplayContext(
					_commercePriceFormatter, _commerceShippingEngineRegistry,
					_commerceShippingMethodLocalService, httpServletRequest);

		CommerceOrder commerceOrder =
			shippingMethodCheckoutStepDisplayContext.getCommerceOrder();

		if (!commerceOrder.isOpen()) {
			httpServletRequest.setAttribute(
				CommerceCheckoutWebKeys.COMMERCE_CHECKOUT_STEP_ORDER_DETAIL_URL,
				_commerceCheckoutStepHelper.getOrderDetailURL(
					httpServletRequest, commerceOrder));

			_jspRenderer.renderJSP(
				httpServletRequest, httpServletResponse, "/error.jsp");
		}
		else {
			httpServletRequest.setAttribute(
				CommerceCheckoutWebKeys.COMMERCE_CHECKOUT_STEP_DISPLAY_CONTEXT,
				shippingMethodCheckoutStepDisplayContext);

			_jspRenderer.renderJSP(
				httpServletRequest, httpServletResponse,
				"/checkout_step/shipping_method.jsp");
		}
	}

	@Override
	public boolean showControls(
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {

		CommerceOrder commerceOrder =
			(CommerceOrder)httpServletRequest.getAttribute(
				CommerceCheckoutWebKeys.COMMERCE_ORDER);

		if (!commerceOrder.isOpen()) {
			return false;
		}

		return super.showControls(httpServletRequest, httpServletResponse);
	}

	protected BigDecimal getShippingAmount(
			CommerceContext commerceContext, CommerceOrder commerceOrder,
			long commerceShippingMethodId, String shippingOptionName,
			Locale locale)
		throws PortalException {

		CommerceShippingMethod commerceShippingMethod =
			_commerceShippingMethodLocalService.getCommerceShippingMethod(
				commerceShippingMethodId);

		if (!commerceShippingMethod.isActive()) {
			throw new CommerceOrderShippingMethodException(
				"Shipping method " +
					commerceShippingMethod.getCommerceShippingMethodId() +
						" is not active");
		}

		CommerceShippingEngine commerceShippingEngine =
			_commerceShippingEngineRegistry.getCommerceShippingEngine(
				commerceShippingMethod.getEngineKey());

		List<CommerceShippingOption> commerceShippingOptions =
			commerceShippingEngine.getCommerceShippingOptions(
				commerceContext, commerceOrder, locale);

		for (CommerceShippingOption commerceShippingOption :
				commerceShippingOptions) {

			if (shippingOptionName.equals(commerceShippingOption.getName())) {
				return commerceShippingOption.getAmount();
			}
		}

		throw new CommerceOrderShippingMethodException(
			StringBundler.concat(
				"Unable to get amount of option \"", shippingOptionName,
				"\" for shipping method ", commerceShippingMethodId));
	}

	@Reference(
		target = "(model.class.name=com.liferay.commerce.model.CommerceOrder)",
		unbind = "-"
	)
	protected void setModelResourcePermission(
		ModelResourcePermission<CommerceOrder> modelResourcePermission) {

		_commerceOrderModelResourcePermission = modelResourcePermission;
	}

	protected void updateCommerceOrderShippingMethod(
			ActionRequest actionRequest)
		throws Exception {

		String commerceShippingOptionKey = ParamUtil.getString(
			actionRequest, "commerceShippingOptionKey");

		if (Validator.isNull(commerceShippingOptionKey)) {
			throw new CommerceOrderShippingMethodException();
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		CommerceContext commerceContext =
			(CommerceContext)actionRequest.getAttribute(
				CommerceWebKeys.COMMERCE_CONTEXT);

		long commerceOrderId = ParamUtil.getLong(
			actionRequest, "commerceOrderId");

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			commerceOrderId);

		PermissionChecker permissionChecker =
			PermissionCheckerFactoryUtil.create(themeDisplay.getUser());

		if (!_commerceOrderModelResourcePermission.contains(
				permissionChecker, commerceOrder, ActionKeys.UPDATE)) {

			return;
		}

		int pos = commerceShippingOptionKey.indexOf(
			COMMERCE_SHIPPING_OPTION_KEY_SEPARATOR);

		long commerceShippingMethodId = GetterUtil.getLong(
			commerceShippingOptionKey.substring(0, pos));
		String shippingOptionName = commerceShippingOptionKey.substring(
			pos + 1);

		BigDecimal shippingAmount = getShippingAmount(
			commerceContext, commerceOrder, commerceShippingMethodId,
			shippingOptionName, themeDisplay.getLocale());

		_commerceOrderLocalService.updateShippingMethod(
			commerceOrder.getCommerceOrderId(), commerceShippingMethodId,
			shippingOptionName, shippingAmount, commerceContext);
	}

	@Reference
	private CommerceCheckoutStepHelper _commerceCheckoutStepHelper;

	@Reference
	private CommerceOrderLocalService _commerceOrderLocalService;

	private ModelResourcePermission<CommerceOrder>
		_commerceOrderModelResourcePermission;

	@Reference
	private CommerceOrderService _commerceOrderService;

	@Reference
	private CommercePriceFormatter _commercePriceFormatter;

	@Reference
	private CommerceShippingEngineRegistry _commerceShippingEngineRegistry;

	@Reference
	private CommerceShippingMethodLocalService
		_commerceShippingMethodLocalService;

	@Reference
	private JSPRenderer _jspRenderer;

}