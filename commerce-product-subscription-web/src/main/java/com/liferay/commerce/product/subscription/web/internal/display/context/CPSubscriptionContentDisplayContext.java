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

package com.liferay.commerce.product.subscription.web.internal.display.context;

import com.liferay.commerce.model.CPSubscriptionEntry;
import com.liferay.commerce.product.display.context.util.CPRequestHelper;
import com.liferay.commerce.product.model.CPAttachmentFileEntry;
import com.liferay.commerce.product.model.CPAttachmentFileEntryConstants;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.util.CPDefinitionHelper;
import com.liferay.commerce.product.util.CPInstanceHelper;
import com.liferay.commerce.service.CPSubscriptionEntryService;
import com.liferay.commerce.util.comparator.CPSubscriptionEntryCreateDateComparator;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.KeyValuePair;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alessio Antonio Rendina
 */
public class CPSubscriptionContentDisplayContext {

	public CPSubscriptionContentDisplayContext(
			CPDefinitionHelper cpDefinitionHelper,
			CPInstanceHelper cpInstanceHelper,
			CPSubscriptionEntryService cpSubscriptionEntryService,
			HttpServletRequest httpServletRequest)
		throws PortalException {

		_cpDefinitionHelper = cpDefinitionHelper;
		_cpInstanceHelper = cpInstanceHelper;
		_cpSubscriptionEntryService = cpSubscriptionEntryService;

		_cpRequestHelper = new CPRequestHelper(httpServletRequest);
	}

	public String getCPDefinitionURL(
			CPSubscriptionEntry cpSubscriptionEntry, ThemeDisplay themeDisplay)
		throws PortalException {

		return _cpDefinitionHelper.getFriendlyURL(
			cpSubscriptionEntry.getCPDefinitionId(), themeDisplay);
	}

	public String getCPSubscriptionEntryThumbnailSrc(
			CPSubscriptionEntry cpSubscriptionEntry, ThemeDisplay themeDisplay)
		throws Exception {

		CPInstance cpInstance = cpSubscriptionEntry.getCPInstance();

		List<CPAttachmentFileEntry> cpAttachmentFileEntries =
			_cpInstanceHelper.getCPAttachmentFileEntries(
				cpInstance.getCPDefinitionId(), cpInstance.getJson(),
				CPAttachmentFileEntryConstants.TYPE_IMAGE);

		if (cpAttachmentFileEntries.isEmpty()) {
			CPDefinition cpDefinition = cpInstance.getCPDefinition();

			return cpDefinition.getDefaultImageThumbnailSrc(themeDisplay);
		}

		CPAttachmentFileEntry cpAttachmentFileEntry =
			cpAttachmentFileEntries.get(0);

		FileEntry fileEntry = cpAttachmentFileEntry.getFileEntry();

		if (fileEntry == null) {
			return null;
		}

		return DLUtil.getThumbnailSrc(fileEntry, themeDisplay);
	}

	public List<KeyValuePair> getKeyValuePairs(
			CPSubscriptionEntry cpSubscriptionEntry)
		throws PortalException {

		CPInstance cpInstance = cpSubscriptionEntry.getCPInstance();

		return _cpInstanceHelper.getKeyValuePairs(
			cpInstance.getJson(), _cpRequestHelper.getLocale());
	}

	public PortletURL getPortletURL() throws PortalException {
		LiferayPortletResponse liferayPortletResponse =
			_cpRequestHelper.getLiferayPortletResponse();

		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		HttpServletRequest httpServletRequest = _cpRequestHelper.getRequest();

		String redirect = ParamUtil.getString(httpServletRequest, "redirect");

		if (Validator.isNotNull(redirect)) {
			portletURL.setParameter("redirect", redirect);
		}

		String delta = ParamUtil.getString(httpServletRequest, "delta");

		if (Validator.isNotNull(delta)) {
			portletURL.setParameter("delta", delta);
		}

		String deltaEntry = ParamUtil.getString(
			httpServletRequest, "deltaEntry");

		if (Validator.isNotNull(deltaEntry)) {
			portletURL.setParameter("deltaEntry", deltaEntry);
		}

		return portletURL;
	}

	public SearchContainer<CPSubscriptionEntry> getSearchContainer()
		throws PortalException {

		if (_searchContainer != null) {
			return _searchContainer;
		}

		_searchContainer = new SearchContainer<>(
			_cpRequestHelper.getLiferayPortletRequest(), getPortletURL(), null,
			"there-are-no-subscriptions");

		OrderByComparator<CPSubscriptionEntry> orderByComparator =
			new CPSubscriptionEntryCreateDateComparator();

		int total = _cpSubscriptionEntryService.getCPSubscriptionEntriesCount(
			_cpRequestHelper.getScopeGroupId(), _cpRequestHelper.getUserId());

		List<CPSubscriptionEntry> results =
			_cpSubscriptionEntryService.getCPSubscriptionEntries(
				_cpRequestHelper.getScopeGroupId(),
				_cpRequestHelper.getUserId(), _searchContainer.getStart(),
				_searchContainer.getEnd(), orderByComparator);

		_searchContainer.setTotal(total);
		_searchContainer.setResults(results);

		return _searchContainer;
	}

	private final CPDefinitionHelper _cpDefinitionHelper;
	private final CPInstanceHelper _cpInstanceHelper;
	private final CPRequestHelper _cpRequestHelper;
	private final CPSubscriptionEntryService _cpSubscriptionEntryService;
	private SearchContainer<CPSubscriptionEntry> _searchContainer;

}