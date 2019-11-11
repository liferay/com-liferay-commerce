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

package com.liferay.commerce.machine.learning.forecast.alert.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.machine.learning.forecast.alert.model.CommerceMLForecastAlertEntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.util.List;

/**
 * Provides the remote service interface for CommerceMLForecastAlertEntry. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Riccardo Ferrari
 * @see CommerceMLForecastAlertEntryServiceUtil
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(
	property = {
		"json.web.service.context.name=commerce",
		"json.web.service.context.path=CommerceMLForecastAlertEntry"
	},
	service = CommerceMLForecastAlertEntryService.class
)
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface CommerceMLForecastAlertEntryService extends BaseService {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceMLForecastAlertEntryServiceUtil} to access the commerce ml forecast alert entry remote service. Add custom service methods to <code>com.liferay.commerce.machine.learning.forecast.alert.service.impl.CommerceMLForecastAlertEntryServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CommerceMLForecastAlertEntry>
			getAboveThresholdCommerceMLForecastAlertEntries(
				long companyId, long userId, int status,
				double relativeChangeThreshold, int start, int end)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAboveThresholdCommerceMLForecastAlertEntriesCount(
			long companyId, long userId, int status,
			double relativeChangeThreshold)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CommerceMLForecastAlertEntry>
			getBelowThresholdCommerceMLForecastAlertEntries(
				long companyId, long userId, int status,
				double relativeChangeThreshold, int start, int end)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getBelowThresholdCommerceMLForecastAlertEntriesCount(
			long companyId, long userId, int status,
			double relativeChangeThreshold)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CommerceMLForecastAlertEntry> getCommerceMLForecastAlertEntries(
			long companyId, long userId, int status, int start, int end)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCommerceMLForecastAlertEntriesCount(
			long companyId, long userId, int status)
		throws PortalException;

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	public CommerceMLForecastAlertEntry updateStatus(
			long userId, long commerceMLForecastAlertEntryId, int status)
		throws PortalException;

}