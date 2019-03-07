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

import com.liferay.commerce.product.importer.CPFileImporter;
import com.liferay.commerce.theme.minium.api.SiteInitializerDependencyResolver;
import com.liferay.commerce.theme.minium.api.SiteInitializerDependencyResolverThreadLocal;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.service.ServiceContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 */
@Component(service = MiniumLayoutsInitializer.class)
public class MiniumLayoutsInitializer {

	public void initialize(ServiceContext serviceContext) throws Exception {
		SiteInitializerDependencyResolver siteInitializerDependencyResolver =
			SiteInitializerDependencyResolverThreadLocal.
				getSiteInitializerDependencyResolver();

		if (siteInitializerDependencyResolver != null) {
			_siteInitializerDependencyResolver =
				siteInitializerDependencyResolver;
		}

		_cpFileImporter.cleanLayouts(serviceContext);

		_createLayouts(serviceContext);
	}

	private void _createLayouts(ServiceContext serviceContext)
		throws Exception {

		String json = _siteInitializerDependencyResolver.getJSON(
			"layouts.json");

		JSONArray jsonArray = _jsonFactory.createJSONArray(json);

		_cpFileImporter.createLayouts(
			jsonArray, _siteInitializerDependencyResolver.getImageClassLoader(),
			_siteInitializerDependencyResolver.getImageDependencyPath(),
			serviceContext);
	}

	@Reference
	private CPFileImporter _cpFileImporter;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference(
		target = "(site.initializer.key=" + MiniumSiteInitializer.KEY + ")"
	)
	private SiteInitializerDependencyResolver
		_siteInitializerDependencyResolver;

}