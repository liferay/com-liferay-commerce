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

package com.liferay.commerce.frontend.taglib.servlet.taglib;

import com.liferay.commerce.frontend.taglib.internal.servlet.ServletContextUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.taglib.util.IncludeTag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

/**
 * @author Fabio Diego Mastrorilli
 */
public class PanelHiddenContentTag extends IncludeTag {

	@Override
	public int doStartTag() throws JspException {
		setAttributeNamespace(_ATTRIBUTE_NAMESPACE);
		setNamespacedAttribute(request, "wrapperId", _wrapperId);
		super.doStartTag();
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doEndTag() throws JspException {
		setAttributeNamespace(_ATTRIBUTE_NAMESPACE);
		return super.doEndTag();
	}

	@Override
	protected String getEndPage() {
		return _END_PAGE;
	}

	@Override
	protected String getStartPage() {
		return _START_PAGE;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	private static final String _END_PAGE = "/panel_hidden_content/end.jsp";
	private static final String _START_PAGE = "/panel_hidden_content/start.jsp";
	private static final String _ATTRIBUTE_NAMESPACE =
			"liferay-commerce:panel-hidden-content:";
	private static final Log _log = LogFactoryUtil.getLog(PanelHiddenContentTag.class);

	public String getWrapperId() {
		return _wrapperId;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_wrapperId = null;
	}

	private String _wrapperId= null;

	public void setWrapperId(String wrapperId) {
		_wrapperId = wrapperId;
	}

}