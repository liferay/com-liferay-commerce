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

package com.liferay.commerce.payment.method.mercanet.internal.worldline.sips.helper;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

import java.util.Arrays;
import java.util.Comparator;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Luca Pellizzon
 */
public class SortedReflectionToStringBuilder extends ReflectionToStringBuilder {

	public SortedReflectionToStringBuilder(Object object, ToStringStyle style) {
		super(object, style);
	}

	public void setComparator(Comparator<Field> comparator) {
		_comparator = comparator;
	}

	@Override
	protected void appendFieldsIn(Class<?> clazz) {
		if (clazz.isArray()) {
			reflectionAppendArray(getObject());

			return;
		}

		Field[] fields = clazz.getDeclaredFields();

		if (_comparator != null) {
			Arrays.sort(fields, _comparator);
		}

		AccessibleObject.setAccessible(fields, true);

		for (final Field field : fields) {
			final String fieldName = field.getName();

			if (accept(field)) {
				try {
					final Object fieldValue = getValue(field);

					if (!isExcludeNullValues() || (fieldValue != null)) {
						append(fieldName, fieldValue);
					}
				}
				catch (IllegalAccessException iae) {
					throw new InternalError(
						"Unexpected IllegalAccessException: " +
							iae.getMessage());
				}
			}
		}
	}

	private Comparator<Field> _comparator;

}