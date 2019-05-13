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

package com.liferay.commerce.inventory.service.persistence;

import aQute.bnd.annotation.ProviderType;

/**
 * @author Luca Pellizzon
 * @generated
 */
@ProviderType
public interface CommerceInventoryWarehouseFinder {
	public int countByKeywords(long companyId, long groupId, String keywords,
		Boolean active, String commerceCountryCode);

	public int countByG_A(long companyId, long groupId, Boolean active);

	public int countByG_A_C(long companyId, long groupId, Boolean active,
		String commerceCountryCode);

	public int countByG_N_D_S_C_Z_C(long companyId, long groupId,
		String[] names, String[] descriptions, String[] streets,
		String[] cities, String[] zips, Boolean active,
		String commerceCountryCode, boolean andOperator);

	public java.util.List<com.liferay.commerce.inventory.model.CommerceInventoryWarehouse> findByKeywords(
		long companyId, long groupId, String keywords, Boolean active,
		String commerceCountryCode, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.inventory.model.CommerceInventoryWarehouse> orderByComparator);

	public java.util.List<com.liferay.commerce.inventory.model.CommerceInventoryWarehouse> findByG_N_D_S_C_Z_C(
		long companyId, long groupId, String[] names, String[] descriptions,
		String[] streets, String[] cities, String[] zips, Boolean active,
		String commerceCountryCode, boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.inventory.model.CommerceInventoryWarehouse> orderByComparator);

	public java.util.List<com.liferay.commerce.inventory.model.CommerceInventoryWarehouse> findWarehousesByGroupId(
		long companyId, long groupId);

	public java.util.List<com.liferay.commerce.inventory.model.CommerceInventoryWarehouse> findWarehousesByGroupIdAndActive(
		long companyId, long groupId, boolean active);

	public java.util.List<com.liferay.commerce.inventory.model.CommerceInventoryWarehouse> findWarehousesByGroupIdAndActiveAndCountryISOCode(
		long companyId, long groupId, boolean active, String countryCode);

	public java.util.List<com.liferay.commerce.inventory.model.CommerceInventoryWarehouse> findWarehousesByGroupIdAndSku(
		long companyId, long groupId, String sku);
}