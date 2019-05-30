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

package com.liferay.commerce.currency.service.impl;

import com.liferay.commerce.currency.configuration.CommerceCurrencyConfiguration;
import com.liferay.commerce.currency.configuration.RoundingTypeConfiguration;
import com.liferay.commerce.currency.constants.CommerceCurrencyExchangeRateConstants;
import com.liferay.commerce.currency.constants.RoundingTypeConstants;
import com.liferay.commerce.currency.exception.CommerceCurrencyCodeException;
import com.liferay.commerce.currency.exception.CommerceCurrencyNameException;
import com.liferay.commerce.currency.exception.NoSuchCurrencyException;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.model.CommerceCurrencyConstants;
import com.liferay.commerce.currency.service.base.CommerceCurrencyLocalServiceBaseImpl;
import com.liferay.commerce.currency.util.ExchangeRateProvider;
import com.liferay.commerce.currency.util.ExchangeRateProviderRegistry;
import com.liferay.commerce.currency.util.comparator.CommerceCurrencyPriorityComparator;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.settings.CompanyServiceSettingsLocator;
import com.liferay.portal.kernel.settings.SystemSettingsLocator;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Andrea Di Giorgi
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
public class CommerceCurrencyLocalServiceImpl
	extends CommerceCurrencyLocalServiceBaseImpl {

	@Override
	public CommerceCurrency addCommerceCurrency(
			long userId, String code, Map<Locale, String> nameMap,
			BigDecimal rate, Map<Locale, String> formatPatternMap,
			int maxFractionDigits, int minFractionDigits, String roundingMode,
			boolean primary, double priority, boolean active,
			ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		if (primary) {
			rate = BigDecimal.ONE;
		}

		validate(0, serviceContext.getCompanyId(), code, nameMap, primary);

		RoundingTypeConfiguration roundingTypeConfiguration =
			_configurationProvider.getConfiguration(
				RoundingTypeConfiguration.class,
				new SystemSettingsLocator(RoundingTypeConstants.SERVICE_NAME));

		if (formatPatternMap.isEmpty()) {
			formatPatternMap.put(
				serviceContext.getLocale(),
				CommerceCurrencyConstants.DEFAULT_FORMAT_PATTERN);
		}

		if (Validator.isNull(roundingMode)) {
			RoundingMode roundingModeEnum =
				roundingTypeConfiguration.roundingMode();

			roundingMode = roundingModeEnum.name();
		}

		long commerceCurrencyId = counterLocalService.increment();

		CommerceCurrency commerceCurrency = commerceCurrencyPersistence.create(
			commerceCurrencyId);

		commerceCurrency.setUuid(serviceContext.getUuid());
		commerceCurrency.setCompanyId(user.getCompanyId());
		commerceCurrency.setUserId(user.getUserId());
		commerceCurrency.setUserName(user.getFullName());
		commerceCurrency.setCode(code);
		commerceCurrency.setNameMap(nameMap);
		commerceCurrency.setRate(rate);
		commerceCurrency.setFormatPatternMap(formatPatternMap);
		commerceCurrency.setMaxFractionDigits(maxFractionDigits);
		commerceCurrency.setMinFractionDigits(minFractionDigits);
		commerceCurrency.setRoundingMode(roundingMode);
		commerceCurrency.setPrimary(primary);
		commerceCurrency.setPriority(priority);
		commerceCurrency.setActive(active);

		commerceCurrencyPersistence.update(commerceCurrency);

		return commerceCurrency;
	}

	@Override
	public void deleteCommerceCurrencies(long companyId) {
		commerceCurrencyPersistence.removeByCompanyId(companyId);
	}

	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public CommerceCurrency deleteCommerceCurrency(
		CommerceCurrency commerceCurrency) {

		return commerceCurrencyPersistence.remove(commerceCurrency);
	}

	@Override
	public CommerceCurrency deleteCommerceCurrency(long commerceCurrencyId)
		throws PortalException {

		CommerceCurrency commerceCurrency =
			commerceCurrencyPersistence.findByPrimaryKey(commerceCurrencyId);

		return commerceCurrencyLocalService.deleteCommerceCurrency(
			commerceCurrency);
	}

	@Override
	public CommerceCurrency fetchPrimaryCommerceCurrency(long companyId) {
		return commerceCurrencyPersistence.fetchByC_P_A_First(
			companyId, true, true, new CommerceCurrencyPriorityComparator());
	}

	@Override
	public List<CommerceCurrency> getCommerceCurrencies(
		long companyId, boolean active) {

		return commerceCurrencyPersistence.findByC_A(companyId, active);
	}

	@Override
	public List<CommerceCurrency> getCommerceCurrencies(
		long companyId, boolean active, int start, int end,
		OrderByComparator<CommerceCurrency> orderByComparator) {

		return commerceCurrencyPersistence.findByC_A(
			companyId, active, start, end, orderByComparator);
	}

	@Override
	public List<CommerceCurrency> getCommerceCurrencies(
		long companyId, int start, int end,
		OrderByComparator<CommerceCurrency> orderByComparator) {

		return commerceCurrencyPersistence.findByCompanyId(
			companyId, start, end, orderByComparator);
	}

	@Override
	public int getCommerceCurrenciesCount(long companyId) {
		return commerceCurrencyPersistence.countByCompanyId(companyId);
	}

	@Override
	public int getCommerceCurrenciesCount(long companyId, boolean active) {
		return commerceCurrencyPersistence.countByC_A(companyId, active);
	}

	@Override
	public CommerceCurrency getCommerceCurrency(long companyId, String code)
		throws NoSuchCurrencyException {

		return commerceCurrencyPersistence.findByC_C(companyId, code);
	}

	@Override
	public void importDefaultValues(ServiceContext serviceContext)
		throws Exception {

		Class<?> clazz = getClass();

		String currenciesPath =
			"com/liferay/commerce/currency/service/impl/dependencies" +
				"/currencies.json";

		String countriesJSON = StringUtil.read(
			clazz.getClassLoader(), currenciesPath, false);

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray(countriesJSON);

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			String code = jsonObject.getString("code");

			CommerceCurrency commerceCurrency =
				commerceCurrencyPersistence.fetchByC_C(
					serviceContext.getCompanyId(), code);

			if (commerceCurrency == null) {
				String name = jsonObject.getString("name");
				boolean primary = jsonObject.getBoolean("primary");
				double priority = jsonObject.getDouble("priority");
				String symbol = jsonObject.getString("symbol");

				RoundingTypeConfiguration roundingTypeConfiguration =
					_configurationProvider.getConfiguration(
						RoundingTypeConfiguration.class,
						new SystemSettingsLocator(
							RoundingTypeConstants.SERVICE_NAME));

				Map<Locale, String> nameMap = new HashMap<>();
				Map<Locale, String> formatPatternMap = new HashMap<>();

				nameMap.put(serviceContext.getLocale(), name);
				formatPatternMap.put(
					serviceContext.getLocale(),
					symbol + CommerceCurrencyConstants.DEFAULT_FORMAT_PATTERN);

				RoundingMode roundingMode =
					roundingTypeConfiguration.roundingMode();

				commerceCurrencyLocalService.addCommerceCurrency(
					serviceContext.getUserId(), code, nameMap, BigDecimal.ONE,
					formatPatternMap,
					roundingTypeConfiguration.maximumFractionDigits(),
					roundingTypeConfiguration.minimumFractionDigits(),
					roundingMode.name(), primary, priority, true,
					serviceContext);
			}
		}

		for (String exchangeRateProviderKey :
				_exchangeRateProviderRegistry.getExchangeRateProviderKeys()) {

			_updateExchangeRates(
				serviceContext.getCompanyId(), exchangeRateProviderKey);

			break;
		}
	}

	@Override
	public CommerceCurrency setActive(long commerceCurrencyId, boolean active)
		throws PortalException {

		CommerceCurrency commerceCurrency =
			commerceCurrencyPersistence.findByPrimaryKey(commerceCurrencyId);

		commerceCurrency.setActive(active);

		commerceCurrencyPersistence.update(commerceCurrency);

		return commerceCurrency;
	}

	@Override
	public CommerceCurrency setPrimary(long commerceCurrencyId, boolean primary)
		throws PortalException {

		CommerceCurrency commerceCurrency =
			commerceCurrencyPersistence.findByPrimaryKey(commerceCurrencyId);

		validate(
			commerceCurrencyId, commerceCurrency.getCompanyId(),
			commerceCurrency.getCode(), commerceCurrency.getNameMap(), primary);

		commerceCurrency.setPrimary(primary);

		commerceCurrencyPersistence.update(commerceCurrency);

		return commerceCurrency;
	}

	@Override
	public CommerceCurrency updateCommerceCurrency(
			long commerceCurrencyId, String code, Map<Locale, String> nameMap,
			BigDecimal rate, Map<Locale, String> formatPatternMap,
			int maxFractionDigits, int minFractionDigits, String roundingMode,
			boolean primary, double priority, boolean active,
			ServiceContext serviceContext)
		throws PortalException {

		CommerceCurrency commerceCurrency =
			commerceCurrencyPersistence.findByPrimaryKey(commerceCurrencyId);

		if (primary) {
			rate = BigDecimal.ONE;
		}

		validate(
			commerceCurrency.getCommerceCurrencyId(),
			serviceContext.getCompanyId(), code, nameMap, primary);

		RoundingTypeConfiguration roundingTypeConfiguration =
			_configurationProvider.getConfiguration(
				RoundingTypeConfiguration.class,
				new SystemSettingsLocator(RoundingTypeConstants.SERVICE_NAME));

		if (formatPatternMap.isEmpty()) {
			formatPatternMap.put(
				serviceContext.getLocale(),
				CommerceCurrencyConstants.DEFAULT_FORMAT_PATTERN);
		}

		if (Validator.isNull(roundingMode)) {
			RoundingMode roundingModeEnum =
				roundingTypeConfiguration.roundingMode();

			roundingMode = roundingModeEnum.name();
		}

		commerceCurrency.setCode(code);
		commerceCurrency.setNameMap(nameMap);
		commerceCurrency.setRate(rate);
		commerceCurrency.setFormatPatternMap(formatPatternMap);
		commerceCurrency.setMaxFractionDigits(maxFractionDigits);
		commerceCurrency.setMinFractionDigits(minFractionDigits);
		commerceCurrency.setRoundingMode(roundingMode);
		commerceCurrency.setPrimary(primary);
		commerceCurrency.setPriority(priority);
		commerceCurrency.setActive(active);

		commerceCurrencyPersistence.update(commerceCurrency);

		return commerceCurrency;
	}

	@Override
	public CommerceCurrency updateCommerceCurrencyRate(
			long commerceCurrencyId, BigDecimal rate)
		throws PortalException {

		CommerceCurrency commerceCurrency =
			commerceCurrencyPersistence.findByPrimaryKey(commerceCurrencyId);

		commerceCurrency.setRate(rate);

		return commerceCurrencyPersistence.update(commerceCurrency);
	}

	@Override
	public void updateExchangeRate(
			long commerceCurrencyId, String exchangeRateProviderKey)
		throws PortalException {

		ExchangeRateProvider exchangeRateProvider =
			_exchangeRateProviderRegistry.getExchangeRateProvider(
				exchangeRateProviderKey);

		if (exchangeRateProvider == null) {
			return;
		}

		CommerceCurrency commerceCurrency =
			commerceCurrencyPersistence.findByPrimaryKey(commerceCurrencyId);

		CommerceCurrency primaryCommerceCurrency =
			commerceCurrencyLocalService.fetchPrimaryCommerceCurrency(
				commerceCurrency.getCompanyId());

		if (primaryCommerceCurrency == null) {
			return;
		}

		BigDecimal exchangeRate = BigDecimal.ZERO;

		try {
			exchangeRate = exchangeRateProvider.getExchangeRate(
				primaryCommerceCurrency, commerceCurrency);
		}
		catch (Exception e) {
			_log.error(e, e);

			return;
		}

		commerceCurrency.setRate(exchangeRate);

		commerceCurrencyLocalService.updateCommerceCurrency(commerceCurrency);
	}

	@Override
	public void updateExchangeRates() throws PortalException {
		long[] companyIds = ArrayUtil.toLongArray(
			commerceCurrencyFinder.getCompanyIds());

		for (long companyId : companyIds) {
			CommerceCurrencyConfiguration commerceCurrencyConfiguration =
				_configurationProvider.getConfiguration(
					CommerceCurrencyConfiguration.class,
					new CompanyServiceSettingsLocator(
						companyId,
						CommerceCurrencyExchangeRateConstants.SERVICE_NAME));

			if (commerceCurrencyConfiguration.enableAutoUpdate()) {
				String defaultExchangeRateProviderKey =
					commerceCurrencyConfiguration.
						defaultExchangeRateProviderKey();

				_updateExchangeRates(companyId, defaultExchangeRateProviderKey);
			}
		}
	}

	protected void validate(
			long commerceCurrencyId, long companyId, String code,
			Map<Locale, String> nameMap, boolean primary)
		throws PortalException {

		Locale locale = LocaleUtil.getSiteDefault();

		if (Validator.isNull(code)) {
			throw new CommerceCurrencyCodeException();
		}

		String name = nameMap.get(locale);

		if (Validator.isNull(name)) {
			throw new CommerceCurrencyNameException();
		}

		if (primary) {
			List<CommerceCurrency> commerceCurrencies =
				commerceCurrencyPersistence.findByC_P(companyId, primary);

			for (CommerceCurrency commerceCurrency : commerceCurrencies) {
				if (commerceCurrency.getCommerceCurrencyId() !=
						commerceCurrencyId) {

					commerceCurrency.setPrimary(false);

					commerceCurrencyPersistence.update(commerceCurrency);
				}
			}
		}
	}

	private void _updateExchangeRates(
			long companyId, String exchangeRateProviderKey)
		throws PortalException {

		List<CommerceCurrency> commerceCurrencies =
			commerceCurrencyLocalService.getCommerceCurrencies(companyId, true);

		for (CommerceCurrency commerceCurrency : commerceCurrencies) {
			commerceCurrencyLocalService.updateExchangeRate(
				commerceCurrency.getCommerceCurrencyId(),
				exchangeRateProviderKey);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceCurrencyLocalServiceImpl.class);

	@ServiceReference(type = ConfigurationProvider.class)
	private ConfigurationProvider _configurationProvider;

	@ServiceReference(type = ExchangeRateProviderRegistry.class)
	private ExchangeRateProviderRegistry _exchangeRateProviderRegistry;

}