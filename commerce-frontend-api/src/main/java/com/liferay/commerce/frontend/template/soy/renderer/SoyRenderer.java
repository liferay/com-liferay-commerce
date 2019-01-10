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

package com.liferay.commerce.frontend.template.soy.renderer;

import com.liferay.portal.kernel.template.TemplateException;

import java.io.IOException;
import java.io.Writer;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Iván Zaera Avellón
 */
public interface SoyRenderer {

	public void renderSoy(
			HttpServletRequest request, HttpServletResponse response,
			String templateNamespace, Map<String, ?> context)
		throws IOException, TemplateException;

	public void renderSoy(
			HttpServletRequest request, Writer writer, String templateNamespace,
			Map<String, ?> context)
		throws TemplateException;

}