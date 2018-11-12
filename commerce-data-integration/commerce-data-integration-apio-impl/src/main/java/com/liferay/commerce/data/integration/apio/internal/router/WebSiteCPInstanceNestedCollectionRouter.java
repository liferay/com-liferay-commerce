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

package com.liferay.commerce.data.integration.apio.internal.router;

import com.liferay.apio.architect.pagination.PageItems;
import com.liferay.apio.architect.pagination.Pagination;
import com.liferay.apio.architect.router.NestedCollectionRouter;
import com.liferay.apio.architect.routes.NestedCollectionRoutes;
import com.liferay.commerce.data.integration.apio.identifier.CPInstanceIdentifier;
import com.liferay.commerce.data.integration.apio.identifier.ClassPKExternalReferenceCode;
import com.liferay.commerce.data.integration.apio.internal.form.CPInstanceWebSiteUpserterForm;
import com.liferay.commerce.data.integration.apio.internal.util.CPInstanceHelper;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPInstanceService;
import com.liferay.portal.apio.permission.HasPermission;
import com.liferay.portal.apio.user.CurrentUser;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.site.apio.architect.identifier.WebSiteIdentifier;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rodrigo Guedes de Souza
 * @author Zoltán Takács
 */
@Component(immediate = true, service = NestedCollectionRouter.class)
public class WebSiteCPInstanceNestedCollectionRouter
	implements NestedCollectionRouter
		<CPInstance, ClassPKExternalReferenceCode, CPInstanceIdentifier, Long,
		 WebSiteIdentifier> {

	@Override
	public NestedCollectionRoutes
		<CPInstance, ClassPKExternalReferenceCode, Long>
			collectionRoutes(NestedCollectionRoutes.Builder
				<CPInstance, ClassPKExternalReferenceCode, Long>
					 builder) {

		return builder.addGetter(
			this::_getPageItems
		).addCreator(
			this::_upsertCPInstance, CurrentUser.class,
			_hasPermission.forAddingIn(WebSiteIdentifier.class),
			CPInstanceWebSiteUpserterForm::buildForm
		).build();
	}

	private PageItems<CPInstance> _getPageItems(
			Pagination pagination, long groupId)
		throws PortalException {

		List<CPInstance> commercePriceEntries =
			_cpInstanceService.getCPInstances(
				groupId, WorkflowConstants.STATUS_ANY,
				pagination.getStartPosition(), pagination.getEndPosition(),
				null);

		int count = _cpInstanceService.getCPInstancesCount(
			groupId, WorkflowConstants.STATUS_ANY);

		return new PageItems<>(commercePriceEntries, count);
	}

	private CPInstance _upsertCPInstance(
			long groupId,
			CPInstanceWebSiteUpserterForm cpInstanceWebSiteUpserterForm,
			User currentUser)
		throws PortalException {

		return _cpInstanceHelper.upsertCPInstance(
			groupId, 0, cpInstanceWebSiteUpserterForm.getSku(),
			cpInstanceWebSiteUpserterForm.getGtin(),
			cpInstanceWebSiteUpserterForm.getManufacturerPartNumber(),
			cpInstanceWebSiteUpserterForm.getPurchasable(),
			cpInstanceWebSiteUpserterForm.getWidth(),
			cpInstanceWebSiteUpserterForm.getHeight(),
			cpInstanceWebSiteUpserterForm.getDepth(),
			cpInstanceWebSiteUpserterForm.getWeight(),
			cpInstanceWebSiteUpserterForm.getCost(),
			cpInstanceWebSiteUpserterForm.getPrice(),
			cpInstanceWebSiteUpserterForm.getPromoPrice(),
			cpInstanceWebSiteUpserterForm.getPublished(),
			cpInstanceWebSiteUpserterForm.getDisplayDate(),
			cpInstanceWebSiteUpserterForm.getExpirationDate(),
			cpInstanceWebSiteUpserterForm.getNeverExpire(),
			cpInstanceWebSiteUpserterForm.getExternalReferenceCode(),
			cpInstanceWebSiteUpserterForm.getTitleMap(),
			cpInstanceWebSiteUpserterForm.getDescriptionMap(),
			cpInstanceWebSiteUpserterForm.getShortDescriptionMap(),
			cpInstanceWebSiteUpserterForm.getProductTypeName(),
			cpInstanceWebSiteUpserterForm.
				getProductDefinitionExternalReferenceCode(),
			ArrayUtil.toLongArray(
				cpInstanceWebSiteUpserterForm.getAssetCategoryIds()),
			cpInstanceWebSiteUpserterForm.getActive(),
			cpInstanceWebSiteUpserterForm.getDefaultSku(),
			cpInstanceWebSiteUpserterForm.getCpInstanceId(), currentUser);
	}

	@Reference
	private CPInstanceHelper _cpInstanceHelper;

	@Reference
	private CPInstanceService _cpInstanceService;

	@Reference(
		target = "(model.class.name=com.liferay.commerce.product.model.CPInstance)"
	)
	private HasPermission<ClassPKExternalReferenceCode> _hasPermission;

}