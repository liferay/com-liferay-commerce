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

import com.liferay.commerce.product.service.CPOptionServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;

import java.rmi.RemoteException;

import java.util.Locale;
import java.util.Map;

/**
 * Provides the SOAP utility for the
 * {@link CPOptionServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.commerce.product.model.CPOptionSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.commerce.product.model.CPOption}, that is translated to a
 * {@link com.liferay.commerce.product.model.CPOptionSoap}. Methods that SOAP cannot
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
 * @see CPOptionServiceHttp
 * @see com.liferay.commerce.product.model.CPOptionSoap
 * @see CPOptionServiceUtil
 * @generated
 */
@ProviderType
public class CPOptionServiceSoap {
	public static com.liferay.commerce.product.model.CPOptionSoap addCPOption(
		String[] nameMapLanguageIds, String[] nameMapValues,
		String[] descriptionMapLanguageIds, String[] descriptionMapValues,
		String ddmFormFieldTypeName, boolean facetable, boolean required,
		boolean skuContributor, String key,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			Map<Locale, String> nameMap = LocalizationUtil.getLocalizationMap(nameMapLanguageIds,
					nameMapValues);
			Map<Locale, String> descriptionMap = LocalizationUtil.getLocalizationMap(descriptionMapLanguageIds,
					descriptionMapValues);

			com.liferay.commerce.product.model.CPOption returnValue = CPOptionServiceUtil.addCPOption(nameMap,
					descriptionMap, ddmFormFieldTypeName, facetable, required,
					skuContributor, key, serviceContext);

			return com.liferay.commerce.product.model.CPOptionSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteCPOption(long cpOptionId)
		throws RemoteException {
		try {
			CPOptionServiceUtil.deleteCPOption(cpOptionId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.product.model.CPOptionSoap fetchCPOption(
		long cpOptionId) throws RemoteException {
		try {
			com.liferay.commerce.product.model.CPOption returnValue = CPOptionServiceUtil.fetchCPOption(cpOptionId);

			return com.liferay.commerce.product.model.CPOptionSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.product.model.CPOptionSoap fetchCPOption(
		long groupId, String key) throws RemoteException {
		try {
			com.liferay.commerce.product.model.CPOption returnValue = CPOptionServiceUtil.fetchCPOption(groupId,
					key);

			return com.liferay.commerce.product.model.CPOptionSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.product.model.CPOptionSoap getCPOption(
		long cpOptionId) throws RemoteException {
		try {
			com.liferay.commerce.product.model.CPOption returnValue = CPOptionServiceUtil.getCPOption(cpOptionId);

			return com.liferay.commerce.product.model.CPOptionSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.product.model.CPOptionSoap fetchByExternalReferenceCode(
		long companyId, String externalReferenceCode) throws RemoteException {
		try {
			com.liferay.commerce.product.model.CPOption returnValue = CPOptionServiceUtil.fetchByExternalReferenceCode(companyId,
					externalReferenceCode);

			return com.liferay.commerce.product.model.CPOptionSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.product.model.CPOptionSoap[] getCPOptions(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.product.model.CPOption> orderByComparator)
		throws RemoteException {
		try {
			java.util.List<com.liferay.commerce.product.model.CPOption> returnValue =
				CPOptionServiceUtil.getCPOptions(groupId, start, end,
					orderByComparator);

			return com.liferay.commerce.product.model.CPOptionSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getCPOptionsCount(long groupId) throws RemoteException {
		try {
			int returnValue = CPOptionServiceUtil.getCPOptionsCount(groupId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.product.model.CPOptionSoap updateCPOption(
		long cpOptionId, String[] nameMapLanguageIds, String[] nameMapValues,
		String[] descriptionMapLanguageIds, String[] descriptionMapValues,
		String ddmFormFieldTypeName, boolean facetable, boolean required,
		boolean skuContributor, String key,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			Map<Locale, String> nameMap = LocalizationUtil.getLocalizationMap(nameMapLanguageIds,
					nameMapValues);
			Map<Locale, String> descriptionMap = LocalizationUtil.getLocalizationMap(descriptionMapLanguageIds,
					descriptionMapValues);

			com.liferay.commerce.product.model.CPOption returnValue = CPOptionServiceUtil.updateCPOption(cpOptionId,
					nameMap, descriptionMap, ddmFormFieldTypeName, facetable,
					required, skuContributor, key, serviceContext);

			return com.liferay.commerce.product.model.CPOptionSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.product.model.CPOptionSoap upsertCPOption(
		String[] nameMapLanguageIds, String[] nameMapValues,
		String[] descriptionMapLanguageIds, String[] descriptionMapValues,
		String ddmFormFieldTypeName, boolean facetable, boolean required,
		boolean skuContributor, String key, String externalReferenceCode,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			Map<Locale, String> nameMap = LocalizationUtil.getLocalizationMap(nameMapLanguageIds,
					nameMapValues);
			Map<Locale, String> descriptionMap = LocalizationUtil.getLocalizationMap(descriptionMapLanguageIds,
					descriptionMapValues);

			com.liferay.commerce.product.model.CPOption returnValue = CPOptionServiceUtil.upsertCPOption(nameMap,
					descriptionMap, ddmFormFieldTypeName, facetable, required,
					skuContributor, key, externalReferenceCode, serviceContext);

			return com.liferay.commerce.product.model.CPOptionSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(CPOptionServiceSoap.class);
}