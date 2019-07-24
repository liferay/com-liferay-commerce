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

package com.liferay.commerce.product.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for CPDefinitionLink. This utility wraps
 * <code>com.liferay.commerce.product.service.impl.CPDefinitionLinkLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Marco Leo
 * @see CPDefinitionLinkLocalService
 * @generated
 */
@ProviderType
public class CPDefinitionLinkLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.product.service.impl.CPDefinitionLinkLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the cp definition link to the database. Also notifies the appropriate model listeners.
	 *
	 * @param cpDefinitionLink the cp definition link
	 * @return the cp definition link that was added
	 */
	public static com.liferay.commerce.product.model.CPDefinitionLink
		addCPDefinitionLink(
			com.liferay.commerce.product.model.CPDefinitionLink
				cpDefinitionLink) {

		return getService().addCPDefinitionLink(cpDefinitionLink);
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	public static com.liferay.commerce.product.model.CPDefinitionLink
			addCPDefinitionLink(
				long cpDefinitionId1, long cpDefinitionId2, double priority,
				String type,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addCPDefinitionLink(
			cpDefinitionId1, cpDefinitionId2, priority, type, serviceContext);
	}

	public static com.liferay.commerce.product.model.CPDefinitionLink
			addCPDefinitionLinkByCProductId(
				long cpDefinitionId, long cProductId, double priority,
				String type,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addCPDefinitionLinkByCProductId(
			cpDefinitionId, cProductId, priority, type, serviceContext);
	}

	/**
	 * Creates a new cp definition link with the primary key. Does not add the cp definition link to the database.
	 *
	 * @param CPDefinitionLinkId the primary key for the new cp definition link
	 * @return the new cp definition link
	 */
	public static com.liferay.commerce.product.model.CPDefinitionLink
		createCPDefinitionLink(long CPDefinitionLinkId) {

		return getService().createCPDefinitionLink(CPDefinitionLinkId);
	}

	/**
	 * Deletes the cp definition link from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cpDefinitionLink the cp definition link
	 * @return the cp definition link that was removed
	 * @throws PortalException
	 */
	public static com.liferay.commerce.product.model.CPDefinitionLink
			deleteCPDefinitionLink(
				com.liferay.commerce.product.model.CPDefinitionLink
					cpDefinitionLink)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteCPDefinitionLink(cpDefinitionLink);
	}

	/**
	 * Deletes the cp definition link with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param CPDefinitionLinkId the primary key of the cp definition link
	 * @return the cp definition link that was removed
	 * @throws PortalException if a cp definition link with the primary key could not be found
	 */
	public static com.liferay.commerce.product.model.CPDefinitionLink
			deleteCPDefinitionLink(long CPDefinitionLinkId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteCPDefinitionLink(CPDefinitionLinkId);
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	public static void deleteCPDefinitionLinks(long cpDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteCPDefinitionLinks(cpDefinitionId);
	}

	public static void deleteCPDefinitionLinksByCPDefinitionId(
			long cpDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteCPDefinitionLinksByCPDefinitionId(cpDefinitionId);
	}

	public static void deleteCPDefinitionLinksByCProductId(long cProductId)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteCPDefinitionLinksByCProductId(cProductId);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.product.model.impl.CPDefinitionLinkModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.product.model.impl.CPDefinitionLinkModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.commerce.product.model.CPDefinitionLink
		fetchCPDefinitionLink(long CPDefinitionLinkId) {

		return getService().fetchCPDefinitionLink(CPDefinitionLinkId);
	}

	/**
	 * Returns the cp definition link matching the UUID and group.
	 *
	 * @param uuid the cp definition link's UUID
	 * @param groupId the primary key of the group
	 * @return the matching cp definition link, or <code>null</code> if a matching cp definition link could not be found
	 */
	public static com.liferay.commerce.product.model.CPDefinitionLink
		fetchCPDefinitionLinkByUuidAndGroupId(String uuid, long groupId) {

		return getService().fetchCPDefinitionLinkByUuidAndGroupId(
			uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the cp definition link with the primary key.
	 *
	 * @param CPDefinitionLinkId the primary key of the cp definition link
	 * @return the cp definition link
	 * @throws PortalException if a cp definition link with the primary key could not be found
	 */
	public static com.liferay.commerce.product.model.CPDefinitionLink
			getCPDefinitionLink(long CPDefinitionLinkId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCPDefinitionLink(CPDefinitionLinkId);
	}

	/**
	 * Returns the cp definition link matching the UUID and group.
	 *
	 * @param uuid the cp definition link's UUID
	 * @param groupId the primary key of the group
	 * @return the matching cp definition link
	 * @throws PortalException if a matching cp definition link could not be found
	 */
	public static com.liferay.commerce.product.model.CPDefinitionLink
			getCPDefinitionLinkByUuidAndGroupId(String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCPDefinitionLinkByUuidAndGroupId(uuid, groupId);
	}

	/**
	 * Returns a range of all the cp definition links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.product.model.impl.CPDefinitionLinkModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp definition links
	 * @param end the upper bound of the range of cp definition links (not inclusive)
	 * @return the range of cp definition links
	 */
	public static java.util.List
		<com.liferay.commerce.product.model.CPDefinitionLink>
			getCPDefinitionLinks(int start, int end) {

		return getService().getCPDefinitionLinks(start, end);
	}

	public static java.util.List
		<com.liferay.commerce.product.model.CPDefinitionLink>
			getCPDefinitionLinks(long cpDefinitionId) {

		return getService().getCPDefinitionLinks(cpDefinitionId);
	}

	public static java.util.List
		<com.liferay.commerce.product.model.CPDefinitionLink>
			getCPDefinitionLinks(long cpDefinitionId, int start, int end) {

		return getService().getCPDefinitionLinks(cpDefinitionId, start, end);
	}

	public static java.util.List
		<com.liferay.commerce.product.model.CPDefinitionLink>
			getCPDefinitionLinks(long cpDefinitionId, String type) {

		return getService().getCPDefinitionLinks(cpDefinitionId, type);
	}

	public static java.util.List
		<com.liferay.commerce.product.model.CPDefinitionLink>
			getCPDefinitionLinks(
				long cpDefinitionId, String type, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.commerce.product.model.CPDefinitionLink>
						orderByComparator) {

		return getService().getCPDefinitionLinks(
			cpDefinitionId, type, start, end, orderByComparator);
	}

	/**
	 * Returns all the cp definition links matching the UUID and company.
	 *
	 * @param uuid the UUID of the cp definition links
	 * @param companyId the primary key of the company
	 * @return the matching cp definition links, or an empty list if no matches were found
	 */
	public static java.util.List
		<com.liferay.commerce.product.model.CPDefinitionLink>
			getCPDefinitionLinksByUuidAndCompanyId(
				String uuid, long companyId) {

		return getService().getCPDefinitionLinksByUuidAndCompanyId(
			uuid, companyId);
	}

	/**
	 * Returns a range of cp definition links matching the UUID and company.
	 *
	 * @param uuid the UUID of the cp definition links
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of cp definition links
	 * @param end the upper bound of the range of cp definition links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching cp definition links, or an empty list if no matches were found
	 */
	public static java.util.List
		<com.liferay.commerce.product.model.CPDefinitionLink>
			getCPDefinitionLinksByUuidAndCompanyId(
				String uuid, long companyId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.commerce.product.model.CPDefinitionLink>
						orderByComparator) {

		return getService().getCPDefinitionLinksByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of cp definition links.
	 *
	 * @return the number of cp definition links
	 */
	public static int getCPDefinitionLinksCount() {
		return getService().getCPDefinitionLinksCount();
	}

	public static int getCPDefinitionLinksCount(long cpDefinitionId) {
		return getService().getCPDefinitionLinksCount(cpDefinitionId);
	}

	public static int getCPDefinitionLinksCount(
		long cpDefinitionId, String type) {

		return getService().getCPDefinitionLinksCount(cpDefinitionId, type);
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	public static java.util.List
		<com.liferay.commerce.product.model.CPDefinitionLink>
			getReverseCPDefinitionLinks(long cProductId, String type) {

		return getService().getReverseCPDefinitionLinks(cProductId, type);
	}

	/**
	 * Updates the cp definition link in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param cpDefinitionLink the cp definition link
	 * @return the cp definition link that was updated
	 */
	public static com.liferay.commerce.product.model.CPDefinitionLink
		updateCPDefinitionLink(
			com.liferay.commerce.product.model.CPDefinitionLink
				cpDefinitionLink) {

		return getService().updateCPDefinitionLink(cpDefinitionLink);
	}

	public static com.liferay.commerce.product.model.CPDefinitionLink
			updateCPDefinitionLink(
				long cpDefinitionLinkId, double priority,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateCPDefinitionLink(
			cpDefinitionLinkId, priority, serviceContext);
	}

	public static void updateCPDefinitionLinkCProductIds(
			long cpDefinitionId, long[] cProductIds, String type,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().updateCPDefinitionLinkCProductIds(
			cpDefinitionId, cProductIds, type, serviceContext);
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	public static void updateCPDefinitionLinks(
			long cpDefinitionId1, long[] cpDefinitionIds2, String type,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().updateCPDefinitionLinks(
			cpDefinitionId1, cpDefinitionIds2, type, serviceContext);
	}

	public static CPDefinitionLinkLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CPDefinitionLinkLocalService, CPDefinitionLinkLocalService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			CPDefinitionLinkLocalService.class);

		ServiceTracker
			<CPDefinitionLinkLocalService, CPDefinitionLinkLocalService>
				serviceTracker =
					new ServiceTracker
						<CPDefinitionLinkLocalService,
						 CPDefinitionLinkLocalService>(
							 bundle.getBundleContext(),
							 CPDefinitionLinkLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}