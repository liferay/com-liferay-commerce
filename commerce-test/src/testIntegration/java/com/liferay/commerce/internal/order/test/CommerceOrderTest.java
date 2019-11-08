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

package com.liferay.commerce.internal.order.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.commerce.account.constants.CommerceAccountConstants;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.CommerceAccountLocalService;
import com.liferay.commerce.account.service.CommerceAccountUserRelLocalService;
import com.liferay.commerce.account.util.CommerceAccountHelper;
import com.liferay.commerce.constants.CommerceAddressConstants;
import com.liferay.commerce.constants.CommerceOrderConstants;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.test.util.CommerceCurrencyTestUtil;
import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceRegion;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.model.CommerceChannelConstants;
import com.liferay.commerce.product.service.CommerceChannelLocalService;
import com.liferay.commerce.service.CommerceAddressLocalService;
import com.liferay.commerce.service.CommerceCountryLocalService;
import com.liferay.commerce.service.CommerceOrderLocalService;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.commerce.service.CommerceRegionLocalService;
import com.liferay.commerce.test.util.TestCommerceContext;
import com.liferay.petra.lang.CentralizedThreadLocal;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.settings.GroupServiceSettingsLocator;
import com.liferay.portal.kernel.settings.ModifiableSettings;
import com.liferay.portal.kernel.settings.Settings;
import com.liferay.portal.kernel.settings.SettingsFactory;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerTestRule;

import java.util.ArrayList;
import java.util.List;

import org.frutilla.FrutillaRule;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Alec Sloan
 */
@RunWith(Arquillian.class)
public class CommerceOrderTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();
		_user = UserTestUtil.addUser(true);

		_commerceCurrency = CommerceCurrencyTestUtil.addCommerceCurrency(
			_group.getGroupId());

		_serviceContext = ServiceContextTestUtil.getServiceContext(
			_group.getGroupId());

		_commerceChannel = _commerceChannelLocalService.addCommerceChannel(
			_group.getGroupId(), "Test Channel",
			CommerceChannelConstants.CHANNEL_TYPE_SITE, null,
			_commerceCurrency.getCode(), null, _serviceContext);

		Settings settings = _settingsFactory.getSettings(
			new GroupServiceSettingsLocator(
				_commerceChannel.getGroupId(),
				CommerceAccountConstants.SERVICE_NAME));

		ModifiableSettings modifiableSettings =
			settings.getModifiableSettings();

		modifiableSettings.setValue(
			"commerceSiteType",
			String.valueOf(CommerceAccountConstants.SITE_TYPE_B2B));

		modifiableSettings.store();
	}

	@After
	public void tearDown() {
		CentralizedThreadLocal.clearShortLivedThreadLocals();
	}

	@Test
	public void testGetCommerceOrderFilteredByAccount() throws Exception {
		frutillaRule.scenario(
			"Ensure that users can only see orders for accounts they belong to"
		).given(
			"2 accounts on a B2B site, with 1 order each"
		).and(
			"A Group"
		).and(
			"A User"
		).when(
			"I pull all orders for that user"
		).then(
			"I should only see 1 order"
		).but(
			"If they are added to the second account they should see 2 orders"
		);

		CommerceAccount commerceAccount =
			_commerceAccountLocalService.addBusinessCommerceAccount(
				"Test Business Account", 0, null, null, true, null,
				new long[] {_user.getUserId()},
				new String[] {_user.getEmailAddress()}, _serviceContext);

		long commerceChannelGroupId = _commerceChannel.getGroupId();

		CommerceOrder commerceOrder =
			_commerceOrderLocalService.addCommerceOrder(
				_user.getUserId(), commerceChannelGroupId,
				commerceAccount.getCommerceAccountId(),
				_commerceCurrency.getCommerceCurrencyId());

		User secondUser = UserTestUtil.addUser();

		CommerceAccount secondCommerceAccount =
			_commerceAccountLocalService.addBusinessCommerceAccount(
				"Second Test Business Account", 0, null, null, true, null, null,
				null, _serviceContext);

		CommerceOrder secondCommerceOrder =
			_commerceOrderLocalService.addCommerceOrder(
				secondUser.getUserId(), commerceChannelGroupId,
				secondCommerceAccount.getCommerceAccountId(),
				_commerceCurrency.getCommerceCurrencyId());

		List<CommerceOrder> commerceOrders = _getUserOrders(
			commerceChannelGroupId, false);

		Assert.assertEquals(
			commerceOrders.toString(), 1, commerceOrders.size());

		CommerceOrder actualCommerceOrder = commerceOrders.get(0);

		Assert.assertEquals(commerceOrder, actualCommerceOrder);
		Assert.assertEquals(
			commerceAccount, actualCommerceOrder.getCommerceAccount());
		Assert.assertNotEquals(
			secondCommerceAccount, actualCommerceOrder.getCommerceAccount());
		Assert.assertNotEquals(secondCommerceOrder, actualCommerceOrder);

		// Add the user to the second account and they should see 2 orders

		_commerceAccountUserRelLocalService.addCommerceAccountUserRels(
			secondCommerceAccount.getCommerceAccountId(),
			new long[] {_user.getUserId()},
			new String[] {_user.getEmailAddress()}, null, _serviceContext);

		commerceOrders = _getUserOrders(commerceChannelGroupId, false);

		Assert.assertEquals(
			commerceOrders.toString(), 2, commerceOrders.size());

		Assert.assertEquals(commerceOrder, commerceOrders.get(0));
		Assert.assertEquals(secondCommerceOrder, commerceOrders.get(1));

		_commerceOrderLocalService.deleteCommerceOrders(commerceChannelGroupId);
		_commerceAccountLocalService.deleteCommerceAccount(commerceAccount);
		_commerceAccountLocalService.deleteCommerceAccount(
			secondCommerceAccount);
		_userLocalService.deleteUser(secondUser);
	}

	@Test
	public void testGetCommerceOrderFromAccountPermission() throws Exception {
		frutillaRule.scenario(
			"Ensure that users can only see orders for accounts they belong to"
		).given(
			"2 accounts on a B2B site, with 1 order each"
		).and(
			"A Group"
		).and(
			"A User"
		).and(
			"The User is part of both accounts"
		).when(
			"I pull all orders for that user"
		).then(
			"I should see 2 order"
		).but(
			"If I remove the user from the second account they should only " +
				"see 1 order"
		);
		CommerceAccount commerceAccount =
			_commerceAccountLocalService.addBusinessCommerceAccount(
				"Test Business Account", 0, null, null, true, null,
				new long[] {_user.getUserId()},
				new String[] {_user.getEmailAddress()}, _serviceContext);

		long commerceChannelGroupId = _commerceChannel.getGroupId();

		CommerceOrder commerceOrder =
			_commerceOrderLocalService.addCommerceOrder(
				_user.getUserId(), commerceChannelGroupId,
				commerceAccount.getCommerceAccountId(),
				_commerceCurrency.getCommerceCurrencyId());

		User secondUser = UserTestUtil.addUser();

		CommerceAccount secondCommerceAccount =
			_commerceAccountLocalService.addBusinessCommerceAccount(
				"Second Test Business Account", 0, null, null, true, null, null,
				null, _serviceContext);

		// Add the user to the second account

		_commerceAccountUserRelLocalService.addCommerceAccountUserRels(
			secondCommerceAccount.getCommerceAccountId(),
			new long[] {_user.getUserId()},
			new String[] {_user.getEmailAddress()}, null, _serviceContext);

		CommerceOrder secondCommerceOrder =
			_commerceOrderLocalService.addCommerceOrder(
				secondUser.getUserId(), commerceChannelGroupId,
				secondCommerceAccount.getCommerceAccountId(),
				_commerceCurrency.getCommerceCurrencyId());

		List<CommerceOrder> commerceOrders = _getUserOrders(
			commerceChannelGroupId, false);

		Assert.assertEquals(
			commerceOrders.toString(), 2, commerceOrders.size());

		Assert.assertEquals(commerceOrder, commerceOrders.get(0));
		Assert.assertEquals(secondCommerceOrder, commerceOrders.get(1));

		// Remove the user from the second account and get user's orders again

		_commerceAccountUserRelLocalService.deleteCommerceAccountUserRels(
			secondCommerceAccount.getCommerceAccountId(),
			new long[] {_user.getUserId()});

		commerceOrders = _getUserOrders(commerceChannelGroupId, false);

		Assert.assertEquals(
			commerceOrders.toString(), 1, commerceOrders.size());

		CommerceOrder actualCommerceOrder = commerceOrders.get(0);

		Assert.assertEquals(commerceOrder, actualCommerceOrder);
		Assert.assertEquals(
			commerceAccount, actualCommerceOrder.getCommerceAccount());
		Assert.assertNotEquals(
			secondCommerceAccount, actualCommerceOrder.getCommerceAccount());
		Assert.assertNotEquals(secondCommerceOrder, actualCommerceOrder);

		_commerceOrderLocalService.deleteCommerceOrders(commerceChannelGroupId);
		_commerceAccountLocalService.deleteCommerceAccount(commerceAccount);
		_commerceAccountLocalService.deleteCommerceAccount(
			secondCommerceAccount);
		_userLocalService.deleteUser(secondUser);
	}

	@Test
	public void testGetCommerceOrdersForOmniAdmin() throws Exception {
		frutillaRule.scenario(
			"Ensure that the Omni-Admin is able to pull all orders"
		).given(
			"A Group"
		).and(
			"A User"
		).and(
			"A random amount of Accounts"
		).and(
			"a random amount of Orders for each Account"
		).when(
			"I get the count of all Orders the Omni-Admin can see"
		).then(
			"I should get the same count of Orders that were created"
		);

		long commerceChannelGroupId = _commerceChannel.getGroupId();

		int ordersCreated = 0;

		int accountsToCreate = RandomTestUtil.randomInt(2, 10);

		List<User> randomUsers = new ArrayList<>();
		List<CommerceAccount> randomAccounts = new ArrayList<>();
		List<CommerceOrder> randomOrders = new ArrayList<>();

		for (int i = 0; i < accountsToCreate; i++) {
			User user = UserTestUtil.addUser(true);

			randomUsers.add(user);

			CommerceAccount commerceAccount =
				_commerceAccountLocalService.addBusinessCommerceAccount(
					"Test Generated Account " + i, 0, null, null, true, null,
					new long[] {user.getUserId()},
					new String[] {user.getEmailAddress()}, _serviceContext);

			randomAccounts.add(commerceAccount);

			int ordersToCreate = RandomTestUtil.randomInt(1, 3);

			for (int j = 0; j < ordersToCreate; j++) {
				randomOrders.add(
					_commerceOrderLocalService.addCommerceOrder(
						user.getUserId(), commerceChannelGroupId,
						commerceAccount.getCommerceAccountId(),
						_commerceCurrency.getCommerceCurrencyId()));
			}

			ordersCreated += ordersToCreate;
		}

		long ordersCount =
			_commerceOrderService.getUserPendingCommerceOrdersCount(
				_group.getCompanyId(), commerceChannelGroupId,
				StringPool.BLANK);

		Assert.assertEquals(
			"Assert the Omni-admin can see the count of all pending orders",
			ordersCreated, ordersCount);

		List<CommerceOrder> commerceOrders =
			_commerceOrderService.getUserPendingCommerceOrders(
				_group.getCompanyId(), commerceChannelGroupId, StringPool.BLANK,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		Assert.assertEquals(
			"Assert the Omni-admin can see all pending orders were created",
			ListUtil.sort(randomOrders), ListUtil.sort(commerceOrders));

		// Checkout a random number of orders

		CommerceContext commerceContext = new TestCommerceContext(
			_commerceCurrency, _user, _group, null, null);

		List<CommerceOrder> placedCommerceOrders = new ArrayList<>();

		for (CommerceOrder commerceOrder : randomOrders) {
			if (RandomTestUtil.randomBoolean()) {
				CommerceAddress commerceAddress = _addAddressToAccount(
					commerceOrder.getCommerceAccountId());

				commerceOrder.setBillingAddressId(
					commerceAddress.getCommerceAddressId());
				commerceOrder.setShippingAddressId(
					commerceAddress.getCommerceAddressId());

				commerceOrder = _commerceOrderLocalService.updateCommerceOrder(
					commerceOrder);

				placedCommerceOrders.add(
					_commerceOrderLocalService.checkoutCommerceOrder(
						commerceOrder.getCommerceOrderId(), commerceContext,
						_serviceContext));
			}
		}

		ordersCount = _commerceOrderService.getUserPlacedCommerceOrdersCount(
			_group.getCompanyId(), commerceChannelGroupId, StringPool.BLANK);

		Assert.assertEquals(
			"Assert the Omni-admin can see the count of all placed orders",
			placedCommerceOrders.size(), ordersCount);

		commerceOrders = _commerceOrderService.getUserPlacedCommerceOrders(
			_group.getCompanyId(), commerceChannelGroupId, StringPool.BLANK,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		Assert.assertEquals(
			"Assert the Omni-admin can see all placed orders",
			ListUtil.sort(placedCommerceOrders), ListUtil.sort(commerceOrders));

		_commerceOrderLocalService.deleteCommerceOrders(commerceChannelGroupId);

		for (CommerceAccount commerceAccount : randomAccounts) {
			_commerceAccountLocalService.deleteCommerceAccount(commerceAccount);
		}

		for (User user : randomUsers) {
			_userLocalService.deleteUser(user);
		}
	}

	@Test
	public void testGetPendingCommerceOrder() throws Exception {
		frutillaRule.scenario(
			"Try to get a pending order based on the userId, and directly " +
				"based on the commerceAccountId"
		).given(
			"A B2B Site"
		).and(
			"A Group"
		).and(
			"A User"
		).when(
			"I try to get that order by the current user's id or the accountId"
		).then(
			"I should be able to get it both ways"
		);

		CommerceAccount commerceAccount =
			_commerceAccountLocalService.addBusinessCommerceAccount(
				"Test Business Account", 0, null, null, true, null,
				new long[] {_user.getUserId()},
				new String[] {_user.getEmailAddress()}, _serviceContext);

		long commerceChannelGroupId = _commerceChannel.getGroupId();

		CommerceOrder commerceOrder =
			_commerceOrderLocalService.addCommerceOrder(
				_user.getUserId(), commerceChannelGroupId,
				commerceAccount.getCommerceAccountId(),
				_commerceCurrency.getCommerceCurrencyId());

		int ordersCountByAccountId =
			_commerceOrderService.getPendingCommerceOrdersCount(
				commerceChannelGroupId, commerceAccount.getCommerceAccountId(),
				StringPool.BLANK);

		Assert.assertEquals(1, ordersCountByAccountId);

		List<CommerceOrder> commerceOrders =
			_commerceOrderService.getPendingCommerceOrders(
				commerceChannelGroupId, commerceAccount.getCommerceAccountId(),
				StringPool.BLANK, 0, 1);

		CommerceOrder actualCommerceOrder = commerceOrders.get(0);

		Assert.assertEquals(commerceOrder, actualCommerceOrder);

		long ordersCountByUser =
			_getUserOrdersCount(commerceChannelGroupId, false);

		Assert.assertEquals(1, ordersCountByUser);

		commerceOrders = _getUserOrders(commerceChannelGroupId, false);

		actualCommerceOrder = commerceOrders.get(0);

		Assert.assertEquals(commerceOrder, actualCommerceOrder);

		_commerceOrderLocalService.deleteCommerceOrders(commerceChannelGroupId);
		_commerceAccountLocalService.deleteCommerceAccount(commerceAccount);
	}

	@Test
	public void testGetPlacedCommerceOrder() throws Exception {
		frutillaRule.scenario(
			"Try to get a placed order based on the userId, and directly " +
				"based on the commerceAccountId"
		).given(
			"A B2B Site"
		).and(
			"A Group"
		).and(
			"A User"
		).when(
			"I try to get that order by the current user's id or the accountId"
		).then(
			"I should be able to get it both ways"
		);

		CommerceAccount commerceAccount =
			_commerceAccountLocalService.addBusinessCommerceAccount(
				"Test Business Account", 0, null, null, true, null,
				new long[] {_user.getUserId()},
				new String[] {_user.getEmailAddress()}, _serviceContext);

		long commerceChannelGroupId = _commerceChannel.getGroupId();

		CommerceOrder commerceOrder =
			_commerceOrderLocalService.addCommerceOrder(
				_user.getUserId(), commerceChannelGroupId,
				commerceAccount.getCommerceAccountId(),
				_commerceCurrency.getCommerceCurrencyId());

		CommerceAddress commerceAddress = _addAddressToAccount(
			commerceAccount.getCommerceAccountId());

		commerceOrder.setBillingAddressId(
			commerceAddress.getCommerceAddressId());
		commerceOrder.setShippingAddressId(
			commerceAddress.getCommerceAddressId());

		commerceOrder = _commerceOrderLocalService.updateCommerceOrder(
			commerceOrder);

		CommerceContext commerceContext = new TestCommerceContext(
			_commerceCurrency, _user, _group, commerceAccount, commerceOrder);

		commerceOrder = _commerceOrderLocalService.checkoutCommerceOrder(
			commerceOrder.getCommerceOrderId(), commerceContext,
			_serviceContext);

		int ordersCountByAccountId =
			_commerceOrderService.getPlacedCommerceOrdersCount(
				commerceChannelGroupId, commerceAccount.getCommerceAccountId(),
				StringPool.BLANK);

		Assert.assertEquals(1, ordersCountByAccountId);

		List<CommerceOrder> commerceOrders =
			_commerceOrderService.getPlacedCommerceOrders(
				commerceChannelGroupId, commerceAccount.getCommerceAccountId(),
				StringPool.BLANK, 0, 1);

		CommerceOrder actualCommerceOrder = commerceOrders.get(0);

		Assert.assertEquals(commerceOrder, actualCommerceOrder);

		long ordersCountByUser =
			_getUserOrdersCount(commerceChannelGroupId, true);

		Assert.assertEquals(1, ordersCountByUser);

		commerceOrders = _getUserOrders(commerceChannelGroupId, true);

		actualCommerceOrder = commerceOrders.get(0);

		Assert.assertEquals(commerceOrder, actualCommerceOrder);

		_commerceOrderLocalService.deleteCommerceOrders(commerceChannelGroupId);
		_commerceAccountLocalService.deleteCommerceAccount(commerceAccount);
		_commerceAddressLocalService.deleteCommerceAddress(commerceAddress);
	}

	@Rule
	public FrutillaRule frutillaRule = new FrutillaRule();

	private CommerceAddress _addAddressToAccount(long commerceAccountId)
		throws Exception {

		_commerceCountry = _commerceCountryLocalService.fetchCommerceCountry(
			_serviceContext.getCompanyId(), 000);

		if (_commerceCountry == null) {
			_commerceCountry = _commerceCountryLocalService.addCommerceCountry(
				RandomTestUtil.randomLocaleStringMap(), true, true, "ZZ", "ZZZ",
				000, false, RandomTestUtil.randomDouble(), true,
				_serviceContext);

			_commerceRegion = _commerceRegionLocalService.addCommerceRegion(
				_commerceCountry.getCommerceCountryId(),
				RandomTestUtil.randomString(), "ZZ",
				RandomTestUtil.randomDouble(), true, _serviceContext);
		}
		else {
			_commerceRegion = _commerceRegionLocalService.getCommerceRegion(
				_commerceCountry.getCommerceCountryId(), "ZZ");
		}

		return _commerceAddressLocalService.addCommerceAddress(
			CommerceAccount.class.getName(), commerceAccountId,
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			String.valueOf(30133), _commerceRegion.getCommerceRegionId(),
			_commerceCountry.getCommerceCountryId(),
			RandomTestUtil.randomString(),
			CommerceAddressConstants.ADDRESS_TYPE_BILLING_AND_SHIPPING,
			_serviceContext);
	}

	private List<CommerceOrder> _getUserOrders(long groupId, boolean negate)
		throws Exception {

		// This simulates using
		// commerceOrderService.getUser(Pending||Placed)CommerceOrders()
		// If we used that method here it would pull accounts for the omni-admin

		long[] commerceAccountIds =
			_commerceAccountHelper.getUserCommerceAccountIds(
				_user.getUserId(), groupId);

		return _commerceOrderLocalService.getCommerceOrders(
			_group.getCompanyId(), groupId, commerceAccountIds,
			StringPool.BLANK,
			new int[] {CommerceOrderConstants.ORDER_STATUS_OPEN}, negate,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	private long _getUserOrdersCount(long groupId, boolean negate)
		throws Exception {

		// This simulates using
		// commerceOrderService.getUser(Pending||Placed)CommerceOrdersCount()
		// If we used that method here it would pull accounts for the omni-admin

		long[] commerceAccountIds =
			_commerceAccountHelper.getUserCommerceAccountIds(
				_user.getUserId(), groupId);

		return _commerceOrderLocalService.getCommerceOrdersCount(
			_group.getCompanyId(), groupId, commerceAccountIds,
			StringPool.BLANK,
			new int[] {CommerceOrderConstants.ORDER_STATUS_OPEN}, negate);
	}

	@Inject
	private CommerceAccountHelper _commerceAccountHelper;

	@Inject
	private CommerceAccountLocalService _commerceAccountLocalService;

	@Inject
	private CommerceAccountUserRelLocalService
		_commerceAccountUserRelLocalService;

	@Inject
	private CommerceAddressLocalService _commerceAddressLocalService;

	@DeleteAfterTestRun
	private CommerceChannel _commerceChannel;

	@Inject
	private CommerceChannelLocalService _commerceChannelLocalService;

	@DeleteAfterTestRun
	private CommerceCountry _commerceCountry;

	@Inject
	private CommerceCountryLocalService _commerceCountryLocalService;

	@DeleteAfterTestRun
	private CommerceCurrency _commerceCurrency;

	@Inject
	private CommerceOrderLocalService _commerceOrderLocalService;

	@Inject
	private CommerceOrderService _commerceOrderService;

	@DeleteAfterTestRun
	private CommerceRegion _commerceRegion;

	@Inject
	private CommerceRegionLocalService _commerceRegionLocalService;

	@DeleteAfterTestRun
	private Group _group;

	private ServiceContext _serviceContext;

	@Inject
	private SettingsFactory _settingsFactory;

	@DeleteAfterTestRun
	private User _user;

	@Inject
	private UserLocalService _userLocalService;

}