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

package com.liferay.commerce.product.internal.util;

import com.liferay.commerce.product.catalog.CPCatalogEntry;
import com.liferay.commerce.product.catalog.CPQuery;
import com.liferay.commerce.product.constants.CPConstants;
import com.liferay.commerce.product.constants.CPPortletKeys;
import com.liferay.commerce.product.data.source.CPDataSourceResult;
import com.liferay.commerce.product.internal.catalog.DatabaseCPCatalogEntryImpl;
import com.liferay.commerce.product.internal.catalog.IndexCPCatalogEntryImpl;
import com.liferay.commerce.product.internal.search.CPDefinitionSearcher;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPFriendlyURLEntry;
import com.liferay.commerce.product.model.CProduct;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.permission.CommerceProductViewPermission;
import com.liferay.commerce.product.search.CPDefinitionIndexer;
import com.liferay.commerce.product.service.CPDefinitionLocalService;
import com.liferay.commerce.product.service.CPFriendlyURLEntryLocalService;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.commerce.product.service.CProductLocalService;
import com.liferay.commerce.product.service.CommerceChannelLocalService;
import com.liferay.commerce.product.util.CPDefinitionHelper;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 * @author Andrea Di Giorgi
 */
@Component(immediate = true, service = CPDefinitionHelper.class)
public class CPDefinitionHelperImpl implements CPDefinitionHelper {

	@Override
	public CPCatalogEntry getCPCatalogEntry(Document document, Locale locale) {
		return new IndexCPCatalogEntryImpl(
			document, _cpDefinitionLocalService, _cpInstanceLocalService,
			locale);
	}

	@Override
	public CPCatalogEntry getCPCatalogEntry(
			long commerceAccountId, long groupId, long cpDefinitionId,
			Locale locale)
		throws PortalException {

		_commerceProductViewPermission.check(
			PermissionThreadLocal.getPermissionChecker(), commerceAccountId,
			groupId, cpDefinitionId);

		CPDefinition cpDefinition = _cpDefinitionLocalService.getCPDefinition(
			cpDefinitionId);

		if (!cpDefinition.isApproved() || !cpDefinition.isPublished()) {
			return null;
		}

		return new DatabaseCPCatalogEntryImpl(
			cpDefinition, _cpInstanceLocalService, locale);
	}

	@Override
	public String getFriendlyURL(long cpDefinitionId, ThemeDisplay themeDisplay)
		throws PortalException {

		CPDefinition cpDefinition = _cpDefinitionLocalService.getCPDefinition(
			cpDefinitionId);

		return _getFriendlyURL(cpDefinition.getCProductId(), themeDisplay);
	}

	@Override
	public CPDataSourceResult search(
			long groupId, SearchContext searchContext, CPQuery cpQuery,
			int start, int end)
		throws PortalException {

		List<CPCatalogEntry> cpCatalogEntries = new ArrayList<>();

		CPDefinitionSearcher cpDefinitionSearcher = _getCPDefinitionSearcher(
			groupId, searchContext, cpQuery, start, end);

		Hits hits = cpDefinitionSearcher.search(searchContext);

		Document[] documents = hits.getDocs();

		for (Document document : documents) {
			cpCatalogEntries.add(
				getCPCatalogEntry(document, searchContext.getLocale()));
		}

		return new CPDataSourceResult(cpCatalogEntries, hits.getLength());
	}

	private long _checkChannelGroupId(long groupId) {
		Group group = _groupLocalService.fetchGroup(groupId);

		String className = group.getClassName();

		if (className.equals(CommerceChannel.class.getName())) {
			return groupId;
		}

		CommerceChannel commerceChannel =
			_commerceChannelLocalService.fetchCommerceChannelBySiteGroupId(
				groupId);

		if (commerceChannel != null) {
			return commerceChannel.getGroupId();
		}

		return groupId;
	}

	private CPDefinitionSearcher _getCPDefinitionSearcher(
		long groupId, SearchContext searchContext, CPQuery cpQuery, int start,
		int end) {

		CPDefinitionSearcher cpDefinitionSearcher = new CPDefinitionSearcher(
			cpQuery);

		searchContext.setAttribute(
			CPDefinitionIndexer.FIELD_PUBLISHED, Boolean.TRUE);
		searchContext.setAttribute(
			"commerceChannelGroupId", _checkChannelGroupId(groupId));
		searchContext.setAttribute("secure", Boolean.TRUE);

		searchContext.setEnd(end);

		QueryConfig queryConfig = searchContext.getQueryConfig();

		queryConfig.setScoreEnabled(false);

		searchContext.setSorts(_getSorts(cpQuery, searchContext.getLocale()));
		searchContext.setStart(start);

		return cpDefinitionSearcher;
	}

	private String _getFriendlyURL(long cProductId, ThemeDisplay themeDisplay)
		throws PortalException {

		long classNameId = _portal.getClassNameId(CProduct.class);

		CPFriendlyURLEntry cpFriendlyURLEntry =
			_cpFriendlyURLEntryLocalService.fetchCPFriendlyURLEntry(
				GroupConstants.DEFAULT_LIVE_GROUP_ID, classNameId, cProductId,
				themeDisplay.getLanguageId(), true);

		if (cpFriendlyURLEntry == null) {
			cpFriendlyURLEntry =
				_cpFriendlyURLEntryLocalService.fetchCPFriendlyURLEntry(
					GroupConstants.DEFAULT_LIVE_GROUP_ID, classNameId,
					cProductId,
					LocaleUtil.toLanguageId(
						themeDisplay.getSiteDefaultLocale()),
					true);
		}

		if (cpFriendlyURLEntry == null) {
			if (_log.isInfoEnabled()) {
				_log.info("No friendly URL found for " + cProductId);
			}

			return StringPool.BLANK;
		}

		Layout layout = null;

		Group group = themeDisplay.getScopeGroup();

		CProduct cProduct = _cProductLocalService.getCProduct(cProductId);

		String layoutUuid = _cpDefinitionLocalService.getLayoutUuid(
			cProduct.getPublishedCPDefinitionId());

		if (Validator.isNotNull(layoutUuid)) {
			try {
				layout = _layoutLocalService.getLayoutByUuidAndGroupId(
					layoutUuid, group.getGroupId(), true);
			}
			catch (PortalException pe) {
				if (_log.isDebugEnabled()) {
					_log.debug(pe, pe);
				}
			}

			if (layout == null) {
				try {
					layout = _layoutLocalService.getLayoutByUuidAndGroupId(
						layoutUuid, group.getGroupId(), false);
				}
				catch (PortalException pe) {
					if (_log.isDebugEnabled()) {
						_log.debug(pe, pe);
					}
				}
			}
		}

		if (layout == null) {
			long plid = _portal.getPlidFromPortletId(
				group.getGroupId(), CPPortletKeys.CP_CONTENT_WEB);

			if (plid > 0) {
				layout = _layoutLocalService.getLayout(plid);
			}
		}

		if (layout == null) {
			layout = themeDisplay.getLayout();
		}

		String currentSiteURL = _portal.getGroupFriendlyURL(
			layout.getLayoutSet(), themeDisplay);

		String productFriendlyURL =
			currentSiteURL + CPConstants.SEPARATOR_PRODUCT_URL +
				cpFriendlyURLEntry.getUrlTitle();

		return _portal.addPreservedParameters(themeDisplay, productFriendlyURL);
	}

	private String _getOrderByCol(String sortField, Locale locale) {
		if (sortField.equals("modifiedDate")) {
			sortField = Field.MODIFIED_DATE;
		}
		else if (sortField.equals("name")) {
			sortField = Field.getSortableFieldName(
				"localized_name_".concat(LocaleUtil.toLanguageId(locale)));
		}

		return sortField;
	}

	private Sort _getSort(String orderByType, String sortField, Locale locale) {
		int sortType = _getSortType(sortField);

		return SortFactoryUtil.getSort(
			CPDefinition.class, sortType, _getOrderByCol(sortField, locale),
			orderByType);
	}

	private Sort[] _getSorts(CPQuery cpQuery, Locale locale) {
		Sort sort1 = _getSort(
			cpQuery.getOrderByType1(), cpQuery.getOrderByCol1(), locale);
		Sort sort2 = _getSort(
			cpQuery.getOrderByType2(), cpQuery.getOrderByCol2(), locale);

		return new Sort[] {sort1, sort2};
	}

	private int _getSortType(String fieldType) {
		int sortType = Sort.STRING_TYPE;

		if (fieldType.equals(Field.CREATE_DATE) ||
			fieldType.equals(Field.EXPIRATION_DATE) ||
			fieldType.equals(Field.PUBLISH_DATE) ||
			fieldType.equals("modifiedDate")) {

			sortType = Sort.LONG_TYPE;
		}
		else if (fieldType.equals(Field.PRIORITY)) {
			sortType = Sort.DOUBLE_TYPE;
		}

		return sortType;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CPDefinitionHelperImpl.class);

	@Reference
	private CommerceChannelLocalService _commerceChannelLocalService;

	@Reference
	private CommerceProductViewPermission _commerceProductViewPermission;

	@Reference
	private CPDefinitionLocalService _cpDefinitionLocalService;

	@Reference
	private CPFriendlyURLEntryLocalService _cpFriendlyURLEntryLocalService;

	@Reference
	private CPInstanceLocalService _cpInstanceLocalService;

	@Reference
	private CProductLocalService _cProductLocalService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private Portal _portal;

}