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

package com.liferay.commerce.data.integration.apio.internal.resource;

import com.liferay.apio.architect.pagination.PageItems;
import com.liferay.apio.architect.pagination.Pagination;
import com.liferay.apio.architect.representor.Representor;
import com.liferay.apio.architect.resource.NestedCollectionResource;
import com.liferay.apio.architect.routes.ItemRoutes;
import com.liferay.apio.architect.routes.NestedCollectionRoutes;
import com.liferay.commerce.data.integration.apio.identifier.CPInstanceIdentifier;
import com.liferay.commerce.data.integration.apio.identifier.ClassPKExternalReferenceCode;
import com.liferay.commerce.data.integration.apio.identifier.CommerceOrderIdentifier;
import com.liferay.commerce.data.integration.apio.identifier.CommerceOrderItemIdentifier;
import com.liferay.commerce.data.integration.apio.internal.util.CPInstanceHelper;
import com.liferay.commerce.data.integration.apio.internal.util.CommerceOrderHelper;
import com.liferay.commerce.data.integration.apio.internal.util.CommerceOrderItemHelper;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.service.CommerceOrderItemService;
import com.liferay.person.apio.architect.identifier.PersonIdentifier;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rodrigo Guedes de Souza
 */
@Component(immediate = true, service = NestedCollectionResource.class)
public class CommerceOrderItemNestedCollectionResource
	implements NestedCollectionResource
		<CommerceOrderItem, ClassPKExternalReferenceCode,
		 CommerceOrderItemIdentifier, ClassPKExternalReferenceCode,
		 CommerceOrderIdentifier> {

	@Override
	public NestedCollectionRoutes
		<CommerceOrderItem, ClassPKExternalReferenceCode,
		 ClassPKExternalReferenceCode>
			collectionRoutes(
				NestedCollectionRoutes.Builder
					<CommerceOrderItem, ClassPKExternalReferenceCode,
					 ClassPKExternalReferenceCode>
						builder) {

		return builder.addGetter(
			this::_getPageItems
		).build();
	}

	@Override
	public String getName() {
		return "commerce-order-item";
	}

	@Override
	public ItemRoutes<CommerceOrderItem, ClassPKExternalReferenceCode>
		itemRoutes(ItemRoutes.Builder
			<CommerceOrderItem, ClassPKExternalReferenceCode>
				builder) {

		return builder.addGetter(
			this::_getCommerceOrderItem
		).build();
	}

	@Override
	public Representor<CommerceOrderItem> representor(
		Representor.Builder<CommerceOrderItem, ClassPKExternalReferenceCode>
			builder) {

		return builder.types(
			"CommerceOrderItem"
		).identifier(
			_commerceOrderItemHelper::
				commerceOrderItemToClassPKExternalReferenceCode
		).addBidirectionalModel(
			"commerceOrder", "commerceOrderItems",
			CommerceOrderIdentifier.class,
			commerceOrderItem -> _commerceOrderHelper.
				commerceOrderIdToClassPKExternalReferenceCode(
					commerceOrderItem.getCommerceOrderId())
		).addLinkedModel(
			"cpInstance", CPInstanceIdentifier.class,
			commerceOrderItem -> _cpInstanceHelper.
				cpInstanceIdToClassPKExternalReferenceCode(
					commerceOrderItem.getCPInstanceId())
		).addNumber(
			"id", CommerceOrderItem::getCommerceOrderItemId
		).addString(
			"sku", CommerceOrderItem::getSku
		).addLocalizedStringByLocale(
			"name", CommerceOrderItem::getName
		).addNumber(
			"quantity", CommerceOrderItem::getQuantity
		).addNumber(
			"shippedQuantity", CommerceOrderItem::getShippedQuantity
		).addNumber(
			"unitPrice", CommerceOrderItem::getUnitPrice
		).addNumber(
			"finalPrice", CommerceOrderItem::getFinalPrice
		).addNumber(
			"discountAmount", CommerceOrderItem::getDiscountAmount
		).addNumber(
			"discountPercentageLevel1",
			CommerceOrderItem::getDiscountPercentageLevel1
		).addNumber(
			"discountPercentageLevel2",
			CommerceOrderItem::getDiscountPercentageLevel2
		).addNumber(
			"discountPercentageLevel3",
			CommerceOrderItem::getDiscountPercentageLevel3
		).addNumber(
			"discountPercentageLevel4",
			CommerceOrderItem::getDiscountPercentageLevel4
		).addDate(
			"dateCreated", CommerceOrderItem::getCreateDate
		).addDate(
			"dateModified", CommerceOrderItem::getModifiedDate
		).addLinkedModel(
			"author", PersonIdentifier.class, CommerceOrderItem::getUserId
		).addNumber(
			"commerceOrderId", CommerceOrderItem::getCommerceOrderId
		).addString(
			"skuExternalReferenceCode", this::_getSkuExternalReferenceCode
		).build();
	}

	private CommerceOrderItem _getCommerceOrderItem(
			ClassPKExternalReferenceCode
				commerceOrderItemClassPKExternalReferenceCode)
		throws PortalException {

		return _commerceOrderItemHelper.
			getCommerceOrderItemByClassPKExternalReferenceCode(
				commerceOrderItemClassPKExternalReferenceCode);
	}

	private PageItems<CommerceOrderItem> _getPageItems(
			Pagination pagination,
			ClassPKExternalReferenceCode
				commerceOrderClassPKExternalReferenceCode)
		throws PortalException {

		CommerceOrder commerceOrder =
			_commerceOrderHelper.getCommerceOrderByClassPKExternalReferenceCode(
				commerceOrderClassPKExternalReferenceCode);

		List<CommerceOrderItem> commerceOrderItems =
			_commerceOrderItemService.getCommerceOrderItems(
				commerceOrder.getCommerceOrderId(),
				pagination.getStartPosition(), pagination.getEndPosition());

		int total = _commerceOrderItemService.getCommerceOrderItemsCount(
			commerceOrder.getCommerceOrderId());

		return new PageItems<>(commerceOrderItems, total);
	}

	private String _getSkuExternalReferenceCode(
		CommerceOrderItem commerceOrderItem) {

		if (commerceOrderItem != null) {
			try {
				CPInstance cpInstance = commerceOrderItem.getCPInstance();

				return cpInstance.getExternalReferenceCode();
			}
			catch (PortalException pe) {
				_log.error(
					"Unable to find product sku with ID " +
						commerceOrderItem.getCPInstanceId(),
					pe);
			}
		}

		return null;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceOrderItemNestedCollectionResource.class);

	@Reference
	private CommerceOrderHelper _commerceOrderHelper;

	@Reference
	private CommerceOrderItemHelper _commerceOrderItemHelper;

	@Reference
	private CommerceOrderItemService _commerceOrderItemService;

	@Reference
	private CPInstanceHelper _cpInstanceHelper;

}