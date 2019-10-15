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

package com.liferay.commerce.notification.web.internal.display.context;

import com.liferay.commerce.account.item.selector.criterion.CommerceAccountGroupItemSelectorCriterion;
import com.liferay.commerce.account.model.CommerceAccountGroup;
import com.liferay.commerce.account.service.CommerceAccountGroupService;
import com.liferay.commerce.notification.constants.CommerceNotificationActionKeys;
import com.liferay.commerce.notification.model.CommerceNotificationTemplate;
import com.liferay.commerce.notification.model.CommerceNotificationTemplateCommerceAccountGroupRel;
import com.liferay.commerce.notification.service.CommerceNotificationTemplateCommerceAccountGroupRelService;
import com.liferay.commerce.notification.service.CommerceNotificationTemplateService;
import com.liferay.commerce.notification.type.CommerceNotificationType;
import com.liferay.commerce.notification.type.CommerceNotificationTypeRegistry;
import com.liferay.commerce.notification.util.comparator.CommerceNotificationTemplateCommerceAccountGroupRelCreateDateComparator;
import com.liferay.commerce.notification.web.internal.admin.NotificationsCommerceAdminModule;
import com.liferay.commerce.notification.web.internal.display.context.util.CommerceNotificationsRequestHelper;
import com.liferay.commerce.notification.web.internal.util.CommerceNotificationsUtil;
import com.liferay.commerce.order.CommerceDefinitionTermContributor;
import com.liferay.commerce.order.CommerceDefinitionTermContributorRegistry;
import com.liferay.item.selector.ItemSelector;
import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.criteria.UUIDItemSelectorReturnType;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.RowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Stream;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceNotificationTemplatesDisplayContext {

	public CommerceNotificationTemplatesDisplayContext(
		CommerceDefinitionTermContributorRegistry
			commerceDefinitionTermContributorRegistry,
		CommerceNotificationTemplateService commerceNotificationTemplateService,
		CommerceNotificationTemplateCommerceAccountGroupRelService
			commerceNotificationTemplateCommerceAccountGroupRelService,
		CommerceNotificationTypeRegistry commerceNotificationTypeRegistry,
		CommerceAccountGroupService commerceAccountGroupService,
		HttpServletRequest httpServletRequest, ItemSelector itemSelector,
		PortletResourcePermission portletResourcePermission) {

		_commerceDefinitionTermContributorRegistry =
			commerceDefinitionTermContributorRegistry;
		_commerceNotificationTemplateService =
			commerceNotificationTemplateService;
		_commerceNotificationTemplateCommerceAccountGroupRelService =
			commerceNotificationTemplateCommerceAccountGroupRelService;
		_commerceNotificationTypeRegistry = commerceNotificationTypeRegistry;
		_commerceAccountGroupService = commerceAccountGroupService;
		_itemSelector = itemSelector;
		_portletResourcePermission = portletResourcePermission;

		_commerceNotificationsRequestHelper =
			new CommerceNotificationsRequestHelper(httpServletRequest);
	}

	public CommerceAccountGroup getCommerceAccountGroup(
			long commerceAccountGroupId)
		throws PortalException {

		return _commerceAccountGroupService.getCommerceAccountGroup(
			commerceAccountGroupId);
	}

	public CommerceNotificationTemplate getCommerceNotificationTemplate()
		throws PortalException {

		if (_commerceNotificationTemplate != null) {
			return _commerceNotificationTemplate;
		}

		long commerceNotificationTemplateId = ParamUtil.getLong(
			_commerceNotificationsRequestHelper.getRequest(),
			"commerceNotificationTemplateId");

		if (commerceNotificationTemplateId > 0) {
			_commerceNotificationTemplate =
				_commerceNotificationTemplateService.
					getCommerceNotificationTemplate(
						commerceNotificationTemplateId);
		}

		return _commerceNotificationTemplate;
	}

	public List<CommerceNotificationTemplateCommerceAccountGroupRel>
			getCommerceNotificationTemplateCommerceAccountGroupRels()
		throws PortalException {

		return _commerceNotificationTemplateCommerceAccountGroupRelService.
			getCommerceNotificationTemplateCommerceAccountGroupRels(
				getCommerceNotificationTemplateId(), QueryUtil.ALL_POS,
				QueryUtil.ALL_POS,
				new CommerceNotificationTemplateCommerceAccountGroupRelCreateDateComparator());
	}

	public long getCommerceNotificationTemplateId() throws PortalException {
		CommerceNotificationTemplate commerceNotificationTemplate =
			getCommerceNotificationTemplate();

		if (commerceNotificationTemplate == null) {
			return 0;
		}

		return commerceNotificationTemplate.getCommerceNotificationTemplateId();
	}

	public CommerceNotificationType getCommerceNotificationType(String key) {
		return _commerceNotificationTypeRegistry.getCommerceNotificationType(
			key);
	}

	public List<CommerceNotificationType> getCommerceNotificationTypes() {
		return _commerceNotificationTypeRegistry.getCommerceNotificationTypes();
	}

	public Map<String, String> getDefinitionTerms(
		String contributorKey, String notificationTypeKey, Locale locale) {

		List<CommerceDefinitionTermContributor>
			definitionTermContributorsByContributorKey =
				_commerceDefinitionTermContributorRegistry.
					getDefinitionTermContributorsByContributorKey(
						contributorKey);
		List<CommerceDefinitionTermContributor>
			definitionTermContributorsByNotificationTypeKey =
				_commerceDefinitionTermContributorRegistry.
					getDefinitionTermContributorsByNotificationTypeKey(
						notificationTypeKey);

		Map<String, String> results = new HashMap<>();

		for (CommerceDefinitionTermContributor
				commerceDefinitionTermContributor :
					definitionTermContributorsByContributorKey) {

			if (definitionTermContributorsByNotificationTypeKey.contains(
					commerceDefinitionTermContributor)) {

				results.putAll(
					commerceDefinitionTermContributor.getDefinitionTerms(
						locale));
			}
		}

		return results;
	}

	public String getItemSelectorUrl() throws PortalException {
		RequestBackedPortletURLFactory requestBackedPortletURLFactory =
			RequestBackedPortletURLFactoryUtil.create(
				_commerceNotificationsRequestHelper.getRequest());

		CommerceAccountGroupItemSelectorCriterion
			commerceAccountGroupItemSelectorCriterion =
				new CommerceAccountGroupItemSelectorCriterion();

		commerceAccountGroupItemSelectorCriterion.
			setDesiredItemSelectorReturnTypes(
				Collections.<ItemSelectorReturnType>singletonList(
					new UUIDItemSelectorReturnType()));

		PortletURL itemSelectorURL = _itemSelector.getItemSelectorURL(
			requestBackedPortletURLFactory, "accountGroupsSelectItem",
			commerceAccountGroupItemSelectorCriterion);

		String checkedCommerceAccountGroupIds = StringUtil.merge(
			getCheckedcommerceAccountGroupIds());

		itemSelectorURL.setParameter(
			"checkedCommerceAccountGroupIds", checkedCommerceAccountGroupIds);

		return itemSelectorURL.toString();
	}

	public String getOrderByCol() {
		return ParamUtil.getString(
			_commerceNotificationsRequestHelper.getRequest(),
			SearchContainer.DEFAULT_ORDER_BY_COL_PARAM, "modified-date");
	}

	public String getOrderByType() {
		return ParamUtil.getString(
			_commerceNotificationsRequestHelper.getRequest(),
			SearchContainer.DEFAULT_ORDER_BY_TYPE_PARAM, "desc");
	}

	public PortletURL getPortletURL() {
		LiferayPortletResponse liferayPortletResponse =
			_commerceNotificationsRequestHelper.getLiferayPortletResponse();

		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		portletURL.setParameter(
			"mvcRenderCommandName", "viewCommerceNotificationTemplates");
		portletURL.setParameter(
			"commerceAdminModuleKey", NotificationsCommerceAdminModule.KEY);

		String delta = ParamUtil.getString(
			_commerceNotificationsRequestHelper.getRequest(), "delta");

		if (Validator.isNotNull(delta)) {
			portletURL.setParameter("delta", delta);
		}

		portletURL.setParameter("orderByCol", getOrderByCol());
		portletURL.setParameter("orderByType", getOrderByType());

		return portletURL;
	}

	public SearchContainer<CommerceNotificationTemplate> getSearchContainer()
		throws PortalException {

		if (_searchContainer != null) {
			return _searchContainer;
		}

		Boolean enabled = null;
		String emptyResultsMessage = "there-are-no-notification-templates";

		String navigation = getNavigation();

		if (navigation.equals("enabled")) {
			enabled = Boolean.TRUE;
			emptyResultsMessage = "there-are-no-enabled-notification-templates";
		}
		else if (navigation.equals("disabled")) {
			enabled = Boolean.FALSE;
			emptyResultsMessage =
				"there-are-no-disabled-notification-templates";
		}

		_searchContainer = new SearchContainer<>(
			_commerceNotificationsRequestHelper.getLiferayPortletRequest(),
			getPortletURL(), null, emptyResultsMessage);

		if (isShowAddButton()) {
			_searchContainer.setEmptyResultsMessageCssClass(
				"taglib-empty-result-message-header-has-plus-btn");
		}

		String orderByCol = getOrderByCol();
		String orderByType = getOrderByType();

		OrderByComparator<CommerceNotificationTemplate> orderByComparator =
			CommerceNotificationsUtil.
				getCommerceNotificationTemplateOrderByComparator(
					orderByCol, orderByType);

		_searchContainer.setOrderByCol(orderByCol);
		_searchContainer.setOrderByComparator(orderByComparator);
		_searchContainer.setOrderByType(orderByType);
		_searchContainer.setRowChecker(getRowChecker());

		int total;
		List<CommerceNotificationTemplate> results;

		if (enabled == null) {
			total =
				_commerceNotificationTemplateService.
					getCommerceNotificationTemplatesCount(
						_commerceNotificationsRequestHelper.getScopeGroupId());
			results =
				_commerceNotificationTemplateService.
					getCommerceNotificationTemplates(
						_commerceNotificationsRequestHelper.getScopeGroupId(),
						_searchContainer.getStart(), _searchContainer.getEnd(),
						orderByComparator);
		}
		else {
			total =
				_commerceNotificationTemplateService.
					getCommerceNotificationTemplatesCount(
						_commerceNotificationsRequestHelper.getScopeGroupId(),
						enabled);

			results =
				_commerceNotificationTemplateService.
					getCommerceNotificationTemplates(
						_commerceNotificationsRequestHelper.getScopeGroupId(),
						enabled, _searchContainer.getStart(),
						_searchContainer.getEnd(), orderByComparator);
		}

		_searchContainer.setTotal(total);
		_searchContainer.setResults(results);

		return _searchContainer;
	}

	public boolean isShowAddButton() {
		return _portletResourcePermission.contains(
			_commerceNotificationsRequestHelper.getPermissionChecker(),
			_commerceNotificationsRequestHelper.getScopeGroupId(),
			CommerceNotificationActionKeys.ADD_COMMERCE_NOTIFICATION_TEMPLATE);
	}

	protected long[] getCheckedcommerceAccountGroupIds()
		throws PortalException {

		List<CommerceNotificationTemplateCommerceAccountGroupRel>
			commerceNotificationTemplateCommerceAccountGroupRels =
				getCommerceNotificationTemplateCommerceAccountGroupRels();

		if (commerceNotificationTemplateCommerceAccountGroupRels.isEmpty()) {
			return new long[0];
		}

		Stream<CommerceNotificationTemplateCommerceAccountGroupRel> stream =
			commerceNotificationTemplateCommerceAccountGroupRels.stream();

		return stream.mapToLong(
			CommerceNotificationTemplateCommerceAccountGroupRel::
				getCommerceNotificationTemplateCommerceAccountGroupRelId
		).toArray();
	}

	protected String getNavigation() {
		return ParamUtil.getString(
			_commerceNotificationsRequestHelper.getRequest(), "navigation");
	}

	protected RowChecker getRowChecker() {
		if (_rowChecker == null) {
			_rowChecker = new EmptyOnClickRowChecker(
				_commerceNotificationsRequestHelper.
					getLiferayPortletResponse());
		}

		return _rowChecker;
	}

	private final CommerceAccountGroupService _commerceAccountGroupService;
	private final CommerceDefinitionTermContributorRegistry
		_commerceDefinitionTermContributorRegistry;
	private final CommerceNotificationsRequestHelper
		_commerceNotificationsRequestHelper;
	private CommerceNotificationTemplate _commerceNotificationTemplate;
	private final CommerceNotificationTemplateCommerceAccountGroupRelService
		_commerceNotificationTemplateCommerceAccountGroupRelService;
	private final CommerceNotificationTemplateService
		_commerceNotificationTemplateService;
	private final CommerceNotificationTypeRegistry
		_commerceNotificationTypeRegistry;
	private final ItemSelector _itemSelector;
	private final PortletResourcePermission _portletResourcePermission;
	private RowChecker _rowChecker;
	private SearchContainer<CommerceNotificationTemplate> _searchContainer;

}