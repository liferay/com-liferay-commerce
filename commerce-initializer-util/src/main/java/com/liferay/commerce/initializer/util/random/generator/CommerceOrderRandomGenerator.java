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

package com.liferay.commerce.initializer.util.random.generator;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.model.CommerceAccountUserRel;
import com.liferay.commerce.account.service.CommerceAccountLocalService;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.context.CommerceContextFactory;
import com.liferay.commerce.inventory.CPDefinitionInventoryEngine;
import com.liferay.commerce.inventory.CPDefinitionInventoryEngineRegistry;
import com.liferay.commerce.model.CPDefinitionInventory;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.product.catalog.CPCatalogEntry;
import com.liferay.commerce.product.catalog.CPQuery;
import com.liferay.commerce.product.catalog.CPSku;
import com.liferay.commerce.product.data.source.CPDataSourceResult;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.commerce.product.util.CPDefinitionHelper;
import com.liferay.commerce.service.CPDefinitionInventoryLocalService;
import com.liferay.commerce.service.CommerceOrderItemLocalService;
import com.liferay.commerce.service.CommerceOrderLocalService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.Serializable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(service = CommerceOrderRandomGenerator.class)
public class CommerceOrderRandomGenerator {

	public void generate(long groupId, int ordersCount) throws PortalException {
		CPDataSourceResult cpDataSourceResult = _cpDefinitionHelper.search(
			groupId, _getSearchContext(groupId), new CPQuery(),
			QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		if (cpDataSourceResult.getLength() <= 0) {
			if (_log.isInfoEnabled()) {
				_log.info("There are no products");
			}

			return;
		}

		for (int i = 0; i < ordersCount; i++) {
			List<CPCatalogEntry> cpCatalogEntries = _getCPCatalogEntries(
				cpDataSourceResult,
				_getRandomPosition(cpDataSourceResult.getLength()),
				_getRandomPosition(cpDataSourceResult.getLength()));

			_generateCommerceOrder(groupId, cpCatalogEntries);
		}
	}

	private void _generateCommerceOrder(
			long groupId, List<CPCatalogEntry> cpCatalogEntries)
		throws PortalException {

		CommerceAccount commerceAccount = _getRandomCommerceAccount();

		List<CommerceAccountUserRel> commerceAccountUserRels =
			commerceAccount.getCommerceAccountUserRels();

		CommerceAccountUserRel commerceAccountUserRel =
			commerceAccountUserRels.get(0);

		CommerceOrder commerceOrder =
			_commerceOrderLocalService.addCommerceOrder(
				groupId, commerceAccountUserRel.getCommerceAccountUserId(),
				commerceAccountUserRel.getCommerceAccountId());

		CommerceContext commerceContext = _commerceContextFactory.create(
			groupId, commerceAccountUserRel.getCommerceAccountUserId(),
			commerceOrder.getCommerceOrderId(),
			commerceAccountUserRel.getCommerceAccountId());

		ServiceContext serviceContext = _getServiceContext(commerceOrder);

		_generateCommerceOrderItems(
			commerceOrder.getCommerceOrderId(), cpCatalogEntries,
			commerceContext, serviceContext);

		_commerceOrderLocalService.checkoutCommerceOrder(
			commerceOrder.getCommerceOrderId(), commerceContext,
			serviceContext);
	}

	private void _generateCommerceOrderItems(
			long commerceOrderId, List<CPCatalogEntry> cpCatalogEntries,
			CommerceContext commerceContext, ServiceContext serviceContext)
		throws PortalException {

		for (CPCatalogEntry cpCatalogEntry : cpCatalogEntries) {
			CPSku cpSku = _getRandomCPSku(cpCatalogEntry);

			if (cpSku == null) {
				continue;
			}

			CPInstance cpInstance = _cpInstanceLocalService.getCPInstance(
				cpSku.getCPInstanceId());

			CPDefinitionInventory cpDefinitionInventory =
				_cpDefinitionInventoryLocalService.
					fetchCPDefinitionInventoryByCPDefinitionId(
						cpInstance.getCPDefinitionId());

			CPDefinitionInventoryEngine cpDefinitionInventoryEngine =
				_cpDefinitionInventoryEngineRegistry.
					getCPDefinitionInventoryEngine(cpDefinitionInventory);

			_commerceOrderItemLocalService.addCommerceOrderItem(
				commerceOrderId, cpInstance.getCPInstanceId(),
				RandomTestUtil.randomInt(
					cpDefinitionInventoryEngine.getMinOrderQuantity(cpInstance),
					_getMaxOrderQuantity(
						cpInstance, cpDefinitionInventoryEngine)),
				0, cpInstance.getJson(), commerceContext, serviceContext);
		}
	}

	private List<CPCatalogEntry> _getCPCatalogEntries(
		CPDataSourceResult cpDataSourceResult, int start, int end) {

		if (start > end) {
			end = start;
		}

		List<CPCatalogEntry> cpCatalogEntries =
			cpDataSourceResult.getCPCatalogEntries();

		return cpCatalogEntries.subList(start, end);
	}

	private int _getMaxOrderQuantity(
			CPInstance cpInstance,
			CPDefinitionInventoryEngine cpDefinitionInventoryEngine)
		throws PortalException {

		int stockQuantity = cpDefinitionInventoryEngine.getStockQuantity(
			cpInstance);
		int maxOrderQuantity = cpDefinitionInventoryEngine.getMaxOrderQuantity(
			cpInstance);

		if (stockQuantity < maxOrderQuantity) {
			return stockQuantity;
		}

		return maxOrderQuantity;
	}

	private CommerceAccount _getRandomCommerceAccount() {
		List<CommerceAccount> commerceAccounts =
			_commerceAccountLocalService.getCommerceAccounts(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		return commerceAccounts.get(
			_getRandomPosition(commerceAccounts.size()));
	}

	private CPSku _getRandomCPSku(CPCatalogEntry cpCatalogEntry) {
		return _getRandomCPSku(cpCatalogEntry, 1);
	}

	private CPSku _getRandomCPSku(CPCatalogEntry cpCatalogEntry, int attempt) {
		if (attempt > 10) {
			if (_log.isInfoEnabled()) {
				_log.info(
					"Unable to find an active SKU after 10 attempts for " +
						"CPDefinition with ID: " +
							cpCatalogEntry.getCPDefinitionId());
			}

			return null;
		}

		List<CPSku> cpSkus = cpCatalogEntry.getCPSkus();

		CPSku cpSku = cpSkus.get(_getRandomPosition(cpSkus.size()));

		if (cpSku.isPublished() && cpSku.isPurchasable()) {
			return cpSku;
		}

		return _getRandomCPSku(cpCatalogEntry, attempt++);
	}

	private int _getRandomPosition(int max) {
		return RandomTestUtil.randomInt(0, max);
	}

	private SearchContext _getSearchContext(long groupId)
		throws PortalException {

		SearchContext searchContext = new SearchContext();

		Map<String, Serializable> attributes = new HashMap<>();

		attributes.put(Field.STATUS, WorkflowConstants.STATUS_APPROVED);

		searchContext.setAttributes(attributes);

		Group group = _groupLocalService.getGroup(groupId);

		searchContext.setCompanyId(group.getCompanyId());
		searchContext.setGroupIds(new long[] {group.getGroupId()});

		return searchContext;
	}

	private ServiceContext _getServiceContext(CommerceOrder commerceOrder) {
		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(commerceOrder.getCompanyId());
		serviceContext.setScopeGroupId(commerceOrder.getGroupId());
		serviceContext.setUserId(commerceOrder.getUserId());

		return serviceContext;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceOrderRandomGenerator.class);

	@Reference
	private CommerceAccountLocalService _commerceAccountLocalService;

	@Reference
	private CommerceContextFactory _commerceContextFactory;

	@Reference
	private CommerceOrderItemLocalService _commerceOrderItemLocalService;

	@Reference
	private CommerceOrderLocalService _commerceOrderLocalService;

	@Reference
	private CPDefinitionHelper _cpDefinitionHelper;

	@Reference
	private CPDefinitionInventoryEngineRegistry
		_cpDefinitionInventoryEngineRegistry;

	@Reference
	private CPDefinitionInventoryLocalService
		_cpDefinitionInventoryLocalService;

	@Reference
	private CPInstanceLocalService _cpInstanceLocalService;

	@Reference
	private GroupLocalService _groupLocalService;

}