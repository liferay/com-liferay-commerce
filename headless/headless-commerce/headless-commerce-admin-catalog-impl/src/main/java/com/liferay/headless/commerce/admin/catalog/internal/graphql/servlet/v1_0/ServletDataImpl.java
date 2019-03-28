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

package com.liferay.headless.commerce.admin.catalog.internal.graphql.servlet.v1_0;

import com.liferay.headless.commerce.admin.catalog.internal.graphql.mutation.v1_0.Mutation;
import com.liferay.headless.commerce.admin.catalog.internal.graphql.query.v1_0.Query;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.AttachmentResource;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.OptionCategoryResource;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.ProductOptionResource;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.ProductResource;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.SkuResource;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.SpecificationResource;
import com.liferay.portal.vulcan.graphql.servlet.ServletData;

import javax.annotation.Generated;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentServiceObjects;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceScope;

/**
 * @author Zoltán Takács
 * @generated
 */
@Component(immediate = true, service = ServletData.class)
@Generated("")
public class ServletDataImpl implements ServletData {

	@Activate
	public void activate(BundleContext bundleContext) {
		Mutation.setAttachmentResourceComponentServiceObjects(
			_attachmentResourceComponentServiceObjects);
		Mutation.setOptionCategoryResourceComponentServiceObjects(
			_optionCategoryResourceComponentServiceObjects);
		Mutation.setProductResourceComponentServiceObjects(
			_productResourceComponentServiceObjects);
		Mutation.setProductOptionResourceComponentServiceObjects(
			_productOptionResourceComponentServiceObjects);
		Mutation.setSkuResourceComponentServiceObjects(
			_skuResourceComponentServiceObjects);
		Mutation.setSpecificationResourceComponentServiceObjects(
			_specificationResourceComponentServiceObjects);

		Query.setAttachmentResourceComponentServiceObjects(
			_attachmentResourceComponentServiceObjects);
		Query.setOptionCategoryResourceComponentServiceObjects(
			_optionCategoryResourceComponentServiceObjects);
		Query.setProductResourceComponentServiceObjects(
			_productResourceComponentServiceObjects);
		Query.setProductOptionResourceComponentServiceObjects(
			_productOptionResourceComponentServiceObjects);
		Query.setSkuResourceComponentServiceObjects(
			_skuResourceComponentServiceObjects);
		Query.setSpecificationResourceComponentServiceObjects(
			_specificationResourceComponentServiceObjects);
	}

	@Override
	public Mutation getMutation() {
		return new Mutation();
	}

	@Override
	public String getPath() {
		return "/headless-commerce-admin-catalog-graphql/v1_0";
	}

	@Override
	public Query getQuery() {
		return new Query();
	}

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<AttachmentResource>
		_attachmentResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<OptionCategoryResource>
		_optionCategoryResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<ProductResource>
		_productResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<ProductOptionResource>
		_productOptionResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<SkuResource>
		_skuResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<SpecificationResource>
		_specificationResourceComponentServiceObjects;

}