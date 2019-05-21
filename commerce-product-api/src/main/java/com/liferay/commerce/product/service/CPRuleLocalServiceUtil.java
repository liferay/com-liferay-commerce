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
 * Provides the local service utility for CPRule. This utility wraps
 * <code>com.liferay.commerce.product.service.impl.CPRuleLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Marco Leo
 * @see CPRuleLocalService
 * @generated
 */
@ProviderType
public class CPRuleLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.product.service.impl.CPRuleLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the cp rule to the database. Also notifies the appropriate model listeners.
	 *
	 * @param cpRule the cp rule
	 * @return the cp rule that was added
	 */
	public static com.liferay.commerce.product.model.CPRule addCPRule(
		com.liferay.commerce.product.model.CPRule cpRule) {

		return getService().addCPRule(cpRule);
	}

	public static com.liferay.commerce.product.model.CPRule addCPRule(
			String name, boolean active, String type,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addCPRule(name, active, type, serviceContext);
	}

	public static com.liferay.commerce.product.model.CPRule addCPRule(
			String name, boolean active, String type,
			com.liferay.portal.kernel.util.UnicodeProperties
				typeSettingsProperties,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addCPRule(
			name, active, type, typeSettingsProperties, serviceContext);
	}

	public static void cleanCPRulesCache(long groupId) {
		getService().cleanCPRulesCache(groupId);
	}

	/**
	 * Creates a new cp rule with the primary key. Does not add the cp rule to the database.
	 *
	 * @param CPRuleId the primary key for the new cp rule
	 * @return the new cp rule
	 */
	public static com.liferay.commerce.product.model.CPRule createCPRule(
		long CPRuleId) {

		return getService().createCPRule(CPRuleId);
	}

	/**
	 * Deletes the cp rule from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cpRule the cp rule
	 * @return the cp rule that was removed
	 * @throws PortalException
	 */
	public static com.liferay.commerce.product.model.CPRule deleteCPRule(
			com.liferay.commerce.product.model.CPRule cpRule)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteCPRule(cpRule);
	}

	/**
	 * Deletes the cp rule with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param CPRuleId the primary key of the cp rule
	 * @return the cp rule that was removed
	 * @throws PortalException if a cp rule with the primary key could not be found
	 */
	public static com.liferay.commerce.product.model.CPRule deleteCPRule(
			long CPRuleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteCPRule(CPRuleId);
	}

	public static void deleteCPRules(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteCPRules(groupId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.product.model.impl.CPRuleModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.product.model.impl.CPRuleModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.commerce.product.model.CPRule fetchCPRule(
		long CPRuleId) {

		return getService().fetchCPRule(CPRuleId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the cp rule with the primary key.
	 *
	 * @param CPRuleId the primary key of the cp rule
	 * @return the cp rule
	 * @throws PortalException if a cp rule with the primary key could not be found
	 */
	public static com.liferay.commerce.product.model.CPRule getCPRule(
			long CPRuleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCPRule(CPRuleId);
	}

	/**
	 * Returns a range of all the cp rules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.product.model.impl.CPRuleModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp rules
	 * @param end the upper bound of the range of cp rules (not inclusive)
	 * @return the range of cp rules
	 */
	public static java.util.List<com.liferay.commerce.product.model.CPRule>
		getCPRules(int start, int end) {

		return getService().getCPRules(start, end);
	}

	public static java.util.List<com.liferay.commerce.product.model.CPRule>
		getCPRules(
			long groupId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.commerce.product.model.CPRule> orderByComparator) {

		return getService().getCPRules(groupId, start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.commerce.product.model.CPRule>
			getCPRules(long groupId, long[] commerceUserSegmentEntryIds)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCPRules(groupId, commerceUserSegmentEntryIds);
	}

	/**
	 * Returns the number of cp rules.
	 *
	 * @return the number of cp rules
	 */
	public static int getCPRulesCount() {
		return getService().getCPRulesCount();
	}

	public static int getCPRulesCount(long groupId) {
		return getService().getCPRulesCount(groupId);
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

	public static com.liferay.portal.kernel.search.BaseModelSearchResult
		<com.liferay.commerce.product.model.CPRule> searchCPRules(
				long companyId, long groupId, String keywords, int start,
				int end, com.liferay.portal.kernel.search.Sort sort)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().searchCPRules(
			companyId, groupId, keywords, start, end, sort);
	}

	public static com.liferay.portal.kernel.search.BaseModelSearchResult
		<com.liferay.commerce.product.model.CPRule> searchCPRules(
				com.liferay.portal.kernel.search.SearchContext searchContext)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().searchCPRules(searchContext);
	}

	/**
	 * Updates the cp rule in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param cpRule the cp rule
	 * @return the cp rule that was updated
	 */
	public static com.liferay.commerce.product.model.CPRule updateCPRule(
		com.liferay.commerce.product.model.CPRule cpRule) {

		return getService().updateCPRule(cpRule);
	}

	public static com.liferay.commerce.product.model.CPRule updateCPRule(
			long cpRuleId, String name, boolean active, String type,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateCPRule(
			cpRuleId, name, active, type, serviceContext);
	}

	public static com.liferay.commerce.product.model.CPRule updateCPRule(
			long cpRuleId, String name, boolean active, String type,
			com.liferay.portal.kernel.util.UnicodeProperties
				typeSettingsProperties,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateCPRule(
			cpRuleId, name, active, type, typeSettingsProperties,
			serviceContext);
	}

	public static CPRuleLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CPRuleLocalService, CPRuleLocalService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CPRuleLocalService.class);

		ServiceTracker<CPRuleLocalService, CPRuleLocalService> serviceTracker =
			new ServiceTracker<CPRuleLocalService, CPRuleLocalService>(
				bundle.getBundleContext(), CPRuleLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}