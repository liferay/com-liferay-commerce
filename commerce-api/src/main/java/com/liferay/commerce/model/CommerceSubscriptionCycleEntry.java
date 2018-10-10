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
 * The extended model interface for the CommerceSubscriptionCycleEntry service. Represents a row in the &quot;CSubscriptionCycleEntry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceSubscriptionCycleEntryModel
 * @see com.liferay.commerce.model.impl.CommerceSubscriptionCycleEntryImpl
 * @see com.liferay.commerce.model.impl.CommerceSubscriptionCycleEntryModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.commerce.model.impl.CommerceSubscriptionCycleEntryImpl")
@ProviderType
public interface CommerceSubscriptionCycleEntry
	extends CommerceSubscriptionCycleEntryModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.commerce.model.impl.CommerceSubscriptionCycleEntryImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CommerceSubscriptionCycleEntry, Long> COMMERCE_SUBSCRIPTION_CYCLE_ENTRY_ID_ACCESSOR =
		new Accessor<CommerceSubscriptionCycleEntry, Long>() {
			@Override
			public Long get(
				CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry) {
				return commerceSubscriptionCycleEntry.getCommerceSubscriptionCycleEntryId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<CommerceSubscriptionCycleEntry> getTypeClass() {
				return CommerceSubscriptionCycleEntry.class;
			}
		};

	public CommerceSubscriptionEntry getCommerceSubscriptionEntry()
		throws com.liferay.portal.kernel.exception.PortalException;
}