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

import com.liferay.commerce.exception.CommerceAddressCityException;
import com.liferay.commerce.exception.CommerceAddressCountryException;
import com.liferay.commerce.exception.CommerceAddressNameException;
import com.liferay.commerce.exception.CommerceAddressStreetException;
import com.liferay.commerce.exception.CommerceAddressZipException;
import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.model.CommerceGeocoder;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.service.base.CommerceAddressLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
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
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Andrea Di Giorgi
 * @author Alec Sloan
 */
public class CommerceAddressLocalServiceImpl
	extends CommerceAddressLocalServiceBaseImpl {

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceAddress addCommerceAddress(
			String className, long classPK, String name, String description,
			String street1, String street2, String street3, String city,
			String zip, long commerceRegionId, long commerceCountryId,
			String phoneNumber, boolean defaultBilling, boolean defaultShipping,
			ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(serviceContext.getUserId());

		long companyId = user.getCompanyId();

		validate(
			0, companyId, className, classPK, name, street1, city, zip,
			commerceCountryId, defaultBilling, defaultShipping);

		long commerceAddressId = counterLocalService.increment();

		CommerceAddress commerceAddress = commerceAddressPersistence.create(
			commerceAddressId);

		commerceAddress.setCompanyId(companyId);
		commerceAddress.setUserId(user.getUserId());
		commerceAddress.setUserName(user.getFullName());
		commerceAddress.setClassName(className);
		commerceAddress.setClassPK(classPK);
		commerceAddress.setName(name);
		commerceAddress.setDescription(description);
		commerceAddress.setStreet1(street1);
		commerceAddress.setStreet2(street2);
		commerceAddress.setStreet3(street3);
		commerceAddress.setCity(city);
		commerceAddress.setZip(zip);
		commerceAddress.setCommerceRegionId(commerceRegionId);
		commerceAddress.setCommerceCountryId(commerceCountryId);
		commerceAddress.setPhoneNumber(phoneNumber);
		commerceAddress.setDefaultBilling(defaultBilling);
		commerceAddress.setDefaultShipping(defaultShipping);

		commerceAddressPersistence.update(commerceAddress);

		return commerceAddress;
	}

	@Override
	public CommerceAddress copyCommerceAddress(
			long commerceAddressId, String className, long classPK,
			ServiceContext serviceContext)
		throws PortalException {

		CommerceAddress commerceAddress =
			commerceAddressPersistence.findByPrimaryKey(commerceAddressId);

		return commerceAddressLocalService.addCommerceAddress(
			className, classPK, commerceAddress.getName(),
			commerceAddress.getDescription(), commerceAddress.getStreet1(),
			commerceAddress.getStreet2(), commerceAddress.getStreet3(),
			commerceAddress.getCity(), commerceAddress.getZip(),
			commerceAddress.getCommerceRegionId(),
			commerceAddress.getCommerceCountryId(),
			commerceAddress.getPhoneNumber(), false, false, serviceContext);
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	public CommerceAddress deleteCommerceAddress(
			CommerceAddress commerceAddress)
		throws PortalException {

		// Commerce address

		commerceAddressPersistence.remove(commerceAddress);

		// Commerce orders

		List<CommerceOrder> commerceOrders =
			commerceOrderLocalService.getCommerceOrdersByBillingAddress(
				commerceAddress.getCommerceAddressId());

		removeCommerceOrderAddresses(
			commerceOrders, commerceAddress.getCommerceAddressId());

		commerceOrders =
			commerceOrderLocalService.getCommerceOrdersByShippingAddress(
				commerceAddress.getCommerceAddressId());

		removeCommerceOrderAddresses(
			commerceOrders, commerceAddress.getCommerceAddressId());

		return commerceAddress;
	}

	@Override
	public CommerceAddress deleteCommerceAddress(long commerceAddressId)
		throws PortalException {

		CommerceAddress commerceAddress =
			commerceAddressPersistence.findByPrimaryKey(commerceAddressId);

		return commerceAddressLocalService.deleteCommerceAddress(
			commerceAddress);
	}

	@Override
	public void deleteCommerceAddresses(String className, long classPK)
		throws PortalException {

		List<CommerceAddress> commerceAddresses =
			commerceAddressPersistence.findByC_C(
				classNameLocalService.getClassNameId(className), classPK);

		for (CommerceAddress commerceAddress : commerceAddresses) {
			commerceAddressLocalService.deleteCommerceAddress(commerceAddress);
		}
	}

	@Override
	public void deleteCountryCommerceAddresses(long commerceCountryId)
		throws PortalException {

		List<CommerceAddress> commerceAddresses =
			commerceAddressPersistence.findByCommerceCountryId(
				commerceCountryId);

		for (CommerceAddress commerceAddress : commerceAddresses) {
			commerceAddressLocalService.deleteCommerceAddress(commerceAddress);
		}
	}

	@Override
	public void deleteRegionCommerceAddresses(long commerceRegionId)
		throws PortalException {

		List<CommerceAddress> commerceAddresses =
			commerceAddressPersistence.findByCommerceRegionId(commerceRegionId);

		for (CommerceAddress commerceAddress : commerceAddresses) {
			commerceAddressLocalService.deleteCommerceAddress(commerceAddress);
		}
	}

	@Override
	public CommerceAddress fetchDefaultBillingCommerceAddress(
		long companyId, String className, long classPK) {

		List<CommerceAddress> commerceAddresses =
			commerceAddressPersistence.findByC_C_C_DB(
				companyId, classNameLocalService.getClassNameId(className),
				classPK, true);

		if (commerceAddresses.isEmpty()) {
			return null;
		}

		return commerceAddresses.get(0);
	}

	@Override
	public CommerceAddress fetchDefaultShippingCommerceAddress(
		long companyId, String className, long classPK) {

		List<CommerceAddress> commerceAddresses =
			commerceAddressPersistence.findByC_C_C_DS(
				companyId, classNameLocalService.getClassNameId(className),
				classPK, true);

		if (commerceAddresses.isEmpty()) {
			return null;
		}

		return commerceAddresses.get(0);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceAddress geolocateCommerceAddress(long commerceAddressId)
		throws PortalException {

		CommerceAddress commerceAddress =
			commerceAddressPersistence.findByPrimaryKey(commerceAddressId);

		double[] coordinates = _commerceGeocoder.getCoordinates(
			commerceAddress.getStreet1(), commerceAddress.getCity(),
			commerceAddress.getZip(), commerceAddress.getCommerceRegion(),
			commerceAddress.getCommerceCountry());

		commerceAddress.setLatitude(coordinates[0]);
		commerceAddress.setLongitude(coordinates[1]);

		return commerceAddressPersistence.update(commerceAddress);
	}

	@Override
	public List<CommerceAddress> getAvailableBillingCommerceAddresses(
		long companyId, String className, long classPK) {

		List<CommerceAddress> commerceAddresses =
			commerceAddressPersistence.findByC_C_C_DS(
				companyId, classNameLocalService.getClassNameId(className),
				classPK, false);

		CommerceAddress defaultBillingAddress =
			fetchDefaultBillingCommerceAddress(companyId, className, classPK);

		if ((defaultBillingAddress != null) &&
			!commerceAddresses.contains(defaultBillingAddress)) {

			if (commerceAddresses.isEmpty()) {
				commerceAddresses = new ArrayList<>();
			}

			commerceAddresses.add(defaultBillingAddress);
		}

		return commerceAddresses;
	}

	@Override
	public List<CommerceAddress> getAvailableShippingCommerceAddresses(
		long companyId, String className, long classPK) {

		List<CommerceAddress> commerceAddresses =
			commerceAddressPersistence.findByC_C_C_DB(
				companyId, classNameLocalService.getClassNameId(className),
				classPK, false);

		CommerceAddress defaultShippingAddress =
			fetchDefaultShippingCommerceAddress(companyId, className, classPK);

		if ((defaultShippingAddress != null) &&
			!commerceAddresses.contains(defaultShippingAddress)) {

			if (commerceAddresses.isEmpty()) {
				commerceAddresses = new ArrayList<>();
			}

			commerceAddresses.add(defaultShippingAddress);
		}

		return commerceAddresses;
	}

	/**
	 * @deprecated As of Mueller (7.2.x), commerceAddress is scoped to Company use *ByCompanyId
	 */
	@Deprecated
	@Override
	public List<CommerceAddress> getCommerceAddresses(
		long groupId, String className, long classPK) {

		Group group = _groupLocalService.fetchGroup(groupId);

		if (group == null) {
			return new ArrayList<>();
		}

		return commerceAddressPersistence.findByC_C_C(
			group.getCompanyId(),
			classNameLocalService.getClassNameId(className), classPK);
	}

	/**
	 * @deprecated As of Mueller (7.2.x), commerceAddress is scoped to Company use *ByCompanyId
	 */
	@Deprecated
	@Override
	public List<CommerceAddress> getCommerceAddresses(
		long groupId, String className, long classPK, int start, int end,
		OrderByComparator<CommerceAddress> orderByComparator) {

		Group group = _groupLocalService.fetchGroup(groupId);

		if (group == null) {
			return new ArrayList<>();
		}

		return commerceAddressPersistence.findByC_C_C(
			group.getCompanyId(),
			classNameLocalService.getClassNameId(className), classPK, start,
			end, orderByComparator);
	}

	@Override
	public List<CommerceAddress> getCommerceAddresses(
		String className, long classPK, int start, int end,
		OrderByComparator<CommerceAddress> orderByComparator) {

		return commerceAddressPersistence.findByC_C(
			classNameLocalService.getClassNameId(className), classPK, start,
			end, orderByComparator);
	}

	@Override
	public List<CommerceAddress> getCommerceAddressesByCompanyId(
		long companyId, String className, long classPK) {

		return commerceAddressPersistence.findByC_C_C(
			companyId, classNameLocalService.getClassNameId(className),
			classPK);
	}

	@Override
	public List<CommerceAddress> getCommerceAddressesByCompanyId(
		long companyId, String className, long classPK, int start, int end,
		OrderByComparator<CommerceAddress> orderByComparator) {

		return commerceAddressPersistence.findByC_C_C(
			companyId, classNameLocalService.getClassNameId(className), classPK,
			start, end, orderByComparator);
	}

	/**
	 * @deprecated As of Mueller (7.2.x), commerceAddress is scoped to Company use *ByCompanyId
	 */
	@Deprecated
	@Override
	public int getCommerceAddressesCount(
		long groupId, String className, long classPK) {

		Group group = _groupLocalService.fetchGroup(groupId);

		if (group == null) {
			return 0;
		}

		return commerceAddressPersistence.countByC_C_C(
			group.getCompanyId(),
			classNameLocalService.getClassNameId(className), classPK);
	}

	@Override
	public int getCommerceAddressesCount(String className, long classPK) {
		return commerceAddressPersistence.countByC_C(
			classNameLocalService.getClassNameId(className), classPK);
	}

	@Override
	public int getCommerceAddressesCountByCompanyId(
		long companyId, String className, long classPK) {

		return commerceAddressPersistence.countByC_C_C(
			companyId, classNameLocalService.getClassNameId(className),
			classPK);
	}

	/**
	 * @deprecated As of Mueller (7.2.x), commerceAddress is scoped to Company. Don't need to pass groupId
	 */
	@Deprecated
	@Override
	public BaseModelSearchResult<CommerceAddress> searchCommerceAddresses(
			long companyId, long groupId, String className, long classPK,
			String keywords, int start, int end, Sort sort)
		throws PortalException {

		SearchContext searchContext = buildSearchContext(
			companyId, className, classPK, keywords, start, end, sort);

		return searchCommerceAddresses(searchContext);
	}

	@Override
	public BaseModelSearchResult<CommerceAddress> searchCommerceAddresses(
			long companyId, String className, long classPK, String keywords,
			int start, int end, Sort sort)
		throws PortalException {

		SearchContext searchContext = buildSearchContext(
			companyId, className, classPK, keywords, start, end, sort);

		return searchCommerceAddresses(searchContext);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceAddress updateCommerceAddress(
			long commerceAddressId, String name, String description,
			String street1, String street2, String street3, String city,
			String zip, long commerceRegionId, long commerceCountryId,
			String phoneNumber, boolean defaultBilling, boolean defaultShipping,
			ServiceContext serviceContext)
		throws PortalException {

		// Commerce address

		CommerceAddress commerceAddress =
			commerceAddressPersistence.findByPrimaryKey(commerceAddressId);

		validate(
			commerceAddress.getCommerceAddressId(),
			commerceAddress.getCompanyId(), commerceAddress.getClassName(),
			commerceAddress.getClassPK(), name, street1, city, zip,
			commerceCountryId, defaultBilling, defaultShipping);

		commerceAddress.setName(name);
		commerceAddress.setDescription(description);
		commerceAddress.setStreet1(street1);
		commerceAddress.setStreet2(street2);
		commerceAddress.setStreet3(street3);
		commerceAddress.setCity(city);
		commerceAddress.setZip(zip);
		commerceAddress.setCommerceRegionId(commerceRegionId);
		commerceAddress.setCommerceCountryId(commerceCountryId);
		commerceAddress.setLatitude(0);
		commerceAddress.setLongitude(0);
		commerceAddress.setPhoneNumber(phoneNumber);
		commerceAddress.setDefaultBilling(defaultBilling);
		commerceAddress.setDefaultShipping(defaultShipping);

		commerceAddressPersistence.update(commerceAddress);

		// Commerce orders

		List<CommerceOrder> commerceOrders =
			commerceOrderLocalService.getCommerceOrdersByShippingAddress(
				commerceAddressId);

		for (CommerceOrder commerceOrder : commerceOrders) {
			commerceOrderLocalService.resetCommerceOrderShipping(
				commerceOrder.getCommerceOrderId());
		}

		return commerceAddress;
	}

	protected SearchContext buildSearchContext(
		long companyId, String className, long classPK, String keywords,
		int start, int end, Sort sort) {

		SearchContext searchContext = new SearchContext();

		Map<String, Serializable> attributes = new HashMap<>();

		attributes.put(
			Field.CLASS_NAME_ID,
			classNameLocalService.getClassNameId(className));

		attributes.put(Field.CLASS_PK, classPK);
		attributes.put(Field.NAME, keywords);
		attributes.put("city", keywords);
		attributes.put("countryName", keywords);
		attributes.put("regionName", keywords);
		attributes.put("zip", keywords);

		searchContext.setAttributes(attributes);

		searchContext.setCompanyId(companyId);
		searchContext.setStart(start);
		searchContext.setEnd(end);

		if (Validator.isNotNull(keywords)) {
			searchContext.setKeywords(keywords);
		}

		QueryConfig queryConfig = searchContext.getQueryConfig();

		queryConfig.setHighlightEnabled(false);
		queryConfig.setScoreEnabled(false);

		if (sort != null) {
			searchContext.setSorts(sort);
		}

		return searchContext;
	}

	protected List<CommerceAddress> getCommerceAddresses(Hits hits)
		throws PortalException {

		List<Document> documents = hits.toList();

		List<CommerceAddress> commerceAddresses = new ArrayList<>(
			documents.size());

		for (Document document : documents) {
			long commerceAddressId = GetterUtil.getLong(
				document.get(Field.ENTRY_CLASS_PK));

			CommerceAddress commerceAddress = fetchCommerceAddress(
				commerceAddressId);

			if (commerceAddress == null) {
				commerceAddresses = null;

				Indexer<CommerceAddress> indexer =
					IndexerRegistryUtil.getIndexer(CommerceAddress.class);

				long companyId = GetterUtil.getLong(
					document.get(Field.COMPANY_ID));

				indexer.delete(companyId, document.getUID());
			}
			else if (commerceAddresses != null) {
				commerceAddresses.add(commerceAddress);
			}
		}

		return commerceAddresses;
	}

	protected void removeCommerceOrderAddresses(
			List<CommerceOrder> commerceOrders, long commerceAddressId)
		throws PortalException {

		for (CommerceOrder commerceOrder : commerceOrders) {
			long billingAddressId = commerceOrder.getBillingAddressId();
			long shippingAddressId = commerceOrder.getShippingAddressId();

			long commerceShippingMethodId =
				commerceOrder.getCommerceShippingMethodId();
			String shippingOptionName = commerceOrder.getShippingOptionName();
			BigDecimal shippingPrice = commerceOrder.getShippingAmount();

			if (billingAddressId == commerceAddressId) {
				billingAddressId = 0;
			}

			if (shippingAddressId == commerceAddressId) {
				shippingAddressId = 0;

				commerceShippingMethodId = 0;
				shippingOptionName = null;
				shippingPrice = BigDecimal.ZERO;
			}

			commerceOrderLocalService.updateCommerceOrder(
				commerceOrder.getCommerceOrderId(), billingAddressId,
				shippingAddressId, commerceOrder.getCommercePaymentMethodKey(),
				commerceShippingMethodId, shippingOptionName,
				commerceOrder.getPurchaseOrderNumber(),
				commerceOrder.getSubtotal(), shippingPrice,
				commerceOrder.getTotal(), commerceOrder.getAdvanceStatus(),
				null);
		}
	}

	protected BaseModelSearchResult<CommerceAddress> searchCommerceAddresses(
			SearchContext searchContext)
		throws PortalException {

		Indexer<CommerceAddress> indexer =
			IndexerRegistryUtil.nullSafeGetIndexer(CommerceAddress.class);

		for (int i = 0; i < 10; i++) {
			Hits hits = indexer.search(searchContext, _SELECTED_FIELD_NAMES);

			List<CommerceAddress> commerceAddresses = getCommerceAddresses(
				hits);

			if (commerceAddresses != null) {
				return new BaseModelSearchResult<>(
					commerceAddresses, hits.getLength());
			}
		}

		throw new SearchException(
			"Unable to fix the search index after 10 attempts");
	}

	protected void validate(
			long commerceAddressId, long companyId, String className,
			long classPK, String name, String street1, String city, String zip,
			long commerceCountryId, boolean defaultBilling,
			boolean defaultShipping)
		throws PortalException {

		if (Validator.isNull(name)) {
			throw new CommerceAddressNameException();
		}

		if (Validator.isNull(street1)) {
			throw new CommerceAddressStreetException();
		}

		if (Validator.isNull(city)) {
			throw new CommerceAddressCityException();
		}

		if (Validator.isNull(zip)) {
			throw new CommerceAddressZipException();
		}

		if (commerceCountryId <= 0) {
			throw new CommerceAddressCountryException();
		}

		if (defaultBilling) {
			CommerceAddress commerceAddress =
				fetchDefaultBillingCommerceAddress(
					companyId, className, classPK);

			if ((commerceAddress != null) &&
				(commerceAddress.getCommerceAddressId() != commerceAddressId)) {

				commerceAddress.setDefaultBilling(false);

				commerceAddressPersistence.update(commerceAddress);
			}
		}

		if (defaultShipping) {
			CommerceAddress commerceAddress =
				fetchDefaultShippingCommerceAddress(
					companyId, className, classPK);

			if ((commerceAddress != null) &&
				(commerceAddress.getCommerceAddressId() != commerceAddressId)) {

				commerceAddress.setDefaultShipping(false);

				commerceAddressPersistence.update(commerceAddress);
			}
		}
	}

	private static final String[] _SELECTED_FIELD_NAMES = {
		Field.ENTRY_CLASS_PK, Field.COMPANY_ID, Field.UID
	};

	@ServiceReference(type = CommerceGeocoder.class)
	private CommerceGeocoder _commerceGeocoder;

	@ServiceReference(type = GroupLocalService.class)
	private GroupLocalService _groupLocalService;

}