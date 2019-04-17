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

package com.liferay.commerce.product.internal.instance.lifecycle;

import com.liferay.commerce.product.indexer.CommerceCatalogScopeIndexer;
import com.liferay.portal.instance.lifecycle.BasePortalInstanceLifecycleListener;
import com.liferay.portal.instance.lifecycle.PortalInstanceLifecycleListener;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.service.CompanyLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ethan Bustad
 * @author Marco Leo
 */
@Component(immediate = true, service = PortalInstanceLifecycleListener.class)
public class CommerceCatalogScopeIndexPortalInstanceLifecycleListener
	extends BasePortalInstanceLifecycleListener {

	@Override
	public void portalInstanceRegistered(Company company) throws Exception {
		try {
			_commerceCatalogScopeIndexer.createIndex(company.getCompanyId());
		}
		catch (Exception e) {
			_log.error(
				"Unable to add commerce catalog scope index for company " +
					company.getCompanyId(),
				e);
		}
	}

	@Override
	public void portalInstanceUnregistered(Company company) throws Exception {
		try {
			_commerceCatalogScopeIndexer.dropIndex(company.getCompanyId());
		}
		catch (Exception e) {
			_log.error(
				"Unable to remove commerce catalog scope index for company " +
					company.getCompanyId(),
				e);
		}
	}

	@Reference(unbind = "-")
	protected void setCommerceCatalogScopeIndexer(
		CommerceCatalogScopeIndexer commerceCatalogScopeIndexer) {

		_commerceCatalogScopeIndexer = commerceCatalogScopeIndexer;

		if (_companyLocalService != null) {
			verifyCompanies();
		}
	}

	@Reference(unbind = "-")
	protected void setCompanyLocalService(
		CompanyLocalService companyLocalService) {

		_companyLocalService = companyLocalService;

		if (_commerceCatalogScopeIndexer != null) {
			verifyCompanies();
		}
	}

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	protected void verifyCompanies() {
		for (Company company : _companyLocalService.getCompanies()) {
			_commerceCatalogScopeIndexer.createIndex(company.getCompanyId());
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceCatalogScopeIndexPortalInstanceLifecycleListener.class);

	private CommerceCatalogScopeIndexer _commerceCatalogScopeIndexer;
	private CompanyLocalService _companyLocalService;

}