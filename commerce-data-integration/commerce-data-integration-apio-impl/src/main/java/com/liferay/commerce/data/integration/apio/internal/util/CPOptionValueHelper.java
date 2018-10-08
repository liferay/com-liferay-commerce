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
import com.liferay.commerce.product.model.CPOptionValue;
import com.liferay.commerce.product.service.CPOptionService;
import com.liferay.commerce.product.service.CPOptionValueService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rodrigo Guedes de Souza
 */
@Component(immediate = true, service = CPOptionValueHelper.class)
public class CPOptionValueHelper {

	public CPOptionValue createCPOptionValue(
			Long cpOptionId, Map<Locale, String> titleMap, String key)
		throws PortalException {

		CPOption cpOption = _cpOptionService.getCPOption(cpOptionId);

		ServiceContext serviceContext = _getServiceContext(cpOption);

		return _cpOptionValueService.addCPOptionValue(
			cpOptionId, titleMap, 0, key, serviceContext);
	}

	public CPOptionValue updateCPOptionValue(
			Long cpOptionValueId, Map<Locale, String> nameMap, String key)
		throws PortalException {

		CPOptionValue cpOptionValue = _cpOptionValueService.getCPOptionValue(
			cpOptionValueId);

		ServiceContext serviceContext = _getServiceContext(
			cpOptionValue.getCPOption());

		return _cpOptionValueService.updateCPOptionValue(
			cpOptionValueId, nameMap, 0, key, serviceContext);
	}

	private ServiceContext _getServiceContext(CPOption cpOption) {
		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);
		serviceContext.setCompanyId(cpOption.getCompanyId());
		serviceContext.setScopeGroupId(cpOption.getGroupId());
		serviceContext.setUserId(cpOption.getUserId());

		return serviceContext;
	}

	@Reference
	private CPOptionService _cpOptionService;

	@Reference
	private CPOptionValueService _cpOptionValueService;

}