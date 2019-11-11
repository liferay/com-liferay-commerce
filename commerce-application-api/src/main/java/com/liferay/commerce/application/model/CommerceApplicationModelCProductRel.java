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
 * The extended model interface for the CommerceApplicationModelCProductRel service. Represents a row in the &quot;CAModelCProductRel&quot; database table, with each column mapped to a property of this class.
 *
 * @author Luca Pellizzon
 * @see CommerceApplicationModelCProductRelModel
 * @see com.liferay.commerce.application.model.impl.CommerceApplicationModelCProductRelImpl
 * @see com.liferay.commerce.application.model.impl.CommerceApplicationModelCProductRelModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.commerce.application.model.impl.CommerceApplicationModelCProductRelImpl")
@ProviderType
public interface CommerceApplicationModelCProductRel
	extends CommerceApplicationModelCProductRelModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.commerce.application.model.impl.CommerceApplicationModelCProductRelImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CommerceApplicationModelCProductRel, Long> COMMERCE_APPLICATION_MODEL_C_PRODUCT_REL_ID_ACCESSOR =
		new Accessor<CommerceApplicationModelCProductRel, Long>() {
			@Override
			public Long get(
				CommerceApplicationModelCProductRel commerceApplicationModelCProductRel) {
				return commerceApplicationModelCProductRel.getCommerceApplicationModelCProductRelId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<CommerceApplicationModelCProductRel> getTypeClass() {
				return CommerceApplicationModelCProductRel.class;
			}
		};
}