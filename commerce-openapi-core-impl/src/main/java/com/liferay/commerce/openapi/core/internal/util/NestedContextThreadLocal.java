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

package com.liferay.commerce.openapi.core.internal.util;

import com.liferay.commerce.openapi.core.internal.context.NestedContext;
import com.liferay.petra.lang.CentralizedThreadLocal;

/**
 * @author Ivica Cardic
 */
public class NestedContextThreadLocal {

	public static NestedContext getNestedContext() {
		return _nestedContextThreadLocal.get();
	}

	public static void setNestedContext(NestedContext nestedContext) {
		_nestedContextThreadLocal.set(nestedContext);
	}

	private static final ThreadLocal<NestedContext> _nestedContextThreadLocal =
		new CentralizedThreadLocal<>(
			NestedContextThreadLocal.class + "._nestedContextThreadLocal");

}