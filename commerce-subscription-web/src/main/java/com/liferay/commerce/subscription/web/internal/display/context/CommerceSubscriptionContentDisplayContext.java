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

package com.liferay.commerce.subscription.web.internal.display.context;

import com.liferay.commerce.model.CommerceSubscriptionEntry;
import com.liferay.commerce.product.display.context.util.CPRequestHelper;
import com.liferay.commerce.product.model.CPAttachmentFileEntry;
import com.liferay.commerce.product.model.CPAttachmentFileEntryConstants;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.util.CPDefinitionHelper;
import com.liferay.commerce.product.util.CPInstanceHelper;
import com.liferay.commerce.service.CommerceSubscriptionEntryService;
import com.liferay.commerce.util.comparator.CommerceSubscriptionEntryCreateDateComparator;
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
public class CommerceSubscriptionContentDisplayContext {

	public CommerceSubscriptionContentDisplayContext(
			CPDefinitionHelper cpDefinitionHelper,
			CPInstanceHelper cpInstanceHelper,
			CommerceSubscriptionEntryService commerceSubscriptionEntryService,
			HttpServletRequest httpServletRequest)
		throws PortalException {

		_cpDefinitionHelper = cpDefinitionHelper;
		_cpInstanceHelper = cpInstanceHelper;
		_commerceSubscriptionEntryService = commerceSubscriptionEntryService;

		_cpRequestHelper = new CPRequestHelper(httpServletRequest);
	}

	public String getCommerceSubscriptionEntryThumbnailSrc(
			CommerceSubscriptionEntry commerceSubscriptionEntry,
			ThemeDisplay themeDisplay)
		throws Exception {

		CPInstance cpInstance = commerceSubscriptionEntry.getCPInstance();

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

	public String getCPDefinitionURL(
			CommerceSubscriptionEntry commerceSubscriptionEntry,
			ThemeDisplay themeDisplay)
		throws PortalException {

		return _cpDefinitionHelper.getFriendlyURL(
			commerceSubscriptionEntry.getCPDefinitionId(), themeDisplay);
	}

	public List<KeyValuePair> getKeyValuePairs(
			CommerceSubscriptionEntry commerceSubscriptionEntry)
		throws PortalException {

		CPInstance cpInstance = commerceSubscriptionEntry.getCPInstance();

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

	public SearchContainer<CommerceSubscriptionEntry> getSearchContainer()
		throws PortalException {

		if (_searchContainer != null) {
			return _searchContainer;
		}

		_searchContainer = new SearchContainer<>(
			_cpRequestHelper.getLiferayPortletRequest(), getPortletURL(), null,
			"there-are-no-subscriptions");

		OrderByComparator<CommerceSubscriptionEntry> orderByComparator =
			new CommerceSubscriptionEntryCreateDateComparator();

		int total =
			_commerceSubscriptionEntryService.getCPSubscriptionEntriesCount(
				_cpRequestHelper.getScopeGroupId(),
				_cpRequestHelper.getUserId());

		List<CommerceSubscriptionEntry> results =
			_commerceSubscriptionEntryService.getCPSubscriptionEntries(
				_cpRequestHelper.getScopeGroupId(),
				_cpRequestHelper.getUserId(), _searchContainer.getStart(),
				_searchContainer.getEnd(), orderByComparator);

		_searchContainer.setTotal(total);
		_searchContainer.setResults(results);

		return _searchContainer;
	}

	private final CommerceSubscriptionEntryService
		_commerceSubscriptionEntryService;
	private final CPDefinitionHelper _cpDefinitionHelper;
	private final CPInstanceHelper _cpInstanceHelper;
	private final CPRequestHelper _cpRequestHelper;
	private SearchContainer<CommerceSubscriptionEntry> _searchContainer;

}