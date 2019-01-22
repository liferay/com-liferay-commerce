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

package com.liferay.commerce.account.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.account.model.CommerceAccount;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.util.List;

/**
 * Provides the remote service interface for CommerceAccount. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Marco Leo
 * @see CommerceAccountServiceUtil
 * @see com.liferay.commerce.account.service.base.CommerceAccountServiceBaseImpl
 * @see com.liferay.commerce.account.service.impl.CommerceAccountServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(property =  {
	"json.web.service.context.name=commerce", "json.web.service.context.path=CommerceAccount"}, service = CommerceAccountService.class)
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface CommerceAccountService extends BaseService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceAccountServiceUtil} to access the commerce account remote service. Add custom service methods to {@link com.liferay.commerce.account.service.impl.CommerceAccountServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public CommerceAccount addBusinessCommerceAccount(String name,
		long parentCommerceAccountId, String email, String taxId,
		boolean active, String externalReferenceCode, long[] userIds,
		String[] emailAddresses, ServiceContext serviceContext)
		throws PortalException;

	public CommerceAccount addCommerceAccount(String name,
		long parentCommerceAccountId, String email, String taxId, int type,
		boolean active, String externalReferenceCode,
		ServiceContext serviceContext) throws PortalException;

	public void deleteCommerceAccount(long commerceAccountId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CommerceAccount fetchCommerceAccount(long commerceAccountId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CommerceAccount getCommerceAccount(long commerceAccountId)
		throws PortalException;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public String getOSGiServiceIdentifier();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CommerceAccount getPersonalCommerceAccount(long companyId,
		long userId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CommerceAccount> getUserCommerceAccounts(long userId,
		int commerceSiteType, int start, int end) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CommerceAccount> getUserCommerceAccounts(long userId,
		Long parentCommerceAccountId, int commerceSiteType, String keywords,
		int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getUserCommerceAccountsCount(long userId,
		Long parentCommerceAccountId, int commerceSiteType, String keywords);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public BaseModelSearchResult<CommerceAccount> searchCommerceAccounts(
		long parentCommerceAccountId, String keywords, Boolean active,
		int start, int end, Sort sort) throws PortalException;

	public CommerceAccount updateCommerceAccount(long commerceAccountId,
		String name, boolean logo, byte[] logoBytes, String email,
		String taxId, boolean active, ServiceContext serviceContext)
		throws PortalException;

	public CommerceAccount upsertCommerceAccount(String name,
		long parentCommerceAccountId, boolean logo, byte[] logoBytes,
		String email, String taxId, int type, boolean active,
		String externalReferenceCode, ServiceContext serviceContext)
		throws PortalException;
}