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

package com.liferay.commerce.data.integration.apio.internal.uri.mapper;

import com.liferay.apio.architect.functional.Try;
import com.liferay.apio.architect.uri.Path;
import com.liferay.apio.architect.uri.mapper.PathIdentifierMapper;
import com.liferay.commerce.data.integration.apio.identifier.ClassPKExternalReferenceCode;
import com.liferay.portal.kernel.util.GetterUtil;

import javax.ws.rs.BadRequestException;

import org.osgi.service.component.annotations.Component;

/**
 * @author Rodrigo Guedes de Souza
 */
@Component(immediate = true, service = PathIdentifierMapper.class)
public class ClassPKExternalReferenceCodeIdentifierMapper
	implements PathIdentifierMapper<ClassPKExternalReferenceCode> {

	@Override
	public ClassPKExternalReferenceCode map(Path path) {
		String id = path.getId();

		String[] components = id.split(":");

		if (components.length > 2) {
			throw new BadRequestException(
				id + " should be a string with the form " +
					"\"classPK:externalReferenceCode\"");
		}

		long classPK = _getAsLong(components[0]);

		String externalReferenceCode = null;

		if (components.length == 2) {
			externalReferenceCode = components[1];
		}

		return ClassPKExternalReferenceCode.create(
			classPK, externalReferenceCode);
	}

	@Override
	public Path map(
		String name,
		ClassPKExternalReferenceCode classPKExternalReferenceCode) {

		String id =
			classPKExternalReferenceCode.getClassPK() + ":" +
				classPKExternalReferenceCode.getExternalReferenceCode();

		return new Path(name, id);
	}

	private long _getAsLong(String string) {
		return Try.fromFallible(
			() -> GetterUtil.getLong(string)
		).orElseThrow(
			() -> new BadRequestException(
				"Unable to convert " + string + " to a long")
		);
	}

}