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

package com.liferay.commerce.util;

import com.liferay.commerce.inventory.model.CommerceInventoryWarehouse;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItem;
import com.liferay.commerce.inventory.util.comparator.CommerceInventoryWarehouseCityComparator;
import com.liferay.commerce.inventory.util.comparator.CommerceInventoryWarehouseItemQuantityComparator;
import com.liferay.commerce.inventory.util.comparator.CommerceInventoryWarehouseItemWarehouseNameComparator;
import com.liferay.commerce.inventory.util.comparator.CommerceInventoryWarehouseNameComparator;
import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.model.CommerceAddressRestriction;
import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.model.CommerceRegion;
import com.liferay.commerce.model.CommerceShipment;
import com.liferay.commerce.model.CommerceShipmentItem;
import com.liferay.commerce.util.comparator.CommerceAddressCreateDateComparator;
import com.liferay.commerce.util.comparator.CommerceAddressRestrictionCreateDateComparator;
import com.liferay.commerce.util.comparator.CommerceCountryNameComparator;
import com.liferay.commerce.util.comparator.CommerceCountryPriorityComparator;
import com.liferay.commerce.util.comparator.CommerceRegionNameComparator;
import com.liferay.commerce.util.comparator.CommerceRegionPriorityComparator;
import com.liferay.commerce.util.comparator.CommerceShipmentCreateDateComparator;
import com.liferay.commerce.util.comparator.CommerceShipmentExpectedDateComparator;
import com.liferay.commerce.util.comparator.CommerceShipmentIdComparator;
import com.liferay.commerce.util.comparator.CommerceShipmentItemCreateDateComparator;
import com.liferay.commerce.util.comparator.CommerceShipmentShippingDateComparator;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Andrea Di Giorgi
 * @author Alessio Antonio Rendina
 * @author Luca Pellizzon
 */
public class CommerceUtil {

	public static OrderByComparator<CommerceAddress>
		getCommerceAddressOrderByComparator(
			String orderByCol, String orderByType) {

		boolean orderByAsc = false;

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}

		OrderByComparator<CommerceAddress> orderByComparator = null;

		if (orderByCol.equals("create-date")) {
			orderByComparator = new CommerceAddressCreateDateComparator(
				orderByAsc);
		}

		return orderByComparator;
	}

	public static OrderByComparator<CommerceAddressRestriction>
		getCommerceAddressRestrictionOrderByComparator(
			String orderByCol, String orderByType) {

		boolean orderByAsc = false;

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}

		OrderByComparator<CommerceAddressRestriction> orderByComparator = null;

		if (orderByCol.equals("create-date")) {
			orderByComparator =
				new CommerceAddressRestrictionCreateDateComparator(orderByAsc);
		}

		return orderByComparator;
	}

	public static Sort getCommerceAddressSort(
		String orderByCol, String orderByType) {

		boolean reverse = true;

		if (orderByType.equals("asc")) {
			reverse = false;
		}

		Sort sort = null;

		if (orderByCol.equals("create-date")) {
			sort = SortFactoryUtil.create(Field.CREATE_DATE, reverse);
		}

		return sort;
	}

	public static OrderByComparator<CommerceCountry>
		getCommerceCountryOrderByComparator(
			String orderByCol, String orderByType) {

		boolean orderByAsc = false;

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}

		OrderByComparator<CommerceCountry> orderByComparator = null;

		if (orderByCol.equals("name")) {
			orderByComparator = new CommerceCountryNameComparator(orderByAsc);
		}
		else if (orderByCol.equals("priority")) {
			orderByComparator = new CommerceCountryPriorityComparator(
				orderByAsc);
		}

		return orderByComparator;
	}

	public static Sort getCommerceCountrySort(
		String orderByCol, String orderByType) {

		boolean reverse = true;

		if (orderByType.equals("asc")) {
			reverse = false;
		}

		Sort sort = null;

		if (orderByCol.equals("name")) {
			sort = SortFactoryUtil.create(
				Field.NAME, Sort.STRING_TYPE, reverse);
		}
		else if (orderByCol.equals("priority")) {
			sort = SortFactoryUtil.create(
				Field.PRIORITY + "_Number_sortable", reverse);
		}

		return sort;
	}

	public static OrderByComparator<CommerceInventoryWarehouseItem>
		getCommerceInventoryWarehouseItemOrderByComparator(
			String orderByCol, String orderByType) {

		boolean orderByAsc = false;

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}

		OrderByComparator<CommerceInventoryWarehouseItem> orderByComparator =
			null;

		if (orderByCol.equals("name")) {
			orderByComparator =
				new CommerceInventoryWarehouseItemWarehouseNameComparator(
					orderByAsc);
		}
		else if (orderByCol.equals("quantity")) {
			orderByComparator =
				new CommerceInventoryWarehouseItemQuantityComparator(
					orderByAsc);
		}

		return orderByComparator;
	}

	public static OrderByComparator<CommerceInventoryWarehouse>
		getCommerceInventoryWarehouseOrderByComparator(
			String orderByCol, String orderByType) {

		boolean orderByAsc = false;

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}

		OrderByComparator<CommerceInventoryWarehouse> orderByComparator = null;

		if (orderByCol.equals("city")) {
			orderByComparator = new CommerceInventoryWarehouseCityComparator(
				orderByAsc);
		}
		else if (orderByCol.equals("name")) {
			orderByComparator = new CommerceInventoryWarehouseNameComparator(
				orderByAsc);
		}

		return orderByComparator;
	}

	public static Sort getCommerceInventoryWarehouseSort(
		String orderByCol, String orderByType) {

		boolean reverse = true;

		if (orderByType.equals("asc")) {
			reverse = false;
		}

		Sort sort = null;

		if (orderByCol.equals("city")) {
			sort = SortFactoryUtil.create("city", Sort.STRING_TYPE, reverse);
		}
		else if (orderByCol.equals("name")) {
			sort = SortFactoryUtil.create(
				Field.NAME, Sort.STRING_TYPE, reverse);
		}

		return sort;
	}

	public static Sort[] getCommerceOrderSorts(
		String orderByCol, String orderByType) {

		if (Validator.isNull(orderByCol) || Validator.isNull(orderByType)) {
			return SortFactoryUtil.getDefaultSorts();
		}

		boolean reverse = true;

		if (orderByType.equals("asc")) {
			reverse = false;
		}

		if (orderByCol.equals("create-date")) {
			return new Sort[] {
				SortFactoryUtil.create(
					Field.CREATE_DATE + "_sortable", reverse),
				SortFactoryUtil.create(null, Sort.SCORE_TYPE, false)
			};
		}
		else if (orderByCol.equals("order-id")) {
			return new Sort[] {
				SortFactoryUtil.create(
					Field.ENTRY_CLASS_PK + "_Number_sortable", reverse),
				SortFactoryUtil.create(null, Sort.SCORE_TYPE, false)
			};
		}
		else if (orderByCol.equals("total")) {
			return new Sort[] {
				SortFactoryUtil.create("total_Number_sortable", reverse),
				SortFactoryUtil.create(null, Sort.SCORE_TYPE, false)
			};
		}

		return SortFactoryUtil.getDefaultSorts();
	}

	public static OrderByComparator<CommerceRegion>
		getCommerceRegionOrderByComparator(
			String orderByCol, String orderByType) {

		boolean orderByAsc = false;

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}

		OrderByComparator<CommerceRegion> orderByComparator = null;

		if (orderByCol.equals("name")) {
			orderByComparator = new CommerceRegionNameComparator(orderByAsc);
		}
		else if (orderByCol.equals("priority")) {
			orderByComparator = new CommerceRegionPriorityComparator(
				orderByAsc);
		}

		return orderByComparator;
	}

	public static OrderByComparator<CommerceShipmentItem>
		getCommerceShipmentItemOrderByComparator(
			String orderByCol, String orderByType) {

		boolean orderByAsc = false;

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}

		OrderByComparator<CommerceShipmentItem> orderByComparator = null;

		if (orderByCol.equals("create-date")) {
			orderByComparator = new CommerceShipmentItemCreateDateComparator(
				orderByAsc);
		}

		return orderByComparator;
	}

	public static OrderByComparator<CommerceShipment>
		getCommerceShipmentOrderByComparator(
			String orderByCol, String orderByType) {

		boolean orderByAsc = false;

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}

		OrderByComparator<CommerceShipment> orderByComparator = null;

		if (orderByCol.equals("create-date")) {
			orderByComparator = new CommerceShipmentCreateDateComparator(
				orderByAsc);
		}
		else if (orderByCol.equals("expected-delivery-date")) {
			orderByComparator = new CommerceShipmentExpectedDateComparator(
				orderByAsc);
		}
		else if (orderByCol.equals("shipment-number")) {
			orderByComparator = new CommerceShipmentIdComparator(orderByAsc);
		}
		else if (orderByCol.equals("shipping-date")) {
			orderByComparator = new CommerceShipmentShippingDateComparator(
				orderByAsc);
		}

		return orderByComparator;
	}

}