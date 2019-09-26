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

import com.liferay.commerce.exception.CommerceCountryAlreadyExistsException;
import com.liferay.commerce.exception.CommerceCountryNameException;
import com.liferay.commerce.exception.CommerceCountryThreeLettersISOCodeException;
import com.liferay.commerce.exception.CommerceCountryTwoLettersISOCodeException;
import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.service.base.CommerceCountryLocalServiceBaseImpl;
import com.liferay.commerce.starter.CommerceRegionsStarter;
import com.liferay.commerce.starter.CommerceRegionsStarterRegistry;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.service.access.policy.model.SAPEntry;
import com.liferay.portal.security.service.access.policy.service.SAPEntryLocalService;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @author Alessio Antonio Rendina
 * @author Andrea Di Giorgi
 * @author Marco Leo
 * @author Luca Pellizzon
 */
public class CommerceCountryLocalServiceImpl
	extends CommerceCountryLocalServiceBaseImpl {

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceCountry addCommerceCountry(
			Map<Locale, String> nameMap, boolean billingAllowed,
			boolean shippingAllowed, String twoLettersISOCode,
			String threeLettersISOCode, int numericISOCode,
			boolean subjectToVAT, double priority, boolean active,
			ServiceContext serviceContext)
		throws PortalException {

		if (commerceCountryLocalService.fetchCommerceCountry(
				serviceContext.getCompanyId(), twoLettersISOCode) != null) {

			throw new CommerceCountryAlreadyExistsException();
		}

		User user = userLocalService.getUser(serviceContext.getUserId());

		validate(nameMap, twoLettersISOCode, threeLettersISOCode);

		long commerceCountryId = counterLocalService.increment();

		CommerceCountry commerceCountry = commerceCountryPersistence.create(
			commerceCountryId);

		commerceCountry.setUuid(serviceContext.getUuid());
		commerceCountry.setCompanyId(user.getCompanyId());
		commerceCountry.setUserId(user.getUserId());
		commerceCountry.setUserName(user.getFullName());
		commerceCountry.setNameMap(nameMap);
		commerceCountry.setBillingAllowed(billingAllowed);
		commerceCountry.setShippingAllowed(shippingAllowed);
		commerceCountry.setTwoLettersISOCode(twoLettersISOCode);
		commerceCountry.setThreeLettersISOCode(threeLettersISOCode);
		commerceCountry.setNumericISOCode(numericISOCode);
		commerceCountry.setSubjectToVAT(subjectToVAT);
		commerceCountry.setPriority(priority);
		commerceCountry.setActive(active);
		commerceCountry.setChannelFilterEnabled(false);

		commerceCountryPersistence.update(commerceCountry);

		return commerceCountry;
	}

	@Override
	public void deleteCommerceCountries(long companyId) throws PortalException {
		List<CommerceCountry> commerceCountries =
			commerceCountryPersistence.findByCompanyId(companyId);

		for (CommerceCountry commerceCountry : commerceCountries) {
			commerceCountryLocalService.deleteCommerceCountry(commerceCountry);
		}
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public CommerceCountry deleteCommerceCountry(
			CommerceCountry commerceCountry)
		throws PortalException {

		// Commerce country

		commerceCountryPersistence.remove(commerceCountry);

		// Commerce regions

		commerceRegionLocalService.deleteCommerceRegions(
			commerceCountry.getCommerceCountryId());

		// Commerce addresses

		commerceAddressLocalService.deleteCountryCommerceAddresses(
			commerceCountry.getCommerceCountryId());

		// Commerce address restrictions

		commerceAddressRestrictionLocalService.
			deleteCommerceAddressRestrictions(
				commerceCountry.getCommerceCountryId());

		return commerceCountry;
	}

	@Override
	public CommerceCountry deleteCommerceCountry(long commerceCountryId)
		throws PortalException {

		CommerceCountry commerceCountry =
			commerceCountryPersistence.findByPrimaryKey(commerceCountryId);

		return commerceCountryLocalService.deleteCommerceCountry(
			commerceCountry);
	}

	@Override
	public CommerceCountry fetchCommerceCountry(
			long companyId, int numericISOCode)
		throws PortalException {

		return commerceCountryPersistence.fetchByC_N(companyId, numericISOCode);
	}

	@Override
	public CommerceCountry fetchCommerceCountry(
		long companyId, String twoLettersISOCode) {

		return commerceCountryPersistence.fetchByC_Tw(
			companyId, twoLettersISOCode);
	}

	@Override
	public List<CommerceCountry> getBillingCommerceCountries(
		long companyId, boolean billingAllowed, boolean active) {

		return commerceCountryPersistence.findByC_B_A(
			companyId, billingAllowed, active);
	}

	@Override
	public List<CommerceCountry> getBillingCommerceCountriesByChannelId(
		long commerceChannelId, int start, int end) {

		return commerceCountryFinder.findByCommerceChannel(
			commerceChannelId, false, true, start, end);
	}

	@Override
	public List<CommerceCountry> getCommerceCountries(
		long companyId, boolean active) {

		return commerceCountryPersistence.findByC_A(companyId, active);
	}

	@Override
	public List<CommerceCountry> getCommerceCountries(
		long companyId, boolean active, int start, int end,
		OrderByComparator<CommerceCountry> orderByComparator) {

		return commerceCountryPersistence.findByC_A(
			companyId, active, start, end, orderByComparator);
	}

	@Override
	public List<CommerceCountry> getCommerceCountries(
		long companyId, int start, int end,
		OrderByComparator<CommerceCountry> orderByComparator) {

		return commerceCountryPersistence.findByCompanyId(
			companyId, start, end, orderByComparator);
	}

	@Override
	public int getCommerceCountriesCount(long companyId) {
		return commerceCountryPersistence.countByCompanyId(companyId);
	}

	@Override
	public int getCommerceCountriesCount(long companyId, boolean active) {
		return commerceCountryPersistence.countByC_A(companyId, active);
	}

	@Override
	public CommerceCountry getCommerceCountry(
			long companyId, String twoLettersISOCode)
		throws PortalException {

		return commerceCountryPersistence.findByC_Tw(
			companyId, twoLettersISOCode);
	}

	@Override
	public List<CommerceCountry> getShippingCommerceCountries(
		long companyId, boolean shippingAllowed, boolean active) {

		return commerceCountryPersistence.findByC_S_A(
			companyId, shippingAllowed, active);
	}

	@Override
	public List<CommerceCountry> getShippingCommerceCountriesByChannelId(
		long commerceChannelId, int start, int end) {

		return commerceCountryFinder.findByCommerceChannel(
			commerceChannelId, true, false, start, end);
	}

	@Override
	public List<CommerceCountry> getWarehouseCommerceCountries(
		long companyId, boolean all) {

		return commerceCountryFinder.findByCommerceInventoryWarehouses(
			companyId, all);
	}

	@Override
	public void importDefaultCountries(ServiceContext serviceContext)
		throws Exception {

		Class<?> clazz = getClass();

		String countriesPath = "com/liferay/commerce/internal/countries.json";

		String countriesJSON = StringUtil.read(
			clazz.getClassLoader(), countriesPath, false);

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray(countriesJSON);

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			int numericISOCode = jsonObject.getInt("numericISOCode");

			CommerceCountry commerceCountry =
				commerceCountryLocalService.fetchCommerceCountry(
					serviceContext.getCompanyId(), numericISOCode);

			if (commerceCountry == null) {
				String name = jsonObject.getString("name");
				double priority = jsonObject.getDouble("priority");
				String threeLettersISOCode = jsonObject.getString(
					"threeLettersISOCode");
				String twoLettersISOCode = jsonObject.getString(
					"twoLettersISOCode");

				String localizedName = LanguageUtil.get(
					serviceContext.getLocale(), "country." + name);

				Map<Locale, String> nameMap = new HashMap<>();

				nameMap.put(serviceContext.getLocale(), localizedName);

				commerceCountryLocalService.addCommerceCountry(
					nameMap, true, true, twoLettersISOCode, threeLettersISOCode,
					numericISOCode, false, priority, true, serviceContext);

				CommerceRegionsStarter commerceRegionsStarter =
					_commerceRegionsStarterRegistry.getCommerceRegionsStarter(
						String.valueOf(numericISOCode));

				if (commerceRegionsStarter != null) {
					commerceRegionsStarter.start(serviceContext);
				}
			}
		}

		SAPEntry sapEntry = _sapEntryLocalService.fetchSAPEntry(
			serviceContext.getCompanyId(), _COMMERCE_SAP_ENTRY_NAME);

		if (sapEntry == null) {
			String ungatedServicesPath =
				"com/liferay/commerce/internal/ungated-services.json";

			String ungatedServicesJSON = StringUtil.read(
				clazz.getClassLoader(), ungatedServicesPath, false);

			JSONArray ungatedServicesJSONArray =
				JSONFactoryUtil.createJSONArray(ungatedServicesJSON);

			String[] ungatedServices =
				new String[ungatedServicesJSONArray.length()];

			for (int i = 0; i < ungatedServicesJSONArray.length(); i++) {
				ungatedServices[i] = ungatedServicesJSONArray.getString(i);
			}

			Map<Locale, String> titleMap = new HashMap<>();

			ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
				"content.Language", serviceContext.getLocale(), clazz);

			String localizedTitle = LanguageUtil.get(
				resourceBundle,
				"public-access-to-the-commerce-country-and-region-apis");

			titleMap.put(serviceContext.getLocale(), localizedTitle);

			_sapEntryLocalService.addSAPEntry(
				serviceContext.getUserId(),
				StringUtil.merge(ungatedServices, StringPool.NEW_LINE), true,
				true, _COMMERCE_SAP_ENTRY_NAME, titleMap, serviceContext);
		}
	}

	@Override
	public BaseModelSearchResult<CommerceCountry> searchCommerceCountries(
			SearchContext searchContext)
		throws PortalException {

		Indexer<CommerceCountry> indexer =
			IndexerRegistryUtil.nullSafeGetIndexer(CommerceCountry.class);

		for (int i = 0; i < 10; i++) {
			Hits hits = indexer.search(searchContext, _SELECTED_FIELD_NAMES);

			List<CommerceCountry> commerceCountries = getCommerceCountries(
				hits);

			if (commerceCountries != null) {
				return new BaseModelSearchResult<>(
					commerceCountries, hits.getLength());
			}
		}

		throw new SearchException(
			"Unable to fix the search index after 10 attempts");
	}

	@Override
	public CommerceCountry setActive(long commerceCountryId, boolean active)
		throws PortalException {

		CommerceCountry commerceCountry =
			commerceCountryPersistence.findByPrimaryKey(commerceCountryId);

		commerceCountry.setActive(active);

		commerceCountryPersistence.update(commerceCountry);

		return commerceCountry;
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceCountry updateCommerceCountry(
			long commerceCountryId, Map<Locale, String> nameMap,
			boolean billingAllowed, boolean shippingAllowed,
			String twoLettersISOCode, String threeLettersISOCode,
			int numericISOCode, boolean subjectToVAT, double priority,
			boolean active, ServiceContext serviceContext)
		throws PortalException {

		CommerceCountry commerceCountry =
			commerceCountryPersistence.findByPrimaryKey(commerceCountryId);

		validate(nameMap, twoLettersISOCode, threeLettersISOCode);

		commerceCountry.setNameMap(nameMap);
		commerceCountry.setBillingAllowed(billingAllowed);
		commerceCountry.setShippingAllowed(shippingAllowed);
		commerceCountry.setTwoLettersISOCode(twoLettersISOCode);
		commerceCountry.setThreeLettersISOCode(threeLettersISOCode);
		commerceCountry.setNumericISOCode(numericISOCode);
		commerceCountry.setSubjectToVAT(subjectToVAT);
		commerceCountry.setPriority(priority);
		commerceCountry.setActive(active);

		commerceCountryPersistence.update(commerceCountry);

		return commerceCountry;
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceCountry updateCommerceCountryChannelFilter(
			long commerceCountryId, boolean enable)
		throws PortalException {

		CommerceCountry commerceCountry =
			commerceCountryLocalService.getCommerceCountry(commerceCountryId);

		commerceCountry.setChannelFilterEnabled(enable);

		return commerceCountryPersistence.update(commerceCountry);
	}

	protected List<CommerceCountry> getCommerceCountries(Hits hits)
		throws PortalException {

		List<Document> documents = hits.toList();

		List<CommerceCountry> commerceCountries = new ArrayList<>(
			documents.size());

		for (Document document : documents) {
			long commerceCountryId = GetterUtil.getLong(
				document.get(Field.ENTRY_CLASS_PK));

			CommerceCountry commerceCountry = fetchCommerceCountry(
				commerceCountryId);

			if (commerceCountry == null) {
				commerceCountries = null;

				Indexer<CommerceCountry> indexer =
					IndexerRegistryUtil.getIndexer(CommerceCountry.class);

				long companyId = GetterUtil.getLong(
					document.get(Field.COMPANY_ID));

				indexer.delete(companyId, document.getUID());
			}
			else if (commerceCountries != null) {
				commerceCountries.add(commerceCountry);
			}
		}

		return commerceCountries;
	}

	protected void validate(
			Map<Locale, String> nameMap, String twoLettersISOCode,
			String threeLettersISOCode)
		throws PortalException {

		Locale locale = LocaleUtil.getSiteDefault();

		String name = nameMap.get(locale);

		if (Validator.isNull(name)) {
			throw new CommerceCountryNameException();
		}

		if (Validator.isNotNull(twoLettersISOCode) &&
			(twoLettersISOCode.length() != 2)) {

			throw new CommerceCountryTwoLettersISOCodeException();
		}

		if (Validator.isNotNull(threeLettersISOCode) &&
			(threeLettersISOCode.length() != 3)) {

			throw new CommerceCountryThreeLettersISOCodeException();
		}
	}

	private static final String _COMMERCE_SAP_ENTRY_NAME = "COMMERCE_DEFAULT";

	private static final String[] _SELECTED_FIELD_NAMES = {
		Field.ENTRY_CLASS_PK, Field.COMPANY_ID, Field.GROUP_ID, Field.UID
	};

	@ServiceReference(type = CommerceRegionsStarterRegistry.class)
	private CommerceRegionsStarterRegistry _commerceRegionsStarterRegistry;

	@ServiceReference(type = SAPEntryLocalService.class)
	private SAPEntryLocalService _sapEntryLocalService;

}