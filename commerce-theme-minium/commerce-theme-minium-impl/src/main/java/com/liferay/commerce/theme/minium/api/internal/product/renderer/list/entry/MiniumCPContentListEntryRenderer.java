/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.commerce.theme.minium.api.internal.product.renderer.list.entry;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.constants.CommerceWebKeys;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.frontend.model.PriceModel;
import com.liferay.commerce.frontend.model.ProductSettingsModel;
import com.liferay.commerce.frontend.template.soy.renderer.ComponentDescriptor;
import com.liferay.commerce.frontend.template.soy.renderer.SoyComponentRenderer;
import com.liferay.commerce.frontend.util.ProductHelper;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.product.catalog.CPCatalogEntry;
import com.liferay.commerce.product.catalog.CPSku;
import com.liferay.commerce.product.constants.CPPortletKeys;
import com.liferay.commerce.product.content.constants.CPContentWebKeys;
import com.liferay.commerce.product.content.render.list.entry.CPContentListEntryRenderer;
import com.liferay.commerce.product.content.util.CPContentHelper;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 */
@Component(
	immediate = true,
	property = {
		"commerce.product.content.list.entry.renderer.key=" + MiniumCPContentListEntryRenderer.KEY,
		"commerce.product.content.list.entry.renderer.portlet.name=" + CPPortletKeys.CP_COMPARE_CONTENT_WEB,
		"commerce.product.content.list.entry.renderer.portlet.name=" + CPPortletKeys.CP_PUBLISHER_WEB,
		"commerce.product.content.list.entry.renderer.portlet.name=" + CPPortletKeys.CP_SEARCH_RESULTS,
		"commerce.product.content.list.entry.renderer.type=grouped",
		"commerce.product.content.list.entry.renderer.type=simple",
		"commerce.product.content.list.entry.renderer.type=virtual"
	},
	service = CPContentListEntryRenderer.class
)
public class MiniumCPContentListEntryRenderer
	implements CPContentListEntryRenderer {

	public static final String KEY = "list-entry-minium";

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(resourceBundle, "minium");
	}

	@Override
	public void render(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {

		CommerceContext commerceContext =
			(CommerceContext)httpServletRequest.getAttribute(
				CommerceWebKeys.COMMERCE_CONTEXT);

		CPContentHelper cpContentHelper =
			(CPContentHelper)httpServletRequest.getAttribute(
				CPContentWebKeys.CP_CONTENT_HELPER);

		CPCatalogEntry cpCatalogEntry = cpContentHelper.getCPCatalogEntry(
			httpServletRequest);

		List<CPSku> cpSkus = cpCatalogEntry.getCPSkus();

		CPSku cpSku = null;

		if (cpSkus.size() == 1) {
			cpSku = cpSkus.get(0);
		}

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		Map<String, Object> context = new HashMap<>();

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		String portletName = portletDisplay.getPortletName();

		if (portletName.equals(CPPortletKeys.CP_COMPARE_CONTENT_WEB)) {
			PortletURL editCompareProductActionURL =
				PortletURLFactoryUtil.create(
					httpServletRequest, CPPortletKeys.CP_COMPARE_CONTENT_WEB,
					PortletRequest.ACTION_PHASE);

			editCompareProductActionURL.setParameter(
				ActionRequest.ACTION_NAME, "editCompareProduct");

			context.put("compareCheckboxVisible", false);
			context.put(
				"compareContentNamespace",
				_portal.getPortletNamespace(
					CPPortletKeys.CP_COMPARE_CONTENT_WEB));
			context.put("deleteButtonVisible", true);
			context.put(
				"editCompareProductActionURL",
				editCompareProductActionURL.toString());
		}
		else {
			context.put("compareCheckboxVisible", true);
			context.put("deleteButtonVisible", false);
		}

		CommerceAccount commerceAccount = commerceContext.getCommerceAccount();

		if (commerceAccount != null) {
			context.put("accountId", commerceAccount.getCommerceAccountId());
		}

		CommerceOrder commerceOrder = commerceContext.getCommerceOrder();

		if (commerceOrder != null) {
			context.put("orderId", commerceOrder.getCommerceOrderId());
		}

		context.put("availability", "available");
		context.put(
			"cartAPI",
			_portal.getPortalURL(httpServletRequest) +
				"/o/commerce-ui/cart-item");
		context.put("categories", null);
		context.put("description", cpCatalogEntry.getShortDescription());
		context.put(
			"detailsLink",
			cpContentHelper.getFriendlyURL(cpCatalogEntry, themeDisplay));
		context.put("name", cpCatalogEntry.getName());
		context.put("pictureUrl", cpCatalogEntry.getDefaultImageFileUrl());
		context.put("productId", cpCatalogEntry.getCPDefinitionId());

		if (cpSku != null) {
			context.put("sku", cpSku.getSku());
			context.put("skuId", cpSku.getCPInstanceId());

			ProductSettingsModel productSettingsModel =
				_productHelper.getProductSettingsModel(cpSku.getCPInstanceId());

			PriceModel priceModel = _productHelper.getPrice(
				cpSku.getCPInstanceId(), productSettingsModel.getMinQuantity(),
				commerceContext, themeDisplay.getLocale());

			context.put("prices", priceModel);

			context.put("settings", productSettingsModel);
		}

		context.put(
			"spritemap",
			themeDisplay.getPathThemeImages() + "/commerce-icons.svg");

		String module =
			"commerce-theme-minium-impl@1.0.8/product_card/ProductCard.es";

		Set<String> dependencies = new HashSet<>();

		dependencies.add(
			"commerce-frontend-taglib@1.1.1/add_to_cart/AddToCartButton.es");
		dependencies.add(
			"commerce-frontend-taglib@1.1.1/compare_checkbox" +
				"/CompareCheckbox.es");
		dependencies.add("commerce-frontend-taglib@1.1.1/price/Price.es");

		ComponentDescriptor componentDescriptor = new ComponentDescriptor(
			"ProductCard.render", module, null, dependencies);

		_soyComponentRenderer.renderSoyComponent(
			httpServletRequest, httpServletResponse, componentDescriptor,
			context);
	}

	@Reference
	private Portal _portal;

	@Reference
	private ProductHelper _productHelper;

	@Reference
	private SoyComponentRenderer _soyComponentRenderer;

}