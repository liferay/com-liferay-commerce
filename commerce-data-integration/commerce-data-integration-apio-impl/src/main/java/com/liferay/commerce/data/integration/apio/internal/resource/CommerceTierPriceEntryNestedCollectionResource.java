/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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
import com.liferay.commerce.data.integration.apio.identifiers.CommerceTierPriceEntryIdentifier;
import com.liferay.commerce.data.integration.apio.internal.exceptions.ConflictException;
import com.liferay.commerce.data.integration.apio.internal.form.CommerceTierPriceEntryUpdaterForm;
import com.liferay.commerce.data.integration.apio.internal.form.CommerceTierPriceEntryUpserterForm;
import com.liferay.commerce.data.integration.apio.internal.util.CommercePriceEntryHelper;
import com.liferay.commerce.data.integration.apio.internal.util.CommerceTierPriceEntryHelper;
import com.liferay.commerce.price.list.exception.DuplicateCommerceTierPriceEntryException;
import com.liferay.commerce.price.list.exception.NoSuchPriceEntryException;
import com.liferay.commerce.price.list.model.CommercePriceEntry;
import com.liferay.commerce.price.list.model.CommerceTierPriceEntry;
import com.liferay.commerce.price.list.service.CommerceTierPriceEntryService;
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
 * Provides the information necessary to expose {@link CommerceTierPriceEntry}
 * resources through a web API.
 *
 * @author Zoltán Takács
 */
@Component(immediate = true)
public class CommerceTierPriceEntryNestedCollectionResource
	implements NestedCollectionResource
		<CommerceTierPriceEntry, Long, CommerceTierPriceEntryIdentifier,
		 ClassPKExternalReferenceCode, CommercePriceEntryIdentifier> {

	@Override
	public NestedCollectionRoutes
		<CommerceTierPriceEntry, Long, ClassPKExternalReferenceCode>
			collectionRoutes(
				NestedCollectionRoutes.Builder
					<CommerceTierPriceEntry, Long, ClassPKExternalReferenceCode>
						builder) {

		return builder.addGetter(
			this::_getPageItems
		).addCreator(
			this::_upsertCommerceTierPriceEntry,
			_hasPermission.forAddingIn(CommercePriceEntryIdentifier.class),
			CommerceTierPriceEntryUpserterForm::buildForm
		).build();
	}

	@Override
	public String getName() {
		return "commerce-tier-price-entry";
	}

	@Override
	public ItemRoutes<CommerceTierPriceEntry, Long> itemRoutes(
		ItemRoutes.Builder<CommerceTierPriceEntry, Long> builder) {

		return builder.addGetter(
			_commerceTierPriceEntryHelper::getCommerceTierPriceEntry
		).addUpdater(
			this::_updateCommercePriceEntry, (credentials, s) -> true,
			CommerceTierPriceEntryUpdaterForm::buildForm
		).addRemover(
			idempotent(
				_commerceTierPriceEntryService::deleteCommerceTierPriceEntry),
			(credentials, s) -> true
		).build();
	}

	@Override
	public Representor<CommerceTierPriceEntry> representor(
		Representor.Builder<CommerceTierPriceEntry, Long> builder) {

		return builder.types(
			"CommerceTierPriceEntry"
		).identifier(
			CommerceTierPriceEntry::getCommerceTierPriceEntryId
		).addBidirectionalModel(
			"commercePriceEntry", "commerceTierPriceEntries",
			CommercePriceEntryIdentifier.class,
			commercePriceTier -> _commercePriceEntryHelper.
				commercePriceEntryIdToClassPKExternalReferenceCode(
					commercePriceTier.getCommercePriceEntryId())
		).addBidirectionalModel(
			"webSite", "commerceTierPriceEntries", WebSiteIdentifier.class,
			CommerceTierPriceEntry::getGroupId
		).addDate(
			"dateCreated", CommerceTierPriceEntry::getCreateDate
		).addDate(
			"dateModified", CommerceTierPriceEntry::getModifiedDate
		).addNumber(
			"id", CommerceTierPriceEntry::getCommerceTierPriceEntryId
		).addNumber(
			"minQuantity", CommerceTierPriceEntry::getMinQuantity
		).addNumber(
			"price", CommerceTierPriceEntry::getPrice
		).addNumber(
			"promoPrice", CommerceTierPriceEntry::getPromoPrice
		).addString(
			"externalReferenceCode",
			CommerceTierPriceEntry::getExternalReferenceCode
		).build();
	}

	private PageItems<CommerceTierPriceEntry> _getPageItems(
			Pagination pagination,
			ClassPKExternalReferenceCode
				commercePriceEntryClassPKExternalReferenceCode)
		throws PortalException {

		CommercePriceEntry commercePriceEntry =
			_commercePriceEntryHelper.
				getCommercePriceEntryByClassPKExternalReferenceCode(
					commercePriceEntryClassPKExternalReferenceCode);

		long commercePriceEntryId =
			commercePriceEntry.getCommercePriceEntryId();

		List<CommerceTierPriceEntry> commerceTierPriceEntries =
			_commerceTierPriceEntryService.getCommerceTierPriceEntries(
				commercePriceEntryId, pagination.getStartPosition(),
				pagination.getEndPosition());

		if (_log.isDebugEnabled()) {
			if (ListUtil.isEmpty(commerceTierPriceEntries)) {
				_log.debug(
					"Unable to find Tier Price Entries in Price Entry with " +
						"ID " + commercePriceEntryId);
			}
		}

		int count =
			_commerceTierPriceEntryService.getCommerceTierPriceEntriesCount(
				commercePriceEntryId);

		return new PageItems<>(commerceTierPriceEntries, count);
	}

	private CommerceTierPriceEntry _updateCommercePriceEntry(
			Long commerceTierPriceEntryId,
			CommerceTierPriceEntryUpdaterForm commerceTierPriceEntryUpdaterForm)
		throws PortalException {

		try {
			return _commerceTierPriceEntryHelper.updateCommerceTierPriceEntry(
				commerceTierPriceEntryId,
				commerceTierPriceEntryUpdaterForm.getMinQuantity(),
				commerceTierPriceEntryUpdaterForm.getPrice(),
				commerceTierPriceEntryUpdaterForm.getPromoPrice());
		}
		catch (DuplicateCommerceTierPriceEntryException dctpee) {
			throw new ConflictException(
				"Minimum quantity already exists for this price entry: " +
					commerceTierPriceEntryUpdaterForm.getMinQuantity(),
				Response.Status.CONFLICT.getStatusCode(), dctpee);
		}
	}

	private CommerceTierPriceEntry _upsertCommerceTierPriceEntry(
			ClassPKExternalReferenceCode
				commercePriceEntryClassPKExternalReferenceCode,
			CommerceTierPriceEntryUpserterForm
				commerceTierPriceEntryUpserterForm)
		throws PortalException {

		try {
			CommercePriceEntry commercePriceEntry =
				_commercePriceEntryHelper.
					getCommercePriceEntryByClassPKExternalReferenceCode(
						commercePriceEntryClassPKExternalReferenceCode);

			return _commerceTierPriceEntryHelper.upsertCommerceTierPriceEntry(
				commerceTierPriceEntryUpserterForm.
					getCommerceTierPriceEntryId(),
				commercePriceEntry.getCommercePriceEntryId(),
				commerceTierPriceEntryUpserterForm.getMinQuantity(),
				commerceTierPriceEntryUpserterForm.getPrice(),
				commerceTierPriceEntryUpserterForm.getPromoPrice(),
				commerceTierPriceEntryUpserterForm.getExternalReferenceCode(),
				commerceTierPriceEntryUpserterForm.
					getPriceEntryExternalReferenceCode());
		}
		catch (NoSuchPriceEntryException nspee) {
			throw new NotFoundException(
				"Unable to find price entry: " + nspee.getLocalizedMessage(),
				nspee);
		}
		catch (DuplicateCommerceTierPriceEntryException dctpee) {
			throw new ConflictException(
				"Minimum quantity already exists for this price entry: " +
					commerceTierPriceEntryUpserterForm.getMinQuantity(),
				Response.Status.CONFLICT.getStatusCode(), dctpee);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceTierPriceEntryNestedCollectionResource.class);

	@Reference
	private CommercePriceEntryHelper _commercePriceEntryHelper;

	@Reference
	private CommerceTierPriceEntryHelper _commerceTierPriceEntryHelper;

	@Reference
	private CommerceTierPriceEntryService _commerceTierPriceEntryService;

	@Reference(
		target = "(model.class.name=com.liferay.commerce.price.list.model.CommerceTierPriceEntry)"
	)
	private HasPermission<ClassPKExternalReferenceCode> _hasPermission;

}