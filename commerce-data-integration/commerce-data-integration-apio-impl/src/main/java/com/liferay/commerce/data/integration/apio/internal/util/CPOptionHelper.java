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

package com.liferay.commerce.data.integration.apio.internal.util;

import com.liferay.commerce.product.model.CPOption;
import com.liferay.commerce.product.service.CPOptionLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rodrigo Guedes de Souza
 */
@Component(immediate = true, service = CPOptionHelper.class)
public class CPOptionHelper {

	public CPOption createCPOption(
			Long webSiteId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String fieldType, String key,
			User currentUser)
		throws PortalException {

		ServiceContext serviceContext = _getServiceContext(
			webSiteId, currentUser);

		return _cpOptionLocalService.addCPOption(
			nameMap, descriptionMap, fieldType, false, false, false, key,
			serviceContext);
	}

	public CPOption updateCPOption(
			Long cpOptionId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String fieldType, String key,
			User currentUser)
		throws PortalException {

		CPOption cpOption = _cpOptionLocalService.getCPOption(cpOptionId);

		cpOption.setDDMFormFieldTypeName(fieldType);
		cpOption.setDescriptionMap(descriptionMap);
		cpOption.setKey(key);
		cpOption.setNameMap(nameMap);

		ServiceContext serviceContext = _getServiceContext(
			cpOption.getGroupId(), currentUser);

		return _cpOptionLocalService.updateCPOption(
			cpOptionId, cpOption.getNameMap(), cpOption.getDescriptionMap(),
			cpOption.getDDMFormFieldTypeName(), cpOption.isFacetable(),
			cpOption.isRequired(), cpOption.isSkuContributor(),
			cpOption.getKey(), serviceContext);
	}

	private ServiceContext _getServiceContext(Long groupId, User currentUser)
		throws PortalException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);
		serviceContext.setCompanyId(currentUser.getCompanyId());
		serviceContext.setScopeGroupId(groupId);
		serviceContext.setTimeZone(currentUser.getTimeZone());
		serviceContext.setUserId(currentUser.getUserId());

		return serviceContext;
	}

	@Reference
	private CPOptionLocalService _cpOptionLocalService;

}