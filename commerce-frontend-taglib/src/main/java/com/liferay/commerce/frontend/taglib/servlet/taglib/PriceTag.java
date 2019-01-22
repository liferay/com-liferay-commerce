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

package com.liferay.commerce.frontend.taglib.servlet.taglib;

import com.liferay.commerce.constants.CPDefinitionInventoryConstants;
import com.liferay.commerce.constants.CommerceWebKeys;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.currency.model.CommerceMoney;
import com.liferay.commerce.discount.CommerceDiscountValue;
import com.liferay.commerce.frontend.taglib.internal.js.loader.modules.extender.npm.NPMResolverProvider;
import com.liferay.commerce.frontend.taglib.internal.model.PriceModel;
import com.liferay.commerce.frontend.taglib.internal.servlet.ServletContextUtil;
import com.liferay.commerce.model.CPDefinitionInventory;
import com.liferay.commerce.price.CommerceProductPrice;
import com.liferay.commerce.price.CommerceProductPriceCalculation;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPInstanceServiceUtil;
import com.liferay.commerce.service.CPDefinitionInventoryLocalServiceUtil;
import com.liferay.frontend.js.loader.modules.extender.npm.NPMResolver;
import com.liferay.frontend.taglib.soy.servlet.taglib.ComponentRendererTag;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.math.BigDecimal;

import java.util.Locale;
import java.util.Map;

import javax.servlet.jsp.PageContext;

/**
 * @author Marco Leo
 */
public class PriceTag extends ComponentRendererTag {

	@Override
	public int doStartTag() {
		CommerceContext commerceContext = (CommerceContext)request.getAttribute(
			CommerceWebKeys.COMMERCE_CONTEXT);
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		try {
			Map<String, Object> context = getContext();

			long cpInstanceId = (Long)context.getOrDefault("CPInstanceId", 0L);

			CPInstance cpInstance = CPInstanceServiceUtil.getCPInstance(
				cpInstanceId);

			int quantity = (Integer)context.getOrDefault("quantity", 1);

			if (quantity <= 0) {
				CPDefinitionInventory cpDefinitionInventory =
					CPDefinitionInventoryLocalServiceUtil.
						fetchCPDefinitionInventoryByCPDefinitionId(
							cpInstance.getCPDefinitionId());

				if (cpDefinitionInventory != null) {
					quantity = cpDefinitionInventory.getMinOrderQuantity();
				}
				else {
					quantity =
						CPDefinitionInventoryConstants.
							DEFAULT_MIN_ORDER_QUANTITY;
				}
			}

			PriceModel priceModel = _getPrice(
				cpInstanceId, quantity, commerceContext,
				themeDisplay.getLocale());

			putValue("prices", priceModel);

			setTemplateNamespace("Price.render");
		}
		catch (PortalException pe) {
			if (_log.isDebugEnabled()) {
				_log.debug(pe, pe);
			}

			return SKIP_BODY;
		}

		return super.doStartTag();
	}

	@Override
	public String getModule() {
		NPMResolver npmResolver = NPMResolverProvider.getNPMResolver();

		if (npmResolver == null) {
			return StringPool.BLANK;
		}

		return npmResolver.resolveModuleName(
			"commerce-frontend-taglib/price/Price.es");
	}

	public void setAdditionalDiscountedClasses(
		String additionalDiscountedClasses) {

		putValue("additionalDiscountedClasses", additionalDiscountedClasses);
	}

	public void setAdditionalOldPriceClasses(String additionalOldPriceClasses) {
		putValue("additionalOldPriceClasses", additionalOldPriceClasses);
	}

	public void setAdditionalPriceClasses(String additionalPriceClasses) {
		putValue("additionalPriceClasses", additionalPriceClasses);
	}

	public void setCPInstanceId(long cpInstanceId) {
		putValue("CPInstanceId", cpInstanceId);
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		_commerceProductPriceCalculation =
			ServletContextUtil.getCommerceProductPriceCalculation();
	}

	public void setQuantity(String quantity) {
		putValue("quantity", quantity);
	}

	private PriceModel _getPrice(
			long cpInstanceId, int quantity, CommerceContext commerceContext,
			Locale locale)
		throws PortalException {

		CommerceProductPrice commerceProductPrice =
			_commerceProductPriceCalculation.getCommerceProductPrice(
				cpInstanceId, quantity, true, commerceContext);

		if (commerceProductPrice == null) {
			return null;
		}

		CommerceMoney unitPrice = commerceProductPrice.getUnitPrice();

		PriceModel priceModel = new PriceModel(unitPrice.format(locale));

		CommerceMoney unitPromoPrice = commerceProductPrice.getUnitPromoPrice();

		BigDecimal promoPrice = unitPromoPrice.getPrice();

		if ((promoPrice.compareTo(BigDecimal.ZERO) > 0) &&
			(promoPrice.compareTo(unitPrice.getPrice()) < 0)) {

			priceModel.setPromoPrice(unitPromoPrice.format(locale));
		}

		CommerceDiscountValue discountValue =
			commerceProductPrice.getDiscountValue();

		if (discountValue != null) {
			CommerceMoney discountAmount = discountValue.getDiscountAmount();

			priceModel.setDiscount(discountAmount.format(locale));
		}

		return priceModel;
	}

	private static final Log _log = LogFactoryUtil.getLog(PriceTag.class);

	private CommerceProductPriceCalculation _commerceProductPriceCalculation;

}