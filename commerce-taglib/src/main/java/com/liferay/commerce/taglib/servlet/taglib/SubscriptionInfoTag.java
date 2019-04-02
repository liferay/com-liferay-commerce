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

import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.model.CommerceSubscriptionEntry;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CPSubscriptionInfo;
import com.liferay.commerce.product.service.CPInstanceServiceUtil;
import com.liferay.commerce.product.util.CPSubscriptionType;
import com.liferay.commerce.product.util.CPSubscriptionTypeRegistry;
import com.liferay.commerce.service.CommerceOrderItemLocalServiceUtil;
import com.liferay.commerce.service.CommerceSubscriptionEntryLocalServiceUtil;
import com.liferay.commerce.taglib.servlet.taglib.internal.servlet.ServletContextUtil;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
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
			CommerceOrderItem commerceOrderItem =
				CommerceOrderItemLocalServiceUtil.fetchCommerceOrderItem(
					_commerceOrderItemId);

			CPInstance cpInstance = CPInstanceServiceUtil.fetchCPInstance(
				_cpInstanceId);

			CommerceSubscriptionEntry commerceSubscriptionEntry = null;

			try {
				commerceSubscriptionEntry =
					CommerceSubscriptionEntryLocalServiceUtil.
						fetchCommerceSubscriptionEntries(
							cpInstance.getCPInstanceUuid(),
							commerceOrderItem.getCProductId(),
							_commerceOrderItemId);
			}
			catch (Exception e) {
				if (_log.isDebugEnabled()) {
					_log.debug(e.getMessage(), e);
				}
			}

			if ((commerceSubscriptionEntry == null) && (cpInstance == null)) {
				return SKIP_BODY;
			}

			String subscriptionType = null;

			if (commerceSubscriptionEntry != null) {
				_length = commerceSubscriptionEntry.getSubscriptionLength();

				_duration =
					_length *
						commerceSubscriptionEntry.getMaxSubscriptionCycles();

				subscriptionType =
					commerceSubscriptionEntry.getSubscriptionType();
			}
			else {
				CPSubscriptionInfo cpSubscriptionInfo =
					cpInstance.getCPSubscriptionInfo();

				if (cpSubscriptionInfo == null) {
					return SKIP_BODY;
				}

				_length = cpSubscriptionInfo.getSubscriptionLength();

				_duration =
					_length * cpSubscriptionInfo.getMaxSubscriptionCycles();

				subscriptionType = cpSubscriptionInfo.getSubscriptionType();
			}

			String period = StringPool.BLANK;

			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
				WebKeys.THEME_DISPLAY);

			CPSubscriptionType cpSubscriptionType =
				cpSubscriptionTypeRegistry.getCPSubscriptionType(
					subscriptionType);

			if (cpSubscriptionType != null) {
				period = cpSubscriptionType.getLabel(themeDisplay.getLocale());
			}

			_subscriptionPeriodKey = _getPeriodKey(period, _length != 1);

			_durationPeriodKey = _getPeriodKey(period, _duration != 1);
		}
		catch (PortalException pe) {
			if (_log.isDebugEnabled()) {
				_log.debug(pe, pe);
			}

			return SKIP_BODY;
		}

		return super.doStartTag();
	}

	public long getCommerceOrderItemId() {
		return _commerceOrderItemId;
	}

	public long getCPInstanceId() {
		return _cpInstanceId;
	}

	public boolean isShowDuration() {
		return _showDuration;
	}

	public void setCommerceOrderItemId(long commerceOrderItemId) {
		_commerceOrderItemId = commerceOrderItemId;
	}

	public void setCPInstanceId(long cpInstanceId) {
		_cpInstanceId = cpInstanceId;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		cpSubscriptionTypeRegistry =
			ServletContextUtil.getCPSubscriptionTypeRegistry();
		servletContext = ServletContextUtil.getServletContext();
	}

	public void setShowDuration(boolean showDuration) {
		_showDuration = showDuration;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_commerceOrderItemId = 0;
		_cpInstanceId = 0;
		_duration = 0;
		_durationPeriodKey = null;
		_length = 0;
		_showDuration = true;
		_subscriptionPeriodKey = null;
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
			"liferay-commerce:subscription-info:durationPeriodKey",
			_durationPeriodKey);
		request.setAttribute(
			"liferay-commerce:subscription-info:length", _length);
		request.setAttribute(
			"liferay-commerce:subscription-info:showDuration", _showDuration);
		request.setAttribute(
			"liferay-commerce:subscription-info:subscriptionPeriodKey",
			_subscriptionPeriodKey);
	}

	protected CPSubscriptionTypeRegistry cpSubscriptionTypeRegistry;

	private String _getPeriodKey(String period, boolean plural) {
		if (plural) {
			return LanguageUtil.get(
				request,
				StringUtil.toLowerCase(period) + CharPool.LOWER_CASE_S);
		}

		return period;
	}

	private static final String _PAGE = "/subscription_info/page.jsp";

	private static final Log _log = LogFactoryUtil.getLog(
		SubscriptionInfoTag.class);

	private long _commerceOrderItemId;
	private long _cpInstanceId;
	private long _duration;
	private String _durationPeriodKey;
	private long _length;
	private boolean _showDuration = true;
	private String _subscriptionPeriodKey;

}