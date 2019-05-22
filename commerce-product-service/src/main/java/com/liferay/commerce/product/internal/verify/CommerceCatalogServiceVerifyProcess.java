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

package com.liferay.commerce.product.internal.verify;

import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.service.CommerceCatalogLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.verify.VerifyProcess;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = "verify.process.name=com.liferay.commerce.product.service",
	service = {CommerceCatalogServiceVerifyProcess.class, VerifyProcess.class}
)
public class CommerceCatalogServiceVerifyProcess extends VerifyProcess {

	@Override
	protected void doVerify() {
		verifyMasterCommerceCatalog();
	}

	protected void verifyMasterCommerceCatalog() {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			List<Company> companies = _companyLocalService.getCompanies();

			for (Company company : companies) {
				List<CommerceCatalog> commerceCatalogs =
					_commerceCatalogLocalService.getCommerceCatalogs(
						company.getCompanyId(), true);

				if (commerceCatalogs.isEmpty()) {
					_commerceCatalogLocalService.addDefaultCommerceCatalog(
						company.getCompanyId());
				}
			}
		}
		catch (PortalException pe) {
			_log.error(pe, pe);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceCatalogServiceVerifyProcess.class);

	@Reference
	private CommerceCatalogLocalService _commerceCatalogLocalService;

	@Reference
	private CompanyLocalService _companyLocalService;

}