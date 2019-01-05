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

package com.liferay.commerce.account.internal.verify;

import com.liferay.commerce.account.util.CommerceAccountRoleHelper;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.security.SecureRandomUtil;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.verify.VerifyProcess;

import java.util.List;
import java.util.UUID;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = "verify.process.name=com.liferay.commerce.account.service",
	service = {CommerceAccountServiceVerifyProcess.class, VerifyProcess.class}
)
public class CommerceAccountServiceVerifyProcess extends VerifyProcess {

	@Override
	protected void doVerify() throws Exception {
		verifyAccountRoles();
	}

	protected void verifyAccountRoles() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			List<Company> companies = _companyLocalService.getCompanies();

			for (Company company : companies) {
				ServiceContext serviceContext = new ServiceContext();

				serviceContext.setCompanyId(company.getCompanyId());

				UUID uuid = new UUID(
					SecureRandomUtil.nextLong(), SecureRandomUtil.nextLong());

				serviceContext.setUuid(uuid.toString());

				_commerceAccountRoleHelper.checkCommerceAccountRoles(
					serviceContext);
			}
		}
	}

	@Reference
	private CommerceAccountRoleHelper _commerceAccountRoleHelper;

	@Reference
	private CompanyLocalService _companyLocalService;

}