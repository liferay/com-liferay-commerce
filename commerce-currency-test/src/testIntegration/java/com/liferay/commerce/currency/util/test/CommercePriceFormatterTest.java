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

package com.liferay.commerce.currency.util.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.test.util.CommerceCurrencyTestUtil;
import com.liferay.commerce.currency.util.CommercePriceFormatter;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.math.BigDecimal;

import java.util.Locale;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Luca Pellizzon
 */
@RunWith(Arquillian.class)
public class CommercePriceFormatterTest {

	@ClassRule
	@Rule
	public static AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();

		_price = new BigDecimal(1234560.78);
	}

	@Test
	public void testFormatBigDecimal() throws Exception {
		String regex = "^(\\d*,?\\.?)*\\d\\d$";

		String formattedPrice = _commercePriceFormatter.format(
			_price, LocaleUtil.getDefault());

		Assert.assertTrue(formattedPrice.matches(regex));
	}

	@Test
	public void testFormatCurrencyBigDecimalLocaleFR() throws Exception {
		String regexFR = "^\\d{1,3}(.\\d{3})*,\\d\\d\\s[" + _SYMBOLS + "]$";

		CommerceCurrency commerceCurrency =
			CommerceCurrencyTestUtil.addCommerceCurrency(_group.getGroupId());

		Locale france = LocaleUtil.FRANCE;

		commerceCurrency.setFormatPattern("###,##0.00 $", france);

		LocaleUtil.setDefault(france.getLanguage(), france.getCountry(), null);

		String formattedPrice = _commercePriceFormatter.format(
			commerceCurrency, _price, france);

		Assert.assertTrue(formattedPrice.matches(regexFR));
	}

	@Test
	public void testFormatCurrencyBigDecimalLocaleIT() throws Exception {
		String regexIT = "^[" + _SYMBOLS + "]\\s\\d{1,3}(\\.\\d{3})*,\\d\\d$";

		CommerceCurrency commerceCurrency =
			CommerceCurrencyTestUtil.addCommerceCurrency(_group.getGroupId());

		Locale italy = LocaleUtil.ITALY;

		commerceCurrency.setFormatPattern("$ ###,##0.00", italy);

		LocaleUtil.setDefault(italy.getLanguage(), italy.getCountry(), null);

		String formattedPrice = _commercePriceFormatter.format(
			commerceCurrency, _price, italy);

		Assert.assertTrue(formattedPrice.matches(regexIT));
	}

	@Test
	public void testFormatCurrencyBigDecimalLocaleUS() throws Exception {
		String regexUS = "^[" + _SYMBOLS + "]\\d{1,3}(\\,\\d{3})*\\.\\d\\d$";

		CommerceCurrency commerceCurrency =
			CommerceCurrencyTestUtil.addCommerceCurrency(_group.getGroupId());

		Locale us = LocaleUtil.US;

		commerceCurrency.setFormatPattern("$###,##0.00", us);

		LocaleUtil.setDefault(us.getLanguage(), us.getCountry(), null);

		String formattedPrice = _commercePriceFormatter.format(
			commerceCurrency, _price, us);

		Assert.assertTrue(formattedPrice.matches(regexUS));
	}

	private static final String _SYMBOLS = "€$¥£R$₹";

	@Inject
	private CommercePriceFormatter _commercePriceFormatter;

	@DeleteAfterTestRun
	private Group _group;

	private BigDecimal _price;

}