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
import com.liferay.commerce.data.integration.apio.identifier.ClassPKExternalReferenceCode;
import com.liferay.commerce.data.integration.apio.identifier.CommerceOrderIdentifier;
import com.liferay.commerce.data.integration.apio.identifier.CommerceOrderNoteIdentifier;
import com.liferay.commerce.data.integration.apio.internal.form.CommerceOrderNoteUpserterForm;
import com.liferay.commerce.data.integration.apio.internal.util.CommerceOrderHelper;
import com.liferay.commerce.data.integration.apio.internal.util.CommerceOrderNoteHelper;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderNote;
import com.liferay.commerce.service.CommerceOrderNoteService;
import com.liferay.person.apio.architect.identifier.PersonIdentifier;
import com.liferay.portal.apio.permission.HasPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.site.apio.architect.identifier.WebSiteIdentifier;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rodrigo Guedes de Souza
 */
@Component(immediate = true, service = NestedCollectionResource.class)
public class CommerceOrderNoteNestedCollectionResource
	implements NestedCollectionResource
		<CommerceOrderNote, ClassPKExternalReferenceCode,
		 CommerceOrderNoteIdentifier, ClassPKExternalReferenceCode,
		 CommerceOrderIdentifier> {

	@Override
	public NestedCollectionRoutes
		<CommerceOrderNote, ClassPKExternalReferenceCode,
		 ClassPKExternalReferenceCode>
			collectionRoutes(NestedCollectionRoutes.Builder
			<CommerceOrderNote, ClassPKExternalReferenceCode,
				 ClassPKExternalReferenceCode>
					builder) {

		return builder.addGetter(
			this::_getPageItems
		).addCreator(
			this::_upsertCommerceOrderNote,
			_hasPermission.forAddingIn(CommerceOrderIdentifier.class),
			CommerceOrderNoteUpserterForm::buildForm
		).build();
	}

	@Override
	public String getName() {
		return "commerce-order-note";
	}

	@Override
	public ItemRoutes<CommerceOrderNote, ClassPKExternalReferenceCode>
		itemRoutes(
			ItemRoutes.Builder<CommerceOrderNote, ClassPKExternalReferenceCode>
				builder) {

		return builder.addGetter(
			_commerceOrderNoteHelper::
				getCommerceOrderNoteByClassPKExternalReferenceCode
		).addRemover(
			idempotent(_commerceOrderNoteHelper::deleteOrderNote),
			_hasPermission::forDeleting
		).build();
	}

	@Override
	public Representor<CommerceOrderNote> representor(
		Representor.Builder<CommerceOrderNote, ClassPKExternalReferenceCode>
			builder) {

		return builder.types(
			"CommerceOrderNote"
		).identifier(
			_commerceOrderNoteHelper::
				commerceOrderNoteToClassPKExternalReferenceCode
		).addBidirectionalModel(
			"commerceOrder", "commerceOrderNotes",
			CommerceOrderIdentifier.class,
			commerceOrderNote -> _commerceOrderHelper.
				commerceOrderIdToClassPKExternalReferenceCode(
					commerceOrderNote.getCommerceOrderId())
		).addBidirectionalModel(
			"webSite", "commerceOrderNotes", WebSiteIdentifier.class,
			CommerceOrderNote::getGroupId
		).addNumber(
			"id", CommerceOrderNote::getCommerceOrderNoteId
		).addString(
			"externalReferenceCode", CommerceOrderNote::getExternalReferenceCode
		).addString(
			"content", CommerceOrderNote::getContent
		).addBoolean(
			"restricted", CommerceOrderNote::getRestricted
		).addDate(
			"dateCreated", CommerceOrderNote::getCreateDate
		).addDate(
			"dateModified", CommerceOrderNote::getModifiedDate
		).addLinkedModel(
			"author", PersonIdentifier.class, CommerceOrderNote::getUserId
		).build();
	}

	private PageItems<CommerceOrderNote> _getPageItems(
			Pagination pagination,
			ClassPKExternalReferenceCode
				commerceOrderClassPKExternalReferenceCode)
		throws PortalException {

		CommerceOrder commerceOrder =
			_commerceOrderHelper.getCommerceOrderByClassPKExternalReferenceCode(
				commerceOrderClassPKExternalReferenceCode);

		List<CommerceOrderNote> commerceOrderNotes =
			_commerceOrderNoteService.getCommerceOrderNotes(
				commerceOrder.getCommerceOrderId(),
				pagination.getStartPosition(), pagination.getEndPosition());

		int total = _commerceOrderNoteService.getCommerceOrderNotesCount(
			commerceOrder.getCommerceOrderId());

		return new PageItems<>(commerceOrderNotes, total);
	}

	private CommerceOrderNote _upsertCommerceOrderNote(
			ClassPKExternalReferenceCode
				commerceOrderClassPKExternalReferenceCode,
			CommerceOrderNoteUpserterForm commerceOrderNoteUpserterForm)
		throws PortalException {

		CommerceOrder commerceOrder =
			_commerceOrderHelper.getCommerceOrderByClassPKExternalReferenceCode(
				commerceOrderClassPKExternalReferenceCode);

		return _commerceOrderNoteHelper.upsertCommerceOrderNote(
			commerceOrderNoteUpserterForm.getCommerceOrderNoteId(),
			commerceOrder.getCommerceOrderId(),
			commerceOrderNoteUpserterForm.getContent(),
			commerceOrderNoteUpserterForm.getRestricted(),
			commerceOrderNoteUpserterForm.getExternalReferenceCode());
	}

	@Reference
	private CommerceOrderHelper _commerceOrderHelper;

	@Reference
	private CommerceOrderNoteHelper _commerceOrderNoteHelper;

	@Reference
	private CommerceOrderNoteService _commerceOrderNoteService;

	@Reference(
		target = "(model.class.name=com.liferay.commerce.model.CommerceOrderNote)"
	)
	private HasPermission<ClassPKExternalReferenceCode> _hasPermission;

}