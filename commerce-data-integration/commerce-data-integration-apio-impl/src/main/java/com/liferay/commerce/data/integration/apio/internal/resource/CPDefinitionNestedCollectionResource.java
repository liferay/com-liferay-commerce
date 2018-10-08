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
import com.liferay.commerce.data.integration.apio.identifiers.CPDefinitionIdentifier;
import com.liferay.commerce.data.integration.apio.identifiers.ClassPKExternalReferenceCode;
import com.liferay.commerce.data.integration.apio.internal.form.CPDefinitionUpserterForm;
import com.liferay.commerce.data.integration.apio.internal.util.CPDefinitionHelper;
import com.liferay.commerce.product.exception.CPDefinitionProductTypeNameException;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.service.CPDefinitionService;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.person.apio.architect.identifier.PersonIdentifier;
import com.liferay.portal.apio.permission.HasPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.site.apio.architect.identifier.WebSiteIdentifier;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.NotFoundException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the information necessary to expose <a
 * href="http://schema.org/Product">Product</a> resources through a web API. The
 * resources are mapped from the internal model {@code CPDefinition}.
 *
 * @author Zoltán Takács
 */
@Component(immediate = true)
public class CPDefinitionNestedCollectionResource
	implements NestedCollectionResource
		<CPDefinition, ClassPKExternalReferenceCode, CPDefinitionIdentifier,
		 Long, WebSiteIdentifier> {

	@Override
	public NestedCollectionRoutes
		<CPDefinition, ClassPKExternalReferenceCode, Long> collectionRoutes(
			NestedCollectionRoutes.Builder
				<CPDefinition, ClassPKExternalReferenceCode, Long>
					builder) {

		return builder.addGetter(
			this::_getPageItems
		).addCreator(
			this::_upsertCPDefinition,
			_hasPermission.forAddingIn(WebSiteIdentifier.class),
			CPDefinitionUpserterForm::buildForm
		).build();
	}

	@Override
	public String getName() {
		return "commerce-product-definition";
	}

	@Override
	public ItemRoutes<CPDefinition, ClassPKExternalReferenceCode> itemRoutes(
		ItemRoutes.Builder<CPDefinition, ClassPKExternalReferenceCode>
			builder) {

		return builder.addGetter(
			_cpDefinitionHelper::getCPDefinitionByClassPKExternalReferenceCode
		).addRemover(
			idempotent(_cpDefinitionHelper::deleteCPDefinition),
			_hasPermission::forDeleting
		).build();
	}

	@Override
	public Representor<CPDefinition> representor(
		Representor.Builder<CPDefinition, ClassPKExternalReferenceCode>
			builder) {

		return builder.types(
			"CommerceProductDefinition"
		).identifier(
			_cpDefinitionHelper::cpDefinitionToClassPKExternalReferenceCode
		).addBidirectionalModel(
			"webSite", "commerceProductDefinitions", WebSiteIdentifier.class,
			CPDefinition::getGroupId
		).addDate(
			"dateCreated", CPDefinition::getCreateDate
		).addDate(
			"dateModified", CPDefinition::getModifiedDate
		).addLinkedModel(
			"author", PersonIdentifier.class, CPDefinition::getUserId
		).addString(
			"description", CPDefinition::getDescription
		).addString(
			"productType", CPDefinition::getProductTypeName
		).addString(
			"name", CPDefinition::getName
		).addString(
			"externalReferenceCode", CPDefinition::getExternalReferenceCode
		).addStringList(
			"skus", this::_getSKUs
		).build();
	}

	private PageItems<CPDefinition> _getPageItems(
			Pagination pagination, Long webSiteId)
		throws PortalException {

		List<CPDefinition> cpDefinitions =
			_cpDefinitionService.getCPDefinitions(
				webSiteId, null, null, WorkflowConstants.STATUS_APPROVED,
				pagination.getStartPosition(), pagination.getEndPosition(),
				null);

		int total = _cpDefinitionService.getCPDefinitionsCount(
			webSiteId, null, null, WorkflowConstants.STATUS_APPROVED);

		return new PageItems<>(cpDefinitions, total);
	}

	private List<String> _getSKUs(CPDefinition cpDefinition) {
		return Arrays.asList(
			_cpInstanceLocalService.getSKUs(cpDefinition.getCPDefinitionId()));
	}

	private CPDefinition _upsertCPDefinition(
			Long webSiteId, CPDefinitionUpserterForm cpDefinitionUpserterForm)
		throws PortalException {

		try {
			return _cpDefinitionHelper.upsertCPDefinition(
				webSiteId, cpDefinitionUpserterForm.getTitleMap(),
				cpDefinitionUpserterForm.getDescriptionMap(),
				cpDefinitionUpserterForm.getShortDescriptionMap(),
				cpDefinitionUpserterForm.getProductTypeName(),
				ArrayUtil.toLongArray(
					cpDefinitionUpserterForm.getAssetCategoryIds()),
				cpDefinitionUpserterForm.getExternalReferenceCode(),
				cpDefinitionUpserterForm.getDefaultSku(),
				cpDefinitionUpserterForm.getActive());
		}
		catch (CPDefinitionProductTypeNameException cpdptne) {
			throw new NotFoundException(
				"Product type not available: " +
					cpDefinitionUpserterForm.getProductTypeName(),
				cpdptne);
		}
	}

	@Reference
	private CPDefinitionHelper _cpDefinitionHelper;

	@Reference
	private CPDefinitionService _cpDefinitionService;

	@Reference
	private CPInstanceLocalService _cpInstanceLocalService;

	@Reference(
		target = "(model.class.name=com.liferay.commerce.product.model.CPDefinition)"
	)
	private HasPermission<ClassPKExternalReferenceCode> _hasPermission;

}