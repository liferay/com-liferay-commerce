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
import com.liferay.taglib.util.IncludeTag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

/**
 * @author Fabio Diego Mastrorilli
 */
public class InfoBoxTag extends IncludeTag {

	@Override
	public int doEndTag() throws JspException {
		setAttributeNamespace(_ATTRIBUTE_NAMESPACE);

		return super.doEndTag();
	}

	@Override
	public int doStartTag() throws JspException {
		setAttributeNamespace(_ATTRIBUTE_NAMESPACE);

		setNamespacedAttribute(request, "elementClasses", _elementClasses);
		setNamespacedAttribute(request, "title", _title);
		setNamespacedAttribute(request, "actionTargetId", _actionTargetId);
		setNamespacedAttribute(request, "actionLabel", _actionLabel);
		setNamespacedAttribute(request, "actionUrl", _actionUrl);

		super.doStartTag();

		return EVAL_BODY_INCLUDE;
	}

	public String getActionLabel() {
		return _actionLabel;
	}

	public String getActionTargetId() {
		return _actionTargetId;
	}

	public String getActionUrl() {
		return _actionUrl;
	}

	public String getElementClasses() {
		return _elementClasses;
	}

	public String getTitle() {
		return _title;
	}

	public void setActionLabel(String actionLabel) {
		_actionLabel = actionLabel;
	}

	public void setActionTargetId(String actionTargetId) {
		_actionTargetId = actionTargetId;
	}

	public void setActionUrl(String actionUrl) {
		_actionUrl = actionUrl;
	}

	public void setElementClasses(String elementClasses) {
		_elementClasses = elementClasses;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	public void setTitle(String title) {
		_title = title;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_actionLabel = null;
		_actionTargetId = null;
		_actionUrl = null;
		_elementClasses = null;
		_title = null;
	}

	@Override
	protected String getEndPage() {
		return _END_PAGE;
	}

	@Override
	protected String getStartPage() {
		return _START_PAGE;
	}

	private static final String _ATTRIBUTE_NAMESPACE =
		"liferay-commerce:info-box:";

	private static final String _END_PAGE = "/info_box/end.jsp";

	private static final String _START_PAGE = "/info_box/start.jsp";

	private String _actionLabel;
	private String _actionTargetId;
	private String _actionUrl;
	private String _elementClasses;
	private String _title;

}