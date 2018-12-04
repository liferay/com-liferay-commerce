/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 * <p>
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * <p>
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.commerce.cart.taglib.servlet.taglib.internal.application;

import com.liferay.commerce.cart.taglib.servlet.taglib.internal.application.context.provider.CommerceContextProvider;
import com.liferay.commerce.constants.CPDefinitionInventoryConstants;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.currency.model.CommerceMoney;
import com.liferay.commerce.discount.CommerceDiscountValue;
import com.liferay.commerce.model.CPDefinitionInventory;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.price.CommerceOrderPrice;
import com.liferay.commerce.price.CommerceOrderPriceCalculation;
import com.liferay.commerce.price.CommerceProductPrice;
import com.liferay.commerce.price.CommerceProductPriceCalculation;
import com.liferay.commerce.product.model.CPAttachmentFileEntry;
import com.liferay.commerce.product.model.CPAttachmentFileEntryConstants;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.util.CPInstanceHelper;
import com.liferay.commerce.service.CPDefinitionInventoryLocalService;
import com.liferay.commerce.service.CommerceOrderItemService;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.events.ServicePreAction;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.InstancePool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.math.BigDecimal;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/commerce-cart",
		JaxrsWhiteboardConstants.JAX_RS_NAME + "=CommerceCart.Rest",
		"auth.verifier.auth.verifier.PortalSessionAuthVerifier.urls.includes=/*",
		"auth.verifier.guest.allowed=true", "liferay.oauth2=false"
	},
	service = Application.class
)
public class CommerceCartApplication extends Application {

	@DELETE
	@Path("/{commerceOrderItemId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCommerceOrderItem(
		@PathParam("commerceOrderItemId") long commerceOrderItemId,
		@Context UriInfo uriInfo, @Context CommerceContext commerceContext,
		@Context HttpServletRequest httpServletRequest,
		@Context HttpServletResponse httpServletResponse) {

		try {
			CommerceOrderItem commerceOrderItem =
				_commerceOrderItemService.getCommerceOrderItem(
					commerceOrderItemId);

			long commerceOrderId = commerceOrderItem.getCommerceOrderId();

			_commerceOrderItemService.deleteCommerceOrderItem(
				commerceOrderItem.getCommerceOrderItemId());

			CommerceOrder commerceOrder =
				_commerceOrderService.getCommerceOrder(commerceOrderId);

			ServicePreAction servicePreAction =
				(ServicePreAction)InstancePool.get(
					ServicePreAction.class.getName());

			ThemeDisplay themeDisplay = servicePreAction.initThemeDisplay(
				httpServletRequest, httpServletResponse);

			httpServletRequest.setAttribute(
				WebKeys.THEME_DISPLAY, themeDisplay);

			JSONObject jsonObject = _jsonFactory.createJSONObject();

			JSONArray productsJSONArray = _getProductsJSONArray(
				commerceOrder, themeDisplay, commerceContext);

			jsonObject.put("products", productsJSONArray);

			JSONObject summaryJSONObject = _getSummaryJSONObject(
				commerceOrder, themeDisplay.getLocale(), commerceContext);

			jsonObject.put("summary", summaryJSONObject);

			return Response.ok(
				jsonObject.toString(2), MediaType.APPLICATION_JSON
			).build();
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return Response.status(
			Response.Status.NOT_FOUND
		).build();
	}

	@GET
	@Path("/{commerceOrderId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCommerceOrder(
		@PathParam("commerceOrderId") long commerceOrderId,
		@Context UriInfo uriInfo, @Context CommerceContext commerceContext,
		@Context HttpServletRequest httpServletRequest,
		@Context HttpServletResponse httpServletResponse) {

		try {
			CommerceOrder commerceOrder =
				_commerceOrderService.getCommerceOrder(commerceOrderId);

			ServicePreAction servicePreAction =
				(ServicePreAction)InstancePool.get(
					ServicePreAction.class.getName());

			ThemeDisplay themeDisplay = servicePreAction.initThemeDisplay(
				httpServletRequest, httpServletResponse);

			httpServletRequest.setAttribute(
				WebKeys.THEME_DISPLAY, themeDisplay);

			JSONObject jsonObject = _jsonFactory.createJSONObject();

			JSONArray productsJSONArray = _getProductsJSONArray(
				commerceOrder, themeDisplay, commerceContext);

			jsonObject.put("products", productsJSONArray);

			JSONObject summaryJSONObject = _getSummaryJSONObject(
				commerceOrder, themeDisplay.getLocale(), commerceContext);

			jsonObject.put("summary", summaryJSONObject);

			return Response.ok(
				jsonObject.toString(2), MediaType.APPLICATION_JSON
			).build();
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return Response.status(
			Response.Status.NOT_FOUND
		).build();
	}

	public Set<Object> getSingletons() {
		Set<Object> singletons = new HashSet<>();

		singletons.add(_commerceContextProvider);
		singletons.add(this);

		return singletons;
	}

	@Path("/{commerceOrderItemId}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateOrderItem(
		@PathParam("commerceOrderItemId") long commerceOrderItemId,
		@FormParam("quantity") int quantity, @Context UriInfo uriInfo,
		@Context CommerceContext commerceContext,
		@Context HttpServletRequest httpServletRequest,
		@Context HttpServletResponse httpServletResponse) {

		try {
			CommerceOrderItem commerceOrderItem =
				_commerceOrderItemService.updateCommerceOrderItem(
					commerceOrderItemId, quantity, commerceContext);

			CommerceOrder commerceOrder = commerceOrderItem.getCommerceOrder();

			ServicePreAction servicePreAction =
				(ServicePreAction)InstancePool.get(
					ServicePreAction.class.getName());

			ThemeDisplay themeDisplay = servicePreAction.initThemeDisplay(
				httpServletRequest, httpServletResponse);

			httpServletRequest.setAttribute(
				WebKeys.THEME_DISPLAY, themeDisplay);

			JSONObject jsonObject = _jsonFactory.createJSONObject();

			JSONArray productsJSONArray = _getProductsJSONArray(
				commerceOrder, themeDisplay, commerceContext);

			jsonObject.put("products", productsJSONArray);

			JSONObject summaryJSONObject = _getSummaryJSONObject(
				commerceOrder, themeDisplay.getLocale(), commerceContext);

			jsonObject.put("summary", summaryJSONObject);

			return Response.ok(
				jsonObject.toString(2), MediaType.APPLICATION_JSON
			).build();
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return Response.status(
			Response.Status.NOT_FOUND
		).build();
	}

	private String _getCommerceOrderItemThumbnailSrc(
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

	private JSONObject _getPriceJSONObject(
			CommerceOrderItem commerceOrderItem, Locale locale,
			CommerceContext commerceContext)
		throws PortalException {

		JSONObject jsonObject = _jsonFactory.createJSONObject();

		CommerceProductPrice commerceProductPrice =
			_commerceProductPriceCalculation.getCommerceProductPrice(
				commerceOrderItem.getCPInstanceId(),
				commerceOrderItem.getQuantity(), true, commerceContext);

		CommerceDiscountValue discountValue =
			commerceProductPrice.getDiscountValue();

		if (discountValue != null) {
			CommerceMoney discountAmount = discountValue.getDiscountAmount();

			jsonObject.put("discount", discountAmount.format(locale));
		}

		CommerceMoney unitPrice = commerceProductPrice.getUnitPrice();

		jsonObject.put("formattedPrice", unitPrice.format(locale));

		CommerceMoney unitPromoPrice = commerceProductPrice.getUnitPromoPrice();

		BigDecimal price = unitPromoPrice.getPrice();

		if ((BigDecimal.ZERO.compareTo(price) >= 0) &&
			(price.compareTo(unitPrice.getPrice()) >= 0)) {

			jsonObject.put(
				"formattedPromoPrice", unitPromoPrice.format(locale));
		}

		return jsonObject;
	}

	private JSONObject _getProductJSONObject(
			CommerceOrderItem commerceOrderItem, ThemeDisplay themeDisplay,
			CommerceContext commerceContext)
		throws Exception {

		JSONObject productJSONObject = _jsonFactory.createJSONObject();

		productJSONObject.put("error", "");
		productJSONObject.put("id", commerceOrderItem.getCPInstanceId());
		productJSONObject.put(
			"name", commerceOrderItem.getName(themeDisplay.getLocale()));
		productJSONObject.put("quantity", commerceOrderItem.getQuantity());
		productJSONObject.put("sku", commerceOrderItem.getSku());

		productJSONObject.put(
			"thumbnail",
			_getCommerceOrderItemThumbnailSrc(commerceOrderItem, themeDisplay));

		productJSONObject.put(
			"price",
			_getPriceJSONObject(
				commerceOrderItem, themeDisplay.getLocale(), commerceContext));

		productJSONObject.put(
			"settings", _getSettingsJSONObject(commerceOrderItem));

		return productJSONObject;
	}

	private JSONArray _getProductsJSONArray(
			CommerceOrder commerceOrder, ThemeDisplay themeDisplay,
			CommerceContext commerceContext)
		throws Exception {

		JSONArray jsonArray = _jsonFactory.createJSONArray();

		List<CommerceOrderItem> commerceOrderItems =
			commerceOrder.getCommerceOrderItems();

		for (CommerceOrderItem commerceOrderItem : commerceOrderItems) {
			jsonArray.put(
				_getProductJSONObject(
					commerceOrderItem, themeDisplay, commerceContext));
		}

		return jsonArray;
	}

	private JSONObject _getSettingsJSONObject(
			CommerceOrderItem commerceOrderItem)
		throws PortalException {

		JSONObject jsonObject = _jsonFactory.createJSONObject();

		CPDefinitionInventory cpDefinitionInventory =
			_cpDefinitionInventoryLocalService.
				fetchCPDefinitionInventoryByCPDefinitionId(
					commerceOrderItem.getCPDefinitionId());

		if (cpDefinitionInventory == null) {
			jsonObject.put(
				"maxQuantity",
				CPDefinitionInventoryConstants.DEFAULT_MAX_ORDER_QUANTITY);
			jsonObject.put(
				"minQuantity",
				CPDefinitionInventoryConstants.DEFAULT_MIN_ORDER_QUANTITY);
			jsonObject.put(
				"multipleQuantities",
				CPDefinitionInventoryConstants.DEFAULT_MULTIPLE_ORDER_QUANTITY);
		}
		else {
			jsonObject.put(
				"maxQuantity", cpDefinitionInventory.getMaxOrderQuantity());
			jsonObject.put(
				"minQuantity", cpDefinitionInventory.getMinOrderQuantity());
			jsonObject.put(
				"multipleQuantities",
				cpDefinitionInventory.getMultipleOrderQuantity());

			String allowedOrderQuantities =
				cpDefinitionInventory.getAllowedOrderQuantities();

			if (Validator.isNotNull(allowedOrderQuantities)) {
				String allowedOrderQuantitiesString =
					allowedOrderQuantities.replaceAll(
						" *(, *)|(\\. *)|( +)", StringPool.COMMA);

				jsonObject.put(
					"allowedOptions",
					_jsonFactory.createJSONArray(allowedOrderQuantitiesString));
			}
		}

		return jsonObject;
	}

	private JSONObject _getSummaryJSONObject(
			CommerceOrder commerceOrder, Locale locale,
			CommerceContext commerceContext)
		throws PortalException {

		JSONObject jsonObject = _jsonFactory.createJSONObject();

		CommerceOrderPrice commerceOrderPrice =
			_commerceOrderPriceCalculation.getCommerceOrderPrice(
				commerceOrder, commerceContext);

		CommerceMoney total = commerceOrderPrice.getTotal();
		CommerceMoney subtotal = commerceOrderPrice.getSubtotal();

		jsonObject.put("grandTotal", total.format(locale));
		jsonObject.put("subtotal", subtotal.format(locale));

		CommerceDiscountValue totalDiscountValue =
			commerceOrderPrice.getTotalDiscountValue();

		if (totalDiscountValue != null) {
			CommerceMoney discountAmount =
				totalDiscountValue.getDiscountAmount();

			jsonObject.put("discount", discountAmount.format(locale));
		}

		int totalUnits =
			_commerceOrderItemService.getCommerceOrderItemsQuantity(
				commerceOrder.getCommerceOrderId());

		jsonObject.put("totalUnits", totalUnits);

		return jsonObject;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceCartApplication.class);

	@Reference
	private CommerceContextProvider _commerceContextProvider;

	@Reference
	private CommerceOrderItemService _commerceOrderItemService;

	@Reference
	private CommerceOrderPriceCalculation _commerceOrderPriceCalculation;

	@Reference
	private CommerceOrderService _commerceOrderService;

	@Reference
	private CommerceProductPriceCalculation _commerceProductPriceCalculation;

	@Reference
	private CPDefinitionInventoryLocalService
		_cpDefinitionInventoryLocalService;

	@Reference
	private CPInstanceHelper _cpInstanceHelper;

	@Reference
	private JSONFactory _jsonFactory;

}