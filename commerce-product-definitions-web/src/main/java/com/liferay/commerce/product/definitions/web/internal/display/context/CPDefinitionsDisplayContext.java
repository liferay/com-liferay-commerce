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

package com.liferay.commerce.product.definitions.web.internal.display.context;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetVocabularyServiceUtil;
import com.liferay.commerce.product.definitions.web.display.context.BaseCPDefinitionsSearchContainerDisplayContext;
import com.liferay.commerce.product.definitions.web.internal.util.CPDefinitionsPortletUtil;
import com.liferay.commerce.product.definitions.web.portlet.action.ActionHelper;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.service.CPDefinitionService;
import com.liferay.commerce.product.service.CommerceCatalogService;
import com.liferay.frontend.taglib.servlet.taglib.ManagementBarFilterItem;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.taglib.util.CustomAttributesUtil;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alessio Antonio Rendina
 * @author Marco Leo
 */
public class CPDefinitionsDisplayContext
	extends BaseCPDefinitionsSearchContainerDisplayContext<CPDefinition> {

	public CPDefinitionsDisplayContext(
		ActionHelper actionHelper, HttpServletRequest httpServletRequest,
		CommerceCatalogService commerceCatalogService,
		CPDefinitionService cpDefinitionService) {

		super(
			actionHelper, httpServletRequest,
			CPDefinition.class.getSimpleName());

		setDefaultOrderByType("desc");

		_commerceCatalogService = commerceCatalogService;
		_cpDefinitionService = cpDefinitionService;
	}

	public CommerceCatalog fetchCommerceCatalogByGroupId(long groupId)
		throws PortalException {

		return _commerceCatalogService.fetchCommerceCatalogByGroupId(groupId);
	}

	public String getCategorySelectorURL(String eventName) throws Exception {
		PortletURL portletURL = PortletProviderUtil.getPortletURL(
			httpServletRequest, AssetCategory.class.getName(),
			PortletProvider.Action.BROWSE);

		if (portletURL == null) {
			return null;
		}

		portletURL.setParameter("eventName", eventName);
		portletURL.setParameter("selectedCategories", "{selectedCategories}");
		portletURL.setParameter("singleSelect", "{singleSelect}");
		portletURL.setParameter("vocabularyIds", "{vocabularyIds}");
		portletURL.setWindowState(LiferayWindowState.POP_UP);

		return portletURL.toString();
	}

	public List<CommerceCatalog> getCommerceCatalogs() throws PortalException {
		return _commerceCatalogService.searchCommerceCatalogs(
			cpRequestHelper.getCompanyId(), null, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	@Override
	public List<ManagementBarFilterItem> getManagementBarStatusFilterItems()
		throws PortalException, PortletException {

		List<ManagementBarFilterItem> managementBarFilterItems =
			super.getManagementBarStatusFilterItems();

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		int workflowDefinitionLinksCount =
			WorkflowDefinitionLinkLocalServiceUtil.
				getWorkflowDefinitionLinksCount(
					themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(),
					CPDefinition.class.getName());

		if (workflowDefinitionLinksCount == 0) {
			workflowDefinitionLinksCount =
				WorkflowDefinitionLinkLocalServiceUtil.
					getWorkflowDefinitionLinksCount(
						themeDisplay.getCompanyId(),
						WorkflowConstants.DEFAULT_GROUP_ID,
						CPDefinition.class.getName());
		}

		if (workflowDefinitionLinksCount > 0) {
			managementBarFilterItems.add(
				getManagementBarFilterItem(WorkflowConstants.STATUS_PENDING));
			managementBarFilterItems.add(
				getManagementBarFilterItem(WorkflowConstants.STATUS_DENIED));
		}

		return managementBarFilterItems;
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

	@Override
	public SearchContainer<CPDefinition> getSearchContainer()
		throws PortalException {

		if (searchContainer != null) {
			return searchContainer;
		}

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		searchContainer = new SearchContainer<>(
			liferayPortletRequest, getPortletURL(), null, null);

		OrderByComparator<CPDefinition> orderByComparator =
			CPDefinitionsPortletUtil.getCPDefinitionOrderByComparator(
				getOrderByCol(), getOrderByType());

		searchContainer.setOrderByCol(getOrderByCol());
		searchContainer.setOrderByComparator(orderByComparator);
		searchContainer.setOrderByType(getOrderByType());
		searchContainer.setRowChecker(getRowChecker());

		Sort sort = CPDefinitionsPortletUtil.getCPDefinitionSort(
			getOrderByCol(), getOrderByType());

		String filterFields = ParamUtil.getString(
			httpServletRequest, "filterFields");

		String filtersValues = ParamUtil.getString(
			httpServletRequest, "filtersValues");

		BaseModelSearchResult<CPDefinition> cpDefinitionBaseModelSearchResult =
			_cpDefinitionService.searchCPDefinitions(
				themeDisplay.getCompanyId(), getKeywords(), filterFields,
				filtersValues, searchContainer.getStart(),
				searchContainer.getEnd(), sort);

		searchContainer.setTotal(cpDefinitionBaseModelSearchResult.getLength());
		searchContainer.setResults(
			cpDefinitionBaseModelSearchResult.getBaseModels());

		return searchContainer;
	}

	public String getUrlTitleMapAsXML() throws PortalException {
		long cpDefinitionId = getCPDefinitionId();

		if (cpDefinitionId <= 0) {
			return StringPool.BLANK;
		}

		return _cpDefinitionService.getUrlTitleMapAsXML(cpDefinitionId);
	}

	public String getVocabularyIds() throws Exception {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		List<AssetVocabulary> vocabularies =
			AssetVocabularyServiceUtil.getGroupVocabularies(
				themeDisplay.getCompanyGroupId());

		return ListUtil.toString(
			vocabularies, AssetVocabulary.VOCABULARY_ID_ACCESSOR);
	}

	public boolean hasApprovedCPInstance(CPDefinition cpDefinition) {
		if (cpDefinition == null) {
			return true;
		}

		for (CPInstance cpInstance : cpDefinition.getCPInstances()) {
			if (cpInstance.isApproved()) {
				return true;
			}
		}

		return false;
	}

	public boolean hasCustomAttributesAvailable() throws Exception {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		return CustomAttributesUtil.hasCustomAttributes(
			themeDisplay.getCompanyId(), CPDefinition.class.getName(),
			getCPDefinitionId(), null);
	}

	public boolean isSelectedCatalog(CommerceCatalog commerceCatalog)
		throws PortalException {

		CPDefinition cpDefinition = getCPDefinition();

		if (commerceCatalog.getGroupId() == cpDefinition.getGroupId()) {
			return true;
		}

		return false;
	}

	private final CommerceCatalogService _commerceCatalogService;
	private final CPDefinitionService _cpDefinitionService;

}