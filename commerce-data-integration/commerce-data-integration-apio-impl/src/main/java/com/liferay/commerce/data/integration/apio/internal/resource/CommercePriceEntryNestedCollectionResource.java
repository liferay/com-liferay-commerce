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

import static com.liferay.portal.apio.idempotent.Idempotent.idempotent;

import com.liferay.apio.architect.pagination.PageItems;
import com.liferay.apio.architect.pagination.Pagination;
import com.liferay.apio.architect.representor.Representor;
import com.liferay.apio.architect.resource.NestedCollectionResource;
import com.liferay.apio.architect.routes.ItemRoutes;
import com.liferay.apio.architect.routes.NestedCollectionRoutes;
import com.liferay.commerce.data.integration.apio.identifiers.ClassPKExternalReferenceCode;
import com.liferay.commerce.data.integration.apio.identifiers.CommercePriceEntryIdentifier;
import com.liferay.commerce.data.integration.apio.identifiers.CommercePriceListIdentifier;
import com.liferay.commerce.data.integration.apio.internal.exceptions.ConflictException;
import com.liferay.commerce.data.integration.apio.internal.form.CommercePriceEntryUpdaterForm;
import com.liferay.commerce.data.integration.apio.internal.form.CommercePriceEntryUpserterForm;
import com.liferay.commerce.data.integration.apio.internal.util.CommercePriceEntryHelper;
import com.liferay.commerce.data.integration.apio.internal.util.CommercePriceListHelper;
import com.liferay.commerce.price.list.exception.DuplicateCommercePriceEntryException;
import com.liferay.commerce.price.list.model.CommercePriceEntry;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.service.CommercePriceEntryService;
import com.liferay.commerce.product.exception.NoSuchCPInstanceException;
import com.liferay.portal.apio.permission.HasPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.site.apio.architect.identifier.WebSiteIdentifier;

import java.util.List;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the information necessary to expose {@link CommercePriceEntry}
 * resources through a web API.
 *
 * @author Zoltán Takács
 */
@Component(immediate = true)
public class CommercePriceEntryNestedCollectionResource
	implements NestedCollectionResource
		<CommercePriceEntry, ClassPKExternalReferenceCode,
		 CommercePriceEntryIdentifier, ClassPKExternalReferenceCode,
		 CommercePriceListIdentifier> {

	@Override
	public NestedCollectionRoutes
		<CommercePriceEntry, ClassPKExternalReferenceCode,
		 ClassPKExternalReferenceCode>
			collectionRoutes(
				NestedCollectionRoutes.Builder
					<CommercePriceEntry, ClassPKExternalReferenceCode,
					 ClassPKExternalReferenceCode>
						builder) {

		return builder.addGetter(
			this::_getPageItems
		).addCreator(
			this::_upsertCommercePriceEntry,
			_hasPermission.forAddingIn(CommercePriceListIdentifier.class),
			CommercePriceEntryUpserterForm::buildForm
		).build();
	}

	@Override
	public String getName() {
		return "commerce-price-entry";
	}

	@Override
	public ItemRoutes
		<CommercePriceEntry, ClassPKExternalReferenceCode> itemRoutes(
			ItemRoutes.Builder<CommercePriceEntry, ClassPKExternalReferenceCode>
				builder) {

		return builder.addGetter(
			_commercePriceEntryHelper::
				getCommercePriceEntryByClassPKExternalReferenceCode
		).addUpdater(
			this::_updateCommercePriceEntry, _hasPermission::forUpdating,
			CommercePriceEntryUpdaterForm::buildForm
		).addRemover(
			idempotent(_commercePriceEntryHelper::deletePriceEntry),
			_hasPermission::forDeleting
		).build();
	}

	@Override
	public Representor<CommercePriceEntry> representor(
		Representor.Builder<CommercePriceEntry, ClassPKExternalReferenceCode>
			builder) {

		return builder.types(
			"CommercePriceEntry"
		).identifier(
			_commercePriceEntryHelper::
				commercePriceEntryToClassPKExternalReferenceCode
		).addBidirectionalModel(
			"commercePriceList", "commercePriceEntries",
			CommercePriceListIdentifier.class,
			commercePriceEntry -> _commercePriceListHelper.
				commercePriceListIdToClassPKExternalReferenceCode(
					commercePriceEntry.getCommercePriceListId())
		).addBidirectionalModel(
			"webSite", "commercePriceEntries", WebSiteIdentifier.class,
			CommercePriceEntry::getGroupId
		).addBoolean(
			"hasTierPrice", CommercePriceEntry::isHasTierPrice
		).addDate(
			"dateCreated", CommercePriceEntry::getCreateDate
		).addDate(
			"dateModified", CommercePriceEntry::getModifiedDate
		).addNumber(
			"id", CommercePriceEntry::getCommercePriceEntryId
		).addNumber(
			"price", CommercePriceEntry::getPrice
		).addNumber(
			"promoPrice", CommercePriceEntry::getPromoPrice
		).addString(
			"externalReferenceCode",
			CommercePriceEntry::getExternalReferenceCode
		).addString(
			"sku", CommercePriceEntryHelper::getSKU
		).addString(
			"cpInstanceExternalReferenceCode",
			CommercePriceEntryHelper::getSKUExternalReferenceCode
		).build();
	}

	private PageItems<CommercePriceEntry> _getPageItems(
			Pagination pagination,
			ClassPKExternalReferenceCode
				commercePriceListClassExternalReferenceCode)
		throws PortalException {

		CommercePriceList commercePriceList =
			_commercePriceListHelper.
				getCommercePriceListByClassPKExternalReferenceCode(
					commercePriceListClassExternalReferenceCode);

		List<CommercePriceEntry> commercePriceEntries =
			_commercePriceEntryService.getCommercePriceEntries(
				commercePriceList.getCommercePriceListId(),
				pagination.getStartPosition(), pagination.getEndPosition());

		if (_log.isDebugEnabled()) {
			if (ListUtil.isEmpty(commercePriceEntries)) {
				_log.debug(
					"Unable to find Price Entries in Price List with ID " +
						commercePriceListClassExternalReferenceCode);
			}
		}

		int count = _commercePriceEntryService.getCommercePriceEntriesCount(
			commercePriceList.getCommercePriceListId());

		return new PageItems<>(commercePriceEntries, count);
	}

	private CommercePriceEntry _updateCommercePriceEntry(
			ClassPKExternalReferenceCode classPKExternalReferenceCode,
			CommercePriceEntryUpdaterForm commercePriceEntryUpdaterForm)
		throws PortalException {

		return _commercePriceEntryHelper.updateCommercePriceEntry(
			classPKExternalReferenceCode,
			commercePriceEntryUpdaterForm.getPrice(),
			commercePriceEntryUpdaterForm.getPromoPrice());
	}

	private CommercePriceEntry _upsertCommercePriceEntry(
			ClassPKExternalReferenceCode
				commercePriceListClassPKExternalReferenceCode,
			CommercePriceEntryUpserterForm commercePriceEntryUpserterForm)
		throws PortalException {

		try {
			CommercePriceList commercePriceList =
				_commercePriceListHelper.
					getCommercePriceListByClassPKExternalReferenceCode(
						commercePriceListClassPKExternalReferenceCode);

			return _commercePriceEntryHelper.upsertCommercePriceEntry(
				commercePriceEntryUpserterForm.getCommercePriceEntryId(),
				commercePriceEntryUpserterForm.getCommerceProductInstanceId(),
				commercePriceList.getCommercePriceListId(),
				commercePriceEntryUpserterForm.getExternalReferenceCode(),
				commercePriceEntryUpserterForm.getSkuExternalReferenceCode(),
				commercePriceEntryUpserterForm.getPrice(),
				commercePriceEntryUpserterForm.getPromoPrice(),
				commercePriceEntryUpserterForm.getStandardPrice());
		}
		catch (NoSuchCPInstanceException nscpie) {
			throw new NotFoundException(
				"Unable to find SKU: " + nscpie.getLocalizedMessage(), nscpie);
		}
		catch (DuplicateCommercePriceEntryException dcpee) {
			throw new ConflictException(
				"Duplicate Product Instance on the Price List ID",
				Response.Status.CONFLICT.getStatusCode(), dcpee);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommercePriceEntryNestedCollectionResource.class);

	@Reference
	private CommercePriceEntryHelper _commercePriceEntryHelper;

	@Reference
	private CommercePriceEntryService _commercePriceEntryService;

	@Reference
	private CommercePriceListHelper _commercePriceListHelper;

	@Reference(
		target = "(model.class.name=com.liferay.commerce.price.list.model.CommercePriceEntry)"
	)
	private HasPermission<ClassPKExternalReferenceCode> _hasPermission;

}