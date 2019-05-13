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

package com.liferay.commerce.inventory.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.inventory.model.CommerceInventoryWarehouse;
import com.liferay.commerce.inventory.service.CommerceInventoryWarehouseLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * The extended model implementation for the CommerceInventoryWarehouseItem service. Represents a row in the &quot;CIWarehouseItem&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItem} interface.
 * </p>
 *
 * @author Luca Pellizzon
 */
@ProviderType
public class CommerceInventoryWarehouseItemImpl
	extends CommerceInventoryWarehouseItemBaseImpl {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a commerce inventory warehouse item model instance should use the {@link com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItem} interface instead.
	 */
	public CommerceInventoryWarehouseItemImpl() {
	}

	@Override
	public CommerceInventoryWarehouse getCommerceWarehouse()
		throws PortalException {

		return CommerceInventoryWarehouseLocalServiceUtil.
			getCommerceInventoryWarehouse(getCommerceWarehouseId());
	}

}