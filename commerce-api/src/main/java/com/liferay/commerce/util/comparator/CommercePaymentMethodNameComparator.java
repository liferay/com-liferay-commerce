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

package com.liferay.commerce.util.comparator;

import com.liferay.commerce.payment.method.CommercePaymentMethod;
import com.liferay.portal.kernel.util.CollatorUtil;

import java.io.Serializable;

import java.text.Collator;

import java.util.Comparator;
import java.util.Locale;

/**
 * @author Andrea Di Giorgi
 */
public class CommercePaymentMethodNameComparator
	implements Comparator<CommercePaymentMethod>, Serializable {

	public CommercePaymentMethodNameComparator(Locale locale) {
		_locale = locale;
	}

	@Override
	public int compare(
		CommercePaymentMethod commercePaymentMethod1,
		CommercePaymentMethod commercePaymentMethod2) {

		Collator collator = CollatorUtil.getInstance(_locale);

		String name1 = commercePaymentMethod1.getName(_locale);
		String name2 = commercePaymentMethod2.getName(_locale);

		return collator.compare(name1, name2);
	}

	private final Locale _locale;

}