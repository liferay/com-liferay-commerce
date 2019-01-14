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

package com.liferay.commerce.price;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.currency.model.CommerceMoney;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
@ProviderType
public interface CommerceOrderPriceCalculation {

	public CommerceOrderPrice getCommerceOrderPrice(
			CommerceOrder commerceOrder, boolean secure,
			CommerceContext commerceContext)
		throws PortalException;

	public CommerceOrderPrice getCommerceOrderPrice(
			CommerceOrder commerceOrder, CommerceContext commerceContext)
		throws PortalException;

	public CommerceMoney getSubtotal(
			CommerceOrder commerceOrder, boolean secure,
			CommerceContext commerceContext)
		throws PortalException;

	public CommerceMoney getSubtotal(
			CommerceOrder commerceOrder, CommerceContext commerceContext)
		throws PortalException;

	public CommerceMoney getTaxValue(
			CommerceOrder commerceOrder, boolean secure,
			CommerceContext commerceContext)
		throws PortalException;

	public CommerceMoney getTaxValue(
			CommerceOrder commerceOrder, CommerceContext commerceContext)
		throws PortalException;

	public CommerceMoney getTotal(
			CommerceOrder commerceOrder, boolean secure,
			CommerceContext commerceContext)
		throws PortalException;

	public CommerceMoney getTotal(
			CommerceOrder commerceOrder, CommerceContext commerceContext)
		throws PortalException;

}