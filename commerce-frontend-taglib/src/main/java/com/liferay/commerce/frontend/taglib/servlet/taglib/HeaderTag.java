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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import java.util.List;

/**
 * @author Fabio Diego Mastrorilli
 */
public class HeaderTag extends IncludeTag {

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
		request.setAttribute("liferay-commerce:header:bean", _bean);
		request.setAttribute("liferay-commerce:header:spritemap", _spritemap);
		request.setAttribute("liferay-commerce:header:model", _model);
		request.setAttribute("liferay-commerce:header:title", _title);
		request.setAttribute("liferay-commerce:header:version", _version);
		request.setAttribute("liferay-commerce:header:thumbnailUrl", _thumbnailUrl);
		request.setAttribute("liferay-commerce:header:previewUrl", _previewUrl);
		request.setAttribute("liferay-commerce:header:headerButtonModels", _headerButtonModels);
		request.setAttribute("liferay-commerce:header:dropdownItems", _dropdownItems);
		request.setAttribute("liferay-commerce:header:hasWorkflow", false);
	}

	private static final String _PAGE = "/header/page.jsp";
	private static final Log _log = LogFactoryUtil.getLog(HeaderTag.class);

	public Object getBean() {
		return _bean;
	}

	public Class<?> getModel() {
		return _model;
	}

	public String getTitle() {
		return _title;
	}

	public String getVersion() {
		return _version;
	}

	public String getSpritemap() {
		return _spritemap;
	}

	public String getPreviewUrl() {
		return _previewUrl;
	}

	public String getThumbnailUrl() {
		return _thumbnailUrl;
	}

	public List<HeaderButtonModel> getHeaderButtons() {
		return _headerButtonModels;
	}

	public List<DropdownItem> getDropdownItems() {
		return _dropdownItems;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_bean = null;
		_model = null;
		_title = null;
		_thumbnailUrl = null;
		_previewUrl = null;
		_version = null;
		_headerButtonModels = null;
	}

	private Object _bean = null;
	private Class<?> _model = null;
	private String _title= null;
	private String _spritemap = null;
	private String _version = null;
	private String _thumbnailUrl = null;
	private String _previewUrl = null;
	private List<HeaderButtonModel> _headerButtonModels;
	private List<DropdownItem> _dropdownItems;

	public void setBean(Object bean) {
		_bean = bean;
	}

	public void setModel(Class<?> model) {
		_model = model;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public void setSpritemap(String spritemap) {
		_spritemap = spritemap;
	}

	public void setVersion(String version) {
		_version = version;
	}

	public void setPreviewUrl(String previewUrl) {
		_previewUrl = previewUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		_thumbnailUrl = thumbnailUrl;
	}

	public void setHeaderButtons(List<HeaderButtonModel> headerButtonModels) {
		_headerButtonModels = headerButtonModels;
	}

	public void setDropdownItems(List<DropdownItem> dropdownItems) {
		_dropdownItems = dropdownItems;
	}

}