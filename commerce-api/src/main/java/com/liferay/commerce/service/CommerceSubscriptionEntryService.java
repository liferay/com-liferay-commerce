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

package com.liferay.commerce.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.model.CommerceSubscriptionEntry;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.util.List;

/**
 * Provides the remote service interface for CommerceSubscriptionEntry. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceSubscriptionEntryServiceUtil
 * @see com.liferay.commerce.service.base.CommerceSubscriptionEntryServiceBaseImpl
 * @see com.liferay.commerce.service.impl.CommerceSubscriptionEntryServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(property =  {
	"json.web.service.context.name=commerce", "json.web.service.context.path=CommerceSubscriptionEntry"}, service = CommerceSubscriptionEntryService.class)
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface CommerceSubscriptionEntryService extends BaseService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceSubscriptionEntryServiceUtil} to access the commerce subscription entry remote service. Add custom service methods to {@link com.liferay.commerce.service.impl.CommerceSubscriptionEntryServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public void deleteCommerceSubscriptionEntry(
		long commerceSubscriptionEntryId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CommerceSubscriptionEntry fetchCommerceSubscriptionEntry(
		long commerceSubscriptionEntryId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CommerceSubscriptionEntry> getCommerceSubscriptionEntries(
		long groupId, long userId, int start, int end,
		OrderByComparator<CommerceSubscriptionEntry> orderByComparator)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public BaseModelSearchResult<CommerceSubscriptionEntry> getCommerceSubscriptionEntries(
		long companyId, long groupId, Long maxSubscriptionCycles,
		Boolean active, String keywords, int start, int end, Sort sort)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCommerceSubscriptionEntriesCount(long groupId, long userId)
		throws PortalException;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public String getOSGiServiceIdentifier();

	public CommerceSubscriptionEntry setActive(
		long commerceSubscriptionEntryId, boolean active)
		throws PortalException;

	public CommerceSubscriptionEntry updateCommerceSubscriptionEntry(
		long commerceSubscriptionEntryId, int subscriptionLength,
		String subscriptionType,
		UnicodeProperties subscriptionTypeSettingsProperties,
		long maxSubscriptionCycles, boolean active, int startDateMonth,
		int startDateDay, int startDateYear, int startDateHour,
		int startDateMinute, int nextInterationDateMonth,
		int nextInterationDateDay, int nextInterationDateYear,
		int nextInterationDateHour, int nextInterationDateMinute)
		throws PortalException;
}