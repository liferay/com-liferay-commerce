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

package com.liferay.commerce.product.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the CommerceChannelFilter service. Represents a row in the &quot;CommerceChannelFilter&quot; database table, with each column mapped to a property of this class.
 *
 * @author Marco Leo
 * @see CommerceChannelFilterModel
 * @see com.liferay.commerce.product.model.impl.CommerceChannelFilterImpl
 * @see com.liferay.commerce.product.model.impl.CommerceChannelFilterModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.commerce.product.model.impl.CommerceChannelFilterImpl")
@ProviderType
public interface CommerceChannelFilter extends CommerceChannelFilterModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.commerce.product.model.impl.CommerceChannelFilterImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CommerceChannelFilter, Long> COMMERCE_CHANNEL_FILTER_ID_ACCESSOR =
		new Accessor<CommerceChannelFilter, Long>() {
			@Override
			public Long get(CommerceChannelFilter commerceChannelFilter) {
				return commerceChannelFilter.getCommerceChannelFilterId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<CommerceChannelFilter> getTypeClass() {
				return CommerceChannelFilter.class;
			}
		};
}