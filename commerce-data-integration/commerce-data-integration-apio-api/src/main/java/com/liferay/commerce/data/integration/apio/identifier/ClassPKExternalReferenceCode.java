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

package com.liferay.commerce.data.integration.apio.identifiers;

import aQute.bnd.annotation.ProviderType;

/**
 * @author Rodrigo Guedes de Souza
 * @author Zoltán Takács
 */
@ProviderType
public interface ClassPKExternalReferenceCode {

	public static ClassPKExternalReferenceCode create(
		long classPK, String externalReferenceCode) {

		if ((classPK == 0) && (externalReferenceCode == null)) {
			throw new UnsupportedOperationException(
				"Class Primary Key cannot be 0");
		}

		return new ClassPKExternalReferenceCode() {

			@Override
			public long getClassPK() {
				return classPK;
			}

			@Override
			public String getExternalReferenceCode() {
				if (externalReferenceCode == null) {
					return "";
				}

				return externalReferenceCode;
			}

			@Override
			public String toString() {
				StringBuilder sb = new StringBuilder("{");

				sb.append("classPK=");
				sb.append(classPK);
				sb.append(", externalReferenceCode=");
				sb.append(externalReferenceCode);
				sb.append("}");

				return sb.toString();
			}

		};
	}

	public long getClassPK();

	public String getExternalReferenceCode();

}