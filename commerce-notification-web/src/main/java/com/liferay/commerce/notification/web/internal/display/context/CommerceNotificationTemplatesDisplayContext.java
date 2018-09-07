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

import com.liferay.commerce.notification.constants.CommerceNotificationActionKeys;
import com.liferay.commerce.notification.model.CommerceNotificationTemplate;
import com.liferay.commerce.notification.model.CommerceNotificationTemplateUserSegmentRel;
import com.liferay.commerce.notification.service.CommerceNotificationTemplateService;
import com.liferay.commerce.notification.service.CommerceNotificationTemplateUserSegmentRelService;
import com.liferay.commerce.notification.type.CommerceNotificationType;
import com.liferay.commerce.notification.type.CommerceNotificationTypeRegistry;
import com.liferay.commerce.notification.util.comparator.CommerceNotificationTemplateUserSegmentRelCreateDateComparator;
import com.liferay.commerce.notification.web.internal.admin.NotificationsCommerceAdminModule;
import com.liferay.commerce.notification.web.internal.display.context.util.CommerceNotificationsRequestHelper;
import com.liferay.commerce.notification.web.internal.util.CommerceNotificationsUtil;
import com.liferay.commerce.user.segment.item.selector.criterion.CommerceUserSegmentEntryItemSelectorCriterion;
import com.liferay.commerce.user.segment.model.CommerceUserSegmentEntry;
import com.liferay.commerce.user.segment.service.CommerceUserSegmentEntryService;
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
import java.util.List;
import java.util.stream.Stream;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceNotificationTemplatesDisplayContext {

	public CommerceNotificationTemplatesDisplayContext(
		CommerceNotificationTemplateService commerceNotificationTemplateService,
		CommerceNotificationTemplateUserSegmentRelService
			commerceNotificationTemplateUserSegmentRelService,
		CommerceNotificationTypeRegistry commerceNotificationTypeRegistry,
		CommerceUserSegmentEntryService commerceUserSegmentEntryService,
		HttpServletRequest httpServletRequest, ItemSelector itemSelector,
		PortletResourcePermission portletResourcePermission) {

		_commerceNotificationTemplateService =
			commerceNotificationTemplateService;
		_commerceNotificationTemplateUserSegmentRelService =
			commerceNotificationTemplateUserSegmentRelService;
		_commerceNotificationTypeRegistry = commerceNotificationTypeRegistry;
		_commerceUserSegmentEntryService = commerceUserSegmentEntryService;
		_itemSelector = itemSelector;
		_portletResourcePermission = portletResourcePermission;

		_commerceNotificationsRequestHelper =
			new CommerceNotificationsRequestHelper(httpServletRequest);
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

	public long getCommerceNotificationTemplateId() throws PortalException {
		CommerceNotificationTemplate commerceNotificationTemplate =
			getCommerceNotificationTemplate();

		if (commerceNotificationTemplate == null) {
			return 0;
		}

		return commerceNotificationTemplate.getCommerceNotificationTemplateId();
	}

	public List<CommerceNotificationTemplateUserSegmentRel>
			getCommerceNotificationTemplateUserSegmentRels()
		throws PortalException {

		return _commerceNotificationTemplateUserSegmentRelService.
			getCommerceNotificationTemplateUserSegmentRels(
				getCommerceNotificationTemplateId(), QueryUtil.ALL_POS,
				QueryUtil.ALL_POS,
				new CommerceNotificationTemplateUserSegmentRelCreateDateComparator());
	}

	public CommerceNotificationType getCommerceNotificationType(String key) {
		return _commerceNotificationTypeRegistry.getCommerceNotificationType(
			key);
	}

	public List<CommerceNotificationType> getCommerceNotificationTypes() {
		return _commerceNotificationTypeRegistry.getCommerceNotificationTypes();
	}

	public CommerceUserSegmentEntry getCommerceUserSegmentEntry(
			long commerceUserSegmentEntryId)
		throws PortalException {

		return _commerceUserSegmentEntryService.getCommerceUserSegmentEntry(
			commerceUserSegmentEntryId);
	}

	public String getItemSelectorUrl() throws PortalException {
		RequestBackedPortletURLFactory requestBackedPortletURLFactory =
			RequestBackedPortletURLFactoryUtil.create(
				_commerceNotificationsRequestHelper.getRequest());

		CommerceUserSegmentEntryItemSelectorCriterion
			commerceUserSegmentEntryItemSelectorCriterion =
				new CommerceUserSegmentEntryItemSelectorCriterion();

		commerceUserSegmentEntryItemSelectorCriterion.
			setDesiredItemSelectorReturnTypes(
				Collections.<ItemSelectorReturnType>singletonList(
					new UUIDItemSelectorReturnType()));

		PortletURL itemSelectorURL = _itemSelector.getItemSelectorURL(
			requestBackedPortletURLFactory, "userSegmentsSelectItem",
			commerceUserSegmentEntryItemSelectorCriterion);

		String checkedCommerceUserSegmentEntryIds = StringUtil.merge(
			getCheckedCommerceUserSegmentEntryIds());

		itemSelectorURL.setParameter(
			"checkedCommerceUserSegmentEntryIds",
			checkedCommerceUserSegmentEntryIds);

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

	protected long[] getCheckedCommerceUserSegmentEntryIds()
		throws PortalException {

		List<CommerceNotificationTemplateUserSegmentRel>
			commerceNotificationTemplateUserSegmentRels =
				getCommerceNotificationTemplateUserSegmentRels();

		if (commerceNotificationTemplateUserSegmentRels.isEmpty()) {
			return new long[0];
		}

		Stream<CommerceNotificationTemplateUserSegmentRel> stream =
			commerceNotificationTemplateUserSegmentRels.stream();

		return stream.mapToLong(
			CommerceNotificationTemplateUserSegmentRel::
				getCommerceNotificationTemplateUserSegmentRelId
		).toArray();
	}

	protected String getNavigation() {
		return ParamUtil.getString(
			_commerceNotificationsRequestHelper.getRequest(), "navigation");
	}

	protected RowChecker getRowChecker() {
		if (_rowChecker == null) {
			_rowChecker = new EmptyOnClickRowChecker(
				_commerceNotificationsRequestHelper.getLiferayPortletResponse());
		}

		return _rowChecker;
	}

	private final CommerceNotificationsRequestHelper
		_commerceNotificationsRequestHelper;
	private CommerceNotificationTemplate _commerceNotificationTemplate;
	private final CommerceNotificationTemplateService
		_commerceNotificationTemplateService;
	private final CommerceNotificationTemplateUserSegmentRelService
		_commerceNotificationTemplateUserSegmentRelService;
	private final CommerceNotificationTypeRegistry
		_commerceNotificationTypeRegistry;
	private final CommerceUserSegmentEntryService
		_commerceUserSegmentEntryService;
	private final ItemSelector _itemSelector;
	private final PortletResourcePermission _portletResourcePermission;
	private RowChecker _rowChecker;
	private SearchContainer<CommerceNotificationTemplate> _searchContainer;

}