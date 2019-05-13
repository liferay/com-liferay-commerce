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

package com.liferay.commerce.inventory.service.impl;

import com.liferay.commerce.inventory.constants.CommerceInventoryConstants;
import com.liferay.commerce.inventory.exception.CommerceInventoryWarehouseActiveException;
import com.liferay.commerce.inventory.exception.CommerceInventoryWarehouseNameException;
import com.liferay.commerce.inventory.exception.NoSuchInventoryWarehouseException;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouse;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouseGroupRel;
import com.liferay.commerce.inventory.service.base.CommerceInventoryWarehouseLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

/**
 * @author Luca Pellizzon
 */
public class CommerceInventoryWarehouseLocalServiceImpl
	extends CommerceInventoryWarehouseLocalServiceBaseImpl {

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceInventoryWarehouse addCommerceWarehouse(
			String name, String description, boolean active, String street1,
			String street2, String street3, String city, String zip,
			String commerceRegionCode, String commerceCountryCode,
			double latitude, double longitude, ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(serviceContext.getUserId());

		validate(name, active, latitude, longitude);

		long commerceWarehouseId = counterLocalService.increment();

		CommerceInventoryWarehouse commerceWarehouse =
			commerceInventoryWarehousePersistence.create(commerceWarehouseId);

		commerceWarehouse.setCompanyId(user.getCompanyId());
		commerceWarehouse.setUserId(user.getUserId());
		commerceWarehouse.setUserName(user.getFullName());
		commerceWarehouse.setName(name);
		commerceWarehouse.setDescription(description);
		commerceWarehouse.setActive(active);
		commerceWarehouse.setStreet1(street1);
		commerceWarehouse.setStreet2(street2);
		commerceWarehouse.setStreet3(street3);
		commerceWarehouse.setCity(city);
		commerceWarehouse.setZip(zip);
		commerceWarehouse.setCommerceRegionCode(commerceRegionCode);
		commerceWarehouse.setCountryTwoLettersISOCode(commerceCountryCode);
		commerceWarehouse.setLatitude(latitude);
		commerceWarehouse.setLongitude(longitude);
		commerceWarehouse.setExpandoBridgeAttributes(serviceContext);

		commerceInventoryWarehousePersistence.update(commerceWarehouse);

		// Resources

		resourceLocalService.addResources(
			user.getCompanyId(), GroupConstants.DEFAULT_LIVE_GROUP_ID,
			user.getUserId(), CommerceInventoryWarehouse.class.getName(),
			commerceWarehouse.getCommerceInventoryWarehouseId(), false, false,
			false);

		return commerceWarehouse;
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	public CommerceInventoryWarehouse deleteCommerceWarehouse(
			CommerceInventoryWarehouse commerceWarehouse)
		throws PortalException {

		commerceInventoryWarehousePersistence.remove(commerceWarehouse);

		// Commerce warehouse items

		commerceInventoryWarehouseItemLocalService.deleteCommerceWarehouseItems(
			commerceWarehouse.getCommerceInventoryWarehouseId());

		// Expando

		expandoRowLocalService.deleteRows(
			commerceWarehouse.getCommerceInventoryWarehouseId());

		// Resources

		resourceLocalService.deleteResource(
			commerceWarehouse, ResourceConstants.SCOPE_INDIVIDUAL);

		return commerceWarehouse;
	}

	@Override
	public CommerceInventoryWarehouse fetchDefaultCommerceWarehouse(
		long groupId) {

		long commerceWarehouseId =
			commerceInventoryWarehouseGroupRelLocalService.
				getPrimaryCommerceWarehouseId(groupId);

		return commerceInventoryWarehousePersistence.fetchByPrimaryKey(
			commerceWarehouseId);
	}

	@Override
	public CommerceInventoryWarehouse geolocateCommerceWarehouse(
			long commerceWarehouseId, double latitude, double longitude)
		throws PortalException {

		CommerceInventoryWarehouse commerceWarehouse =
			commerceInventoryWarehousePersistence.findByPrimaryKey(
				commerceWarehouseId);

		commerceWarehouse.setLatitude(latitude);
		commerceWarehouse.setLongitude(longitude);

		return commerceInventoryWarehousePersistence.update(commerceWarehouse);
	}

	@Override
	public List<CommerceInventoryWarehouse> getCommerceWarehouses(
		long companyId, long groupId, boolean active,
		String commerceCountryCode) {

		return commerceInventoryWarehouseFinder.
			findWarehousesByGroupIdAndActiveAndCountryISOCode(
				companyId, groupId, active, commerceCountryCode);
	}

	@Override
	public List<CommerceInventoryWarehouse> getCommerceWarehousesByGroupId(
		long companyId, long groupId) {

		return commerceInventoryWarehouseFinder.findWarehousesByGroupId(
			companyId, groupId);
	}

	@Override
	public List<CommerceInventoryWarehouse>
		getCommerceWarehousesByGroupIdAndActive(
			long companyId, long groupId, boolean active) {

		return commerceInventoryWarehouseFinder.
			findWarehousesByGroupIdAndActive(companyId, groupId, active);
	}

	@Override
	public List<CommerceInventoryWarehouse>
		getCommerceWarehousesByGroupIdAndSku(
			long companyId, long groupId, String sku) {

		return commerceInventoryWarehouseFinder.findWarehousesByGroupIdAndSku(
			companyId, groupId, sku);
	}

	@Override
	public int getCommerceWarehousesCount(
		long companyId, long groupId, boolean active) {

		return commerceInventoryWarehouseFinder.countByG_A(
			companyId, groupId, active);
	}

	@Override
	public int getCommerceWarehousesCount(
		long companyId, long groupId, boolean active,
		String commerceCountryCode) {

		if (commerceCountryCode != null) {
			return commerceInventoryWarehouseFinder.countByG_A_C(
				companyId, groupId, active, commerceCountryCode);
		}

		return getCommerceWarehousesCount(companyId, groupId, active);
	}

	@Override
	public CommerceInventoryWarehouse getDefaultCommerceWarehouse(long groupId)
		throws NoSuchInventoryWarehouseException {

		long commerceWarehouseId =
			commerceInventoryWarehouseGroupRelLocalService.
				getPrimaryCommerceWarehouseId(groupId);

		return commerceInventoryWarehousePersistence.findByPrimaryKey(
			commerceWarehouseId);
	}

	@Override
	public CommerceInventoryWarehouse importDefaultCommerceWarehouse(
			ServiceContext serviceContext)
		throws PortalException {

		List<CommerceInventoryWarehouseGroupRel> commerceWarehouseGroupRels =
			commerceInventoryWarehouseGroupRelPersistence.findByG_P(
				serviceContext.getScopeGroupId(), true);

		if (commerceWarehouseGroupRels.isEmpty()) {
			return _addDefaultCommerceWarehouse(
				CommerceInventoryConstants.DEFAULT_WAREHOUSE_NAME, null, null,
				null, null, null, "", "", -1, -1, serviceContext);
		}

		CommerceInventoryWarehouseGroupRel commerceWarehouseGroupRel =
			commerceWarehouseGroupRels.get(0);

		return commerceInventoryWarehouseLocalService.
			getCommerceInventoryWarehouse(
				commerceWarehouseGroupRel.getCommerceWarehouseId());
	}

	@Override
	public List<CommerceInventoryWarehouse> search(
		long companyId, long groupId, String keywords, Boolean active,
		String commerceCountryCode, int start, int end,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator) {

		return commerceInventoryWarehouseFinder.findByKeywords(
			companyId, groupId, keywords, active, commerceCountryCode, start,
			end, orderByComparator);
	}

	@Override
	public int searchCount(
		long companyId, long groupId, String keywords, Boolean active,
		String commerceCountryCode) {

		return commerceInventoryWarehouseFinder.countByKeywords(
			companyId, groupId, keywords, active, commerceCountryCode);
	}

	@Override
	public CommerceInventoryWarehouse setActive(
			long commerceWarehouseId, boolean active)
		throws PortalException {

		CommerceInventoryWarehouse commerceWarehouse =
			commerceInventoryWarehousePersistence.findByPrimaryKey(
				commerceWarehouseId);

		commerceWarehouse.setActive(active);

		commerceInventoryWarehousePersistence.update(commerceWarehouse);

		return commerceWarehouse;
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceInventoryWarehouse updateCommerceWarehouse(
			long commerceWarehouseId, String name, String description,
			boolean active, String street1, String street2, String street3,
			String city, String zip, String commerceRegionCode,
			String commerceCountryCode, double latitude, double longitude,
			ServiceContext serviceContext)
		throws PortalException {

		validate(name, active, latitude, longitude);

		CommerceInventoryWarehouse commerceWarehouse =
			commerceInventoryWarehousePersistence.findByPrimaryKey(
				commerceWarehouseId);

		commerceWarehouse.setName(name);
		commerceWarehouse.setDescription(description);
		commerceWarehouse.setActive(active);
		commerceWarehouse.setStreet1(street1);
		commerceWarehouse.setStreet2(street2);
		commerceWarehouse.setStreet3(street3);
		commerceWarehouse.setCity(city);
		commerceWarehouse.setZip(zip);
		commerceWarehouse.setCommerceRegionCode(commerceRegionCode);
		commerceWarehouse.setCountryTwoLettersISOCode(commerceCountryCode);
		commerceWarehouse.setLatitude(latitude);
		commerceWarehouse.setLongitude(longitude);
		commerceWarehouse.setExpandoBridgeAttributes(serviceContext);

		commerceInventoryWarehousePersistence.update(commerceWarehouse);

		return commerceWarehouse;
	}

	protected void validate(
			String name, boolean active, double latitude, double longitude)
		throws PortalException {

		if (Validator.isNull(name)) {
			throw new CommerceInventoryWarehouseNameException();
		}

		if (active && (latitude == 0) && (longitude == 0)) {
			throw new CommerceInventoryWarehouseActiveException();
		}
	}

	private CommerceInventoryWarehouse _addDefaultCommerceWarehouse(
			String name, String street1, String street2, String street3,
			String city, String zip, String commerceRegionCode,
			String commerceCountryCode, double latitude, double longitude,
			ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(serviceContext.getUserId());

		validate(name, true, latitude, longitude);

		long commerceWarehouseId = counterLocalService.increment();

		CommerceInventoryWarehouse commerceWarehouse =
			commerceInventoryWarehousePersistence.create(commerceWarehouseId);

		commerceWarehouse.setCompanyId(user.getCompanyId());
		commerceWarehouse.setUserId(user.getUserId());
		commerceWarehouse.setUserName(user.getFullName());
		commerceWarehouse.setName(name);
		commerceWarehouse.setActive(true);
		commerceWarehouse.setStreet1(street1);
		commerceWarehouse.setStreet2(street2);
		commerceWarehouse.setStreet3(street3);
		commerceWarehouse.setCity(city);
		commerceWarehouse.setZip(zip);
		commerceWarehouse.setCommerceRegionCode(commerceRegionCode);
		commerceWarehouse.setCountryTwoLettersISOCode(commerceCountryCode);
		commerceWarehouse.setLatitude(latitude);
		commerceWarehouse.setLongitude(longitude);

		commerceInventoryWarehousePersistence.update(commerceWarehouse);

		commerceInventoryWarehouseGroupRelLocalService.
			addCommerceWarehouseGroupRel(
				commerceWarehouseId, true, serviceContext);

		return commerceWarehouse;
	}

}