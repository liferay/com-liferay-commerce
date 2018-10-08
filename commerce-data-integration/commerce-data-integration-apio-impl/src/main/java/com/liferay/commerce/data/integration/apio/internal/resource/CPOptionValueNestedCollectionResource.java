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
import com.liferay.commerce.data.integration.apio.identifiers.CPOptionIdentifier;
import com.liferay.commerce.data.integration.apio.identifiers.CPOptionValueIdentifier;
import com.liferay.commerce.data.integration.apio.internal.form.CPOptionValueCreatorForm;
import com.liferay.commerce.data.integration.apio.internal.util.CPOptionValueHelper;
import com.liferay.commerce.product.exception.CPOptionValueKeyException;
import com.liferay.commerce.product.model.CPOptionValue;
import com.liferay.commerce.product.service.CPOptionValueService;
import com.liferay.portal.apio.permission.HasPermission;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

import javax.ws.rs.BadRequestException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rodrigo Guedes de Souza
 */
@Component(immediate = true)
public class CPOptionValueNestedCollectionResource
	implements NestedCollectionResource
		<CPOptionValue, Long, CPOptionValueIdentifier, Long,
		 CPOptionIdentifier> {

	@Override
	public NestedCollectionRoutes<CPOptionValue, Long, Long> collectionRoutes(
		NestedCollectionRoutes.Builder<CPOptionValue, Long, Long> builder) {

		return builder.addGetter(
			this::_getPageItems
		).addCreator(
			this::_addCPOptionValue,
			_hasPermission.forAddingIn(CPOptionIdentifier.class),
			CPOptionValueCreatorForm::buildForm
		).build();
	}

	@Override
	public String getName() {
		return "commerce-product-option-value";
	}

	@Override
	public ItemRoutes<CPOptionValue, Long> itemRoutes(
		ItemRoutes.Builder<CPOptionValue, Long> builder) {

		return builder.addGetter(
			_cpOptionValueService::getCPOptionValue
		).addRemover(
			idempotent(_cpOptionValueService::deleteCPOptionValue),
			_hasPermission::forDeleting
		).addUpdater(
			this::_updateCPOptionValue, _hasPermission::forUpdating,
			CPOptionValueCreatorForm::buildForm
		).build();
	}

	@Override
	public Representor<CPOptionValue> representor(
		Representor.Builder<CPOptionValue, Long> builder) {

		return builder.types(
			"CommerceProductOptionValue"
		).identifier(
			CPOptionValue::getCPOptionValueId
		).addBidirectionalModel(
			"commerceProductOption", "commerceProductOptionValues",
			CPOptionIdentifier.class, CPOptionValue::getCPOptionId
		).addLocalizedStringByLocale(
			"name", CPOptionValue::getName
		).addString(
			"key", CPOptionValue::getKey
		).build();
	}

	private CPOptionValue _addCPOptionValue(
			Long cpOptionId, CPOptionValueCreatorForm cpOptionValueCreatorForm)
		throws PortalException {

		try {
			return _cpOptionValueHelper.createCPOptionValue(
				cpOptionId, cpOptionValueCreatorForm.getNameMap(),
				cpOptionValueCreatorForm.getKey());
		}
		catch (CPOptionValueKeyException cpovke) {
			throw new BadRequestException(
				String.format(
					"An option value with key '%s' already exists",
					cpOptionValueCreatorForm.getKey()),
				cpovke);
		}
	}

	private PageItems<CPOptionValue> _getPageItems(
			Pagination pagination, Long cpOptionId)
		throws PortalException {

		List<CPOptionValue> cpOptionValues =
			_cpOptionValueService.getCPOptionValues(
				cpOptionId, pagination.getStartPosition(),
				pagination.getEndPosition());

		int total = _cpOptionValueService.getCPOptionValuesCount(cpOptionId);

		return new PageItems<>(cpOptionValues, total);
	}

	private CPOptionValue _updateCPOptionValue(
			Long cpOptionValueId,
			CPOptionValueCreatorForm cpOptionValueCreatorForm)
		throws PortalException {

		return _cpOptionValueHelper.updateCPOptionValue(
			cpOptionValueId, cpOptionValueCreatorForm.getNameMap(),
			cpOptionValueCreatorForm.getKey());
	}

	@Reference
	private CPOptionValueHelper _cpOptionValueHelper;

	@Reference
	private CPOptionValueService _cpOptionValueService;

	@Reference(
		target = "(model.class.name=com.liferay.commerce.product.model.CPOptionValue)"
	)
	private HasPermission<Long> _hasPermission;

}