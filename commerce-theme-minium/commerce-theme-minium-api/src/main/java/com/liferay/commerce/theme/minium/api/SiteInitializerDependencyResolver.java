/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.commerce.theme.minium.api;

import aQute.bnd.annotation.ProviderType;

import java.io.IOException;

/**
 * @author Marco Leo
 */
@ProviderType
public interface SiteInitializerDependencyResolver {

	public String getDependenciesPath();

	public ClassLoader getDisplayTemplatesClassLoader();

	public String getDisplayTemplatesDependencyPath();

	public ClassLoader getImageClassLoader();

	public String getImageDependencyPath();

	public String getJSON(String name) throws IOException;

}