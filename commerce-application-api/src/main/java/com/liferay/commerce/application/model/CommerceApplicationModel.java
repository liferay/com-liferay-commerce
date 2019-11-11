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
 * The extended model interface for the CommerceApplicationModel service. Represents a row in the &quot;CommerceApplicationModel&quot; database table, with each column mapped to a property of this class.
 *
 * @author Luca Pellizzon
 * @see CommerceApplicationModelModel
 * @see com.liferay.commerce.application.model.impl.CommerceApplicationModelImpl
 * @see com.liferay.commerce.application.model.impl.CommerceApplicationModelModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.commerce.application.model.impl.CommerceApplicationModelImpl")
@ProviderType
public interface CommerceApplicationModel extends CommerceApplicationModelModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.commerce.application.model.impl.CommerceApplicationModelImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CommerceApplicationModel, Long> COMMERCE_APPLICATION_MODEL_ID_ACCESSOR =
		new Accessor<CommerceApplicationModel, Long>() {
			@Override
			public Long get(CommerceApplicationModel commerceApplicationModel) {
				return commerceApplicationModel.getCommerceApplicationModelId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<CommerceApplicationModel> getTypeClass() {
				return CommerceApplicationModel.class;
			}
		};
}