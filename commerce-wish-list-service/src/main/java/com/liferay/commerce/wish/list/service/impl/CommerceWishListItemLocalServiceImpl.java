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

package com.liferay.commerce.wish.list.service.impl;

import com.liferay.commerce.product.exception.NoSuchCPInstanceException;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPDefinitionLocalService;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.commerce.wish.list.exception.GuestWishListItemMaxAllowedException;
import com.liferay.commerce.wish.list.internal.configuration.CommerceWishListConfiguration;
import com.liferay.commerce.wish.list.model.CommerceWishList;
import com.liferay.commerce.wish.list.model.CommerceWishListItem;
import com.liferay.commerce.wish.list.service.base.CommerceWishListItemLocalServiceBaseImpl;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.List;

/**
 * @author Andrea Di Giorgi
 */
public class CommerceWishListItemLocalServiceImpl
	extends CommerceWishListItemLocalServiceBaseImpl {

	@Override
	public CommerceWishListItem addCommerceWishListItem(
			long commerceWishListId, long cpDefinitionId, long cpInstanceId,
			String json, ServiceContext serviceContext)
		throws PortalException {

		CommerceWishList commerceWishList =
			commerceWishListLocalService.getCommerceWishList(
				commerceWishListId);
		User user = userLocalService.getUser(serviceContext.getUserId());

		validate(commerceWishList, cpDefinitionId, cpInstanceId);

		long commerceWishListItemId = counterLocalService.increment();

		CommerceWishListItem commerceWishListItem =
			commerceWishListItemPersistence.create(commerceWishListItemId);

		CPDefinition cpDefinition = _cpDefinitionLocalService.fetchCPDefinition(
			cpDefinitionId);

		CPInstance cpInstance = _cpInstanceLocalService.getCPInstance(
			cpInstanceId);

		commerceWishListItem.setGroupId(commerceWishList.getGroupId());
		commerceWishListItem.setCompanyId(user.getCompanyId());
		commerceWishListItem.setUserId(user.getUserId());
		commerceWishListItem.setUserName(user.getFullName());
		commerceWishListItem.setCommerceWishListId(
			commerceWishList.getCommerceWishListId());
		commerceWishListItem.setCProductId(cpDefinition.getCProductId());
		commerceWishListItem.setCPInstanceUuid(cpInstance.getUuid());

		commerceWishListItem.setJson(json);

		commerceWishListItemPersistence.update(commerceWishListItem);

		return commerceWishListItem;
	}

	@Override
	public void deleteCommerceWishListItems(long commerceWishListId) {
		commerceWishListItemPersistence.removeByCommerceWishListId(
			commerceWishListId);
	}

	/**
	 * @deprecated As of 1.1
	 */
	@Deprecated
	@Override
	public void deleteCommerceWishListItemsByCPDefinitionId(
		long cpDefinitionId) {

		try {
			CPDefinition cpDefinition =
				_cpDefinitionLocalService.getCPDefinition(cpDefinitionId);

			commerceWishListItemPersistence.removeByCProductId(
				cpDefinition.getCProductId());
		}
		catch (PortalException pe) {
			if (_log.isWarnEnabled()) {
				_log.error(pe, pe);
			}
		}
	}

	/**
	 * @deprecated As of 1.1
	 */
	@Deprecated
	@Override
	public void deleteCommerceWishListItemsByCPInstanceId(long cpInstanceId) {
		try {
			CPInstance cpInstance = _cpInstanceLocalService.getCPInstance(
				cpInstanceId);

			commerceWishListItemPersistence.removeByCPInstanceUuid(
				cpInstance.getUuid());
		}
		catch (PortalException pe) {
			if (_log.isWarnEnabled()) {
				_log.error(pe, pe);
			}
		}
	}

	@Override
	public List<CommerceWishListItem> getCommerceWishListItems(
		long commerceWishListId, int start, int end,
		OrderByComparator<CommerceWishListItem> orderByComparator) {

		return commerceWishListItemPersistence.findByCommerceWishListId(
			commerceWishListId, start, end, orderByComparator);
	}

	@Override
	public int getCommerceWishListItemsCount(long commerceWishListId) {
		return commerceWishListItemPersistence.countByCommerceWishListId(
			commerceWishListId);
	}

	protected void validate(
			CommerceWishList commerceWishList, long cpDefinitionId,
			long cpInstanceId)
		throws PortalException {

		if (commerceWishList.getUserId() == 0) {
			int count =
				commerceWishListItemPersistence.countByCommerceWishListId(
					commerceWishList.getCommerceWishListId());

			if (count >=
					_commerceWishListConfiguration.
						guestWishListItemMaxAllowed()) {

				throw new GuestWishListItemMaxAllowedException();
			}
		}

		CPDefinition cpDefinition = _cpDefinitionLocalService.getCPDefinition(
			cpDefinitionId);

		if (cpInstanceId > 0) {
			CPInstance cpInstance = _cpInstanceLocalService.getCPInstance(
				cpInstanceId);

			if (cpInstance.getCPDefinitionId() !=
					cpDefinition.getCPDefinitionId()) {

				throw new NoSuchCPInstanceException(
					StringBundler.concat(
						"CPInstance ", cpInstance.getCPInstanceId(),
						" belongs to a different CPDefinition than ",
						cpDefinition.getCPDefinitionId()));
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceWishListItemLocalServiceImpl.class);

	@ServiceReference(type = CommerceWishListConfiguration.class)
	private CommerceWishListConfiguration _commerceWishListConfiguration;

	@ServiceReference(type = CPDefinitionLocalService.class)
	private CPDefinitionLocalService _cpDefinitionLocalService;

	@ServiceReference(type = CPInstanceLocalService.class)
	private CPInstanceLocalService _cpInstanceLocalService;

}