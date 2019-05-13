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

package com.liferay.commerce.inventory.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the CommerceInventoryWarehouseGroupRel service. Represents a row in the &quot;CIWarehouseGroupRel&quot; database table, with each column mapped to a property of this class.
 *
 * @author Luca Pellizzon
 * @see CommerceInventoryWarehouseGroupRelModel
 * @see com.liferay.commerce.inventory.model.impl.CommerceInventoryWarehouseGroupRelImpl
 * @see com.liferay.commerce.inventory.model.impl.CommerceInventoryWarehouseGroupRelModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.commerce.inventory.model.impl.CommerceInventoryWarehouseGroupRelImpl")
@ProviderType
public interface CommerceInventoryWarehouseGroupRel
	extends CommerceInventoryWarehouseGroupRelModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.commerce.inventory.model.impl.CommerceInventoryWarehouseGroupRelImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CommerceInventoryWarehouseGroupRel, Long> COMMERCE_INVENTORY_WAREHOUSE_GROUP_REL_ID_ACCESSOR =
		new Accessor<CommerceInventoryWarehouseGroupRel, Long>() {
			@Override
			public Long get(
				CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel) {
				return commerceInventoryWarehouseGroupRel.getCommerceInventoryWarehouseGroupRelId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<CommerceInventoryWarehouseGroupRel> getTypeClass() {
				return CommerceInventoryWarehouseGroupRel.class;
			}
		};
}