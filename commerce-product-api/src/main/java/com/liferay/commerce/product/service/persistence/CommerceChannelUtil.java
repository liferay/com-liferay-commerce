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

package com.liferay.commerce.product.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.product.model.CommerceChannel;
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
 * The persistence utility for the commerce channel service. This utility wraps <code>com.liferay.commerce.product.service.persistence.impl.CommerceChannelPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @see CommerceChannelPersistence
 * @generated
 */
@ProviderType
public class CommerceChannelUtil {

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
	public static void clearCache(CommerceChannel commerceChannel) {
		getPersistence().clearCache(commerceChannel);
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
	public static Map<Serializable, CommerceChannel> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<CommerceChannel> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CommerceChannel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CommerceChannel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CommerceChannel> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CommerceChannel update(CommerceChannel commerceChannel) {
		return getPersistence().update(commerceChannel);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CommerceChannel update(
		CommerceChannel commerceChannel, ServiceContext serviceContext) {

		return getPersistence().update(commerceChannel, serviceContext);
	}

	/**
	 * Returns all the commerce channels where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching commerce channels
	 */
	public static List<CommerceChannel> findByCompanyId(long companyId) {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	 * Returns a range of all the commerce channels where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceChannelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce channels
	 * @param end the upper bound of the range of commerce channels (not inclusive)
	 * @return the range of matching commerce channels
	 */
	public static List<CommerceChannel> findByCompanyId(
		long companyId, int start, int end) {

		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the commerce channels where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceChannelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce channels
	 * @param end the upper bound of the range of commerce channels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce channels
	 */
	public static List<CommerceChannel> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CommerceChannel> orderByComparator) {

		return getPersistence().findByCompanyId(
			companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce channels where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceChannelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce channels
	 * @param end the upper bound of the range of commerce channels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce channels
	 */
	public static List<CommerceChannel> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CommerceChannel> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByCompanyId(
			companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first commerce channel in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce channel
	 * @throws NoSuchChannelException if a matching commerce channel could not be found
	 */
	public static CommerceChannel findByCompanyId_First(
			long companyId,
			OrderByComparator<CommerceChannel> orderByComparator)
		throws com.liferay.commerce.product.exception.NoSuchChannelException {

		return getPersistence().findByCompanyId_First(
			companyId, orderByComparator);
	}

	/**
	 * Returns the first commerce channel in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce channel, or <code>null</code> if a matching commerce channel could not be found
	 */
	public static CommerceChannel fetchByCompanyId_First(
		long companyId, OrderByComparator<CommerceChannel> orderByComparator) {

		return getPersistence().fetchByCompanyId_First(
			companyId, orderByComparator);
	}

	/**
	 * Returns the last commerce channel in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce channel
	 * @throws NoSuchChannelException if a matching commerce channel could not be found
	 */
	public static CommerceChannel findByCompanyId_Last(
			long companyId,
			OrderByComparator<CommerceChannel> orderByComparator)
		throws com.liferay.commerce.product.exception.NoSuchChannelException {

		return getPersistence().findByCompanyId_Last(
			companyId, orderByComparator);
	}

	/**
	 * Returns the last commerce channel in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce channel, or <code>null</code> if a matching commerce channel could not be found
	 */
	public static CommerceChannel fetchByCompanyId_Last(
		long companyId, OrderByComparator<CommerceChannel> orderByComparator) {

		return getPersistence().fetchByCompanyId_Last(
			companyId, orderByComparator);
	}

	/**
	 * Returns the commerce channels before and after the current commerce channel in the ordered set where companyId = &#63;.
	 *
	 * @param commerceChannelId the primary key of the current commerce channel
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce channel
	 * @throws NoSuchChannelException if a commerce channel with the primary key could not be found
	 */
	public static CommerceChannel[] findByCompanyId_PrevAndNext(
			long commerceChannelId, long companyId,
			OrderByComparator<CommerceChannel> orderByComparator)
		throws com.liferay.commerce.product.exception.NoSuchChannelException {

		return getPersistence().findByCompanyId_PrevAndNext(
			commerceChannelId, companyId, orderByComparator);
	}

	/**
	 * Returns all the commerce channels that the user has permission to view where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching commerce channels that the user has permission to view
	 */
	public static List<CommerceChannel> filterFindByCompanyId(long companyId) {
		return getPersistence().filterFindByCompanyId(companyId);
	}

	/**
	 * Returns a range of all the commerce channels that the user has permission to view where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceChannelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce channels
	 * @param end the upper bound of the range of commerce channels (not inclusive)
	 * @return the range of matching commerce channels that the user has permission to view
	 */
	public static List<CommerceChannel> filterFindByCompanyId(
		long companyId, int start, int end) {

		return getPersistence().filterFindByCompanyId(companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the commerce channels that the user has permissions to view where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceChannelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce channels
	 * @param end the upper bound of the range of commerce channels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce channels that the user has permission to view
	 */
	public static List<CommerceChannel> filterFindByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CommerceChannel> orderByComparator) {

		return getPersistence().filterFindByCompanyId(
			companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the commerce channels before and after the current commerce channel in the ordered set of commerce channels that the user has permission to view where companyId = &#63;.
	 *
	 * @param commerceChannelId the primary key of the current commerce channel
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce channel
	 * @throws NoSuchChannelException if a commerce channel with the primary key could not be found
	 */
	public static CommerceChannel[] filterFindByCompanyId_PrevAndNext(
			long commerceChannelId, long companyId,
			OrderByComparator<CommerceChannel> orderByComparator)
		throws com.liferay.commerce.product.exception.NoSuchChannelException {

		return getPersistence().filterFindByCompanyId_PrevAndNext(
			commerceChannelId, companyId, orderByComparator);
	}

	/**
	 * Removes all the commerce channels where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	public static void removeByCompanyId(long companyId) {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	 * Returns the number of commerce channels where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching commerce channels
	 */
	public static int countByCompanyId(long companyId) {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	 * Returns the number of commerce channels that the user has permission to view where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching commerce channels that the user has permission to view
	 */
	public static int filterCountByCompanyId(long companyId) {
		return getPersistence().filterCountByCompanyId(companyId);
	}

	/**
	 * Returns the commerce channel where siteGroupId = &#63; or throws a <code>NoSuchChannelException</code> if it could not be found.
	 *
	 * @param siteGroupId the site group ID
	 * @return the matching commerce channel
	 * @throws NoSuchChannelException if a matching commerce channel could not be found
	 */
	public static CommerceChannel findBySiteGroupId(long siteGroupId)
		throws com.liferay.commerce.product.exception.NoSuchChannelException {

		return getPersistence().findBySiteGroupId(siteGroupId);
	}

	/**
	 * Returns the commerce channel where siteGroupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param siteGroupId the site group ID
	 * @return the matching commerce channel, or <code>null</code> if a matching commerce channel could not be found
	 */
	public static CommerceChannel fetchBySiteGroupId(long siteGroupId) {
		return getPersistence().fetchBySiteGroupId(siteGroupId);
	}

	/**
	 * Returns the commerce channel where siteGroupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param siteGroupId the site group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching commerce channel, or <code>null</code> if a matching commerce channel could not be found
	 */
	public static CommerceChannel fetchBySiteGroupId(
		long siteGroupId, boolean useFinderCache) {

		return getPersistence().fetchBySiteGroupId(siteGroupId, useFinderCache);
	}

	/**
	 * Removes the commerce channel where siteGroupId = &#63; from the database.
	 *
	 * @param siteGroupId the site group ID
	 * @return the commerce channel that was removed
	 */
	public static CommerceChannel removeBySiteGroupId(long siteGroupId)
		throws com.liferay.commerce.product.exception.NoSuchChannelException {

		return getPersistence().removeBySiteGroupId(siteGroupId);
	}

	/**
	 * Returns the number of commerce channels where siteGroupId = &#63;.
	 *
	 * @param siteGroupId the site group ID
	 * @return the number of matching commerce channels
	 */
	public static int countBySiteGroupId(long siteGroupId) {
		return getPersistence().countBySiteGroupId(siteGroupId);
	}

	/**
	 * Returns the commerce channel where companyId = &#63; and externalReferenceCode = &#63; or throws a <code>NoSuchChannelException</code> if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the matching commerce channel
	 * @throws NoSuchChannelException if a matching commerce channel could not be found
	 */
	public static CommerceChannel findByC_ERC(
			long companyId, String externalReferenceCode)
		throws com.liferay.commerce.product.exception.NoSuchChannelException {

		return getPersistence().findByC_ERC(companyId, externalReferenceCode);
	}

	/**
	 * Returns the commerce channel where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the matching commerce channel, or <code>null</code> if a matching commerce channel could not be found
	 */
	public static CommerceChannel fetchByC_ERC(
		long companyId, String externalReferenceCode) {

		return getPersistence().fetchByC_ERC(companyId, externalReferenceCode);
	}

	/**
	 * Returns the commerce channel where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching commerce channel, or <code>null</code> if a matching commerce channel could not be found
	 */
	public static CommerceChannel fetchByC_ERC(
		long companyId, String externalReferenceCode, boolean useFinderCache) {

		return getPersistence().fetchByC_ERC(
			companyId, externalReferenceCode, useFinderCache);
	}

	/**
	 * Removes the commerce channel where companyId = &#63; and externalReferenceCode = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the commerce channel that was removed
	 */
	public static CommerceChannel removeByC_ERC(
			long companyId, String externalReferenceCode)
		throws com.liferay.commerce.product.exception.NoSuchChannelException {

		return getPersistence().removeByC_ERC(companyId, externalReferenceCode);
	}

	/**
	 * Returns the number of commerce channels where companyId = &#63; and externalReferenceCode = &#63;.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the number of matching commerce channels
	 */
	public static int countByC_ERC(
		long companyId, String externalReferenceCode) {

		return getPersistence().countByC_ERC(companyId, externalReferenceCode);
	}

	/**
	 * Caches the commerce channel in the entity cache if it is enabled.
	 *
	 * @param commerceChannel the commerce channel
	 */
	public static void cacheResult(CommerceChannel commerceChannel) {
		getPersistence().cacheResult(commerceChannel);
	}

	/**
	 * Caches the commerce channels in the entity cache if it is enabled.
	 *
	 * @param commerceChannels the commerce channels
	 */
	public static void cacheResult(List<CommerceChannel> commerceChannels) {
		getPersistence().cacheResult(commerceChannels);
	}

	/**
	 * Creates a new commerce channel with the primary key. Does not add the commerce channel to the database.
	 *
	 * @param commerceChannelId the primary key for the new commerce channel
	 * @return the new commerce channel
	 */
	public static CommerceChannel create(long commerceChannelId) {
		return getPersistence().create(commerceChannelId);
	}

	/**
	 * Removes the commerce channel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceChannelId the primary key of the commerce channel
	 * @return the commerce channel that was removed
	 * @throws NoSuchChannelException if a commerce channel with the primary key could not be found
	 */
	public static CommerceChannel remove(long commerceChannelId)
		throws com.liferay.commerce.product.exception.NoSuchChannelException {

		return getPersistence().remove(commerceChannelId);
	}

	public static CommerceChannel updateImpl(CommerceChannel commerceChannel) {
		return getPersistence().updateImpl(commerceChannel);
	}

	/**
	 * Returns the commerce channel with the primary key or throws a <code>NoSuchChannelException</code> if it could not be found.
	 *
	 * @param commerceChannelId the primary key of the commerce channel
	 * @return the commerce channel
	 * @throws NoSuchChannelException if a commerce channel with the primary key could not be found
	 */
	public static CommerceChannel findByPrimaryKey(long commerceChannelId)
		throws com.liferay.commerce.product.exception.NoSuchChannelException {

		return getPersistence().findByPrimaryKey(commerceChannelId);
	}

	/**
	 * Returns the commerce channel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceChannelId the primary key of the commerce channel
	 * @return the commerce channel, or <code>null</code> if a commerce channel with the primary key could not be found
	 */
	public static CommerceChannel fetchByPrimaryKey(long commerceChannelId) {
		return getPersistence().fetchByPrimaryKey(commerceChannelId);
	}

	/**
	 * Returns all the commerce channels.
	 *
	 * @return the commerce channels
	 */
	public static List<CommerceChannel> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the commerce channels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceChannelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce channels
	 * @param end the upper bound of the range of commerce channels (not inclusive)
	 * @return the range of commerce channels
	 */
	public static List<CommerceChannel> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the commerce channels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceChannelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce channels
	 * @param end the upper bound of the range of commerce channels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce channels
	 */
	public static List<CommerceChannel> findAll(
		int start, int end,
		OrderByComparator<CommerceChannel> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce channels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceChannelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce channels
	 * @param end the upper bound of the range of commerce channels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of commerce channels
	 */
	public static List<CommerceChannel> findAll(
		int start, int end,
		OrderByComparator<CommerceChannel> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the commerce channels from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of commerce channels.
	 *
	 * @return the number of commerce channels
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CommerceChannelPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CommerceChannelPersistence, CommerceChannelPersistence>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			CommerceChannelPersistence.class);

		ServiceTracker<CommerceChannelPersistence, CommerceChannelPersistence>
			serviceTracker =
				new ServiceTracker
					<CommerceChannelPersistence, CommerceChannelPersistence>(
						bundle.getBundleContext(),
						CommerceChannelPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}