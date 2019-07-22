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

package com.liferay.headless.commerce.admin.account.internal.graphql.mutation.v1_0;

import com.liferay.headless.commerce.admin.account.dto.v1_0.Account;
import com.liferay.headless.commerce.admin.account.dto.v1_0.AccountGroup;
import com.liferay.headless.commerce.admin.account.dto.v1_0.AccountMember;
import com.liferay.headless.commerce.admin.account.dto.v1_0.Address;
import com.liferay.headless.commerce.admin.account.resource.v1_0.AccountGroupResource;
import com.liferay.headless.commerce.admin.account.resource.v1_0.AccountMemberResource;
import com.liferay.headless.commerce.admin.account.resource.v1_0.AccountResource;
import com.liferay.headless.commerce.admin.account.resource.v1_0.AddressResource;
import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.vulcan.multipart.MultipartBody;

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

	public static void setAccountResourceComponentServiceObjects(
		ComponentServiceObjects<AccountResource>
			accountResourceComponentServiceObjects) {

		_accountResourceComponentServiceObjects =
			accountResourceComponentServiceObjects;
	}

	public static void setAccountGroupResourceComponentServiceObjects(
		ComponentServiceObjects<AccountGroupResource>
			accountGroupResourceComponentServiceObjects) {

		_accountGroupResourceComponentServiceObjects =
			accountGroupResourceComponentServiceObjects;
	}

	public static void setAccountMemberResourceComponentServiceObjects(
		ComponentServiceObjects<AccountMemberResource>
			accountMemberResourceComponentServiceObjects) {

		_accountMemberResourceComponentServiceObjects =
			accountMemberResourceComponentServiceObjects;
	}

	public static void setAddressResourceComponentServiceObjects(
		ComponentServiceObjects<AddressResource>
			addressResourceComponentServiceObjects) {

		_addressResourceComponentServiceObjects =
			addressResourceComponentServiceObjects;
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Account postAccount(@GraphQLName("account") Account account)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> accountResource.postAccount(account));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	@GraphQLName(
		"postAccountByExternalReferenceCodeLogoExternalReferenceCodeMultipartBody"
	)
	public Response postAccountByExternalReferenceCodeLogo(
			@GraphQLName("externalReferenceCode") String externalReferenceCode,
			@GraphQLName("multipartBody") MultipartBody multipartBody)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource ->
				accountResource.postAccountByExternalReferenceCodeLogo(
					externalReferenceCode, multipartBody));
	}

	@GraphQLInvokeDetached
	public Response deleteAccountByExternalReferenceCode(
			@GraphQLName("externalReferenceCode") String externalReferenceCode)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource ->
				accountResource.deleteAccountByExternalReferenceCode(
					externalReferenceCode));
	}

	@GraphQLInvokeDetached
	public Response patchAccountByExternalReferenceCode(
			@GraphQLName("externalReferenceCode") String externalReferenceCode,
			@GraphQLName("account") Account account)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource ->
				accountResource.patchAccountByExternalReferenceCode(
					externalReferenceCode, account));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	@GraphQLName("postAccountLogoIdMultipartBody")
	public Response postAccountLogo(
			@GraphQLName("id") Long id,
			@GraphQLName("multipartBody") MultipartBody multipartBody)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> accountResource.postAccountLogo(
				id, multipartBody));
	}

	@GraphQLInvokeDetached
	public Response deleteAccount(@GraphQLName("id") Long id) throws Exception {
		return _applyComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> accountResource.deleteAccount(id));
	}

	@GraphQLInvokeDetached
	public Response patchAccount(
			@GraphQLName("id") Long id, @GraphQLName("account") Account account)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> accountResource.patchAccount(id, account));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public AccountGroup postAccountGroup(
			@GraphQLName("accountGroup") AccountGroup accountGroup)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountGroupResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountGroupResource -> accountGroupResource.postAccountGroup(
				accountGroup));
	}

	@GraphQLInvokeDetached
	public Response deleteAccountGroupByExternalReferenceCode(
			@GraphQLName("externalReferenceCode") String externalReferenceCode)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountGroupResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountGroupResource ->
				accountGroupResource.deleteAccountGroupByExternalReferenceCode(
					externalReferenceCode));
	}

	@GraphQLInvokeDetached
	public Response patchAccountGroupByExternalReferenceCode(
			@GraphQLName("externalReferenceCode") String externalReferenceCode,
			@GraphQLName("accountGroup") AccountGroup accountGroup)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountGroupResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountGroupResource ->
				accountGroupResource.patchAccountGroupByExternalReferenceCode(
					externalReferenceCode, accountGroup));
	}

	@GraphQLInvokeDetached
	public Response deleteAccountGroup(@GraphQLName("id") Long id)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountGroupResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountGroupResource -> accountGroupResource.deleteAccountGroup(
				id));
	}

	@GraphQLInvokeDetached
	public Response patchAccountGroup(
			@GraphQLName("id") Long id,
			@GraphQLName("accountGroup") AccountGroup accountGroup)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountGroupResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountGroupResource -> accountGroupResource.patchAccountGroup(
				id, accountGroup));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public AccountMember postAccountByExternalReferenceCodeAccountMember(
			@GraphQLName("externalReferenceCode") String externalReferenceCode,
			@GraphQLName("accountMember") AccountMember accountMember)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountMemberResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountMemberResource ->
				accountMemberResource.
					postAccountByExternalReferenceCodeAccountMember(
						externalReferenceCode, accountMember));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public AccountMember postAccountIdAccountMember(
			@GraphQLName("id") Long id,
			@GraphQLName("accountMember") AccountMember accountMember)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountMemberResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountMemberResource ->
				accountMemberResource.postAccountIdAccountMember(
					id, accountMember));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Address postAccountByExternalReferenceCodeAddress(
			@GraphQLName("externalReferenceCode") String externalReferenceCode,
			@GraphQLName("address") Address address)
		throws Exception {

		return _applyComponentServiceObjects(
			_addressResourceComponentServiceObjects,
			this::_populateResourceContext,
			addressResource ->
				addressResource.postAccountByExternalReferenceCodeAddress(
					externalReferenceCode, address));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Address postAccountIdAddress(
			@GraphQLName("id") Long id, @GraphQLName("address") Address address)
		throws Exception {

		return _applyComponentServiceObjects(
			_addressResourceComponentServiceObjects,
			this::_populateResourceContext,
			addressResource -> addressResource.postAccountIdAddress(
				id, address));
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

	private void _populateResourceContext(AccountResource accountResource)
		throws Exception {

		accountResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private void _populateResourceContext(
			AccountGroupResource accountGroupResource)
		throws Exception {

		accountGroupResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private void _populateResourceContext(
			AccountMemberResource accountMemberResource)
		throws Exception {

		accountMemberResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private void _populateResourceContext(AddressResource addressResource)
		throws Exception {

		addressResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private static ComponentServiceObjects<AccountResource>
		_accountResourceComponentServiceObjects;
	private static ComponentServiceObjects<AccountGroupResource>
		_accountGroupResourceComponentServiceObjects;
	private static ComponentServiceObjects<AccountMemberResource>
		_accountMemberResourceComponentServiceObjects;
	private static ComponentServiceObjects<AddressResource>
		_addressResourceComponentServiceObjects;

}