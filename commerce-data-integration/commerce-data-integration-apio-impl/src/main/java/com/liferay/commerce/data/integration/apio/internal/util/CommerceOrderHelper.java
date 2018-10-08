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

package com.liferay.commerce.data.integration.apio.internal.util;

import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.service.CommerceCurrencyService;
import com.liferay.commerce.data.integration.apio.identifiers.ClassPKExternalReferenceCode;
import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceRegion;
import com.liferay.commerce.organization.service.CommerceOrganizationService;
import com.liferay.commerce.service.CommerceOrderLocalService;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.Locale;

import javax.ws.rs.NotFoundException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rodrigo Guedes de Souza
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = CommerceOrderHelper.class)
public class CommerceOrderHelper {

	public static String getBillingAddressCity(CommerceOrder commerceOrder) {
		CommerceAddress commerceAddress = _getBillingAddress(commerceOrder);

		if (commerceAddress != null) {
			return commerceAddress.getCity();
		}

		return null;
	}

	public static String getBillingAddressCountry(
		CommerceOrder commerceOrder, Locale locale) {

		CommerceAddress commerceAddress = _getBillingAddress(commerceOrder);

		if (commerceAddress != null) {
			CommerceCountry commerceCountry = _getCommerceCountry(
				commerceAddress);

			if (commerceCountry != null) {
				return commerceCountry.getName(locale);
			}
		}

		return null;
	}

	public static String getBillingAddressState(CommerceOrder commerceOrder) {
		CommerceAddress commerceAddress = _getBillingAddress(commerceOrder);

		if (commerceAddress != null) {
			CommerceRegion commerceRegion = _getCommerceRegion(commerceAddress);

			if (commerceRegion != null) {
				return commerceRegion.getName();
			}
		}

		return null;
	}

	public static String getBillingAddressStreet(CommerceOrder commerceOrder) {
		CommerceAddress commerceAddress = _getBillingAddress(commerceOrder);

		if (commerceAddress != null) {
			return commerceAddress.getStreet1();
		}

		return null;
	}

	public static String getBillingAddressZip(CommerceOrder commerceOrder) {
		CommerceAddress commerceAddress = _getBillingAddress(commerceOrder);

		if (commerceAddress != null) {
			return commerceAddress.getZip();
		}

		return null;
	}

	public static String getShippingAddressCity(CommerceOrder commerceOrder) {
		CommerceAddress commerceAddress = _getShippingAddress(commerceOrder);

		if (commerceAddress != null) {
			return commerceAddress.getCity();
		}

		return null;
	}

	public static String getShippingAddressCountry(
		CommerceOrder commerceOrder, Locale locale) {

		CommerceAddress commerceAddress = _getShippingAddress(commerceOrder);

		if (commerceAddress != null) {
			CommerceCountry commerceCountry = _getCommerceCountry(
				commerceAddress);

			if (commerceCountry != null) {
				return commerceCountry.getName(locale);
			}
		}

		return null;
	}

	public static String getShippingAddressState(CommerceOrder commerceOrder) {
		CommerceAddress commerceAddress = _getShippingAddress(commerceOrder);

		if (commerceAddress != null) {
			CommerceRegion commerceRegion = _getCommerceRegion(commerceAddress);

			if (commerceRegion != null) {
				return commerceRegion.getName();
			}
		}

		return null;
	}

	public static String getShippingAddressStreet(CommerceOrder commerceOrder) {
		CommerceAddress commerceAddress = _getShippingAddress(commerceOrder);

		if (commerceAddress != null) {
			return commerceAddress.getStreet1();
		}

		return null;
	}

	public static String getShippingAddressZip(CommerceOrder commerceOrder) {
		CommerceAddress commerceAddress = _getShippingAddress(commerceOrder);

		if (commerceAddress != null) {
			return commerceAddress.getZip();
		}

		return null;
	}

	public ClassPKExternalReferenceCode
		commerceOrderIdToClassPKExternalReferenceCode(long commerceOrderId) {

		CommerceOrder commerceOrder =
			_commerceOrderLocalService.fetchCommerceOrder(commerceOrderId);

		return commerceOrderToClassPKExternalReferenceCode(commerceOrder);
	}

	public ClassPKExternalReferenceCode
		commerceOrderToClassPKExternalReferenceCode(
			CommerceOrder commerceOrder) {

		if (commerceOrder != null) {
			return ClassPKExternalReferenceCode.create(
				commerceOrder.getCommerceOrderId(),
				commerceOrder.getExternalReferenceCode());
		}

		return null;
	}

	public CommerceOrder getCommerceOrderByClassPKExternalReferenceCode(
			ClassPKExternalReferenceCode
				commerceOrderClassPKExternalReferenceCode)
		throws PortalException {

		long commerceOrderId =
			commerceOrderClassPKExternalReferenceCode.getClassPK();

		if (commerceOrderId > 0) {
			CommerceOrder commerceOrderItem =
				_commerceOrderLocalService.getCommerceOrder(commerceOrderId);

			if (commerceOrderItem == null) {
				throw new NotFoundException(
					"Unable to find order with ID " + commerceOrderId);
			}

			return commerceOrderItem;
		}
		else {
			String externalReferenceCode =
				commerceOrderClassPKExternalReferenceCode.
					getExternalReferenceCode();

			return _commerceOrderLocalService.fetchByExternalReferenceCode(
				CompanyThreadLocal.getCompanyId(), externalReferenceCode);
		}
	}

	public CommerceOrder updateCommerceOrder(
			ClassPKExternalReferenceCode
				commerceOrderClassPKExternalReferenceCode,
			Long orderStatus, Long paymentStatus, String externalReferenceCode)
		throws PortalException {

		CommerceOrder commerceOrder =
			getCommerceOrderByClassPKExternalReferenceCode(
				commerceOrderClassPKExternalReferenceCode);

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			commerceOrder.getGroupId());

		_commerceOrderService.updateCommerceOrder(
			commerceOrder.getCommerceOrderId(),
			commerceOrder.getBillingAddressId(),
			commerceOrder.getShippingAddressId(),
			commerceOrder.getCommercePaymentMethodId(),
			commerceOrder.getCommerceShippingMethodId(),
			commerceOrder.getShippingOptionName(),
			commerceOrder.getPurchaseOrderNumber(), commerceOrder.getSubtotal(),
			commerceOrder.getShippingAmount(), commerceOrder.getTotal(),
			commerceOrder.getAdvanceStatus(), externalReferenceCode, null);

		if (orderStatus != null) {
			_commerceOrderService.updateOrderStatus(
				commerceOrder.getCommerceOrderId(), orderStatus.intValue());
		}

		if (paymentStatus != null) {
			_commerceOrderService.updatePaymentStatus(
				commerceOrder.getCommerceOrderId(), paymentStatus.intValue(),
				serviceContext);
		}

		return _commerceOrderService.getCommerceOrder(
			commerceOrder.getCommerceOrderId());
	}

	public CommerceOrder upsertCommerceOrder(
			long groupId, long orderOrganizationId, long orderUserId,
			String currency, long shippingAddressId, String purchaseOrderNumber,
			User currentUser)
		throws PortalException {

		long commerceCurrencyId = _getCommerceCurrencyId(groupId, currency);

		if (orderOrganizationId > 0) {
			Organization organization =
				_commerceOrganizationService.getOrganization(
					orderOrganizationId);

			return _commerceOrderService.addOrganizationCommerceOrder(
				organization.getGroupId(), groupId,
				organization.getOrganizationId(), commerceCurrencyId,
				shippingAddressId, purchaseOrderNumber);
		}

		return _commerceOrderService.addUserCommerceOrder(
			groupId, currentUser.getUserId(), orderUserId, commerceCurrencyId);
	}

	private static CommerceAddress _getBillingAddress(
		CommerceOrder commerceOrder) {

		try {
			return commerceOrder.getBillingAddress();
		}
		catch (PortalException pe) {
			_log.error(
				"Unable to find billing address with ID " +
					commerceOrder.getBillingAddressId(),
				pe);
		}

		return null;
	}

	private static CommerceCountry _getCommerceCountry(
		CommerceAddress commerceAddress) {

		try {
			return commerceAddress.getCommerceCountry();
		}
		catch (PortalException pe) {
			_log.error(
				"Unable to find country with ID " +
					commerceAddress.getCommerceCountryId(),
				pe);
		}

		return null;
	}

	private static CommerceRegion _getCommerceRegion(
		CommerceAddress commerceAddress) {

		try {
			return commerceAddress.getCommerceRegion();
		}
		catch (PortalException pe) {
			_log.error(
				"Unable to find region with ID " +
					commerceAddress.getCommerceRegionId(),
				pe);
		}

		return null;
	}

	private static CommerceAddress _getShippingAddress(
		CommerceOrder commerceOrder) {

		try {
			return commerceOrder.getShippingAddress();
		}
		catch (PortalException pe) {
			_log.error(
				"Unable to find shipping address with ID " +
					commerceOrder.getBillingAddressId(),
				pe);
		}

		return null;
	}

	private long _getCommerceCurrencyId(Long groupId, String currencyCode)
		throws PortalException {

		CommerceCurrency commerceCurrency =
			_commerceCurrencyService.getCommerceCurrency(groupId, currencyCode);

		return commerceCurrency.getCommerceCurrencyId();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceOrderHelper.class);

	@Reference
	private CommerceCurrencyService _commerceCurrencyService;

	@Reference
	private CommerceOrderLocalService _commerceOrderLocalService;

	@Reference
	private CommerceOrderService _commerceOrderService;

	@Reference
	private CommerceOrganizationService _commerceOrganizationService;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}