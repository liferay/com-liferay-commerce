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

package com.liferay.commerce.tax.engine.fixed.service.persistence;

import com.liferay.commerce.tax.engine.fixed.model.CommerceTaxFixedRate;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the commerce tax fixed rate service. This utility wraps <code>com.liferay.commerce.tax.engine.fixed.service.persistence.impl.CommerceTaxFixedRatePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommerceTaxFixedRatePersistence
 * @generated
 */
public class CommerceTaxFixedRateUtil {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(CommerceTaxFixedRate commerceTaxFixedRate) {
		getPersistence().clearCache(commerceTaxFixedRate);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, CommerceTaxFixedRate> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<CommerceTaxFixedRate> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CommerceTaxFixedRate> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CommerceTaxFixedRate> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CommerceTaxFixedRate> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CommerceTaxFixedRate update(
		CommerceTaxFixedRate commerceTaxFixedRate) {

		return getPersistence().update(commerceTaxFixedRate);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CommerceTaxFixedRate update(
		CommerceTaxFixedRate commerceTaxFixedRate,
		ServiceContext serviceContext) {

		return getPersistence().update(commerceTaxFixedRate, serviceContext);
	}

	/**
	 * Returns all the commerce tax fixed rates where CPTaxCategoryId = &#63;.
	 *
	 * @param CPTaxCategoryId the cp tax category ID
	 * @return the matching commerce tax fixed rates
	 */
	public static List<CommerceTaxFixedRate> findByCPTaxCategoryId(
		long CPTaxCategoryId) {

		return getPersistence().findByCPTaxCategoryId(CPTaxCategoryId);
	}

	/**
	 * Returns a range of all the commerce tax fixed rates where CPTaxCategoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceTaxFixedRateModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPTaxCategoryId the cp tax category ID
	 * @param start the lower bound of the range of commerce tax fixed rates
	 * @param end the upper bound of the range of commerce tax fixed rates (not inclusive)
	 * @return the range of matching commerce tax fixed rates
	 */
	public static List<CommerceTaxFixedRate> findByCPTaxCategoryId(
		long CPTaxCategoryId, int start, int end) {

		return getPersistence().findByCPTaxCategoryId(
			CPTaxCategoryId, start, end);
	}

	/**
	 * Returns an ordered range of all the commerce tax fixed rates where CPTaxCategoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceTaxFixedRateModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPTaxCategoryId the cp tax category ID
	 * @param start the lower bound of the range of commerce tax fixed rates
	 * @param end the upper bound of the range of commerce tax fixed rates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce tax fixed rates
	 */
	public static List<CommerceTaxFixedRate> findByCPTaxCategoryId(
		long CPTaxCategoryId, int start, int end,
		OrderByComparator<CommerceTaxFixedRate> orderByComparator) {

		return getPersistence().findByCPTaxCategoryId(
			CPTaxCategoryId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce tax fixed rates where CPTaxCategoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceTaxFixedRateModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPTaxCategoryId the cp tax category ID
	 * @param start the lower bound of the range of commerce tax fixed rates
	 * @param end the upper bound of the range of commerce tax fixed rates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce tax fixed rates
	 */
	public static List<CommerceTaxFixedRate> findByCPTaxCategoryId(
		long CPTaxCategoryId, int start, int end,
		OrderByComparator<CommerceTaxFixedRate> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByCPTaxCategoryId(
			CPTaxCategoryId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first commerce tax fixed rate in the ordered set where CPTaxCategoryId = &#63;.
	 *
	 * @param CPTaxCategoryId the cp tax category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce tax fixed rate
	 * @throws NoSuchTaxFixedRateException if a matching commerce tax fixed rate could not be found
	 */
	public static CommerceTaxFixedRate findByCPTaxCategoryId_First(
			long CPTaxCategoryId,
			OrderByComparator<CommerceTaxFixedRate> orderByComparator)
		throws com.liferay.commerce.tax.engine.fixed.exception.
			NoSuchTaxFixedRateException {

		return getPersistence().findByCPTaxCategoryId_First(
			CPTaxCategoryId, orderByComparator);
	}

	/**
	 * Returns the first commerce tax fixed rate in the ordered set where CPTaxCategoryId = &#63;.
	 *
	 * @param CPTaxCategoryId the cp tax category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce tax fixed rate, or <code>null</code> if a matching commerce tax fixed rate could not be found
	 */
	public static CommerceTaxFixedRate fetchByCPTaxCategoryId_First(
		long CPTaxCategoryId,
		OrderByComparator<CommerceTaxFixedRate> orderByComparator) {

		return getPersistence().fetchByCPTaxCategoryId_First(
			CPTaxCategoryId, orderByComparator);
	}

	/**
	 * Returns the last commerce tax fixed rate in the ordered set where CPTaxCategoryId = &#63;.
	 *
	 * @param CPTaxCategoryId the cp tax category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce tax fixed rate
	 * @throws NoSuchTaxFixedRateException if a matching commerce tax fixed rate could not be found
	 */
	public static CommerceTaxFixedRate findByCPTaxCategoryId_Last(
			long CPTaxCategoryId,
			OrderByComparator<CommerceTaxFixedRate> orderByComparator)
		throws com.liferay.commerce.tax.engine.fixed.exception.
			NoSuchTaxFixedRateException {

		return getPersistence().findByCPTaxCategoryId_Last(
			CPTaxCategoryId, orderByComparator);
	}

	/**
	 * Returns the last commerce tax fixed rate in the ordered set where CPTaxCategoryId = &#63;.
	 *
	 * @param CPTaxCategoryId the cp tax category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce tax fixed rate, or <code>null</code> if a matching commerce tax fixed rate could not be found
	 */
	public static CommerceTaxFixedRate fetchByCPTaxCategoryId_Last(
		long CPTaxCategoryId,
		OrderByComparator<CommerceTaxFixedRate> orderByComparator) {

		return getPersistence().fetchByCPTaxCategoryId_Last(
			CPTaxCategoryId, orderByComparator);
	}

	/**
	 * Returns the commerce tax fixed rates before and after the current commerce tax fixed rate in the ordered set where CPTaxCategoryId = &#63;.
	 *
	 * @param commerceTaxFixedRateId the primary key of the current commerce tax fixed rate
	 * @param CPTaxCategoryId the cp tax category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce tax fixed rate
	 * @throws NoSuchTaxFixedRateException if a commerce tax fixed rate with the primary key could not be found
	 */
	public static CommerceTaxFixedRate[] findByCPTaxCategoryId_PrevAndNext(
			long commerceTaxFixedRateId, long CPTaxCategoryId,
			OrderByComparator<CommerceTaxFixedRate> orderByComparator)
		throws com.liferay.commerce.tax.engine.fixed.exception.
			NoSuchTaxFixedRateException {

		return getPersistence().findByCPTaxCategoryId_PrevAndNext(
			commerceTaxFixedRateId, CPTaxCategoryId, orderByComparator);
	}

	/**
	 * Removes all the commerce tax fixed rates where CPTaxCategoryId = &#63; from the database.
	 *
	 * @param CPTaxCategoryId the cp tax category ID
	 */
	public static void removeByCPTaxCategoryId(long CPTaxCategoryId) {
		getPersistence().removeByCPTaxCategoryId(CPTaxCategoryId);
	}

	/**
	 * Returns the number of commerce tax fixed rates where CPTaxCategoryId = &#63;.
	 *
	 * @param CPTaxCategoryId the cp tax category ID
	 * @return the number of matching commerce tax fixed rates
	 */
	public static int countByCPTaxCategoryId(long CPTaxCategoryId) {
		return getPersistence().countByCPTaxCategoryId(CPTaxCategoryId);
	}

	/**
	 * Returns all the commerce tax fixed rates where commerceTaxMethodId = &#63;.
	 *
	 * @param commerceTaxMethodId the commerce tax method ID
	 * @return the matching commerce tax fixed rates
	 */
	public static List<CommerceTaxFixedRate> findByCommerceTaxMethodId(
		long commerceTaxMethodId) {

		return getPersistence().findByCommerceTaxMethodId(commerceTaxMethodId);
	}

	/**
	 * Returns a range of all the commerce tax fixed rates where commerceTaxMethodId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceTaxFixedRateModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceTaxMethodId the commerce tax method ID
	 * @param start the lower bound of the range of commerce tax fixed rates
	 * @param end the upper bound of the range of commerce tax fixed rates (not inclusive)
	 * @return the range of matching commerce tax fixed rates
	 */
	public static List<CommerceTaxFixedRate> findByCommerceTaxMethodId(
		long commerceTaxMethodId, int start, int end) {

		return getPersistence().findByCommerceTaxMethodId(
			commerceTaxMethodId, start, end);
	}

	/**
	 * Returns an ordered range of all the commerce tax fixed rates where commerceTaxMethodId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceTaxFixedRateModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceTaxMethodId the commerce tax method ID
	 * @param start the lower bound of the range of commerce tax fixed rates
	 * @param end the upper bound of the range of commerce tax fixed rates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce tax fixed rates
	 */
	public static List<CommerceTaxFixedRate> findByCommerceTaxMethodId(
		long commerceTaxMethodId, int start, int end,
		OrderByComparator<CommerceTaxFixedRate> orderByComparator) {

		return getPersistence().findByCommerceTaxMethodId(
			commerceTaxMethodId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce tax fixed rates where commerceTaxMethodId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceTaxFixedRateModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceTaxMethodId the commerce tax method ID
	 * @param start the lower bound of the range of commerce tax fixed rates
	 * @param end the upper bound of the range of commerce tax fixed rates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce tax fixed rates
	 */
	public static List<CommerceTaxFixedRate> findByCommerceTaxMethodId(
		long commerceTaxMethodId, int start, int end,
		OrderByComparator<CommerceTaxFixedRate> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByCommerceTaxMethodId(
			commerceTaxMethodId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first commerce tax fixed rate in the ordered set where commerceTaxMethodId = &#63;.
	 *
	 * @param commerceTaxMethodId the commerce tax method ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce tax fixed rate
	 * @throws NoSuchTaxFixedRateException if a matching commerce tax fixed rate could not be found
	 */
	public static CommerceTaxFixedRate findByCommerceTaxMethodId_First(
			long commerceTaxMethodId,
			OrderByComparator<CommerceTaxFixedRate> orderByComparator)
		throws com.liferay.commerce.tax.engine.fixed.exception.
			NoSuchTaxFixedRateException {

		return getPersistence().findByCommerceTaxMethodId_First(
			commerceTaxMethodId, orderByComparator);
	}

	/**
	 * Returns the first commerce tax fixed rate in the ordered set where commerceTaxMethodId = &#63;.
	 *
	 * @param commerceTaxMethodId the commerce tax method ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce tax fixed rate, or <code>null</code> if a matching commerce tax fixed rate could not be found
	 */
	public static CommerceTaxFixedRate fetchByCommerceTaxMethodId_First(
		long commerceTaxMethodId,
		OrderByComparator<CommerceTaxFixedRate> orderByComparator) {

		return getPersistence().fetchByCommerceTaxMethodId_First(
			commerceTaxMethodId, orderByComparator);
	}

	/**
	 * Returns the last commerce tax fixed rate in the ordered set where commerceTaxMethodId = &#63;.
	 *
	 * @param commerceTaxMethodId the commerce tax method ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce tax fixed rate
	 * @throws NoSuchTaxFixedRateException if a matching commerce tax fixed rate could not be found
	 */
	public static CommerceTaxFixedRate findByCommerceTaxMethodId_Last(
			long commerceTaxMethodId,
			OrderByComparator<CommerceTaxFixedRate> orderByComparator)
		throws com.liferay.commerce.tax.engine.fixed.exception.
			NoSuchTaxFixedRateException {

		return getPersistence().findByCommerceTaxMethodId_Last(
			commerceTaxMethodId, orderByComparator);
	}

	/**
	 * Returns the last commerce tax fixed rate in the ordered set where commerceTaxMethodId = &#63;.
	 *
	 * @param commerceTaxMethodId the commerce tax method ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce tax fixed rate, or <code>null</code> if a matching commerce tax fixed rate could not be found
	 */
	public static CommerceTaxFixedRate fetchByCommerceTaxMethodId_Last(
		long commerceTaxMethodId,
		OrderByComparator<CommerceTaxFixedRate> orderByComparator) {

		return getPersistence().fetchByCommerceTaxMethodId_Last(
			commerceTaxMethodId, orderByComparator);
	}

	/**
	 * Returns the commerce tax fixed rates before and after the current commerce tax fixed rate in the ordered set where commerceTaxMethodId = &#63;.
	 *
	 * @param commerceTaxFixedRateId the primary key of the current commerce tax fixed rate
	 * @param commerceTaxMethodId the commerce tax method ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce tax fixed rate
	 * @throws NoSuchTaxFixedRateException if a commerce tax fixed rate with the primary key could not be found
	 */
	public static CommerceTaxFixedRate[] findByCommerceTaxMethodId_PrevAndNext(
			long commerceTaxFixedRateId, long commerceTaxMethodId,
			OrderByComparator<CommerceTaxFixedRate> orderByComparator)
		throws com.liferay.commerce.tax.engine.fixed.exception.
			NoSuchTaxFixedRateException {

		return getPersistence().findByCommerceTaxMethodId_PrevAndNext(
			commerceTaxFixedRateId, commerceTaxMethodId, orderByComparator);
	}

	/**
	 * Removes all the commerce tax fixed rates where commerceTaxMethodId = &#63; from the database.
	 *
	 * @param commerceTaxMethodId the commerce tax method ID
	 */
	public static void removeByCommerceTaxMethodId(long commerceTaxMethodId) {
		getPersistence().removeByCommerceTaxMethodId(commerceTaxMethodId);
	}

	/**
	 * Returns the number of commerce tax fixed rates where commerceTaxMethodId = &#63;.
	 *
	 * @param commerceTaxMethodId the commerce tax method ID
	 * @return the number of matching commerce tax fixed rates
	 */
	public static int countByCommerceTaxMethodId(long commerceTaxMethodId) {
		return getPersistence().countByCommerceTaxMethodId(commerceTaxMethodId);
	}

	/**
	 * Returns the commerce tax fixed rate where CPTaxCategoryId = &#63; and commerceTaxMethodId = &#63; or throws a <code>NoSuchTaxFixedRateException</code> if it could not be found.
	 *
	 * @param CPTaxCategoryId the cp tax category ID
	 * @param commerceTaxMethodId the commerce tax method ID
	 * @return the matching commerce tax fixed rate
	 * @throws NoSuchTaxFixedRateException if a matching commerce tax fixed rate could not be found
	 */
	public static CommerceTaxFixedRate findByC_C(
			long CPTaxCategoryId, long commerceTaxMethodId)
		throws com.liferay.commerce.tax.engine.fixed.exception.
			NoSuchTaxFixedRateException {

		return getPersistence().findByC_C(CPTaxCategoryId, commerceTaxMethodId);
	}

	/**
	 * Returns the commerce tax fixed rate where CPTaxCategoryId = &#63; and commerceTaxMethodId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param CPTaxCategoryId the cp tax category ID
	 * @param commerceTaxMethodId the commerce tax method ID
	 * @return the matching commerce tax fixed rate, or <code>null</code> if a matching commerce tax fixed rate could not be found
	 */
	public static CommerceTaxFixedRate fetchByC_C(
		long CPTaxCategoryId, long commerceTaxMethodId) {

		return getPersistence().fetchByC_C(
			CPTaxCategoryId, commerceTaxMethodId);
	}

	/**
	 * Returns the commerce tax fixed rate where CPTaxCategoryId = &#63; and commerceTaxMethodId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param CPTaxCategoryId the cp tax category ID
	 * @param commerceTaxMethodId the commerce tax method ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching commerce tax fixed rate, or <code>null</code> if a matching commerce tax fixed rate could not be found
	 */
	public static CommerceTaxFixedRate fetchByC_C(
		long CPTaxCategoryId, long commerceTaxMethodId,
		boolean useFinderCache) {

		return getPersistence().fetchByC_C(
			CPTaxCategoryId, commerceTaxMethodId, useFinderCache);
	}

	/**
	 * Removes the commerce tax fixed rate where CPTaxCategoryId = &#63; and commerceTaxMethodId = &#63; from the database.
	 *
	 * @param CPTaxCategoryId the cp tax category ID
	 * @param commerceTaxMethodId the commerce tax method ID
	 * @return the commerce tax fixed rate that was removed
	 */
	public static CommerceTaxFixedRate removeByC_C(
			long CPTaxCategoryId, long commerceTaxMethodId)
		throws com.liferay.commerce.tax.engine.fixed.exception.
			NoSuchTaxFixedRateException {

		return getPersistence().removeByC_C(
			CPTaxCategoryId, commerceTaxMethodId);
	}

	/**
	 * Returns the number of commerce tax fixed rates where CPTaxCategoryId = &#63; and commerceTaxMethodId = &#63;.
	 *
	 * @param CPTaxCategoryId the cp tax category ID
	 * @param commerceTaxMethodId the commerce tax method ID
	 * @return the number of matching commerce tax fixed rates
	 */
	public static int countByC_C(
		long CPTaxCategoryId, long commerceTaxMethodId) {

		return getPersistence().countByC_C(
			CPTaxCategoryId, commerceTaxMethodId);
	}

	/**
	 * Caches the commerce tax fixed rate in the entity cache if it is enabled.
	 *
	 * @param commerceTaxFixedRate the commerce tax fixed rate
	 */
	public static void cacheResult(CommerceTaxFixedRate commerceTaxFixedRate) {
		getPersistence().cacheResult(commerceTaxFixedRate);
	}

	/**
	 * Caches the commerce tax fixed rates in the entity cache if it is enabled.
	 *
	 * @param commerceTaxFixedRates the commerce tax fixed rates
	 */
	public static void cacheResult(
		List<CommerceTaxFixedRate> commerceTaxFixedRates) {

		getPersistence().cacheResult(commerceTaxFixedRates);
	}

	/**
	 * Creates a new commerce tax fixed rate with the primary key. Does not add the commerce tax fixed rate to the database.
	 *
	 * @param commerceTaxFixedRateId the primary key for the new commerce tax fixed rate
	 * @return the new commerce tax fixed rate
	 */
	public static CommerceTaxFixedRate create(long commerceTaxFixedRateId) {
		return getPersistence().create(commerceTaxFixedRateId);
	}

	/**
	 * Removes the commerce tax fixed rate with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceTaxFixedRateId the primary key of the commerce tax fixed rate
	 * @return the commerce tax fixed rate that was removed
	 * @throws NoSuchTaxFixedRateException if a commerce tax fixed rate with the primary key could not be found
	 */
	public static CommerceTaxFixedRate remove(long commerceTaxFixedRateId)
		throws com.liferay.commerce.tax.engine.fixed.exception.
			NoSuchTaxFixedRateException {

		return getPersistence().remove(commerceTaxFixedRateId);
	}

	public static CommerceTaxFixedRate updateImpl(
		CommerceTaxFixedRate commerceTaxFixedRate) {

		return getPersistence().updateImpl(commerceTaxFixedRate);
	}

	/**
	 * Returns the commerce tax fixed rate with the primary key or throws a <code>NoSuchTaxFixedRateException</code> if it could not be found.
	 *
	 * @param commerceTaxFixedRateId the primary key of the commerce tax fixed rate
	 * @return the commerce tax fixed rate
	 * @throws NoSuchTaxFixedRateException if a commerce tax fixed rate with the primary key could not be found
	 */
	public static CommerceTaxFixedRate findByPrimaryKey(
			long commerceTaxFixedRateId)
		throws com.liferay.commerce.tax.engine.fixed.exception.
			NoSuchTaxFixedRateException {

		return getPersistence().findByPrimaryKey(commerceTaxFixedRateId);
	}

	/**
	 * Returns the commerce tax fixed rate with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceTaxFixedRateId the primary key of the commerce tax fixed rate
	 * @return the commerce tax fixed rate, or <code>null</code> if a commerce tax fixed rate with the primary key could not be found
	 */
	public static CommerceTaxFixedRate fetchByPrimaryKey(
		long commerceTaxFixedRateId) {

		return getPersistence().fetchByPrimaryKey(commerceTaxFixedRateId);
	}

	/**
	 * Returns all the commerce tax fixed rates.
	 *
	 * @return the commerce tax fixed rates
	 */
	public static List<CommerceTaxFixedRate> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the commerce tax fixed rates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceTaxFixedRateModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce tax fixed rates
	 * @param end the upper bound of the range of commerce tax fixed rates (not inclusive)
	 * @return the range of commerce tax fixed rates
	 */
	public static List<CommerceTaxFixedRate> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the commerce tax fixed rates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceTaxFixedRateModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce tax fixed rates
	 * @param end the upper bound of the range of commerce tax fixed rates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce tax fixed rates
	 */
	public static List<CommerceTaxFixedRate> findAll(
		int start, int end,
		OrderByComparator<CommerceTaxFixedRate> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce tax fixed rates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceTaxFixedRateModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce tax fixed rates
	 * @param end the upper bound of the range of commerce tax fixed rates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of commerce tax fixed rates
	 */
	public static List<CommerceTaxFixedRate> findAll(
		int start, int end,
		OrderByComparator<CommerceTaxFixedRate> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the commerce tax fixed rates from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of commerce tax fixed rates.
	 *
	 * @return the number of commerce tax fixed rates
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static CommerceTaxFixedRatePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CommerceTaxFixedRatePersistence, CommerceTaxFixedRatePersistence>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			CommerceTaxFixedRatePersistence.class);

		ServiceTracker
			<CommerceTaxFixedRatePersistence, CommerceTaxFixedRatePersistence>
				serviceTracker =
					new ServiceTracker
						<CommerceTaxFixedRatePersistence,
						 CommerceTaxFixedRatePersistence>(
							 bundle.getBundleContext(),
							 CommerceTaxFixedRatePersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}