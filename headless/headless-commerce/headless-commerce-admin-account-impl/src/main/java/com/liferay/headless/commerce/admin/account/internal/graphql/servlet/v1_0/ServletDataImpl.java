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

package com.liferay.headless.commerce.admin.account.internal.graphql.servlet.v1_0;

import com.liferay.headless.commerce.admin.account.internal.graphql.mutation.v1_0.Mutation;
import com.liferay.headless.commerce.admin.account.internal.graphql.query.v1_0.Query;
import com.liferay.headless.commerce.admin.account.resource.v1_0.AccountAddressResource;
import com.liferay.headless.commerce.admin.account.resource.v1_0.AccountGroupResource;
import com.liferay.headless.commerce.admin.account.resource.v1_0.AccountMemberResource;
import com.liferay.headless.commerce.admin.account.resource.v1_0.AccountOrganizationResource;
import com.liferay.headless.commerce.admin.account.resource.v1_0.AccountResource;
import com.liferay.headless.commerce.admin.account.resource.v1_0.UserResource;
import com.liferay.portal.vulcan.graphql.servlet.ServletData;

import javax.annotation.Generated;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentServiceObjects;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceScope;

/**
 * @author Alessio Antonio Rendina
 * @generated
 */
@Component(immediate = true, service = ServletData.class)
@Generated("")
public class ServletDataImpl implements ServletData {

	@Activate
	public void activate(BundleContext bundleContext) {
		Mutation.setAccountResourceComponentServiceObjects(
			_accountResourceComponentServiceObjects);
		Mutation.setAccountAddressResourceComponentServiceObjects(
			_accountAddressResourceComponentServiceObjects);
		Mutation.setAccountGroupResourceComponentServiceObjects(
			_accountGroupResourceComponentServiceObjects);
		Mutation.setAccountMemberResourceComponentServiceObjects(
			_accountMemberResourceComponentServiceObjects);
		Mutation.setAccountOrganizationResourceComponentServiceObjects(
			_accountOrganizationResourceComponentServiceObjects);
		Mutation.setUserResourceComponentServiceObjects(
			_userResourceComponentServiceObjects);

		Query.setAccountResourceComponentServiceObjects(
			_accountResourceComponentServiceObjects);
		Query.setAccountAddressResourceComponentServiceObjects(
			_accountAddressResourceComponentServiceObjects);
		Query.setAccountGroupResourceComponentServiceObjects(
			_accountGroupResourceComponentServiceObjects);
		Query.setAccountMemberResourceComponentServiceObjects(
			_accountMemberResourceComponentServiceObjects);
		Query.setAccountOrganizationResourceComponentServiceObjects(
			_accountOrganizationResourceComponentServiceObjects);
	}

	@Override
	public Mutation getMutation() {
		return new Mutation();
	}

	@Override
	public String getPath() {
		return "/headless-commerce-admin-account-graphql/v1_0";
	}

	@Override
	public Query getQuery() {
		return new Query();
	}

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<AccountResource>
		_accountResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<AccountAddressResource>
		_accountAddressResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<AccountGroupResource>
		_accountGroupResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<AccountMemberResource>
		_accountMemberResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<AccountOrganizationResource>
		_accountOrganizationResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<UserResource>
		_userResourceComponentServiceObjects;

}