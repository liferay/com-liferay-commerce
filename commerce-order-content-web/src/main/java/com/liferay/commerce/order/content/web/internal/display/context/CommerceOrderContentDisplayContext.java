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

package com.liferay.commerce.order.content.web.internal.display.context;

import com.liferay.commerce.account.constants.CommerceAccountConstants;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.constants.CommerceOrderActionKeys;
import com.liferay.commerce.constants.CommerceOrderConstants;
import com.liferay.commerce.constants.CommerceShipmentConstants;
import com.liferay.commerce.constants.CommerceWebKeys;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.model.CommerceMoney;
import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderNote;
import com.liferay.commerce.model.CommerceShipmentItem;
import com.liferay.commerce.order.content.web.internal.portlet.configuration.CommerceOrderContentPortletInstanceConfiguration;
import com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel;
import com.liferay.commerce.payment.service.CommercePaymentMethodGroupRelService;
import com.liferay.commerce.price.CommerceOrderPrice;
import com.liferay.commerce.price.CommerceOrderPriceCalculation;
import com.liferay.commerce.product.display.context.util.CPRequestHelper;
import com.liferay.commerce.service.CommerceAddressService;
import com.liferay.commerce.service.CommerceOrderLocalService;
import com.liferay.commerce.service.CommerceOrderNoteService;
import com.liferay.commerce.service.CommerceShipmentItemService;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import java.math.BigDecimal;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;

import java.util.Collections;
import java.util.List;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceOrderContentDisplayContext {

	public CommerceOrderContentDisplayContext(
			CommerceAddressService commerceAddressService,
			CommerceOrderLocalService commerceOrderLocalService,
			CommerceOrderNoteService commerceOrderNoteService,
			CommerceOrderPriceCalculation commerceOrderPriceCalculation,
			CommercePaymentMethodGroupRelService
				commercePaymentMethodGroupRelService,
			CommerceShipmentItemService commerceShipmentItemService,
			HttpServletRequest httpServletRequest,
			ModelResourcePermission<CommerceOrder> modelResourcePermission,
			PortletResourcePermission portletResourcePermission)
		throws PortalException {

		_commerceAddressService = commerceAddressService;
		_commerceOrderLocalService = commerceOrderLocalService;
		_commerceOrderNoteService = commerceOrderNoteService;
		_commerceOrderPriceCalculation = commerceOrderPriceCalculation;
		_commercePaymentMethodGroupRelService =
			commercePaymentMethodGroupRelService;
		_commerceShipmentItemService = commerceShipmentItemService;
		_httpServletRequest = httpServletRequest;
		_modelResourcePermission = modelResourcePermission;
		_portletResourcePermission = portletResourcePermission;

		_cpRequestHelper = new CPRequestHelper(httpServletRequest);

		PortletDisplay portletDisplay = _cpRequestHelper.getPortletDisplay();

		_commerceOrderContentPortletInstanceConfiguration =
			portletDisplay.getPortletInstanceConfiguration(
				CommerceOrderContentPortletInstanceConfiguration.class);

		ThemeDisplay themeDisplay = _cpRequestHelper.getThemeDisplay();

		_commerceOrderDateFormatDate = FastDateFormatFactoryUtil.getDate(
			DateFormat.MEDIUM, themeDisplay.getLocale(),
			themeDisplay.getTimeZone());
		_commerceOrderDateFormatTime = FastDateFormatFactoryUtil.getTime(
			DateFormat.MEDIUM, themeDisplay.getLocale(),
			themeDisplay.getTimeZone());

		_commerceContext = (CommerceContext)_httpServletRequest.getAttribute(
			CommerceWebKeys.COMMERCE_CONTEXT);

		_commerceAccount = _commerceContext.getCommerceAccount();

		_commerceOrderNoteId = ParamUtil.getLong(
			_httpServletRequest, "commerceOrderNoteId");
	}

	public List<CommerceAddress> getAvailableCommerceOrderAddresses()
		throws PortalException {

		if (_commerceAccount == null) {
			return Collections.emptyList();
		}

		return _commerceAddressService.getCommerceAddresses(
			_commerceAccount.getCommerceAccountGroupId(),
			CommerceAccount.class.getName(),
			_commerceAccount.getCommerceAccountId());
	}

	public CommerceAccount getCommerceAccount() {
		return _commerceAccount;
	}

	public CommerceOrder getCommerceOrder() {
		return _commerceOrderLocalService.fetchCommerceOrder(
			getCommerceOrderId());
	}

	public String getCommerceOrderDate(CommerceOrder commerceOrder) {
		return _commerceOrderDateFormatDate.format(
			commerceOrder.getCreateDate());
	}

	public long getCommerceOrderId() {
		return ParamUtil.getLong(_httpServletRequest, "commerceOrderId");
	}

	public String getCommerceOrderItemsDetailURL(long commerceOrderId) {
		LiferayPortletResponse liferayPortletResponse =
			_cpRequestHelper.getLiferayPortletResponse();

		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		portletURL.setParameter(
			"mvcRenderCommandName", "viewCommerceOrderItems");
		portletURL.setParameter("redirect", _cpRequestHelper.getCurrentURL());
		portletURL.setParameter(
			"commerceOrderId", String.valueOf(commerceOrderId));

		return portletURL.toString();
	}

	public CommerceOrderNote getCommerceOrderNote() throws PortalException {
		if ((_commerceOrderNote == null) && (_commerceOrderNoteId > 0)) {
			_commerceOrderNote = _commerceOrderNoteService.getCommerceOrderNote(
				_commerceOrderNoteId);
		}

		return _commerceOrderNote;
	}

	public List<CommerceOrderNote> getCommerceOrderNotes(
			CommerceOrder commerceOrder)
		throws PortalException {

		if (hasModelPermission(
				commerceOrder,
				CommerceOrderActionKeys.
					MANAGE_COMMERCE_ORDER_RESTRICTED_NOTES)) {

			return _commerceOrderNoteService.getCommerceOrderNotes(
				commerceOrder.getCommerceOrderId(), QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);
		}

		return _commerceOrderNoteService.getCommerceOrderNotes(
			commerceOrder.getCommerceOrderId(), false);
	}

	public int getCommerceOrderNotesCount(CommerceOrder commerceOrder)
		throws PortalException {

		if (hasModelPermission(commerceOrder, ActionKeys.UPDATE_DISCUSSION)) {
			return _commerceOrderNoteService.getCommerceOrderNotesCount(
				commerceOrder.getCommerceOrderId());
		}

		return _commerceOrderNoteService.getCommerceOrderNotesCount(
			commerceOrder.getCommerceOrderId(), false);
	}

	public String getCommerceOrderPaymentMethodName(CommerceOrder commerceOrder)
		throws PortalException {

		String commercePaymentMethodKey =
			commerceOrder.getCommercePaymentMethodKey();

		if (Validator.isNull(commercePaymentMethodKey)) {
			return StringPool.BLANK;
		}

		CommercePaymentMethodGroupRel commercePaymentMethod =
			_commercePaymentMethodGroupRelService.
				getCommercePaymentMethodGroupRel(
					commerceOrder.getGroupId(), commercePaymentMethodKey);

		if (commercePaymentMethod == null) {
			return StringPool.BLANK;
		}

		String name = commercePaymentMethod.getName(
			_cpRequestHelper.getLocale());

		if (!commercePaymentMethod.isActive()) {
			StringBundler sb = new StringBundler(4);

			sb.append(name);
			sb.append(" (");
			sb.append(
				LanguageUtil.get(_cpRequestHelper.getRequest(), "inactive"));
			sb.append(CharPool.CLOSE_PARENTHESIS);

			name = sb.toString();
		}

		return name;
	}

	public CommerceOrderPrice getCommerceOrderPrice() throws PortalException {
		return _commerceOrderPriceCalculation.getCommerceOrderPrice(
			getCommerceOrder(), _commerceContext);
	}

	public List<CommerceOrder> getCommerceOrders() {
		if (_commerceOrders != null) {
			return _commerceOrders;
		}

		String keywords = ParamUtil.getString(_httpServletRequest, "keywords");

		if (_commerceOrderContentPortletInstanceConfiguration.exclude()) {
			_commerceOrders = _commerceOrderLocalService.getUserCommerceOrders(
				_cpRequestHelper.getScopeGroupId(),
				_cpRequestHelper.getUserId(),
				_commerceOrderContentPortletInstanceConfiguration.orderStatus(),
				_commerceOrderContentPortletInstanceConfiguration.exclude(),
				keywords, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		}
		else {
			_commerceOrders = _commerceOrderLocalService.getUserCommerceOrders(
				_cpRequestHelper.getScopeGroupId(),
				_cpRequestHelper.getUserId(),
				_commerceOrderContentPortletInstanceConfiguration.orderStatus(),
				keywords, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		}

		return _commerceOrders;
	}

	public String getCommerceOrderStatus(CommerceOrder commerceOrder) {
		return LanguageUtil.get(
			_httpServletRequest,
			CommerceOrderConstants.getOrderStatusLabel(
				commerceOrder.getOrderStatus()));
	}

	public String getCommerceOrderTime(CommerceOrder commerceOrder) {
		return _commerceOrderDateFormatTime.format(
			commerceOrder.getCreateDate());
	}

	public String getCommerceOrderTotal(CommerceOrder commerceOrder)
		throws PortalException {

		CommerceMoney total = _commerceOrderPriceCalculation.getTotal(
			commerceOrder, _commerceContext);

		return total.format(_cpRequestHelper.getLocale());
	}

	public List<CommerceShipmentItem> getCommerceShipmentItems(
			long commerceOrderItemId)
		throws PortalException {

		return _commerceShipmentItemService.getCommerceShipmentItems(
			commerceOrderItemId);
	}

	public String getCommerceShipmentStatusLabel(int status) {
		return LanguageUtil.get(
			_cpRequestHelper.getLocale(),
			CommerceShipmentConstants.getShipmentStatusLabel(status));
	}

	public boolean getConfigurationExclude() {
		return _commerceOrderContentPortletInstanceConfiguration.exclude();
	}

	public int getConfigurationOrderStatus() {
		return _commerceOrderContentPortletInstanceConfiguration.orderStatus();
	}

	public String getDisplayStyle() {
		return _commerceOrderContentPortletInstanceConfiguration.displayStyle();
	}

	public long getDisplayStyleGroupId() {
		if (_displayStyleGroupId > 0) {
			return _displayStyleGroupId;
		}

		_displayStyleGroupId =
			_commerceOrderContentPortletInstanceConfiguration.
				displayStyleGroupId();

		if (_displayStyleGroupId <= 0) {
			_displayStyleGroupId = _cpRequestHelper.getScopeGroupId();
		}

		return _displayStyleGroupId;
	}

	public String getFormattedPercentage(BigDecimal percentage)
		throws PortalException {

		CommerceOrder commerceOrder = getCommerceOrder();

		if (commerceOrder == null) {
			return StringPool.BLANK;
		}

		CommerceCurrency commerceCurrency = commerceOrder.getCommerceCurrency();

		DecimalFormat decimalFormat = new DecimalFormat();

		decimalFormat.setMaximumFractionDigits(
			commerceCurrency.getMaxFractionDigits());
		decimalFormat.setMinimumFractionDigits(
			commerceCurrency.getMinFractionDigits());
		decimalFormat.setNegativeSuffix(StringPool.PERCENT);
		decimalFormat.setPositiveSuffix(StringPool.PERCENT);

		return decimalFormat.format(percentage);
	}

	public PortletURL getPortletURL() throws PortalException {
		LiferayPortletResponse liferayPortletResponse =
			_cpRequestHelper.getLiferayPortletResponse();

		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		String delta = ParamUtil.getString(_httpServletRequest, "delta");

		if (Validator.isNotNull(delta)) {
			portletURL.setParameter("delta", delta);
		}

		String deltaEntry = ParamUtil.getString(
			_httpServletRequest, "deltaEntry");

		if (Validator.isNotNull(deltaEntry)) {
			portletURL.setParameter("deltaEntry", deltaEntry);
		}

		return portletURL;
	}

	public boolean hasModelPermission(
			CommerceOrder commerceOrder, String actionId)
		throws PortalException {

		return _modelResourcePermission.contains(
			_cpRequestHelper.getPermissionChecker(), commerceOrder, actionId);
	}

	public boolean hasModelPermission(long commerceOrderId, String actionId)
		throws PortalException {

		return _modelResourcePermission.contains(
			_cpRequestHelper.getPermissionChecker(), commerceOrderId, actionId);
	}

	public boolean hasPermission(String actionId) {
		return _portletResourcePermission.contains(
			_cpRequestHelper.getPermissionChecker(),
			_cpRequestHelper.getScopeGroupId(), actionId);
	}

	public boolean isCommerceSiteTypeB2C() {
		if (_commerceContext.getCommerceSiteType() ==
				CommerceAccountConstants.SITE_TYPE_B2C) {

			return true;
		}

		return false;
	}

	private final CommerceAccount _commerceAccount;
	private final CommerceAddressService _commerceAddressService;
	private final CommerceContext _commerceContext;
	private final CommerceOrderContentPortletInstanceConfiguration
		_commerceOrderContentPortletInstanceConfiguration;
	private final Format _commerceOrderDateFormatDate;
	private final Format _commerceOrderDateFormatTime;
	private final CommerceOrderLocalService _commerceOrderLocalService;
	private CommerceOrderNote _commerceOrderNote;
	private final long _commerceOrderNoteId;
	private final CommerceOrderNoteService _commerceOrderNoteService;
	private final CommerceOrderPriceCalculation _commerceOrderPriceCalculation;
	private List<CommerceOrder> _commerceOrders;
	private final CommercePaymentMethodGroupRelService
		_commercePaymentMethodGroupRelService;
	private final CommerceShipmentItemService _commerceShipmentItemService;
	private final CPRequestHelper _cpRequestHelper;
	private long _displayStyleGroupId;
	private final HttpServletRequest _httpServletRequest;
	private final ModelResourcePermission<CommerceOrder>
		_modelResourcePermission;
	private final PortletResourcePermission _portletResourcePermission;

}