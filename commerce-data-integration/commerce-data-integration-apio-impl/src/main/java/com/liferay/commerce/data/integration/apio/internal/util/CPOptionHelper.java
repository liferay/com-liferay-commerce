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
			cpOption.getDDMFormFieldTypeName(), cpOption.getFacetable(),
			cpOption.getRequired(), cpOption.getSkuContributor(),
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