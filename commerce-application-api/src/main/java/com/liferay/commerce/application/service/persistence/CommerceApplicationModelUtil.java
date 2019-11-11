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

package com.liferay.commerce.application.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.application.model.CommerceApplicationModel;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the commerce application model service. This utility wraps {@link com.liferay.commerce.application.service.persistence.impl.CommerceApplicationModelPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommerceApplicationModelPersistence
 * @see com.liferay.commerce.application.service.persistence.impl.CommerceApplicationModelPersistenceImpl
 * @generated
 */
@ProviderType
public class CommerceApplicationModelUtil {
	/*
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
	public static void clearCache(
		CommerceApplicationModel commerceApplicationModel) {
		getPersistence().clearCache(commerceApplicationModel);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<CommerceApplicationModel> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CommerceApplicationModel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CommerceApplicationModel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CommerceApplicationModel> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CommerceApplicationModel update(
		CommerceApplicationModel commerceApplicationModel) {
		return getPersistence().update(commerceApplicationModel);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CommerceApplicationModel update(
		CommerceApplicationModel commerceApplicationModel,
		ServiceContext serviceContext) {
		return getPersistence().update(commerceApplicationModel, serviceContext);
	}

	/**
	* Returns all the commerce application models where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching commerce application models
	*/
	public static List<CommerceApplicationModel> findByCompanyId(long companyId) {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	* Returns a range of all the commerce application models where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceApplicationModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of commerce application models
	* @param end the upper bound of the range of commerce application models (not inclusive)
	* @return the range of matching commerce application models
	*/
	public static List<CommerceApplicationModel> findByCompanyId(
		long companyId, int start, int end) {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	* Returns an ordered range of all the commerce application models where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceApplicationModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of commerce application models
	* @param end the upper bound of the range of commerce application models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce application models
	*/
	public static List<CommerceApplicationModel> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CommerceApplicationModel> orderByComparator) {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the commerce application models where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceApplicationModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of commerce application models
	* @param end the upper bound of the range of commerce application models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching commerce application models
	*/
	public static List<CommerceApplicationModel> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CommerceApplicationModel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first commerce application model in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce application model
	* @throws NoSuchApplicationModelException if a matching commerce application model could not be found
	*/
	public static CommerceApplicationModel findByCompanyId_First(
		long companyId,
		OrderByComparator<CommerceApplicationModel> orderByComparator)
		throws com.liferay.commerce.application.exception.NoSuchApplicationModelException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the first commerce application model in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce application model, or <code>null</code> if a matching commerce application model could not be found
	*/
	public static CommerceApplicationModel fetchByCompanyId_First(
		long companyId,
		OrderByComparator<CommerceApplicationModel> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the last commerce application model in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce application model
	* @throws NoSuchApplicationModelException if a matching commerce application model could not be found
	*/
	public static CommerceApplicationModel findByCompanyId_Last(
		long companyId,
		OrderByComparator<CommerceApplicationModel> orderByComparator)
		throws com.liferay.commerce.application.exception.NoSuchApplicationModelException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the last commerce application model in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce application model, or <code>null</code> if a matching commerce application model could not be found
	*/
	public static CommerceApplicationModel fetchByCompanyId_Last(
		long companyId,
		OrderByComparator<CommerceApplicationModel> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the commerce application models before and after the current commerce application model in the ordered set where companyId = &#63;.
	*
	* @param commerceApplicationModelId the primary key of the current commerce application model
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce application model
	* @throws NoSuchApplicationModelException if a commerce application model with the primary key could not be found
	*/
	public static CommerceApplicationModel[] findByCompanyId_PrevAndNext(
		long commerceApplicationModelId, long companyId,
		OrderByComparator<CommerceApplicationModel> orderByComparator)
		throws com.liferay.commerce.application.exception.NoSuchApplicationModelException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(commerceApplicationModelId,
			companyId, orderByComparator);
	}

	/**
	* Returns all the commerce application models that the user has permission to view where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching commerce application models that the user has permission to view
	*/
	public static List<CommerceApplicationModel> filterFindByCompanyId(
		long companyId) {
		return getPersistence().filterFindByCompanyId(companyId);
	}

	/**
	* Returns a range of all the commerce application models that the user has permission to view where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceApplicationModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of commerce application models
	* @param end the upper bound of the range of commerce application models (not inclusive)
	* @return the range of matching commerce application models that the user has permission to view
	*/
	public static List<CommerceApplicationModel> filterFindByCompanyId(
		long companyId, int start, int end) {
		return getPersistence().filterFindByCompanyId(companyId, start, end);
	}

	/**
	* Returns an ordered range of all the commerce application models that the user has permissions to view where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceApplicationModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of commerce application models
	* @param end the upper bound of the range of commerce application models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce application models that the user has permission to view
	*/
	public static List<CommerceApplicationModel> filterFindByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CommerceApplicationModel> orderByComparator) {
		return getPersistence()
				   .filterFindByCompanyId(companyId, start, end,
			orderByComparator);
	}

	/**
	* Returns the commerce application models before and after the current commerce application model in the ordered set of commerce application models that the user has permission to view where companyId = &#63;.
	*
	* @param commerceApplicationModelId the primary key of the current commerce application model
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce application model
	* @throws NoSuchApplicationModelException if a commerce application model with the primary key could not be found
	*/
	public static CommerceApplicationModel[] filterFindByCompanyId_PrevAndNext(
		long commerceApplicationModelId, long companyId,
		OrderByComparator<CommerceApplicationModel> orderByComparator)
		throws com.liferay.commerce.application.exception.NoSuchApplicationModelException {
		return getPersistence()
				   .filterFindByCompanyId_PrevAndNext(commerceApplicationModelId,
			companyId, orderByComparator);
	}

	/**
	* Removes all the commerce application models where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public static void removeByCompanyId(long companyId) {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	* Returns the number of commerce application models where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching commerce application models
	*/
	public static int countByCompanyId(long companyId) {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	* Returns the number of commerce application models that the user has permission to view where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching commerce application models that the user has permission to view
	*/
	public static int filterCountByCompanyId(long companyId) {
		return getPersistence().filterCountByCompanyId(companyId);
	}

	/**
	* Returns all the commerce application models where commerceApplicationBrandId = &#63;.
	*
	* @param commerceApplicationBrandId the commerce application brand ID
	* @return the matching commerce application models
	*/
	public static List<CommerceApplicationModel> findByCommerceApplicationBrandId(
		long commerceApplicationBrandId) {
		return getPersistence()
				   .findByCommerceApplicationBrandId(commerceApplicationBrandId);
	}

	/**
	* Returns a range of all the commerce application models where commerceApplicationBrandId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceApplicationModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commerceApplicationBrandId the commerce application brand ID
	* @param start the lower bound of the range of commerce application models
	* @param end the upper bound of the range of commerce application models (not inclusive)
	* @return the range of matching commerce application models
	*/
	public static List<CommerceApplicationModel> findByCommerceApplicationBrandId(
		long commerceApplicationBrandId, int start, int end) {
		return getPersistence()
				   .findByCommerceApplicationBrandId(commerceApplicationBrandId,
			start, end);
	}

	/**
	* Returns an ordered range of all the commerce application models where commerceApplicationBrandId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceApplicationModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commerceApplicationBrandId the commerce application brand ID
	* @param start the lower bound of the range of commerce application models
	* @param end the upper bound of the range of commerce application models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce application models
	*/
	public static List<CommerceApplicationModel> findByCommerceApplicationBrandId(
		long commerceApplicationBrandId, int start, int end,
		OrderByComparator<CommerceApplicationModel> orderByComparator) {
		return getPersistence()
				   .findByCommerceApplicationBrandId(commerceApplicationBrandId,
			start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the commerce application models where commerceApplicationBrandId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceApplicationModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commerceApplicationBrandId the commerce application brand ID
	* @param start the lower bound of the range of commerce application models
	* @param end the upper bound of the range of commerce application models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching commerce application models
	*/
	public static List<CommerceApplicationModel> findByCommerceApplicationBrandId(
		long commerceApplicationBrandId, int start, int end,
		OrderByComparator<CommerceApplicationModel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCommerceApplicationBrandId(commerceApplicationBrandId,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first commerce application model in the ordered set where commerceApplicationBrandId = &#63;.
	*
	* @param commerceApplicationBrandId the commerce application brand ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce application model
	* @throws NoSuchApplicationModelException if a matching commerce application model could not be found
	*/
	public static CommerceApplicationModel findByCommerceApplicationBrandId_First(
		long commerceApplicationBrandId,
		OrderByComparator<CommerceApplicationModel> orderByComparator)
		throws com.liferay.commerce.application.exception.NoSuchApplicationModelException {
		return getPersistence()
				   .findByCommerceApplicationBrandId_First(commerceApplicationBrandId,
			orderByComparator);
	}

	/**
	* Returns the first commerce application model in the ordered set where commerceApplicationBrandId = &#63;.
	*
	* @param commerceApplicationBrandId the commerce application brand ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce application model, or <code>null</code> if a matching commerce application model could not be found
	*/
	public static CommerceApplicationModel fetchByCommerceApplicationBrandId_First(
		long commerceApplicationBrandId,
		OrderByComparator<CommerceApplicationModel> orderByComparator) {
		return getPersistence()
				   .fetchByCommerceApplicationBrandId_First(commerceApplicationBrandId,
			orderByComparator);
	}

	/**
	* Returns the last commerce application model in the ordered set where commerceApplicationBrandId = &#63;.
	*
	* @param commerceApplicationBrandId the commerce application brand ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce application model
	* @throws NoSuchApplicationModelException if a matching commerce application model could not be found
	*/
	public static CommerceApplicationModel findByCommerceApplicationBrandId_Last(
		long commerceApplicationBrandId,
		OrderByComparator<CommerceApplicationModel> orderByComparator)
		throws com.liferay.commerce.application.exception.NoSuchApplicationModelException {
		return getPersistence()
				   .findByCommerceApplicationBrandId_Last(commerceApplicationBrandId,
			orderByComparator);
	}

	/**
	* Returns the last commerce application model in the ordered set where commerceApplicationBrandId = &#63;.
	*
	* @param commerceApplicationBrandId the commerce application brand ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce application model, or <code>null</code> if a matching commerce application model could not be found
	*/
	public static CommerceApplicationModel fetchByCommerceApplicationBrandId_Last(
		long commerceApplicationBrandId,
		OrderByComparator<CommerceApplicationModel> orderByComparator) {
		return getPersistence()
				   .fetchByCommerceApplicationBrandId_Last(commerceApplicationBrandId,
			orderByComparator);
	}

	/**
	* Returns the commerce application models before and after the current commerce application model in the ordered set where commerceApplicationBrandId = &#63;.
	*
	* @param commerceApplicationModelId the primary key of the current commerce application model
	* @param commerceApplicationBrandId the commerce application brand ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce application model
	* @throws NoSuchApplicationModelException if a commerce application model with the primary key could not be found
	*/
	public static CommerceApplicationModel[] findByCommerceApplicationBrandId_PrevAndNext(
		long commerceApplicationModelId, long commerceApplicationBrandId,
		OrderByComparator<CommerceApplicationModel> orderByComparator)
		throws com.liferay.commerce.application.exception.NoSuchApplicationModelException {
		return getPersistence()
				   .findByCommerceApplicationBrandId_PrevAndNext(commerceApplicationModelId,
			commerceApplicationBrandId, orderByComparator);
	}

	/**
	* Returns all the commerce application models that the user has permission to view where commerceApplicationBrandId = &#63;.
	*
	* @param commerceApplicationBrandId the commerce application brand ID
	* @return the matching commerce application models that the user has permission to view
	*/
	public static List<CommerceApplicationModel> filterFindByCommerceApplicationBrandId(
		long commerceApplicationBrandId) {
		return getPersistence()
				   .filterFindByCommerceApplicationBrandId(commerceApplicationBrandId);
	}

	/**
	* Returns a range of all the commerce application models that the user has permission to view where commerceApplicationBrandId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceApplicationModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commerceApplicationBrandId the commerce application brand ID
	* @param start the lower bound of the range of commerce application models
	* @param end the upper bound of the range of commerce application models (not inclusive)
	* @return the range of matching commerce application models that the user has permission to view
	*/
	public static List<CommerceApplicationModel> filterFindByCommerceApplicationBrandId(
		long commerceApplicationBrandId, int start, int end) {
		return getPersistence()
				   .filterFindByCommerceApplicationBrandId(commerceApplicationBrandId,
			start, end);
	}

	/**
	* Returns an ordered range of all the commerce application models that the user has permissions to view where commerceApplicationBrandId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceApplicationModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commerceApplicationBrandId the commerce application brand ID
	* @param start the lower bound of the range of commerce application models
	* @param end the upper bound of the range of commerce application models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce application models that the user has permission to view
	*/
	public static List<CommerceApplicationModel> filterFindByCommerceApplicationBrandId(
		long commerceApplicationBrandId, int start, int end,
		OrderByComparator<CommerceApplicationModel> orderByComparator) {
		return getPersistence()
				   .filterFindByCommerceApplicationBrandId(commerceApplicationBrandId,
			start, end, orderByComparator);
	}

	/**
	* Returns the commerce application models before and after the current commerce application model in the ordered set of commerce application models that the user has permission to view where commerceApplicationBrandId = &#63;.
	*
	* @param commerceApplicationModelId the primary key of the current commerce application model
	* @param commerceApplicationBrandId the commerce application brand ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce application model
	* @throws NoSuchApplicationModelException if a commerce application model with the primary key could not be found
	*/
	public static CommerceApplicationModel[] filterFindByCommerceApplicationBrandId_PrevAndNext(
		long commerceApplicationModelId, long commerceApplicationBrandId,
		OrderByComparator<CommerceApplicationModel> orderByComparator)
		throws com.liferay.commerce.application.exception.NoSuchApplicationModelException {
		return getPersistence()
				   .filterFindByCommerceApplicationBrandId_PrevAndNext(commerceApplicationModelId,
			commerceApplicationBrandId, orderByComparator);
	}

	/**
	* Removes all the commerce application models where commerceApplicationBrandId = &#63; from the database.
	*
	* @param commerceApplicationBrandId the commerce application brand ID
	*/
	public static void removeByCommerceApplicationBrandId(
		long commerceApplicationBrandId) {
		getPersistence()
			.removeByCommerceApplicationBrandId(commerceApplicationBrandId);
	}

	/**
	* Returns the number of commerce application models where commerceApplicationBrandId = &#63;.
	*
	* @param commerceApplicationBrandId the commerce application brand ID
	* @return the number of matching commerce application models
	*/
	public static int countByCommerceApplicationBrandId(
		long commerceApplicationBrandId) {
		return getPersistence()
				   .countByCommerceApplicationBrandId(commerceApplicationBrandId);
	}

	/**
	* Returns the number of commerce application models that the user has permission to view where commerceApplicationBrandId = &#63;.
	*
	* @param commerceApplicationBrandId the commerce application brand ID
	* @return the number of matching commerce application models that the user has permission to view
	*/
	public static int filterCountByCommerceApplicationBrandId(
		long commerceApplicationBrandId) {
		return getPersistence()
				   .filterCountByCommerceApplicationBrandId(commerceApplicationBrandId);
	}

	/**
	* Caches the commerce application model in the entity cache if it is enabled.
	*
	* @param commerceApplicationModel the commerce application model
	*/
	public static void cacheResult(
		CommerceApplicationModel commerceApplicationModel) {
		getPersistence().cacheResult(commerceApplicationModel);
	}

	/**
	* Caches the commerce application models in the entity cache if it is enabled.
	*
	* @param commerceApplicationModels the commerce application models
	*/
	public static void cacheResult(
		List<CommerceApplicationModel> commerceApplicationModels) {
		getPersistence().cacheResult(commerceApplicationModels);
	}

	/**
	* Creates a new commerce application model with the primary key. Does not add the commerce application model to the database.
	*
	* @param commerceApplicationModelId the primary key for the new commerce application model
	* @return the new commerce application model
	*/
	public static CommerceApplicationModel create(
		long commerceApplicationModelId) {
		return getPersistence().create(commerceApplicationModelId);
	}

	/**
	* Removes the commerce application model with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceApplicationModelId the primary key of the commerce application model
	* @return the commerce application model that was removed
	* @throws NoSuchApplicationModelException if a commerce application model with the primary key could not be found
	*/
	public static CommerceApplicationModel remove(
		long commerceApplicationModelId)
		throws com.liferay.commerce.application.exception.NoSuchApplicationModelException {
		return getPersistence().remove(commerceApplicationModelId);
	}

	public static CommerceApplicationModel updateImpl(
		CommerceApplicationModel commerceApplicationModel) {
		return getPersistence().updateImpl(commerceApplicationModel);
	}

	/**
	* Returns the commerce application model with the primary key or throws a {@link NoSuchApplicationModelException} if it could not be found.
	*
	* @param commerceApplicationModelId the primary key of the commerce application model
	* @return the commerce application model
	* @throws NoSuchApplicationModelException if a commerce application model with the primary key could not be found
	*/
	public static CommerceApplicationModel findByPrimaryKey(
		long commerceApplicationModelId)
		throws com.liferay.commerce.application.exception.NoSuchApplicationModelException {
		return getPersistence().findByPrimaryKey(commerceApplicationModelId);
	}

	/**
	* Returns the commerce application model with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param commerceApplicationModelId the primary key of the commerce application model
	* @return the commerce application model, or <code>null</code> if a commerce application model with the primary key could not be found
	*/
	public static CommerceApplicationModel fetchByPrimaryKey(
		long commerceApplicationModelId) {
		return getPersistence().fetchByPrimaryKey(commerceApplicationModelId);
	}

	public static java.util.Map<java.io.Serializable, CommerceApplicationModel> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the commerce application models.
	*
	* @return the commerce application models
	*/
	public static List<CommerceApplicationModel> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the commerce application models.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceApplicationModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce application models
	* @param end the upper bound of the range of commerce application models (not inclusive)
	* @return the range of commerce application models
	*/
	public static List<CommerceApplicationModel> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the commerce application models.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceApplicationModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce application models
	* @param end the upper bound of the range of commerce application models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of commerce application models
	*/
	public static List<CommerceApplicationModel> findAll(int start, int end,
		OrderByComparator<CommerceApplicationModel> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the commerce application models.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceApplicationModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce application models
	* @param end the upper bound of the range of commerce application models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of commerce application models
	*/
	public static List<CommerceApplicationModel> findAll(int start, int end,
		OrderByComparator<CommerceApplicationModel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the commerce application models from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of commerce application models.
	*
	* @return the number of commerce application models
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static CommerceApplicationModelPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommerceApplicationModelPersistence, CommerceApplicationModelPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceApplicationModelPersistence.class);

		ServiceTracker<CommerceApplicationModelPersistence, CommerceApplicationModelPersistence> serviceTracker =
			new ServiceTracker<CommerceApplicationModelPersistence, CommerceApplicationModelPersistence>(bundle.getBundleContext(),
				CommerceApplicationModelPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}