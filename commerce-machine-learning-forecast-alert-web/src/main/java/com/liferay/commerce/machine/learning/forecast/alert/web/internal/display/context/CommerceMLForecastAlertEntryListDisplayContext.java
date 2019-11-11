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

package com.liferay.commerce.machine.learning.forecast.alert.web.internal.display.context;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.CommerceAccountLocalService;
import com.liferay.commerce.machine.learning.forecast.alert.constants.CommerceMLForecastAlertActionKeys;
import com.liferay.commerce.machine.learning.forecast.alert.constants.CommerceMLForecastAlertConstants;
import com.liferay.commerce.machine.learning.forecast.alert.model.CommerceMLForecastAlertEntry;
import com.liferay.commerce.machine.learning.forecast.alert.service.CommerceMLForecastAlertEntryService;
import com.liferay.commerce.machine.learning.forecast.alert.web.internal.display.context.util.CommerceMLForecastAlertEntryRequestHelper;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;

import java.util.List;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;

/**
 * @author Riccardo Ferrari
 */
public class CommerceMLForecastAlertEntryListDisplayContext {

	public CommerceMLForecastAlertEntryListDisplayContext(
		CommerceAccountLocalService commerceAccountLocalService,
		CommerceMLForecastAlertEntryService commerceMLForecastAlertEntryService,
		PortletResourcePermission portletResourcePermission,
		RenderRequest renderRequest) {

		_commerceAccountLocalService = commerceAccountLocalService;
		_commerceMLForecastAlertEntryService =
			commerceMLForecastAlertEntryService;
		_commerceMLForecastAlertEntryRequestHelper =
			new CommerceMLForecastAlertEntryRequestHelper(renderRequest);
		_portletResourcePermission = portletResourcePermission;
	}

	public CommerceAccount getCommerceAccount(long commerceAccountId) {
		try {
			return _commerceAccountLocalService.getCommerceAccount(
				commerceAccountId);
		}
		catch (PortalException pe) {
			_log.error(pe, pe);

			return null;
		}
	}

	public PortletURL getPortletURL() {
		LiferayPortletResponse liferayPortletResponse =
			_commerceMLForecastAlertEntryRequestHelper.
				getLiferayPortletResponse();

		return liferayPortletResponse.createRenderURL();
	}

	public SearchContainer<CommerceMLForecastAlertEntry> getSearchContainer()
		throws PortalException {

		if (_searchContainer != null) {
			return _searchContainer;
		}

		_searchContainer = new SearchContainer<>(
			_commerceMLForecastAlertEntryRequestHelper.
				getLiferayPortletRequest(),
			getPortletURL(), null, null);

		_searchContainer.setEmptyResultsMessage(
			"there-are-no-forecast-alert-entries-to-display");

		List<CommerceMLForecastAlertEntry> results =
			_commerceMLForecastAlertEntryService.
				getBelowThresholdCommerceMLForecastAlertEntries(
					_commerceMLForecastAlertEntryRequestHelper.getCompanyId(),
					_commerceMLForecastAlertEntryRequestHelper.getUserId(),
					CommerceMLForecastAlertConstants.STATUS_NEW, 0.0,
					_searchContainer.getStart(), _searchContainer.getEnd());

		_searchContainer.setResults(results);

		int total =
			_commerceMLForecastAlertEntryService.
				getBelowThresholdCommerceMLForecastAlertEntriesCount(
					_commerceMLForecastAlertEntryRequestHelper.getCompanyId(),
					_commerceMLForecastAlertEntryRequestHelper.getUserId(),
					CommerceMLForecastAlertConstants.STATUS_NEW, 0.0);

		_searchContainer.setTotal(total);

		return _searchContainer;
	}

	public boolean hasUpdatePermission() {
		return _portletResourcePermission.contains(
			_commerceMLForecastAlertEntryRequestHelper.getPermissionChecker(),
			GroupConstants.DEFAULT_LIVE_GROUP_ID,
			CommerceMLForecastAlertActionKeys.MANAGE_ALERT_STATUS);
	}

	public boolean hasViewPermission() {
		return _portletResourcePermission.contains(
			_commerceMLForecastAlertEntryRequestHelper.getPermissionChecker(),
			GroupConstants.DEFAULT_LIVE_GROUP_ID,
			CommerceMLForecastAlertActionKeys.VIEW_ALERTS);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceMLForecastAlertEntryListDisplayContext.class);

	private final CommerceAccountLocalService _commerceAccountLocalService;
	private final CommerceMLForecastAlertEntryRequestHelper
		_commerceMLForecastAlertEntryRequestHelper;
	private final CommerceMLForecastAlertEntryService
		_commerceMLForecastAlertEntryService;
	private final PortletResourcePermission _portletResourcePermission;
	private SearchContainer<CommerceMLForecastAlertEntry> _searchContainer;

}