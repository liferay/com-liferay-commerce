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

import com.liferay.commerce.frontend.taglib.internal.js.loader.modules.extender.npm.NPMResolverProvider;
import com.liferay.commerce.frontend.taglib.internal.servlet.ServletContextUtil;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CPSubscriptionInfo;
import com.liferay.commerce.product.service.CPInstanceServiceUtil;
import com.liferay.commerce.product.util.CPSubscriptionType;
import com.liferay.commerce.product.util.CPSubscriptionTypeRegistry;
import com.liferay.frontend.js.loader.modules.extender.npm.NPMResolver;
import com.liferay.frontend.taglib.soy.servlet.taglib.ComponentRendererTag;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import javax.servlet.jsp.PageContext;

/**
 * @author Alessio Antonio Rendina
 */
public class ProductSubscriptionInfoTag extends ComponentRendererTag {

	@Override
	public int doStartTag() {
		try {
			CPInstance cpInstance = CPInstanceServiceUtil.fetchCPInstance(
				_cpInstanceId);

			if (cpInstance == null) {
				return SKIP_BODY;
			}

			CPSubscriptionInfo cpSubscriptionInfo =
				cpInstance.getCPSubscriptionInfo();

			if (cpSubscriptionInfo == null) {
				return SKIP_BODY;
			}

			_length = cpSubscriptionInfo.getSubscriptionLength();

			_duration = _length * cpSubscriptionInfo.getMaxSubscriptionCycles();

			String subscriptionType = cpSubscriptionInfo.getSubscriptionType();

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

		String durationPeriod = StringPool.BLANK;
		String subscriptionPeriod = StringPool.BLANK;

		if (_showDuration && (_duration > 0)) {
			durationPeriod = LanguageUtil.format(
				request, "duration-x-x",
				new Object[] {_duration, _durationPeriodKey});
		}

		if ((_length > 0) && Validator.isNotNull(_subscriptionPeriodKey)) {
			subscriptionPeriod = LanguageUtil.format(
				request, "every-x-x",
				new Object[] {_length, _subscriptionPeriodKey});
		}

		if (Validator.isNotNull(durationPeriod)) {
			putValue("durationPeriod", durationPeriod);
		}

		if (Validator.isNotNull(subscriptionPeriod)) {
			putValue("subscriptionPeriod", subscriptionPeriod);
		}

		setTemplateNamespace("SubscriptionInfo.render");

		return super.doStartTag();
	}

	public long getCPInstanceId() {
		return _cpInstanceId;
	}

	@Override
	public String getModule() {
		NPMResolver npmResolver = NPMResolverProvider.getNPMResolver();

		if (npmResolver == null) {
			return StringPool.BLANK;
		}

		return npmResolver.resolveModuleName(
			"commerce-frontend-taglib/js/subscription_info" +
				"/SubscriptionInfo.es");
	}

	public boolean isShowDuration() {
		return _showDuration;
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

		_cpInstanceId = 0;
		_duration = 0;
		_durationPeriodKey = null;
		_length = 0;
		_showDuration = true;
		_subscriptionPeriodKey = null;
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

	private static final Log _log = LogFactoryUtil.getLog(
		ProductSubscriptionInfoTag.class);

	private long _cpInstanceId;
	private long _duration;
	private String _durationPeriodKey;
	private long _length;
	private boolean _showDuration = true;
	private String _subscriptionPeriodKey;

}