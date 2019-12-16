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
public class SidePanelContentTag extends IncludeTag {

	@Override
	public int doEndTag() throws JspException {
		setAttributeNamespace(_ATTRIBUTE_NAMESPACE);

		return super.doEndTag();
	}

	@Override
	public int doStartTag() throws JspException {
		setAttributeNamespace(_ATTRIBUTE_NAMESPACE);

		setNamespacedAttribute(request, "sidePanelId", _sidePanelId);
		setNamespacedAttribute(request, "title", _title);
		setNamespacedAttribute(request, "showCloseButton", _showCloseButton);

		super.doStartTag();

		return EVAL_BODY_INCLUDE;
	}

	public String getSidePanelId() {
		return _sidePanelId;
	}

	public boolean getShowCloseButton() {
		return _showCloseButton;
	}

	public String getTitle() {
		return _title;
	}

	public void setSidePanelId(String sidePanelId) {
		_sidePanelId = sidePanelId;
	}

	public void setShowCloseButton(boolean showCloseButton) {
		_showCloseButton = showCloseButton;
	}

	public void setTitle(String title) {
		_title = title;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_showCloseButton = false;
		_sidePanelId = null;
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
		"liferay-commerce:side-panel-content:";

	private static final String _END_PAGE = "/side_panel_content/end.jsp";

	private static final String _START_PAGE = "/side_panel_content/start.jsp";

	private boolean _showCloseButton;
	private String _sidePanelId;
	private String _title;

}