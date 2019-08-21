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

import com.liferay.commerce.frontend.model.PanelActionModel;
import com.liferay.commerce.frontend.taglib.internal.servlet.ServletContextUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import java.util.List;

/**
 * @author Fabio Diego Mastrorilli
 */
public class PanelTag extends IncludeTag {

	@Override
	public int doStartTag() throws JspException {

		if(_spritemap == null){

			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
			_spritemap = themeDisplay.getPathThemeImages() + "/commerce-icons.svg";
		}

		return super.doStartTag();
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	@Override
	protected void setAttributes(HttpServletRequest httpServletRequest) {
		request.setAttribute("liferay-commerce:header:spritemap", _spritemap);
		request.setAttribute("liferay-commerce:header:title", _title);
		request.setAttribute("liferay-commerce:header:panelAction", _panelAction);
	}

	private static final String _PAGE = "/panel/page.jsp";
	private static final Log _log = LogFactoryUtil.getLog(PanelTag.class);

	public String getTitle() {
		return _title;
	}

	public String getSpritemap() {
		return _spritemap;
	}

	public PanelActionModel getPanelAction() {
		return _panelAction;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_title = null;
		_spritemap = null;
		_panelAction = null;
	}

	private String _title= null;
	private String _spritemap = null;
	private PanelActionModel _panelAction = null;

	public void setTitle(String title) {
		_title = title;
	}

	public void setSpritemap(String spritemap) {
		_spritemap = spritemap;
	}

	public void setPanelAction(PanelActionModel panelAction) {
		_panelAction = panelAction;
	}

}