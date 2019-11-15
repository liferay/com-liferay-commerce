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

import com.liferay.commerce.application.exception.NoSuchApplicationModelException;
import com.liferay.commerce.application.model.CommerceApplicationModel;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the commerce application model service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Luca Pellizzon
 * @see com.liferay.commerce.application.service.persistence.impl.CommerceApplicationModelPersistenceImpl
 * @see CommerceApplicationModelUtil
 * @generated
 */
@ProviderType
public interface CommerceApplicationModelPersistence extends BasePersistence<CommerceApplicationModel> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceApplicationModelUtil} to access the commerce application model persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the commerce application models where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching commerce application models
	*/
	public java.util.List<CommerceApplicationModel> findByCompanyId(
		long companyId);

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
	public java.util.List<CommerceApplicationModel> findByCompanyId(
		long companyId, int start, int end);

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
	public java.util.List<CommerceApplicationModel> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceApplicationModel> orderByComparator);

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
	public java.util.List<CommerceApplicationModel> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceApplicationModel> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first commerce application model in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce application model
	* @throws NoSuchApplicationModelException if a matching commerce application model could not be found
	*/
	public CommerceApplicationModel findByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceApplicationModel> orderByComparator)
		throws NoSuchApplicationModelException;

	/**
	* Returns the first commerce application model in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce application model, or <code>null</code> if a matching commerce application model could not be found
	*/
	public CommerceApplicationModel fetchByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceApplicationModel> orderByComparator);

	/**
	* Returns the last commerce application model in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce application model
	* @throws NoSuchApplicationModelException if a matching commerce application model could not be found
	*/
	public CommerceApplicationModel findByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceApplicationModel> orderByComparator)
		throws NoSuchApplicationModelException;

	/**
	* Returns the last commerce application model in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce application model, or <code>null</code> if a matching commerce application model could not be found
	*/
	public CommerceApplicationModel fetchByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceApplicationModel> orderByComparator);

	/**
	* Returns the commerce application models before and after the current commerce application model in the ordered set where companyId = &#63;.
	*
	* @param commerceApplicationModelId the primary key of the current commerce application model
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce application model
	* @throws NoSuchApplicationModelException if a commerce application model with the primary key could not be found
	*/
	public CommerceApplicationModel[] findByCompanyId_PrevAndNext(
		long commerceApplicationModelId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceApplicationModel> orderByComparator)
		throws NoSuchApplicationModelException;

	/**
	* Returns all the commerce application models that the user has permission to view where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching commerce application models that the user has permission to view
	*/
	public java.util.List<CommerceApplicationModel> filterFindByCompanyId(
		long companyId);

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
	public java.util.List<CommerceApplicationModel> filterFindByCompanyId(
		long companyId, int start, int end);

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
	public java.util.List<CommerceApplicationModel> filterFindByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceApplicationModel> orderByComparator);

	/**
	* Returns the commerce application models before and after the current commerce application model in the ordered set of commerce application models that the user has permission to view where companyId = &#63;.
	*
	* @param commerceApplicationModelId the primary key of the current commerce application model
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce application model
	* @throws NoSuchApplicationModelException if a commerce application model with the primary key could not be found
	*/
	public CommerceApplicationModel[] filterFindByCompanyId_PrevAndNext(
		long commerceApplicationModelId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceApplicationModel> orderByComparator)
		throws NoSuchApplicationModelException;

	/**
	* Removes all the commerce application models where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public void removeByCompanyId(long companyId);

	/**
	* Returns the number of commerce application models where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching commerce application models
	*/
	public int countByCompanyId(long companyId);

	/**
	* Returns the number of commerce application models that the user has permission to view where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching commerce application models that the user has permission to view
	*/
	public int filterCountByCompanyId(long companyId);

	/**
	* Returns all the commerce application models where commerceApplicationBrandId = &#63;.
	*
	* @param commerceApplicationBrandId the commerce application brand ID
	* @return the matching commerce application models
	*/
	public java.util.List<CommerceApplicationModel> findByCommerceApplicationBrandId(
		long commerceApplicationBrandId);

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
	public java.util.List<CommerceApplicationModel> findByCommerceApplicationBrandId(
		long commerceApplicationBrandId, int start, int end);

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
	public java.util.List<CommerceApplicationModel> findByCommerceApplicationBrandId(
		long commerceApplicationBrandId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceApplicationModel> orderByComparator);

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
	public java.util.List<CommerceApplicationModel> findByCommerceApplicationBrandId(
		long commerceApplicationBrandId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceApplicationModel> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first commerce application model in the ordered set where commerceApplicationBrandId = &#63;.
	*
	* @param commerceApplicationBrandId the commerce application brand ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce application model
	* @throws NoSuchApplicationModelException if a matching commerce application model could not be found
	*/
	public CommerceApplicationModel findByCommerceApplicationBrandId_First(
		long commerceApplicationBrandId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceApplicationModel> orderByComparator)
		throws NoSuchApplicationModelException;

	/**
	* Returns the first commerce application model in the ordered set where commerceApplicationBrandId = &#63;.
	*
	* @param commerceApplicationBrandId the commerce application brand ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce application model, or <code>null</code> if a matching commerce application model could not be found
	*/
	public CommerceApplicationModel fetchByCommerceApplicationBrandId_First(
		long commerceApplicationBrandId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceApplicationModel> orderByComparator);

	/**
	* Returns the last commerce application model in the ordered set where commerceApplicationBrandId = &#63;.
	*
	* @param commerceApplicationBrandId the commerce application brand ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce application model
	* @throws NoSuchApplicationModelException if a matching commerce application model could not be found
	*/
	public CommerceApplicationModel findByCommerceApplicationBrandId_Last(
		long commerceApplicationBrandId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceApplicationModel> orderByComparator)
		throws NoSuchApplicationModelException;

	/**
	* Returns the last commerce application model in the ordered set where commerceApplicationBrandId = &#63;.
	*
	* @param commerceApplicationBrandId the commerce application brand ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce application model, or <code>null</code> if a matching commerce application model could not be found
	*/
	public CommerceApplicationModel fetchByCommerceApplicationBrandId_Last(
		long commerceApplicationBrandId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceApplicationModel> orderByComparator);

	/**
	* Returns the commerce application models before and after the current commerce application model in the ordered set where commerceApplicationBrandId = &#63;.
	*
	* @param commerceApplicationModelId the primary key of the current commerce application model
	* @param commerceApplicationBrandId the commerce application brand ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce application model
	* @throws NoSuchApplicationModelException if a commerce application model with the primary key could not be found
	*/
	public CommerceApplicationModel[] findByCommerceApplicationBrandId_PrevAndNext(
		long commerceApplicationModelId, long commerceApplicationBrandId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceApplicationModel> orderByComparator)
		throws NoSuchApplicationModelException;

	/**
	* Returns all the commerce application models that the user has permission to view where commerceApplicationBrandId = &#63;.
	*
	* @param commerceApplicationBrandId the commerce application brand ID
	* @return the matching commerce application models that the user has permission to view
	*/
	public java.util.List<CommerceApplicationModel> filterFindByCommerceApplicationBrandId(
		long commerceApplicationBrandId);

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
	public java.util.List<CommerceApplicationModel> filterFindByCommerceApplicationBrandId(
		long commerceApplicationBrandId, int start, int end);

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
	public java.util.List<CommerceApplicationModel> filterFindByCommerceApplicationBrandId(
		long commerceApplicationBrandId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceApplicationModel> orderByComparator);

	/**
	* Returns the commerce application models before and after the current commerce application model in the ordered set of commerce application models that the user has permission to view where commerceApplicationBrandId = &#63;.
	*
	* @param commerceApplicationModelId the primary key of the current commerce application model
	* @param commerceApplicationBrandId the commerce application brand ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce application model
	* @throws NoSuchApplicationModelException if a commerce application model with the primary key could not be found
	*/
	public CommerceApplicationModel[] filterFindByCommerceApplicationBrandId_PrevAndNext(
		long commerceApplicationModelId, long commerceApplicationBrandId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceApplicationModel> orderByComparator)
		throws NoSuchApplicationModelException;

	/**
	* Removes all the commerce application models where commerceApplicationBrandId = &#63; from the database.
	*
	* @param commerceApplicationBrandId the commerce application brand ID
	*/
	public void removeByCommerceApplicationBrandId(
		long commerceApplicationBrandId);

	/**
	* Returns the number of commerce application models where commerceApplicationBrandId = &#63;.
	*
	* @param commerceApplicationBrandId the commerce application brand ID
	* @return the number of matching commerce application models
	*/
	public int countByCommerceApplicationBrandId(
		long commerceApplicationBrandId);

	/**
	* Returns the number of commerce application models that the user has permission to view where commerceApplicationBrandId = &#63;.
	*
	* @param commerceApplicationBrandId the commerce application brand ID
	* @return the number of matching commerce application models that the user has permission to view
	*/
	public int filterCountByCommerceApplicationBrandId(
		long commerceApplicationBrandId);

	/**
	* Caches the commerce application model in the entity cache if it is enabled.
	*
	* @param commerceApplicationModel the commerce application model
	*/
	public void cacheResult(CommerceApplicationModel commerceApplicationModel);

	/**
	* Caches the commerce application models in the entity cache if it is enabled.
	*
	* @param commerceApplicationModels the commerce application models
	*/
	public void cacheResult(
		java.util.List<CommerceApplicationModel> commerceApplicationModels);

	/**
	* Creates a new commerce application model with the primary key. Does not add the commerce application model to the database.
	*
	* @param commerceApplicationModelId the primary key for the new commerce application model
	* @return the new commerce application model
	*/
	public CommerceApplicationModel create(long commerceApplicationModelId);

	/**
	* Removes the commerce application model with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceApplicationModelId the primary key of the commerce application model
	* @return the commerce application model that was removed
	* @throws NoSuchApplicationModelException if a commerce application model with the primary key could not be found
	*/
	public CommerceApplicationModel remove(long commerceApplicationModelId)
		throws NoSuchApplicationModelException;

	public CommerceApplicationModel updateImpl(
		CommerceApplicationModel commerceApplicationModel);

	/**
	* Returns the commerce application model with the primary key or throws a {@link NoSuchApplicationModelException} if it could not be found.
	*
	* @param commerceApplicationModelId the primary key of the commerce application model
	* @return the commerce application model
	* @throws NoSuchApplicationModelException if a commerce application model with the primary key could not be found
	*/
	public CommerceApplicationModel findByPrimaryKey(
		long commerceApplicationModelId) throws NoSuchApplicationModelException;

	/**
	* Returns the commerce application model with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param commerceApplicationModelId the primary key of the commerce application model
	* @return the commerce application model, or <code>null</code> if a commerce application model with the primary key could not be found
	*/
	public CommerceApplicationModel fetchByPrimaryKey(
		long commerceApplicationModelId);

	@Override
	public java.util.Map<java.io.Serializable, CommerceApplicationModel> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the commerce application models.
	*
	* @return the commerce application models
	*/
	public java.util.List<CommerceApplicationModel> findAll();

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
	public java.util.List<CommerceApplicationModel> findAll(int start, int end);

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
	public java.util.List<CommerceApplicationModel> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceApplicationModel> orderByComparator);

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
	public java.util.List<CommerceApplicationModel> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceApplicationModel> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the commerce application models from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of commerce application models.
	*
	* @return the number of commerce application models
	*/
	public int countAll();
}