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

package com.liferay.commerce.shipping.engine.fixed.service.persistence;

import aQute.bnd.annotation.ProviderType;

/**
 * @author Alessio Antonio Rendina
 * @generated
 */
@ProviderType
public interface CommerceShippingFixedOptionRelFinder {

	public com.liferay.commerce.shipping.engine.fixed.model.
		CommerceShippingFixedOptionRel fetchByC_C_C_Z_W_First(
			long commerceShippingFixedOptionId, long commerceCountryId,
			long commerceRegionId, String zip, double weight);

	public java.util.List
		<com.liferay.commerce.shipping.engine.fixed.model.
			CommerceShippingFixedOptionRel> findByC_C_C_Z_W(
				long commerceShippingFixedOptionId, long commerceCountryId,
				long commerceRegionId, String zip, double weight);

	public java.util.List
		<com.liferay.commerce.shipping.engine.fixed.model.
			CommerceShippingFixedOptionRel> findByC_C_C_Z_W(
				long commerceShippingFixedOptionId, long commerceCountryId,
				long commerceRegionId, String zip, double weight, int start,
				int end);

}