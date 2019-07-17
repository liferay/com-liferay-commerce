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

package com.liferay.commerce.shipping.engine.fixed.web.internal;

import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.currency.model.CommerceMoney;
import com.liferay.commerce.exception.CommerceShippingEngineException;
import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceShippingEngine;
import com.liferay.commerce.model.CommerceShippingMethod;
import com.liferay.commerce.model.CommerceShippingOption;
import com.liferay.commerce.price.CommerceOrderPriceCalculation;
import com.liferay.commerce.service.CommerceAddressRestrictionLocalService;
import com.liferay.commerce.service.CommerceShippingMethodLocalService;
import com.liferay.commerce.shipping.engine.fixed.model.CommerceShippingFixedOption;
import com.liferay.commerce.shipping.engine.fixed.model.CommerceShippingFixedOptionRel;
import com.liferay.commerce.shipping.engine.fixed.service.CommerceShippingFixedOptionLocalService;
import com.liferay.commerce.shipping.engine.fixed.service.CommerceShippingFixedOptionRelLocalService;
import com.liferay.commerce.util.CommerceShippingHelper;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = "commerce.shipping.engine.key=" + ByWeightCommerceShippingEngine.KEY,
	service = CommerceShippingEngine.class
)
public class ByWeightCommerceShippingEngine implements CommerceShippingEngine {

	public static final String KEY = "by-weight";

	@Override
	public String getCommerceShippingOptionLabel(String name, Locale locale) {
		ResourceBundle resourceBundle = _getResourceBundle(locale);

		return ResourceBundleUtil.getString(resourceBundle, name);
	}

	@Override
	public List<CommerceShippingOption> getCommerceShippingOptions(
			CommerceContext commerceContext, CommerceOrder commerceOrder,
			Locale locale)
		throws CommerceShippingEngineException {

		List<CommerceShippingOption> commerceShippingOptions =
			new ArrayList<>();

		try {
			commerceShippingOptions = _getCommerceShippingOptions(
				commerceContext, commerceOrder, locale);
		}
		catch (PortalException pe) {
			if (_log.isDebugEnabled()) {
				_log.debug(pe, pe);
			}
		}

		return commerceShippingOptions;
	}

	@Override
	public String getDescription(Locale locale) {
		ResourceBundle resourceBundle = _getResourceBundle(locale);

		return LanguageUtil.get(resourceBundle, "by-weight-description");
	}

	@Override
	public String getName(Locale locale) {
		ResourceBundle resourceBundle = _getResourceBundle(locale);

		return LanguageUtil.get(resourceBundle, "variable-rate");
	}

	private List<CommerceShippingFixedOption> _getCommerceShippingFixedOptions(
		long groupId) {

		CommerceShippingMethod commerceShippingMethod =
			_commerceShippingMethodLocalService.fetchCommerceShippingMethod(
				groupId, KEY);

		if (commerceShippingMethod == null) {
			return Collections.emptyList();
		}

		return _commerceShippingFixedOptionLocalService.
			getCommerceShippingFixedOptions(
				commerceShippingMethod.getCommerceShippingMethodId(),
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	private CommerceShippingOption _getCommerceShippingOption(
			CommerceContext commerceContext, CommerceOrder commerceOrder,
			Locale locale, CommerceAddress commerceAddress,
			CommerceShippingFixedOption commerceShippingFixedOption)
		throws PortalException {

		double orderWeight = _commerceShippingHelper.getWeight(
			commerceOrder.getCommerceOrderItems());

		CommerceShippingFixedOptionRel commerceShippingFixedOptionRel =
			_commerceShippingFixedOptionRelLocalService.
				fetchCommerceShippingFixedOptionRel(
					commerceShippingFixedOption.
						getCommerceShippingFixedOptionId(),
					commerceAddress.getCommerceCountryId(),
					commerceAddress.getCommerceRegionId(),
					commerceAddress.getZip(), orderWeight);

		if (commerceShippingFixedOptionRel == null) {
			return null;
		}

		String name = commerceShippingFixedOption.getName(locale);

		if (_commerceShippingHelper.isFreeShipping(commerceOrder)) {
			return new CommerceShippingOption(name, name, BigDecimal.ZERO);
		}

		BigDecimal amount = commerceShippingFixedOptionRel.getFixedPrice();

		BigDecimal rateUnitWeightPrice =
			commerceShippingFixedOptionRel.getRateUnitWeightPrice();

		if (rateUnitWeightPrice.compareTo(BigDecimal.ZERO) > 0) {
			amount = amount.add(
				rateUnitWeightPrice.multiply(new BigDecimal(orderWeight)));
		}

		BigDecimal ratePercentage = new BigDecimal(
			commerceShippingFixedOptionRel.getRatePercentage());

		CommerceMoney commerceMoney =
			_commerceOrderPriceCalculation.getSubtotal(
				commerceOrder, false, commerceContext);

		BigDecimal orderPrice = commerceMoney.getPrice();

		amount = amount.add(
			ratePercentage.multiply(orderPrice.divide(new BigDecimal(100))));

		return new CommerceShippingOption(name, name, amount);
	}

	private List<CommerceShippingOption> _getCommerceShippingOptions(
			CommerceContext commerceContext, CommerceOrder commerceOrder,
			Locale locale)
		throws PortalException {

		List<CommerceShippingOption> commerceShippingOptions =
			new ArrayList<>();

		CommerceAddress commerceAddress = commerceOrder.getShippingAddress();

		List<CommerceShippingFixedOption> commerceShippingFixedOptions =
			_getCommerceShippingFixedOptions(commerceContext.getSiteGroupId());

		for (CommerceShippingFixedOption commerceShippingFixedOption :
				commerceShippingFixedOptions) {

			boolean restricted =
				_commerceAddressRestrictionLocalService.
					isCommerceShippingMethodRestricted(
						commerceShippingFixedOption.
							getCommerceShippingMethodId(),
						commerceAddress.getCommerceCountryId());

			if (restricted) {
				continue;
			}

			CommerceShippingOption commerceShippingOption =
				_getCommerceShippingOption(
					commerceContext, commerceOrder, locale, commerceAddress,
					commerceShippingFixedOption);

			if (commerceShippingOption != null) {
				commerceShippingOptions.add(commerceShippingOption);
			}
		}

		return commerceShippingOptions;
	}

	private ResourceBundle _getResourceBundle(Locale locale) {
		return ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ByWeightCommerceShippingEngine.class);

	@Reference
	private CommerceAddressRestrictionLocalService
		_commerceAddressRestrictionLocalService;

	@Reference
	private CommerceOrderPriceCalculation _commerceOrderPriceCalculation;

	@Reference
	private CommerceShippingFixedOptionLocalService
		_commerceShippingFixedOptionLocalService;

	@Reference
	private CommerceShippingFixedOptionRelLocalService
		_commerceShippingFixedOptionRelLocalService;

	@Reference
	private CommerceShippingHelper _commerceShippingHelper;

	@Reference
	private CommerceShippingMethodLocalService
		_commerceShippingMethodLocalService;

}