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

import static com.liferay.commerce.data.integration.headless.compat.apio.idempotent.Idempotent.idempotent;

import com.liferay.apio.architect.pagination.PageItems;
import com.liferay.apio.architect.pagination.Pagination;
import com.liferay.apio.architect.representor.Representor;
import com.liferay.apio.architect.resource.NestedCollectionResource;
import com.liferay.apio.architect.routes.ItemRoutes;
import com.liferay.apio.architect.routes.NestedCollectionRoutes;
import com.liferay.commerce.data.integration.apio.identifier.CPOptionIdentifier;
import com.liferay.commerce.data.integration.apio.internal.form.CPOptionUpserterForm;
import com.liferay.commerce.data.integration.apio.internal.util.CPOptionHelper;
import com.liferay.commerce.data.integration.headless.compat.apio.identifier.CommerceWebSiteIdentifier;
import com.liferay.commerce.data.integration.headless.compat.apio.permission.HasPermission;
import com.liferay.commerce.data.integration.headless.compat.apio.user.CurrentUser;
import com.liferay.commerce.product.exception.CPOptionKeyException;
import com.liferay.commerce.product.model.CPOption;
import com.liferay.commerce.product.service.CPOptionService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;

import java.util.List;

import javax.ws.rs.BadRequestException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rodrigo Guedes de Souza
 */
@Component(immediate = true, service = NestedCollectionResource.class)
public class CPOptionNestedCollectionResource
	implements NestedCollectionResource
		<CPOption, Long, CPOptionIdentifier, Long, CommerceWebSiteIdentifier> {

	@Override
	public NestedCollectionRoutes<CPOption, Long, Long> collectionRoutes(
		NestedCollectionRoutes.Builder<CPOption, Long, Long> builder) {

		return builder.addGetter(
			this::_getPageItems
		).addCreator(
			this::_addCPOption, CurrentUser.class,
			_hasPermission.forAddingIn(CommerceWebSiteIdentifier.class),
			CPOptionUpserterForm::buildForm
		).build();
	}

	@Override
	public String getName() {
		return "commerce-product-option";
	}

	@Override
	public ItemRoutes<CPOption, Long> itemRoutes(
		ItemRoutes.Builder<CPOption, Long> builder) {

		return builder.addGetter(
			_cpOptionService::getCPOption
		).addRemover(
			idempotent(_cpOptionService::deleteCPOption),
			_hasPermission::forDeleting
		).addUpdater(
			this::_updateCPOption, CurrentUser.class,
			_hasPermission::forUpdating, CPOptionUpserterForm::buildForm
		).build();
	}

	@Override
	public Representor<CPOption> representor(
		Representor.Builder<CPOption, Long> builder) {

		return builder.types(
			"CommerceProductOption"
		).identifier(
			CPOption::getCPOptionId
		).addBidirectionalModel(
			"commerceWebSite", "commerceProductOptions",
			CommerceWebSiteIdentifier.class, CPOption::getGroupId
		).addLocalizedStringByLocale(
			"name", CPOption::getName
		).addString(
			"fieldType", CPOption::getDDMFormFieldTypeName
		).addString(
			"key", CPOption::getKey
		).build();
	}

	private CPOption _addCPOption(
			Long webSiteId, CPOptionUpserterForm cpOptionUpserterForm,
			User currentUser)
		throws PortalException {

		try {
			return _cpOptionHelper.createCPOption(
				webSiteId, cpOptionUpserterForm.getNameMap(),
				cpOptionUpserterForm.getDescriptionMap(),
				cpOptionUpserterForm.getFieldType(),
				cpOptionUpserterForm.getKey(), currentUser);
		}
		catch (CPOptionKeyException cpoke) {
			throw new BadRequestException(
				String.format(
					"An option with key '%s' already exists",
					cpOptionUpserterForm.getKey()),
				cpoke);
		}
	}

	private PageItems<CPOption> _getPageItems(
			Pagination pagination, Long webSiteId)
		throws PortalException {

		List<CPOption> cpOptions = _cpOptionService.getCPOptions(
			webSiteId, pagination.getStartPosition(),
			pagination.getEndPosition(), null);

		int total = _cpOptionService.getCPOptionsCount(webSiteId);

		return new PageItems<>(cpOptions, total);
	}

	private CPOption _updateCPOption(
			Long cpOptionId, CPOptionUpserterForm cpOptionUpserterForm,
			User currentUser)
		throws PortalException {

		return _cpOptionHelper.updateCPOption(
			cpOptionId, cpOptionUpserterForm.getNameMap(),
			cpOptionUpserterForm.getDescriptionMap(),
			cpOptionUpserterForm.getFieldType(), cpOptionUpserterForm.getKey(),
			currentUser);
	}

	@Reference
	private CPOptionHelper _cpOptionHelper;

	@Reference
	private CPOptionService _cpOptionService;

	@Reference(
		target = "(model.class.name=com.liferay.commerce.product.model.CPOption)"
	)
	private HasPermission<Long> _hasPermission;

}