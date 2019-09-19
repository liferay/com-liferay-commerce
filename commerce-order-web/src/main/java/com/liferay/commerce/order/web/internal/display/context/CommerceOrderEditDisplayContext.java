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

package com.liferay.commerce.order.web.internal.display.context;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.constants.CommerceOrderConstants;
import com.liferay.commerce.currency.model.CommerceMoney;
import com.liferay.commerce.discount.CommerceDiscountValue;
import com.liferay.commerce.frontend.model.HeaderActionModel;
import com.liferay.commerce.frontend.model.StepModel;
import com.liferay.commerce.frontend.model.SummaryElement;
import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.model.CommerceOrderNote;
import com.liferay.commerce.model.CommerceOrderPayment;
import com.liferay.commerce.model.CommerceRegion;
import com.liferay.commerce.model.CommerceShipment;
import com.liferay.commerce.order.web.internal.display.context.util.CommerceOrderRequestHelper;
import com.liferay.commerce.order.web.internal.search.CommerceOrderItemSearch;
import com.liferay.commerce.order.web.internal.search.CommerceOrderItemSearchTerms;
import com.liferay.commerce.order.web.internal.servlet.taglib.ui.CommerceOrderScreenNavigationConstants;
import com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel;
import com.liferay.commerce.payment.service.CommercePaymentMethodGroupRelService;
import com.liferay.commerce.price.CommerceOrderPrice;
import com.liferay.commerce.price.CommerceOrderPriceCalculation;
import com.liferay.commerce.price.CommerceProductPrice;
import com.liferay.commerce.price.CommerceProductPriceCalculation;
import com.liferay.commerce.product.item.selector.criterion.CPInstanceItemSelectorCriterion;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.service.CommerceChannelLocalService;
import com.liferay.commerce.service.CommerceOrderItemService;
import com.liferay.commerce.service.CommerceOrderNoteService;
import com.liferay.commerce.service.CommerceOrderPaymentLocalService;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.commerce.service.CommerceShipmentService;
import com.liferay.commerce.util.CommerceUtil;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.item.selector.ItemSelector;
import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.criteria.UUIDItemSelectorReturnType;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.webserver.WebServerServletTokenUtil;

import java.text.DateFormat;
import java.text.Format;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;

/**
 * @author Andrea Di Giorgi
 * @author Luca Pellizzon
 * @author Fabio Diego Mastrorilli
 * @author Alessio Antonio Rendina
 */
public class CommerceOrderEditDisplayContext {

	public CommerceOrderEditDisplayContext(
			CommerceChannelLocalService commerceChannelLocalService,
			CommerceOrderService commerceOrderService,
			CommerceOrderItemService commerceOrderItemService,
			CommerceOrderNoteService commerceOrderNoteService,
			CommerceOrderPaymentLocalService commerceOrderPaymentLocalService,
			CommercePaymentMethodGroupRelService
				commercePaymentMethodGroupRelService,
			CommerceOrderPriceCalculation commerceOrderPriceCalculation,
			CommerceProductPriceCalculation commerceProductPriceCalculation,
			CommerceShipmentService commerceShipmentService,
			ItemSelector itemSelector, RenderRequest renderRequest)
		throws PortalException {

		_commerceChannelLocalService = commerceChannelLocalService;
		_commerceOrderService = commerceOrderService;
		_commerceOrderItemService = commerceOrderItemService;
		_commerceOrderNoteService = commerceOrderNoteService;
		_commerceOrderPaymentLocalService = commerceOrderPaymentLocalService;
		_commercePaymentMethodGroupRelService =
			commercePaymentMethodGroupRelService;
		_commerceOrderPriceCalculation = commerceOrderPriceCalculation;
		_commerceProductPriceCalculation = commerceProductPriceCalculation;
		_commerceShipmentService = commerceShipmentService;
		_itemSelector = itemSelector;

		long commerceOrderId = ParamUtil.getLong(
			renderRequest, "commerceOrderId");

		if (commerceOrderId > 0) {
			_commerceOrder = _commerceOrderService.getCommerceOrder(
				commerceOrderId);
		}
		else {
			_commerceOrder = null;
		}

		_commerceOrderRequestHelper = new CommerceOrderRequestHelper(
			renderRequest);

		ThemeDisplay themeDisplay =
			_commerceOrderRequestHelper.getThemeDisplay();

		_commerceOrderDateFormatDateTime =
			FastDateFormatFactoryUtil.getDateTime(
				DateFormat.SHORT, DateFormat.SHORT, themeDisplay.getLocale(),
				themeDisplay.getTimeZone());
	}

	public int[] getAvailableOrderStatuses() throws PortalException {
		return _commerceOrderService.getAvailableOrderStatuses(
			getCommerceOrderId());
	}

	public String getCommerceAccountThumbnailURL() throws PortalException {
		if (_commerceOrder == null) {
			return StringPool.BLANK;
		}

		CommerceAccount commerceAccount = _commerceOrder.getCommerceAccount();

		ThemeDisplay themeDisplay =
			_commerceOrderRequestHelper.getThemeDisplay();

		StringBundler sb = new StringBundler(5);

		sb.append(themeDisplay.getPathImage());

		if (commerceAccount.getLogoId() == 0) {
			sb.append("/organization_logo?img_id=0");
		}
		else {
			sb.append("/organization_logo?img_id=");
			sb.append(commerceAccount.getLogoId());
			sb.append("&t=");
			sb.append(
				WebServerServletTokenUtil.getToken(
					commerceAccount.getLogoId()));
		}

		return sb.toString();
	}

	public CommerceOrder getCommerceOrder() {
		return _commerceOrder;
	}

	public String getCommerceOrderDateTime(Date date) {
		if ((_commerceOrder == null) || (date == null)) {
			return StringPool.BLANK;
		}

		return _commerceOrderDateFormatDateTime.format(date);
	}

	public long getCommerceOrderId() {
		if (_commerceOrder == null) {
			return 0;
		}

		return _commerceOrder.getCommerceOrderId();
	}

	public CommerceOrderItem getCommerceOrderItem() throws PortalException {
		if (_commerceOrderItem != null) {
			return _commerceOrderItem;
		}

		long commerceOrderItemId = ParamUtil.getLong(
			_commerceOrderRequestHelper.getRequest(), "commerceOrderItemId");

		if (commerceOrderItemId > 0) {
			_commerceOrderItem = _commerceOrderItemService.getCommerceOrderItem(
				commerceOrderItemId);
		}

		return _commerceOrderItem;
	}

	public PortletURL getCommerceOrderItemsPortletURL() throws PortalException {
		LiferayPortletResponse liferayPortletResponse =
			_commerceOrderRequestHelper.getLiferayPortletResponse();

		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		portletURL.setParameter("mvcRenderCommandName", "editCommerceOrder");
		portletURL.setParameter(
			"commerceOrderId", String.valueOf(getCommerceOrderId()));
		portletURL.setParameter(
			"screenNavigationCategoryKey",
			CommerceOrderScreenNavigationConstants.
				CATEGORY_KEY_COMMERCE_ORDER_SUMMARY);

		String redirect = ParamUtil.getString(
			_commerceOrderRequestHelper.getRequest(), "redirect");

		if (Validator.isNotNull(redirect)) {
			portletURL.setParameter("redirect", redirect);
		}

		return portletURL;
	}

	public SearchContainer<CommerceOrderItem>
			getCommerceOrderItemsSearchContainer()
		throws PortalException {

		if (_itemSearchContainer != null) {
			return _itemSearchContainer;
		}

		_itemSearchContainer = new CommerceOrderItemSearch(
			_commerceOrderRequestHelper.getLiferayPortletRequest(),
			getCommerceOrderItemsPortletURL());

		_itemSearchContainer.setRowChecker(
			new EmptyOnClickRowChecker(
				_commerceOrderRequestHelper.getLiferayPortletResponse()));

		CommerceOrderItemSearchTerms commerceOrderItemSearchTerms =
			(CommerceOrderItemSearchTerms)_itemSearchContainer.getSearchTerms();

		BaseModelSearchResult<CommerceOrderItem> baseModelSearchResult = null;

		Sort sort = SortFactoryUtil.getSort(
			CommerceOrderItem.class, _itemSearchContainer.getOrderByCol(),
			_itemSearchContainer.getOrderByType());

		if (commerceOrderItemSearchTerms.isAdvancedSearch()) {
			baseModelSearchResult = _commerceOrderItemService.search(
				getCommerceOrderId(), commerceOrderItemSearchTerms.getSku(),
				commerceOrderItemSearchTerms.getName(),
				commerceOrderItemSearchTerms.isAndOperator(),
				_itemSearchContainer.getStart(), _itemSearchContainer.getEnd(),
				sort);
		}
		else {
			baseModelSearchResult = _commerceOrderItemService.search(
				getCommerceOrderId(),
				commerceOrderItemSearchTerms.getKeywords(),
				_itemSearchContainer.getStart(), _itemSearchContainer.getEnd(),
				sort);
		}

		_itemSearchContainer.setTotal(baseModelSearchResult.getLength());
		_itemSearchContainer.setResults(baseModelSearchResult.getBaseModels());

		return _itemSearchContainer;
	}

	public List<CommerceOrderNote> getCommerceOrderNotes()
		throws PortalException {

		long commerceOrderId = getCommerceOrderId();

		if (commerceOrderId <= 0) {
			return Collections.emptyList();
		}

		return _commerceOrderNoteService.getCommerceOrderNotes(
			commerceOrderId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	public String getCommerceOrderPaymentMethodName() throws PortalException {
		if ((_commerceOrder == null) ||
			Validator.isNull(_commerceOrder.getCommercePaymentMethodKey())) {

			return StringPool.BLANK;
		}

		CommerceChannel commerceChannel =
			_commerceChannelLocalService.getCommerceChannelByOrderGroupId(
				_commerceOrder.getGroupId());

		CommercePaymentMethodGroupRel commercePaymentMethodGroupRel =
			_commercePaymentMethodGroupRelService.
				getCommercePaymentMethodGroupRel(
					commerceChannel.getSiteGroupId(),
					_commerceOrder.getCommercePaymentMethodKey());

		return commercePaymentMethodGroupRel.getName(
			_commerceOrderRequestHelper.getLocale());
	}

	public PortletURL getCommerceOrderPaymentsPortletURL() {
		LiferayPortletResponse liferayPortletResponse =
			_commerceOrderRequestHelper.getLiferayPortletResponse();

		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		portletURL.setParameter("mvcRenderCommandName", "editCommerceOrder");
		portletURL.setParameter(
			"commerceOrderId", String.valueOf(getCommerceOrderId()));
		portletURL.setParameter(
			"screenNavigationCategoryKey",
			CommerceOrderScreenNavigationConstants.
				CATEGORY_KEY_COMMERCE_ORDER_PAYMENTS);

		String redirect = ParamUtil.getString(
			_commerceOrderRequestHelper.getRequest(), "redirect");

		if (Validator.isNotNull(redirect)) {
			portletURL.setParameter("redirect", redirect);
		}

		return portletURL;
	}

	public SearchContainer<CommerceOrderPayment>
			getCommerceOrderPaymentsSearchContainer()
		throws PortalException {

		if (_paymentSearchContainer != null) {
			return _paymentSearchContainer;
		}

		_paymentSearchContainer = new SearchContainer<>(
			_commerceOrderRequestHelper.getLiferayPortletRequest(),
			getCommerceOrderPaymentsPortletURL(), null,
			"there-are-no-payment-transactions");

		int total =
			_commerceOrderPaymentLocalService.getCommerceOrderPaymentsCount(
				getCommerceOrderId());

		_paymentSearchContainer.setTotal(total);

		List<CommerceOrderPayment> results =
			_commerceOrderPaymentLocalService.getCommerceOrderPayments(
				getCommerceOrderId(), _paymentSearchContainer.getStart(),
				_paymentSearchContainer.getEnd(), null);

		_paymentSearchContainer.setResults(results);

		return _paymentSearchContainer;
	}

	public String getCommercePaymentMethodLabel(
		CommercePaymentMethodGroupRel commercePaymentMethodGroupRel) {

		String label = commercePaymentMethodGroupRel.getName(
			_commerceOrderRequestHelper.getLocale());

		if (!commercePaymentMethodGroupRel.isActive()) {
			StringBundler sb = new StringBundler(4);

			sb.append(label);
			sb.append(" (");
			sb.append(
				LanguageUtil.get(
					_commerceOrderRequestHelper.getRequest(), "inactive"));
			sb.append(CharPool.CLOSE_PARENTHESIS);

			label = sb.toString();
		}

		return label;
	}

	public List<CommercePaymentMethodGroupRel> getCommercePaymentMethods()
		throws PortalException {

		return _commercePaymentMethodGroupRelService.
			getCommercePaymentMethodGroupRels(
				_commerceOrderRequestHelper.getScopeGroupId());
	}

	public CommerceProductPrice getCommerceProductPrice(
			CommerceOrderItem commerceOrderItem)
		throws PortalException {

		return _commerceProductPriceCalculation.getCommerceProductPrice(
			commerceOrderItem.getCPInstanceId(),
			commerceOrderItem.getQuantity(),
			_commerceOrderRequestHelper.getCommerceContext());
	}

	public CommerceShipment getCommerceShipment() throws PortalException {
		if (_commerceShipment != null) {
			return _commerceShipment;
		}

		long commerceShipmentId = ParamUtil.getLong(
			_commerceOrderRequestHelper.getRequest(), "commerceShipmentId");

		if (commerceShipmentId > 0) {
			_commerceShipment = _commerceShipmentService.getCommerceShipment(
				commerceShipmentId);
		}

		return _commerceShipment;
	}

	public PortletURL getCommerceShipmentsPortletURL() {
		LiferayPortletResponse liferayPortletResponse =
			_commerceOrderRequestHelper.getLiferayPortletResponse();

		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		portletURL.setParameter("mvcRenderCommandName", "editCommerceOrder");
		portletURL.setParameter(
			"commerceOrderId", String.valueOf(getCommerceOrderId()));
		portletURL.setParameter(
			"screenNavigationCategoryKey",
			CommerceOrderScreenNavigationConstants.
				CATEGORY_KEY_COMMERCE_ORDER_SHIPMENTS);

		String redirect = ParamUtil.getString(
			_commerceOrderRequestHelper.getRequest(), "redirect");

		if (Validator.isNotNull(redirect)) {
			portletURL.setParameter("redirect", redirect);
		}

		return portletURL;
	}

	public SearchContainer<CommerceShipment>
			getCommerceShipmentsSearchContainer()
		throws PortalException {

		if (_shipmentSearchContainer != null) {
			return _shipmentSearchContainer;
		}

		_shipmentSearchContainer = new SearchContainer<>(
			_commerceOrderRequestHelper.getLiferayPortletRequest(),
			getCommerceShipmentsPortletURL(), null, "there-are-no-shipments");

		_shipmentSearchContainer.setOrderByCol(getOrderByCol());
		_shipmentSearchContainer.setOrderByComparator(
			CommerceUtil.getCommerceShipmentOrderByComparator(
				getOrderByCol(), getOrderByType()));
		_shipmentSearchContainer.setOrderByType(getOrderByType());

		_shipmentSearchContainer.setRowChecker(
			new EmptyOnClickRowChecker(
				_commerceOrderRequestHelper.getLiferayPortletResponse()));

		int total = _commerceShipmentService.getCommerceShipmentsCount(
			_commerceOrderRequestHelper.getCompanyId(),
			_commerceOrder.getShippingAddressId());

		_shipmentSearchContainer.setTotal(total);

		List<CommerceShipment> results =
			_commerceShipmentService.getCommerceShipments(
				_commerceOrderRequestHelper.getCompanyId(),
				_commerceOrder.getShippingAddressId(),
				_shipmentSearchContainer.getStart(),
				_shipmentSearchContainer.getEnd(), null);

		_shipmentSearchContainer.setResults(results);

		return _shipmentSearchContainer;
	}

	public String getDescriptiveCommerceAddress(CommerceAddress commerceAddress)
		throws PortalException {

		if (commerceAddress == null) {
			return StringPool.BLANK;
		}

		CommerceRegion commerceRegion = commerceAddress.getCommerceRegion();

		StringBundler sb = new StringBundler((commerceRegion == null) ? 5 : 7);

		sb.append(commerceAddress.getStreet1());
		sb.append(StringPool.SPACE);
		sb.append(commerceAddress.getCity());
		sb.append(StringPool.NEW_LINE);

		if (commerceRegion != null) {
			sb.append(commerceRegion.getCode());
			sb.append(StringPool.SPACE);
		}

		sb.append(commerceAddress.getZip());

		return sb.toString();
	}

	public List<DropdownItem> getDropdownItems() {
		List<DropdownItem> headerDropdownItems = new ArrayList<>();

		DropdownItem headerDropdownItem = new DropdownItem();

		headerDropdownItem.setLabel("First link");
		headerDropdownItem.setHref("/first-link");
		headerDropdownItem.setIcon("home");

		headerDropdownItems.add(headerDropdownItem);

		DropdownItem headerDropdownItem2 = new DropdownItem();

		headerDropdownItem2.setLabel("Second link");
		headerDropdownItem2.setIcon("blogs");
		headerDropdownItem2.setHref("/second-link");
		headerDropdownItem2.setActive(true);

		headerDropdownItems.add(headerDropdownItem2);

		return headerDropdownItems;
	}

	public List<HeaderActionModel> getHeaderActionModels()
		throws PortalException {

		List<HeaderActionModel> headerActionModels = new ArrayList<>();

		HeaderActionModel headerActionModel1 = new HeaderActionModel();

		headerActionModel1.setLabel("Action 1");
		headerActionModel1.setStyle("secondary");

		headerActionModels.add(headerActionModel1);

		HeaderActionModel headerActionModel2 = new HeaderActionModel();

		headerActionModel2.setLabel("Action 2");

		headerActionModels.add(headerActionModel2);

		return headerActionModels;
	}

	public String getItemSelectorUrl() {
		RequestBackedPortletURLFactory requestBackedPortletURLFactory =
			RequestBackedPortletURLFactoryUtil.create(
				_commerceOrderRequestHelper.getRequest());

		CPInstanceItemSelectorCriterion cpInstanceItemSelectorCriterion =
			new CPInstanceItemSelectorCriterion();

		cpInstanceItemSelectorCriterion.setDesiredItemSelectorReturnTypes(
			Collections.<ItemSelectorReturnType>singletonList(
				new UUIDItemSelectorReturnType()));

		PortletURL itemSelectorURL = _itemSelector.getItemSelectorURL(
			requestBackedPortletURLFactory, "productInstancesSelectItem",
			cpInstanceItemSelectorCriterion);

		return itemSelectorURL.toString();
	}

	public String getOrderByCol() {
		return ParamUtil.getString(
			_commerceOrderRequestHelper.getLiferayPortletRequest(),
			"orderByCol", "create-date");
	}

	public String getOrderByType() {
		return ParamUtil.getString(
			_commerceOrderRequestHelper.getLiferayPortletRequest(),
			"orderByType", "desc");
	}

	public List<StepModel> getOrderSteps() throws PortalException {
		List<StepModel> steps = new ArrayList<>();

		if (_commerceOrder == null) {
			return steps;
		}

		StepModel step1 = new StepModel();
		StepModel step2 = new StepModel();
		StepModel step3 = new StepModel();
		StepModel step4 = new StepModel();
		StepModel step5 = new StepModel();

		step1.setId(
			CommerceOrderConstants.getOrderStatusLabel(
				CommerceOrderConstants.ORDER_STATUS_TO_FULFILL));
		step1.setLabel(
			LanguageUtil.get(
				_commerceOrderRequestHelper.getRequest(),
				CommerceOrderConstants.getOrderStatusLabel(
					CommerceOrderConstants.ORDER_STATUS_TO_FULFILL)));

		step2.setId(
			CommerceOrderConstants.getOrderStatusLabel(
				CommerceOrderConstants.ORDER_STATUS_FULFILLED));
		step2.setLabel(
			LanguageUtil.get(
				_commerceOrderRequestHelper.getRequest(),
				CommerceOrderConstants.getOrderStatusLabel(
					CommerceOrderConstants.ORDER_STATUS_FULFILLED)));

		step3.setId(
			CommerceOrderConstants.getOrderStatusLabel(
				CommerceOrderConstants.ORDER_STATUS_PARTIALLY_SHIPPED));
		step3.setLabel(
			LanguageUtil.get(
				_commerceOrderRequestHelper.getRequest(),
				CommerceOrderConstants.getOrderStatusLabel(
					CommerceOrderConstants.ORDER_STATUS_PARTIALLY_SHIPPED)));

		step4.setId(
			CommerceOrderConstants.getOrderStatusLabel(
				CommerceOrderConstants.ORDER_STATUS_SHIPPED));
		step4.setLabel(
			LanguageUtil.get(
				_commerceOrderRequestHelper.getRequest(),
				CommerceOrderConstants.getOrderStatusLabel(
					CommerceOrderConstants.ORDER_STATUS_SHIPPED)));

		step5.setId(
			CommerceOrderConstants.getOrderStatusLabel(
				CommerceOrderConstants.ORDER_STATUS_COMPLETED));
		step5.setLabel(
			LanguageUtil.get(
				_commerceOrderRequestHelper.getRequest(),
				CommerceOrderConstants.getOrderStatusLabel(
					CommerceOrderConstants.ORDER_STATUS_COMPLETED)));

		int orderStatus = _commerceOrder.getOrderStatus();

		if (orderStatus == CommerceOrderConstants.ORDER_STATUS_COMPLETED) {
			step1.setState("completed");
			step2.setState("completed");
			step3.setState("completed");
			step4.setState("completed");
			step5.setState("completed");
		}
		else if (orderStatus ==
					CommerceOrderConstants.ORDER_STATUS_TO_FULFILL) {

			step1.setState("active");
			step2.setState("inactive");
			step3.setState("inactive");
			step4.setState("inactive");
			step5.setState("inactive");
		}
		else if (orderStatus == CommerceOrderConstants.ORDER_STATUS_FULFILLED) {
			step1.setState("completed");
			step2.setState("active");
			step3.setState("inactive");
			step4.setState("inactive");
			step5.setState("inactive");
		}
		else if (orderStatus ==
					CommerceOrderConstants.ORDER_STATUS_PARTIALLY_SHIPPED) {

			step1.setState("completed");
			step2.setState("completed");
			step3.setState("active");
			step4.setState("inactive");
			step5.setState("inactive");
		}
		else if (orderStatus == CommerceOrderConstants.ORDER_STATUS_SHIPPED) {
			step1.setState("completed");
			step2.setState("completed");
			step3.setState("completed");
			step4.setState("active");
			step5.setState("inactive");
		}

		steps.add(step1);
		steps.add(step2);
		steps.add(step3);
		steps.add(step4);
		steps.add(step5);

		return steps;
	}

	public List<SummaryElement> getSummary() throws PortalException {
		List<SummaryElement> summary = new ArrayList<>();

		if (_commerceOrder == null) {
			return summary;
		}

		SummaryElement itemsSubtotalSummaryElement = new SummaryElement();
		SummaryElement orderDiscountSummaryElement = new SummaryElement();
		SummaryElement promotionCodesSummaryElement = new SummaryElement();
		SummaryElement estimatedTaxSummaryElement = new SummaryElement();
		SummaryElement shippingAndHandingSummaryElement = new SummaryElement();
		SummaryElement grandTotalSummaryElement = new SummaryElement();

		itemsSubtotalSummaryElement.setLabel(
			LanguageUtil.get(
				_commerceOrderRequestHelper.getRequest(), "items-subtotal"));

		CommerceOrderPrice commerceOrderPrice =
			_commerceOrderPriceCalculation.getCommerceOrderPrice(
				_commerceOrder,
				_commerceOrderRequestHelper.getCommerceContext());

		CommerceMoney subtotal = commerceOrderPrice.getSubtotal();

		if (subtotal != null) {
			itemsSubtotalSummaryElement.setValue(
				subtotal.format(_commerceOrderRequestHelper.getLocale()));
		}

		orderDiscountSummaryElement.setLabel(
			LanguageUtil.get(
				_commerceOrderRequestHelper.getRequest(), "order-discount"));

		CommerceDiscountValue totalDiscountValue =
			commerceOrderPrice.getTotalDiscountValue();

		if (totalDiscountValue != null) {
			CommerceMoney discountAmount =
				totalDiscountValue.getDiscountAmount();

			orderDiscountSummaryElement.setValue(
				discountAmount.format(_commerceOrderRequestHelper.getLocale()));
		}

		promotionCodesSummaryElement.setLabel(
			LanguageUtil.get(
				_commerceOrderRequestHelper.getRequest(), "promotion-codes"));
		promotionCodesSummaryElement.setValue(_commerceOrder.getCouponCode());

		estimatedTaxSummaryElement.setLabel(
			LanguageUtil.get(
				_commerceOrderRequestHelper.getRequest(), "estimated-tax"));

		CommerceMoney taxValue = commerceOrderPrice.getTaxValue();

		if (taxValue != null) {
			estimatedTaxSummaryElement.setValue(
				taxValue.format(_commerceOrderRequestHelper.getLocale()));
		}

		shippingAndHandingSummaryElement.setLabel(
			LanguageUtil.get(
				_commerceOrderRequestHelper.getRequest(),
				"shipping-and-handing"));

		CommerceMoney shippingValue = commerceOrderPrice.getShippingValue();

		if (shippingValue != null) {
			shippingAndHandingSummaryElement.setValue(
				shippingValue.format(_commerceOrderRequestHelper.getLocale()));
		}

		grandTotalSummaryElement.setLabel(
			LanguageUtil.get(
				_commerceOrderRequestHelper.getRequest(), "grand-total"));
		grandTotalSummaryElement.setStyle("big");

		CommerceMoney total = commerceOrderPrice.getTotal();

		if (total != null) {
			grandTotalSummaryElement.setValue(
				total.format(_commerceOrderRequestHelper.getLocale()));
		}

		summary.add(itemsSubtotalSummaryElement);
		summary.add(orderDiscountSummaryElement);
		summary.add(promotionCodesSummaryElement);
		summary.add(estimatedTaxSummaryElement);
		summary.add(shippingAndHandingSummaryElement);
		summary.add(grandTotalSummaryElement);

		return summary;
	}

	private final CommerceChannelLocalService _commerceChannelLocalService;
	private final CommerceOrder _commerceOrder;
	private final Format _commerceOrderDateFormatDateTime;
	private CommerceOrderItem _commerceOrderItem;
	private final CommerceOrderItemService _commerceOrderItemService;
	private final CommerceOrderNoteService _commerceOrderNoteService;
	private final CommerceOrderPaymentLocalService
		_commerceOrderPaymentLocalService;
	private final CommerceOrderPriceCalculation _commerceOrderPriceCalculation;
	private final CommerceOrderRequestHelper _commerceOrderRequestHelper;
	private final CommerceOrderService _commerceOrderService;
	private final CommercePaymentMethodGroupRelService
		_commercePaymentMethodGroupRelService;
	private final CommerceProductPriceCalculation
		_commerceProductPriceCalculation;
	private CommerceShipment _commerceShipment;
	private final CommerceShipmentService _commerceShipmentService;
	private SearchContainer<CommerceOrderItem> _itemSearchContainer;
	private final ItemSelector _itemSelector;
	private SearchContainer<CommerceOrderPayment> _paymentSearchContainer;
	private SearchContainer<CommerceShipment> _shipmentSearchContainer;

}