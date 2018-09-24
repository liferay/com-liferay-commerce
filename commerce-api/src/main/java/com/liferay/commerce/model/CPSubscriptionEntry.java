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
 * The extended model interface for the CPSubscriptionEntry service. Represents a row in the &quot;CPSubscriptionEntry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Alessio Antonio Rendina
 * @see CPSubscriptionEntryModel
 * @see com.liferay.commerce.model.impl.CPSubscriptionEntryImpl
 * @see com.liferay.commerce.model.impl.CPSubscriptionEntryModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.commerce.model.impl.CPSubscriptionEntryImpl")
@ProviderType
public interface CPSubscriptionEntry extends CPSubscriptionEntryModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.commerce.model.impl.CPSubscriptionEntryImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CPSubscriptionEntry, Long> CP_SUBSCRIPTION_ENTRY_ID_ACCESSOR =
		new Accessor<CPSubscriptionEntry, Long>() {
			@Override
			public Long get(CPSubscriptionEntry cpSubscriptionEntry) {
				return cpSubscriptionEntry.getCPSubscriptionEntryId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<CPSubscriptionEntry> getTypeClass() {
				return CPSubscriptionEntry.class;
			}
		};

	public CommerceOrderItem fetchCommerceOrderItem();

	public com.liferay.commerce.product.model.CPDefinition getCPDefinition()
		throws com.liferay.portal.kernel.exception.PortalException;

	public long getCPDefinitionId()
		throws com.liferay.portal.kernel.exception.PortalException;

	public com.liferay.commerce.product.model.CPInstance getCPInstance()
		throws com.liferay.portal.kernel.exception.PortalException;
}