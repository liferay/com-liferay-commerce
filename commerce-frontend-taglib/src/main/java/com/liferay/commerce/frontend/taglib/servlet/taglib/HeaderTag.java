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

import com.liferay.commerce.frontend.model.HeaderActionModel;
import com.liferay.commerce.frontend.taglib.internal.servlet.ServletContextUtil;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.taglib.util.IncludeTag;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

/**
 * @author Fabio Diego Mastrorilli
 */
public class HeaderTag extends IncludeTag {

	public Object getBean() {
		return _bean;
	}

	public String getCssClasses() {
		return _cssClasses;
	}

	public List<DropdownItem> getDropdownItems() {
		return _dropdownItems;
	}

	public boolean getFullWidth() {
		return _fullWidth;
	}

	public List<HeaderActionModel> getHeaderActions() {
		return _headerActionModels;
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

	public String getWrapperCssClasses() {
		return _wrapperCssClasses;
	}

	public void setBean(Object bean) {
		_bean = bean;
	}

	public void setCssClasses(String cssClasses) {
		_cssClasses = cssClasses;
	}

	public void setDropdownItems(List<DropdownItem> dropdownItems) {
		_dropdownItems = dropdownItems;
	}

	public void setFullWidth(boolean fullWidth) {
		_fullWidth = fullWidth;
	}

	public void setHeaderActions(List<HeaderActionModel> headerActionModels) {
		_headerActionModels = headerActionModels;
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

	public void setWrapperCssClasses(String wrapperCssClasses) {
		_wrapperCssClasses = wrapperCssClasses;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_bean = null;
		_cssClasses = null;
		_dropdownItems = null;
		_fullWidth = false;
		_headerActionModels = null;
		_model = null;
		_previewUrl = null;
		_spritemap = null;
		_thumbnailUrl = null;
		_title = null;
		_version = null;
		_wrapperCssClasses = null;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest httpServletRequest) {
		if (Validator.isNull(_spritemap)) {
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
				WebKeys.THEME_DISPLAY);

			_spritemap = themeDisplay.getPathThemeImages() + "/clay/icons.svg";
		}

		request.setAttribute("liferay-commerce:header:bean", _bean);
		request.setAttribute("liferay-commerce:header:cssClasses", _cssClasses);
		request.setAttribute(
			"liferay-commerce:header:dropdownItems", _dropdownItems);
		request.setAttribute("liferay-commerce:header:fullWidth", _fullWidth);
		request.setAttribute(
			"liferay-commerce:header:hasWorkflow", Boolean.FALSE);
		request.setAttribute(
			"liferay-commerce:header:headerActionModels", _headerActionModels);
		request.setAttribute("liferay-commerce:header:model", _model);
		request.setAttribute("liferay-commerce:header:previewUrl", _previewUrl);
		request.setAttribute("liferay-commerce:header:spritemap", _spritemap);
		request.setAttribute(
			"liferay-commerce:header:thumbnailUrl", _thumbnailUrl);
		request.setAttribute("liferay-commerce:header:title", _title);
		request.setAttribute("liferay-commerce:header:version", _version);
		request.setAttribute(
			"liferay-commerce:header:wrapperCssClasses", _wrapperCssClasses);
	}

	private static final String _PAGE = "/header/page.jsp";

	private Object _bean;
	private String _cssClasses;
	private List<DropdownItem> _dropdownItems;
	private boolean _fullWidth;
	private List<HeaderActionModel> _headerActionModels;
	private Class<?> _model;
	private String _previewUrl;
	private String _spritemap;
	private String _thumbnailUrl;
	private String _title;
	private String _version;
	private String _wrapperCssClasses;

}