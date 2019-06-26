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

import com.liferay.commerce.constants.CommerceWebKeys;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.frontend.model.PriceModel;
import com.liferay.commerce.frontend.model.ProductSettingsModel;
import com.liferay.commerce.frontend.taglib.internal.js.loader.modules.extender.npm.NPMResolverProvider;
import com.liferay.commerce.frontend.taglib.internal.util.ProductHelperProvider;
import com.liferay.commerce.frontend.util.ProductHelper;
import com.liferay.frontend.js.loader.modules.extender.npm.NPMResolver;
import com.liferay.frontend.taglib.soy.servlet.taglib.ComponentRendererTag;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

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

			int quantity = (Integer)context.getOrDefault("quantity", 1);

			if (quantity <= 0) {
				ProductSettingsModel productSettingsModel =
					_productHelper.getProductSettingsModel(cpInstanceId);

				quantity = productSettingsModel.getMinQuantity();
			}

			PriceModel priceModel = _productHelper.getPrice(
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

	public void setadditionalFinalPriceClasses(
		String additionalFinalPriceClasses) {

		putValue("additionalFinalPriceClasses", additionalFinalPriceClasses);
	}

	public void setShowLabel(
		boolean showLabel) {

		putValue("showLabel", showLabel);
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

		_productHelper = ProductHelperProvider.getProductHelper();
	}

	public void setQuantity(String quantity) {
		putValue("quantity", quantity);
	}

	private static final Log _log = LogFactoryUtil.getLog(PriceTag.class);

	private ProductHelper _productHelper;

}