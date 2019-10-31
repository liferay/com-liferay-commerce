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

package com.liferay.commerce.product.definitions.web.internal.portlet.action;

import com.liferay.asset.kernel.exception.AssetCategoryException;
import com.liferay.asset.kernel.exception.AssetTagException;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetLink;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.asset.kernel.service.AssetLinkLocalService;
import com.liferay.commerce.account.model.CommerceAccountGroupRel;
import com.liferay.commerce.account.service.CommerceAccountGroupRelService;
import com.liferay.commerce.product.constants.CPPortletKeys;
import com.liferay.commerce.product.definitions.web.servlet.taglib.ui.CPDefinitionScreenNavigationConstants;
import com.liferay.commerce.product.exception.CPDefinitionExpirationDateException;
import com.liferay.commerce.product.exception.CPDefinitionMetaDescriptionException;
import com.liferay.commerce.product.exception.CPDefinitionMetaKeywordsException;
import com.liferay.commerce.product.exception.CPDefinitionMetaTitleException;
import com.liferay.commerce.product.exception.CPFriendlyURLEntryException;
import com.liferay.commerce.product.exception.NoSuchCPDefinitionException;
import com.liferay.commerce.product.exception.NoSuchCatalogException;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstanceConstants;
import com.liferay.commerce.product.model.CommerceChannelRel;
import com.liferay.commerce.product.service.CPDefinitionService;
import com.liferay.commerce.product.service.CommerceChannelRelService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.TransactionConfig;
import com.liferay.portal.kernel.transaction.TransactionInvokerUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PropertiesParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + CPPortletKeys.CP_DEFINITIONS,
		"mvc.command.name=editProductDefinition"
	},
	service = MVCActionCommand.class
)
public class EditCPDefinitionMVCActionCommand extends BaseMVCActionCommand {

	protected void deleteCPDefinitions(ActionRequest actionRequest)
		throws Exception {

		long[] deleteCPDefinitionIds = null;

		long cpDefinitionId = ParamUtil.getLong(
			actionRequest, "cpDefinitionId");

		if (cpDefinitionId > 0) {
			deleteCPDefinitionIds = new long[] {cpDefinitionId};
		}
		else {
			deleteCPDefinitionIds = StringUtil.split(
				ParamUtil.getString(actionRequest, "deleteCPDefinitionIds"),
				0L);
		}

		for (long deleteCPDefinitionId : deleteCPDefinitionIds) {
			_cpDefinitionService.deleteCPDefinition(deleteCPDefinitionId);
		}
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			if (cmd.equals(Constants.ADD) || cmd.equals(Constants.UPDATE)) {
				CPDefinition cpDefinition = updateCPDefinition(actionRequest);

				String redirect = getSaveAndContinueRedirect(
					actionRequest, cpDefinition,
					CPDefinitionScreenNavigationConstants.CATEGORY_KEY_DETAILS);

				sendRedirect(actionRequest, actionResponse, redirect);
			}
			else if (cmd.equals(Constants.DELETE)) {
				deleteCPDefinitions(actionRequest);
			}
			else if (cmd.equals("updateCategorization")) {
				CPDefinition cpDefinition = updateCategorization(actionRequest);

				String redirect = getSaveAndContinueRedirect(
					actionRequest, cpDefinition,
					CPDefinitionScreenNavigationConstants.
						CATEGORY_KEY_CATEGORIZATION);

				sendRedirect(actionRequest, actionResponse, redirect);
			}
			else if (cmd.equals("updateAccountGroups")) {
				Callable<Object> cpDefinitionAccountGroupsCallable =
					new CPDefinitionAccountGroupsCallable(actionRequest);

				TransactionInvokerUtil.invoke(
					_transactionConfig, cpDefinitionAccountGroupsCallable);
			}
			else if (cmd.equals("updateChannels")) {
				Callable<Object> cpDefinitionChannelsCallable =
					new CPDefinitionChannelsCallable(actionRequest);

				TransactionInvokerUtil.invoke(
					_transactionConfig, cpDefinitionChannelsCallable);
			}
			else if (cmd.equals("updateCPDisplayLayout")) {
				updateCPDisplayLayout(actionRequest);
			}
			else if (cmd.equals("updateShippingInfo")) {
				updateShippingInfo(actionRequest);
			}
			else if (cmd.equals("updateSubscriptionInfo")) {
				updateSubscriptionInfo(actionRequest);
			}
			else if (cmd.equals("updateTaxCategoryInfo")) {
				updateTaxCategoryInfo(actionRequest);
			}
		}
		catch (Throwable t) {
			if (t instanceof NoSuchCPDefinitionException ||
				t instanceof PrincipalException) {

				SessionErrors.add(actionRequest, t.getClass());

				actionResponse.setRenderParameter("mvcPath", "/error.jsp");
			}
			else if (t instanceof AssetCategoryException ||
					 t instanceof AssetTagException ||
					 t instanceof CPDefinitionExpirationDateException ||
					 t instanceof CPDefinitionMetaDescriptionException ||
					 t instanceof CPDefinitionMetaKeywordsException ||
					 t instanceof CPDefinitionMetaTitleException ||
					 t instanceof CPFriendlyURLEntryException ||
					 t instanceof NoSuchCatalogException) {

				SessionErrors.add(actionRequest, t.getClass(), t);

				String redirect = ParamUtil.getString(
					actionRequest, "redirect");

				sendRedirect(actionRequest, actionResponse, redirect);
			}
		}
	}

	protected long[] getAssetCategoryIds(CPDefinition cpDefinition) {
		AssetEntry assetEntry = _assetEntryLocalService.fetchEntry(
			cpDefinition.getModelClassName(), cpDefinition.getCPDefinitionId());

		if (assetEntry == null) {
			return new long[0];
		}

		return assetEntry.getCategoryIds();
	}

	protected double getAssetEntryPriority(CPDefinition cpDefinition) {
		AssetEntry assetEntry = _assetEntryLocalService.fetchEntry(
			cpDefinition.getModelClassName(), cpDefinition.getCPDefinitionId());

		if (assetEntry == null) {
			return 0;
		}

		return assetEntry.getPriority();
	}

	protected long[] getAssetLinkEntryIds(CPDefinition cpDefinition) {
		AssetEntry assetEntry = _assetEntryLocalService.fetchEntry(
			cpDefinition.getModelClassName(), cpDefinition.getCPDefinitionId());

		if (assetEntry == null) {
			return new long[0];
		}

		List<Long> assetLinkEntryIds = new ArrayList<>();

		List<AssetLink> assetLinks = _assetLinkLocalService.getLinks(
			assetEntry.getEntryId());

		for (AssetLink assetLink : assetLinks) {
			if (assetLink.getEntryId1() != assetEntry.getEntryId()) {
				assetLinkEntryIds.add(assetLink.getEntryId1());
			}
		}

		return ArrayUtil.toLongArray(assetLinkEntryIds);
	}

	protected String[] getAssetTagNames(CPDefinition cpDefinition) {
		AssetEntry assetEntry = _assetEntryLocalService.fetchEntry(
			cpDefinition.getModelClassName(), cpDefinition.getCPDefinitionId());

		if (assetEntry == null) {
			return new String[0];
		}

		return assetEntry.getTagNames();
	}

	protected String getSaveAndContinueRedirect(
			ActionRequest actionRequest, CPDefinition cpDefinition,
			String screenNavigationCategoryKey)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PortletURL portletURL = PortletProviderUtil.getPortletURL(
			actionRequest, themeDisplay.getScopeGroup(),
			CPDefinition.class.getName(), PortletProvider.Action.EDIT);

		portletURL.setParameter(
			"mvcRenderCommandName", "editProductDefinition");
		portletURL.setParameter(
			"cpDefinitionId", String.valueOf(cpDefinition.getCPDefinitionId()));
		portletURL.setParameter(
			"screenNavigationCategoryKey", screenNavigationCategoryKey);

		return portletURL.toString();
	}

	protected void reindexCPDefinition(long cpDefinitionId)
		throws PortalException {

		CPDefinition cpDefinition = _cpDefinitionService.getCPDefinition(
			cpDefinitionId);

		Indexer<CPDefinition> indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			CPDefinition.class);

		indexer.reindex(cpDefinition);
	}

	protected void updateAccountGroups(ActionRequest actionRequest)
		throws PortalException {

		long cpDefinitionId = ParamUtil.getLong(
			actionRequest, "cpDefinitionId");

		long[] commerceAccountGroupIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "commerceAccountGroupIds"), 0L);

		boolean accountGroupFilterEnabled = ParamUtil.getBoolean(
			actionRequest, "accountGroupFilterEnabled");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			CommerceAccountGroupRel.class.getName(), actionRequest);

		_commerceAccountGroupRelService.deleteCommerceAccountGroupRels(
			CPDefinition.class.getName(), cpDefinitionId);

		for (long commerceAccountGroupId : commerceAccountGroupIds) {
			if (commerceAccountGroupId == 0) {
				continue;
			}

			_commerceAccountGroupRelService.addCommerceAccountGroupRel(
				CPDefinition.class.getName(), cpDefinitionId,
				commerceAccountGroupId, serviceContext);
		}

		_cpDefinitionService.updateCPDefinitionAccountGroupFilter(
			cpDefinitionId, accountGroupFilterEnabled);

		reindexCPDefinition(cpDefinitionId);
	}

	protected CPDefinition updateCategorization(ActionRequest actionRequest)
		throws PortalException {

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			CPDefinition.class.getName(), actionRequest);

		long cpDefinitionId = ParamUtil.getLong(
			actionRequest, "cpDefinitionId");

		return _cpDefinitionService.updateCPDefinitionCategorization(
			cpDefinitionId, serviceContext);
	}

	protected void updateChannels(ActionRequest actionRequest)
		throws PortalException {

		long cpDefinitionId = ParamUtil.getLong(
			actionRequest, "cpDefinitionId");

		long[] commerceChannelIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "commerceChannelIds"), 0L);

		boolean channelFilterEnabled = ParamUtil.getBoolean(
			actionRequest, "channelFilterEnabled");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			CommerceChannelRel.class.getName(), actionRequest);

		_commerceChannelRelService.deleteCommerceChannelRels(
			CPDefinition.class.getName(), cpDefinitionId);

		for (long commerceChannelId : commerceChannelIds) {
			if (commerceChannelId == 0) {
				continue;
			}

			_commerceChannelRelService.addCommerceChannelRel(
				CPDefinition.class.getName(), cpDefinitionId, commerceChannelId,
				serviceContext);
		}

		_cpDefinitionService.updateCPDefinitionChannelFilter(
			cpDefinitionId, channelFilterEnabled);

		reindexCPDefinition(cpDefinitionId);
	}

	protected CPDefinition updateCPDefinition(ActionRequest actionRequest)
		throws Exception {

		long cpDefinitionId = ParamUtil.getLong(
			actionRequest, "cpDefinitionId");

		long commerceCatalogGroupId = ParamUtil.getLong(
			actionRequest, "commerceCatalogGroupId");
		Map<Locale, String> nameMap = LocalizationUtil.getLocalizationMap(
			actionRequest, "nameMapAsXML");
		Map<Locale, String> shortDescriptionMap =
			LocalizationUtil.getLocalizationMap(
				actionRequest, "shortDescriptionMapAsXML");
		Map<Locale, String> descriptionMap =
			LocalizationUtil.getLocalizationMap(
				actionRequest, "descriptionMapAsXML");
		String productTypeName = ParamUtil.getString(
			actionRequest, "productTypeName");
		Map<Locale, String> urlTitleMap = LocalizationUtil.getLocalizationMap(
			actionRequest, "urlTitleMapAsXML");
		Map<Locale, String> metaTitleMap = LocalizationUtil.getLocalizationMap(
			actionRequest, "metaTitleMapAsXML");
		Map<Locale, String> metaDescriptionMap =
			LocalizationUtil.getLocalizationMap(
				actionRequest, "metaDescriptionMapAsXML");
		Map<Locale, String> metaKeywordsMap =
			LocalizationUtil.getLocalizationMap(
				actionRequest, "metaKeywordsMapAsXML");
		boolean published = ParamUtil.getBoolean(actionRequest, "published");

		int displayDateMonth = ParamUtil.getInteger(
			actionRequest, "displayDateMonth");
		int displayDateDay = ParamUtil.getInteger(
			actionRequest, "displayDateDay");
		int displayDateYear = ParamUtil.getInteger(
			actionRequest, "displayDateYear");
		int displayDateHour = ParamUtil.getInteger(
			actionRequest, "displayDateHour");
		int displayDateMinute = ParamUtil.getInteger(
			actionRequest, "displayDateMinute");
		int displayDateAmPm = ParamUtil.getInteger(
			actionRequest, "displayDateAmPm");

		if (displayDateAmPm == Calendar.PM) {
			displayDateHour += 12;
		}

		int expirationDateMonth = ParamUtil.getInteger(
			actionRequest, "expirationDateMonth");
		int expirationDateDay = ParamUtil.getInteger(
			actionRequest, "expirationDateDay");
		int expirationDateYear = ParamUtil.getInteger(
			actionRequest, "expirationDateYear");
		int expirationDateHour = ParamUtil.getInteger(
			actionRequest, "expirationDateHour");
		int expirationDateMinute = ParamUtil.getInteger(
			actionRequest, "expirationDateMinute");
		int expirationDateAmPm = ParamUtil.getInteger(
			actionRequest, "expirationDateAmPm");

		if (expirationDateAmPm == Calendar.PM) {
			expirationDateHour += 12;
		}

		boolean neverExpire = ParamUtil.getBoolean(
			actionRequest, "neverExpire");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			CPDefinition.class.getName(), actionRequest);

		CPDefinition cpDefinition = null;

		if (cpDefinitionId <= 0) {

			// Add commerce product definition

			cpDefinition = _cpDefinitionService.addCPDefinition(
				commerceCatalogGroupId, serviceContext.getUserId(), nameMap,
				shortDescriptionMap, descriptionMap, urlTitleMap, metaTitleMap,
				metaDescriptionMap, metaKeywordsMap, productTypeName, true,
				true, false, false, 0D, 0D, 0D, 0D, 0D, 0L, false, false, null,
				published, displayDateMonth, displayDateDay, displayDateYear,
				displayDateHour, displayDateMinute, expirationDateMonth,
				expirationDateDay, expirationDateYear, expirationDateHour,
				expirationDateMinute, neverExpire,
				CPInstanceConstants.DEFAULT_SKU, false, 0, null, null, 0L, null,
				serviceContext);
		}
		else {

			// Update commerce product definition

			CPDefinition oldCPDefinition = _cpDefinitionService.getCPDefinition(
				cpDefinitionId);

			serviceContext.setAssetCategoryIds(
				getAssetCategoryIds(oldCPDefinition));
			serviceContext.setAssetLinkEntryIds(
				getAssetLinkEntryIds(oldCPDefinition));
			serviceContext.setAssetPriority(
				getAssetEntryPriority(oldCPDefinition));
			serviceContext.setAssetTagNames(getAssetTagNames(oldCPDefinition));

			cpDefinition = _cpDefinitionService.updateCPDefinition(
				cpDefinitionId, nameMap, shortDescriptionMap, descriptionMap,
				urlTitleMap, metaTitleMap, metaDescriptionMap, metaKeywordsMap,
				oldCPDefinition.isIgnoreSKUCombinations(), null, published,
				displayDateMonth, displayDateDay, displayDateYear,
				displayDateHour, displayDateMinute, expirationDateMonth,
				expirationDateDay, expirationDateYear, expirationDateHour,
				expirationDateMinute, neverExpire, serviceContext);
		}

		return cpDefinition;
	}

	protected void updateCPDisplayLayout(ActionRequest actionRequest)
		throws PortalException {

		long cpDefinitionId = ParamUtil.getLong(
			actionRequest, "cpDefinitionId");

		String layoutUuid = ParamUtil.getString(actionRequest, "layoutUuid");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			CPDefinition.class.getName(), actionRequest);

		_cpDefinitionService.updateCPDisplayLayout(
			cpDefinitionId, layoutUuid, serviceContext);
	}

	protected void updateShippingInfo(ActionRequest actionRequest)
		throws PortalException {

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			CPDefinition.class.getName(), actionRequest);

		long cpDefinitionId = ParamUtil.getLong(
			actionRequest, "cpDefinitionId");

		boolean shippable = ParamUtil.getBoolean(actionRequest, "shippable");
		boolean freeShipping = ParamUtil.getBoolean(
			actionRequest, "freeShipping");
		boolean shipSeparately = ParamUtil.getBoolean(
			actionRequest, "shipSeparately");
		double shippingExtraPrice = ParamUtil.getDouble(
			actionRequest, "shippingExtraPrice");
		double width = ParamUtil.getDouble(actionRequest, "width");
		double height = ParamUtil.getDouble(actionRequest, "height");
		double depth = ParamUtil.getDouble(actionRequest, "depth");
		double weight = ParamUtil.getDouble(actionRequest, "weight");

		_cpDefinitionService.updateShippingInfo(
			cpDefinitionId, shippable, freeShipping, shipSeparately,
			shippingExtraPrice, width, height, depth, weight, serviceContext);
	}

	protected void updateSubscriptionInfo(ActionRequest actionRequest)
		throws PortalException {

		long cpDefinitionId = ParamUtil.getLong(
			actionRequest, "cpDefinitionId");
		boolean subscriptionEnabled = ParamUtil.getBoolean(
			actionRequest, "subscriptionEnabled");
		int subscriptionLength = ParamUtil.getInteger(
			actionRequest, "subscriptionLength");
		String subscriptionType = ParamUtil.getString(
			actionRequest, "subscriptionType");
		UnicodeProperties subscriptionTypeSettingsProperties =
			PropertiesParamUtil.getProperties(
				actionRequest, "subscriptionTypeSettings--");
		long maxSubscriptionCycles = ParamUtil.getLong(
			actionRequest, "maxSubscriptionCycles");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			CPDefinition.class.getName(), actionRequest);

		_cpDefinitionService.updateSubscriptionInfo(
			cpDefinitionId, subscriptionEnabled, subscriptionLength,
			subscriptionType, subscriptionTypeSettingsProperties,
			maxSubscriptionCycles, serviceContext);
	}

	protected void updateTaxCategoryInfo(ActionRequest actionRequest)
		throws PortalException {

		long cpDefinitionId = ParamUtil.getLong(
			actionRequest, "cpDefinitionId");

		long cpTaxCategoryId = ParamUtil.getLong(
			actionRequest, "cpTaxCategoryId");
		boolean taxExempt = ParamUtil.getBoolean(actionRequest, "taxExempt");
		boolean telcoOrElectronics = ParamUtil.getBoolean(
			actionRequest, "telcoOrElectronics");

		_cpDefinitionService.updateTaxCategoryInfo(
			cpDefinitionId, cpTaxCategoryId, taxExempt, telcoOrElectronics);
	}

	private static final TransactionConfig _transactionConfig =
		TransactionConfig.Factory.create(
			Propagation.REQUIRED, new Class<?>[] {Exception.class});

	@Reference
	private AssetEntryLocalService _assetEntryLocalService;

	@Reference
	private AssetLinkLocalService _assetLinkLocalService;

	@Reference
	private CommerceAccountGroupRelService _commerceAccountGroupRelService;

	@Reference
	private CommerceChannelRelService _commerceChannelRelService;

	@Reference
	private CPDefinitionService _cpDefinitionService;

	@Reference
	private Portal _portal;

	private class CPDefinitionAccountGroupsCallable
		implements Callable<Object> {

		@Override
		public Object call() throws Exception {
			updateAccountGroups(_actionRequest);

			return null;
		}

		private CPDefinitionAccountGroupsCallable(ActionRequest actionRequest) {
			_actionRequest = actionRequest;
		}

		private final ActionRequest _actionRequest;

	}

	private class CPDefinitionChannelsCallable implements Callable<Object> {

		@Override
		public Object call() throws Exception {
			updateChannels(_actionRequest);

			return null;
		}

		private CPDefinitionChannelsCallable(ActionRequest actionRequest) {
			_actionRequest = actionRequest;
		}

		private final ActionRequest _actionRequest;

	}

}