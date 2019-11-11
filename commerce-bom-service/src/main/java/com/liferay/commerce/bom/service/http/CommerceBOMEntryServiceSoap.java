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

package com.liferay.commerce.bom.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.bom.service.CommerceBOMEntryServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link CommerceBOMEntryServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.commerce.bom.model.CommerceBOMEntrySoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.commerce.bom.model.CommerceBOMEntry}, that is translated to a
 * {@link com.liferay.commerce.bom.model.CommerceBOMEntrySoap}. Methods that SOAP cannot
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
 * @see CommerceBOMEntryServiceHttp
 * @see com.liferay.commerce.bom.model.CommerceBOMEntrySoap
 * @see CommerceBOMEntryServiceUtil
 * @generated
 */
@ProviderType
public class CommerceBOMEntryServiceSoap {
	public static com.liferay.commerce.bom.model.CommerceBOMEntrySoap addCommerceBOMEntry(
		long userId, int number, String cpInstanceUuid, long cProductId,
		long commerceBOMDefinitionId, double positionX, double positionY,
		double radius) throws RemoteException {
		try {
			com.liferay.commerce.bom.model.CommerceBOMEntry returnValue = CommerceBOMEntryServiceUtil.addCommerceBOMEntry(userId,
					number, cpInstanceUuid, cProductId,
					commerceBOMDefinitionId, positionX, positionY, radius);

			return com.liferay.commerce.bom.model.CommerceBOMEntrySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteCommerceBOMEntry(long commerceBOMEntryId)
		throws RemoteException {
		try {
			CommerceBOMEntryServiceUtil.deleteCommerceBOMEntry(commerceBOMEntryId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.bom.model.CommerceBOMEntrySoap getCommerceBOMEntry(
		long commerceBOMEntryId) throws RemoteException {
		try {
			com.liferay.commerce.bom.model.CommerceBOMEntry returnValue = CommerceBOMEntryServiceUtil.getCommerceBOMEntry(commerceBOMEntryId);

			return com.liferay.commerce.bom.model.CommerceBOMEntrySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.bom.model.CommerceBOMEntrySoap[] getCommerceBOMEntries(
		long commerceBOMDefinitionId, int start, int end)
		throws RemoteException {
		try {
			java.util.List<com.liferay.commerce.bom.model.CommerceBOMEntry> returnValue =
				CommerceBOMEntryServiceUtil.getCommerceBOMEntries(commerceBOMDefinitionId,
					start, end);

			return com.liferay.commerce.bom.model.CommerceBOMEntrySoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getCommerceBOMEntriesCount(long commerceBOMDefinitionId)
		throws RemoteException {
		try {
			int returnValue = CommerceBOMEntryServiceUtil.getCommerceBOMEntriesCount(commerceBOMDefinitionId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.bom.model.CommerceBOMEntrySoap updateCommerceBOMEntry(
		long commerceBOMEntryId, int number, String cpInstanceUuid,
		long cProductId, double positionX, double positionY, double radius)
		throws RemoteException {
		try {
			com.liferay.commerce.bom.model.CommerceBOMEntry returnValue = CommerceBOMEntryServiceUtil.updateCommerceBOMEntry(commerceBOMEntryId,
					number, cpInstanceUuid, cProductId, positionX, positionY,
					radius);

			return com.liferay.commerce.bom.model.CommerceBOMEntrySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(CommerceBOMEntryServiceSoap.class);
}