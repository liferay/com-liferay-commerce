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

package com.liferay.commerce.service.impl;

import com.liferay.commerce.configuration.CommerceOrderConfiguration;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.currency.model.CommerceMoney;
import com.liferay.commerce.discount.CommerceDiscountValue;
import com.liferay.commerce.exception.CommerceOrderValidatorException;
import com.liferay.commerce.exception.GuestCartItemMaxAllowedException;
import com.liferay.commerce.internal.search.CommerceOrderItemIndexer;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.model.CommerceWarehouseItem;
import com.liferay.commerce.order.CommerceOrderValidatorRegistry;
import com.liferay.commerce.order.CommerceOrderValidatorResult;
import com.liferay.commerce.price.CommerceProductPrice;
import com.liferay.commerce.price.CommerceProductPriceCalculation;
import com.liferay.commerce.product.exception.NoSuchCPInstanceException;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPDefinitionLocalService;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.commerce.service.base.CommerceOrderItemLocalServiceBaseImpl;
import com.liferay.exportimport.kernel.lar.ExportImportThreadLocal;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author Andrea Di Giorgi
 * @author Alessio Antonio Rendina
 * @author Ethan Bustad
 */
public class CommerceOrderItemLocalServiceImpl
	extends CommerceOrderItemLocalServiceBaseImpl {

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceOrderItem addCommerceOrderItem(
			long commerceOrderId, long cpInstanceId, int quantity,
			int shippedQuantity, String json, CommerceContext commerceContext,
			ServiceContext serviceContext)
		throws PortalException {

		CommerceOrder commerceOrder =
			commerceOrderLocalService.getCommerceOrder(commerceOrderId);
		User user = userLocalService.getUser(serviceContext.getUserId());

		CPDefinition cpDefinition = null;

		CPInstance cpInstance = _cpInstanceLocalService.fetchCPInstance(
			cpInstanceId);

		if (cpInstance != null) {
			cpDefinition = _cpDefinitionLocalService.getCPDefinition(
				cpInstance.getCPDefinitionId());

			if (Validator.isNull(json) || json.equals("[]")) {
				json = cpInstance.getJson();
			}
		}

		validate(
			serviceContext.getLocale(), commerceOrder, cpDefinition, cpInstance,
			quantity);

		CommerceProductPrice commerceProductPrice =
			_commerceProductPriceCalculation.getCommerceProductPrice(
				cpInstanceId, quantity, false, commerceContext);

		CommerceMoney unitPrice = commerceProductPrice.getUnitPrice();
		CommerceMoney finalPrice = commerceProductPrice.getFinalPrice();

		long commerceOrderItemId = counterLocalService.increment();

		CommerceOrderItem commerceOrderItem =
			commerceOrderItemPersistence.create(commerceOrderItemId);

		commerceOrderItem.setGroupId(commerceOrder.getGroupId());
		commerceOrderItem.setCompanyId(user.getCompanyId());
		commerceOrderItem.setUserId(user.getUserId());
		commerceOrderItem.setUserName(user.getFullName());
		commerceOrderItem.setCommerceOrderId(
			commerceOrder.getCommerceOrderId());
		commerceOrderItem.setCProductId(cpDefinition.getCProductId());
		commerceOrderItem.setCPInstanceId(cpInstanceId);
		commerceOrderItem.setQuantity(quantity);
		commerceOrderItem.setShippedQuantity(shippedQuantity);
		commerceOrderItem.setJson(json);
		commerceOrderItem.setUnitPrice(unitPrice.getPrice());
		commerceOrderItem.setFinalPrice(finalPrice.getPrice());
		commerceOrderItem.setNameMap(cpDefinition.getNameMap());
		commerceOrderItem.setSku(cpInstance.getSku());
		commerceOrderItem.setExpandoBridgeAttributes(serviceContext);

		_setCommerceOrderItemDiscountValue(
			commerceOrderItem, commerceProductPrice.getDiscountValue());

		boolean subscription = cpDefinition.isSubscriptionEnabled();

		if (cpInstance.isOverrideSubscriptionInfo()) {
			subscription = cpInstance.isSubscriptionEnabled();
		}

		commerceOrderItem.setSubscription(subscription);

		commerceOrderItemPersistence.update(commerceOrderItem);

		commerceOrderLocalService.recalculatePrice(
			commerceOrderItem.getCommerceOrderId(), commerceContext);

		return commerceOrderItem;
	}

	/**
	 * @return
	 * @deprecated As of Mueller (7.2.x), use deleteCommerceOrderItem(CommerceOrderItem, CommerceContext)
	 */
	@Deprecated
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CommerceOrderItem deleteCommerceOrderItem(
			CommerceOrderItem commerceOrderItem)
		throws PortalException {

		// Commerce order item

		commerceOrderItemPersistence.remove(commerceOrderItem);

		// Expando

		expandoRowLocalService.deleteRows(
			commerceOrderItem.getCommerceOrderItemId());

		return commerceOrderItem;
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	public CommerceOrderItem deleteCommerceOrderItem(
			CommerceOrderItem commerceOrderItem,
			CommerceContext commerceContext)
		throws PortalException {

		// Commerce order item

		commerceOrderItemPersistence.remove(commerceOrderItem);

		// Expando

		expandoRowLocalService.deleteRows(
			commerceOrderItem.getCommerceOrderItemId());

		commerceOrderLocalService.recalculatePrice(
			commerceOrderItem.getCommerceOrderId(), commerceContext);

		return commerceOrderItem;
	}

	@Override
	public CommerceOrderItem deleteCommerceOrderItem(long commerceOrderItemId)
		throws PortalException {

		CommerceOrderItem commerceOrderItem =
			commerceOrderItemPersistence.findByPrimaryKey(commerceOrderItemId);

		return commerceOrderItemLocalService.deleteCommerceOrderItem(
			commerceOrderItem);
	}

	@Override
	public void deleteCommerceOrderItems(long commerceOrderId)
		throws PortalException {

		List<CommerceOrderItem> commerceOrderItems =
			commerceOrderItemPersistence.findByCommerceOrderId(
				commerceOrderId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (CommerceOrderItem commerceOrderItem : commerceOrderItems) {
			commerceOrderItemLocalService.deleteCommerceOrderItem(
				commerceOrderItem);
		}
	}

	@Override
	public void deleteCommerceOrderItemsByCPInstanceId(long cpInstanceId)
		throws PortalException {

		List<CommerceOrderItem> commerceOrderItems =
			commerceOrderItemPersistence.findByCPInstanceId(cpInstanceId);

		for (CommerceOrderItem commerceOrderItem : commerceOrderItems) {
			deleteCommerceOrderItem(commerceOrderItem);
		}
	}

	@Override
	public CommerceOrderItem fetchByExternalReferenceCode(
		long companyId, String externalReferenceCode) {

		return commerceOrderItemPersistence.fetchByC_ERC(
			companyId, externalReferenceCode);
	}

	@Override
	public List<CommerceOrderItem> getAvailableForShipmentCommerceOrderItems(
		long commerceOrderId) {

		return commerceOrderItemFinder.findByAvailableQuantity(commerceOrderId);
	}

	@Override
	public List<CommerceOrderItem> getCommerceOrderItems(
		long commerceOrderId, int start, int end) {

		return commerceOrderItemPersistence.findByCommerceOrderId(
			commerceOrderId, start, end);
	}

	@Override
	public List<CommerceOrderItem> getCommerceOrderItems(
		long commerceOrderId, int start, int end,
		OrderByComparator<CommerceOrderItem> orderByComparator) {

		return commerceOrderItemPersistence.findByCommerceOrderId(
			commerceOrderId, start, end, orderByComparator);
	}

	@Override
	public List<CommerceOrderItem> getCommerceOrderItems(
		long commerceOrderId, long cpInstanceId, int start, int end) {

		return commerceOrderItemPersistence.findByC_I(
			commerceOrderId, cpInstanceId, start, end);
	}

	@Override
	public List<CommerceOrderItem> getCommerceOrderItems(
		long commerceOrderId, long cpInstanceId, int start, int end,
		OrderByComparator<CommerceOrderItem> orderByComparator) {

		return commerceOrderItemPersistence.findByC_I(
			commerceOrderId, cpInstanceId, start, end, orderByComparator);
	}

	@Override
	public int getCommerceOrderItemsCount(long commerceOrderId) {
		return commerceOrderItemPersistence.countByCommerceOrderId(
			commerceOrderId);
	}

	@Override
	public int getCommerceOrderItemsQuantity(long commerceOrderId) {
		return commerceOrderItemFinder.getCommerceOrderItemsQuantity(
			commerceOrderId);
	}

	@Override
	public int getCommerceWarehouseItemQuantity(
			long commerceOrderItemId, long commerceWarehouseId)
		throws PortalException {

		CommerceOrderItem commerceOrderItem =
			commerceOrderItemPersistence.findByPrimaryKey(commerceOrderItemId);

		CommerceWarehouseItem commerceWarehouseItem =
			commerceWarehouseItemLocalService.fetchCommerceWarehouseItem(
				commerceWarehouseId, commerceOrderItem.getCPInstanceId());

		if (commerceWarehouseItem == null) {
			return 0;
		}

		return commerceWarehouseItem.getQuantity();
	}

	@Override
	public int getCPInstanceQuantity(long cpInstanceId, int orderStatus) {
		return commerceOrderItemFinder.getCPInstanceQuantity(
			cpInstanceId, orderStatus);
	}

	@Override
	public List<CommerceOrderItem> getSubscriptionCommerceOrderItems(
		long commerceOrderId) {

		return commerceOrderItemPersistence.findByC_S(commerceOrderId, true);
	}

	@Override
	public CommerceOrderItem incrementShippedQuantity(
			long commerceOrderItemId, int shippedQuantity)
		throws PortalException {

		CommerceOrderItem commerceOrderItem =
			commerceOrderItemPersistence.findByPrimaryKey(commerceOrderItemId);

		shippedQuantity =
			commerceOrderItem.getShippedQuantity() + shippedQuantity;

		commerceOrderItem.setShippedQuantity(shippedQuantity);

		return commerceOrderItemPersistence.update(commerceOrderItem);
	}

	@Override
	public BaseModelSearchResult<CommerceOrderItem> search(
			long commerceOrderId, String keywords, int start, int end,
			Sort sort)
		throws PortalException {

		SearchContext searchContext = buildSearchContext(
			commerceOrderId, start, end, sort);

		searchContext.setKeywords(keywords);

		return searchCommerceOrderItems(searchContext);
	}

	@Override
	public BaseModelSearchResult<CommerceOrderItem> search(
			long commerceOrderId, String sku, String name, boolean andOperator,
			int start, int end, Sort sort)
		throws PortalException {

		SearchContext searchContext = buildSearchContext(
			commerceOrderId, start, end, sort);

		searchContext.setAndSearch(andOperator);
		searchContext.setAttribute(CommerceOrderItemIndexer.FIELD_SKU, sku);
		searchContext.setAttribute(Field.NAME, name);

		return searchCommerceOrderItems(searchContext);
	}

	@Override
	public CommerceOrderItem updateCommerceOrderItem(
			long commerceOrderItemId, int quantity,
			CommerceContext commerceContext, ServiceContext serviceContext)
		throws PortalException {

		CommerceOrderItem commerceOrderItem =
			commerceOrderItemPersistence.findByPrimaryKey(commerceOrderItemId);

		return commerceOrderItemLocalService.updateCommerceOrderItem(
			commerceOrderItemId, quantity, commerceOrderItem.getJson(),
			commerceContext, serviceContext);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceOrderItem updateCommerceOrderItem(
			long commerceOrderItemId, int quantity, String json,
			CommerceContext commerceContext, ServiceContext serviceContext)
		throws PortalException {

		CommerceOrderItem commerceOrderItem =
			commerceOrderItemPersistence.findByPrimaryKey(commerceOrderItemId);

		CommerceProductPrice commerceProductPrice =
			_commerceProductPriceCalculation.getCommerceProductPrice(
				commerceOrderItem.getCPInstanceId(), quantity, false,
				commerceContext);

		CommerceMoney unitPrice = commerceProductPrice.getUnitPrice();
		CommerceMoney finalPrice = commerceProductPrice.getFinalPrice();

		validate(
			serviceContext.getLocale(), commerceOrderItem.getCommerceOrder(),
			commerceOrderItem.getCPDefinition(),
			commerceOrderItem.getCPInstance(), quantity);

		commerceOrderItem.setQuantity(quantity);
		commerceOrderItem.setJson(json);
		commerceOrderItem.setUnitPrice(unitPrice.getPrice());
		commerceOrderItem.setFinalPrice(finalPrice.getPrice());
		commerceOrderItem.setExpandoBridgeAttributes(serviceContext);

		_setCommerceOrderItemDiscountValue(
			commerceOrderItem, commerceProductPrice.getDiscountValue());

		commerceOrderItemPersistence.update(commerceOrderItem);

		commerceOrderLocalService.recalculatePrice(
			commerceOrderItem.getCommerceOrderId(), commerceContext);

		return commerceOrderItem;
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceOrderItem updateCommerceOrderItemPrice(
			long commerceOrderItemId, CommerceContext commerceContext)
		throws PortalException {

		CommerceOrderItem commerceOrderItem =
			commerceOrderItemPersistence.findByPrimaryKey(commerceOrderItemId);

		CommerceProductPrice commerceProductPrice =
			_commerceProductPriceCalculation.getCommerceProductPrice(
				commerceOrderItem.getCPInstanceId(),
				commerceOrderItem.getQuantity(), false, commerceContext);

		CommerceMoney unitPrice = commerceProductPrice.getUnitPrice();
		CommerceMoney finalPrice = commerceProductPrice.getFinalPrice();

		commerceOrderItem.setUnitPrice(unitPrice.getPrice());
		commerceOrderItem.setFinalPrice(finalPrice.getPrice());

		_setCommerceOrderItemDiscountValue(
			commerceOrderItem, commerceProductPrice.getDiscountValue());

		commerceOrderItemPersistence.update(commerceOrderItem);

		return commerceOrderItem;
	}

	@Override
	public CommerceOrderItem upsertCommerceOrderItem(
			long commerceOrderId, long cpInstanceId, int quantity,
			int shippedQuantity, String json, CommerceContext commerceContext,
			ServiceContext serviceContext)
		throws PortalException {

		List<CommerceOrderItem> commerceOrderItems = getCommerceOrderItems(
			commerceOrderId, cpInstanceId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS);

		if (!commerceOrderItems.isEmpty()) {
			CommerceOrderItem commerceOrderItem = commerceOrderItems.get(0);

			return commerceOrderItemLocalService.updateCommerceOrderItem(
				commerceOrderItem.getCommerceOrderItemId(),
				commerceOrderItem.getQuantity() + quantity,
				commerceOrderItem.getJson(), commerceContext, serviceContext);
		}

		return addCommerceOrderItem(
			commerceOrderId, cpInstanceId, quantity, 0, json, commerceContext,
			serviceContext);
	}

	protected SearchContext buildSearchContext(
			long commerceOrderId, int start, int end, Sort sort)
		throws PortalException {

		SearchContext searchContext = new SearchContext();

		CommerceOrder commerceOrder =
			commerceOrderLocalService.getCommerceOrder(commerceOrderId);

		searchContext.setAttribute(
			CommerceOrderItemIndexer.FIELD_COMMERCE_ORDER_ID, commerceOrderId);
		searchContext.setCompanyId(commerceOrder.getCompanyId());
		searchContext.setEnd(end);

		QueryConfig queryConfig = searchContext.getQueryConfig();

		queryConfig.setHighlightEnabled(false);
		queryConfig.setScoreEnabled(false);

		if (sort != null) {
			searchContext.setSorts(sort);
		}

		searchContext.setStart(start);

		return searchContext;
	}

	protected List<CommerceOrderItem> getCommerceOrderItems(Hits hits)
		throws PortalException {

		List<Document> documents = hits.toList();

		List<CommerceOrderItem> commerceOrderItems = new ArrayList<>(
			documents.size());

		for (Document document : documents) {
			long commerceOrderItemId = GetterUtil.getLong(
				document.get(Field.ENTRY_CLASS_PK));

			CommerceOrderItem commerceOrderItem = fetchCommerceOrderItem(
				commerceOrderItemId);

			if (commerceOrderItem == null) {
				commerceOrderItems = null;

				Indexer<CommerceOrderItem> indexer =
					IndexerRegistryUtil.getIndexer(CommerceOrderItem.class);

				long companyId = GetterUtil.getLong(
					document.get(Field.COMPANY_ID));

				indexer.delete(companyId, document.getUID());
			}
			else if (commerceOrderItems != null) {
				commerceOrderItems.add(commerceOrderItem);
			}
		}

		return commerceOrderItems;
	}

	protected BaseModelSearchResult<CommerceOrderItem> searchCommerceOrderItems(
			SearchContext searchContext)
		throws PortalException {

		Indexer<CommerceOrderItem> indexer =
			IndexerRegistryUtil.nullSafeGetIndexer(CommerceOrderItem.class);

		for (int i = 0; i < 10; i++) {
			Hits hits = indexer.search(searchContext, _SELECTED_FIELD_NAMES);

			List<CommerceOrderItem> commerceOrderItems = getCommerceOrderItems(
				hits);

			if (commerceOrderItems != null) {
				return new BaseModelSearchResult<>(
					commerceOrderItems, hits.getLength());
			}
		}

		throw new SearchException(
			"Unable to fix the search index after 10 attempts");
	}

	protected void validate(
			Locale locale, CommerceOrder commerceOrder,
			CPDefinition cpDefinition, CPInstance cpInstance, int quantity)
		throws PortalException {

		if (commerceOrder.getUserId() == 0) {
			int count = commerceOrderItemPersistence.countByCommerceOrderId(
				commerceOrder.getCommerceOrderId());

			if (count >=
					_commerceOrderConfiguration.guestCartItemMaxAllowed()) {

				throw new GuestCartItemMaxAllowedException();
			}
		}

		if ((cpDefinition != null) && (cpInstance != null) &&
			(cpDefinition.getCPDefinitionId() !=
				cpInstance.getCPDefinitionId())) {

			throw new NoSuchCPInstanceException(
				StringBundler.concat(
					"CPInstance ", cpInstance.getCPInstanceId(),
					" belongs to a different CPDefinition than ",
					cpDefinition.getCPDefinitionId()));
		}

		if (!ExportImportThreadLocal.isImportInProcess()) {
			List<CommerceOrderValidatorResult> commerceCartValidatorResults =
				_commerceOrderValidatorRegistry.validate(
					locale, commerceOrder, cpInstance, quantity);

			if (!commerceCartValidatorResults.isEmpty()) {
				throw new CommerceOrderValidatorException(
					commerceCartValidatorResults);
			}
		}
	}

	private void _setCommerceOrderItemDiscountValue(
		CommerceOrderItem commerceOrderItem,
		CommerceDiscountValue commerceDiscountValue) {

		BigDecimal discountAmount = BigDecimal.ZERO;
		BigDecimal discountPercentageLevel1 = BigDecimal.ZERO;
		BigDecimal discountPercentageLevel2 = BigDecimal.ZERO;
		BigDecimal discountPercentageLevel3 = BigDecimal.ZERO;
		BigDecimal discountPercentageLevel4 = BigDecimal.ZERO;

		if (commerceDiscountValue != null) {
			CommerceMoney discountAmountCommerceMoney =
				commerceDiscountValue.getDiscountAmount();

			discountAmount = discountAmountCommerceMoney.getPrice();

			BigDecimal[] percentages = commerceDiscountValue.getPercentages();

			if (percentages.length >= 1) {
				discountPercentageLevel1 = percentages[0];
			}

			if (percentages.length >= 2) {
				discountPercentageLevel1 = percentages[1];
			}

			if (percentages.length >= 3) {
				discountPercentageLevel1 = percentages[2];
			}

			if (percentages.length >= 4) {
				discountPercentageLevel1 = percentages[3];
			}
		}

		commerceOrderItem.setDiscountAmount(discountAmount);
		commerceOrderItem.setDiscountPercentageLevel1(discountPercentageLevel1);
		commerceOrderItem.setDiscountPercentageLevel2(discountPercentageLevel2);
		commerceOrderItem.setDiscountPercentageLevel3(discountPercentageLevel3);
		commerceOrderItem.setDiscountPercentageLevel4(discountPercentageLevel4);
	}

	private static final String[] _SELECTED_FIELD_NAMES = {
		Field.ENTRY_CLASS_PK, Field.COMPANY_ID
	};

	@ServiceReference(type = CommerceOrderConfiguration.class)
	private CommerceOrderConfiguration _commerceOrderConfiguration;

	@ServiceReference(type = CommerceOrderValidatorRegistry.class)
	private CommerceOrderValidatorRegistry _commerceOrderValidatorRegistry;

	@ServiceReference(type = CommerceProductPriceCalculation.class)
	private CommerceProductPriceCalculation _commerceProductPriceCalculation;

	@ServiceReference(type = CPDefinitionLocalService.class)
	private CPDefinitionLocalService _cpDefinitionLocalService;

	@ServiceReference(type = CPInstanceLocalService.class)
	private CPInstanceLocalService _cpInstanceLocalService;

}