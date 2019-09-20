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

package com.liferay.commerce.notification.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the CommerceNotificationTemplateCommerceAccountGroupRel service. Represents a row in the &quot;CNTemplateCAccountGroupRel&quot; database table, with each column mapped to a property of this class.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceNotificationTemplateCommerceAccountGroupRelModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.commerce.notification.model.impl.CommerceNotificationTemplateCommerceAccountGroupRelImpl"
)
@ProviderType
public interface CommerceNotificationTemplateCommerceAccountGroupRel
	extends CommerceNotificationTemplateCommerceAccountGroupRelModel,
			PersistedModel {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.commerce.notification.model.impl.CommerceNotificationTemplateCommerceAccountGroupRelImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor
		<CommerceNotificationTemplateCommerceAccountGroupRel, Long>
			COMMERCE_NOTIFICATION_TEMPLATE_COMMERCE_ACCOUNT_GROUP_REL_ID_ACCESSOR =
				new Accessor
					<CommerceNotificationTemplateCommerceAccountGroupRel,
					 Long>() {

					@Override
					public Long get(
						CommerceNotificationTemplateCommerceAccountGroupRel
							commerceNotificationTemplateCommerceAccountGroupRel) {

						return commerceNotificationTemplateCommerceAccountGroupRel.
							getCommerceNotificationTemplateCommerceAccountGroupRelId();
					}

					@Override
					public Class<Long> getAttributeClass() {
						return Long.class;
					}

					@Override
					public Class
						<CommerceNotificationTemplateCommerceAccountGroupRel>
							getTypeClass() {

						return CommerceNotificationTemplateCommerceAccountGroupRel.class;
					}

				};
	public static final Accessor
		<CommerceNotificationTemplateCommerceAccountGroupRel, Long>
			COMMERCE_ACCOUNT_GROUP_ID_ACCESSOR =
				new Accessor
					<CommerceNotificationTemplateCommerceAccountGroupRel,
					 Long>() {

					@Override
					public Long get(
						CommerceNotificationTemplateCommerceAccountGroupRel
							commerceNotificationTemplateCommerceAccountGroupRel) {

						return commerceNotificationTemplateCommerceAccountGroupRel.
							getCommerceAccountGroupId();
					}

					@Override
					public Class<Long> getAttributeClass() {
						return Long.class;
					}

					@Override
					public Class
						<CommerceNotificationTemplateCommerceAccountGroupRel>
							getTypeClass() {

						return CommerceNotificationTemplateCommerceAccountGroupRel.class;
					}

				};

}