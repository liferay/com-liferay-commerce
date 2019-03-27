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

package com.liferay.commerce.product.internal.catalog;

import com.liferay.commerce.product.catalog.CPSku;
import com.liferay.commerce.product.model.CPInstance;

import java.math.BigDecimal;

/**
 * @author Alessio Antonio Rendina
 */
public class CPSkuImpl implements CPSku {

	public CPSkuImpl(CPInstance cpInstance) {
		_cpInstance = cpInstance;
	}

	@Override
	public long getCPInstanceId() {
		return _cpInstance.getCPInstanceId();
	}

	@Override
	public String getCPInstanceUuid() {
		return _cpInstance.getCPInstanceUuid();
	}

	@Override
	public String getExternalReferenceCode() {
		return _cpInstance.getExternalReferenceCode();
	}

	@Override
	public String getGtin() {
		return _cpInstance.getGtin();
	}

	@Override
	public String getManufacturerPartNumber() {
		return _cpInstance.getManufacturerPartNumber();
	}

	@Override
	public BigDecimal getPrice() {
		return _cpInstance.getPrice();
	}

	@Override
	public BigDecimal getPromoPrice() {
		return _cpInstance.getPromoPrice();
	}

	@Override
	public String getSku() {
		return _cpInstance.getSku();
	}

	@Override
	public boolean isPublished() {
		return _cpInstance.isPublished();
	}

	@Override
	public boolean isPurchasable() {
		return _cpInstance.isPurchasable();
	}

	private final CPInstance _cpInstance;

}