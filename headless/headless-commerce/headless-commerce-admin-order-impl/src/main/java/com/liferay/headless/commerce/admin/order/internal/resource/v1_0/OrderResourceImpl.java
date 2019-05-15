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

package com.liferay.headless.commerce.admin.order.internal.resource.v1_0;

import com.liferay.commerce.context.CommerceContextFactory;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.service.CommerceCurrencyService;
import com.liferay.commerce.exception.NoSuchOrderException;
import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceShippingMethod;
import com.liferay.commerce.service.CommerceAddressService;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.commerce.service.CommerceShippingMethodService;
import com.liferay.headless.commerce.admin.order.dto.v1_0.BillingAddress;
import com.liferay.headless.commerce.admin.order.dto.v1_0.Order;
import com.liferay.headless.commerce.admin.order.dto.v1_0.ShippingAddress;
import com.liferay.headless.commerce.admin.order.resource.v1_0.OrderResource;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterRegistry;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DefaultDTOConverterContext;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/order.properties",
	scope = ServiceScope.PROTOTYPE, service = OrderResource.class
)
public class OrderResourceImpl extends BaseOrderResourceImpl {

	@Override
	public BillingAddress getOrderBillingAddres(Long id) throws Exception {
		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			id);

		CommerceAddress commerceAddress =
			_commerceAddressService.getCommerceAddress(
				commerceOrder.getBillingAddressId());

		DTOConverter billingAddressDTOConverter =
			_dtoConverterRegistry.getDTOConverter("BillingAddress");

		return (BillingAddress)billingAddressDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				commerceAddress.getCommerceAddressId()));
	}

	@Override
	public BillingAddress getOrderByExternalReferenceCodeBillingAddres(
			String externalReferenceCode)
		throws Exception {

		CommerceOrder commerceOrder =
			_commerceOrderService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceOrder == null) {
			throw new NoSuchOrderException(
				"Unable to find Order with externalReferenceCode: " +
					externalReferenceCode);
		}

		CommerceAddress commerceAddress =
			_commerceAddressService.getCommerceAddress(
				commerceOrder.getBillingAddressId());

		DTOConverter billingAddressDTOConverter =
			_dtoConverterRegistry.getDTOConverter("BillingAddress");

		return (BillingAddress)billingAddressDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				commerceAddress.getCommerceAddressId()));
	}

	@Override
	public ShippingAddress getOrderByExternalReferenceCodeShippingAddres(
			String externalReferenceCode)
		throws Exception {

		CommerceOrder commerceOrder =
			_commerceOrderService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceOrder == null) {
			throw new NoSuchOrderException(
				"Unable to find Order with externalReferenceCode: " +
					externalReferenceCode);
		}

		CommerceAddress commerceAddress =
			_commerceAddressService.getCommerceAddress(
				commerceOrder.getShippingAddressId());

		DTOConverter shippingAddressDTOConverter =
			_dtoConverterRegistry.getDTOConverter("ShippingAddress");

		return (ShippingAddress)shippingAddressDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				commerceAddress.getCommerceAddressId()));
	}

	@Override
	public Page<Order> getOrderBySiteIdSite(
			Long siteId, Long accountId, Pagination pagination)
		throws Exception {

		List<CommerceOrder> commerceOrders;
		int totalItems;

		if ((accountId != null) && (accountId > 0)) {
			commerceOrders = _commerceOrderService.getCommerceOrders(
				siteId, accountId, pagination.getStartPosition(),
				pagination.getEndPosition(), null);

			totalItems = _commerceOrderService.getCommerceOrdersCount(
				siteId, accountId);
		}
		else {
			commerceOrders = _commerceOrderService.getCommerceOrders(
				siteId, pagination.getStartPosition(),
				pagination.getEndPosition(), null);

			totalItems = _commerceOrderService.getCommerceOrdersCount(siteId);
		}

		return Page.of(_toOrders(commerceOrders), pagination, totalItems);
	}

	@Override
	public ShippingAddress getOrderShippingAddres(Long id) throws Exception {
		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			id);

		CommerceAddress commerceAddress =
			_commerceAddressService.getCommerceAddress(
				commerceOrder.getShippingAddressId());

		DTOConverter shippingAddressDTOConverter =
			_dtoConverterRegistry.getDTOConverter("ShippingAddress");

		return (ShippingAddress)shippingAddressDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				commerceAddress.getCommerceAddressId()));
	}

	@Override
	public Response patchOrderBillingAddres(
			Long id, BillingAddress billingAddress)
		throws Exception {

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			id);

		_updateBillingAddress(commerceOrder, billingAddress);

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Response patchOrderByExternalReferenceCodeBillingAddres(
			String externalReferenceCode, BillingAddress billingAddress)
		throws Exception {

		CommerceOrder commerceOrder =
			_commerceOrderService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceOrder == null) {
			throw new NoSuchOrderException(
				"Unable to find Order with externalReferenceCode: " +
					externalReferenceCode);
		}

		_updateBillingAddress(commerceOrder, billingAddress);

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Response patchOrderByExternalReferenceCodeShippingAddres(
			String externalReferenceCode, ShippingAddress shippingAddress)
		throws Exception {

		CommerceOrder commerceOrder =
			_commerceOrderService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceOrder == null) {
			throw new NoSuchOrderException(
				"Unable to find Order with externalReferenceCode: " +
					externalReferenceCode);
		}

		_updateShippingAddress(commerceOrder, shippingAddress);

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Response patchOrderShippingAddres(
			Long id, ShippingAddress shippingAddress)
		throws Exception {

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			id);

		_updateShippingAddress(commerceOrder, shippingAddress);

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Order postOrderBySiteIdSite(Long siteId, Order order)
		throws Exception {

		return _upsertOrder(siteId, order);
	}

	private List<Order> _toOrders(List<CommerceOrder> commerceOrders)
		throws Exception {

		List<Order> orders = new ArrayList<>();

		DTOConverter orderDTOConverter = _dtoConverterRegistry.getDTOConverter(
			CommerceOrder.class.getName());

		for (CommerceOrder commerceOrder : commerceOrders) {
			orders.add(
				(Order)orderDTOConverter.toDTO(
					new DefaultDTOConverterContext(
						contextAcceptLanguage.getPreferredLocale(),
						commerceOrder.getCommerceOrderId())));
		}

		return orders;
	}

	private Order _updateBillingAddress(
			CommerceOrder commerceOrder, BillingAddress billingAddress)
		throws Exception {

		CommerceAddress commerceAddress =
			_commerceAddressService.getCommerceAddress(
				commerceOrder.getBillingAddressId());

		commerceOrder = _commerceOrderService.updateBillingAddress(
			commerceOrder.getCommerceOrderId(), billingAddress.getName(),
			GetterUtil.get(
				billingAddress.getDescription(),
				commerceAddress.getDescription()),
			billingAddress.getStreet1(),
			GetterUtil.get(
				billingAddress.getStreet2(), commerceAddress.getStreet2()),
			GetterUtil.get(
				billingAddress.getStreet3(), commerceAddress.getStreet3()),
			billingAddress.getCity(),
			GetterUtil.get(billingAddress.getZip(), commerceAddress.getZip()),
			GetterUtil.get(
				billingAddress.getCommerceRegionId(),
				commerceAddress.getCommerceRegionId()),
			billingAddress.getCommerceCountryId(),
			GetterUtil.get(
				billingAddress.getPhoneNumber(),
				commerceAddress.getPhoneNumber()),
			_serviceContextHelper.getServiceContext(
				commerceOrder.getGroupId()));

		DTOConverter orderDTOConverter = _dtoConverterRegistry.getDTOConverter(
			CommerceOrder.class.getName());

		return (Order)orderDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				commerceOrder.getCommerceOrderId()));
	}

	private Order _updateShippingAddress(
			CommerceOrder commerceOrder, ShippingAddress shippingAddress)
		throws Exception {

		CommerceAddress commerceAddress =
			_commerceAddressService.getCommerceAddress(
				commerceOrder.getShippingAddressId());

		commerceOrder = _commerceOrderService.updateBillingAddress(
			commerceOrder.getCommerceOrderId(), shippingAddress.getName(),
			GetterUtil.get(
				shippingAddress.getDescription(),
				commerceAddress.getDescription()),
			shippingAddress.getStreet1(),
			GetterUtil.get(
				shippingAddress.getStreet2(), commerceAddress.getStreet2()),
			GetterUtil.get(
				shippingAddress.getStreet3(), commerceAddress.getStreet3()),
			shippingAddress.getCity(),
			GetterUtil.get(shippingAddress.getZip(), commerceAddress.getZip()),
			GetterUtil.get(
				shippingAddress.getCommerceRegionId(),
				commerceAddress.getCommerceRegionId()),
			shippingAddress.getCommerceCountryId(),
			GetterUtil.get(
				shippingAddress.getPhoneNumber(),
				commerceAddress.getPhoneNumber()),
			_serviceContextHelper.getServiceContext(
				commerceOrder.getGroupId()));

		DTOConverter orderDTOConverter = _dtoConverterRegistry.getDTOConverter(
			CommerceOrder.class.getName());

		return (Order)orderDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				commerceOrder.getCommerceOrderId()));
	}

	private Order _upsertOrder(long siteId, Order order) throws Exception {
		long commerceShippingMethodId = 0;

		CommerceShippingMethod commerceShippingMethod =
			_commerceShippingMethodService.fetchCommerceShippingMethod(
				siteId, order.getShippingMethod());

		if (commerceShippingMethod != null) {
			commerceShippingMethodId =
				commerceShippingMethod.getCommerceShippingMethodId();
		}

		CommerceCurrency commerceCurrency =
			_commerceCurrencyService.getCommerceCurrency(
				siteId, order.getCurrency());

		CommerceOrder commerceOrder = _commerceOrderService.upsertCommerceOrder(
			order.getCommerceAccountId(),
			commerceCurrency.getCommerceCurrencyId(),
			GetterUtil.get(order.getBillingAddressId(), 0L),
			GetterUtil.get(order.getShippingAddressId(), 0L),
			order.getPaymentMethod(), commerceShippingMethodId,
			order.getShippingOption(), order.getPurchaseOrderNumber(),
			order.getSubtotal(), order.getShippingAmount(), order.getTotal(),
			GetterUtil.get(order.getPaymentStatus(), 0),
			GetterUtil.get(order.getOrderStatus(), 0), order.getAdvanceStatus(),
			order.getExternalReferenceCode(),
			_commerceContextFactory.create(
				siteId, _user.getUserId(), 0L, order.getCommerceAccountId()),
			_serviceContextHelper.getServiceContext(siteId));

		DTOConverter orderDTOConverter = _dtoConverterRegistry.getDTOConverter(
			CommerceOrder.class.getName());

		return (Order)orderDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				commerceOrder.getCommerceOrderId()));
	}

	@Reference
	private CommerceAddressService _commerceAddressService;

	@Reference
	private CommerceContextFactory _commerceContextFactory;

	@Reference
	private CommerceCurrencyService _commerceCurrencyService;

	@Reference
	private CommerceOrderService _commerceOrderService;

	@Reference
	private CommerceShippingMethodService _commerceShippingMethodService;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

	@Context
	private User _user;

}