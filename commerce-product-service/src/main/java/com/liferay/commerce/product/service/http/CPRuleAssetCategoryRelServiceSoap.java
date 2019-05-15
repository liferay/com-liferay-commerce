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

import com.liferay.commerce.product.service.CPRuleAssetCategoryRelServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>CPRuleAssetCategoryRelServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.liferay.commerce.product.model.CPRuleAssetCategoryRelSoap</code>. If the method in the
 * service utility returns a
 * <code>com.liferay.commerce.product.model.CPRuleAssetCategoryRel</code>, that is translated to a
 * <code>com.liferay.commerce.product.model.CPRuleAssetCategoryRelSoap</code>. Methods that SOAP
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
 * @see CPRuleAssetCategoryRelServiceHttp
 * @generated
 */
@ProviderType
public class CPRuleAssetCategoryRelServiceSoap {

	public static com.liferay.commerce.product.model.CPRuleAssetCategoryRelSoap
			addCPRuleAssetCategoryRel(
				long cpRuleId, long assetCategoryId,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			com.liferay.commerce.product.model.CPRuleAssetCategoryRel
				returnValue =
					CPRuleAssetCategoryRelServiceUtil.addCPRuleAssetCategoryRel(
						cpRuleId, assetCategoryId, serviceContext);

			return com.liferay.commerce.product.model.
				CPRuleAssetCategoryRelSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteCPRuleAssetCategoryRel(
			long cpRuleAssetCategoryRelId)
		throws RemoteException {

		try {
			CPRuleAssetCategoryRelServiceUtil.deleteCPRuleAssetCategoryRel(
				cpRuleAssetCategoryRelId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static long[] getAssetCategoryIds(long cpRuleId)
		throws RemoteException {

		try {
			long[] returnValue =
				CPRuleAssetCategoryRelServiceUtil.getAssetCategoryIds(cpRuleId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static
		com.liferay.commerce.product.model.CPRuleAssetCategoryRelSoap[]
				getCPRuleAssetCategoryRels(long cpRuleId)
			throws RemoteException {

		try {
			java.util.List
				<com.liferay.commerce.product.model.CPRuleAssetCategoryRel>
					returnValue =
						CPRuleAssetCategoryRelServiceUtil.
							getCPRuleAssetCategoryRels(cpRuleId);

			return com.liferay.commerce.product.model.
				CPRuleAssetCategoryRelSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static
		com.liferay.commerce.product.model.CPRuleAssetCategoryRelSoap[]
				getCPRuleAssetCategoryRels(long cpRuleId, int start, int end)
			throws RemoteException {

		try {
			java.util.List
				<com.liferay.commerce.product.model.CPRuleAssetCategoryRel>
					returnValue =
						CPRuleAssetCategoryRelServiceUtil.
							getCPRuleAssetCategoryRels(cpRuleId, start, end);

			return com.liferay.commerce.product.model.
				CPRuleAssetCategoryRelSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getCPRuleAssetCategoryRelsCount(long cpRuleId)
		throws RemoteException {

		try {
			int returnValue =
				CPRuleAssetCategoryRelServiceUtil.
					getCPRuleAssetCategoryRelsCount(cpRuleId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		CPRuleAssetCategoryRelServiceSoap.class);

}