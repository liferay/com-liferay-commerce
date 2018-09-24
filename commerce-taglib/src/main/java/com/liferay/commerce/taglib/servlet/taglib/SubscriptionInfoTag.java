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

package com.liferay.commerce.taglib.servlet.taglib;

import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CPSubscriptionInfo;
import com.liferay.commerce.product.service.CPInstanceServiceUtil;
import com.liferay.commerce.taglib.servlet.taglib.internal.servlet.ServletContextUtil;
import com.liferay.petra.string.CharPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.taglib.util.IncludeTag;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

/**
 * @author Alessio Antonio Rendina
 */
public class SubscriptionInfoTag extends IncludeTag {

	@Override
	public int doStartTag() throws JspException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
				WebKeys.THEME_DISPLAY);

			CPInstance cpInstance = CPInstanceServiceUtil.getCPInstance(
				_cpInstanceId);

			CPSubscriptionInfo cpSubscriptionInfo =
				cpInstance.getCPSubscriptionInfo();

			if (cpSubscriptionInfo == null) {
				return SKIP_BODY;
			}

			_length = cpSubscriptionInfo.getSubscriptionCycleLength();

			long maxSubscriptionCyclesNumber =
				cpSubscriptionInfo.getMaxSubscriptionCyclesNumber();
			String subscriptionCyclePeriod =
				cpSubscriptionInfo.getSubscriptionCyclePeriod();

			_periodSuffix = _getSuffix(_length, subscriptionCyclePeriod);

			if (_showDuration && (maxSubscriptionCyclesNumber > 0)) {
				ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
					"content.Language", themeDisplay.getLocale(), getClass());

				long totalLength = _length * maxSubscriptionCyclesNumber;

				_duration = LanguageUtil.format(
					resourceBundle, "duration-x", totalLength, false);

				_durationSuffix = _getSuffix(
					totalLength, subscriptionCyclePeriod);
			}
		}
		catch (PortalException pe) {
			if (_log.isDebugEnabled()) {
				_log.debug(pe, pe);
			}

			return SKIP_BODY;
		}

		return super.doStartTag();
	}

	public void setCPInstanceId(long cpInstanceId) {
		_cpInstanceId = cpInstanceId;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	public void setShowDuration(boolean showDuration) {
		_showDuration = showDuration;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_cpInstanceId = 0;
		_duration = null;
		_durationSuffix = null;
		_length = 0;
		_periodSuffix = null;
		_showDuration = true;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest httpServletRequest) {
		request.setAttribute(
			"liferay-commerce:subscription-info:duration", _duration);
		request.setAttribute(
			"liferay-commerce:subscription-info:durationSuffix",
			_durationSuffix);
		request.setAttribute(
			"liferay-commerce:subscription-info:length", _length);
		request.setAttribute(
			"liferay-commerce:subscription-info:periodSuffix", _periodSuffix);
	}

	private String _getSuffix(long count, String period) {
		if (count > 1) {
			return LanguageUtil.get(request, period + CharPool.LOWER_CASE_S);
		}
		else {
			return LanguageUtil.get(request, period);
		}
	}

	private static final String _PAGE = "/subscription_info/page.jsp";

	private static final Log _log = LogFactoryUtil.getLog(
		SubscriptionInfoTag.class);

	private long _cpInstanceId;
	private String _duration;
	private String _durationSuffix;
	private long _length;
	private String _periodSuffix;
	private boolean _showDuration = true;

}