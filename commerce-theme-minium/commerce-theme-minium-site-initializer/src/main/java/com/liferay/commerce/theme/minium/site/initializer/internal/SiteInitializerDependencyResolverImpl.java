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

package com.liferay.commerce.theme.minium.site.initializer.internal;

import com.liferay.commerce.theme.minium.api.SiteInitializerDependencyResolver;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.IOException;

import org.osgi.service.component.annotations.Component;

/**
 * @author Marco Leo
 */
@Component(
	immediate = true,
	property = "site.initializer.key=" + MiniumSiteInitializer.KEY,
	service = SiteInitializerDependencyResolver.class
)
public class SiteInitializerDependencyResolverImpl
	implements SiteInitializerDependencyResolver {

	@Override
	public String getDependenciesPath() {
		return _DEPENDENCIES_PATH;
	}

	@Override
	public ClassLoader getDisplayTemplatesClassLoader() {
		return SiteInitializerDependencyResolverImpl.class.getClassLoader();
	}

	@Override
	public String getDisplayTemplatesDependencyPath() {
		return _DEPENDENCIES_PATH + "display_templates/";
	}

	@Override
	public ClassLoader getImageClassLoader() {
		return SiteInitializerDependencyResolverImpl.class.getClassLoader();
	}

	@Override
	public String getImageDependencyPath() {
		return _DEPENDENCIES_PATH + "images/";
	}

	@Override
	public String getJSON(String name) throws IOException {
		return StringUtil.read(
			SiteInitializerDependencyResolverImpl.class.getClassLoader(),
			_DEPENDENCIES_PATH + name);
	}

	private static final String _DEPENDENCIES_PATH =
		"com/liferay/commerce/theme/minium/site/initializer/internal" +
			"/dependencies/";

}