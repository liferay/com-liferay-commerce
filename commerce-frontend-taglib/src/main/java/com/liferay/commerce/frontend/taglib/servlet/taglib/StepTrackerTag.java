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

import com.liferay.commerce.frontend.model.StepModel;
import com.liferay.commerce.frontend.taglib.internal.servlet.ServletContextUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;
import java.util.List;

/**
 * @author Fabio Diego Mastrorilli
 */
public class StepTrackerTag extends IncludeTag {

	public String getSpritemap() {
		return _spritemap;
	}

	public List<StepModel> getSteps() {
		return _steps;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	public void setSpritemap(String spritemap) {
		_spritemap = spritemap;
	}

	public void setSteps(List<StepModel> steps) {
		_steps = steps;
	}


	@Override
	protected void cleanUp() {
		super.cleanUp();

		_steps = null;
		_spritemap = null;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest httpServletRequest) {
		String spritemap = _spritemap;

		if (Validator.isNull(_spritemap)) {
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
				WebKeys.THEME_DISPLAY);

			spritemap = themeDisplay.getPathThemeImages(
			).concat(
				"/clay/icons.svg"
			);
		}

		request.setAttribute("liferay-commerce:step-tracker:steps", _steps);
		request.setAttribute("liferay-commerce:step-tracker_spritemap", _spritemap);
	}

	private static final String _PAGE = "/step_tracker/page.jsp";

	private static final Log _log = LogFactoryUtil.getLog(StepTrackerTag.class);

	private List<StepModel> _steps;
	private String _spritemap;

}