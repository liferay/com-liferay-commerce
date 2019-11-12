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

package com.liferay.commerce.application.item.selector.web.internal;

import com.liferay.commerce.application.item.selector.criterion.CommerceApplicationModelItemSelectorCriterion;
import com.liferay.commerce.application.item.selector.web.internal.display.context.CommerceApplicationModelItemSelectorViewDisplayContext;
import com.liferay.commerce.application.service.CommerceApplicationModelService;
import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.ItemSelectorView;
import com.liferay.item.selector.criteria.UUIDItemSelectorReturnType;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletURL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = ItemSelectorView.class)
public class CommerceApplicationModelItemSelectorView
	implements ItemSelectorView<CommerceApplicationModelItemSelectorCriterion> {

	@Override
	public Class<CommerceApplicationModelItemSelectorCriterion>
		getItemSelectorCriterionClass() {

		return CommerceApplicationModelItemSelectorCriterion.class;
	}

	public ServletContext getServletContext() {
		return _servletContext;
	}

	@Override
	public List<ItemSelectorReturnType> getSupportedItemSelectorReturnTypes() {
		return _supportedItemSelectorReturnTypes;
	}

	@Override
	public String getTitle(Locale locale) {
		return LanguageUtil.get(locale, "folders");
	}

	@Override
	public boolean isShowSearch() {
		return true;
	}

	@Override
	public boolean isVisible(ThemeDisplay themeDisplay) {
		return true;
	}

	@Override
	public void renderHTML(
			ServletRequest servletRequest, ServletResponse servletResponse,
			CommerceApplicationModelItemSelectorCriterion
				commerceApplicationModelItemSelectorCriterion,
			PortletURL portletURL, String itemSelectedEventName, boolean search)
		throws IOException, ServletException {

		HttpServletRequest httpServletRequest =
			(HttpServletRequest)servletRequest;

		CommerceApplicationModelItemSelectorViewDisplayContext
			commerceApplicationModelItemSelectorViewDisplayContext =
				new CommerceApplicationModelItemSelectorViewDisplayContext(
					_commerceApplicationModelService, httpServletRequest,
					portletURL, itemSelectedEventName);

		httpServletRequest.setAttribute(
			WebKeys.PORTLET_DISPLAY_CONTEXT,
			commerceApplicationModelItemSelectorViewDisplayContext);

		ServletContext servletContext = getServletContext();

		RequestDispatcher requestDispatcher =
			servletContext.getRequestDispatcher(
				"/application_model_item_selector.jsp");

		requestDispatcher.include(servletRequest, servletResponse);
	}

	private static final List<ItemSelectorReturnType>
		_supportedItemSelectorReturnTypes = Collections.unmodifiableList(
			ListUtil.fromArray(
				new ItemSelectorReturnType[] {
					new UUIDItemSelectorReturnType()
				}));

	@Reference
	private CommerceApplicationModelService _commerceApplicationModelService;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.commerce.application.item.selector.web)"
	)
	private ServletContext _servletContext;

}