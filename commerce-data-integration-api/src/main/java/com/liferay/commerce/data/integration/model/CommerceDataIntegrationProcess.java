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

package com.liferay.commerce.data.integration.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the CommerceDataIntegrationProcess service. Represents a row in the &quot;CDataIntegrationProcess&quot; database table, with each column mapped to a property of this class.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceDataIntegrationProcessModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.commerce.data.integration.model.impl.CommerceDataIntegrationProcessImpl"
)
@ProviderType
public interface CommerceDataIntegrationProcess
	extends CommerceDataIntegrationProcessModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.commerce.data.integration.model.impl.CommerceDataIntegrationProcessImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CommerceDataIntegrationProcess, Long>
		COMMERCE_DATA_INTEGRATION_PROCESS_ID_ACCESSOR =
			new Accessor<CommerceDataIntegrationProcess, Long>() {

				@Override
				public Long get(
					CommerceDataIntegrationProcess
						commerceDataIntegrationProcess) {

					return commerceDataIntegrationProcess.
						getCommerceDataIntegrationProcessId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<CommerceDataIntegrationProcess> getTypeClass() {
					return CommerceDataIntegrationProcess.class;
				}

			};

	public com.liferay.portal.kernel.util.UnicodeProperties
		getTypeSettingsProperties();

	public void setTypeSettingsProperties(
		com.liferay.portal.kernel.util.UnicodeProperties
			typeSettingsProperties);

}