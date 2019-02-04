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

package com.liferay.commerce.account.service.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.commerce.account.constants.CommerceAccountConstants;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.CommerceAccountLocalService;
import com.liferay.commerce.account.test.util.CommerceAccountTestUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.OrganizationConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.CompanyTestUtil;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.List;

import org.frutilla.FrutillaRule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Alessio Antonio Rendina
 */
@RunWith(Arquillian.class)
public class CommerceAccountLocalServiceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_company = CompanyTestUtil.addCompany();

		_user = UserTestUtil.addUser(_company);
	}

	@Test
	public void testAddBusinessCommerceAccount() throws Exception {
		frutillaRule.scenario(
			"Adding a new business Commerce Account"
		).given(
			"A company"
		).and(
			"A user as Account Administrator"
		).when(
			"The Commerce Account is created"
		).then(
			"The list of accounts contains only one Commerce Account"
		).and(
			"That Commerce Account matches the one created before."
		);

		CommerceAccount businessCommerceAccount =
			CommerceAccountTestUtil.addBusinessCommerceAccount(
				_user.getUserId(), "Business Account", "example@email.com",
				_getServiceContext());

		List<CommerceAccount> commerceAccounts =
			_commerceAccountLocalService.getUserCommerceAccounts(
				_user.getUserId(),
				CommerceAccountConstants.DEFAULT_PARENT_ACCOUNT_ID,
				CommerceAccountConstants.SITE_TYPE_B2B, StringPool.BLANK,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		Assert.assertEquals(
			commerceAccounts.toString(), 1, commerceAccounts.size());

		CommerceAccount commerceAccount = commerceAccounts.get(0);

		Assert.assertEquals(
			businessCommerceAccount.getCommerceAccountId(),
			commerceAccount.getCommerceAccountId());
		Assert.assertEquals(
			businessCommerceAccount.getType(), commerceAccount.getType());
		Assert.assertEquals(
			WorkflowConstants.STATUS_APPROVED, commerceAccount.getStatus());
	}

	@Test
	public void testAddPersonalCommerceAccount() throws Exception {
		frutillaRule.scenario(
			"Adding a new personal Commerce Account"
		).given(
			"A company"
		).when(
			"The Commerce Account is created"
		).then(
			"The list of accounts contains only one Commerce Account"
		).and(
			"That Commerce Account matches the one created before."
		);

		CommerceAccount personalCommerceAccount =
			CommerceAccountTestUtil.addPersonalCommerceAccount(
				_user.getUserId(), _getServiceContext());

		Assert.assertEquals(
			personalCommerceAccount.getName(),
			String.valueOf(_user.getUserId()));

		List<CommerceAccount> commerceAccounts =
			_commerceAccountLocalService.getUserCommerceAccounts(
				_user.getUserId(),
				CommerceAccountConstants.DEFAULT_PARENT_ACCOUNT_ID,
				CommerceAccountConstants.SITE_TYPE_B2C, StringPool.BLANK,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		Assert.assertEquals(
			commerceAccounts.toString(), 1, commerceAccounts.size());

		CommerceAccount commerceAccount = commerceAccounts.get(0);

		Assert.assertEquals(
			personalCommerceAccount.getCommerceAccountId(),
			commerceAccount.getCommerceAccountId());
		Assert.assertEquals(
			commerceAccount.getName(), String.valueOf(_user.getUserId()));
		Assert.assertEquals(
			personalCommerceAccount.getType(), commerceAccount.getType());
		Assert.assertEquals(
			WorkflowConstants.STATUS_APPROVED, commerceAccount.getStatus());
	}

	@Test
	public void testBusinessOrganizationCommerceAccountsVisibility()
		throws Exception {

		frutillaRule.scenario(
			"Adding new business Commerce Accounts"
		).given(
			"A company"
		).when(
			"The Commerce Accounts are created"
		).and(
			"Organizations are added to them"
		).then(
			"Check the visibility of that accounts for all the organizations"
		);

		ServiceContext serviceContext = _getServiceContext();

		String organizationName = RandomTestUtil.randomString();

		for (int i = 1; i < 3; i++) {
			Organization organization =
				OrganizationLocalServiceUtil.addOrganization(
					_user.getUserId(),
					OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID,
					organizationName + i, false);

			User user = UserTestUtil.addUser(
				_user.getCompanyId(), _user.getUserId(), "organizationUser" + i,
				serviceContext.getLocale(), RandomTestUtil.randomString(),
				RandomTestUtil.randomString(),
				new long[] {serviceContext.getScopeGroupId()}, serviceContext);

			OrganizationLocalServiceUtil.addUserOrganization(
				user.getUserId(), organization);

			CommerceAccountTestUtil.addBusinessCommerceAccount(
				_user.getUserId(), "businessOrganizationAccount" + i,
				"example@example.com", null,
				new long[] {organization.getOrganizationId()}, serviceContext);
		}

		User organizationUser1 = _userLocalService.getUserByScreenName(
			_company.getCompanyId(), "organizationUser1");
		User organizationUser2 = _userLocalService.getUserByScreenName(
			_company.getCompanyId(), "organizationUser2");

		List<CommerceAccount> organizationUser1CommerceAccounts =
			_commerceAccountLocalService.getUserCommerceAccounts(
				organizationUser1.getUserId(),
				CommerceAccountConstants.DEFAULT_PARENT_ACCOUNT_ID,
				CommerceAccountConstants.SITE_TYPE_B2B, StringPool.BLANK,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		Assert.assertEquals(
			organizationUser1CommerceAccounts.toString(), 1,
			organizationUser1CommerceAccounts.size());

		CommerceAccount organizationUser1CommerceAccount =
			organizationUser1CommerceAccounts.get(0);

		Assert.assertEquals(
			"businessOrganizationAccount1",
			organizationUser1CommerceAccount.getName());

		List<CommerceAccount> organizationUser2CommerceAccounts =
			_commerceAccountLocalService.getUserCommerceAccounts(
				organizationUser2.getUserId(),
				CommerceAccountConstants.DEFAULT_PARENT_ACCOUNT_ID,
				CommerceAccountConstants.SITE_TYPE_B2B, StringPool.BLANK,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		Assert.assertEquals(
			organizationUser2CommerceAccounts.toString(), 1,
			organizationUser2CommerceAccounts.size());

		CommerceAccount organizationUser2CommerceAccount =
			organizationUser2CommerceAccounts.get(0);

		Assert.assertEquals(
			"businessOrganizationAccount2",
			organizationUser2CommerceAccount.getName());
	}

	@Test
	public void testBusinessUserCommerceAccountsVisibility() throws Exception {
		frutillaRule.scenario(
			"Adding new business Commerce Accounts"
		).given(
			"A company"
		).when(
			"The Commerce Accounts are created"
		).and(
			"Users are added to them"
		).then(
			"Check the visibility of that accounts for all the users"
		);

		ServiceContext serviceContext = _getServiceContext();

		for (int i = 1; i < 3; i++) {
			User user = UserTestUtil.addUser(
				_user.getCompanyId(), _user.getUserId(), "businessUser" + i,
				serviceContext.getLocale(), RandomTestUtil.randomString(),
				RandomTestUtil.randomString(),
				new long[] {serviceContext.getScopeGroupId()}, serviceContext);

			CommerceAccountTestUtil.addBusinessCommerceAccount(
				_user.getUserId(), "businessUserAccount" + i,
				"example@example.com", new long[] {user.getUserId()}, null,
				serviceContext);
		}

		User businessUser1 = _userLocalService.getUserByScreenName(
			_company.getCompanyId(), "businessUser1");
		User businessUser2 = _userLocalService.getUserByScreenName(
			_company.getCompanyId(), "businessUser2");

		List<CommerceAccount> businessUser1CommerceAccounts =
			_commerceAccountLocalService.getUserCommerceAccounts(
				businessUser1.getUserId(),
				CommerceAccountConstants.DEFAULT_PARENT_ACCOUNT_ID,
				CommerceAccountConstants.SITE_TYPE_B2B, StringPool.BLANK,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		Assert.assertEquals(
			businessUser1CommerceAccounts.toString(), 1,
			businessUser1CommerceAccounts.size());

		CommerceAccount businessUser1CommerceAccount =
			businessUser1CommerceAccounts.get(0);

		Assert.assertEquals(
			"businessUserAccount1", businessUser1CommerceAccount.getName());

		List<CommerceAccount> businessUser2CommerceAccounts =
			_commerceAccountLocalService.getUserCommerceAccounts(
				businessUser2.getUserId(),
				CommerceAccountConstants.DEFAULT_PARENT_ACCOUNT_ID,
				CommerceAccountConstants.SITE_TYPE_B2B, StringPool.BLANK,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		Assert.assertEquals(
			businessUser2CommerceAccounts.toString(), 1,
			businessUser2CommerceAccounts.size());

		CommerceAccount businessUser2CommerceAccount =
			businessUser2CommerceAccounts.get(0);

		Assert.assertEquals(
			"businessUserAccount2", businessUser2CommerceAccount.getName());
	}

	@Rule
	public final FrutillaRule frutillaRule = new FrutillaRule();

	private ServiceContext _getServiceContext() throws Exception {
		Group group = GroupTestUtil.addGroup(
			_user.getCompanyId(), _user.getUserId(),
			GroupConstants.DEFAULT_PARENT_GROUP_ID);

		return ServiceContextTestUtil.getServiceContext(group.getGroupId());
	}

	@Inject
	private CommerceAccountLocalService _commerceAccountLocalService;

	@DeleteAfterTestRun
	private Company _company;

	@DeleteAfterTestRun
	private User _user;

	@Inject
	private UserLocalService _userLocalService;

}