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

package com.liferay.commerce.product.util;

import com.liferay.commerce.product.model.CPRule;
import com.liferay.petra.lang.CentralizedThreadLocal;

import java.util.Collections;
import java.util.List;

/**
 * @author Andrea Di Giorgi
 */
public class CPRulesThreadLocal {

	public static List<CPRule> getCPRules() {
		return _cpRules.get();
	}

	public static void setCPRules(List<CPRule> cpRules) {
		if (cpRules != null) {
			_cpRules.set(Collections.unmodifiableList(cpRules));

			return;
		}

		_cpRules.set(null);
	}

	private static final ThreadLocal<List<CPRule>> _cpRules =
		new CentralizedThreadLocal<>(
			CPRulesThreadLocal.class.getName() + "._cpRules");

}