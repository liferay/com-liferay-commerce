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

package com.liferay.commerce.application.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.application.service.CommerceApplicationBrandServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link CommerceApplicationBrandServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.commerce.application.model.CommerceApplicationBrandSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.commerce.application.model.CommerceApplicationBrand}, that is translated to a
 * {@link com.liferay.commerce.application.model.CommerceApplicationBrandSoap}. Methods that SOAP cannot
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
 * @author Luca Pellizzon
 * @see CommerceApplicationBrandServiceHttp
 * @see com.liferay.commerce.application.model.CommerceApplicationBrandSoap
 * @see CommerceApplicationBrandServiceUtil
 * @generated
 */
@ProviderType
public class CommerceApplicationBrandServiceSoap {
	public static com.liferay.commerce.application.model.CommerceApplicationBrandSoap addCommerceApplicationBrand(
		long userId, String name, boolean logo, byte[] logoBytes)
		throws RemoteException {
		try {
			com.liferay.commerce.application.model.CommerceApplicationBrand returnValue =
				CommerceApplicationBrandServiceUtil.addCommerceApplicationBrand(userId,
					name, logo, logoBytes);

			return com.liferay.commerce.application.model.CommerceApplicationBrandSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteCommerceApplicationBrand(
		long commerceApplicationBrandId) throws RemoteException {
		try {
			CommerceApplicationBrandServiceUtil.deleteCommerceApplicationBrand(commerceApplicationBrandId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.application.model.CommerceApplicationBrandSoap getCommerceApplicationBrand(
		long commerceApplicationBrandId) throws RemoteException {
		try {
			com.liferay.commerce.application.model.CommerceApplicationBrand returnValue =
				CommerceApplicationBrandServiceUtil.getCommerceApplicationBrand(commerceApplicationBrandId);

			return com.liferay.commerce.application.model.CommerceApplicationBrandSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.application.model.CommerceApplicationBrandSoap[] getCommerceApplicationBrands(
		long companyId, int start, int end) throws RemoteException {
		try {
			java.util.List<com.liferay.commerce.application.model.CommerceApplicationBrand> returnValue =
				CommerceApplicationBrandServiceUtil.getCommerceApplicationBrands(companyId,
					start, end);

			return com.liferay.commerce.application.model.CommerceApplicationBrandSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getCommerceApplicationBrandsCount(long companyId)
		throws RemoteException {
		try {
			int returnValue = CommerceApplicationBrandServiceUtil.getCommerceApplicationBrandsCount(companyId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.application.model.CommerceApplicationBrandSoap updateCommerceApplicationBrand(
		long commerceApplicationBrandId, String name, boolean logo,
		byte[] logoBytes) throws RemoteException {
		try {
			com.liferay.commerce.application.model.CommerceApplicationBrand returnValue =
				CommerceApplicationBrandServiceUtil.updateCommerceApplicationBrand(commerceApplicationBrandId,
					name, logo, logoBytes);

			return com.liferay.commerce.application.model.CommerceApplicationBrandSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(CommerceApplicationBrandServiceSoap.class);
}