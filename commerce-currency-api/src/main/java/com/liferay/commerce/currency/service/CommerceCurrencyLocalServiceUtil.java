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

package com.liferay.commerce.currency.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for CommerceCurrency. This utility wraps
 * <code>com.liferay.commerce.currency.service.impl.CommerceCurrencyLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Andrea Di Giorgi
 * @see CommerceCurrencyLocalService
 * @generated
 */
public class CommerceCurrencyLocalServiceUtil {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.currency.service.impl.CommerceCurrencyLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the commerce currency to the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceCurrency the commerce currency
	 * @return the commerce currency that was added
	 */
	public static com.liferay.commerce.currency.model.CommerceCurrency
		addCommerceCurrency(
			com.liferay.commerce.currency.model.CommerceCurrency
				commerceCurrency) {

		return getService().addCommerceCurrency(commerceCurrency);
	}

	public static com.liferay.commerce.currency.model.CommerceCurrency
			addCommerceCurrency(
				long userId, String code,
				java.util.Map<java.util.Locale, String> nameMap,
				java.math.BigDecimal rate,
				java.util.Map<java.util.Locale, String> formatPatternMap,
				int maxFractionDigits, int minFractionDigits,
				String roundingMode, boolean primary, double priority,
				boolean active,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addCommerceCurrency(
			userId, code, nameMap, rate, formatPatternMap, maxFractionDigits,
			minFractionDigits, roundingMode, primary, priority, active,
			serviceContext);
	}

	/**
	 * Creates a new commerce currency with the primary key. Does not add the commerce currency to the database.
	 *
	 * @param commerceCurrencyId the primary key for the new commerce currency
	 * @return the new commerce currency
	 */
	public static com.liferay.commerce.currency.model.CommerceCurrency
		createCommerceCurrency(long commerceCurrencyId) {

		return getService().createCommerceCurrency(commerceCurrencyId);
	}

	public static void deleteCommerceCurrencies(long companyId) {
		getService().deleteCommerceCurrencies(companyId);
	}

	/**
	 * Deletes the commerce currency from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceCurrency the commerce currency
	 * @return the commerce currency that was removed
	 */
	public static com.liferay.commerce.currency.model.CommerceCurrency
		deleteCommerceCurrency(
			com.liferay.commerce.currency.model.CommerceCurrency
				commerceCurrency) {

		return getService().deleteCommerceCurrency(commerceCurrency);
	}

	/**
	 * Deletes the commerce currency with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceCurrencyId the primary key of the commerce currency
	 * @return the commerce currency that was removed
	 * @throws PortalException if a commerce currency with the primary key could not be found
	 */
	public static com.liferay.commerce.currency.model.CommerceCurrency
			deleteCommerceCurrency(long commerceCurrencyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteCommerceCurrency(commerceCurrencyId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.currency.model.impl.CommerceCurrencyModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.currency.model.impl.CommerceCurrencyModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.commerce.currency.model.CommerceCurrency
		fetchCommerceCurrency(long commerceCurrencyId) {

		return getService().fetchCommerceCurrency(commerceCurrencyId);
	}

	/**
	 * Returns the commerce currency with the matching UUID and company.
	 *
	 * @param uuid the commerce currency's UUID
	 * @param companyId the primary key of the company
	 * @return the matching commerce currency, or <code>null</code> if a matching commerce currency could not be found
	 */
	public static com.liferay.commerce.currency.model.CommerceCurrency
		fetchCommerceCurrencyByUuidAndCompanyId(String uuid, long companyId) {

		return getService().fetchCommerceCurrencyByUuidAndCompanyId(
			uuid, companyId);
	}

	public static com.liferay.commerce.currency.model.CommerceCurrency
		fetchPrimaryCommerceCurrency(long companyId) {

		return getService().fetchPrimaryCommerceCurrency(companyId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns a range of all the commerce currencies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.commerce.currency.model.impl.CommerceCurrencyModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce currencies
	 * @param end the upper bound of the range of commerce currencies (not inclusive)
	 * @return the range of commerce currencies
	 */
	public static java.util.List
		<com.liferay.commerce.currency.model.CommerceCurrency>
			getCommerceCurrencies(int start, int end) {

		return getService().getCommerceCurrencies(start, end);
	}

	public static java.util.List
		<com.liferay.commerce.currency.model.CommerceCurrency>
			getCommerceCurrencies(long companyId, boolean active) {

		return getService().getCommerceCurrencies(companyId, active);
	}

	public static java.util.List
		<com.liferay.commerce.currency.model.CommerceCurrency>
			getCommerceCurrencies(
				long companyId, boolean active, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.commerce.currency.model.CommerceCurrency>
						orderByComparator) {

		return getService().getCommerceCurrencies(
			companyId, active, start, end, orderByComparator);
	}

	public static java.util.List
		<com.liferay.commerce.currency.model.CommerceCurrency>
			getCommerceCurrencies(
				long companyId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.commerce.currency.model.CommerceCurrency>
						orderByComparator) {

		return getService().getCommerceCurrencies(
			companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of commerce currencies.
	 *
	 * @return the number of commerce currencies
	 */
	public static int getCommerceCurrenciesCount() {
		return getService().getCommerceCurrenciesCount();
	}

	public static int getCommerceCurrenciesCount(long companyId) {
		return getService().getCommerceCurrenciesCount(companyId);
	}

	public static int getCommerceCurrenciesCount(
		long companyId, boolean active) {

		return getService().getCommerceCurrenciesCount(companyId, active);
	}

	/**
	 * Returns the commerce currency with the primary key.
	 *
	 * @param commerceCurrencyId the primary key of the commerce currency
	 * @return the commerce currency
	 * @throws PortalException if a commerce currency with the primary key could not be found
	 */
	public static com.liferay.commerce.currency.model.CommerceCurrency
			getCommerceCurrency(long commerceCurrencyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCommerceCurrency(commerceCurrencyId);
	}

	public static com.liferay.commerce.currency.model.CommerceCurrency
			getCommerceCurrency(long companyId, String code)
		throws com.liferay.commerce.currency.exception.NoSuchCurrencyException {

		return getService().getCommerceCurrency(companyId, code);
	}

	/**
	 * Returns the commerce currency with the matching UUID and company.
	 *
	 * @param uuid the commerce currency's UUID
	 * @param companyId the primary key of the company
	 * @return the matching commerce currency
	 * @throws PortalException if a matching commerce currency could not be found
	 */
	public static com.liferay.commerce.currency.model.CommerceCurrency
			getCommerceCurrencyByUuidAndCompanyId(String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCommerceCurrencyByUuidAndCompanyId(
			uuid, companyId);
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

	public static void importDefaultValues(
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws Exception {

		getService().importDefaultValues(serviceContext);
	}

	public static com.liferay.commerce.currency.model.CommerceCurrency
			setActive(long commerceCurrencyId, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().setActive(commerceCurrencyId, active);
	}

	public static com.liferay.commerce.currency.model.CommerceCurrency
			setPrimary(long commerceCurrencyId, boolean primary)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().setPrimary(commerceCurrencyId, primary);
	}

	/**
	 * Updates the commerce currency in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param commerceCurrency the commerce currency
	 * @return the commerce currency that was updated
	 */
	public static com.liferay.commerce.currency.model.CommerceCurrency
		updateCommerceCurrency(
			com.liferay.commerce.currency.model.CommerceCurrency
				commerceCurrency) {

		return getService().updateCommerceCurrency(commerceCurrency);
	}

	public static com.liferay.commerce.currency.model.CommerceCurrency
			updateCommerceCurrency(
				long commerceCurrencyId, String code,
				java.util.Map<java.util.Locale, String> nameMap,
				java.math.BigDecimal rate,
				java.util.Map<java.util.Locale, String> formatPatternMap,
				int maxFractionDigits, int minFractionDigits,
				String roundingMode, boolean primary, double priority,
				boolean active,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateCommerceCurrency(
			commerceCurrencyId, code, nameMap, rate, formatPatternMap,
			maxFractionDigits, minFractionDigits, roundingMode, primary,
			priority, active, serviceContext);
	}

	public static com.liferay.commerce.currency.model.CommerceCurrency
			updateCommerceCurrencyRate(
				long commerceCurrencyId, java.math.BigDecimal rate)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateCommerceCurrencyRate(
			commerceCurrencyId, rate);
	}

	public static void updateExchangeRate(
			long commerceCurrencyId, String exchangeRateProviderKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().updateExchangeRate(
			commerceCurrencyId, exchangeRateProviderKey);
	}

	public static void updateExchangeRates()
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().updateExchangeRates();
	}

	public static CommerceCurrencyLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CommerceCurrencyLocalService, CommerceCurrencyLocalService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			CommerceCurrencyLocalService.class);

		ServiceTracker
			<CommerceCurrencyLocalService, CommerceCurrencyLocalService>
				serviceTracker =
					new ServiceTracker
						<CommerceCurrencyLocalService,
						 CommerceCurrencyLocalService>(
							 bundle.getBundleContext(),
							 CommerceCurrencyLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}