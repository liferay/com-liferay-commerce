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

package com.liferay.commerce.application.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the CommerceApplicationBrand service. Represents a row in the &quot;CommerceApplicationBrand&quot; database table, with each column mapped to a property of this class.
 *
 * @author Luca Pellizzon
 * @see CommerceApplicationBrandModel
 * @see com.liferay.commerce.application.model.impl.CommerceApplicationBrandImpl
 * @see com.liferay.commerce.application.model.impl.CommerceApplicationBrandModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.commerce.application.model.impl.CommerceApplicationBrandImpl")
@ProviderType
public interface CommerceApplicationBrand extends CommerceApplicationBrandModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.commerce.application.model.impl.CommerceApplicationBrandImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CommerceApplicationBrand, Long> COMMERCE_APPLICATION_BRAND_ID_ACCESSOR =
		new Accessor<CommerceApplicationBrand, Long>() {
			@Override
			public Long get(CommerceApplicationBrand commerceApplicationBrand) {
				return commerceApplicationBrand.getCommerceApplicationBrandId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<CommerceApplicationBrand> getTypeClass() {
				return CommerceApplicationBrand.class;
			}
		};
}