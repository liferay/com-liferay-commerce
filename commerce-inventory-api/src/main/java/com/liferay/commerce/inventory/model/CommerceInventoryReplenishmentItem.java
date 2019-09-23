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
 * The extended model interface for the CommerceInventoryReplenishmentItem service. Represents a row in the &quot;CIReplenishmentItem&quot; database table, with each column mapped to a property of this class.
 *
 * @author Luca Pellizzon
 * @see CommerceInventoryReplenishmentItemModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.commerce.inventory.model.impl.CommerceInventoryReplenishmentItemImpl"
)
@ProviderType
public interface CommerceInventoryReplenishmentItem
	extends CommerceInventoryReplenishmentItemModel, PersistedModel {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.commerce.inventory.model.impl.CommerceInventoryReplenishmentItemImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CommerceInventoryReplenishmentItem, Long>
		COMMERCE_INVENTORY_REPLENISHMENT_ITEM_ID_ACCESSOR =
			new Accessor<CommerceInventoryReplenishmentItem, Long>() {

				@Override
				public Long get(
					CommerceInventoryReplenishmentItem
						commerceInventoryReplenishmentItem) {

					return commerceInventoryReplenishmentItem.
						getCommerceInventoryReplenishmentItemId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<CommerceInventoryReplenishmentItem>
					getTypeClass() {

					return CommerceInventoryReplenishmentItem.class;
				}

			};

}