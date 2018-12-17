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

package com.liferay.commerce.product.type.grouped.service.impl;

import com.liferay.commerce.product.exception.NoSuchCPDefinitionException;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.service.CPDefinitionLocalService;
import com.liferay.commerce.product.service.CProductLocalService;
import com.liferay.commerce.product.type.grouped.constants.GroupedCPTypeConstants;
import com.liferay.commerce.product.type.grouped.exception.CPDefinitionGroupedEntryQuantityException;
import com.liferay.commerce.product.type.grouped.model.CPDefinitionGroupedEntry;
import com.liferay.commerce.product.type.grouped.service.base.CPDefinitionGroupedEntryLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.List;

/**
 * @author Andrea Di Giorgi
 */
public class CPDefinitionGroupedEntryLocalServiceImpl
	extends CPDefinitionGroupedEntryLocalServiceBaseImpl {

	@Override
	public void addCPDefinitionGroupedEntries(
			long cpDefinitionId, long[] entryCProductIds,
			ServiceContext serviceContext)
		throws PortalException {

		for (long entryCProductId : entryCProductIds) {
			cpDefinitionGroupedEntryLocalService.addCPDefinitionGroupedEntry(
				cpDefinitionId, entryCProductId, 0, 1, serviceContext);
		}
	}

	@Override
	public CPDefinitionGroupedEntry addCPDefinitionGroupedEntry(
			long cpDefinitionId, long entryCProductId, double priority,
			int quantity, ServiceContext serviceContext)
		throws PortalException {

		CPDefinition cpDefinition = _cpDefinitionLocalService.getCPDefinition(
			cpDefinitionId);
		User user = userLocalService.getUser(serviceContext.getUserId());

		validate(cpDefinitionId, entryCProductId, quantity);

		long cpDefinitionGroupedEntryId = counterLocalService.increment();

		CPDefinitionGroupedEntry cpDefinitionGroupedEntry =
			cpDefinitionGroupedEntryPersistence.create(
				cpDefinitionGroupedEntryId);

		if (_cpDefinitionLocalService.isPublishedCPDefinition(
				cpDefinitionGroupedEntry.getCPDefinitionId())) {

			cpDefinition = _cpDefinitionLocalService.copyCPDefinition(
				cpDefinitionId);

			_cProductLocalService.updatePublishedDefinitionId(
				cpDefinition.getCProductId(), cpDefinition.getCPDefinitionId());
		}

		cpDefinitionGroupedEntry.setUuid(serviceContext.getUuid());
		cpDefinitionGroupedEntry.setGroupId(cpDefinition.getGroupId());
		cpDefinitionGroupedEntry.setCompanyId(user.getCompanyId());
		cpDefinitionGroupedEntry.setUserId(user.getUserId());
		cpDefinitionGroupedEntry.setUserName(user.getFullName());
		cpDefinitionGroupedEntry.setCPDefinitionId(
			cpDefinition.getCPDefinitionId());
		cpDefinitionGroupedEntry.setEntryCProductId(
			cpDefinition.getCProductId());
		cpDefinitionGroupedEntry.setPriority(priority);
		cpDefinitionGroupedEntry.setQuantity(quantity);

		cpDefinitionGroupedEntryPersistence.update(cpDefinitionGroupedEntry);

		return cpDefinitionGroupedEntry;
	}

	@Override
	public void deleteCPDefinitionGroupedEntries(long cpDefinitionId) {
		if (_cpDefinitionLocalService.isPublishedCPDefinition(cpDefinitionId)) {
			CPDefinition newCPDefinition =
				_cpDefinitionLocalService.copyCPDefinition(cpDefinitionId);

			_cProductLocalService.updatePublishedDefinitionId(
				newCPDefinition.getCProductId(),
				newCPDefinition.getCPDefinitionId());

			cpDefinitionId = newCPDefinition.getCPDefinitionId();
		}

		cpDefinitionGroupedEntryPersistence.removeByCPDefinitionId(
			cpDefinitionId);
	}

	@Override
	public CPDefinitionGroupedEntry fetchCPDefinitionGroupedEntryByC_E(
		long cpDefinitionId, long entryCProductId) {

		return cpDefinitionGroupedEntryPersistence.fetchByC_E(
			cpDefinitionId, entryCProductId);
	}

	@Override
	public List<CPDefinitionGroupedEntry> getCPDefinitionGroupedEntries(
		long cpDefinitionId) {

		return cpDefinitionGroupedEntryPersistence.findByCPDefinitionId(
			cpDefinitionId);
	}

	@Override
	public List<CPDefinitionGroupedEntry> getCPDefinitionGroupedEntries(
		long cpDefinitionId, int start, int end,
		OrderByComparator<CPDefinitionGroupedEntry> orderByComparator) {

		return cpDefinitionGroupedEntryPersistence.findByCPDefinitionId(
			cpDefinitionId, start, end, orderByComparator);
	}

	@Override
	public List<CPDefinitionGroupedEntry>
		getCPDefinitionGroupedEntriesByCPDefinitionId(long cpDefinitionId) {

		return cpDefinitionGroupedEntryPersistence.findByCPDefinitionId(
			cpDefinitionId);
	}

	@Override
	public int getCPDefinitionGroupedEntriesCount(long cpDefinitionId) {
		return cpDefinitionGroupedEntryPersistence.countByCPDefinitionId(
			cpDefinitionId);
	}

	@Override
	public CPDefinitionGroupedEntry updateCPDefinitionGroupedEntry(
			long cpDefinitionGroupedEntryId, double priority, int quantity)
		throws PortalException {

		CPDefinitionGroupedEntry cpDefinitionGroupedEntry =
			cpDefinitionGroupedEntryPersistence.findByPrimaryKey(
				cpDefinitionGroupedEntryId);

		long cpDefinitionId = cpDefinitionGroupedEntry.getCPDefinitionId();

		if (_cpDefinitionLocalService.isPublishedCPDefinition(cpDefinitionId)) {
			CPDefinition newCPDefinition =
				_cpDefinitionLocalService.copyCPDefinition(cpDefinitionId);

			_cProductLocalService.updatePublishedDefinitionId(
				newCPDefinition.getCProductId(),
				newCPDefinition.getCPDefinitionId());

			cpDefinitionGroupedEntry =
				cpDefinitionGroupedEntryPersistence.findByC_E(
					newCPDefinition.getCPDefinitionId(),
					cpDefinitionGroupedEntry.getEntryCProductId());
		}

		validate(
			cpDefinitionGroupedEntry.getCPDefinitionId(),
			cpDefinitionGroupedEntry.getEntryCProductId(), quantity);

		cpDefinitionGroupedEntry.setPriority(priority);
		cpDefinitionGroupedEntry.setQuantity(quantity);

		cpDefinitionGroupedEntryPersistence.update(cpDefinitionGroupedEntry);

		return cpDefinitionGroupedEntry;
	}

	protected void validate(
			long cpDefinitionId, long entryCProductId, int quantity)
		throws PortalException {

		CPDefinition entryCPDefinition =
			_cpDefinitionLocalService.getCPDefinition(entryCProductId);

		if ((cpDefinitionId == entryCPDefinition.getCPDefinitionId()) ||
			GroupedCPTypeConstants.NAME.equals(
				entryCPDefinition.getProductTypeName())) {

			throw new NoSuchCPDefinitionException();
		}

		if (quantity <= 0) {
			throw new CPDefinitionGroupedEntryQuantityException();
		}
	}

	@ServiceReference(type = CPDefinitionLocalService.class)
	private CPDefinitionLocalService _cpDefinitionLocalService;

	@ServiceReference(type = CProductLocalService.class)
	private CProductLocalService _cProductLocalService;

}