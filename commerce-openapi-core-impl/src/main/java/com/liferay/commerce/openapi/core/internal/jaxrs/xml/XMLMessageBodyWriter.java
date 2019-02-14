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

package com.liferay.commerce.openapi.core.internal.jaxrs.xml;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import com.liferay.commerce.openapi.core.constants.OpenApiPropsKeys;

import java.io.IOException;
import java.io.OutputStream;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
 * @author Ivica Cardic
 */
@Component(
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_SELECT + "=(" + OpenApiPropsKeys.MESSAGE_BODY_WRITERS_ENABLED + ")",
		JaxrsWhiteboardConstants.JAX_RS_EXTENSION + "=true"
	},
	service = MessageBodyWriter.class
)
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_XHTML_XML})
@Provider
public class XMLMessageBodyWriter implements MessageBodyWriter<Object> {

	public long getSize(
		Object data, Class<?> type, Type genericType, Annotation[] annotations,
		MediaType mediaType) {

		return -1;
	}

	public boolean isWriteable(
		Class<?> type, Type genericType, Annotation[] annotations,
		MediaType mediaType) {

		if (type == String.class) {
			return false;
		}

		return _XML_MAPPER.canSerialize(type);
	}

	public void writeTo(
			Object data, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> headers, OutputStream out)
		throws IOException, WebApplicationException {

		_XML_MAPPER.writeValue(out, data);

		out.flush();
	}

	private static final XmlMapper _XML_MAPPER = new XmlMapper();

}