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
 * Provides the local service utility for CPRuleCommerceAccountGroupRel. This utility wraps
 * {@link com.liferay.commerce.product.service.impl.CPRuleCommerceAccountGroupRelLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Marco Leo
 * @see CPRuleCommerceAccountGroupRelLocalService
 * @see com.liferay.commerce.product.service.base.CPRuleCommerceAccountGroupRelLocalServiceBaseImpl
 * @see com.liferay.commerce.product.service.impl.CPRuleCommerceAccountGroupRelLocalServiceImpl
 * @generated
 */
@ProviderType
public class CPRuleCommerceAccountGroupRelLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.commerce.product.service.impl.CPRuleCommerceAccountGroupRelLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the cp rule commerce account group rel to the database. Also notifies the appropriate model listeners.
	*
	* @param cpRuleCommerceAccountGroupRel the cp rule commerce account group rel
	* @return the cp rule commerce account group rel that was added
	*/
	public static com.liferay.commerce.product.model.CPRuleCommerceAccountGroupRel addCPRuleCommerceAccountGroupRel(
		com.liferay.commerce.product.model.CPRuleCommerceAccountGroupRel cpRuleCommerceAccountGroupRel) {
		return getService()
				   .addCPRuleCommerceAccountGroupRel(cpRuleCommerceAccountGroupRel);
	}

	public static com.liferay.commerce.product.model.CPRuleCommerceAccountGroupRel addCPRuleCommerceAccountGroupRel(
		long cpRuleId, long commerceAccountGroupId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addCPRuleCommerceAccountGroupRel(cpRuleId,
			commerceAccountGroupId, serviceContext);
	}

	/**
	* Creates a new cp rule commerce account group rel with the primary key. Does not add the cp rule commerce account group rel to the database.
	*
	* @param CPRuleCommerceAccountGroupRelId the primary key for the new cp rule commerce account group rel
	* @return the new cp rule commerce account group rel
	*/
	public static com.liferay.commerce.product.model.CPRuleCommerceAccountGroupRel createCPRuleCommerceAccountGroupRel(
		long CPRuleCommerceAccountGroupRelId) {
		return getService()
				   .createCPRuleCommerceAccountGroupRel(CPRuleCommerceAccountGroupRelId);
	}

	/**
	* Deletes the cp rule commerce account group rel from the database. Also notifies the appropriate model listeners.
	*
	* @param cpRuleCommerceAccountGroupRel the cp rule commerce account group rel
	* @return the cp rule commerce account group rel that was removed
	* @throws PortalException
	*/
	public static com.liferay.commerce.product.model.CPRuleCommerceAccountGroupRel deleteCPRuleCommerceAccountGroupRel(
		com.liferay.commerce.product.model.CPRuleCommerceAccountGroupRel cpRuleCommerceAccountGroupRel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .deleteCPRuleCommerceAccountGroupRel(cpRuleCommerceAccountGroupRel);
	}

	/**
	* Deletes the cp rule commerce account group rel with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param CPRuleCommerceAccountGroupRelId the primary key of the cp rule commerce account group rel
	* @return the cp rule commerce account group rel that was removed
	* @throws PortalException if a cp rule commerce account group rel with the primary key could not be found
	*/
	public static com.liferay.commerce.product.model.CPRuleCommerceAccountGroupRel deleteCPRuleCommerceAccountGroupRel(
		long CPRuleCommerceAccountGroupRelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .deleteCPRuleCommerceAccountGroupRel(CPRuleCommerceAccountGroupRelId);
	}

	public static void deleteCPRuleCommerceAccountGroupRelsBycommerceAccountGroupId(
		long commerceAccountGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.deleteCPRuleCommerceAccountGroupRelsBycommerceAccountGroupId(commerceAccountGroupId);
	}

	public static void deleteCPRuleCommerceAccountGroupRelsByCPRuleId(
		long cpRuleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteCPRuleCommerceAccountGroupRelsByCPRuleId(cpRuleId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.product.model.impl.CPRuleCommerceAccountGroupRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.product.model.impl.CPRuleCommerceAccountGroupRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
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

	public static com.liferay.commerce.product.model.CPRuleCommerceAccountGroupRel fetchCPRuleCommerceAccountGroupRel(
		long CPRuleCommerceAccountGroupRelId) {
		return getService()
				   .fetchCPRuleCommerceAccountGroupRel(CPRuleCommerceAccountGroupRelId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns the cp rule commerce account group rel with the primary key.
	*
	* @param CPRuleCommerceAccountGroupRelId the primary key of the cp rule commerce account group rel
	* @return the cp rule commerce account group rel
	* @throws PortalException if a cp rule commerce account group rel with the primary key could not be found
	*/
	public static com.liferay.commerce.product.model.CPRuleCommerceAccountGroupRel getCPRuleCommerceAccountGroupRel(
		long CPRuleCommerceAccountGroupRelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getCPRuleCommerceAccountGroupRel(CPRuleCommerceAccountGroupRelId);
	}

	/**
	* Returns a range of all the cp rule commerce account group rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.product.model.impl.CPRuleCommerceAccountGroupRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cp rule commerce account group rels
	* @param end the upper bound of the range of cp rule commerce account group rels (not inclusive)
	* @return the range of cp rule commerce account group rels
	*/
	public static java.util.List<com.liferay.commerce.product.model.CPRuleCommerceAccountGroupRel> getCPRuleCommerceAccountGroupRels(
		int start, int end) {
		return getService().getCPRuleCommerceAccountGroupRels(start, end);
	}

	public static java.util.List<com.liferay.commerce.product.model.CPRuleCommerceAccountGroupRel> getCPRuleCommerceAccountGroupRels(
		long cpRuleId) {
		return getService().getCPRuleCommerceAccountGroupRels(cpRuleId);
	}

	public static java.util.List<com.liferay.commerce.product.model.CPRuleCommerceAccountGroupRel> getCPRuleCommerceAccountGroupRels(
		long cpRuleId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.product.model.CPRuleCommerceAccountGroupRel> orderByComparator) {
		return getService()
				   .getCPRuleCommerceAccountGroupRels(cpRuleId, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of cp rule commerce account group rels.
	*
	* @return the number of cp rule commerce account group rels
	*/
	public static int getCPRuleCommerceAccountGroupRelsCount() {
		return getService().getCPRuleCommerceAccountGroupRelsCount();
	}

	public static int getCPRuleCommerceAccountGroupRelsCount(long cpRuleId) {
		return getService().getCPRuleCommerceAccountGroupRelsCount(cpRuleId);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
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

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the cp rule commerce account group rel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param cpRuleCommerceAccountGroupRel the cp rule commerce account group rel
	* @return the cp rule commerce account group rel that was updated
	*/
	public static com.liferay.commerce.product.model.CPRuleCommerceAccountGroupRel updateCPRuleCommerceAccountGroupRel(
		com.liferay.commerce.product.model.CPRuleCommerceAccountGroupRel cpRuleCommerceAccountGroupRel) {
		return getService()
				   .updateCPRuleCommerceAccountGroupRel(cpRuleCommerceAccountGroupRel);
	}

	public static CPRuleCommerceAccountGroupRelLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CPRuleCommerceAccountGroupRelLocalService, CPRuleCommerceAccountGroupRelLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CPRuleCommerceAccountGroupRelLocalService.class);

		ServiceTracker<CPRuleCommerceAccountGroupRelLocalService, CPRuleCommerceAccountGroupRelLocalService> serviceTracker =
			new ServiceTracker<CPRuleCommerceAccountGroupRelLocalService, CPRuleCommerceAccountGroupRelLocalService>(bundle.getBundleContext(),
				CPRuleCommerceAccountGroupRelLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}