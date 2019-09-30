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

package com.liferay.commerce.internal.model.listener;

import com.liferay.commerce.account.exception.CommerceAccountOrdersException;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.service.CommerceOrderLocalService;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alec Sloan
 */
@Component(immediate = true, service = ModelListener.class)
public class CommerceAccountModelListener
	extends BaseModelListener<CommerceAccount> {

	@Override
	public void onBeforeRemove(CommerceAccount commerceAccount) {
		try {
			int accountOrders =
				_commerceOrderLocalService.
					getCommerceOrdersCountByCommerceAccountId(
						commerceAccount.getCommerceAccountId());

			if (accountOrders > 0) {
				throw new CommerceAccountOrdersException();
			}
		}
		catch (CommerceAccountOrdersException caoe) {
			_log.error(caoe, caoe);

			throw new ModelListenerException(caoe);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceAccountModelListener.class);

	@Reference
	private CommerceOrderLocalService _commerceOrderLocalService;

}