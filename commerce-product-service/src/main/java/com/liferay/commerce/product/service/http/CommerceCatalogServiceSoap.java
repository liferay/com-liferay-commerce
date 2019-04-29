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

import com.liferay.commerce.product.service.CommerceCatalogServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;

import java.rmi.RemoteException;

import java.util.Locale;
import java.util.Map;

/**
 * Provides the SOAP utility for the
 * {@link CommerceCatalogServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.commerce.product.model.CommerceCatalogSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.commerce.product.model.CommerceCatalog}, that is translated to a
 * {@link com.liferay.commerce.product.model.CommerceCatalogSoap}. Methods that SOAP cannot
 * safely wire are skipped.
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
 * @see CommerceCatalogServiceHttp
 * @see com.liferay.commerce.product.model.CommerceCatalogSoap
 * @see CommerceCatalogServiceUtil
 * @generated
 */
@ProviderType
public class CommerceCatalogServiceSoap {
	public static com.liferay.commerce.product.model.CommerceCatalogSoap addCommerceCatalog(
		String[] nameMapLanguageIds, String[] nameMapValues,
		String catalogDefaultLanguageId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			Map<Locale, String> nameMap = LocalizationUtil.getLocalizationMap(nameMapLanguageIds,
					nameMapValues);

			com.liferay.commerce.product.model.CommerceCatalog returnValue = CommerceCatalogServiceUtil.addCommerceCatalog(nameMap,
					catalogDefaultLanguageId, serviceContext);

			return com.liferay.commerce.product.model.CommerceCatalogSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.product.model.CommerceCatalogSoap deleteCommerceCatalog(
		long commerceCatalogId) throws RemoteException {
		try {
			com.liferay.commerce.product.model.CommerceCatalog returnValue = CommerceCatalogServiceUtil.deleteCommerceCatalog(commerceCatalogId);

			return com.liferay.commerce.product.model.CommerceCatalogSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.product.model.CommerceCatalogSoap fetchCommerceCatalog(
		long commerceCatalogId) throws RemoteException {
		try {
			com.liferay.commerce.product.model.CommerceCatalog returnValue = CommerceCatalogServiceUtil.fetchCommerceCatalog(commerceCatalogId);

			return com.liferay.commerce.product.model.CommerceCatalogSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.kernel.model.Group getCommerceCatalogGroup(
		long commerceCatalogId) throws RemoteException {
		try {
			com.liferay.portal.kernel.model.Group returnValue = CommerceCatalogServiceUtil.getCommerceCatalogGroup(commerceCatalogId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.product.model.CommerceCatalogSoap[] searchCommerceCatalogs(
		long companyId) throws RemoteException {
		try {
			java.util.List<com.liferay.commerce.product.model.CommerceCatalog> returnValue =
				CommerceCatalogServiceUtil.searchCommerceCatalogs(companyId);

			return com.liferay.commerce.product.model.CommerceCatalogSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.product.model.CommerceCatalogSoap[] searchCommerceCatalogs(
		long companyId, String keywords, int start, int end,
		com.liferay.portal.kernel.search.Sort sort) throws RemoteException {
		try {
			java.util.List<com.liferay.commerce.product.model.CommerceCatalog> returnValue =
				CommerceCatalogServiceUtil.searchCommerceCatalogs(companyId,
					keywords, start, end, sort);

			return com.liferay.commerce.product.model.CommerceCatalogSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.product.model.CommerceCatalogSoap updateCommerceCatalog(
		long commerceCatalogId, String catalogDefaultLanguageId,
		String[] nameMapLanguageIds, String[] nameMapValues,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			Map<Locale, String> nameMap = LocalizationUtil.getLocalizationMap(nameMapLanguageIds,
					nameMapValues);

			com.liferay.commerce.product.model.CommerceCatalog returnValue = CommerceCatalogServiceUtil.updateCommerceCatalog(commerceCatalogId,
					catalogDefaultLanguageId, nameMap, serviceContext);

			return com.liferay.commerce.product.model.CommerceCatalogSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(CommerceCatalogServiceSoap.class);
}