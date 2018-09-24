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

package com.liferay.commerce.product.taglib.servlet.taglib;

import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CPSubscriptionInfo;
import com.liferay.commerce.product.service.CPInstanceServiceUtil;
import com.liferay.commerce.product.taglib.servlet.taglib.internal.servlet.ServletContextUtil;
import com.liferay.petra.string.CharPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.taglib.util.IncludeTag;

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
			CPInstance cpInstance = CPInstanceServiceUtil.getCPInstance(
				_cpInstanceId);

			CPSubscriptionInfo cpSubscriptionInfo =
				cpInstance.getCPSubscriptionInfo();

			if (cpSubscriptionInfo == null) {
				return SKIP_BODY;
			}

			_length = cpSubscriptionInfo.getSubscriptionCycleLength();

			_duration =
				_length * cpSubscriptionInfo.getMaxSubscriptionCyclesNumber();

			_periodSuffix = _getPeriodSuffix(
				cpSubscriptionInfo.getSubscriptionCyclePeriod(), _length > 0);

			_durationSuffix = _getPeriodSuffix(
				cpSubscriptionInfo.getSubscriptionCyclePeriod(), _duration > 0);
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
		_duration = 0;
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
			"liferay-commerce-product:subscription-info:duration", _duration);
		request.setAttribute(
			"liferay-commerce-product:subscription-info:durationSuffix",
			_durationSuffix);
		request.setAttribute(
			"liferay-commerce-product:subscription-info:length", _length);
		request.setAttribute(
			"liferay-commerce-product:subscription-info:periodSuffix",
			_periodSuffix);
		request.setAttribute(
			"liferay-commerce-product:subscription-info:showDuration",
			_showDuration);
	}

	private String _getPeriodSuffix(String period, boolean plural) {
		if (plural) {
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
	private long _duration;
	private String _durationSuffix;
	private long _length;
	private String _periodSuffix;
	private boolean _showDuration = true;

}