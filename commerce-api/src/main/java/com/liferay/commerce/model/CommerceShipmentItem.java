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

package com.liferay.commerce.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the CommerceShipmentItem service. Represents a row in the &quot;CommerceShipmentItem&quot; database table, with each column mapped to a property of this class.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceShipmentItemModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.commerce.model.impl.CommerceShipmentItemImpl"
)
@ProviderType
public interface CommerceShipmentItem
	extends CommerceShipmentItemModel, PersistedModel {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.commerce.model.impl.CommerceShipmentItemImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CommerceShipmentItem, Long>
		COMMERCE_SHIPMENT_ITEM_ID_ACCESSOR =
			new Accessor<CommerceShipmentItem, Long>() {

				@Override
				public Long get(CommerceShipmentItem commerceShipmentItem) {
					return commerceShipmentItem.getCommerceShipmentItemId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<CommerceShipmentItem> getTypeClass() {
					return CommerceShipmentItem.class;
				}

			};

	public CommerceOrderItem fetchCommerceOrderItem();

	public CommerceShipment getCommerceShipment()
		throws com.liferay.portal.kernel.exception.PortalException;

}