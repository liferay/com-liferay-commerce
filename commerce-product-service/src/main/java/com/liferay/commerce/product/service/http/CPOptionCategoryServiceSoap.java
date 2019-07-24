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

package com.liferay.commerce.product.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.product.service.CPOptionCategoryServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;

import java.rmi.RemoteException;

import java.util.Locale;
import java.util.Map;

/**
 * Provides the SOAP utility for the
 * <code>CPOptionCategoryServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.liferay.commerce.product.model.CPOptionCategorySoap</code>. If the method in the
 * service utility returns a
 * <code>com.liferay.commerce.product.model.CPOptionCategory</code>, that is translated to a
 * <code>com.liferay.commerce.product.model.CPOptionCategorySoap</code>. Methods that SOAP
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
 * @author Marco Leo
 * @see CPOptionCategoryServiceHttp
 * @generated
 */
@ProviderType
public class CPOptionCategoryServiceSoap {

	public static com.liferay.commerce.product.model.CPOptionCategorySoap
			addCPOptionCategory(
				String[] titleMapLanguageIds, String[] titleMapValues,
				String[] descriptionMapLanguageIds,
				String[] descriptionMapValues, double priority, String key,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			Map<Locale, String> titleMap = LocalizationUtil.getLocalizationMap(
				titleMapLanguageIds, titleMapValues);
			Map<Locale, String> descriptionMap =
				LocalizationUtil.getLocalizationMap(
					descriptionMapLanguageIds, descriptionMapValues);

			com.liferay.commerce.product.model.CPOptionCategory returnValue =
				CPOptionCategoryServiceUtil.addCPOptionCategory(
					titleMap, descriptionMap, priority, key, serviceContext);

			return com.liferay.commerce.product.model.CPOptionCategorySoap.
				toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteCPOptionCategory(long cpOptionCategoryId)
		throws RemoteException {

		try {
			CPOptionCategoryServiceUtil.deleteCPOptionCategory(
				cpOptionCategoryId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.product.model.CPOptionCategorySoap
			fetchCPOptionCategory(long cpOptionCategoryId)
		throws RemoteException {

		try {
			com.liferay.commerce.product.model.CPOptionCategory returnValue =
				CPOptionCategoryServiceUtil.fetchCPOptionCategory(
					cpOptionCategoryId);

			return com.liferay.commerce.product.model.CPOptionCategorySoap.
				toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.product.model.CPOptionCategorySoap
			getCPOptionCategory(long cpOptionCategoryId)
		throws RemoteException {

		try {
			com.liferay.commerce.product.model.CPOptionCategory returnValue =
				CPOptionCategoryServiceUtil.getCPOptionCategory(
					cpOptionCategoryId);

			return com.liferay.commerce.product.model.CPOptionCategorySoap.
				toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.product.model.CPOptionCategorySoap
			updateCPOptionCategory(
				long cpOptionCategoryId, String[] titleMapLanguageIds,
				String[] titleMapValues, String[] descriptionMapLanguageIds,
				String[] descriptionMapValues, double priority, String key)
		throws RemoteException {

		try {
			Map<Locale, String> titleMap = LocalizationUtil.getLocalizationMap(
				titleMapLanguageIds, titleMapValues);
			Map<Locale, String> descriptionMap =
				LocalizationUtil.getLocalizationMap(
					descriptionMapLanguageIds, descriptionMapValues);

			com.liferay.commerce.product.model.CPOptionCategory returnValue =
				CPOptionCategoryServiceUtil.updateCPOptionCategory(
					cpOptionCategoryId, titleMap, descriptionMap, priority,
					key);

			return com.liferay.commerce.product.model.CPOptionCategorySoap.
				toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		CPOptionCategoryServiceSoap.class);

}