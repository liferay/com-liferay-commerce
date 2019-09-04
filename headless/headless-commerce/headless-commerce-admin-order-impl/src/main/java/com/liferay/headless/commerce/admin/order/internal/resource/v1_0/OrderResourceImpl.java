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

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.CommerceAccountService;
import com.liferay.commerce.constants.CommerceOrderConstants;
import com.liferay.commerce.context.CommerceContextFactory;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.service.CommerceCurrencyService;
import com.liferay.commerce.exception.NoSuchOrderException;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceShippingMethod;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.service.CPInstanceService;
import com.liferay.commerce.product.service.CommerceChannelLocalService;
import com.liferay.commerce.service.CommerceAddressService;
import com.liferay.commerce.service.CommerceOrderItemService;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.commerce.service.CommerceShippingMethodService;
import com.liferay.headless.commerce.admin.order.dto.v1_0.BillingAddress;
import com.liferay.headless.commerce.admin.order.dto.v1_0.Order;
import com.liferay.headless.commerce.admin.order.dto.v1_0.OrderItem;
import com.liferay.headless.commerce.admin.order.dto.v1_0.ShippingAddress;
import com.liferay.headless.commerce.admin.order.internal.util.v1_0.BillingAddressUtil;
import com.liferay.headless.commerce.admin.order.internal.util.v1_0.OrderItemUtil;
import com.liferay.headless.commerce.admin.order.internal.util.v1_0.ShippingAddressUtil;
import com.liferay.headless.commerce.admin.order.resource.v1_0.OrderResource;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterRegistry;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DefaultDTOConverterContext;
import com.liferay.headless.commerce.core.util.ExpandoUtil;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.util.SearchUtil;

import java.math.BigDecimal;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

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
	public Response deleteOrder(Long id) throws Exception {
		_commerceOrderService.deleteCommerceOrder(id);

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Response deleteOrderByExternalReferenceCode(
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

		_commerceOrderService.deleteCommerceOrder(
			commerceOrder.getCommerceOrderId());

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Order getOrder(Long id) throws Exception {
		DTOConverter orderDTOConverter = _dtoConverterRegistry.getDTOConverter(
			CommerceOrder.class.getName());

		return (Order)orderDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				GetterUtil.getLong(id)));
	}

	@Override
	public Order getOrderByExternalReferenceCode(String externalReferenceCode)
		throws Exception {

		CommerceOrder commerceOrder =
			_commerceOrderService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceOrder == null) {
			throw new NoSuchOrderException(
				"Unable to find Order with externalReferenceCode: " +
					externalReferenceCode);
		}

		DTOConverter orderDTOConverter = _dtoConverterRegistry.getDTOConverter(
			CommerceOrder.class.getName());

		return (Order)orderDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				commerceOrder.getCommerceOrderId()));
	}

	@Override
	public Page<Order> getOrdersPage(
			Filter filter, Pagination pagination, Sort[] sorts)
		throws Exception {

		return SearchUtil.search(
			booleanQuery -> booleanQuery.getPreBooleanFilter(), filter,
			CommerceOrder.class, StringPool.BLANK, pagination,
			queryConfig -> queryConfig.setSelectedFieldNames(
				Field.ENTRY_CLASS_PK),
			searchContext -> {
				searchContext.setCompanyId(contextCompany.getCompanyId());

				long[] commerceChannelGroupIds = _getCommerceChannelGroupIds();

				if ((commerceChannelGroupIds != null) &&
					(commerceChannelGroupIds.length > 0)) {

					searchContext.setGroupIds(commerceChannelGroupIds);
				}
			},
			document -> _toOrder(
				_commerceOrderService.getCommerceOrder(
					GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)))),
			sorts);
	}

	@Override
	public Response patchOrder(Long id, Order order) throws Exception {
		_updateOrder(_commerceOrderService.getCommerceOrder(id), order);

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Response patchOrderByExternalReferenceCode(
			String externalReferenceCode, Order order)
		throws Exception {

		CommerceOrder commerceOrder =
			_commerceOrderService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceOrder == null) {
			throw new NoSuchOrderException(
				"Unable to find Order with externalReferenceCode: " +
					externalReferenceCode);
		}

		_updateOrder(commerceOrder, order);

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Order postOrder(Order order) throws Exception {
		CommerceOrder commerceOrder = _upsertOrder(order);

		DTOConverter orderDTOConverter = _dtoConverterRegistry.getDTOConverter(
			CommerceOrder.class.getName());

		return (Order)orderDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				commerceOrder.getCommerceOrderId()));
	}

	private long[] _getCommerceChannelGroupIds() throws PortalException {
		List<CommerceChannel> commerceChannels =
			_commerceChannelLocalService.searchCommerceChannels(
				contextCompany.getCompanyId());

		Stream<CommerceChannel> stream = commerceChannels.stream();

		return stream.mapToLong(
			CommerceChannel::getGroupId
		).toArray();
	}

	private Order _toOrder(CommerceOrder commerceOrder) throws Exception {
		DTOConverter orderDTOConverter = _dtoConverterRegistry.getDTOConverter(
			CommerceOrder.class.getName());

		return (Order)orderDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				commerceOrder.getCommerceOrderId()));
	}

	private CommerceOrder _updateNestedResources(
			Order order, CommerceOrder commerceOrder,
			ServiceContext serviceContext)
		throws Exception {

		// Order items

		OrderItem[] orderItems = order.getItems();

		if (orderItems != null) {
			for (OrderItem orderItem : orderItems) {
				OrderItemUtil.upsertCommerceOrderItem(
					_cpInstanceService, _commerceOrderItemService, orderItem,
					commerceOrder,
					_commerceContextFactory.create(
						contextCompany.getCompanyId(),
						commerceOrder.getGroupId(), _user.getUserId(),
						commerceOrder.getCommerceOrderId(),
						commerceOrder.getCommerceAccountId()),
					serviceContext);
			}
		}

		// Billing Address

		BillingAddress billingAddress = order.getBillingAddress();

		if (billingAddress != null) {
			commerceOrder = BillingAddressUtil.upsertBillingAddress(
				_commerceAddressService, _commerceOrderService, commerceOrder,
				billingAddress, serviceContext);
		}

		// Shipping Address

		ShippingAddress shippingAddress = order.getShippingAddress();

		if (shippingAddress != null) {
			commerceOrder = ShippingAddressUtil.upsertShippingAddress(
				_commerceAddressService, _commerceOrderService, commerceOrder,
				shippingAddress, serviceContext);
		}

		return commerceOrder;
	}

	private CommerceOrder _updateOrder(CommerceOrder commerceOrder, Order order)
		throws Exception {

		CommerceChannel commerceChannel =
			_commerceChannelLocalService.getCommerceChannelByOrderGroupId(
				commerceOrder.getGroupId());

		long commerceShippingMethodId =
			commerceOrder.getCommerceShippingMethodId();

		CommerceShippingMethod commerceShippingMethod =
			_commerceShippingMethodService.fetchCommerceShippingMethod(
				commerceChannel.getSiteGroupId(), order.getShippingMethod());

		if (commerceShippingMethod != null) {
			commerceShippingMethodId =
				commerceShippingMethod.getCommerceShippingMethodId();
		}

		commerceOrder = _commerceOrderService.updateCommerceOrder(
			commerceOrder.getCommerceOrderId(),
			GetterUtil.get(
				order.getBillingAddressId(),
				commerceOrder.getBillingAddressId()),
			GetterUtil.get(
				order.getShippingAddressId(),
				commerceOrder.getShippingAddressId()),
			GetterUtil.get(
				order.getPaymentMethod(),
				commerceOrder.getCommercePaymentMethodKey()),
			commerceShippingMethodId,
			GetterUtil.get(
				order.getShippingOption(),
				commerceOrder.getShippingOptionName()),
			GetterUtil.get(
				order.getPurchaseOrderNumber(),
				commerceOrder.getPurchaseOrderNumber()),
			(BigDecimal)GetterUtil.get(
				order.getSubtotal(), commerceOrder.getSubtotal()),
			(BigDecimal)GetterUtil.get(
				order.getShippingAmount(), commerceOrder.getShippingAmount()),
			(BigDecimal)GetterUtil.get(
				order.getTotal(), commerceOrder.getTotal()),
			GetterUtil.get(
				order.getAdvanceStatus(), commerceOrder.getAdvanceStatus()),
			GetterUtil.get(
				order.getExternalReferenceCode(),
				commerceOrder.getExternalReferenceCode()),
			_commerceContextFactory.create(
				contextCompany.getCompanyId(), commerceChannel.getSiteGroupId(),
				_user.getUserId(), 0L, order.getAccountId()));

		// Expando

		Map<String, ?> customFields = order.getCustomFields();

		if ((customFields != null) && !customFields.isEmpty()) {
			ExpandoUtil.updateExpando(
				contextCompany.getCompanyId(), CommerceOrder.class,
				commerceOrder.getPrimaryKey(), customFields);
		}

		// Update nested resources

		commerceOrder = _updateNestedResources(
			order, commerceOrder,
			_serviceContextHelper.getServiceContext(
				commerceOrder.getGroupId()));

		return commerceOrder;
	}

	private CommerceOrder _upsertOrder(Order order) throws Exception {
		CommerceChannel commerceChannel =
			_commerceChannelLocalService.getCommerceChannel(
				order.getChannelId());

		long commerceShippingMethodId = 0;

		CommerceShippingMethod commerceShippingMethod =
			_commerceShippingMethodService.fetchCommerceShippingMethod(
				commerceChannel.getSiteGroupId(), order.getShippingMethod());

		if (commerceShippingMethod != null) {
			commerceShippingMethodId =
				commerceShippingMethod.getCommerceShippingMethodId();
		}

		CommerceCurrency commerceCurrency =
			_commerceCurrencyService.getCommerceCurrency(
				commerceChannel.getCompanyId(), order.getCurrencyCode());

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			commerceChannel.getGroupId());

		CommerceAccount commerceAccount = null;

		if (order.getAccountId() != null) {
			commerceAccount = _commerceAccountService.getCommerceAccount(
				order.getAccountId());
		}

		if (Validator.isNotNull(order.getAccountExternalReferenceCode())) {
			commerceAccount =
				_commerceAccountService.fetchByExternalReferenceCode(
					commerceChannel.getCompanyId(),
					order.getAccountExternalReferenceCode());
		}

		CommerceOrder commerceOrder = _commerceOrderService.upsertCommerceOrder(
			_user.getUserId(), commerceChannel.getGroupId(),
			commerceAccount.getCommerceAccountId(),
			commerceCurrency.getCommerceCurrencyId(),
			GetterUtil.get(order.getBillingAddressId(), 0L),
			GetterUtil.get(order.getShippingAddressId(), 0L),
			order.getPaymentMethod(), commerceShippingMethodId,
			order.getShippingOption(), order.getPurchaseOrderNumber(),
			order.getSubtotal(), order.getShippingAmount(), order.getTotal(),
			GetterUtil.get(
				order.getPaymentStatus(),
				CommerceOrderConstants.PAYMENT_STATUS_PENDING),
			GetterUtil.get(
				order.getOrderStatus(),
				CommerceOrderConstants.ORDER_STATUS_AWAITING_FULFILLMENT),
			order.getAdvanceStatus(), order.getExternalReferenceCode(),
			_commerceContextFactory.create(
				contextCompany.getCompanyId(), commerceChannel.getSiteGroupId(),
				_user.getUserId(), 0L, commerceAccount.getCommerceAccountId()),
			serviceContext);

		// Expando

		Map<String, ?> customFields = order.getCustomFields();

		if ((customFields != null) && !customFields.isEmpty()) {
			ExpandoUtil.updateExpando(
				contextCompany.getCompanyId(), CommerceOrder.class,
				commerceOrder.getPrimaryKey(), customFields);
		}

		// Update nested resources

		commerceOrder = _updateNestedResources(
			order, commerceOrder, serviceContext);

		return commerceOrder;
	}

	@Reference
	private CommerceAccountService _commerceAccountService;

	@Reference
	private CommerceAddressService _commerceAddressService;

	@Reference
	private CommerceChannelLocalService _commerceChannelLocalService;

	@Reference
	private CommerceContextFactory _commerceContextFactory;

	@Reference
	private CommerceCurrencyService _commerceCurrencyService;

	@Reference
	private CommerceOrderItemService _commerceOrderItemService;

	@Reference
	private CommerceOrderService _commerceOrderService;

	@Reference
	private CommerceShippingMethodService _commerceShippingMethodService;

	@Reference
	private CPInstanceService _cpInstanceService;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

	@Context
	private User _user;

}