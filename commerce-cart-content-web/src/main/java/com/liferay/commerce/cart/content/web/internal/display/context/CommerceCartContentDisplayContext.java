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

package com.liferay.commerce.cart.content.web.internal.display.context;

import com.liferay.commerce.account.constants.CommerceAccountConstants;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.cart.content.web.internal.display.context.util.CommerceCartContentRequestHelper;
import com.liferay.commerce.cart.content.web.internal.portlet.configuration.CommerceCartContentPortletInstanceConfiguration;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.order.CommerceOrderValidatorRegistry;
import com.liferay.commerce.order.CommerceOrderValidatorResult;
import com.liferay.commerce.price.CommerceOrderPrice;
import com.liferay.commerce.price.CommerceOrderPriceCalculation;
import com.liferay.commerce.price.CommerceProductPrice;
import com.liferay.commerce.price.CommerceProductPriceCalculation;
import com.liferay.commerce.product.constants.CPActionKeys;
import com.liferay.commerce.product.util.CPDefinitionHelper;
import com.liferay.commerce.product.util.CPInstanceHelper;
import com.liferay.commerce.service.CommerceOrderItemService;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.KeyValuePair;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
public class CommerceCartContentDisplayContext {

	public CommerceCartContentDisplayContext(
			HttpServletRequest httpServletRequest,
			CommerceOrderItemService commerceOrderItemService,
			CommerceOrderPriceCalculation commerceOrderPriceCalculation,
			CommerceOrderValidatorRegistry commerceOrderValidatorRegistry,
			CommerceProductPriceCalculation commerceProductPriceCalculation,
			CPDefinitionHelper cpDefinitionHelper,
			CPInstanceHelper cpInstanceHelper,
			ModelResourcePermission<CommerceOrder>
				commerceOrderModelResourcePermission,
			PortletResourcePermission commerceProductPortletResourcePermission)
		throws PortalException {

		_commerceOrderItemService = commerceOrderItemService;
		_commerceOrderPriceCalculation = commerceOrderPriceCalculation;
		_commerceOrderValidatorRegistry = commerceOrderValidatorRegistry;
		_commerceProductPriceCalculation = commerceProductPriceCalculation;
		_commerceProductPortletResourcePermission =
			commerceProductPortletResourcePermission;

		this.cpDefinitionHelper = cpDefinitionHelper;
		this.cpInstanceHelper = cpInstanceHelper;
		this.commerceOrderModelResourcePermission =
			commerceOrderModelResourcePermission;

		commerceCartContentRequestHelper = new CommerceCartContentRequestHelper(
			httpServletRequest);

		commerceContext = commerceCartContentRequestHelper.getCommerceContext();

		PortletDisplay portletDisplay =
			commerceCartContentRequestHelper.getPortletDisplay();

		_commerceCartContentPortletInstanceConfiguration =
			portletDisplay.getPortletInstanceConfiguration(
				CommerceCartContentPortletInstanceConfiguration.class);
	}

	public CommerceOrder getCommerceOrder() throws PortalException {
		if (_commerceOrder != null) {
			return _commerceOrder;
		}

		_commerceOrder = commerceContext.getCommerceOrder();

		return _commerceOrder;
	}

	public long getCommerceOrderId() throws PortalException {
		CommerceOrder commerceOrder = getCommerceOrder();

		if (commerceOrder == null) {
			return 0;
		}

		return commerceOrder.getCommerceOrderId();
	}

	public String getCommerceOrderItemThumbnailSrc(
			CommerceOrderItem commerceOrderItem)
		throws Exception {

		return cpInstanceHelper.getCPInstanceThumbnailSrc(
			commerceOrderItem.getCPInstanceId());
	}

	public CommerceOrderPrice getCommerceOrderPrice() throws PortalException {
		return _commerceOrderPriceCalculation.getCommerceOrderPrice(
			getCommerceOrder(), commerceContext);
	}

	public Map<Long, List<CommerceOrderValidatorResult>>
			getCommerceOrderValidatorResults()
		throws PortalException {

		return _commerceOrderValidatorRegistry.getCommerceOrderValidatorResults(
			commerceCartContentRequestHelper.getLocale(), getCommerceOrder());
	}

	public CommerceProductPrice getCommerceProductPrice(
			CommerceOrderItem commerceOrderItem)
		throws PortalException {

		return _commerceProductPriceCalculation.getCommerceProductPrice(
			commerceOrderItem.getCPInstanceId(),
			commerceOrderItem.getQuantity(), commerceContext);
	}

	public String getCPDefinitionURL(
			long cpDefinitionId, ThemeDisplay themeDisplay)
		throws PortalException {

		return cpDefinitionHelper.getFriendlyURL(cpDefinitionId, themeDisplay);
	}

	public String getDeleteURL(CommerceOrderItem commerceOrderItem) {
		LiferayPortletResponse liferayPortletResponse =
			commerceCartContentRequestHelper.getLiferayPortletResponse();

		PortletURL portletURL = liferayPortletResponse.createActionURL();

		portletURL.setParameter(
			ActionRequest.ACTION_NAME, "editCommerceOrderItem");
		portletURL.setParameter(Constants.CMD, Constants.DELETE);
		portletURL.setParameter(
			"redirect", commerceCartContentRequestHelper.getCurrentURL());
		portletURL.setParameter(
			"commerceOrderItemId",
			String.valueOf(commerceOrderItem.getCommerceOrderItemId()));

		return portletURL.toString();
	}

	public String getDisplayStyle() {
		return _commerceCartContentPortletInstanceConfiguration.displayStyle();
	}

	public long getDisplayStyleGroupId() {
		if (_displayStyleGroupId > 0) {
			return _displayStyleGroupId;
		}

		_displayStyleGroupId =
			_commerceCartContentPortletInstanceConfiguration.
				displayStyleGroupId();

		if (_displayStyleGroupId <= 0) {
			_displayStyleGroupId =
				commerceCartContentRequestHelper.getScopeGroupId();
		}

		return _displayStyleGroupId;
	}

	public List<KeyValuePair> getKeyValuePairs(
			long cpDefinitionId, String json, Locale locale)
		throws PortalException {

		return cpInstanceHelper.getKeyValuePairs(cpDefinitionId, json, locale);
	}

	public PortletURL getPortletURL() throws PortalException {
		LiferayPortletResponse liferayPortletResponse =
			commerceCartContentRequestHelper.getLiferayPortletResponse();

		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		String delta = ParamUtil.getString(
			commerceCartContentRequestHelper.getRequest(), "delta");

		if (Validator.isNotNull(delta)) {
			portletURL.setParameter("delta", delta);
		}

		String deltaEntry = ParamUtil.getString(
			commerceCartContentRequestHelper.getRequest(), "deltaEntry");

		if (Validator.isNotNull(deltaEntry)) {
			portletURL.setParameter("deltaEntry", deltaEntry);
		}

		return portletURL;
	}

	public SearchContainer<CommerceOrderItem> getSearchContainer()
		throws PortalException {

		if (_searchContainer != null) {
			return _searchContainer;
		}

		_searchContainer = new SearchContainer<>(
			commerceCartContentRequestHelper.getLiferayPortletRequest(),
			getPortletURL(), null, null);

		_searchContainer.setEmptyResultsMessage("no-items-were-found");

		long commerceOrderId = getCommerceOrderId();

		if (commerceOrderId == 0) {
			return _searchContainer;
		}

		int total = _commerceOrderItemService.getCommerceOrderItemsCount(
			commerceOrderId);

		_searchContainer.setTotal(total);

		List<CommerceOrderItem> results =
			_commerceOrderItemService.getCommerceOrderItems(
				commerceOrderId, _searchContainer.getStart(),
				_searchContainer.getEnd());

		_searchContainer.setResults(results);

		return _searchContainer;
	}

	public boolean hasPermission(String actionId) throws PortalException {
		if (_commerceOrder == null) {
			return false;
		}

		return commerceOrderModelResourcePermission.contains(
			commerceCartContentRequestHelper.getPermissionChecker(),
			_commerceOrder, actionId);
	}

	public boolean hasViewPricePermission() throws PortalException {
		CommerceAccount commerceAccount = commerceContext.getCommerceAccount();

		if ((commerceAccount != null) &&
			(commerceAccount.getType() ==
				CommerceAccountConstants.ACCOUNT_TYPE_BUSINESS)) {

			return _commerceProductPortletResourcePermission.contains(
				commerceCartContentRequestHelper.getPermissionChecker(),
				commerceAccount.getCommerceAccountGroupId(),
				CPActionKeys.VIEW_PRICE);
		}

		return _commerceProductPortletResourcePermission.contains(
			commerceCartContentRequestHelper.getPermissionChecker(),
			commerceContext.getSiteGroupId(), CPActionKeys.VIEW_PRICE);
	}

	public boolean isValidCommerceOrder() throws PortalException {
		CommerceOrder commerceOrder = getCommerceOrder();

		if (commerceOrder == null) {
			return false;
		}

		List<CommerceOrderItem> commerceOrderItems =
			commerceOrder.getCommerceOrderItems();

		if (commerceOrderItems.isEmpty()) {
			return false;
		}

		return _commerceOrderValidatorRegistry.isValid(
			commerceCartContentRequestHelper.getLocale(), commerceOrder);
	}

	public List<CommerceOrderValidatorResult> validateCommerceOrderItem(
			long commerceOrderItemId)
		throws PortalException {

		CommerceOrderItem commerceOrderItem =
			_commerceOrderItemService.fetchCommerceOrderItem(
				commerceOrderItemId);

		return _commerceOrderValidatorRegistry.validate(
			commerceCartContentRequestHelper.getLocale(), commerceOrderItem);
	}

	protected final CommerceCartContentRequestHelper
		commerceCartContentRequestHelper;
	protected final CommerceContext commerceContext;
	protected final ModelResourcePermission<CommerceOrder>
		commerceOrderModelResourcePermission;
	protected final CPDefinitionHelper cpDefinitionHelper;
	protected final CPInstanceHelper cpInstanceHelper;

	private final CommerceCartContentPortletInstanceConfiguration
		_commerceCartContentPortletInstanceConfiguration;
	private CommerceOrder _commerceOrder;
	private final CommerceOrderItemService _commerceOrderItemService;
	private final CommerceOrderPriceCalculation _commerceOrderPriceCalculation;
	private final CommerceOrderValidatorRegistry
		_commerceOrderValidatorRegistry;
	private final PortletResourcePermission
		_commerceProductPortletResourcePermission;
	private final CommerceProductPriceCalculation
		_commerceProductPriceCalculation;
	private long _displayStyleGroupId;
	private SearchContainer<CommerceOrderItem> _searchContainer;

}