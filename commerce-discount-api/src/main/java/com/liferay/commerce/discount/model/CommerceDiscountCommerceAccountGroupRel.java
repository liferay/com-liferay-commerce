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

package com.liferay.commerce.discount.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the CommerceDiscountCommerceAccountGroupRel service. Represents a row in the &quot;CDiscountCAccountGroupRel&quot; database table, with each column mapped to a property of this class.
 *
 * @author Marco Leo
 * @see CommerceDiscountCommerceAccountGroupRelModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.commerce.discount.model.impl.CommerceDiscountCommerceAccountGroupRelImpl"
)
@ProviderType
public interface CommerceDiscountCommerceAccountGroupRel
	extends CommerceDiscountCommerceAccountGroupRelModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.commerce.discount.model.impl.CommerceDiscountCommerceAccountGroupRelImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CommerceDiscountCommerceAccountGroupRel, Long>
		COMMERCE_DISCOUNT_COMMERCE_ACCOUNT_GROUP_REL_ID_ACCESSOR =
			new Accessor<CommerceDiscountCommerceAccountGroupRel, Long>() {

				@Override
				public Long get(
					CommerceDiscountCommerceAccountGroupRel
						commerceDiscountCommerceAccountGroupRel) {

					return commerceDiscountCommerceAccountGroupRel.
						getCommerceDiscountCommerceAccountGroupRelId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<CommerceDiscountCommerceAccountGroupRel>
					getTypeClass() {

					return CommerceDiscountCommerceAccountGroupRel.class;
				}

			};

	public com.liferay.commerce.account.model.CommerceAccountGroup
			getCommerceAccountGroup()
		throws com.liferay.portal.kernel.exception.PortalException;

	public CommerceDiscount getCommerceDiscount()
		throws com.liferay.portal.kernel.exception.PortalException;

}