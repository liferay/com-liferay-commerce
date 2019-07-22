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

package com.liferay.commerce.price.list.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.price.list.service.CommercePriceEntryServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>CommercePriceEntryServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.liferay.commerce.price.list.model.CommercePriceEntrySoap</code>. If the method in the
 * service utility returns a
 * <code>com.liferay.commerce.price.list.model.CommercePriceEntry</code>, that is translated to a
 * <code>com.liferay.commerce.price.list.model.CommercePriceEntrySoap</code>. Methods that SOAP
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
 * @author Alessio Antonio Rendina
 * @see CommercePriceEntryServiceHttp
 * @generated
 */
@ProviderType
public class CommercePriceEntryServiceSoap {

	public static com.liferay.commerce.price.list.model.CommercePriceEntrySoap
			addCommercePriceEntry(
				long cpInstanceId, long commercePriceListId,
				java.math.BigDecimal price, java.math.BigDecimal promoPrice,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			com.liferay.commerce.price.list.model.CommercePriceEntry
				returnValue =
					CommercePriceEntryServiceUtil.addCommercePriceEntry(
						cpInstanceId, commercePriceListId, price, promoPrice,
						serviceContext);

			return com.liferay.commerce.price.list.model.CommercePriceEntrySoap.
				toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.price.list.model.CommercePriceEntrySoap
			addCommercePriceEntry(
				long cpInstanceId, long commercePriceListId,
				String externalReferenceCode, java.math.BigDecimal price,
				java.math.BigDecimal promoPrice,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			com.liferay.commerce.price.list.model.CommercePriceEntry
				returnValue =
					CommercePriceEntryServiceUtil.addCommercePriceEntry(
						cpInstanceId, commercePriceListId,
						externalReferenceCode, price, promoPrice,
						serviceContext);

			return com.liferay.commerce.price.list.model.CommercePriceEntrySoap.
				toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteCommercePriceEntry(long commercePriceEntryId)
		throws RemoteException {

		try {
			CommercePriceEntryServiceUtil.deleteCommercePriceEntry(
				commercePriceEntryId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.price.list.model.CommercePriceEntrySoap
			fetchByExternalReferenceCode(
				long companyId, String externalReferenceCode)
		throws RemoteException {

		try {
			com.liferay.commerce.price.list.model.CommercePriceEntry
				returnValue =
					CommercePriceEntryServiceUtil.fetchByExternalReferenceCode(
						companyId, externalReferenceCode);

			return com.liferay.commerce.price.list.model.CommercePriceEntrySoap.
				toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.price.list.model.CommercePriceEntrySoap
			fetchCommercePriceEntry(long commercePriceEntryId)
		throws RemoteException {

		try {
			com.liferay.commerce.price.list.model.CommercePriceEntry
				returnValue =
					CommercePriceEntryServiceUtil.fetchCommercePriceEntry(
						commercePriceEntryId);

			return com.liferay.commerce.price.list.model.CommercePriceEntrySoap.
				toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.price.list.model.CommercePriceEntrySoap[]
			getCommercePriceEntries(
				long commercePriceListId, int start, int end)
		throws RemoteException {

		try {
			java.util.List
				<com.liferay.commerce.price.list.model.CommercePriceEntry>
					returnValue =
						CommercePriceEntryServiceUtil.getCommercePriceEntries(
							commercePriceListId, start, end);

			return com.liferay.commerce.price.list.model.CommercePriceEntrySoap.
				toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.price.list.model.CommercePriceEntrySoap[]
			getCommercePriceEntries(
				long commercePriceListId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.commerce.price.list.model.CommercePriceEntry>
						orderByComparator)
		throws RemoteException {

		try {
			java.util.List
				<com.liferay.commerce.price.list.model.CommercePriceEntry>
					returnValue =
						CommercePriceEntryServiceUtil.getCommercePriceEntries(
							commercePriceListId, start, end, orderByComparator);

			return com.liferay.commerce.price.list.model.CommercePriceEntrySoap.
				toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.price.list.model.CommercePriceEntrySoap[]
			getCommercePriceEntriesByCompanyId(
				long companyId, int start, int end)
		throws RemoteException {

		try {
			java.util.List
				<com.liferay.commerce.price.list.model.CommercePriceEntry>
					returnValue =
						CommercePriceEntryServiceUtil.
							getCommercePriceEntriesByCompanyId(
								companyId, start, end);

			return com.liferay.commerce.price.list.model.CommercePriceEntrySoap.
				toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getCommercePriceEntriesCount(long commercePriceListId)
		throws RemoteException {

		try {
			int returnValue =
				CommercePriceEntryServiceUtil.getCommercePriceEntriesCount(
					commercePriceListId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getCommercePriceEntriesCountByCompanyId(long companyId)
		throws RemoteException {

		try {
			int returnValue =
				CommercePriceEntryServiceUtil.
					getCommercePriceEntriesCountByCompanyId(companyId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.price.list.model.CommercePriceEntrySoap
			getCommercePriceEntry(long commercePriceEntryId)
		throws RemoteException {

		try {
			com.liferay.commerce.price.list.model.CommercePriceEntry
				returnValue =
					CommercePriceEntryServiceUtil.getCommercePriceEntry(
						commercePriceEntryId);

			return com.liferay.commerce.price.list.model.CommercePriceEntrySoap.
				toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.price.list.model.CommercePriceEntrySoap[]
			getInstanceCommercePriceEntries(
				long cpInstanceId, int start, int end)
		throws RemoteException {

		try {
			java.util.List
				<com.liferay.commerce.price.list.model.CommercePriceEntry>
					returnValue =
						CommercePriceEntryServiceUtil.
							getInstanceCommercePriceEntries(
								cpInstanceId, start, end);

			return com.liferay.commerce.price.list.model.CommercePriceEntrySoap.
				toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.price.list.model.CommercePriceEntrySoap[]
			getInstanceCommercePriceEntries(
				long cpInstanceId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.commerce.price.list.model.CommercePriceEntry>
						orderByComparator)
		throws RemoteException {

		try {
			java.util.List
				<com.liferay.commerce.price.list.model.CommercePriceEntry>
					returnValue =
						CommercePriceEntryServiceUtil.
							getInstanceCommercePriceEntries(
								cpInstanceId, start, end, orderByComparator);

			return com.liferay.commerce.price.list.model.CommercePriceEntrySoap.
				toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getInstanceCommercePriceEntriesCount(long cpInstanceId)
		throws RemoteException {

		try {
			int returnValue =
				CommercePriceEntryServiceUtil.
					getInstanceCommercePriceEntriesCount(cpInstanceId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.price.list.model.CommercePriceEntrySoap
			updateCommercePriceEntry(
				long commercePriceEntryId, java.math.BigDecimal price,
				java.math.BigDecimal promoPrice,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			com.liferay.commerce.price.list.model.CommercePriceEntry
				returnValue =
					CommercePriceEntryServiceUtil.updateCommercePriceEntry(
						commercePriceEntryId, price, promoPrice,
						serviceContext);

			return com.liferay.commerce.price.list.model.CommercePriceEntrySoap.
				toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.price.list.model.CommercePriceEntrySoap
			updateExternalReferenceCode(
				com.liferay.commerce.price.list.model.CommercePriceEntrySoap
					commercePriceEntry,
				String externalReferenceCode)
		throws RemoteException {

		try {
			com.liferay.commerce.price.list.model.CommercePriceEntry
				returnValue =
					CommercePriceEntryServiceUtil.updateExternalReferenceCode(
						com.liferay.commerce.price.list.model.impl.
							CommercePriceEntryModelImpl.toModel(
								commercePriceEntry),
						externalReferenceCode);

			return com.liferay.commerce.price.list.model.CommercePriceEntrySoap.
				toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	public static com.liferay.commerce.price.list.model.CommercePriceEntrySoap
			upsertCommercePriceEntry(
				long commercePriceEntryId, long cpInstanceId,
				long commercePriceListId, String externalReferenceCode,
				java.math.BigDecimal price, java.math.BigDecimal promoPrice,
				String skuExternalReferenceCode,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			com.liferay.commerce.price.list.model.CommercePriceEntry
				returnValue =
					CommercePriceEntryServiceUtil.upsertCommercePriceEntry(
						commercePriceEntryId, cpInstanceId, commercePriceListId,
						externalReferenceCode, price, promoPrice,
						skuExternalReferenceCode, serviceContext);

			return com.liferay.commerce.price.list.model.CommercePriceEntrySoap.
				toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.price.list.model.CommercePriceEntrySoap
			upsertCommercePriceEntry(
				long commercePriceEntryId, long cProductId,
				String cpInstanceUuid, long commercePriceListId,
				String externalReferenceCode, java.math.BigDecimal price,
				java.math.BigDecimal promoPrice,
				String skuExternalReferenceCode,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			com.liferay.commerce.price.list.model.CommercePriceEntry
				returnValue =
					CommercePriceEntryServiceUtil.upsertCommercePriceEntry(
						commercePriceEntryId, cProductId, cpInstanceUuid,
						commercePriceListId, externalReferenceCode, price,
						promoPrice, skuExternalReferenceCode, serviceContext);

			return com.liferay.commerce.price.list.model.CommercePriceEntrySoap.
				toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		CommercePriceEntryServiceSoap.class);

}