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

package com.liferay.commerce.internal.notification.type;

import com.liferay.commerce.account.constants.CommerceAccountConstants;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.model.CommerceSubscriptionEntry;
import com.liferay.commerce.notification.type.CommerceNotificationType;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.service.CommerceOrderItemLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = {
		"commerce.notification.type.key=" + SubscriptionRenewedCommerceNotificationTypeImpl.KEY,
		"commerce.notification.type.order:Integer=20"
	},
	service = CommerceNotificationType.class
)
public class SubscriptionRenewedCommerceNotificationTypeImpl
	implements CommerceNotificationType {

	public static final String KEY = "subscription-renewed";

	@Override
	public String getClassName(Object object) {
		if (!(object instanceof CommerceSubscriptionEntry)) {
			return null;
		}

		return CommerceSubscriptionEntry.class.getName();
	}

	@Override
	public long getClassPK(Object object) {
		if (!(object instanceof CommerceSubscriptionEntry)) {
			return 0;
		}

		CommerceSubscriptionEntry commerceSubscriptionEntry =
			(CommerceSubscriptionEntry)object;

		return commerceSubscriptionEntry.getCommerceSubscriptionEntryId();
	}

	@Override
	public Map<String, String> getDefinitionTerms(Locale locale) {
		Map<String, String> map = new HashMap<>();

		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		map.put(
			_ORDER_CREATOR,
			LanguageUtil.get(resourceBundle, "order-creator-definition-term"));
		map.put(
			_ORDER_ID,
			LanguageUtil.get(resourceBundle, "order-id-definition-term"));
		map.put(
			_PRODUCT_NAME, LanguageUtil.get(resourceBundle, "product-name"));

		return map;
	}

	@Override
	public String getFilledTerm(String term, Object object, Locale locale)
		throws PortalException {

		if (!(object instanceof CommerceSubscriptionEntry)) {
			return term;
		}

		CommerceSubscriptionEntry commerceSubscriptionEntry =
			(CommerceSubscriptionEntry)object;

		if (_commerceOrderItem == null) {
			_commerceOrderItem =
				_commerceOrderItemLocalService.getCommerceOrderItem(
					commerceSubscriptionEntry.getCommerceOrderItemId());
		}

		if (term.equals(_ORDER_CREATOR)) {
			CommerceOrder commerceOrder = _commerceOrderItem.getCommerceOrder();

			CommerceAccount commerceAccount =
				commerceOrder.getCommerceAccount();

			if (commerceAccount.getType() ==
					CommerceAccountConstants.ACCOUNT_TYPE_PERSONAL) {

				User user = _userLocalService.getUser(
					GetterUtil.getLong(commerceAccount.getName()));

				return user.getFullName(true, true);
			}

			return commerceAccount.getName();
		}

		if (term.equals(_ORDER_ID)) {
			return String.valueOf(_commerceOrderItem.getCommerceOrderId());
		}

		if (term.equals(_PRODUCT_NAME)) {
			CPDefinition cpDefinition = _commerceOrderItem.getCPDefinition();

			return cpDefinition.getName(LocaleUtil.toLanguageId(locale));
		}

		return term;
	}

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(resourceBundle, KEY);
	}

	private static final String _ORDER_CREATOR = "[%ORDER_CREATOR%]";

	private static final String _ORDER_ID = "[%ORDER_ID%]";

	private static final String _PRODUCT_NAME = "[%PRODUCT_NAME%]";

	private CommerceOrderItem _commerceOrderItem;

	@Reference
	private CommerceOrderItemLocalService _commerceOrderItemLocalService;

	@Reference
	private UserLocalService _userLocalService;

}