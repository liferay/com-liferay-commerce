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

package com.liferay.commerce.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.constants.CommerceOrderConstants;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.model.CommerceMoney;
import com.liferay.commerce.currency.model.CommerceMoneyFactoryUtil;
import com.liferay.commerce.currency.service.CommerceCurrencyLocalServiceUtil;
import com.liferay.commerce.discount.CommerceDiscountValue;
import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.model.CommercePaymentMethod;
import com.liferay.commerce.model.CommerceShippingMethod;
import com.liferay.commerce.organization.service.CommerceOrganizationLocalServiceUtil;
import com.liferay.commerce.service.CommerceAddressLocalServiceUtil;
import com.liferay.commerce.service.CommerceOrderItemLocalServiceUtil;
import com.liferay.commerce.service.CommercePaymentMethodLocalServiceUtil;
import com.liferay.commerce.service.CommerceShippingMethodLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

import java.math.BigDecimal;

import java.util.List;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 * @author Andrea Di Giorgi
 * @author Ethan Bustad
 */
@ProviderType
public class CommerceOrderImpl extends CommerceOrderBaseImpl {

	public CommerceOrderImpl() {
	}

	@Override
	public CommerceAddress getBillingAddress() throws PortalException {
		long billingAddressId = getBillingAddressId();

		if (billingAddressId > 0) {
			return CommerceAddressLocalServiceUtil.getCommerceAddress(
				getBillingAddressId());
		}

		return null;
	}

	@Override
	public String getClassName() throws PortalException {
		Group group = GroupLocalServiceUtil.getGroup(getGroupId());

		if (group.isOrganization() &&
			CommerceOrganizationLocalServiceUtil.isB2BOrganization(
				group.getOrganizationId())) {

			return Organization.class.getName();
		}

		return User.class.getName();
	}

	@Override
	public long getClassPK() throws PortalException {
		Group group = GroupLocalServiceUtil.getGroup(getGroupId());

		if (group.isOrganization() &&
			CommerceOrganizationLocalServiceUtil.isB2BOrganization(
				group.getOrganizationId())) {

			return group.getOrganizationId();
		}

		return getOrderUserId();
	}

	@Override
	public CommerceCurrency getCommerceCurrency() throws PortalException {
		return CommerceCurrencyLocalServiceUtil.getCommerceCurrency(
			getCommerceCurrencyId());
	}

	@Override
	public List<CommerceOrderItem> getCommerceOrderItems() {
		return CommerceOrderItemLocalServiceUtil.getCommerceOrderItems(
			getCommerceOrderId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	@Override
	public List<CommerceOrderItem> getCommerceOrderItems(long cpInstanceId) {
		return CommerceOrderItemLocalServiceUtil.getCommerceOrderItems(
			getCommerceOrderId(), cpInstanceId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS);
	}

	@Override
	public CommercePaymentMethod getCommercePaymentMethod()
		throws PortalException {

		long commercePaymentMethodId = getCommercePaymentMethodId();

		if (commercePaymentMethodId > 0) {
			return
				CommercePaymentMethodLocalServiceUtil.getCommercePaymentMethod(
					commercePaymentMethodId);
		}

		return null;
	}

	@Override
	public CommerceShippingMethod getCommerceShippingMethod()
		throws PortalException {

		long commerceShippingMethodId = getCommerceShippingMethodId();

		if (commerceShippingMethodId > 0) {
			return CommerceShippingMethodLocalServiceUtil.
				getCommerceShippingMethod(commerceShippingMethodId);
		}

		return null;
	}

	@Override
	public long getCustomerId() {
		long customerId = getOrderOrganizationId();

		if (customerId <= 0) {
			customerId = getOrderUserId();
		}

		return customerId;
	}

	@Override
	public String getCustomerName() throws PortalException {
		Organization organization = getOrderOrganization();

		if (organization != null) {
			return organization.getName();
		}

		User orderUser = getOrderUser();

		if (orderUser.isDefaultUser()) {
			return RoleConstants.GUEST;
		}

		return orderUser.getFullName();
	}

	@Override
	public Organization getOrderOrganization() throws PortalException {
		long orderOrganizationId = getOrderOrganizationId();

		if (orderOrganizationId <= 0) {
			return null;
		}

		return OrganizationLocalServiceUtil.getOrganization(
			orderOrganizationId);
	}

	@Override
	public User getOrderUser() throws PortalException {
		long orderUserId = getOrderUserId();

		if (orderUserId <= 0) {
			return null;
		}

		return UserLocalServiceUtil.getUser(orderUserId);
	}

	@Override
	public CommerceAddress getShippingAddress() throws PortalException {
		long shippingAddressId = getShippingAddressId();

		if (shippingAddressId > 0) {
			return CommerceAddressLocalServiceUtil.getCommerceAddress(
				getShippingAddressId());
		}

		return null;
	}

	@Override
	public CommerceMoney getShippingMoney() throws PortalException {
		return CommerceMoneyFactoryUtil.create(
			getCommerceCurrencyId(), getShippingAmount());
	}

	@Override
	public CommerceMoney getSubtotalMoney() throws PortalException {
		return CommerceMoneyFactoryUtil.create(
			getCommerceCurrencyId(), getSubtotal());
	}

	@Override
	public CommerceMoney getTotalMoney() throws PortalException {
		return CommerceMoneyFactoryUtil.create(
			getCommerceCurrencyId(), getTotal());
	}

	@Override
	public boolean isB2B() throws PortalException {
		String className = getClassName();

		if (className.equals(Organization.class.getName())) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isEmpty() {
		if (CommerceOrderItemLocalServiceUtil.getCommerceOrderItemsCount(
				getCommerceOrderId()) > 0) {

			return false;
		}

		return true;
	}

	@Override
	public boolean isGuestOrder() throws PortalException {
		User orderUser = getOrderUser();

		if ((orderUser != null) && orderUser.isDefaultUser()) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isOpen() {
		if (getOrderStatus() == CommerceOrderConstants.ORDER_STATUS_OPEN) {
			return true;
		}

		return false;
	}

	@Override
	public void setShippingDiscounts(
		CommerceDiscountValue commerceDiscountValue) {

		BigDecimal shippingDiscountAmount = BigDecimal.ZERO;
		BigDecimal shippingDiscountPercentageLevel1 = BigDecimal.ZERO;
		BigDecimal shippingDiscountPercentageLevel2 = BigDecimal.ZERO;
		BigDecimal shippingDiscountPercentageLevel3 = BigDecimal.ZERO;
		BigDecimal shippingDiscountPercentageLevel4 = BigDecimal.ZERO;

		if (commerceDiscountValue != null) {
			CommerceMoney discountAmount =
				commerceDiscountValue.getDiscountAmount();

			BigDecimal[] percentages = commerceDiscountValue.getPercentages();

			shippingDiscountAmount = discountAmount.getPrice();

			if ((percentages != null) && (percentages.length > 0)) {
				shippingDiscountPercentageLevel1 = percentages[0];
			}

			if ((percentages != null) && (percentages.length > 1)) {
				shippingDiscountPercentageLevel1 = percentages[1];
			}

			if ((percentages != null) && (percentages.length > 2)) {
				shippingDiscountPercentageLevel1 = percentages[2];
			}

			if ((percentages != null) && (percentages.length > 3)) {
				shippingDiscountPercentageLevel1 = percentages[3];
			}
		}

		setShippingDiscountAmount(shippingDiscountAmount);
		setShippingDiscountPercentageLevel1(shippingDiscountPercentageLevel1);
		setShippingDiscountPercentageLevel2(shippingDiscountPercentageLevel2);
		setShippingDiscountPercentageLevel3(shippingDiscountPercentageLevel3);
		setShippingDiscountPercentageLevel4(shippingDiscountPercentageLevel4);
	}

	@Override
	public void setSubtotalDiscounts(
		CommerceDiscountValue commerceDiscountValue) {

		BigDecimal subtotalDiscountAmount = BigDecimal.ZERO;
		BigDecimal subtotalDiscountPercentageLevel1 = BigDecimal.ZERO;
		BigDecimal subtotalDiscountPercentageLevel2 = BigDecimal.ZERO;
		BigDecimal subtotalDiscountPercentageLevel3 = BigDecimal.ZERO;
		BigDecimal subtotalDiscountPercentageLevel4 = BigDecimal.ZERO;

		if (commerceDiscountValue != null) {
			CommerceMoney discountAmount =
				commerceDiscountValue.getDiscountAmount();

			BigDecimal[] percentages = commerceDiscountValue.getPercentages();

			subtotalDiscountAmount = discountAmount.getPrice();

			if ((percentages != null) && (percentages.length > 0)) {
				subtotalDiscountPercentageLevel1 = percentages[0];
			}

			if ((percentages != null) && (percentages.length > 1)) {
				subtotalDiscountPercentageLevel1 = percentages[1];
			}

			if ((percentages != null) && (percentages.length > 2)) {
				subtotalDiscountPercentageLevel1 = percentages[2];
			}

			if ((percentages != null) && (percentages.length > 3)) {
				subtotalDiscountPercentageLevel1 = percentages[3];
			}
		}

		setSubtotalDiscountAmount(subtotalDiscountAmount);
		setSubtotalDiscountPercentageLevel1(subtotalDiscountPercentageLevel1);
		setSubtotalDiscountPercentageLevel2(subtotalDiscountPercentageLevel2);
		setSubtotalDiscountPercentageLevel3(subtotalDiscountPercentageLevel3);
		setSubtotalDiscountPercentageLevel4(subtotalDiscountPercentageLevel4);
	}

	@Override
	public void setTotalDiscounts(CommerceDiscountValue commerceDiscountValue) {
		BigDecimal totalDiscountAmount = BigDecimal.ZERO;
		BigDecimal totalDiscountPercentageLevel1 = BigDecimal.ZERO;
		BigDecimal totalDiscountPercentageLevel2 = BigDecimal.ZERO;
		BigDecimal totalDiscountPercentageLevel3 = BigDecimal.ZERO;
		BigDecimal totalDiscountPercentageLevel4 = BigDecimal.ZERO;

		if (commerceDiscountValue != null) {
			CommerceMoney discountAmount =
				commerceDiscountValue.getDiscountAmount();

			BigDecimal[] percentages = commerceDiscountValue.getPercentages();

			totalDiscountAmount = discountAmount.getPrice();

			if ((percentages != null) && (percentages.length > 0)) {
				totalDiscountPercentageLevel1 = percentages[0];
			}

			if ((percentages != null) && (percentages.length > 1)) {
				totalDiscountPercentageLevel1 = percentages[1];
			}

			if ((percentages != null) && (percentages.length > 2)) {
				totalDiscountPercentageLevel1 = percentages[2];
			}

			if ((percentages != null) && (percentages.length > 3)) {
				totalDiscountPercentageLevel1 = percentages[3];
			}
		}

		setTotalDiscountAmount(totalDiscountAmount);
		setTotalDiscountPercentageLevel1(totalDiscountPercentageLevel1);
		setTotalDiscountPercentageLevel2(totalDiscountPercentageLevel2);
		setTotalDiscountPercentageLevel3(totalDiscountPercentageLevel3);
		setTotalDiscountPercentageLevel4(totalDiscountPercentageLevel4);
	}

}