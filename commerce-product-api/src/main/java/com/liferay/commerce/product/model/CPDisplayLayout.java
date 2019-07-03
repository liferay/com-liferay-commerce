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
 * The extended model interface for the CPDisplayLayout service. Represents a row in the &quot;CPDisplayLayout&quot; database table, with each column mapped to a property of this class.
 *
 * @author Marco Leo
 * @see CPDisplayLayoutModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.commerce.product.model.impl.CPDisplayLayoutImpl"
)
@ProviderType
public interface CPDisplayLayout extends CPDisplayLayoutModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.commerce.product.model.impl.CPDisplayLayoutImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CPDisplayLayout, Long>
		CP_DISPLAY_LAYOUT_ID_ACCESSOR = new Accessor<CPDisplayLayout, Long>() {

			@Override
			public Long get(CPDisplayLayout cpDisplayLayout) {
				return cpDisplayLayout.getCPDisplayLayoutId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<CPDisplayLayout> getTypeClass() {
				return CPDisplayLayout.class;
			}

		};

	public com.liferay.asset.kernel.model.AssetCategory fetchAssetCategory();

	public CPDefinition fetchCPDefinition();

	public com.liferay.portal.kernel.model.Layout fetchLayout();

}