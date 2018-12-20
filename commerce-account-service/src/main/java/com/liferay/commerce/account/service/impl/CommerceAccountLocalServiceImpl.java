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

package com.liferay.commerce.account.service.impl;

import com.liferay.commerce.account.constants.CommerceAccountConstants;
import com.liferay.commerce.account.exception.CommerceAccountNameException;
import com.liferay.commerce.account.exception.DuplicateCommerceAccountException;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.base.CommerceAccountLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Marco Leo
 */
public class CommerceAccountLocalServiceImpl
	extends CommerceAccountLocalServiceBaseImpl {

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceAccount addCommerceAccount(
			long userId, long parentCommerceAccountId, String name,
			String taxId, boolean active, String externalReferenceCode,
			ServiceContext serviceContext)
		throws PortalException {
		//TODO ALESSIO  Validare che l'external reference code non esista nel contesto della company
		//Creare metodo di upsert

		// Commerce Account

		User user = userPersistence.findByPrimaryKey(userId);

		parentCommerceAccountId = getParentCommerceAccountId(
			user.getCompanyId(), parentCommerceAccountId);

		validate(user.getCompanyId(), 0, name);

		long commerceAccountId = counterLocalService.increment();

		CommerceAccount commerceAccount = commerceAccountPersistence.create(
			commerceAccountId);

		commerceAccount.setCompanyId(user.getCompanyId());
		commerceAccount.setUserId(user.getUserId());
		commerceAccount.setUserName(user.getFullName());
		commerceAccount.setExternalReferenceCode(externalReferenceCode);
		commerceAccount.setParentCommerceAccountId(parentCommerceAccountId);
		commerceAccount.setName(name);
		commerceAccount.setTaxId(taxId);
		commerceAccount.setActive(active);
		commerceAccount.setExpandoBridgeAttributes(serviceContext);

		commerceAccountPersistence.update(commerceAccount);

		// Group

		groupLocalService.addGroup(
			userId, GroupConstants.DEFAULT_PARENT_GROUP_ID,
			CommerceAccount.class.getName(), commerceAccountId,
			GroupConstants.DEFAULT_LIVE_GROUP_ID, getLocalizationMap(name),
			null, GroupConstants.TYPE_SITE_PRIVATE, false,
			GroupConstants.DEFAULT_MEMBERSHIP_RESTRICTION, null, false, true,
			null);

		// Resources

		resourceLocalService.addResources(
			user.getCompanyId(), 0, userId, CommerceAccount.class.getName(),
			commerceAccount.getCommerceAccountId(), false, false, false);

		// Workflow

		Map<String, Serializable> workflowContext = new HashMap<>();

		return WorkflowHandlerRegistryUtil.startWorkflowInstance(
			commerceAccount.getCompanyId(), WorkflowConstants.DEFAULT_GROUP_ID,
			userId, CommerceAccount.class.getName(), commerceAccountId,
			commerceAccount, serviceContext, workflowContext);
	}

	protected long getParentCommerceAccountId(
		long companyId, long parentCommerceAccountId) {

		if (parentCommerceAccountId !=
				CommerceAccountConstants.DEFAULT_PARENT_ACCOUNT_ID) {

			// Ensure parent account exists and belongs to the proper
			// company

			CommerceAccount parentCommerceAccount =
				commerceAccountPersistence.fetchByPrimaryKey(
					parentCommerceAccountId);

			if ((parentCommerceAccount == null) ||
				(companyId != parentCommerceAccount.getCompanyId())) {

				parentCommerceAccountId =
					CommerceAccountConstants.DEFAULT_PARENT_ACCOUNT_ID;
			}
		}

		return parentCommerceAccountId;
	}

	protected void validate(long companyId, long commerceAccountId, String name)
		throws PortalException {

		if (Validator.isNull(name)) {
			throw new CommerceAccountNameException();
		}

		CommerceAccount commerceAccount = commerceAccountPersistence.fetchByC_N(
			companyId, name);

		if ((commerceAccount != null) &&
			StringUtil.equalsIgnoreCase(commerceAccount.getName(), name)) {

			if ((commerceAccountId <= 0) ||
				(commerceAccount.getCommerceAccountId() != commerceAccountId)) {

				throw new DuplicateCommerceAccountException(
					"There is another commerce account named " + name);
			}
		}
	}

}