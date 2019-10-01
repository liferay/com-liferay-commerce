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

import com.liferay.commerce.constants.CommerceDefinitionTermConstants;
import com.liferay.commerce.constants.CommerceOrderConstants;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.notification.type.CommerceNotificationType;
import com.liferay.commerce.order.CommerceDefinitionTermContributor;
import com.liferay.commerce.order.CommerceOrderDefinitionTermContributorRegistry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Luca Pellizzon
 */
@Component(
	immediate = true,
	property = {
		"commerce.notification.type.key=" + CommerceOrderConstants.ORDER_NOTIFICATION_COMPLETED,
		"commerce.notification.type.order:Integer=100"
	},
	service = CommerceNotificationType.class
)
public class OrderCompletedCommerceNotificationTypeImpl
	implements CommerceNotificationType {

	@Override
	public String getClassName(Object object) {
		if (!(object instanceof CommerceOrder)) {
			return null;
		}

		return CommerceOrder.class.getName();
	}

	@Override
	public long getClassPK(Object object) {
		if (!(object instanceof CommerceOrder)) {
			return 0;
		}

		CommerceOrder commerceOrder = (CommerceOrder)object;

		return commerceOrder.getCommerceOrderId();
	}

	@Override
	public Map<String, String> getDefinitionTerms(Locale locale) {
		Map<String, String> map = new HashMap<>();

		CommerceDefinitionTermContributor definitionTermContributor =
			_commerceOrderDefinitionTermContributorRegistry.
				getDefinitionTermContributor(
					CommerceDefinitionTermConstants.
						ORDER_DEFINITION_TERMS_CONTRIBUTOR);

		List<String> terms = definitionTermContributor.getTerms();

		for (String term : terms) {
			map.put(term, definitionTermContributor.getLabel(term, locale));
		}

		return map;
	}

	@Override
	public String getFilledTerm(String term, Object object, Locale locale)
		throws PortalException {

		CommerceDefinitionTermContributor definitionTermContributor =
			_commerceOrderDefinitionTermContributorRegistry.
				getDefinitionTermContributor(
					CommerceDefinitionTermConstants.
						ORDER_DEFINITION_TERMS_CONTRIBUTOR);

		return definitionTermContributor.getFilledTerm(term, object, locale);
	}

	@Override
	public String getKey() {
		return CommerceOrderConstants.ORDER_NOTIFICATION_COMPLETED;
	}

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(
			resourceBundle,
			CommerceOrderConstants.ORDER_NOTIFICATION_COMPLETED);
	}

	@Reference
	private CommerceOrderDefinitionTermContributorRegistry
		_commerceOrderDefinitionTermContributorRegistry;

}