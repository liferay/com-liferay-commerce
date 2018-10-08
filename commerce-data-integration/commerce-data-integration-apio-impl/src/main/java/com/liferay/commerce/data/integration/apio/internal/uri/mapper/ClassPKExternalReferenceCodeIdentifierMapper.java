/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.commerce.data.integration.apio.internal.uri.mapper;

import com.liferay.apio.architect.functional.Try;
import com.liferay.apio.architect.uri.Path;
import com.liferay.apio.architect.uri.mapper.PathIdentifierMapper;
import com.liferay.commerce.data.integration.apio.identifiers.ClassPKExternalReferenceCode;
import com.liferay.portal.kernel.util.GetterUtil;

import javax.ws.rs.BadRequestException;

import org.osgi.service.component.annotations.Component;

/**
 * @author Rodrigo Guedes de Souza
 */
@Component(immediate = true)
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