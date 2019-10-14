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
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.taglib.util.IncludeTag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

/**
 * @author Fabio Diego Mastrorilli
 */
public class PanelTag extends IncludeTag {

	@Override
	public int doEndTag() throws JspException {
		setAttributeNamespace(_ATTRIBUTE_NAMESPACE);

		return super.doEndTag();
	}

	@Override
	public int doStartTag() throws JspException {
		setAttributeNamespace(_ATTRIBUTE_NAMESPACE);

		String randomNamespace =
			PortalUtil.generateRandomKey(request, "commerce_panel") +
				StringPool.UNDERLINE;

		if (Validator.isNull(_spritemap)) {
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
				WebKeys.THEME_DISPLAY);

			_spritemap = themeDisplay.getPathThemeImages() + "/clay/icons.svg";
		}

		setNamespacedAttribute(request, "spritemap", _spritemap);
		setNamespacedAttribute(request, "title", _title);
		setNamespacedAttribute(request, "actionLabel", _actionLabel);
		setNamespacedAttribute(request, "actionIcon", _actionIcon);
		setNamespacedAttribute(request, "actionTargetId", _actionTargetId);
		setNamespacedAttribute(request, "actionUrl", _actionUrl);
		setNamespacedAttribute(request, "showMoreId", _showMoreId);
		setNamespacedAttribute(request, "showMoreUrl", _showMoreUrl);
		setNamespacedAttribute(request, "elementClasses", _elementClasses);
		setNamespacedAttribute(request, "bodyClasses", _bodyClasses);
		setNamespacedAttribute(request, "randomNamespace", randomNamespace);

		super.doStartTag();

		return EVAL_BODY_INCLUDE;
	}

	public String getActionIcon() {
		return _actionIcon;
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

	public String getBodyClasses() {
		return _bodyClasses;
	}

	public String getElementClasses() {
		return _elementClasses;
	}

	public String getShowMoreId() {
		return _showMoreId;
	}

	public String getShowMoreUrl() {
		return _showMoreUrl;
	}

	public String getSpritemap() {
		return _spritemap;
	}

	public String getTitle() {
		return _title;
	}

	public void setActionIcon(String actionIcon) {
		_actionIcon = actionIcon;
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

	public void setBodyClasses(String bodyClasses) {
		_bodyClasses = bodyClasses;
	}

	public void setElementClasses(String elementClasses) {
		_elementClasses = elementClasses;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	public void setShowMoreId(String showMoreId) {
		_showMoreId = showMoreId;
	}

	public void setShowMoreUrl(String showMoreUrl) {
		_showMoreUrl = showMoreUrl;
	}

	public void setSpritemap(String spritemap) {
		_spritemap = spritemap;
	}

	public void setTitle(String title) {
		_title = title;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_actionIcon = null;
		_actionLabel = null;
		_actionTargetId = null;
		_actionUrl = null;
		_bodyClasses = null;
		_elementClasses = null;
		_showMoreId = null;
		_showMoreUrl = null;
		_spritemap = null;
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
		"liferay-commerce:panel:";

	private static final String _END_PAGE = "/panel/end.jsp";

	private static final String _START_PAGE = "/panel/start.jsp";

	private String _actionIcon;
	private String _actionLabel;
	private String _actionTargetId;
	private String _actionUrl;
	private String _bodyClasses;
	private String _elementClasses;
	private String _showMoreId;
	private String _showMoreUrl;
	private String _spritemap;
	private String _title;

}