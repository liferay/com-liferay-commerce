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

import java.util.Arrays;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.RecursiveToStringStyle;

/**
 * @author Luca Pellizzon
 */
public final class SealStringStyle extends RecursiveToStringStyle {

	public SealStringStyle() {
		setUseClassName(false);
		setUseIdentityHashCode(false);
		setUseFieldNames(false);
		setNullText(StringUtils.EMPTY);
		setContentStart(StringUtils.EMPTY);
		setContentEnd(StringUtils.EMPTY);
		setFieldSeparator(StringUtils.EMPTY);
		setArrayStart(StringUtils.EMPTY);
		setArrayEnd(StringUtils.EMPTY);
		setArraySeparator(StringUtils.EMPTY);
	}

	@Override
	public void appendDetail(
		StringBuffer buffer, String fieldName, Object value) {

		if (!ClassUtils.isPrimitiveWrapper(value.getClass()) &&
			!String.class.equals(value.getClass()) &&
			accept(value.getClass())) {

			buffer.append(
				AlphabeticalReflectionToStringBuilder.toString(value, this));
		}
		else {
			super.appendDetail(buffer, fieldName, value);
		}
	}

	@Override
	protected boolean accept(Class<?> clazz) {
		Package classPackage = clazz.getPackage();

		String packageName = classPackage.getName();

		if (!clazz.isEnum() && packageName.contains("worldline.sips")) {
			return true;
		}

		return false;
	}

	@Override
	protected void appendDetail(
		StringBuffer buffer, String fieldName, Object[] array) {

		Arrays.sort(array);
		super.appendDetail(buffer, fieldName, array);
	}

}