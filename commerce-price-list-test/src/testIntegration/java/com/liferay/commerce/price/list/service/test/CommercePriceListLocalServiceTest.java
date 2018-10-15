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

package com.liferay.commerce.price.list.service.test;

import static org.hamcrest.CoreMatchers.equalTo;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.commerce.currency.exception.NoSuchCurrencyException;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.test.util.CommerceCurrencyTestUtil;
import com.liferay.commerce.price.list.exception.NoSuchPriceListException;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.service.CommercePriceListLocalService;
import com.liferay.commerce.price.list.test.util.CommercePriceListTestUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.Currency;
import java.util.Date;
import java.util.Locale;

import org.frutilla.FrutillaRule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Zoltán Takács
 * @author Ethan Bustad
 */
@RunWith(Arquillian.class)
public class CommercePriceListLocalServiceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();

		Currency currencyGBP = Currency.getInstance(Locale.UK);

		_commerceCurrencyGBP = CommerceCurrencyTestUtil.addCommerceCurrency(
			_group.getGroupId(), currencyGBP.getCurrencyCode());

		Currency currencyUSD = Currency.getInstance(Locale.US);

		_commerceCurrencyUSD = CommerceCurrencyTestUtil.addCommerceCurrency(
			_group.getGroupId(), currencyUSD.getCurrencyCode());
	}

	@Test
	public void testAddCommercePriceList1() throws Exception {
		frutillaRule.scenario(
			"Adding a new Price List"
		).given(
			"A site (group)"
		).and(
			"A currency code expressed with 3-letter ISO 4217 format"
		).and(
			"The name of the new list"
		).when(
			"The name of the Price List"
		).and(
			"The currency code are checked against the input data"
		).then(
			"The result should be a new Price List on the given site"
		);

		Currency currency = Currency.getInstance(Locale.US);
		String name = RandomTestUtil.randomString();

		CommercePriceList commercePriceList =
			CommercePriceListTestUtil.addCommercePriceList(
				_group.getGroupId(), currency.getCurrencyCode(), name,
				RandomTestUtil.randomDouble(), true, null, null, null);

		_assertPriceListAttributes(currency, name, commercePriceList);
	}

	@Test
	public void testAddCommercePriceList2() throws Exception {
		frutillaRule.scenario(
			"Adding a new Price List with an external reference code"
		).given(
			"A site (group)"
		).and(
			"A currency code expressed with 3-letter ISO 4217 format"
		).and(
			"The name of the new list"
		).and(
			"The external reference code (externalReferenceCode)"
		).when(
			"The name of the Price List"
		).and(
			"The currency code"
		).and(
			"The external reference code are checked against the input data"
		).then(
			"The result should be a new Price List on the given site"
		);

		Currency currency = Currency.getInstance(Locale.US);
		String externalReferenceCode = RandomTestUtil.randomString();
		String name = RandomTestUtil.randomString();

		CommercePriceList commercePriceList =
			CommercePriceListTestUtil.addCommercePriceList(
				_group.getGroupId(), currency.getCurrencyCode(), name,
				RandomTestUtil.randomDouble(), true, null, null,
				externalReferenceCode);

		_assertPriceListAttributes(currency, name, commercePriceList);

		Assert.assertThat(
			externalReferenceCode,
			equalTo(commercePriceList.getExternalReferenceCode()));
	}

	@Test
	public void testAddCommercePriceList3() throws Exception {
		frutillaRule.scenario(
			"Adding a new Price List"
		).given(
			"A site (group)"
		).and(
			"A currency code expressed with 3-letter ISO 4217 format"
		).and(
			"A parent price list ID"
		).and(
			"The name of the new list"
		).when(
			"The name of the Price List"
		).and(
			"The parent price list ID (parentCommercePriceListId)"
		).and(
			"The currency code are checked against the input data"
		).then(
			"The result should be a new Price List on the given site"
		);

		Currency currency = Currency.getInstance(Locale.US);
		String name = RandomTestUtil.randomString();
		long parentCommercePriceListId = RandomTestUtil.randomLong();

		CommercePriceList commercePriceList =
			CommercePriceListTestUtil.addCommercePriceList(
				_group.getGroupId(), currency.getCurrencyCode(),
				parentCommercePriceListId, name, RandomTestUtil.randomDouble(),
				true, null, null, null);

		_assertPriceListAttributes(currency, name, commercePriceList);

		Assert.assertThat(
			parentCommercePriceListId,
			equalTo(commercePriceList.getParentCommercePriceListId()));
	}

	@Test
	public void testAddCommercePriceList4() throws Exception {
		frutillaRule.scenario(
			"Adding a new Price List with an external reference code"
		).given(
			"A site (group)"
		).and(
			"A currency code expressed with 3-letter ISO 4217 format"
		).and(
			"A parent price list ID"
		).and(
			"The name of the new list"
		).and(
			"The external reference code (externalReferenceCode)"
		).when(
			"The name of the Price List"
		).and(
			"The parent price list ID (parentCommercePriceListId)"
		).and(
			"The currency code"
		).and(
			"The external reference code are checked against the input data"
		).then(
			"The result should be a new Price List on the given site"
		);

		Currency currency = Currency.getInstance(Locale.US);
		String externalReferenceCode = RandomTestUtil.randomString();
		String name = RandomTestUtil.randomString();
		long parentCommercePriceListId = RandomTestUtil.randomLong();

		CommercePriceList commercePriceList =
			CommercePriceListTestUtil.addCommercePriceList(
				_group.getGroupId(), currency.getCurrencyCode(),
				parentCommercePriceListId, name, RandomTestUtil.randomDouble(),
				true, null, null, externalReferenceCode);

		_assertPriceListAttributes(currency, name, commercePriceList);

		Assert.assertThat(
			externalReferenceCode,
			equalTo(commercePriceList.getExternalReferenceCode()));
		Assert.assertThat(
			parentCommercePriceListId,
			equalTo(commercePriceList.getParentCommercePriceListId()));
	}

	@Test
	public void testUpdateCommercePriceList1() throws Exception {
		frutillaRule.scenario(
			"Update an existing Price List"
		).given(
			"An existing Price List"
		).and(
			"A new currency code expressed with 3-letter ISO 4217 format"
		).and(
			"A new name for the list"
		).and(
			"A new display date"
		).and(
			"A new expiration date"
		).when(
			"The new values are used in the method invocation"
		).and(
			"Model getters are used for all other method invocation parameters"
		).then(
			"The result should be an updated Price List with only the " +
				"currency code, name, display date, and expiration date changed"
		);

		Currency currency = Currency.getInstance(Locale.US);
		String name = RandomTestUtil.randomString();

		CommercePriceList commercePriceList =
			CommercePriceListTestUtil.addCommercePriceList(
				_group.getGroupId(), currency.getCurrencyCode(), name,
				RandomTestUtil.randomDouble(), true, null, null, null);

		currency = Currency.getInstance(Locale.UK);
		name = RandomTestUtil.randomString();
		Date displayDate = new Date();
		Date expirationDate = new Date(Long.MAX_VALUE);

		CommercePriceList updatedCommercePriceList =
			CommercePriceListTestUtil.updateCommercePriceList(
				commercePriceList.getGroupId(),
				commercePriceList.getCommercePriceListId(),
				currency.getCurrencyCode(),
				commercePriceList.getParentCommercePriceListId(), name,
				commercePriceList.getPriority(), false, displayDate,
				expirationDate);

		_assertPriceListAttributes(currency, name, updatedCommercePriceList);

		Assert.assertThat(
			displayDate, equalTo(updatedCommercePriceList.getDisplayDate()));
		Assert.assertThat(
			expirationDate,
			equalTo(updatedCommercePriceList.getExpirationDate()));

		Assert.assertThat(
			commercePriceList.getGroupId(),
			equalTo(updatedCommercePriceList.getGroupId()));
		Assert.assertThat(
			commercePriceList.getCommercePriceListId(),
			equalTo(updatedCommercePriceList.getCommercePriceListId()));
		Assert.assertThat(
			commercePriceList.getParentCommercePriceListId(),
			equalTo(updatedCommercePriceList.getParentCommercePriceListId()));
		Assert.assertThat(
			commercePriceList.getPriority(),
			equalTo(updatedCommercePriceList.getPriority()));
	}

	@Test(expected = NoSuchPriceListException.class)
	public void testUpdateCommercePriceList2() throws Exception {
		frutillaRule.scenario(
			"Update a nonexisting Price List"
		).given(
			"A nonexisting Price List ID"
		).when(
			"The value is used in the method invocation"
		).then(
			"NoSuchPriceListException should be thrown"
		);

		long commercePriceListId = RandomTestUtil.randomLong();
		Currency currency = Currency.getInstance(Locale.US);

		CommercePriceListTestUtil.updateCommercePriceList(
			_group.getGroupId(), commercePriceListId,
			currency.getCurrencyCode(), 0, RandomTestUtil.randomString(),
			RandomTestUtil.randomDouble(), true, null, null);
	}

	@Test
	public void testUpdateCommercePriceList3() throws Exception {
		frutillaRule.scenario(
			"Update an existing Price List"
		).given(
			"An existing Price List"
		).and(
			"A new parent price list ID (parentCommercePriceListId)"
		).when(
			"The new value is used in the method invocation"
		).then(
			"The result should be the new (parentCommercePriceListId) set on " +
				"the Price List"
		);

		Currency currency = Currency.getInstance(Locale.US);
		String name = RandomTestUtil.randomString();

		CommercePriceList commercePriceList =
			CommercePriceListTestUtil.addCommercePriceList(
				_group.getGroupId(), currency.getCurrencyCode(), 0, name,
				RandomTestUtil.randomDouble(), true, null, null, null);

		long parentCommercePriceListId = RandomTestUtil.randomLong();

		CommercePriceList updatedCommercePriceList =
			CommercePriceListTestUtil.updateCommercePriceList(
				commercePriceList.getGroupId(),
				commercePriceList.getCommercePriceListId(),
				currency.getCurrencyCode(), parentCommercePriceListId, name,
				commercePriceList.getPriority(), false,
				commercePriceList.getDisplayDate(),
				commercePriceList.getExpirationDate());

		Assert.assertThat(
			parentCommercePriceListId,
			equalTo(updatedCommercePriceList.getParentCommercePriceListId()));
	}

	@Test
	public void testUpsertCommercePriceList1() throws Exception {
		frutillaRule.scenario(
			"Adding a new Price List"
		).given(
			"A site (group)"
		).and(
			"A currency code expressed with 3-letter ISO 4217 format"
		).and(
			"The name of the new list"
		).when(
			"The external reference code (externalReferenceCode)"
		).and(
			"(commercePriceListId) is not used in the method invocation"
		).then(
			"The result should be a new Price List on the given site"
		);

		Currency currency = Currency.getInstance(Locale.US);
		String name = RandomTestUtil.randomString();

		CommercePriceList commercePriceList =
			CommercePriceListTestUtil.upsertCommercePriceList(
				_group.getGroupId(), 0, currency.getCurrencyCode(), name,
				RandomTestUtil.randomDouble(), true, null, null, null);

		_assertPriceListAttributes(currency, name, commercePriceList);
	}

	@Test
	public void testUpsertCommercePriceList2() throws Exception {
		frutillaRule.scenario(
			"Update an existing Price List"
		).given(
			"A site (group)"
		).and(
			"A currency code expressed with 3-letter ISO 4217 format"
		).and(
			"The name of the list"
		).when(
			"The external reference code (externalReferenceCode) is used"
		).and(
			"(commercePriceListId) is not used in the method invocation"
		).then(
			"The result should be the updated Price List with the given " +
				"(externalReferenceCode) on the given site"
		);

		Currency currency = Currency.getInstance(Locale.US);
		String externalReferenceCode = RandomTestUtil.randomString();
		String name = RandomTestUtil.randomString();

		CommercePriceListTestUtil.addCommercePriceList(
			_group.getGroupId(), currency.getCurrencyCode(), name,
			RandomTestUtil.randomDouble(), true, null, null,
			externalReferenceCode);

		Currency updatedCurrency = Currency.getInstance(Locale.UK);
		String updatedName = RandomTestUtil.randomString();

		CommercePriceListTestUtil.upsertCommercePriceList(
			_group.getGroupId(), 0, updatedCurrency.getCurrencyCode(),
			updatedName, RandomTestUtil.randomDouble(), true, null, null,
			externalReferenceCode);

		CommercePriceList commercePriceList =
			_commercePriceListLocalService.fetchByExternalReferenceCode(
				_group.getCompanyId(), externalReferenceCode);

		_assertPriceListAttributes(
			updatedCurrency, updatedName, commercePriceList);
	}

	@Test
	public void testUpsertCommercePriceList3() throws Exception {
		frutillaRule.scenario(
			"Update an existing Price List"
		).given(
			"A site (group)"
		).and(
			"A currency code expressed with 3-letter ISO 4217 format"
		).and(
			"The name of the new list"
		).when(
			"The external reference code (externalReferenceCode) is not used"
		).and(
			"(commercePriceListId) is used in the method invocation"
		).then(
			"The result should be the updated Price List with the given " +
				"(commercePriceListId) on the given site"
		);

		Currency currency = Currency.getInstance(Locale.US);
		String name = RandomTestUtil.randomString();

		CommercePriceList commercePriceList =
			CommercePriceListTestUtil.addCommercePriceList(
				_group.getGroupId(), currency.getCurrencyCode(), name,
				RandomTestUtil.randomDouble(), true, null, null, null);

		Currency updatedCurrency = Currency.getInstance(Locale.UK);
		String updatedName = RandomTestUtil.randomString();

		CommercePriceListTestUtil.upsertCommercePriceList(
			_group.getGroupId(), commercePriceList.getCommercePriceListId(),
			updatedCurrency.getCurrencyCode(), updatedName,
			RandomTestUtil.randomDouble(), true, null, null, null);

		CommercePriceList updatedCommercePriceList =
			_commercePriceListLocalService.getCommercePriceList(
				commercePriceList.getCommercePriceListId());

		_assertPriceListAttributes(
			updatedCurrency, updatedName, updatedCommercePriceList);
	}

	@Test(expected = NoSuchCurrencyException.class)
	public void testUpsertCommercePriceList4() throws Exception {
		frutillaRule.scenario(
			"Adding a new Price List"
		).given(
			"A site (group)"
		).and(
			"A non-existent currency code on the site"
		).and(
			"The name of the new list"
		).when(
			"The external reference code (externalReferenceCode)"
		).and(
			"(commercePriceListId) is not used in the method invocation"
		).then(
			"CommercePriceListCurrencyException should be thrown"
		);

		Currency currency = Currency.getInstance("KGS");
		String name = RandomTestUtil.randomString();

		CommercePriceListTestUtil.upsertCommercePriceList(
			_group.getGroupId(), 0, currency.getCurrencyCode(), name,
			RandomTestUtil.randomDouble(), true, null, null, null);
	}

	@Test
	public void testUpsertCommercePriceList5() throws Exception {
		frutillaRule.scenario(
			"Adding a new Price List"
		).given(
			"A site (group)"
		).and(
			"A currency code expressed with 3-letter ISO 4217 format"
		).and(
			"The name of the new list"
		).when(
			"The external reference code (externalReferenceCode) is not used"
		).and(
			"A non-existent but greater than 0 (commercePriceListId) is used " +
				"in the method invocation"
		).then(
			"The result should be a new Price List on the given site"
		);

		Currency currency = Currency.getInstance(Locale.US);
		String name = RandomTestUtil.randomString();

		CommercePriceList commercePriceList =
			CommercePriceListTestUtil.upsertCommercePriceList(
				_group.getGroupId(), 1, currency.getCurrencyCode(), name,
				RandomTestUtil.randomDouble(), true, null, null, null);

		_assertPriceListAttributes(currency, name, commercePriceList);
	}

	@Test
	public void testUpsertCommercePriceList6() throws Exception {
		frutillaRule.scenario(
			"Adding a new Price List"
		).given(
			"A site (group)"
		).and(
			"A currency code expressed with 3-letter ISO 4217 format"
		).and(
			"A parent price list ID"
		).and(
			"The name of the new list"
		).when(
			"The external reference code (externalReferenceCode)"
		).and(
			"(commercePriceListId) is not used in the method invocation"
		).then(
			"The result should be a new Price List with the given " +
				"(parentCommercePriceListId) on the given site"
		);

		Currency currency = Currency.getInstance(Locale.US);
		String name = RandomTestUtil.randomString();
		long parentCommercePriceListId = RandomTestUtil.randomLong();

		CommercePriceList commercePriceList =
			CommercePriceListTestUtil.upsertCommercePriceList(
				_group.getGroupId(), 1, currency.getCurrencyCode(),
				parentCommercePriceListId, name, RandomTestUtil.randomDouble(),
				true, null, null, null);

		Assert.assertThat(
			parentCommercePriceListId,
			equalTo(commercePriceList.getParentCommercePriceListId()));
	}

	@Test
	public void testUpsertCommercePriceList7() throws Exception {
		frutillaRule.scenario(
			"Update an existing Price List"
		).given(
			"A site (group)"
		).and(
			"A currency code expressed with 3-letter ISO 4217 format"
		).and(
			"A parent price list ID"
		).and(
			"The name of the list"
		).when(
			"The external reference code (externalReferenceCode) is used"
		).and(
			"(commercePriceListId) is not used in the method invocation"
		).then(
			"The result should be the updated Price List with the given " +
				"(parentCommercePriceListId) on the given site"
		);

		Currency currency = Currency.getInstance(Locale.US);
		String name = RandomTestUtil.randomString();
		long parentCommercePriceListId = RandomTestUtil.randomLong();

		CommercePriceList commercePriceList =
			CommercePriceListTestUtil.upsertCommercePriceList(
				_group.getGroupId(), 1, currency.getCurrencyCode(),
				parentCommercePriceListId, name, RandomTestUtil.randomDouble(),
				true, null, null, null);

		Assert.assertThat(
			parentCommercePriceListId,
			equalTo(commercePriceList.getParentCommercePriceListId()));
	}

	@Rule
	public final FrutillaRule frutillaRule = new FrutillaRule();

	private static void _assertPriceListAttributes(
			Currency currency, String name, CommercePriceList commercePriceList)
		throws PortalException {

		CommerceCurrency commerceCurrency =
			commercePriceList.getCommerceCurrency();

		Assert.assertThat(
			currency,
			equalTo(Currency.getInstance(commerceCurrency.getCode())));

		Assert.assertThat(name, equalTo(commercePriceList.getName()));
	}

	@DeleteAfterTestRun
	private CommerceCurrency _commerceCurrencyGBP;

	@DeleteAfterTestRun
	private CommerceCurrency _commerceCurrencyUSD;

	@Inject
	private CommercePriceListLocalService _commercePriceListLocalService;

	@DeleteAfterTestRun
	private Group _group;

}