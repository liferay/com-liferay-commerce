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

package com.liferay.headless.commerce.admin.account.internal.graphql.query.v1_0;

import com.liferay.headless.commerce.admin.account.dto.v1_0.Account;
import com.liferay.headless.commerce.admin.account.dto.v1_0.AccountAddress;
import com.liferay.headless.commerce.admin.account.dto.v1_0.AccountGroup;
import com.liferay.headless.commerce.admin.account.dto.v1_0.AccountMember;
import com.liferay.headless.commerce.admin.account.dto.v1_0.AccountOrganization;
import com.liferay.headless.commerce.admin.account.resource.v1_0.AccountAddressResource;
import com.liferay.headless.commerce.admin.account.resource.v1_0.AccountGroupResource;
import com.liferay.headless.commerce.admin.account.resource.v1_0.AccountMemberResource;
import com.liferay.headless.commerce.admin.account.resource.v1_0.AccountOrganizationResource;
import com.liferay.headless.commerce.admin.account.resource.v1_0.AccountResource;
import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

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

	public static void setAccountResourceComponentServiceObjects(
		ComponentServiceObjects<AccountResource>
			accountResourceComponentServiceObjects) {

		_accountResourceComponentServiceObjects =
			accountResourceComponentServiceObjects;
	}

	public static void setAccountAddressResourceComponentServiceObjects(
		ComponentServiceObjects<AccountAddressResource>
			accountAddressResourceComponentServiceObjects) {

		_accountAddressResourceComponentServiceObjects =
			accountAddressResourceComponentServiceObjects;
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

	public static void setAccountOrganizationResourceComponentServiceObjects(
		ComponentServiceObjects<AccountOrganizationResource>
			accountOrganizationResourceComponentServiceObjects) {

		_accountOrganizationResourceComponentServiceObjects =
			accountOrganizationResourceComponentServiceObjects;
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<Account> getAccountsPage(
			@GraphQLName("filter") Filter filter,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page, @GraphQLName("sorts") Sort[] sorts)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> {
				Page paginationPage = accountResource.getAccountsPage(
					filter, Pagination.of(pageSize, page), sorts);

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Account getAccountByExternalReferenceCode(
			@GraphQLName("externalReferenceCode") String externalReferenceCode)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource ->
				accountResource.getAccountByExternalReferenceCode(
					externalReferenceCode));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Account getAccount(@GraphQLName("id") Long id) throws Exception {
		return _applyComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> accountResource.getAccount(id));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<AccountAddress>
			getAccountByExternalReferenceCodeAccountAddressesPage(
				@GraphQLName("externalReferenceCode") String
					externalReferenceCode,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountAddressResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountAddressResource -> {
				Page paginationPage =
					accountAddressResource.
						getAccountByExternalReferenceCodeAccountAddressesPage(
							externalReferenceCode,
							Pagination.of(pageSize, page));

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public AccountAddress getAccountAddressByExternalReferenceCode(
			@GraphQLName("externalReferenceCode") String externalReferenceCode)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountAddressResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountAddressResource ->
				accountAddressResource.getAccountAddressByExternalReferenceCode(
					externalReferenceCode));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<AccountAddress> getAccountIdAccountAddressesPage(
			@GraphQLName("id") Long id, @GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountAddressResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountAddressResource -> {
				Page paginationPage =
					accountAddressResource.getAccountIdAccountAddressesPage(
						id, Pagination.of(pageSize, page));

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<AccountGroup> getAccountGroupsPage(
			@GraphQLName("filter") Filter filter,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page, @GraphQLName("sorts") Sort[] sorts)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountGroupResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountGroupResource -> {
				Page paginationPage = accountGroupResource.getAccountGroupsPage(
					filter, Pagination.of(pageSize, page), sorts);

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public AccountGroup getAccountGroupByExternalReferenceCode(
			@GraphQLName("externalReferenceCode") String externalReferenceCode)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountGroupResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountGroupResource ->
				accountGroupResource.getAccountGroupByExternalReferenceCode(
					externalReferenceCode));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public AccountGroup getAccountGroup(@GraphQLName("id") Long id)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountGroupResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountGroupResource -> accountGroupResource.getAccountGroup(id));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<AccountMember>
			getAccountByExternalReferenceCodeAccountMembersPage(
				@GraphQLName("externalReferenceCode") String
					externalReferenceCode,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountMemberResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountMemberResource -> {
				Page paginationPage =
					accountMemberResource.
						getAccountByExternalReferenceCodeAccountMembersPage(
							externalReferenceCode,
							Pagination.of(pageSize, page));

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public AccountMember getAccountByExternalReferenceCodeAccountMember(
			@GraphQLName("externalReferenceCode") String externalReferenceCode,
			@GraphQLName("userId") Long userId)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountMemberResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountMemberResource ->
				accountMemberResource.
					getAccountByExternalReferenceCodeAccountMember(
						externalReferenceCode, userId));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<AccountMember> getAccountIdAccountMembersPage(
			@GraphQLName("id") Long id, @GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountMemberResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountMemberResource -> {
				Page paginationPage =
					accountMemberResource.getAccountIdAccountMembersPage(
						id, Pagination.of(pageSize, page));

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public AccountMember getAccountIdAccountMember(
			@GraphQLName("id") Long id, @GraphQLName("userId") Long userId)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountMemberResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountMemberResource ->
				accountMemberResource.getAccountIdAccountMember(id, userId));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<AccountOrganization>
			getAccountByExternalReferenceCodeAccountOrganizationsPage(
				@GraphQLName("externalReferenceCode") String
					externalReferenceCode,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountOrganizationResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountOrganizationResource -> {
				Page paginationPage =
					accountOrganizationResource.
						getAccountByExternalReferenceCodeAccountOrganizationsPage(
							externalReferenceCode,
							Pagination.of(pageSize, page));

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public AccountOrganization
			getAccountByExternalReferenceCodeAccountOrganization(
				@GraphQLName("externalReferenceCode") String
					externalReferenceCode,
				@GraphQLName("organizationId") Long organizationId)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountOrganizationResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountOrganizationResource ->
				accountOrganizationResource.
					getAccountByExternalReferenceCodeAccountOrganization(
						externalReferenceCode, organizationId));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<AccountOrganization> getAccountIdAccountOrganizationsPage(
			@GraphQLName("id") Long id, @GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountOrganizationResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountOrganizationResource -> {
				Page paginationPage =
					accountOrganizationResource.
						getAccountIdAccountOrganizationsPage(
							id, Pagination.of(pageSize, page));

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public AccountOrganization getAccountIdAccountOrganization(
			@GraphQLName("id") Long id,
			@GraphQLName("organizationId") Long organizationId)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountOrganizationResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountOrganizationResource ->
				accountOrganizationResource.getAccountIdAccountOrganization(
					id, organizationId));
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

	private void _populateResourceContext(AccountResource accountResource)
		throws Exception {

		accountResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private void _populateResourceContext(
			AccountAddressResource accountAddressResource)
		throws Exception {

		accountAddressResource.setContextCompany(
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

	private void _populateResourceContext(
			AccountOrganizationResource accountOrganizationResource)
		throws Exception {

		accountOrganizationResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private static ComponentServiceObjects<AccountResource>
		_accountResourceComponentServiceObjects;
	private static ComponentServiceObjects<AccountAddressResource>
		_accountAddressResourceComponentServiceObjects;
	private static ComponentServiceObjects<AccountGroupResource>
		_accountGroupResourceComponentServiceObjects;
	private static ComponentServiceObjects<AccountMemberResource>
		_accountMemberResourceComponentServiceObjects;
	private static ComponentServiceObjects<AccountOrganizationResource>
		_accountOrganizationResourceComponentServiceObjects;

}