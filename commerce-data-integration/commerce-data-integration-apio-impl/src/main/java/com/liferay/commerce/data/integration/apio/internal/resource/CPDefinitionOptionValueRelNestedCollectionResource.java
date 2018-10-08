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

import com.liferay.apio.architect.pagination.PageItems;
import com.liferay.apio.architect.pagination.Pagination;
import com.liferay.apio.architect.representor.Representor;
import com.liferay.apio.architect.resource.NestedCollectionResource;
import com.liferay.apio.architect.routes.ItemRoutes;
import com.liferay.apio.architect.routes.NestedCollectionRoutes;
import com.liferay.commerce.data.integration.apio.identifiers.CPDefinitionOptionRelIdentifier;
import com.liferay.commerce.data.integration.apio.identifiers.CPDefinitionOptionValueRelIdentifier;
import com.liferay.commerce.product.model.CPDefinitionOptionValueRel;
import com.liferay.commerce.product.service.CPDefinitionOptionValueRelService;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Zoltán Takács
 */
@Component(immediate = true)
public class CPDefinitionOptionValueRelNestedCollectionResource
	implements NestedCollectionResource
		<CPDefinitionOptionValueRel, Long, CPDefinitionOptionValueRelIdentifier,
		Long,
		CPDefinitionOptionRelIdentifier> {

	@Override
	public NestedCollectionRoutes<CPDefinitionOptionValueRel, Long, Long>
		collectionRoutes(
			NestedCollectionRoutes.
				Builder<CPDefinitionOptionValueRel, Long, Long> builder) {

		return builder.addGetter(
			this::_getPageItems
		).build();
	}

	@Override
	public String getName() {
		return "commerce-product-definition-optional-value";
	}

	@Override
	public ItemRoutes<CPDefinitionOptionValueRel, Long> itemRoutes(
		ItemRoutes.Builder<CPDefinitionOptionValueRel, Long> builder) {

		return builder.addGetter(
			_cpDefinitionOptionValueRelService::getCPDefinitionOptionValueRel
		).build();
	}

	@Override
	public Representor<CPDefinitionOptionValueRel> representor(
		Representor.Builder<CPDefinitionOptionValueRel, Long> builder) {

		return builder.types(
			"CommerceProductDefinitionOptionValue"
		).identifier(
			CPDefinitionOptionValueRel::getCPDefinitionOptionValueRelId
		).addBidirectionalModel(
			"commerceProductDefinitionOption",
			"commerceProductDefinitionOptionValues",
			CPDefinitionOptionRelIdentifier.class,
			CPDefinitionOptionValueRel::getCPDefinitionOptionRelId
		).addDate(
			"dateCreated", CPDefinitionOptionValueRel::getCreateDate
		).addDate(
			"dateModified", CPDefinitionOptionValueRel::getModifiedDate
		).addLocalizedStringByLocale(
			"name", CPDefinitionOptionValueRel::getName
		).build();
	}

	private PageItems<CPDefinitionOptionValueRel> _getPageItems(
			Pagination pagination, Long cpDefinitionOptionRelId)
		throws PortalException {

		List<CPDefinitionOptionValueRel> cpDefinitionOptionValueRels =
			_cpDefinitionOptionValueRelService.getCPDefinitionOptionValueRels(
				cpDefinitionOptionRelId, pagination.getStartPosition(),
				pagination.getEndPosition());

		int total =
			_cpDefinitionOptionValueRelService.
				getCPDefinitionOptionValueRelsCount(cpDefinitionOptionRelId);

		return new PageItems<>(cpDefinitionOptionValueRels, total);
	}

	@Reference
	private CPDefinitionOptionValueRelService
		_cpDefinitionOptionValueRelService;

}