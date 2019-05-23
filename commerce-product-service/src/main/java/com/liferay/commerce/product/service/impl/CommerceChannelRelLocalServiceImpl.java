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

package com.liferay.commerce.product.service.impl;

import com.liferay.commerce.product.exception.DuplicateCommerceChannelRelException;
import com.liferay.commerce.product.model.CommerceChannelRel;
import com.liferay.commerce.product.service.base.CommerceChannelRelLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceChannelRelLocalServiceImpl
	extends CommerceChannelRelLocalServiceBaseImpl {

	@Override
	public CommerceChannelRel addCommerceChannelRel(
			String className, long classPK, long commerceChannelId,
			ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(serviceContext.getUserId());

		long classNameId = classNameLocalService.getClassNameId(className);

		if (commerceChannelRelPersistence.fetchByC_C_C(
				classNameId, classPK, commerceChannelId) != null) {

			throw new DuplicateCommerceChannelRelException();
		}

		long commerceChannelRelId = counterLocalService.increment();

		CommerceChannelRel commerceChannelRel =
			commerceChannelRelPersistence.create(commerceChannelRelId);

		commerceChannelRel.setCompanyId(user.getCompanyId());
		commerceChannelRel.setUserId(user.getUserId());
		commerceChannelRel.setUserName(user.getFullName());
		commerceChannelRel.setClassNameId(classNameId);
		commerceChannelRel.setClassPK(classPK);
		commerceChannelRel.setCommerceChannelId(commerceChannelId);

		commerceChannelRelPersistence.update(commerceChannelRel);

		return commerceChannelRel;
	}

	@Override
	public void deleteCommerceChannelRels(long commerceChannelId) {
		commerceChannelRelPersistence.removeByCommerceChannelId(
			commerceChannelId);
	}

	@Override
	public void deleteCommerceChannelRels(String className, long classPK) {
		long classNameId = classNameLocalService.getClassNameId(className);

		commerceChannelRelPersistence.removeByC_C(classNameId, classPK);
	}

	@Override
	public List<CommerceChannelRel> getCommerceChannelRels(
		long commerceChannelId, int start, int end,
		OrderByComparator<CommerceChannelRel> orderByComparator) {

		return commerceChannelRelPersistence.findByCommerceChannelId(
			commerceChannelId, start, end, orderByComparator);
	}

	@Override
	public List<CommerceChannelRel> getCommerceChannelRels(
		String className, long classPK, int start, int end,
		OrderByComparator<CommerceChannelRel> orderByComparator) {

		long classNameId = classNameLocalService.getClassNameId(className);

		return commerceChannelRelPersistence.findByC_C(
			classNameId, classPK, start, end, orderByComparator);
	}

	@Override
	public int getCommerceChannelRelsCount(long commerceChannelId) {
		return commerceChannelRelPersistence.countByCommerceChannelId(
			commerceChannelId);
	}

	@Override
	public int getCommerceChannelRelsCount(String className, long classPK) {
		long classNameId = classNameLocalService.getClassNameId(className);

		return commerceChannelRelPersistence.countByC_C(classNameId, classPK);
	}

}