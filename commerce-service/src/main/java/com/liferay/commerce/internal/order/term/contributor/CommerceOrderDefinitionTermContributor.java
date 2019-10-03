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

package com.liferay.commerce.internal.order.term.contributor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.commerce.account.constants.CommerceAccountConstants;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.constants.CommerceDefinitionTermConstants;
import com.liferay.commerce.constants.CommerceOrderConstants;
import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.model.CommerceRegion;
import com.liferay.commerce.order.CommerceDefinitionTermContributor;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Luca Pellizzon
 */
@Component(
	immediate = true,
	property = {
		"commerce.definition.term.contributor.key=" + CommerceOrderDefinitionTermContributor.KEY,
		"commerce.notification.type.key=" + CommerceOrderConstants.ORDER_NOTIFICATION_AWAITING_SHIPMENT,
		"commerce.notification.type.key=" + CommerceOrderConstants.ORDER_NOTIFICATION_COMPLETED,
		"commerce.notification.type.key=" + CommerceOrderConstants.ORDER_NOTIFICATION_PARTIALLY_SHIPPED,
		"commerce.notification.type.key=" + CommerceOrderConstants.ORDER_NOTIFICATION_PLACED,
		"commerce.notification.type.key=" + CommerceOrderConstants.ORDER_NOTIFICATION_SHIPPED,
		"commerce.notification.type.key=" + CommerceOrderConstants.ORDER_NOTIFICATION_TRANSMITTED
	},
	service = CommerceDefinitionTermContributor.class
)
public class CommerceOrderDefinitionTermContributor
	implements CommerceDefinitionTermContributor {

	public static final String KEY =
		CommerceDefinitionTermConstants.
			BODY_AND_SUBJECT_DEFINITION_TERMS_CONTRIBUTOR;

	@Override
	public Map<String, String> getDefinitionTerms(Locale locale) {
		Map<String, String> map = new HashMap<>();

		List<String> terms = getTerms();

		for (String term : terms) {
			map.put(term, getLabel(term, locale));
		}

		return map;
	}

	@Override
	public String getFilledTerm(String term, Object object, Locale locale)
		throws PortalException {

		if (!(object instanceof CommerceOrder)) {
			return term;
		}

		CommerceOrder commerceOrder = (CommerceOrder)object;

		if (term.equals(_ORDER_CREATOR)) {
			CommerceAccount commerceAccount =
				commerceOrder.getCommerceAccount();

			if (commerceAccount.getType() ==
					CommerceAccountConstants.ACCOUNT_TYPE_PERSONAL) {

				User user = _userLocalService.getUser(
					commerceAccount.getUserId());

				return user.getFullName(true, true);
			}

			return commerceAccount.getName();
		}

		if (term.equals(_ORDER_ID)) {
			return String.valueOf(commerceOrder.getCommerceOrderId());
		}
		
		if (term.equals(_ORDER_ITEMS)) {
			return getOrderItemsTerm(commerceOrder, locale);
		}

		if (term.equals(_ORDER_BILLING_ADDRESS)) {
			if (_log.isDebugEnabled()) {
				_log.debug("Processing billing address term");
			}

			return formatAddressTerm(commerceOrder.getBillingAddress(), locale);
		}

		if (term.equals(_ORDER_SHIPPING_ADDRESS)) {
			if (_log.isDebugEnabled()) {
				_log.debug("Processing shipping address term");
			}

			return formatAddressTerm(
				commerceOrder.getShippingAddress(), locale);
		}

		return term;
	}

	@Override
	public String getLabel(String term, Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(
			resourceBundle, _commerceOrderDefinitionTermsMap.get(term));
	}

	@Override
	public List<String> getTerms() {
		return new ArrayList<>(_commerceOrderDefinitionTermsMap.keySet());
	}
	
	private String formatAddressTerm(
			CommerceAddress commerceAddress, Locale locale) {

			if (commerceAddress == null) {
				if (_log.isDebugEnabled()) {
					_log.debug("The commerce address is null, moving on.");
				}

				return "";
			}

			// Build the address string

			StringBuilder address = new StringBuilder(commerceAddress.getName());

			address.append("<br/>");

			address.append(commerceAddress.getStreet1());
			address.append("<br/>");

			if (!Validator.isBlank(commerceAddress.getStreet2())) {
				address.append(commerceAddress.getStreet2());
				address.append("<br/>");
			}

			if (!Validator.isBlank(commerceAddress.getStreet3())) {
				address.append(commerceAddress.getStreet3());
				address.append("<br/>");
			}

			if (!Validator.isBlank(commerceAddress.getStreet3())) {
				address.append(commerceAddress.getStreet3());
				address.append("<br/>");
			}

			address.append(commerceAddress.getCity());
			address.append(", ");
			address.append(commerceAddress.getZip());
			address.append("<br/>");

			try {
				CommerceRegion commerceRegion = commerceAddress.getCommerceRegion();

				if (commerceRegion != null) {
					address.append(commerceRegion.getName());
					address.append(", ");
				}

				CommerceCountry commerceCountry =
					commerceAddress.getCommerceCountry();

				if (commerceCountry != null) {
					address.append(commerceCountry.getName(locale));
				}
			}
			catch (PortalException pe) {
				if (_log.isDebugEnabled()) {
					_log.debug(
						"It wasn't possible to get either the country or region for this commerce address",
						pe);
				}
			}

			if (_log.isDebugEnabled()) {
				_log.debug(
					"Adding address term to the notification: " +
						address.toString());
			}

			return address.toString();
		}

		private String getOrderItemsTerm(
			CommerceOrder commerceOrder, Locale locale) {

			if (_log.isDebugEnabled()) {
				_log.debug("Processing order items term");
			}

			// Check if we have commerceOrder

			if (commerceOrder == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Trying to get the item list for an order without an order object!");
				}

				return "";
			}

			// Get the order items list

			List<CommerceOrderItem> orderItemsList =
				commerceOrder.getCommerceOrderItems();

			if (ListUtil.isEmpty(orderItemsList)) {
				if (_log.isDebugEnabled()) {
					_log.debug(
						"This order has no linked order items to be included in the mail notification");
				}

				return "";
			}

			// Build the items html table (Header)

			StringBuilder orderItemsTable = new StringBuilder(
				"<table style=\"border: 1px solid black;\">" +
					"<tr><th style=\"border: 1px solid black;\">Product Name</th>" +
						"<th style=\"border: 1px solid black;\">SKU</th>" +
							"<th style=\"border: 1px solid black;\">Quantity</th></tr>");

			// And add the order items

			for (CommerceOrderItem commerceOrderItem : orderItemsList) {
				orderItemsTable.append(
					"<tr><td style=\"border: 1px solid black;\">");
				orderItemsTable.append(commerceOrderItem.getName(locale));
				orderItemsTable.append(
					"</td><td style=\"border: 1px solid black;\">");
				orderItemsTable.append(commerceOrderItem.getSku());
				orderItemsTable.append(
					"</td><td style=\"border: 1px solid black;\">");
				orderItemsTable.append(commerceOrderItem.getQuantity());
				orderItemsTable.append("</td></tr>");
			}

			if (_log.isDebugEnabled()) {
				_log.debug(
					"Order items table built successfully: " +
						orderItemsTable.toString());
			}

			return orderItemsTable.toString();
		}

	private static final String _ORDER_BILLING_ADDRESS =
			"[%ORDER_BILLING_ADDRESS%]";
	
	private static final String _ORDER_CREATOR = "[%ORDER_CREATOR%]";

	private static final String _ORDER_ID = "[%ORDER_ID%]";
	
	private static final String _ORDER_ITEMS = "[%ORDER_ITEMS%]";

	private static final String _ORDER_SHIPPING_ADDRESS =
		"[%ORDER_SHIPPING_ADDRESS%]";

	private static final Map<String, String> _commerceOrderDefinitionTermsMap =
		new HashMap<String, String>() {
			{
				put(_ORDER_BILLING_ADDRESS, "order-billing-address-definition-term");
				put(_ORDER_CREATOR, "order-creator-definition-term");
				put(_ORDER_ID, "order-id-definition-term");
				put(_ORDER_ITEMS, "order-items-definition-term");
				put(_ORDER_SHIPPING_ADDRESS, "order-shipping-address-definition-term");
			}
		};
		
	private static final Log _log = LogFactoryUtil.getLog(CommerceOrderDefinitionTermContributor.class);

	@Reference
	private UserLocalService _userLocalService;

}