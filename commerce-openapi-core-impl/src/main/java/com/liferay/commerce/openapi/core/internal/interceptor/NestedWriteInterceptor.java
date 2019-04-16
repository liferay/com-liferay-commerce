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

package com.liferay.commerce.openapi.core.internal.interceptor;

import com.liferay.commerce.openapi.core.annotation.Nested;
import com.liferay.commerce.openapi.core.constants.OpenApiPropsKeys;
import com.liferay.commerce.openapi.core.internal.context.NestedContext;
import com.liferay.commerce.openapi.core.internal.param.converter.DateParameterConverter;
import com.liferay.commerce.openapi.core.internal.servlet.NestedHttpServletRequestWrapper;
import com.liferay.commerce.openapi.core.internal.util.NestedContextThreadLocal;
import com.liferay.commerce.openapi.core.model.CollectionDTO;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.IOException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import java.math.BigDecimal;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

import org.apache.cxf.jaxrs.ext.ContextProvider;
import org.apache.cxf.message.Message;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
 * @author Ivica Cardic
 */
@Component(
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_SELECT + "=(" + OpenApiPropsKeys.NESTED_FILTER_ENABLED + ")",
		JaxrsWhiteboardConstants.JAX_RS_EXTENSION + "=true"
	},
	service = WriterInterceptor.class
)
@Provider
public class NestedWriteInterceptor implements WriterInterceptor {

	@Activate
	@Modified
	public void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;
	}

	@Override
	public void aroundWriteTo(WriterInterceptorContext context)
		throws IOException, WebApplicationException {

		NestedContext nestedContext =
			NestedContextThreadLocal.getNestedContext();

		if (nestedContext == null) {
			context.proceed();

			return;
		}

		try {
			_setFieldValues(context.getEntity(), nestedContext);
		}
		catch (Exception e) {
			_log.error(e.getMessage(), e);

			throw new WebApplicationException(e);
		}

		context.proceed();
	}

	protected List<ContextProvider> getContextProviders()
		throws InvalidSyntaxException {

		List<ContextProvider> contextProviders = new ArrayList<>();

		Collection<ServiceReference<ContextProvider>> serviceReferences =
			_bundleContext.getServiceReferences(ContextProvider.class, null);

		for (ServiceReference<ContextProvider> serviceReference :
				serviceReferences) {

			ContextProvider contextProvider = _bundleContext.getService(
				serviceReference);

			contextProviders.add(contextProvider);
		}

		return contextProviders;
	}

	protected HttpServletRequest getHttpServletRequest(Message message) {
		return (HttpServletRequest)message.getContextualProperty(
			"HTTP.REQUEST");
	}

	protected List<Object> getResources() throws InvalidSyntaxException {
		List<Object> resources = new ArrayList<>();

		ServiceReference<?>[] serviceReferences =
			_bundleContext.getAllServiceReferences(
				null, "(osgi.jaxrs.resource=true)");

		for (ServiceReference<?> serviceReference : serviceReferences) {
			resources.add(_bundleContext.getService(serviceReference));
		}

		return resources;
	}

	private Object _checkReturnType(Class<?> fieldType, Object result) {
		if (result instanceof CollectionDTO) {
			CollectionDTO collectionDTO = (CollectionDTO)result;

			result = collectionDTO.getItems();
		}

		if (fieldType.isArray() && (result instanceof Collection)) {
			Collection collection = (Collection)result;

			result = Array.newInstance(
				fieldType.getComponentType(), collection.size());

			Iterator iterator = collection.iterator();

			int i = 0;

			while (iterator.hasNext()) {
				Array.set(result, i++, iterator.next());
			}
		}

		return result;
	}

	private Object _convert(String value, Class<?> type) {
		if (value == null) {
			return null;
		}

		if (type == String.class) {
			return value;
		}
		else if (type == BigDecimal.class) {
			return new BigDecimal(value);
		}
		else if (type == Boolean.class) {
			return Boolean.valueOf(value);
		}
		else if (type == Byte.class) {
			return Byte.valueOf(value);
		}
		else if (type == Character.class) {
			return value.charAt(0);
		}
		else if (type == Date.class) {
			return _dateParameterConverter.fromString(value);
		}
		else if (type == Double.class) {
			return Double.valueOf(value);
		}
		else if (type == Float.class) {
			return Float.valueOf(value);
		}
		else if (type == Integer.class) {
			return Integer.valueOf(value);
		}
		else if (type == LocalDate.class) {
			return LocalDate.parse(value);
		}
		else if (type == LocalDateTime.class) {
			return LocalDateTime.parse(value);
		}
		else if (type == Long.class) {
			return Long.valueOf(value);
		}
		else if (type == Short.class) {
			return Short.valueOf(value);
		}
		else {
			throw new IllegalArgumentException(
				String.format(
					"value %s cannot be converted to %s", value, type));
		}
	}

	private Method _getAnnotatedMethod(String fieldName, Class<?> clazz) {
		Method[] methods = clazz.getDeclaredMethods();

		for (Method method : methods) {
			if (method.isAnnotationPresent(Nested.class)) {
				Nested nested = method.getAnnotation(Nested.class);

				if (Objects.equals(fieldName, nested.value())) {
					return method;
				}
			}
		}

		return null;
	}

	private Object _getContext(Class<?> contextClass, Message message)
		throws InvalidSyntaxException {

		ContextProvider contextProvider = _getContextProvider(contextClass);

		if (contextProvider != null) {
			return contextProvider.createContext(message);
		}

		return null;
	}

	private ContextProvider _getContextProvider(Class<?> contextClass)
		throws InvalidSyntaxException {

		for (ContextProvider contextProvider : getContextProviders()) {
			if (_isActualContextProvider(contextProvider, contextClass)) {
				return contextProvider;
			}
		}

		return null;
	}

	private Field _getField(Class<?> entityClass, String fieldName) {
		for (Field declaredField : entityClass.getDeclaredFields()) {
			if (Objects.equals(declaredField.getName(), fieldName) ||
				Objects.equals(declaredField.getName(), "_" + fieldName)) {

				return declaredField;
			}
		}

		return null;
	}

	private Object _getFieldValue(String fieldName, NestedContext nestedContext)
		throws Exception {

		for (Object resource : getResources()) {
			Method method = _getAnnotatedMethod(fieldName, resource.getClass());

			if (method == null) {
				continue;
			}

			_setResourceContexts(resource, nestedContext.getMessage());

			Parameter[] parameters = _getInterfaceMethodParameters(
				method, resource.getClass());

			Object[] args = _getMethodArgs(
				fieldName, parameters, nestedContext);

			return method.invoke(resource, args);
		}

		return null;
	}

	private Parameter[] _getInterfaceMethodParameters(
			Method implMethod, Class<?> clazz)
		throws NoSuchMethodException {

		Class<?>[] interfaceClasses = clazz.getInterfaces();

		Class<?> interfaceClass = interfaceClasses[0];

		Method interfaceMethod = interfaceClass.getMethod(
			implMethod.getName(), implMethod.getParameterTypes());

		return interfaceMethod.getParameters();
	}

	private Object[] _getMethodArgs(
			String fieldName, Parameter[] parameters,
			NestedContext nestedContext)
		throws Exception {

		Object[] args = new Object[parameters.length];

		MultivaluedMap<String, String> pathParameters =
			nestedContext.getPathParameters();
		MultivaluedMap<String, String> queryParameters =
			nestedContext.getQueryParameters();

		for (int i = 0; i < parameters.length; i++) {
			Parameter parameter = parameters[i];

			Annotation[] annotations = parameter.getAnnotations();

			if (annotations.length == 0) {
				continue;
			}

			if (annotations[0] instanceof PathParam) {
				PathParam pathParam = (PathParam)annotations[0];

				args[i] = _convert(
					pathParameters.getFirst(pathParam.value()),
					parameter.getType());
			}
			else if (annotations[0] instanceof QueryParam) {
				QueryParam queryParam = (QueryParam)annotations[0];

				args[i] = _convert(
					queryParameters.getFirst(
						fieldName + "." + queryParam.value()),
					parameter.getType());
			}
			else if (annotations[0] instanceof Context) {
				Message message = _getNestedAwareMessage(
					fieldName, nestedContext.getMessage());

				args[i] = _getContext(parameter.getType(), message);

				_resetNestedAwareMessage(message);
			}
			else {
				args[i] = null;
			}
		}

		return args;
	}

	private Message _getNestedAwareMessage(String fieldName, Message message) {
		message.put(
			"HTTP.REQUEST",
			new NestedHttpServletRequestWrapper(
				fieldName, getHttpServletRequest(message)));

		return message;
	}

	private <T> boolean _isActualContextProvider(
		ContextProvider contextProvider, Class<T> contextClass) {

		Class<? extends ContextProvider> contextProviderClass =
			contextProvider.getClass();

		Type[] genericInterfaceTypes =
			contextProviderClass.getGenericInterfaces();

		for (Type type : genericInterfaceTypes) {
			if (!(type instanceof ParameterizedType)) {
				continue;
			}

			ParameterizedType parameterizedType = (ParameterizedType)type;

			Type[] typeArguments = parameterizedType.getActualTypeArguments();

			if (typeArguments[0] == contextClass) {
				return true;
			}
		}

		return false;
	}

	private void _resetNestedAwareMessage(Message message) {
		NestedHttpServletRequestWrapper requestWrapper =
			(NestedHttpServletRequestWrapper)message.getContextualProperty(
				"HTTP.REQUEST");

		message.put("HTTP.REQUEST", requestWrapper.getRequest());
	}

	private void _setFieldValues(Object entity, NestedContext nestedContext)
		throws Exception {

		for (String fieldName : nestedContext.getFieldNames()) {
			Field field = _getField(entity.getClass(), fieldName);

			if (field == null) {
				continue;
			}

			field.setAccessible(true);

			field.set(
				entity,
				_checkReturnType(
					field.getType(), _getFieldValue(fieldName, nestedContext)));
		}
	}

	private void _setResourceContexts(Object resource, Message message)
		throws Exception {

		Class<?> resourceClass = resource.getClass();

		Field[] fields = resourceClass.getDeclaredFields();

		for (Field field : fields) {
			Annotation[] annotations = field.getAnnotations();

			if (annotations.length == 0) {
				continue;
			}

			if (annotations[0] instanceof Context) {
				field.setAccessible(true);

				field.set(resource, _getContext(field.getType(), message));
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		NestedWriteInterceptor.class);

	private BundleContext _bundleContext;
	private final DateParameterConverter _dateParameterConverter =
		new DateParameterConverter();

}