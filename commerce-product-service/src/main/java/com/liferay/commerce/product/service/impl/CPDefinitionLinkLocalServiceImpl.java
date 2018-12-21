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

package com.liferay.commerce.product.service.impl;

import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPDefinitionLink;
import com.liferay.commerce.product.service.base.CPDefinitionLinkLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * @author Alessio Antonio Rendina
 * @author Marco Leo
 */
public class CPDefinitionLinkLocalServiceImpl
	extends CPDefinitionLinkLocalServiceBaseImpl {

	/**
	 * @deprecated As of Judson (7.1.x)
	 */
	@Deprecated
	@Override
	public CPDefinitionLink addCPDefinitionLink(
			long cpDefinitionId, long cProductId, double priority, String type,
			ServiceContext serviceContext)
		throws PortalException {

		CPDefinition cpDefinition = cpDefinitionPersistence.findByPrimaryKey(
			cProductId);

		return addCPDefinitionLinkByCProductId(
			cpDefinitionId, cpDefinition.getCProductId(), priority, type,
			serviceContext);
	}

	@Override
	public CPDefinitionLink addCPDefinitionLinkByCProductId(
			long cpDefinitionId, long cProductId, double priority, String type,
			ServiceContext serviceContext)
		throws PortalException {

		CPDefinition cpDefinition = null;

		if (cpDefinitionLocalService.isPublishedCPDefinition(cpDefinitionId)) {
			cpDefinition = cpDefinitionLocalService.copyCPDefinition(
				cpDefinitionId);
		}
		else {
			cpDefinition = cpDefinitionPersistence.findByPrimaryKey(
				cpDefinitionId);
		}

		User user = userLocalService.getUser(serviceContext.getUserId());

		long cpDefinitionLinkId = counterLocalService.increment();

		CPDefinitionLink cpDefinitionLink = cpDefinitionLinkPersistence.create(
			cpDefinitionLinkId);

		cpDefinitionLink.setUuid(serviceContext.getUuid());
		cpDefinitionLink.setGroupId(cpDefinition.getGroupId());
		cpDefinitionLink.setCompanyId(user.getCompanyId());
		cpDefinitionLink.setUserId(user.getUserId());
		cpDefinitionLink.setUserName(user.getFullName());
		cpDefinitionLink.setCPDefinitionId(cpDefinition.getCPDefinitionId());
		cpDefinitionLink.setCProductId(cProductId);
		cpDefinitionLink.setPriority(priority);
		cpDefinitionLink.setType(type);
		cpDefinitionLink.setExpandoBridgeAttributes(serviceContext);

		cpDefinitionLinkPersistence.update(cpDefinitionLink);

		reindexCPDefinition(cpDefinition.getCPDefinitionId());

		return cpDefinitionLink;
	}

	@Override
	public CPDefinitionLink deleteCPDefinitionLink(
		CPDefinitionLink cpDefinitionLink) {

		if (cpDefinitionLocalService.isPublishedCPDefinition(
				cpDefinitionLink.getCPDefinitionId())) {

			CPDefinition newCPDefinition = null;

			try {
				newCPDefinition = cpDefinitionLocalService.copyCPDefinition(
					cpDefinitionLink.getCPDefinitionId());

				cpDefinitionLink = cpDefinitionLinkPersistence.findByC_C_T(
					newCPDefinition.getCPDefinitionId(),
					cpDefinitionLink.getCProductId(),
					cpDefinitionLink.getType());
			}
			catch (PortalException pe) {
				throw new SystemException(pe);
			}
		}

		// Commerce product definition link

		cpDefinitionLinkPersistence.remove(cpDefinitionLink);

		// Expando

		expandoRowLocalService.deleteRows(
			cpDefinitionLink.getCPDefinitionLinkId());

		return cpDefinitionLink;
	}

	@Override
	public CPDefinitionLink deleteCPDefinitionLink(long cpDefinitionLinkId)
		throws PortalException {

		CPDefinitionLink cpDefinitionLink =
			cpDefinitionLinkPersistence.findByPrimaryKey(cpDefinitionLinkId);

		return cpDefinitionLinkLocalService.deleteCPDefinitionLink(
			cpDefinitionLink);
	}

	/**
	 * @deprecated As of Judson (7.1.x)
	 */
	@Deprecated
	@Override
	public void deleteCPDefinitionLinks(long cpDefinitionId) {
		deleteCPDefinitionLinksByCPDefinitionId(cpDefinitionId);

		CPDefinition cpDefinition = cpDefinitionPersistence.fetchByPrimaryKey(
			cpDefinitionId);

		if (cpDefinition != null) {
			deleteCPDefinitionLinksByCProductId(cpDefinition.getCProductId());
		}
	}

	@Override
	public void deleteCPDefinitionLinksByCPDefinitionId(long cpDefinitionId) {
		List<CPDefinitionLink> cpDefinitionLinks =
			cpDefinitionLinkPersistence.findByCPDefinitionId(cpDefinitionId);

		for (CPDefinitionLink cpDefinitionLink : cpDefinitionLinks) {
			cpDefinitionLinkLocalService.deleteCPDefinitionLink(
				cpDefinitionLink);
		}
	}

	@Override
	public void deleteCPDefinitionLinksByCProductId(long cProductId) {
		List<CPDefinitionLink> cpDefinitionLinks =
			cpDefinitionLinkPersistence.findByCProductId(cProductId);

		for (CPDefinitionLink cpDefinitionLink : cpDefinitionLinks) {
			cpDefinitionLinkLocalService.deleteCPDefinitionLink(
				cpDefinitionLink);
		}
	}

	@Override
	public List<CPDefinitionLink> getCPDefinitionLinks(
			long cpDefinitionId, String type)
		throws PortalException {

		return cpDefinitionLinkPersistence.findByCPD_T(cpDefinitionId, type);
	}

	@Override
	public List<CPDefinitionLink> getCPDefinitionLinks(
			long cpDefinitionId, String type, int start, int end,
			OrderByComparator<CPDefinitionLink> orderByComparator)
		throws PortalException {

		return cpDefinitionLinkPersistence.findByCPD_T(
			cpDefinitionId, type, start, end, orderByComparator);
	}

	@Override
	public int getCPDefinitionLinksCount(long cpDefinitionId, String type)
		throws PortalException {

		return cpDefinitionLinkPersistence.countByCPD_T(cpDefinitionId, type);
	}

	@Override
	public List<CPDefinitionLink> getReverseCPDefinitionLinks(
		long cpDefinitionId, String type) {

		return cpDefinitionLinkPersistence.findByCPD_T(cpDefinitionId, type);
	}

	@Override
	public CPDefinitionLink updateCPDefinitionLink(
			long cpDefinitionLinkId, double priority,
			ServiceContext serviceContext)
		throws PortalException {

		CPDefinitionLink cpDefinitionLink =
			cpDefinitionLinkPersistence.findByPrimaryKey(cpDefinitionLinkId);

		if (cpDefinitionLocalService.isPublishedCPDefinition(
				cpDefinitionLink.getCPDefinitionId())) {

			CPDefinition newCPDefinition =
				cpDefinitionLocalService.copyCPDefinition(
					cpDefinitionLink.getCPDefinitionId());

			cpDefinitionLink = cpDefinitionLinkPersistence.findByC_C_T(
				newCPDefinition.getCPDefinitionId(),
				cpDefinitionLink.getCProductId(), cpDefinitionLink.getType());
		}

		cpDefinitionLink.setPriority(priority);
		cpDefinitionLink.setExpandoBridgeAttributes(serviceContext);

		cpDefinitionLinkPersistence.update(cpDefinitionLink);

		reindexCPDefinition(cpDefinitionLink.getCPDefinitionId());
		reindexCPDefinition(cpDefinitionLink.getCProductId());

		return cpDefinitionLink;
	}

	@Override
	public void updateCPDefinitionLinkCProductIds(
			long cpDefinitionId, long[] cProductIds, String type,
			ServiceContext serviceContext)
		throws PortalException {

		if (cProductIds == null) {
			return;
		}

		List<CPDefinitionLink> cpDefinitionLinks = getCPDefinitionLinks(
			cpDefinitionId, type);

		for (CPDefinitionLink cpDefinitionLink : cpDefinitionLinks) {
			if (!ArrayUtil.contains(
					cProductIds, cpDefinitionLink.getCProductId())) {

				cpDefinitionLinkLocalService.deleteCPDefinitionLink(
					cpDefinitionLink);
			}
		}

		CPDefinition cpDefinition = cpDefinitionPersistence.findByPrimaryKey(
			cpDefinitionId);

		for (long cProductId : cProductIds) {
			if (cpDefinition.getCProductId() != cProductId) {
				CPDefinitionLink cpDefinitionLink =
					cpDefinitionLinkPersistence.fetchByC_C_T(
						cpDefinitionId, cProductId, type);

				if (cpDefinitionLink == null) {
					cpDefinitionLinkLocalService.addCPDefinitionLink(
						cpDefinitionId, cProductId, 0, type, serviceContext);
				}
			}
		}

		reindexCPDefinition(cpDefinitionId);
	}

	/**
	 * @deprecated As of Judson (7.1.x)
	 */
	@Deprecated
	@Override
	public void updateCPDefinitionLinks(
			long cpDefinitionId1, long[] cpDefinitionIds2, String type,
			ServiceContext serviceContext)
		throws PortalException {

		if (cpDefinitionIds2 == null) {
			return;
		}

		long[] cProductIds = new long[cpDefinitionIds2.length];

		for (int i = 0; i < cProductIds.length; i++) {
			long cpDefinitionId = cpDefinitionIds2[i];

			CPDefinition cpDefinition =
				cpDefinitionPersistence.findByPrimaryKey(cpDefinitionId);

			cProductIds[i] = cpDefinition.getCProductId();
		}

		cpDefinitionLinkLocalService.updateCPDefinitionLinkCProductIds(
			cpDefinitionId1, cProductIds, type, serviceContext);
	}

	protected void reindexCPDefinition(long cpDefinitionId)
		throws PortalException {

		Indexer<CPDefinition> indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			CPDefinition.class);

		indexer.reindex(CPDefinition.class.getName(), cpDefinitionId);
	}

}