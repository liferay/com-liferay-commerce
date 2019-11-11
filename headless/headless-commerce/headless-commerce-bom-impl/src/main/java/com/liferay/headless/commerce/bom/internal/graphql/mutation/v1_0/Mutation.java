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

package com.liferay.headless.commerce.bom.internal.graphql.mutation.v1_0;

import com.liferay.headless.commerce.bom.dto.v1_0.Spot;
import com.liferay.headless.commerce.bom.resource.v1_0.SpotResource;
import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;

import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLInvokeDetached;
import graphql.annotations.annotationTypes.GraphQLName;

import javax.annotation.Generated;

import javax.ws.rs.core.Response;

import org.osgi.service.component.ComponentServiceObjects;

/**
 * @author Alessio Antonio Rendina
 * @generated
 */
@Generated("")
public class Mutation {

	public static void setSpotResourceComponentServiceObjects(
		ComponentServiceObjects<SpotResource>
			spotResourceComponentServiceObjects) {

		_spotResourceComponentServiceObjects =
			spotResourceComponentServiceObjects;
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Spot postAreaIdSpot(
			@GraphQLName("id") Long id, @GraphQLName("spot") Spot spot)
		throws Exception {

		return _applyComponentServiceObjects(
			_spotResourceComponentServiceObjects,
			this::_populateResourceContext,
			spotResource -> spotResource.postAreaIdSpot(id, spot));
	}

	@GraphQLInvokeDetached
	public Response deleteAreaIdSpot(
			@GraphQLName("id") Long id, @GraphQLName("spotId") Long spotId)
		throws Exception {

		return _applyComponentServiceObjects(
			_spotResourceComponentServiceObjects,
			this::_populateResourceContext,
			spotResource -> spotResource.deleteAreaIdSpot(id, spotId));
	}

	@GraphQLInvokeDetached
	public Response putAreaIdSpot(
			@GraphQLName("id") Long id, @GraphQLName("spotId") Long spotId,
			@GraphQLName("spot") Spot spot)
		throws Exception {

		return _applyComponentServiceObjects(
			_spotResourceComponentServiceObjects,
			this::_populateResourceContext,
			spotResource -> spotResource.putAreaIdSpot(id, spotId, spot));
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

	private <T, E1 extends Throwable, E2 extends Throwable> void
			_applyVoidComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeConsumer<T, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			unsafeFunction.accept(resource);
		}
		finally {
			componentServiceObjects.ungetService(resource);
		}
	}

	private void _populateResourceContext(SpotResource spotResource)
		throws Exception {

		spotResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private static ComponentServiceObjects<SpotResource>
		_spotResourceComponentServiceObjects;

}