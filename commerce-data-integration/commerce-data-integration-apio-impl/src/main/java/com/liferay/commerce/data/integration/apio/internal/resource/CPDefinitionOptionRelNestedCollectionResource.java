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
import com.liferay.commerce.data.integration.apio.identifier.CPDefinitionIdentifier;
import com.liferay.commerce.data.integration.apio.identifier.CPDefinitionOptionRelIdentifier;
import com.liferay.commerce.data.integration.apio.identifier.ClassPKExternalReferenceCode;
import com.liferay.commerce.data.integration.apio.internal.form.CPDefinitionOptionRelCreatorForm;
import com.liferay.commerce.data.integration.apio.internal.util.CPDefinitionHelper;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPDefinitionOptionRel;
import com.liferay.commerce.product.service.CPDefinitionOptionRelService;
import com.liferay.portal.apio.permission.HasPermission;
import com.liferay.portal.apio.user.CurrentUser;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Zoltán Takács
 */
@Component(immediate = true, service = NestedCollectionResource.class)
public class CPDefinitionOptionRelNestedCollectionResource
	implements NestedCollectionResource
		<CPDefinitionOptionRel, Long, CPDefinitionOptionRelIdentifier,
		 ClassPKExternalReferenceCode,
		 CPDefinitionIdentifier> {

	@Override
	public NestedCollectionRoutes
		<CPDefinitionOptionRel, Long, ClassPKExternalReferenceCode>
			collectionRoutes(NestedCollectionRoutes.Builder
				<CPDefinitionOptionRel, Long, ClassPKExternalReferenceCode>
				builder) {

		return builder.addGetter(
			this::_getPageItems
		).addCreator(
			this::_addCPDefinitionOptionRel, CurrentUser.class,
			_hasPermission.forAddingIn(CPDefinitionIdentifier.class),
			CPDefinitionOptionRelCreatorForm::buildForm
		).build();
	}

	@Override
	public String getName() {
		return "commerce-product-definition-option";
	}

	@Override
	public ItemRoutes<CPDefinitionOptionRel, Long> itemRoutes(
		ItemRoutes.Builder<CPDefinitionOptionRel, Long> builder) {

		return builder.addGetter(
			_cpDefinitionOptionRelService::getCPDefinitionOptionRel
		).addRemover(
			idempotent(
				_cpDefinitionOptionRelService::deleteCPDefinitionOptionRel),
			_hasPermission::forDeleting
		).build();
	}

	@Override
	public Representor<CPDefinitionOptionRel> representor(
		Representor.Builder<CPDefinitionOptionRel, Long> builder) {

		return builder.types(
			"CommerceProductDefinitionOption"
		).identifier(
			CPDefinitionOptionRel::getCPDefinitionOptionRelId
		).addBidirectionalModel(
			"commerceProductDefinition", "commerceProductDefinitionOptions",
			CPDefinitionIdentifier.class,
			cpDefinitionOptionRel -> _cpDefinitionHelper.
				cpDefinitionIdToclassPKExternalReferenceCode(
					cpDefinitionOptionRel.getCPDefinitionId())
		).addDate(
			"dateCreated", CPDefinitionOptionRel::getCreateDate
		).addDate(
			"dateModified", CPDefinitionOptionRel::getModifiedDate
		).addLocalizedStringByLocale(
			"name", CPDefinitionOptionRel::getName
		).addLocalizedStringByLocale(
			"description", CPDefinitionOptionRel::getDescription
		).build();
	}

	private CPDefinitionOptionRel _addCPDefinitionOptionRel(
			ClassPKExternalReferenceCode
				cpDefinitionClassPKExternalReferenceCode,
			CPDefinitionOptionRelCreatorForm cpDefinitionOptionRelCreatorForm,
			User currentUser)
		throws PortalException {

		CPDefinition cpDefinition =
			_cpDefinitionHelper.getCPDefinitionByClassPKExternalReferenceCode(
				cpDefinitionClassPKExternalReferenceCode);

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);
		serviceContext.setCompanyId(currentUser.getCompanyId());
		serviceContext.setTimeZone(currentUser.getTimeZone());
		serviceContext.setUserId(currentUser.getUserId());
		serviceContext.setScopeGroupId(cpDefinition.getGroupId());

		return _cpDefinitionOptionRelService.addCPDefinitionOptionRel(
			cpDefinition.getCPDefinitionId(),
			cpDefinitionOptionRelCreatorForm.getCommerceProductOptionId(),
			serviceContext);
	}

	private PageItems<CPDefinitionOptionRel> _getPageItems(
			Pagination pagination,
			ClassPKExternalReferenceCode
				cpDefinitionClassPKExternalReferenceCode)
		throws PortalException {

		CPDefinition cpDefinition =
			_cpDefinitionHelper.getCPDefinitionByClassPKExternalReferenceCode(
				cpDefinitionClassPKExternalReferenceCode);

		List<CPDefinitionOptionRel> cpDefinitionOptionRels =
			_cpDefinitionOptionRelService.getCPDefinitionOptionRels(
				cpDefinition.getCPDefinitionId(), pagination.getStartPosition(),
				pagination.getEndPosition());

		int total =
			_cpDefinitionOptionRelService.getCPDefinitionOptionRelsCount(
				cpDefinition.getCPDefinitionId());

		return new PageItems<>(cpDefinitionOptionRels, total);
	}

	@Reference
	private CPDefinitionHelper _cpDefinitionHelper;

	@Reference
	private CPDefinitionOptionRelService _cpDefinitionOptionRelService;

	@Reference(
		target = "(model.class.name=com.liferay.commerce.product.model.CPDefinitionOptionRel)"
	)
	private HasPermission<Long> _hasPermission;

	@Reference
	private UserLocalService _userLocalService;

}