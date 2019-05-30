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

package com.liferay.commerce.initializer.util;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.commerce.discount.model.CommerceDiscount;
import com.liferay.commerce.discount.model.CommerceDiscountConstants;
import com.liferay.commerce.discount.service.CommerceDiscountLocalService;
import com.liferay.commerce.discount.service.CommerceDiscountRelLocalService;
import com.liferay.commerce.product.service.CommerceCatalogLocalService;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;

import java.math.BigDecimal;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Steven Smith
 */
@Component(service = CommerceDiscountsImporter.class)
public class CommerceDiscountsImporter {

	public void importCommerceDiscounts(
			long catalogGroupId, JSONArray jsonArray, long scopeGroupId,
			long userId)
		throws Exception {

		User user = _userLocalService.getUser(userId);

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setScopeGroupId(scopeGroupId);
		serviceContext.setUserId(userId);
		serviceContext.setCompanyId(user.getCompanyId());

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			_importCommerceDiscount(catalogGroupId, jsonObject, serviceContext);
		}
	}

	private CommerceDiscount _addCommerceDiscount(
			long catalogGroupId, JSONObject jsonObject,
			ServiceContext serviceContext)
		throws PortalException {

		// Add Commerce Discount

		String title = jsonObject.getString("title");

		boolean useCouponCode = jsonObject.getBoolean("useCouponCode");
		String couponCode = jsonObject.getString("couponCode");
		boolean usePercentage = jsonObject.getBoolean("usePercentage");
		BigDecimal maximumDiscountAmount = BigDecimal.valueOf(
			jsonObject.getDouble("maximumDiscountAmount"));
		BigDecimal level1 = BigDecimal.valueOf(jsonObject.getDouble("level1"));

		boolean active = jsonObject.getBoolean("active");

		return _commerceDiscountLocalService.addCommerceDiscount(
			catalogGroupId, serviceContext.getUserId(), title,
			CommerceDiscountConstants.TARGET_CATEGORIES, useCouponCode,
			couponCode, usePercentage, maximumDiscountAmount, level1,
			BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
			CommerceDiscountConstants.LIMITATION_TYPE_UNLIMITED, 0, active, 1,
			1, 2019, -1, -1, 0, 0, 0, 0, 0, true, serviceContext);
	}

	private void _importCommerceDiscount(
			long catalogGroupId, JSONObject jsonObject,
			ServiceContext serviceContext)
		throws PortalException {

		CommerceDiscount commerceDiscount = _addCommerceDiscount(
			catalogGroupId, jsonObject, serviceContext);

		JSONArray jsonArray = jsonObject.getJSONArray("categories");

		if (jsonArray.length() > 0) {
			DynamicQuery dynamicQuery =
				_assetCategoryLocalService.dynamicQuery();

			Criterion criterion = RestrictionsFactoryUtil.eq(
				"companyId", serviceContext.getCompanyId());

			List<AssetCategory> assetCategories =
				_assetCategoryLocalService.dynamicQuery(
					dynamicQuery.add(criterion));

			for (int i = 0; i < jsonArray.length(); i++) {
				String category = jsonArray.getString(i);

				for (AssetCategory assetCategory : assetCategories) {
					String name = assetCategory.getName();

					if (name.contains(category)) {
						_commerceDiscountRelLocalService.addCommerceDiscountRel(
							commerceDiscount.getCommerceDiscountId(),
							AssetCategory.class.getName(),
							assetCategory.getPrimaryKey(), serviceContext);

						break;
					}
				}
			}
		}
	}

	@Reference
	private AssetCategoryLocalService _assetCategoryLocalService;

	@Reference
	private CommerceCatalogLocalService _commerceCatalogLocalService;

	@Reference
	private CommerceDiscountLocalService _commerceDiscountLocalService;

	@Reference
	private CommerceDiscountRelLocalService _commerceDiscountRelLocalService;

	@Reference
	private UserLocalService _userLocalService;

}