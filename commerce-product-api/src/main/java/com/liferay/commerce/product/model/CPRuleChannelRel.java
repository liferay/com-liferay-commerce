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
 * The extended model interface for the CPRuleChannelRel service. Represents a row in the &quot;CPRuleChannelRel&quot; database table, with each column mapped to a property of this class.
 *
 * @author Marco Leo
 * @see CPRuleChannelRelModel
 * @see com.liferay.commerce.product.model.impl.CPRuleChannelRelImpl
 * @see com.liferay.commerce.product.model.impl.CPRuleChannelRelModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.commerce.product.model.impl.CPRuleChannelRelImpl")
@ProviderType
public interface CPRuleChannelRel extends CPRuleChannelRelModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.commerce.product.model.impl.CPRuleChannelRelImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CPRuleChannelRel, Long> CP_RULE_CHANNEL_REL_ID_ACCESSOR =
		new Accessor<CPRuleChannelRel, Long>() {
			@Override
			public Long get(CPRuleChannelRel cpRuleChannelRel) {
				return cpRuleChannelRel.getCPRuleChannelRelId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<CPRuleChannelRel> getTypeClass() {
				return CPRuleChannelRel.class;
			}
		};
}