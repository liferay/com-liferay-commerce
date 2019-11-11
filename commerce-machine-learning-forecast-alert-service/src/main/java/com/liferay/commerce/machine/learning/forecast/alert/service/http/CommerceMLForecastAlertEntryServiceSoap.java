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

package com.liferay.commerce.machine.learning.forecast.alert.service.http;

import com.liferay.commerce.machine.learning.forecast.alert.service.CommerceMLForecastAlertEntryServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>CommerceMLForecastAlertEntryServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.liferay.commerce.machine.learning.forecast.alert.model.CommerceMLForecastAlertEntrySoap</code>. If the method in the
 * service utility returns a
 * <code>com.liferay.commerce.machine.learning.forecast.alert.model.CommerceMLForecastAlertEntry</code>, that is translated to a
 * <code>com.liferay.commerce.machine.learning.forecast.alert.model.CommerceMLForecastAlertEntrySoap</code>. Methods that SOAP
 * cannot safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Riccardo Ferrari
 * @see CommerceMLForecastAlertEntryServiceHttp
 * @generated
 */
public class CommerceMLForecastAlertEntryServiceSoap {

	public static com.liferay.commerce.machine.learning.forecast.alert.model.
		CommerceMLForecastAlertEntrySoap[]
				getAboveThresholdCommerceMLForecastAlertEntries(
					long companyId, long userId, int status,
					double relativeChangeThreshold, int start, int end)
			throws RemoteException {

		try {
			java.util.List
				<com.liferay.commerce.machine.learning.forecast.alert.model.
					CommerceMLForecastAlertEntry> returnValue =
						CommerceMLForecastAlertEntryServiceUtil.
							getAboveThresholdCommerceMLForecastAlertEntries(
								companyId, userId, status,
								relativeChangeThreshold, start, end);

			return com.liferay.commerce.machine.learning.forecast.alert.model.
				CommerceMLForecastAlertEntrySoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getAboveThresholdCommerceMLForecastAlertEntriesCount(
			long companyId, long userId, int status,
			double relativeChangeThreshold)
		throws RemoteException {

		try {
			int returnValue =
				CommerceMLForecastAlertEntryServiceUtil.
					getAboveThresholdCommerceMLForecastAlertEntriesCount(
						companyId, userId, status, relativeChangeThreshold);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.machine.learning.forecast.alert.model.
		CommerceMLForecastAlertEntrySoap[]
				getBelowThresholdCommerceMLForecastAlertEntries(
					long companyId, long userId, int status,
					double relativeChangeThreshold, int start, int end)
			throws RemoteException {

		try {
			java.util.List
				<com.liferay.commerce.machine.learning.forecast.alert.model.
					CommerceMLForecastAlertEntry> returnValue =
						CommerceMLForecastAlertEntryServiceUtil.
							getBelowThresholdCommerceMLForecastAlertEntries(
								companyId, userId, status,
								relativeChangeThreshold, start, end);

			return com.liferay.commerce.machine.learning.forecast.alert.model.
				CommerceMLForecastAlertEntrySoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getBelowThresholdCommerceMLForecastAlertEntriesCount(
			long companyId, long userId, int status,
			double relativeChangeThreshold)
		throws RemoteException {

		try {
			int returnValue =
				CommerceMLForecastAlertEntryServiceUtil.
					getBelowThresholdCommerceMLForecastAlertEntriesCount(
						companyId, userId, status, relativeChangeThreshold);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.machine.learning.forecast.alert.model.
		CommerceMLForecastAlertEntrySoap[] getCommerceMLForecastAlertEntries(
				long companyId, long userId, int status, int start, int end)
			throws RemoteException {

		try {
			java.util.List
				<com.liferay.commerce.machine.learning.forecast.alert.model.
					CommerceMLForecastAlertEntry> returnValue =
						CommerceMLForecastAlertEntryServiceUtil.
							getCommerceMLForecastAlertEntries(
								companyId, userId, status, start, end);

			return com.liferay.commerce.machine.learning.forecast.alert.model.
				CommerceMLForecastAlertEntrySoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getCommerceMLForecastAlertEntriesCount(
			long companyId, long userId, int status)
		throws RemoteException {

		try {
			int returnValue =
				CommerceMLForecastAlertEntryServiceUtil.
					getCommerceMLForecastAlertEntriesCount(
						companyId, userId, status);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.machine.learning.forecast.alert.model.
		CommerceMLForecastAlertEntrySoap updateStatus(
				long userId, long commerceMLForecastAlertEntryId, int status)
			throws RemoteException {

		try {
			com.liferay.commerce.machine.learning.forecast.alert.model.
				CommerceMLForecastAlertEntry returnValue =
					CommerceMLForecastAlertEntryServiceUtil.updateStatus(
						userId, commerceMLForecastAlertEntryId, status);

			return com.liferay.commerce.machine.learning.forecast.alert.model.
				CommerceMLForecastAlertEntrySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		CommerceMLForecastAlertEntryServiceSoap.class);

}