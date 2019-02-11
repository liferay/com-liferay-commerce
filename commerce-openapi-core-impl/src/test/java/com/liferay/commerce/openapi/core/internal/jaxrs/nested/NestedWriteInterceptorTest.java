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

package com.liferay.commerce.openapi.core.internal.jaxrs.nested;

import com.liferay.commerce.openapi.core.internal.context.provider.PaginationContextProvider;
import com.liferay.commerce.openapi.core.internal.jaxrs.nested.util.MockHttpServletRequest;
import com.liferay.commerce.openapi.core.internal.jaxrs.nested.util.ProductDTO;
import com.liferay.commerce.openapi.core.internal.jaxrs.nested.util.ProductOptionDTO;
import com.liferay.commerce.openapi.core.internal.jaxrs.nested.util.ProductResourceImpl;
import com.liferay.commerce.openapi.core.internal.jaxrs.nested.util.SkuDTO;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import java.io.IOException;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.ext.WriterInterceptorContext;

import org.apache.cxf.jaxrs.ext.ContextProvider;
import org.apache.cxf.message.Message;
import org.apache.cxf.message.MessageImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.mockito.Mockito;

import org.osgi.framework.BundleContext;

/**
 * @author Ivica Cardic
 */
public class NestedWriteInterceptorTest {

	@Before
	public void setUp() throws Exception {
		BundleContext bundleContext = Mockito.mock(BundleContext.class);

		_context = Mockito.mock(WriterInterceptorContext.class);

		_productDTO = _getProductDTO();

		Mockito.when(
			_context.getEntity()
		).thenReturn(
			_productDTO
		);

		Mockito.doReturn(
			Arrays.asList(
				new PaginationContextProvider(),
				new MockThemeDisplayContextProvider())
		).when(
			_nestedWriteInterceptor
		).getContextProviders();

		_productResourceImpl = new ProductResourceImpl();

		Mockito.doReturn(
			Collections.singletonList(_productResourceImpl)
		).when(
			_nestedWriteInterceptor
		).getResources();

		_nestedWriteInterceptor.activate(bundleContext);
	}

	@Test
	public void testGetNestedFields() throws Exception {
		Mockito.doReturn(
			new MockHttpServletRequest()
		).when(
			_nestedWriteInterceptor
		).getHttpServletRequest(
			Mockito.any(Message.class)
		);

		NestedContextThreadLocal.setNestedContext(
			new NestedContext(
				Arrays.asList("skus", "productOptions"), new MessageImpl(),
				_getPathParameters(), new MultivaluedHashMap<>()));

		_nestedWriteInterceptor.aroundWriteTo(_context);

		Collection<SkuDTO> skuDTOs = _productDTO.getSkus();

		Assert.assertEquals(skuDTOs.toString(), 4, skuDTOs.size());

		Collection<ProductOptionDTO> productOptionsDTOs =
			_productDTO.getProductOptions();

		Assert.assertEquals(
			productOptionsDTOs.toString(), 3, productOptionsDTOs.size());
	}

	@Test
	public void testGetNestedFieldsEmpty() throws IOException {
		NestedContextThreadLocal.setNestedContext(
			new NestedContext(
				Collections.emptyList(), new MessageImpl(),
				_getPathParameters(), new MultivaluedHashMap<>()));

		_nestedWriteInterceptor.aroundWriteTo(_context);

		Collection<SkuDTO> skuDTOs = _productDTO.getSkus();

		Assert.assertEquals(skuDTOs.toString(), 0, skuDTOs.size());

		NestedContextThreadLocal.setNestedContext(
			new NestedContext(
				Collections.singletonList("nonexistent"), new MessageImpl(),
				_getPathParameters(), new MultivaluedHashMap<>()));

		_nestedWriteInterceptor.aroundWriteTo(_context);

		skuDTOs = _productDTO.getSkus();

		Assert.assertEquals(skuDTOs.toString(), 0, skuDTOs.size());
	}

	@Test
	public void testGetNestedFieldsWithPagination() throws Exception {
		Mockito.doReturn(
			new MockHttpServletRequest(
				"skus", "page", String.valueOf(1), "pageSize",
				String.valueOf(2))
		).when(
			_nestedWriteInterceptor
		).getHttpServletRequest(
			Mockito.any(Message.class)
		);

		NestedContextThreadLocal.setNestedContext(
			new NestedContext(
				Collections.singletonList("skus"), new MessageImpl(),
				_getPathParameters(), new MultivaluedHashMap<>()));

		_nestedWriteInterceptor.aroundWriteTo(_context);

		Collection<SkuDTO> skuDTOs = _productDTO.getSkus();

		Assert.assertEquals(skuDTOs.toString(), 2, skuDTOs.size());
	}

	@Test
	public void testGetNestedFieldsWithQueryParameter() throws IOException {
		Mockito.doReturn(
			new MockHttpServletRequest("productOptions")
		).when(
			_nestedWriteInterceptor
		).getHttpServletRequest(
			Mockito.any(Message.class)
		);

		MultivaluedHashMap<String, String> queryParameters =
			new MultivaluedHashMap<String, String>() {
				{
					putSingle("productOptions.name", "test2");
				}
			};

		NestedContextThreadLocal.setNestedContext(
			new NestedContext(
				Collections.singletonList("productOptions"), new MessageImpl(),
				_getPathParameters(), queryParameters));

		_nestedWriteInterceptor.aroundWriteTo(_context);

		Collection<ProductOptionDTO> productOptionDTOs =
			_productDTO.getProductOptions();

		Assert.assertEquals(
			productOptionDTOs.toString(), 1, productOptionDTOs.size());

		ProductOptionDTO productOptionDTO =
			((List<ProductOptionDTO>)productOptionDTOs).get(0);

		Assert.assertEquals("test2", productOptionDTO.getName());
	}

	@Test
	public void testInjectContextResourceFields() throws Exception {
		Mockito.doReturn(
			new MockHttpServletRequest("skus")
		).when(
			_nestedWriteInterceptor
		).getHttpServletRequest(
			Mockito.any(Message.class)
		);

		NestedContextThreadLocal.setNestedContext(
			new NestedContext(
				Arrays.asList("skus", "productOptions"), new MessageImpl(),
				_getPathParameters(), new MultivaluedHashMap<>()));

		Assert.assertNull(_productResourceImpl.themeDisplay);

		_nestedWriteInterceptor.aroundWriteTo(_context);

		Assert.assertNotNull(_productResourceImpl.themeDisplay);
	}

	private MultivaluedHashMap<String, String> _getPathParameters() {
		return new MultivaluedHashMap<String, String>() {
			{
				putSingle("id", "1");
			}
		};
	}

	private ProductDTO _getProductDTO() {
		ProductDTO productDTO = new ProductDTO();

		productDTO.setId(1L);

		return productDTO;
	}

	private WriterInterceptorContext _context;
	private final NestedWriteInterceptor _nestedWriteInterceptor = Mockito.spy(
		new NestedWriteInterceptor());
	private ProductDTO _productDTO;
	private ProductResourceImpl _productResourceImpl;

	private class MockThemeDisplayContextProvider
		implements ContextProvider<ThemeDisplay> {

		@Override
		public ThemeDisplay createContext(Message message) {
			return new ThemeDisplay();
		}

	}

}