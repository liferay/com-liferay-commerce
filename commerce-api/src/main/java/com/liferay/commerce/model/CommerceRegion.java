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
 * The extended model interface for the CommerceRegion service. Represents a row in the &quot;CommerceRegion&quot; database table, with each column mapped to a property of this class.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceRegionModel
 * @generated
 */
@ImplementationClassName("com.liferay.commerce.model.impl.CommerceRegionImpl")
@ProviderType
public interface CommerceRegion extends CommerceRegionModel, PersistedModel {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.commerce.model.impl.CommerceRegionImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CommerceRegion, Long>
		COMMERCE_REGION_ID_ACCESSOR = new Accessor<CommerceRegion, Long>() {

			@Override
			public Long get(CommerceRegion commerceRegion) {
				return commerceRegion.getCommerceRegionId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<CommerceRegion> getTypeClass() {
				return CommerceRegion.class;
			}

		};

	public CommerceCountry getCommerceCountry()
		throws com.liferay.portal.kernel.exception.PortalException;

}