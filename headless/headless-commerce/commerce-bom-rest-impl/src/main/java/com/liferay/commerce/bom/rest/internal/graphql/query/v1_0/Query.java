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

package com.liferay.commerce.bom.rest.internal.graphql.query.v1_0;

import com.liferay.commerce.bom.rest.dto.v1_0.Area;
import com.liferay.commerce.bom.rest.dto.v1_0.Folder;
import com.liferay.commerce.bom.rest.dto.v1_0.Product;
import com.liferay.commerce.bom.rest.resource.v1_0.AreaResource;
import com.liferay.commerce.bom.rest.resource.v1_0.FolderResource;
import com.liferay.commerce.bom.rest.resource.v1_0.ProductResource;
import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.vulcan.pagination.Page;

import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLInvokeDetached;
import graphql.annotations.annotationTypes.GraphQLName;

import java.util.Collection;

import javax.annotation.Generated;

import org.osgi.service.component.ComponentServiceObjects;

/**
 * @author Alessio Antonio Rendina
 * @generated
 */
@Generated("")
public class Query {

	public static void setAreaResourceComponentServiceObjects(
		ComponentServiceObjects<AreaResource>
			areaResourceComponentServiceObjects) {

		_areaResourceComponentServiceObjects =
			areaResourceComponentServiceObjects;
	}

	public static void setFolderResourceComponentServiceObjects(
		ComponentServiceObjects<FolderResource>
			folderResourceComponentServiceObjects) {

		_folderResourceComponentServiceObjects =
			folderResourceComponentServiceObjects;
	}

	public static void setProductResourceComponentServiceObjects(
		ComponentServiceObjects<ProductResource>
			productResourceComponentServiceObjects) {

		_productResourceComponentServiceObjects =
			productResourceComponentServiceObjects;
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Area getArea(@GraphQLName("id") Long id) throws Exception {
		return _applyComponentServiceObjects(
			_areaResourceComponentServiceObjects,
			this::_populateResourceContext,
			areaResource -> areaResource.getArea(id));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Folder getFolder(@GraphQLName("id") Long id) throws Exception {
		return _applyComponentServiceObjects(
			_folderResourceComponentServiceObjects,
			this::_populateResourceContext,
			folderResource -> folderResource.getFolder(id));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<Product> getProductsPage(@GraphQLName("q") String q)
		throws Exception {

		return _applyComponentServiceObjects(
			_productResourceComponentServiceObjects,
			this::_populateResourceContext,
			productResource -> {
				Page paginationPage = productResource.getProductsPage(q);

				return paginationPage.getItems();
			});
	}

	private <T, R, E1 extends Throwable, E2 extends Throwable> R
			_applyComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeFunction<T, R, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			return unsafeFunction.apply(resource);
		}
		finally {
			componentServiceObjects.ungetService(resource);
		}
	}

	private void _populateResourceContext(AreaResource areaResource)
		throws Exception {

		areaResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private void _populateResourceContext(FolderResource folderResource)
		throws Exception {

		folderResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private void _populateResourceContext(ProductResource productResource)
		throws Exception {

		productResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private static ComponentServiceObjects<AreaResource>
		_areaResourceComponentServiceObjects;
	private static ComponentServiceObjects<FolderResource>
		_folderResourceComponentServiceObjects;
	private static ComponentServiceObjects<ProductResource>
		_productResourceComponentServiceObjects;

}