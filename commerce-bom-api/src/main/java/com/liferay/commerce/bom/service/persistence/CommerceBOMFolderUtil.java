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

package com.liferay.commerce.bom.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.bom.model.CommerceBOMFolder;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the commerce bom folder service. This utility wraps {@link com.liferay.commerce.bom.service.persistence.impl.CommerceBOMFolderPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommerceBOMFolderPersistence
 * @see com.liferay.commerce.bom.service.persistence.impl.CommerceBOMFolderPersistenceImpl
 * @generated
 */
@ProviderType
public class CommerceBOMFolderUtil {
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
	public static void clearCache(CommerceBOMFolder commerceBOMFolder) {
		getPersistence().clearCache(commerceBOMFolder);
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
	public static List<CommerceBOMFolder> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CommerceBOMFolder> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CommerceBOMFolder> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CommerceBOMFolder> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CommerceBOMFolder update(CommerceBOMFolder commerceBOMFolder) {
		return getPersistence().update(commerceBOMFolder);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CommerceBOMFolder update(
		CommerceBOMFolder commerceBOMFolder, ServiceContext serviceContext) {
		return getPersistence().update(commerceBOMFolder, serviceContext);
	}

	/**
	* Returns all the commerce bom folders where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching commerce bom folders
	*/
	public static List<CommerceBOMFolder> findByCompanyId(long companyId) {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	* Returns a range of all the commerce bom folders where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of commerce bom folders
	* @param end the upper bound of the range of commerce bom folders (not inclusive)
	* @return the range of matching commerce bom folders
	*/
	public static List<CommerceBOMFolder> findByCompanyId(long companyId,
		int start, int end) {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	* Returns an ordered range of all the commerce bom folders where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of commerce bom folders
	* @param end the upper bound of the range of commerce bom folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce bom folders
	*/
	public static List<CommerceBOMFolder> findByCompanyId(long companyId,
		int start, int end,
		OrderByComparator<CommerceBOMFolder> orderByComparator) {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the commerce bom folders where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of commerce bom folders
	* @param end the upper bound of the range of commerce bom folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching commerce bom folders
	*/
	public static List<CommerceBOMFolder> findByCompanyId(long companyId,
		int start, int end,
		OrderByComparator<CommerceBOMFolder> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first commerce bom folder in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce bom folder
	* @throws NoSuchBOMFolderException if a matching commerce bom folder could not be found
	*/
	public static CommerceBOMFolder findByCompanyId_First(long companyId,
		OrderByComparator<CommerceBOMFolder> orderByComparator)
		throws com.liferay.commerce.bom.exception.NoSuchBOMFolderException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the first commerce bom folder in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce bom folder, or <code>null</code> if a matching commerce bom folder could not be found
	*/
	public static CommerceBOMFolder fetchByCompanyId_First(long companyId,
		OrderByComparator<CommerceBOMFolder> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the last commerce bom folder in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce bom folder
	* @throws NoSuchBOMFolderException if a matching commerce bom folder could not be found
	*/
	public static CommerceBOMFolder findByCompanyId_Last(long companyId,
		OrderByComparator<CommerceBOMFolder> orderByComparator)
		throws com.liferay.commerce.bom.exception.NoSuchBOMFolderException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the last commerce bom folder in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce bom folder, or <code>null</code> if a matching commerce bom folder could not be found
	*/
	public static CommerceBOMFolder fetchByCompanyId_Last(long companyId,
		OrderByComparator<CommerceBOMFolder> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the commerce bom folders before and after the current commerce bom folder in the ordered set where companyId = &#63;.
	*
	* @param commerceBOMFolderId the primary key of the current commerce bom folder
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce bom folder
	* @throws NoSuchBOMFolderException if a commerce bom folder with the primary key could not be found
	*/
	public static CommerceBOMFolder[] findByCompanyId_PrevAndNext(
		long commerceBOMFolderId, long companyId,
		OrderByComparator<CommerceBOMFolder> orderByComparator)
		throws com.liferay.commerce.bom.exception.NoSuchBOMFolderException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(commerceBOMFolderId, companyId,
			orderByComparator);
	}

	/**
	* Returns all the commerce bom folders that the user has permission to view where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching commerce bom folders that the user has permission to view
	*/
	public static List<CommerceBOMFolder> filterFindByCompanyId(long companyId) {
		return getPersistence().filterFindByCompanyId(companyId);
	}

	/**
	* Returns a range of all the commerce bom folders that the user has permission to view where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of commerce bom folders
	* @param end the upper bound of the range of commerce bom folders (not inclusive)
	* @return the range of matching commerce bom folders that the user has permission to view
	*/
	public static List<CommerceBOMFolder> filterFindByCompanyId(
		long companyId, int start, int end) {
		return getPersistence().filterFindByCompanyId(companyId, start, end);
	}

	/**
	* Returns an ordered range of all the commerce bom folders that the user has permissions to view where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of commerce bom folders
	* @param end the upper bound of the range of commerce bom folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce bom folders that the user has permission to view
	*/
	public static List<CommerceBOMFolder> filterFindByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CommerceBOMFolder> orderByComparator) {
		return getPersistence()
				   .filterFindByCompanyId(companyId, start, end,
			orderByComparator);
	}

	/**
	* Returns the commerce bom folders before and after the current commerce bom folder in the ordered set of commerce bom folders that the user has permission to view where companyId = &#63;.
	*
	* @param commerceBOMFolderId the primary key of the current commerce bom folder
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce bom folder
	* @throws NoSuchBOMFolderException if a commerce bom folder with the primary key could not be found
	*/
	public static CommerceBOMFolder[] filterFindByCompanyId_PrevAndNext(
		long commerceBOMFolderId, long companyId,
		OrderByComparator<CommerceBOMFolder> orderByComparator)
		throws com.liferay.commerce.bom.exception.NoSuchBOMFolderException {
		return getPersistence()
				   .filterFindByCompanyId_PrevAndNext(commerceBOMFolderId,
			companyId, orderByComparator);
	}

	/**
	* Removes all the commerce bom folders where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public static void removeByCompanyId(long companyId) {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	* Returns the number of commerce bom folders where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching commerce bom folders
	*/
	public static int countByCompanyId(long companyId) {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	* Returns the number of commerce bom folders that the user has permission to view where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching commerce bom folders that the user has permission to view
	*/
	public static int filterCountByCompanyId(long companyId) {
		return getPersistence().filterCountByCompanyId(companyId);
	}

	/**
	* Returns all the commerce bom folders where companyId = &#63; and parentCommerceBOMFolderId = &#63;.
	*
	* @param companyId the company ID
	* @param parentCommerceBOMFolderId the parent commerce bom folder ID
	* @return the matching commerce bom folders
	*/
	public static List<CommerceBOMFolder> findByC_P(long companyId,
		long parentCommerceBOMFolderId) {
		return getPersistence().findByC_P(companyId, parentCommerceBOMFolderId);
	}

	/**
	* Returns a range of all the commerce bom folders where companyId = &#63; and parentCommerceBOMFolderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param parentCommerceBOMFolderId the parent commerce bom folder ID
	* @param start the lower bound of the range of commerce bom folders
	* @param end the upper bound of the range of commerce bom folders (not inclusive)
	* @return the range of matching commerce bom folders
	*/
	public static List<CommerceBOMFolder> findByC_P(long companyId,
		long parentCommerceBOMFolderId, int start, int end) {
		return getPersistence()
				   .findByC_P(companyId, parentCommerceBOMFolderId, start, end);
	}

	/**
	* Returns an ordered range of all the commerce bom folders where companyId = &#63; and parentCommerceBOMFolderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param parentCommerceBOMFolderId the parent commerce bom folder ID
	* @param start the lower bound of the range of commerce bom folders
	* @param end the upper bound of the range of commerce bom folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce bom folders
	*/
	public static List<CommerceBOMFolder> findByC_P(long companyId,
		long parentCommerceBOMFolderId, int start, int end,
		OrderByComparator<CommerceBOMFolder> orderByComparator) {
		return getPersistence()
				   .findByC_P(companyId, parentCommerceBOMFolderId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the commerce bom folders where companyId = &#63; and parentCommerceBOMFolderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param parentCommerceBOMFolderId the parent commerce bom folder ID
	* @param start the lower bound of the range of commerce bom folders
	* @param end the upper bound of the range of commerce bom folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching commerce bom folders
	*/
	public static List<CommerceBOMFolder> findByC_P(long companyId,
		long parentCommerceBOMFolderId, int start, int end,
		OrderByComparator<CommerceBOMFolder> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByC_P(companyId, parentCommerceBOMFolderId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first commerce bom folder in the ordered set where companyId = &#63; and parentCommerceBOMFolderId = &#63;.
	*
	* @param companyId the company ID
	* @param parentCommerceBOMFolderId the parent commerce bom folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce bom folder
	* @throws NoSuchBOMFolderException if a matching commerce bom folder could not be found
	*/
	public static CommerceBOMFolder findByC_P_First(long companyId,
		long parentCommerceBOMFolderId,
		OrderByComparator<CommerceBOMFolder> orderByComparator)
		throws com.liferay.commerce.bom.exception.NoSuchBOMFolderException {
		return getPersistence()
				   .findByC_P_First(companyId, parentCommerceBOMFolderId,
			orderByComparator);
	}

	/**
	* Returns the first commerce bom folder in the ordered set where companyId = &#63; and parentCommerceBOMFolderId = &#63;.
	*
	* @param companyId the company ID
	* @param parentCommerceBOMFolderId the parent commerce bom folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching commerce bom folder, or <code>null</code> if a matching commerce bom folder could not be found
	*/
	public static CommerceBOMFolder fetchByC_P_First(long companyId,
		long parentCommerceBOMFolderId,
		OrderByComparator<CommerceBOMFolder> orderByComparator) {
		return getPersistence()
				   .fetchByC_P_First(companyId, parentCommerceBOMFolderId,
			orderByComparator);
	}

	/**
	* Returns the last commerce bom folder in the ordered set where companyId = &#63; and parentCommerceBOMFolderId = &#63;.
	*
	* @param companyId the company ID
	* @param parentCommerceBOMFolderId the parent commerce bom folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce bom folder
	* @throws NoSuchBOMFolderException if a matching commerce bom folder could not be found
	*/
	public static CommerceBOMFolder findByC_P_Last(long companyId,
		long parentCommerceBOMFolderId,
		OrderByComparator<CommerceBOMFolder> orderByComparator)
		throws com.liferay.commerce.bom.exception.NoSuchBOMFolderException {
		return getPersistence()
				   .findByC_P_Last(companyId, parentCommerceBOMFolderId,
			orderByComparator);
	}

	/**
	* Returns the last commerce bom folder in the ordered set where companyId = &#63; and parentCommerceBOMFolderId = &#63;.
	*
	* @param companyId the company ID
	* @param parentCommerceBOMFolderId the parent commerce bom folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching commerce bom folder, or <code>null</code> if a matching commerce bom folder could not be found
	*/
	public static CommerceBOMFolder fetchByC_P_Last(long companyId,
		long parentCommerceBOMFolderId,
		OrderByComparator<CommerceBOMFolder> orderByComparator) {
		return getPersistence()
				   .fetchByC_P_Last(companyId, parentCommerceBOMFolderId,
			orderByComparator);
	}

	/**
	* Returns the commerce bom folders before and after the current commerce bom folder in the ordered set where companyId = &#63; and parentCommerceBOMFolderId = &#63;.
	*
	* @param commerceBOMFolderId the primary key of the current commerce bom folder
	* @param companyId the company ID
	* @param parentCommerceBOMFolderId the parent commerce bom folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce bom folder
	* @throws NoSuchBOMFolderException if a commerce bom folder with the primary key could not be found
	*/
	public static CommerceBOMFolder[] findByC_P_PrevAndNext(
		long commerceBOMFolderId, long companyId,
		long parentCommerceBOMFolderId,
		OrderByComparator<CommerceBOMFolder> orderByComparator)
		throws com.liferay.commerce.bom.exception.NoSuchBOMFolderException {
		return getPersistence()
				   .findByC_P_PrevAndNext(commerceBOMFolderId, companyId,
			parentCommerceBOMFolderId, orderByComparator);
	}

	/**
	* Returns all the commerce bom folders that the user has permission to view where companyId = &#63; and parentCommerceBOMFolderId = &#63;.
	*
	* @param companyId the company ID
	* @param parentCommerceBOMFolderId the parent commerce bom folder ID
	* @return the matching commerce bom folders that the user has permission to view
	*/
	public static List<CommerceBOMFolder> filterFindByC_P(long companyId,
		long parentCommerceBOMFolderId) {
		return getPersistence()
				   .filterFindByC_P(companyId, parentCommerceBOMFolderId);
	}

	/**
	* Returns a range of all the commerce bom folders that the user has permission to view where companyId = &#63; and parentCommerceBOMFolderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param parentCommerceBOMFolderId the parent commerce bom folder ID
	* @param start the lower bound of the range of commerce bom folders
	* @param end the upper bound of the range of commerce bom folders (not inclusive)
	* @return the range of matching commerce bom folders that the user has permission to view
	*/
	public static List<CommerceBOMFolder> filterFindByC_P(long companyId,
		long parentCommerceBOMFolderId, int start, int end) {
		return getPersistence()
				   .filterFindByC_P(companyId, parentCommerceBOMFolderId,
			start, end);
	}

	/**
	* Returns an ordered range of all the commerce bom folders that the user has permissions to view where companyId = &#63; and parentCommerceBOMFolderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param parentCommerceBOMFolderId the parent commerce bom folder ID
	* @param start the lower bound of the range of commerce bom folders
	* @param end the upper bound of the range of commerce bom folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching commerce bom folders that the user has permission to view
	*/
	public static List<CommerceBOMFolder> filterFindByC_P(long companyId,
		long parentCommerceBOMFolderId, int start, int end,
		OrderByComparator<CommerceBOMFolder> orderByComparator) {
		return getPersistence()
				   .filterFindByC_P(companyId, parentCommerceBOMFolderId,
			start, end, orderByComparator);
	}

	/**
	* Returns the commerce bom folders before and after the current commerce bom folder in the ordered set of commerce bom folders that the user has permission to view where companyId = &#63; and parentCommerceBOMFolderId = &#63;.
	*
	* @param commerceBOMFolderId the primary key of the current commerce bom folder
	* @param companyId the company ID
	* @param parentCommerceBOMFolderId the parent commerce bom folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next commerce bom folder
	* @throws NoSuchBOMFolderException if a commerce bom folder with the primary key could not be found
	*/
	public static CommerceBOMFolder[] filterFindByC_P_PrevAndNext(
		long commerceBOMFolderId, long companyId,
		long parentCommerceBOMFolderId,
		OrderByComparator<CommerceBOMFolder> orderByComparator)
		throws com.liferay.commerce.bom.exception.NoSuchBOMFolderException {
		return getPersistence()
				   .filterFindByC_P_PrevAndNext(commerceBOMFolderId, companyId,
			parentCommerceBOMFolderId, orderByComparator);
	}

	/**
	* Removes all the commerce bom folders where companyId = &#63; and parentCommerceBOMFolderId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param parentCommerceBOMFolderId the parent commerce bom folder ID
	*/
	public static void removeByC_P(long companyId,
		long parentCommerceBOMFolderId) {
		getPersistence().removeByC_P(companyId, parentCommerceBOMFolderId);
	}

	/**
	* Returns the number of commerce bom folders where companyId = &#63; and parentCommerceBOMFolderId = &#63;.
	*
	* @param companyId the company ID
	* @param parentCommerceBOMFolderId the parent commerce bom folder ID
	* @return the number of matching commerce bom folders
	*/
	public static int countByC_P(long companyId, long parentCommerceBOMFolderId) {
		return getPersistence().countByC_P(companyId, parentCommerceBOMFolderId);
	}

	/**
	* Returns the number of commerce bom folders that the user has permission to view where companyId = &#63; and parentCommerceBOMFolderId = &#63;.
	*
	* @param companyId the company ID
	* @param parentCommerceBOMFolderId the parent commerce bom folder ID
	* @return the number of matching commerce bom folders that the user has permission to view
	*/
	public static int filterCountByC_P(long companyId,
		long parentCommerceBOMFolderId) {
		return getPersistence()
				   .filterCountByC_P(companyId, parentCommerceBOMFolderId);
	}

	/**
	* Caches the commerce bom folder in the entity cache if it is enabled.
	*
	* @param commerceBOMFolder the commerce bom folder
	*/
	public static void cacheResult(CommerceBOMFolder commerceBOMFolder) {
		getPersistence().cacheResult(commerceBOMFolder);
	}

	/**
	* Caches the commerce bom folders in the entity cache if it is enabled.
	*
	* @param commerceBOMFolders the commerce bom folders
	*/
	public static void cacheResult(List<CommerceBOMFolder> commerceBOMFolders) {
		getPersistence().cacheResult(commerceBOMFolders);
	}

	/**
	* Creates a new commerce bom folder with the primary key. Does not add the commerce bom folder to the database.
	*
	* @param commerceBOMFolderId the primary key for the new commerce bom folder
	* @return the new commerce bom folder
	*/
	public static CommerceBOMFolder create(long commerceBOMFolderId) {
		return getPersistence().create(commerceBOMFolderId);
	}

	/**
	* Removes the commerce bom folder with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceBOMFolderId the primary key of the commerce bom folder
	* @return the commerce bom folder that was removed
	* @throws NoSuchBOMFolderException if a commerce bom folder with the primary key could not be found
	*/
	public static CommerceBOMFolder remove(long commerceBOMFolderId)
		throws com.liferay.commerce.bom.exception.NoSuchBOMFolderException {
		return getPersistence().remove(commerceBOMFolderId);
	}

	public static CommerceBOMFolder updateImpl(
		CommerceBOMFolder commerceBOMFolder) {
		return getPersistence().updateImpl(commerceBOMFolder);
	}

	/**
	* Returns the commerce bom folder with the primary key or throws a {@link NoSuchBOMFolderException} if it could not be found.
	*
	* @param commerceBOMFolderId the primary key of the commerce bom folder
	* @return the commerce bom folder
	* @throws NoSuchBOMFolderException if a commerce bom folder with the primary key could not be found
	*/
	public static CommerceBOMFolder findByPrimaryKey(long commerceBOMFolderId)
		throws com.liferay.commerce.bom.exception.NoSuchBOMFolderException {
		return getPersistence().findByPrimaryKey(commerceBOMFolderId);
	}

	/**
	* Returns the commerce bom folder with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param commerceBOMFolderId the primary key of the commerce bom folder
	* @return the commerce bom folder, or <code>null</code> if a commerce bom folder with the primary key could not be found
	*/
	public static CommerceBOMFolder fetchByPrimaryKey(long commerceBOMFolderId) {
		return getPersistence().fetchByPrimaryKey(commerceBOMFolderId);
	}

	public static java.util.Map<java.io.Serializable, CommerceBOMFolder> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the commerce bom folders.
	*
	* @return the commerce bom folders
	*/
	public static List<CommerceBOMFolder> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the commerce bom folders.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce bom folders
	* @param end the upper bound of the range of commerce bom folders (not inclusive)
	* @return the range of commerce bom folders
	*/
	public static List<CommerceBOMFolder> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the commerce bom folders.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce bom folders
	* @param end the upper bound of the range of commerce bom folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of commerce bom folders
	*/
	public static List<CommerceBOMFolder> findAll(int start, int end,
		OrderByComparator<CommerceBOMFolder> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the commerce bom folders.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce bom folders
	* @param end the upper bound of the range of commerce bom folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of commerce bom folders
	*/
	public static List<CommerceBOMFolder> findAll(int start, int end,
		OrderByComparator<CommerceBOMFolder> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the commerce bom folders from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of commerce bom folders.
	*
	* @return the number of commerce bom folders
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static CommerceBOMFolderPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommerceBOMFolderPersistence, CommerceBOMFolderPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceBOMFolderPersistence.class);

		ServiceTracker<CommerceBOMFolderPersistence, CommerceBOMFolderPersistence> serviceTracker =
			new ServiceTracker<CommerceBOMFolderPersistence, CommerceBOMFolderPersistence>(bundle.getBundleContext(),
				CommerceBOMFolderPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}