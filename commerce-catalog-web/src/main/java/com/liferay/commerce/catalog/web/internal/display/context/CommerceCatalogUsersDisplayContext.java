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

package com.liferay.commerce.catalog.web.internal.display.context;

import com.liferay.commerce.catalog.web.display.context.BaseCommerceCatalogSearchContainerDisplayContext;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.service.CommerceCatalogLocalServiceUtil;
import com.liferay.commerce.product.service.CommerceCatalogService;
import com.liferay.item.selector.ItemSelector;
import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.criteria.UUIDItemSelectorReturnType;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.roles.item.selector.RoleItemSelectorCriterion;
import com.liferay.users.admin.item.selector.UserItemSelectorCriterion;

import java.util.Collections;
import java.util.List;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alec Sloan
 */
public class CommerceCatalogUsersDisplayContext
	extends BaseCommerceCatalogSearchContainerDisplayContext<User> {

	public CommerceCatalogUsersDisplayContext(
			HttpServletRequest httpServletRequest,
			CommerceCatalogService commerceCatalogService,
			ItemSelector itemSelector, Portal portal,
			PortletResourcePermission portletResourcePermission,
			UserService userService)
		throws PortalException {

		super(httpServletRequest, User.class.getSimpleName());

		setDefaultOrderByType("desc");

		_commerceCatalogService = commerceCatalogService;
		_itemSelector = itemSelector;
		_portal = portal;
		_portletResourcePermission = portletResourcePermission;
		_userService = userService;
	}

	public CommerceCatalog getCommerceCatalog() throws PortalException {
		long commerceCatalogId = ParamUtil.getLong(
			httpServletRequest, "commerceCatalogId");

		return _commerceCatalogService.fetchCommerceCatalog(commerceCatalogId);
	}

	public long getCommerceCatalogGroupId() throws PortalException {
		long commerceCatalogId = ParamUtil.getLong(
			httpServletRequest, "commerceCatalogId");

		Group group = CommerceCatalogLocalServiceUtil.getCommerceCatalogGroup(
			commerceCatalogId);

		return group.getGroupId();
	}

	public String getItemSelectorUrl() throws PortalException {
		RequestBackedPortletURLFactory requestBackedPortletURLFactory =
			RequestBackedPortletURLFactoryUtil.create(httpServletRequest);

		UserItemSelectorCriterion userItemSelectorCriterion =
			new UserItemSelectorCriterion();

		userItemSelectorCriterion.setDesiredItemSelectorReturnTypes(
			Collections.<ItemSelectorReturnType>singletonList(
				new UUIDItemSelectorReturnType()));

		PortletURL itemSelectorURL = _itemSelector.getItemSelectorURL(
			requestBackedPortletURLFactory, "usersSelectItem",
			userItemSelectorCriterion);

		String checkedUserIds = StringUtil.merge(getCheckedUserIds());

		itemSelectorURL.setParameter("checkedUserIds", checkedUserIds);

		return itemSelectorURL.toString();
	}

	@Override
	public PortletURL getPortletURL() throws PortalException {
		PortletURL portletURL = super.getPortletURL();

		String filterFields = ParamUtil.getString(
			httpServletRequest, "filterFields");

		if (Validator.isNotNull(filterFields)) {
			portletURL.setParameter("filterFields", filterFields);
		}

		String filtersLabels = ParamUtil.getString(
			httpServletRequest, "filtersLabels");

		if (Validator.isNotNull(filtersLabels)) {
			portletURL.setParameter("filtersLabels", filtersLabels);
		}

		String filtersValues = ParamUtil.getString(
			httpServletRequest, "filtersValues");

		if (Validator.isNotNull(filtersValues)) {
			portletURL.setParameter("filtersValues", filtersValues);
		}

		return portletURL;
	}

	public String getRolesItemSelectorUrl() throws PortalException {
		RequestBackedPortletURLFactory requestBackedPortletURLFactory =
			RequestBackedPortletURLFactoryUtil.create(httpServletRequest);

		RoleItemSelectorCriterion roleItemSelectorCriterion =
			new RoleItemSelectorCriterion();

		roleItemSelectorCriterion.setType(RoleConstants.TYPE_SITE);

		roleItemSelectorCriterion.setDesiredItemSelectorReturnTypes(
			Collections.<ItemSelectorReturnType>singletonList(
				new UUIDItemSelectorReturnType()));

		PortletURL itemSelectorURL = _itemSelector.getItemSelectorURL(
			requestBackedPortletURLFactory, "rolesSelectItem",
			roleItemSelectorCriterion);

		return itemSelectorURL.toString();
	}

	@Override
	public SearchContainer<User> getSearchContainer() throws PortalException {
		if (searchContainer != null) {
			return searchContainer;
		}

		searchContainer = new SearchContainer<>(
			liferayPortletRequest, getPortletURL(), null, null);

		searchContainer.setOrderByCol(getOrderByCol());
		searchContainer.setOrderByType(getOrderByType());
		searchContainer.setRowChecker(getRowChecker());

		List<User> users = getSelectedUsers();

		searchContainer.setTotal(users.size());
		searchContainer.setResults(users);

		return searchContainer;
	}

	public List<User> getSelectedUsers() throws PortalException {
		return _userService.getGroupUsers(getCommerceCatalogGroupId());
	}

	public List<Role> getUserRoles(long userId) throws PortalException {
		if (userId == 0) {
			return null;
		}

		return RoleLocalServiceUtil.getUserGroupRoles(
			userId, getCommerceCatalogGroupId());
	}

	public List<User> getUsers() throws PortalException {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		return _userService.getCompanyUsers(
			themeDisplay.getCompanyId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	protected long[] getCheckedUserIds() throws PortalException {
		return ListUtil.toLongArray(getSelectedUsers(), User.USER_ID_ACCESSOR);
	}

	private final CommerceCatalogService _commerceCatalogService;
	private final ItemSelector _itemSelector;
	private final Portal _portal;
	private final PortletResourcePermission _portletResourcePermission;
	private final UserService _userService;

}