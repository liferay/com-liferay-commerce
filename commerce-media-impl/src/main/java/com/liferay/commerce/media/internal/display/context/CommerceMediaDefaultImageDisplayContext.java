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

package com.liferay.commerce.media.internal.display.context;

import com.liferay.commerce.media.impl.configuration.CommerceMediaDefaultImageConfiguration;
import com.liferay.commerce.product.constants.CPActionKeys;
import com.liferay.commerce.product.definitions.web.configuration.AttachmentsConfiguration;
import com.liferay.commerce.product.display.context.util.CPRequestHelper;
import com.liferay.document.library.kernel.service.DLAppService;
import com.liferay.item.selector.ItemSelector;
import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.criteria.FileEntryItemSelectorReturnType;
import com.liferay.item.selector.criteria.image.criterion.ImageItemSelectorCriterion;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Collections;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alec Sloan
 */
public class CommerceMediaDefaultImageDisplayContext {

	public CommerceMediaDefaultImageDisplayContext(
		AttachmentsConfiguration attachmentsConfiguration,
		ConfigurationProvider configurationProvider, DLAppService dlAppService,
		ItemSelector itemSelector,
		PortletResourcePermission portletResourcePermission,
		HttpServletRequest httpServletRequest) {

		_attachmentsConfiguration = attachmentsConfiguration;
		_configurationProvider = configurationProvider;
		_dlAppService = dlAppService;
		_itemSelector = itemSelector;
		_portletResourcePermission = portletResourcePermission;
		_httpServletRequest = httpServletRequest;

		_cpRequestHelper = new CPRequestHelper(_httpServletRequest);
	}

	public FileEntry getDefaultFileEntry() throws PortalException {
		long fileEntryId = getDefaultFileEntryId();

		if (fileEntryId == 0) {
			return null;
		}

		return _dlAppService.getFileEntry(fileEntryId);
	}

	public long getDefaultFileEntryId() throws PortalException {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)_httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		CommerceMediaDefaultImageConfiguration
			commerceMediaDefaultImageConfiguration =
				_configurationProvider.getGroupConfiguration(
					CommerceMediaDefaultImageConfiguration.class,
					themeDisplay.getSiteGroupId());

		return commerceMediaDefaultImageConfiguration.defaultFileEntryId();
	}

	public String[] getImageExtensions() {
		return _attachmentsConfiguration.imageExtensions();
	}

	public String getImageItemSelectorUrl() {
		RequestBackedPortletURLFactory requestBackedPortletURLFactory =
			RequestBackedPortletURLFactoryUtil.create(
				_cpRequestHelper.getRenderRequest());

		ImageItemSelectorCriterion imageItemSelectorCriterion =
			new ImageItemSelectorCriterion();

		imageItemSelectorCriterion.setDesiredItemSelectorReturnTypes(
			Collections.<ItemSelectorReturnType>singletonList(
				new FileEntryItemSelectorReturnType()));

		PortletURL itemSelectorURL = _itemSelector.getItemSelectorURL(
			requestBackedPortletURLFactory, "addFileEntry",
			imageItemSelectorCriterion);

		return itemSelectorURL.toString();
	}

	public long getImageMaxSize() {
		return _attachmentsConfiguration.imageMaxSize();
	}

	public boolean hasManageCommerceMediaDefaultImagesPermission() {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)_httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		return _portletResourcePermission.contains(
			themeDisplay.getPermissionChecker(), themeDisplay.getScopeGroupId(),
			CPActionKeys.MANAGE_CATALOG);
	}

	private final AttachmentsConfiguration _attachmentsConfiguration;
	private final ConfigurationProvider _configurationProvider;
	private final CPRequestHelper _cpRequestHelper;
	private final DLAppService _dlAppService;
	private final HttpServletRequest _httpServletRequest;
	private final ItemSelector _itemSelector;
	private final PortletResourcePermission _portletResourcePermission;

}