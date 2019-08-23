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

import com.liferay.commerce.frontend.model.HeaderButtonModel;
import com.liferay.commerce.frontend.taglib.internal.servlet.ServletContextUtil;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.taglib.util.IncludeTag;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

/**
 * @author Fabio Diego Mastrorilli
 */
public class HeaderTag extends IncludeTag {

	@Override
	public int doStartTag() throws JspException {
		if (_spritemap == null) {
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
				WebKeys.THEME_DISPLAY);

			_spritemap =
				themeDisplay.getPathThemeImages() + "/commerce-icons.svg";
		}

		return super.doStartTag();
	}

	public Object getBean() {
		return _bean;
	}

	public List<DropdownItem> getDropdownItems() {
		return _dropdownItems;
	}

	public List<HeaderButtonModel> getHeaderButtons() {
		return _headerButtonModels;
	}

	public Class<?> getModel() {
		return _model;
	}

	public String getPreviewUrl() {
		return _previewUrl;
	}

	public String getSpritemap() {
		return _spritemap;
	}

	public String getThumbnailUrl() {
		return _thumbnailUrl;
	}

	public String getTitle() {
		return _title;
	}

	public String getVersion() {
		return _version;
	}

	public void setBean(Object bean) {
		_bean = bean;
	}

	public void setDropdownItems(List<DropdownItem> dropdownItems) {
		_dropdownItems = dropdownItems;
	}

	public void setHeaderButtons(List<HeaderButtonModel> headerButtonModels) {
		_headerButtonModels = headerButtonModels;
	}

	public void setModel(Class<?> model) {
		_model = model;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	public void setPreviewUrl(String previewUrl) {
		_previewUrl = previewUrl;
	}

	public void setSpritemap(String spritemap) {
		_spritemap = spritemap;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		_thumbnailUrl = thumbnailUrl;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public void setVersion(String version) {
		_version = version;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_bean = null;
		_headerButtonModels = null;
		_model = null;
		_previewUrl = null;
		_thumbnailUrl = null;
		_title = null;
		_version = null;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest httpServletRequest) {
		request.setAttribute("liferay-commerce:header:bean", _bean);
		request.setAttribute(
			"liferay-commerce:header:dropdownItems", _dropdownItems);
		request.setAttribute(
			"liferay-commerce:header:hasWorkflow", Boolean.FALSE);
		request.setAttribute(
			"liferay-commerce:header:headerButtonModels", _headerButtonModels);
		request.setAttribute("liferay-commerce:header:model", _model);
		request.setAttribute("liferay-commerce:header:previewUrl", _previewUrl);
		request.setAttribute("liferay-commerce:header:spritemap", _spritemap);
		request.setAttribute(
			"liferay-commerce:header:thumbnailUrl", _thumbnailUrl);
		request.setAttribute("liferay-commerce:header:title", _title);
		request.setAttribute("liferay-commerce:header:version", _version);
	}

	private static final String _PAGE = "/header/page.jsp";

	private static final Log _log = LogFactoryUtil.getLog(HeaderTag.class);

	private Object _bean;
	private List<DropdownItem> _dropdownItems;
	private List<HeaderButtonModel> _headerButtonModels;
	private Class<?> _model;
	private String _previewUrl;
	private String _spritemap;
	private String _thumbnailUrl;
	private String _title;
	private String _version;

}