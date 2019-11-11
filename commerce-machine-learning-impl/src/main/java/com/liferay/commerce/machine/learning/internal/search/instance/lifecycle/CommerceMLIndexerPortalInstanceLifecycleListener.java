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

package com.liferay.commerce.machine.learning.internal.search.instance.lifecycle;

import com.liferay.commerce.machine.learning.internal.search.api.CommerceMLIndexer;
import com.liferay.portal.instance.lifecycle.BasePortalInstanceLifecycleListener;
import com.liferay.portal.instance.lifecycle.PortalInstanceLifecycleListener;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.service.CompanyLocalService;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Marco Leo
 */
@Component(immediate = true, service = PortalInstanceLifecycleListener.class)
public class CommerceMLIndexerPortalInstanceLifecycleListener
	extends BasePortalInstanceLifecycleListener {

	@Override
	public void portalInstanceRegistered(Company company) throws Exception {
		try {
			for (CommerceMLIndexer commerceMLIndexer : _commerceMLIndexers) {
				commerceMLIndexer.createIndex(company.getCompanyId());
			}
		}
		catch (Exception e) {
			_log.error(
				"Unable to add commerce recommend index for company " + company,
				e);
		}
	}

	@Override
	public void portalInstanceUnregistered(Company company) throws Exception {
		try {
			for (CommerceMLIndexer commerceMLIndexer : _commerceMLIndexers) {
				commerceMLIndexer.dropIndex(company.getCompanyId());
			}
		}
		catch (Exception e) {
			_log.error(
				"Unable to remove commerce recommend index for company " +
					company,
				e);
		}
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY,
		service = CommerceMLIndexer.class
	)
	protected void setCommerceMachineLearningIndexer(
		CommerceMLIndexer commerceMLIndexer) {

		_commerceMLIndexers.add(commerceMLIndexer);

		if (_companyLocalService == null) {
			_queuedCommerceMLIndexers.add(commerceMLIndexer);

			return;
		}

		verifyCompanies(commerceMLIndexer);
	}

	@Reference(unbind = "-")
	protected void setCompanyLocalService(
		CompanyLocalService companyLocalService) {

		_companyLocalService = companyLocalService;

		for (CommerceMLIndexer queuedCommerceMLIndexer :
				_queuedCommerceMLIndexers) {

			verifyCompanies(queuedCommerceMLIndexer);
		}

		_queuedCommerceMLIndexers.clear();
	}

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	protected void unsetCommerceMachineLearningIndexer(
		CommerceMLIndexer commerceMLIndexer) {

		_commerceMLIndexers.remove(commerceMLIndexer);
	}

	protected void verifyCompanies(CommerceMLIndexer commerceMLIndexer) {
		for (Company company : _companyLocalService.getCompanies()) {
			commerceMLIndexer.createIndex(company.getCompanyId());
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceMLIndexerPortalInstanceLifecycleListener.class);

	private final List<CommerceMLIndexer> _commerceMLIndexers =
		new CopyOnWriteArrayList<>();
	private CompanyLocalService _companyLocalService;
	private final Set<CommerceMLIndexer> _queuedCommerceMLIndexers =
		Collections.newSetFromMap(new ConcurrentHashMap<>());

}