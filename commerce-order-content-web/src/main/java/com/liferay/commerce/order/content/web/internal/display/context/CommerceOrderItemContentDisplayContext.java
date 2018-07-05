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

import com.liferay.commerce.constants.CommerceWebKeys;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.order.CommerceOrderValidatorRegistry;
import com.liferay.commerce.order.CommerceOrderValidatorResult;
import com.liferay.commerce.price.CommerceOrderPrice;
import com.liferay.commerce.price.CommerceOrderPriceCalculation;
import com.liferay.commerce.price.CommerceProductPrice;
import com.liferay.commerce.price.CommerceProductPriceCalculation;
import com.liferay.commerce.product.model.CPAttachmentFileEntry;
import com.liferay.commerce.product.model.CPAttachmentFileEntryConstants;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.util.CPInstanceHelper;
import com.liferay.commerce.service.CommerceOrderItemLocalService;
import com.liferay.commerce.service.CommerceOrderLocalService;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.KeyValuePair;

import java.math.BigDecimal;

import java.text.DecimalFormat;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceOrderItemContentDisplayContext
	extends BaseCommerceOrderContentDisplayContext<CommerceOrderItem> {

	public CommerceOrderItemContentDisplayContext(
			HttpServletRequest httpServletRequest,
			CommerceOrderLocalService commerceOrderLocalService,
			CommerceOrderItemLocalService commerceOrderItemLocalService,
			CommerceOrderPriceCalculation commerceOrderPriceCalculation,
			CommerceOrderValidatorRegistry commerceOrderValidatorRegistry,
			CommerceProductPriceCalculation commerceProductPriceCalculation,
			CPInstanceHelper cpInstanceHelper)
		throws ConfigurationException {

		super(httpServletRequest, commerceOrderLocalService);

		_commerceOrderItemLocalService = commerceOrderItemLocalService;
		_commerceOrderPriceCalculation = commerceOrderPriceCalculation;
		_commerceOrderValidatorRegistry = commerceOrderValidatorRegistry;
		_commerceProductPriceCalculation = commerceProductPriceCalculation;
		_cpInstanceHelper = cpInstanceHelper;
	}

	public String getCommerceOrderItemThumbnailSrc(
			CommerceOrderItem commerceOrderItem, ThemeDisplay themeDisplay)
		throws Exception {

		List<CPAttachmentFileEntry> cpAttachmentFileEntries =
			_cpInstanceHelper.getCPAttachmentFileEntries(
				commerceOrderItem.getCPDefinitionId(),
				commerceOrderItem.getJson(),
				CPAttachmentFileEntryConstants.TYPE_IMAGE);

		if (cpAttachmentFileEntries.isEmpty()) {
			CPDefinition cpDefinition = commerceOrderItem.getCPDefinition();

			return cpDefinition.getDefaultImageThumbnailSrc(themeDisplay);
		}

		CPAttachmentFileEntry cpAttachmentFileEntry =
			cpAttachmentFileEntries.get(0);

		FileEntry fileEntry = cpAttachmentFileEntry.getFileEntry();

		if (fileEntry == null) {
			return null;
		}

		return DLUtil.getThumbnailSrc(fileEntry, themeDisplay);
	}

	public CommerceOrderPrice getCommerceOrderPrice() throws PortalException {
		return _commerceOrderPriceCalculation.getCommerceOrderPrice(
			getCommerceOrder(), getCommerceContext());
	}

	public Map<Long, List<CommerceOrderValidatorResult>>
			getCommerceOrderValidatorResults()
		throws PortalException {

		return _commerceOrderValidatorRegistry.getCommerceOrderValidatorResults(
			getCommerceOrder());
	}

	public CommerceProductPrice getCommerceProductPrice(
			CommerceOrderItem commerceOrderItem)
		throws PortalException {

		return _commerceProductPriceCalculation.getCommerceProductPrice(
			commerceOrderItem.getCPInstanceId(),
			commerceOrderItem.getQuantity(), getCommerceContext());
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

	public List<KeyValuePair> getKeyValuePairs(String json, Locale locale)
		throws PortalException {

		return _cpInstanceHelper.getKeyValuePairs(json, locale);
	}

	@Override
	public PortletURL getPortletURL() throws PortalException {
		PortletURL portletURL = super.getPortletURL();

		portletURL.setParameter(
			"mvcRenderCommandName", "viewCommerceOrderItems");
		portletURL.setParameter(
			"commerceOrderId", String.valueOf(getCommerceOrderId()));

		return portletURL;
	}

	@Override
	public SearchContainer<CommerceOrderItem> getSearchContainer()
		throws PortalException {

		if (_searchContainer != null) {
			return _searchContainer;
		}

		_searchContainer = new SearchContainer<>(
			liferayPortletRequest, getPortletURL(), null, null);

		_searchContainer.setEmptyResultsMessage("no-items-were-found");

		int total = _commerceOrderItemLocalService.getCommerceOrderItemsCount();

		_searchContainer.setTotal(total);

		List<CommerceOrderItem> results =
			_commerceOrderItemLocalService.getCommerceOrderItems(
				getCommerceOrderId(), _searchContainer.getStart(),
				_searchContainer.getEnd());

		_searchContainer.setResults(results);

		return _searchContainer;
	}

	protected CommerceContext getCommerceContext() {
		return (CommerceContext)httpServletRequest.getAttribute(
			CommerceWebKeys.COMMERCE_CONTEXT);
	}

	private final CommerceOrderItemLocalService _commerceOrderItemLocalService;
	private final CommerceOrderPriceCalculation _commerceOrderPriceCalculation;
	private final CommerceOrderValidatorRegistry
		_commerceOrderValidatorRegistry;
	private final CommerceProductPriceCalculation
		_commerceProductPriceCalculation;
	private final CPInstanceHelper _cpInstanceHelper;
	private SearchContainer<CommerceOrderItem> _searchContainer;

}