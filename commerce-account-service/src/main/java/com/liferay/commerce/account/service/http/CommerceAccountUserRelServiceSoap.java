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

package com.liferay.commerce.account.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.account.service.CommerceAccountUserRelServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link CommerceAccountUserRelServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.commerce.account.model.CommerceAccountUserRelSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.commerce.account.model.CommerceAccountUserRel}, that is translated to a
 * {@link com.liferay.commerce.account.model.CommerceAccountUserRelSoap}. Methods that SOAP cannot
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
 * @see CommerceAccountUserRelServiceHttp
 * @see com.liferay.commerce.account.model.CommerceAccountUserRelSoap
 * @see CommerceAccountUserRelServiceUtil
 * @generated
 */
@ProviderType
public class CommerceAccountUserRelServiceSoap {
	public static void addCommerceAccountUserRels(long commerceAccountId,
		long[] userIds, String[] emailAddresses, long[] roleIds,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			CommerceAccountUserRelServiceUtil.addCommerceAccountUserRels(commerceAccountId,
				userIds, emailAddresses, roleIds, serviceContext);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteCommerceAccountUserRel(long commerceAccountId,
		long userId) throws RemoteException {
		try {
			CommerceAccountUserRelServiceUtil.deleteCommerceAccountUserRel(commerceAccountId,
				userId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteCommerceAccountUserRels(long commerceAccountId)
		throws RemoteException {
		try {
			CommerceAccountUserRelServiceUtil.deleteCommerceAccountUserRels(commerceAccountId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteCommerceAccountUserRels(long commerceAccountId,
		long[] userIds) throws RemoteException {
		try {
			CommerceAccountUserRelServiceUtil.deleteCommerceAccountUserRels(commerceAccountId,
				userIds);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.account.model.CommerceAccountUserRelSoap[] getCommerceAccountUserRels(
		long commerceAccountId, int start, int end) throws RemoteException {
		try {
			java.util.List<com.liferay.commerce.account.model.CommerceAccountUserRel> returnValue =
				CommerceAccountUserRelServiceUtil.getCommerceAccountUserRels(commerceAccountId,
					start, end);

			return com.liferay.commerce.account.model.CommerceAccountUserRelSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getCommerceAccountUserRelsCount(long commerceAccountId)
		throws RemoteException {
		try {
			int returnValue = CommerceAccountUserRelServiceUtil.getCommerceAccountUserRelsCount(commerceAccountId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(CommerceAccountUserRelServiceSoap.class);
}