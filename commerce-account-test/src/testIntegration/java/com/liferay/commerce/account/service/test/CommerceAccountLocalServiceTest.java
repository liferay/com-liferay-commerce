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
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.CompanyTestUtil;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
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

		String name = "Business Account";
		String email = "example@email.com";

		Group group = GroupTestUtil.addGroup(
			_company.getCompanyId(), _user.getUserId(),
			GroupConstants.DEFAULT_PARENT_GROUP_ID);

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(group.getGroupId());

		CommerceAccount businessCommerceAccount =
			_commerceAccountLocalService.addBusinessCommerceAccount(
				name, CommerceAccountConstants.DEFAULT_PARENT_ACCOUNT_ID, email,
				StringPool.BLANK, true, StringPool.BLANK,
				new long[] {_user.getUserId()}, null, serviceContext);

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

		Group group = GroupTestUtil.addGroup(
			_company.getCompanyId(), _user.getUserId(),
			GroupConstants.DEFAULT_PARENT_GROUP_ID);

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(group.getGroupId());

		CommerceAccount personalCommerceAccount =
			_commerceAccountLocalService.addPersonalCommerceAccount(
				_user.getUserId(), StringPool.BLANK, StringPool.BLANK,
				serviceContext);

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

	@Rule
	public final FrutillaRule frutillaRule = new FrutillaRule();

	@Inject
	private CommerceAccountLocalService _commerceAccountLocalService;

	@DeleteAfterTestRun
	private Company _company;

	@DeleteAfterTestRun
	private User _user;

}