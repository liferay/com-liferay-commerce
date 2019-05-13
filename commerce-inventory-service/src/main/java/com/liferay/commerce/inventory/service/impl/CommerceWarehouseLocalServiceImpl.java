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
import com.liferay.commerce.inventory.exception.CommerceWarehouseActiveException;
import com.liferay.commerce.inventory.exception.CommerceWarehouseNameException;
import com.liferay.commerce.inventory.exception.NoSuchWarehouseException;
import com.liferay.commerce.inventory.model.CommerceWarehouse;
import com.liferay.commerce.inventory.model.CommerceWarehouseGroupRel;
import com.liferay.commerce.inventory.service.base.CommerceWarehouseLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

/**
 * The implementation of the commerce warehouse local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.commerce.inventory.service.CommerceWarehouseLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommerceWarehouseLocalServiceBaseImpl
 * @see com.liferay.commerce.inventory.service.CommerceWarehouseLocalServiceUtil
 */
public class CommerceWarehouseLocalServiceImpl
	extends CommerceWarehouseLocalServiceBaseImpl {

	@Override
	public CommerceWarehouse addCommerceWarehouse(
			String name, String description, boolean active, String street1,
			String street2, String street3, String city, String zip,
			String commerceRegionCode, String commerceCountryCode,
			double latitude, double longitude, ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(serviceContext.getUserId());

		validate(name, active, latitude, longitude);

		long commerceWarehouseId = counterLocalService.increment();

		CommerceWarehouse commerceWarehouse =
			commerceWarehousePersistence.create(commerceWarehouseId);

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

		commerceWarehousePersistence.update(commerceWarehouse);

		return commerceWarehouse;
	}

	@Override
	public CommerceWarehouse deleteCommerceWarehouse(long commerceWarehouseId)
		throws PortalException {

		CommerceWarehouse commerceWarehouse =
			commerceWarehousePersistence.findByPrimaryKey(commerceWarehouseId);

		return commerceWarehouseLocalService.deleteCommerceWarehouse(
			commerceWarehouse);
	}

	@Override
	public CommerceWarehouse fetchDefaultCommerceWarehouse(long groupId) {
		long commerceWarehouseId =
			commerceWarehouseGroupRelLocalService.getPrimaryCommerceWarehouseId(
				groupId);

		return commerceWarehousePersistence.fetchByPrimaryKey(
			commerceWarehouseId);
	}

	@Override
	public CommerceWarehouse geolocateCommerceWarehouse(
			long commerceWarehouseId, double latitude, double longitude)
		throws PortalException {

		CommerceWarehouse commerceWarehouse =
			commerceWarehousePersistence.findByPrimaryKey(commerceWarehouseId);

		commerceWarehouse.setLatitude(latitude);
		commerceWarehouse.setLongitude(longitude);

		return commerceWarehousePersistence.update(commerceWarehouse);
	}

	@Override
	public List<CommerceWarehouse> getCommerceWarehouses(
		long groupId, boolean active, String commerceCountryCode) {

		return commerceWarehouseFinder.
			findWarehousesByGroupIdAndActiveAndCountryISOCode(
				groupId, active, commerceCountryCode);
	}

	@Override
	public List<CommerceWarehouse> getCommerceWarehousesByGroupId(
		long groupId) {

		return commerceWarehouseFinder.findWarehousesByGroupId(groupId);
	}

	@Override
	public List<CommerceWarehouse> getCommerceWarehousesByGroupIdAndActive(
		long groupId, boolean active) {

		return commerceWarehouseFinder.findWarehousesByGroupIdAndActive(
			groupId, active);
	}

	@Override
	public List<CommerceWarehouse> getCommerceWarehousesByGroupIdAndSku(
		long groupId, String sku) {

		return commerceWarehouseFinder.findWarehousesByGroupIdAndSku(
			groupId, sku);
	}

	@Override
	public int getCommerceWarehousesCount(long groupId, boolean active) {
		return commerceWarehouseFinder.countByG_A(groupId, active);
	}

	@Override
	public int getCommerceWarehousesCount(
		long groupId, boolean active, String commerceCountryCode) {

		if (commerceCountryCode != null) {
			return commerceWarehouseFinder.countByG_A_C(
				groupId, active, commerceCountryCode);
		}

		return getCommerceWarehousesCount(groupId, active);
	}

	@Override
	public CommerceWarehouse getDefaultCommerceWarehouse(long groupId)
		throws NoSuchWarehouseException {

		long commerceWarehouseId =
			commerceWarehouseGroupRelLocalService.getPrimaryCommerceWarehouseId(
				groupId);

		return commerceWarehousePersistence.findByPrimaryKey(
			commerceWarehouseId);
	}

	@Override
	public CommerceWarehouse importDefaultCommerceWarehouse(
			ServiceContext serviceContext)
		throws PortalException {

		List<CommerceWarehouseGroupRel> commerceWarehouseGroupRels =
			commerceWarehouseGroupRelPersistence.findByG_P(
				serviceContext.getScopeGroupId(), true);

		if (commerceWarehouseGroupRels.isEmpty()) {
			return _addDefaultCommerceWarehouse(
				CommerceInventoryConstants.DEFAULT_WAREHOUSE_NAME, null, null,
				null, null, null, "", "", -1, -1, serviceContext);
		}

		CommerceWarehouseGroupRel commerceWarehouseGroupRel =
			commerceWarehouseGroupRels.get(0);

		return commerceWarehouseLocalService.getCommerceWarehouse(
			commerceWarehouseGroupRel.getCommerceWarehouseId());
	}

	@Override
	public List<CommerceWarehouse> search(
		long groupId, String keywords, Boolean active,
		String commerceCountryCode, int start, int end,
		OrderByComparator<CommerceWarehouse> orderByComparator) {

		return commerceWarehouseFinder.findByKeywords(
			groupId, keywords, active, commerceCountryCode, start, end,
			orderByComparator);
	}

	@Override
	public int searchCount(
		long groupId, String keywords, Boolean active,
		String commerceCountryCode) {

		return commerceWarehouseFinder.countByKeywords(
			groupId, keywords, active, commerceCountryCode);
	}

	@Override
	public CommerceWarehouse setActive(long commerceWarehouseId, boolean active)
		throws PortalException {

		CommerceWarehouse commerceWarehouse =
			commerceWarehousePersistence.findByPrimaryKey(commerceWarehouseId);

		commerceWarehouse.setActive(active);

		commerceWarehousePersistence.update(commerceWarehouse);

		return commerceWarehouse;
	}

	@Override
	public CommerceWarehouse updateCommerceWarehouse(
			long commerceWarehouseId, String name, String description,
			boolean active, String street1, String street2, String street3,
			String city, String zip, String commerceRegionCode,
			String commerceCountryCode, double latitude, double longitude,
			ServiceContext serviceContext)
		throws PortalException {

		validate(name, active, latitude, longitude);

		CommerceWarehouse commerceWarehouse =
			commerceWarehousePersistence.findByPrimaryKey(commerceWarehouseId);

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

		commerceWarehousePersistence.update(commerceWarehouse);

		return commerceWarehouse;
	}

	@Override
	public CommerceWarehouse updateDefaultCommerceWarehouse(
			String name, String street1, String street2, String street3,
			String city, String zip, String commerceRegionCode,
			String commerceCountryCode, double latitude, double longitude,
			ServiceContext serviceContext)
		throws NoSuchWarehouseException {

		CommerceWarehouse defaultCommerceWarehouse =
			getDefaultCommerceWarehouse(serviceContext.getScopeGroupId());

		defaultCommerceWarehouse.setName(name);
		defaultCommerceWarehouse.setStreet1(street1);
		defaultCommerceWarehouse.setStreet2(street2);
		defaultCommerceWarehouse.setStreet3(street3);
		defaultCommerceWarehouse.setCity(city);
		defaultCommerceWarehouse.setZip(zip);
		defaultCommerceWarehouse.setCommerceRegionCode(commerceRegionCode);
		defaultCommerceWarehouse.setCountryTwoLettersISOCode(
			commerceCountryCode);
		defaultCommerceWarehouse.setLatitude(latitude);
		defaultCommerceWarehouse.setLongitude(longitude);

		return commerceWarehousePersistence.update(defaultCommerceWarehouse);
	}

	protected void validate(
			String name, boolean active, double latitude, double longitude)
		throws PortalException {

		if (Validator.isNull(name)) {
			throw new CommerceWarehouseNameException();
		}

		if (active && (latitude == 0) && (longitude == 0)) {
			throw new CommerceWarehouseActiveException();
		}
	}

	private CommerceWarehouse _addDefaultCommerceWarehouse(
			String name, String street1, String street2, String street3,
			String city, String zip, String commerceRegionCode,
			String commerceCountryCode, double latitude, double longitude,
			ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(serviceContext.getUserId());

		validate(name, true, latitude, longitude);

		long commerceWarehouseId = counterLocalService.increment();

		CommerceWarehouse commerceWarehouse =
			commerceWarehousePersistence.create(commerceWarehouseId);

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

		commerceWarehousePersistence.update(commerceWarehouse);

		commerceWarehouseGroupRelLocalService.addCommerceWarehouseGroupRel(
			commerceWarehouseId, true, serviceContext);

		return commerceWarehouse;
	}

}