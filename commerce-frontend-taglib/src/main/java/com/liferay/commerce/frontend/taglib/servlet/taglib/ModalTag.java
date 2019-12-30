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
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

/**
 * @author Fabio Diego Mastrorilli
 */
public class ModalTag extends IncludeTag {

	public String getCancelLabel() {
		return _cancelLabel;
	}

	public String getDeleteLabel() {
		return _deleteLabel;
	}

	public String getId() {
		return _id;
	}

	public boolean getRefreshPageOnClose() {
		return _refreshPageOnClose;
	}

	public boolean getShowCancel() {
		return _showCancel;
	}

	public boolean getShowDelete() {
		return _showDelete;
	}

	public boolean getShowSubmit() {
		return _showSubmit;
	}

	public String getSize() {
		return _size;
	}

	public String getSpritemap() {
		return _spritemap;
	}

	public String getSubmitLabel() {
		return _submitLabel;
	}

	public String getTitle() {
		return _title;
	}

	public String getUrl() {
		return _url;
	}

	public void setCancelLabel(String cancelLabel) {
		_cancelLabel = cancelLabel;
	}

	public void setDeleteLabel(String deleteLabel) {
		_deleteLabel = deleteLabel;
	}

	public void setId(String id) {
		_id = id;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	public void setRefreshPageOnClose(boolean refreshPageOnClose) {
		_refreshPageOnClose = refreshPageOnClose;
	}

	public void setShowCancel(boolean showCancel) {
		_showCancel = showCancel;
	}

	public void setShowDelete(boolean showDelete) {
		_showDelete = showDelete;
	}

	public void setShowSubmit(boolean showSubmit) {
		_showSubmit = showSubmit;
	}

	public void setSize(String size) {
		_size = size;
	}

	public void setSpritemap(String spritemap) {
		_spritemap = spritemap;
	}

	public void setSubmitLabel(String submitLabel) {
		_submitLabel = submitLabel;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public void setUrl(String url) {
		_url = url;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_cancelLabel = StringPool.BLANK;
		_deleteLabel = StringPool.BLANK;
		_id = StringPool.BLANK;
		_refreshPageOnClose = false;
		_showCancel = false;
		_showDelete = false;
		_showSubmit = false;
		_size = StringPool.BLANK;
		_spritemap = StringPool.BLANK;
		_submitLabel = StringPool.BLANK;
		_title = StringPool.BLANK;
		_url = StringPool.BLANK;
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

		request.setAttribute(
			"liferay-commerce:modal:cancelLabel", _cancelLabel);
		request.setAttribute(
			"liferay-commerce:modal:deleteLabel", _deleteLabel);
		request.setAttribute("liferay-commerce:modal:id", _id);
		request.setAttribute(
			"liferay-commerce:modal:refreshPageOnClose", _refreshPageOnClose);
		request.setAttribute("liferay-commerce:modal:showCancel", _showCancel);
		request.setAttribute("liferay-commerce:modal:showDelete", _showDelete);
		request.setAttribute("liferay-commerce:modal:showSubmit", _showSubmit);
		request.setAttribute("liferay-commerce:modal:size", _size);
		request.setAttribute("liferay-commerce:modal:spritemap", _spritemap);
		request.setAttribute(
			"liferay-commerce:modal:submitLabel", _submitLabel);
		request.setAttribute("liferay-commerce:modal:title", _title);
		request.setAttribute("liferay-commerce:modal:url", _url);
	}

	private static final String _PAGE = "/modal/page.jsp";

	private String _cancelLabel = StringPool.BLANK;
	private String _deleteLabel = StringPool.BLANK;
	private String _id = StringPool.BLANK;
	private boolean _refreshPageOnClose;
	private boolean _showCancel;
	private boolean _showDelete;
	private boolean _showSubmit;
	private String _size = StringPool.BLANK;
	private String _spritemap = StringPool.BLANK;
	private String _submitLabel = StringPool.BLANK;
	private String _title = StringPool.BLANK;
	private String _url = StringPool.BLANK;

}