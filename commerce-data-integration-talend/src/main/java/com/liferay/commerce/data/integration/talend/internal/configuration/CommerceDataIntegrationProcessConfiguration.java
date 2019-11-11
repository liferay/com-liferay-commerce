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

package com.liferay.commerce.data.integration.talend.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author guywandji
 * @author Alessio Antonio Rendina
 */
@ExtendedObjectClassDefinition(
	category = "data-integration",
	scope = ExtendedObjectClassDefinition.Scope.SYSTEM
)
@Meta.OCD(
	id = "com.liferay.commerce.data.integration.talend.internal.configuration.CommerceDataIntegrationProcessConfiguration",
	localization = "content/Language",
	name = "commerce-data-integration-process-configuration-name"
)
public interface CommerceDataIntegrationProcessConfiguration {

	@Meta.AD(
		deflt = ".zip,.rar,.jar,.properties", name = "file-extensions",
		required = false
	)
	public String[] imageExtensions();

	@Meta.AD(deflt = "50242880", name = "file-max-size", required = false)
	public long imageMaxSize();

}