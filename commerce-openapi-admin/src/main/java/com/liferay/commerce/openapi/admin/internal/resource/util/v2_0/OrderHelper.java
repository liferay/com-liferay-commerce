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

package com.liferay.commerce.openapi.admin.internal.resource.util.v2_0;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.service.CommerceCurrencyService;
import com.liferay.commerce.exception.NoSuchOrderException;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceShippingMethod;
import com.liferay.commerce.openapi.admin.internal.resource.util.ServiceContextHelper;
import com.liferay.commerce.openapi.admin.internal.util.v2_0.DTOUtils;
import com.liferay.commerce.openapi.admin.model.v2_0.AddressDTO;
import com.liferay.commerce.openapi.admin.model.v2_0.OrderDTO;
import com.liferay.commerce.openapi.core.context.Language;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;
import com.liferay.commerce.openapi.core.util.IdUtils;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.commerce.service.CommerceShippingMethodService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.math.BigDecimal;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = OrderHelper.class)
public class OrderHelper {

	public static String getCurrencyCode(CommerceOrder commerceOrder)
		throws PortalException {

		return Optional.of(
			commerceOrder.getCommerceCurrency()
		).map(
			CommerceCurrency::getCode
		).orElse(
			null
		);
	}

	public void deleteOrder(String id, Company company) throws PortalException {
		CommerceOrder commerceOrder = null;

		try {
			commerceOrder = getOrderById(id, company);
		}
		catch (NoSuchOrderException nsoe) {
			if (_log.isDebugEnabled()) {
				_log.debug("Order not exist with ID: " + id, nsoe);
			}

			return;
		}

		_commerceOrderService.deleteCommerceOrder(
			commerceOrder.getCommerceOrderId());
	}

	public OrderDTO getOrder(String id, Language language, Company company)
		throws PortalException {

		return DTOUtils.modelToDTO(
			getOrderById(id, company), language.getLanguageId());
	}

	public CommerceOrder getOrderById(String id, Company company)
		throws PortalException {

		CommerceOrder commerceOrder = null;

		if (IdUtils.isLocalPK(id)) {
			commerceOrder = _commerceOrderService.fetchCommerceOrder(
				GetterUtil.getLong(id));
		}
		else {

			// Get Order by External Reference Code

			String erc = IdUtils.getExternalReferenceCodeFromId(id);

			commerceOrder = _commerceOrderService.fetchByExternalReferenceCode(
				company.getCompanyId(), erc);
		}

		if (commerceOrder == null) {
			throw new NoSuchOrderException(
				"Unable to find Order with ID: " + id);
		}

		return commerceOrder;
	}

	public CollectionDTO<OrderDTO> getOrders(
			String id, long groupId, Language language, Pagination pagination,
			Company company)
		throws PortalException {

		List<CommerceOrder> commerceOrders;
		int totalItems;

		if (Validator.isNull(id)) {
			commerceOrders = _commerceOrderService.getCommerceOrders(
				groupId, pagination.getStartPosition(),
				pagination.getEndPosition(), null);
			totalItems = _commerceOrderService.getCommerceOrdersCount(groupId);
		}
		else {
			CommerceAccount commerceAccount = _accountHelper.getAccountById(
				id, company);

			commerceOrders = _commerceOrderService.getCommerceOrders(
				groupId, commerceAccount.getCommerceAccountId(),
				pagination.getStartPosition(), pagination.getEndPosition(),
				null);
			totalItems = _commerceOrderService.getCommerceOrdersCount(
				groupId, commerceAccount.getCommerceAccountId());
		}

		Stream<CommerceOrder> stream = commerceOrders.stream();

		return stream.map(
			commerceOrder -> DTOUtils.modelToDTO(
				commerceOrder, language.getLanguageId())
		).collect(
			Collectors.collectingAndThen(
				Collectors.toList(),
				orderDTOs ->
					new CollectionDTO<>(orderDTOs, totalItems))
		);
	}

	public OrderDTO updateOrder(
			String id, OrderDTO orderDTO, Language language, Company company,
			CommerceContext commerceContext)
		throws PortalException {

		return DTOUtils.modelToDTO(
			_updateOrder(
				id, company, orderDTO.getBillingAddressId(),
				orderDTO.getShippingAddressId(), orderDTO.getPaymentMethod(),
				orderDTO.getShippingMethod(), orderDTO.getShippingOption(),
				orderDTO.getPurchaseOrderNumber(), orderDTO.getSubtotal(),
				orderDTO.getShippingAmount(), orderDTO.getTotal(),
				orderDTO.getAdvanceStatus(), commerceContext),
			language.getLanguageId());
	}

	public OrderDTO updateOrderBillingAddress(
			String id, AddressDTO addressDTO, Language language,
			Company company)
		throws PortalException {

		return DTOUtils.modelToDTO(
			_updateOrderBillingAddress(
				id, company, addressDTO.getName(), addressDTO.getDescription(),
				addressDTO.getStreet1(), addressDTO.getStreet2(),
				addressDTO.getStreet3(), addressDTO.getCity(),
				addressDTO.getZip(), addressDTO.getCommerceRegionId(),
				addressDTO.getCommerceCountryId(), addressDTO.getPhoneNumber()),
			language.getLanguageId());
	}

	public OrderDTO updateOrderShippingAddress(
			String id, AddressDTO addressDTO, Language language,
			Company company)
		throws PortalException {

		return DTOUtils.modelToDTO(
			_updateOrderShippingAddress(
				id, company, addressDTO.getName(), addressDTO.getDescription(),
				addressDTO.getStreet1(), addressDTO.getStreet2(),
				addressDTO.getStreet3(), addressDTO.getCity(),
				addressDTO.getZip(), addressDTO.getCommerceRegionId(),
				addressDTO.getCommerceCountryId(), addressDTO.getPhoneNumber()),
			language.getLanguageId());
	}

	public OrderDTO upsertOrder(
			String id, long groupId, OrderDTO orderDTO, Language language,
			Company company, CommerceContext commerceContext)
		throws PortalException {

		CommerceAccount commerceAccount = _accountHelper.getAccountById(
			id, company);

		return DTOUtils.modelToDTO(
			_upsertOrder(
				groupId, commerceAccount.getCommerceAccountId(),
				orderDTO.getCurrency(), orderDTO.getBillingAddressId(),
				orderDTO.getShippingAddressId(), orderDTO.getPaymentMethod(),
				orderDTO.getShippingMethod(), orderDTO.getShippingOption(),
				orderDTO.getPurchaseOrderNumber(), orderDTO.getSubtotal(),
				orderDTO.getShippingAmount(), orderDTO.getTotal(),
				orderDTO.getPaymentStatus(), orderDTO.getOrderStatus(),
				orderDTO.getAdvanceStatus(),
				orderDTO.getExternalReferenceCode(), commerceContext),
			language.getLanguageId());
	}

	private long _getCommerceCurrencyId(Long groupId, String currencyCode)
		throws PortalException {

		CommerceCurrency commerceCurrency =
			_commerceCurrencyService.getCommerceCurrency(groupId, currencyCode);

		return commerceCurrency.getCommerceCurrencyId();
	}

	private long _getCommerceShippingMethodId(
			long groupId, String commerceShippingMethodKey)
		throws PortalException {

		CommerceShippingMethod commerceShippingMethod =
			_commerceShippingMethodService.fetchCommerceShippingMethod(
				groupId, commerceShippingMethodKey);

		return commerceShippingMethod.getCommerceShippingMethodId();
	}

	private CommerceOrder _updateOrder(
			String id, Company company, long billingAddressId,
			long shippingAddressId, String commercePaymentMethodKey,
			String commerceShippingMethodKey, String shippingOptionName,
			String purchaseOrderNumber, BigDecimal subtotal,
			BigDecimal shippingAmount, BigDecimal total, String advanceStatus,
			CommerceContext commerceContext)
		throws PortalException {

		CommerceOrder commerceOrder = getOrderById(id, company);

		return _commerceOrderService.updateCommerceOrder(
			commerceOrder.getCommerceOrderId(), billingAddressId,
			shippingAddressId, commercePaymentMethodKey,
			_getCommerceShippingMethodId(
				commerceOrder.getGroupId(), commerceShippingMethodKey),
			shippingOptionName, purchaseOrderNumber, subtotal, shippingAmount,
			total, advanceStatus, commerceContext);
	}

	private CommerceOrder _updateOrderBillingAddress(
			String id, Company company, String name, String description,
			String street1, String street2, String street3, String city,
			String zip, long commerceRegionId, long commerceCountryId,
			String phoneNumber)
		throws PortalException {

		CommerceOrder commerceOrder = getOrderById(id, company);

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			commerceOrder.getGroupId());

		return _commerceOrderService.updateBillingAddress(
			commerceOrder.getCommerceOrderId(), name, description, street1,
			street2, street3, city, zip, commerceRegionId, commerceCountryId,
			phoneNumber, serviceContext);
	}

	private CommerceOrder _updateOrderShippingAddress(
			String id, Company company, String name, String description,
			String street1, String street2, String street3, String city,
			String zip, long commerceRegionId, long commerceCountryId,
			String phoneNumber)
		throws PortalException {

		CommerceOrder commerceOrder = getOrderById(id, company);

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			commerceOrder.getGroupId());

		return _commerceOrderService.updateShippingAddress(
			commerceOrder.getCommerceOrderId(), name, description, street1,
			street2, street3, city, zip, commerceRegionId, commerceCountryId,
			phoneNumber, serviceContext);
	}

	private CommerceOrder _upsertOrder(
			Long groupId, Long commerceAccountId, String currency,
			long billingAddressId, long shippingAddressId,
			String commercePaymentMethodKey, String commerceShippingMethodKey,
			String shippingOptionName, String purchaseOrderNumber,
			BigDecimal subtotal, BigDecimal shippingAmount, BigDecimal total,
			int paymentStatus, int orderStatus, String advanceStatus,
			String externalReferenceCode, CommerceContext commerceContext)
		throws PortalException {

		long commerceCurrencyId = _getCommerceCurrencyId(groupId, currency);

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			groupId);

		CommerceOrder commerceOrder = _commerceOrderService.upsertCommerceOrder(
			commerceAccountId, commerceCurrencyId, billingAddressId,
			shippingAddressId, commercePaymentMethodKey,
			_getCommerceShippingMethodId(groupId, commerceShippingMethodKey),
			shippingOptionName, purchaseOrderNumber, subtotal, shippingAmount,
			total, paymentStatus, orderStatus, advanceStatus,
			externalReferenceCode, commerceContext, serviceContext);

		return commerceOrder;
	}

	private static final Log _log = LogFactoryUtil.getLog(OrderHelper.class);

	@Reference
	private AccountHelper _accountHelper;

	@Reference
	private CommerceCurrencyService _commerceCurrencyService;

	@Reference
	private CommerceOrderService _commerceOrderService;

	@Reference
	private CommerceShippingMethodService _commerceShippingMethodService;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}