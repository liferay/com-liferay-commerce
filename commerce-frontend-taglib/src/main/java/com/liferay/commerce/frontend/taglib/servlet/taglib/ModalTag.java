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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
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

	public String getUrl() { return _url; }
	public String getSize() { return _size; }
	public String getTitle() { return _title; }
	public String getSpritemap() { return _spritemap; }
	public String getTriggerId() { return _triggerId; }
	public Boolean getShowSubmit() { return _showSubmit; }
	public String getSubmitLabel() { return _submitLabel; }
	public Boolean getCloseOnSubmit() { return _closeOnSubmit; }
	public Boolean getSubmitAvailableAtLoading() { return _submitAvailableAtLoading; }
	public Boolean getShowDelete() { return _showDelete; }
	public String getDeleteLabel() { return _deleteLabel; }
	public Boolean getShowCancel() { return _showCancel; }
	public String getCancelLabel() { return _cancelLabel; }

	public void setUrl(String url) { _url = url; }
	public void setSize(String size) { _size = size; }
	public void setTitle(String title) { _title = title; }
	public void setSpritemap(String spritemap) { _spritemap = spritemap; }
	public void setTriggerId(String triggerId) { _triggerId = triggerId; }
	public void setShowSubmit(Boolean showSubmit) { _showSubmit = showSubmit; }
	public void setSubmitLabel(String submitLabel) { _submitLabel = submitLabel; }
	public void setCloseOnSubmit(Boolean closeOnSubmit) { _closeOnSubmit = closeOnSubmit; }
	public void setSubmitAvailableAtLoading(Boolean submitAvailableAtLoading) { _submitAvailableAtLoading = submitAvailableAtLoading; }
	public void setShowDelete(Boolean showDelete) { _showDelete = showDelete; }
	public void setDeleteLabel(String deleteLabel) { _deleteLabel = deleteLabel; }
	public void setShowCancel(Boolean showCancel) { _showCancel = showCancel; }
	public void setCancelLabel(String cancelLabel) { _cancelLabel = cancelLabel; }

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_url = StringPool.BLANK;
		_size = StringPool.BLANK;
		_title = StringPool.BLANK;
		_spritemap = StringPool.BLANK;
		_triggerId = StringPool.BLANK;
		_showSubmit = false;
		_submitLabel = StringPool.BLANK;
		_closeOnSubmit = false;
		_submitAvailableAtLoading = false;
		_showDelete = false;
		_deleteLabel = StringPool.BLANK;
		_showCancel = false;
		_cancelLabel = StringPool.BLANK;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest httpServletRequest) {

		String spritemap = _spritemap;

		if (Validator.isNull(spritemap)) {
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
					WebKeys.THEME_DISPLAY);

			spritemap = themeDisplay.getPathThemeImages().concat("/clay/icons.svg");
		}

		request.setAttribute("liferay-commerce:modal:url", _url);
		request.setAttribute("liferay-commerce:modal:size", _size);
		request.setAttribute("liferay-commerce:modal:title", _title);
		request.setAttribute("liferay-commerce:modal:spritemap", spritemap);
		request.setAttribute("liferay-commerce:modal:triggerId", _triggerId);
		request.setAttribute("liferay-commerce:modal:showSubmit", _showSubmit);
		request.setAttribute("liferay-commerce:modal:submitLabel", _submitLabel);
		request.setAttribute("liferay-commerce:modal:closeOnSubmit", _closeOnSubmit);
		request.setAttribute("liferay-commerce:modal:submitAvailableAtLoading", _submitAvailableAtLoading);
		request.setAttribute("liferay-commerce:modal:showDelete", _showDelete);
		request.setAttribute("liferay-commerce:modal:deleteLabel", _deleteLabel);
		request.setAttribute("liferay-commerce:modal:showCancel", _showCancel);
		request.setAttribute("liferay-commerce:modal:cancelLabel", _cancelLabel);
	}

	private static final String _PAGE = "/modal/page.jsp";

	private static final Log _log = LogFactoryUtil.getLog(HeaderTag.class);

	private String _url = StringPool.BLANK;
	private String _size = StringPool.BLANK;
	private String _title = StringPool.BLANK;
	private String _spritemap = StringPool.BLANK;
	private String _triggerId = StringPool.BLANK;
	private Boolean _showSubmit;
	private String _submitLabel = StringPool.BLANK;
	private Boolean _closeOnSubmit;
	private Boolean _submitAvailableAtLoading;
	private Boolean _showDelete;
	private String _deleteLabel = StringPool.BLANK;
	private Boolean _showCancel;
	private String _cancelLabel = StringPool.BLANK;

}