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

package com.liferay.commerce.product.content.web.internal.portlet.action;

import com.liferay.asset.kernel.exception.AssetTagException;
import com.liferay.asset.kernel.exception.DuplicateQueryRuleException;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.asset.kernel.service.AssetTagLocalService;
import com.liferay.commerce.product.constants.CPPortletKeys;
import com.liferay.commerce.product.content.render.list.CPContentListRendererRegistry;
import com.liferay.commerce.product.content.render.list.entry.CPContentListEntryRendererRegistry;
import com.liferay.commerce.product.content.web.internal.display.context.CPPublisherConfigurationDisplayContext;
import com.liferay.commerce.product.content.web.internal.util.CPPublisherWebHelper;
import com.liferay.commerce.product.content.web.internal.util.CPQueryRule;
import com.liferay.commerce.product.data.source.CPDataSourceRegistry;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.service.CPDefinitionService;
import com.liferay.commerce.product.type.CPTypeServicesTracker;
import com.liferay.commerce.product.util.CPInstanceHelper;
import com.liferay.item.selector.ItemSelector;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + CPPortletKeys.CP_PUBLISHER_WEB,
	service = ConfigurationAction.class
)
public class CPPublisherConfigurationAction extends DefaultConfigurationAction {

	@Override
	public String getJspPath(HttpServletRequest httpServletRequest) {
		try {
			CPPublisherConfigurationDisplayContext
				cpPublisherConfigurationDisplayContext =
					new CPPublisherConfigurationDisplayContext(
						_assetCategoryLocalService, _assetTagLocalService,
						_cpContentListEntryRendererRegistry,
						_cpContentListRendererRegistry, _cpDataSourceRegistry,
						_cpInstanceHelper, _cpPublisherWebHelper,
						_cpTypeServicesTracker, httpServletRequest,
						_itemSelector);

			httpServletRequest.setAttribute(
				WebKeys.PORTLET_DISPLAY_CONTEXT,
				cpPublisherConfigurationDisplayContext);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return "/product_publisher/configuration.jsp";
	}

	@Override
	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		String portletResource = ParamUtil.getString(
			actionRequest, "portletResource");

		PortletPreferences preferences = actionRequest.getPreferences();

		if (cmd.equals(Constants.TRANSLATE)) {
			super.processAction(portletConfig, actionRequest, actionResponse);
		}
		else if (cmd.equals(Constants.UPDATE)) {
			try {
				String selectionStyle = getParameter(
					actionRequest, "selectionStyle");

				if (selectionStyle.equals("dynamic")) {
					updateQueryLogic(actionRequest, preferences);
				}

				super.processAction(
					portletConfig, actionRequest, actionResponse);
			}
			catch (Exception e) {
				if (e instanceof AssetTagException ||
					e instanceof DuplicateQueryRuleException) {

					SessionErrors.add(actionRequest, e.getClass(), e);
				}
				else {
					throw e;
				}
			}
		}
		else if (cmd.equals("add-selection")) {
			addSelection(actionRequest, preferences);
		}
		else if (cmd.equals("move-selection-down")) {
			moveSelectionDown(actionRequest, preferences);
		}
		else if (cmd.equals("move-selection-up")) {
			moveSelectionUp(actionRequest, preferences);
		}
		else if (cmd.equals("remove-selection")) {
			removeSelection(actionRequest, preferences);
		}
		else if (cmd.equals("render-selection")) {
			String renderSelection = getParameter(
				actionRequest, "renderSelection");

			preferences.setValue("renderSelection", renderSelection);
		}
		else if (cmd.equals("select-data-source")) {
			setDataSource(actionRequest, preferences);
		}
		else if (cmd.equals("selection-style")) {
			setSelectionStyle(actionRequest, preferences);
		}

		if (SessionErrors.isEmpty(actionRequest)) {
			preferences.store();

			SessionMessages.add(
				actionRequest,
				_portal.getPortletId(actionRequest) +
					SessionMessages.KEY_SUFFIX_REFRESH_PORTLET,
				portletResource);

			SessionMessages.add(
				actionRequest,
				_portal.getPortletId(actionRequest) +
					SessionMessages.KEY_SUFFIX_UPDATED_CONFIGURATION);
		}

		String redirect = _portal.escapeRedirect(
			ParamUtil.getString(actionRequest, "redirect"));

		if (Validator.isNotNull(redirect)) {
			actionResponse.sendRedirect(redirect);
		}
	}

	@Override
	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.commerce.product.content.web)",
		unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		super.setServletContext(servletContext);
	}

	protected void addSelection(
			PortletPreferences portletPreferences, long cpDefinitionId,
			int productEntryOrder)
		throws Exception {

		CPDefinition cpDefinition = _cpDefinitionService.fetchCPDefinition(
			cpDefinitionId);

		String[] catalogEntryXmls = portletPreferences.getValues(
			"catalogEntryXml", new String[0]);

		String assetEntryXml = _getAssetEntryXml(
			CPDefinition.class.getName(), cpDefinition.getCPDefinitionId());

		if (!ArrayUtil.contains(catalogEntryXmls, assetEntryXml)) {
			if (productEntryOrder > -1) {
				catalogEntryXmls[productEntryOrder] = assetEntryXml;
			}
			else {
				catalogEntryXmls = ArrayUtil.append(
					catalogEntryXmls, assetEntryXml);
			}

			portletPreferences.setValues("catalogEntryXml", catalogEntryXmls);
		}

		try {
			portletPreferences.store();
		}
		catch (IOException ioe) {
			throw new SystemException(ioe);
		}
	}

	protected void addSelection(
			PortletRequest portletRequest,
			PortletPreferences portletPreferences)
		throws Exception {

		long[] cpDefinitionIds = ParamUtil.getLongValues(
			portletRequest, "cpDefinitionIds");

		for (long cpDefinitionId : cpDefinitionIds) {
			addSelection(portletPreferences, cpDefinitionId, -1);
		}
	}

	protected CPQueryRule getQueryRule(ActionRequest actionRequest, int index) {
		boolean contains = ParamUtil.getBoolean(
			actionRequest, "queryContains" + index);
		boolean andOperator = ParamUtil.getBoolean(
			actionRequest, "queryAndOperator" + index);

		String name = ParamUtil.getString(actionRequest, "queryName" + index);

		String[] values = null;

		if (name.equals("assetTags")) {
			values = StringUtil.split(
				ParamUtil.getString(actionRequest, "queryTagNames" + index));
		}
		else {
			values = StringUtil.split(
				ParamUtil.getString(actionRequest, "queryCategoryIds" + index));
		}

		return new CPQueryRule(contains, andOperator, name, values);
	}

	protected void moveSelectionDown(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		int productEntryOrder = ParamUtil.getInteger(
			actionRequest, "productEntryOrder");

		String[] manualEntries = preferences.getValues(
			"catalogEntryXml", new String[0]);

		if ((productEntryOrder >= (manualEntries.length - 1)) ||
			(productEntryOrder < 0)) {

			return;
		}

		String temp = manualEntries[productEntryOrder + 1];

		manualEntries[productEntryOrder + 1] = manualEntries[productEntryOrder];
		manualEntries[productEntryOrder] = temp;

		preferences.setValues("catalogEntryXml", manualEntries);
	}

	protected void moveSelectionUp(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		int productEntryOrder = ParamUtil.getInteger(
			actionRequest, "productEntryOrder");

		String[] manualEntries = preferences.getValues(
			"catalogEntryXml", new String[0]);

		if ((productEntryOrder >= manualEntries.length) ||
			(productEntryOrder <= 0)) {

			return;
		}

		String temp = manualEntries[productEntryOrder - 1];

		manualEntries[productEntryOrder - 1] = manualEntries[productEntryOrder];
		manualEntries[productEntryOrder] = temp;

		preferences.setValues("catalogEntryXml", manualEntries);
	}

	protected void removeSelection(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		int productEntryOrder = ParamUtil.getInteger(
			actionRequest, "productEntryOrder");

		String[] manualEntries = preferences.getValues(
			"catalogEntryXml", new String[0]);

		if (productEntryOrder >= manualEntries.length) {
			return;
		}

		String[] newEntries = new String[manualEntries.length - 1];

		int i = 0;
		int j = 0;

		for (; i < manualEntries.length; i++) {
			if (i != productEntryOrder) {
				newEntries[j++] = manualEntries[i];
			}
		}

		preferences.setValues("catalogEntryXml", newEntries);
	}

	protected void setDataSource(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		String dataSource = getParameter(actionRequest, "dataSource");

		preferences.setValue("dataSource", dataSource);
	}

	protected void setSelectionStyle(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		String selectionStyle = getParameter(actionRequest, "selectionStyle");

		preferences.setValue("selectionStyle", selectionStyle);

		if (selectionStyle.equals("manual")) {
			preferences.setValue("showQueryLogic", Boolean.FALSE.toString());
		}
	}

	protected void updateQueryLogic(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long userId = themeDisplay.getUserId();
		long groupId = themeDisplay.getSiteGroupId();

		int[] queryRulesIndexes = StringUtil.split(
			ParamUtil.getString(actionRequest, "queryLogicIndexes"), 0);

		int i = 0;

		List<CPQueryRule> queryRules = new ArrayList<>();

		for (int queryRulesIndex : queryRulesIndexes) {
			CPQueryRule queryRule = getQueryRule(
				actionRequest, queryRulesIndex);

			validateQueryRule(userId, groupId, queryRules, queryRule);

			queryRules.add(queryRule);

			setPreference(
				actionRequest, "queryContains" + i,
				String.valueOf(queryRule.isContains()));
			setPreference(
				actionRequest, "queryAndOperator" + i,
				String.valueOf(queryRule.isAndOperator()));
			setPreference(actionRequest, "queryName" + i, queryRule.getName());
			setPreference(
				actionRequest, "queryValues" + i, queryRule.getValues());

			i++;
		}

		// Clear previous preferences that are now blank

		String[] values = preferences.getValues(
			"queryValues" + i, new String[0]);

		while (values.length > 0) {
			setPreference(actionRequest, "queryContains" + i, StringPool.BLANK);
			setPreference(
				actionRequest, "queryAndOperator" + i, StringPool.BLANK);
			setPreference(actionRequest, "queryName" + i, StringPool.BLANK);
			setPreference(actionRequest, "queryValues" + i, new String[0]);

			i++;

			values = preferences.getValues("queryValues" + i, new String[0]);
		}
	}

	protected void validateQueryRule(
			long userId, long groupId, List<CPQueryRule> queryRules,
			CPQueryRule queryRule)
		throws Exception {

		String name = queryRule.getName();

		if (name.equals("assetTags")) {
			_assetTagLocalService.checkTags(
				userId, groupId, queryRule.getValues());
		}

		if (queryRules.contains(queryRule)) {
			throw new DuplicateQueryRuleException(
				queryRule.isContains(), queryRule.isAndOperator(),
				queryRule.getName());
		}
	}

	private String _getAssetEntryXml(
		String productEntryType, long cpDefinitionId) {

		String xml = null;

		try {
			Document document = SAXReaderUtil.createDocument(StringPool.UTF8);

			Element productEntryElement = document.addElement("product-entry");

			Element productEntryTypeElement = productEntryElement.addElement(
				"product-entry-type");

			productEntryTypeElement.addText(productEntryType);

			Element productEntryIdElement = productEntryElement.addElement(
				"product-id");

			productEntryIdElement.addText(String.valueOf(cpDefinitionId));

			xml = document.formattedString(StringPool.BLANK);
		}
		catch (IOException ioe) {
			if (_log.isWarnEnabled()) {
				_log.warn(ioe, ioe);
			}
		}

		return xml;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CPPublisherConfigurationAction.class);

	@Reference
	private AssetCategoryLocalService _assetCategoryLocalService;

	@Reference
	private AssetTagLocalService _assetTagLocalService;

	@Reference
	private CPContentListEntryRendererRegistry
		_cpContentListEntryRendererRegistry;

	@Reference
	private CPContentListRendererRegistry _cpContentListRendererRegistry;

	@Reference
	private CPDataSourceRegistry _cpDataSourceRegistry;

	@Reference
	private CPDefinitionService _cpDefinitionService;

	@Reference
	private CPInstanceHelper _cpInstanceHelper;

	@Reference
	private CPPublisherWebHelper _cpPublisherWebHelper;

	@Reference
	private CPTypeServicesTracker _cpTypeServicesTracker;

	@Reference
	private ItemSelector _itemSelector;

	@Reference
	private Portal _portal;

}