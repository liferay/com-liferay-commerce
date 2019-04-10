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

package com.liferay.commerce.media.internal;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.util.CommerceAccountHelper;
import com.liferay.commerce.media.CommerceMediaResolver;
import com.liferay.commerce.media.constants.CommerceMediaConstants;
import com.liferay.commerce.media.internal.configuration.CommerceMediaDefaultImageConfiguration;
import com.liferay.commerce.product.model.CPAttachmentFileEntry;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPRule;
import com.liferay.commerce.product.model.CProduct;
import com.liferay.commerce.product.service.CPAttachmentFileEntryLocalService;
import com.liferay.commerce.product.service.CPAttachmentFileEntryService;
import com.liferay.commerce.product.service.CPDefinitionLocalService;
import com.liferay.commerce.product.service.CPRuleLocalService;
import com.liferay.commerce.product.service.CProductLocalService;
import com.liferay.commerce.product.util.CPRulesThreadLocal;
import com.liferay.commerce.user.segment.util.CommerceUserSegmentHelper;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationProviderUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.servlet.PortalSessionThreadLocal;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.settings.GroupServiceSettingsLocator;
import com.liferay.portal.kernel.util.File;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Html;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.URLCodec;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portlet.asset.service.permission.AssetCategoryPermission;

import java.io.IOException;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alec Sloan
 */
@Component(service = CommerceMediaResolver.class)
public class DefaultCommerceMediaResolver implements CommerceMediaResolver {

	@Override
	public String getDefaultUrl(long groupId) {
		StringBundler sb = new StringBundler(5);

		sb.append(_portal.getPathModule());
		sb.append(StringPool.SLASH);
		sb.append(CommerceMediaConstants.SERVLET_PATH);
		sb.append("/default/?groupId=");
		sb.append(groupId);

		return _html.escape(sb.toString());
	}

	@Override
	public String getDownloadUrl(long cpAttachmentFileEntryId)
		throws PortalException {

		return getUrl(cpAttachmentFileEntryId, true, false);
	}

	@Override
	public byte[] getMediaBytes(HttpServletRequest httpServletRequest)
		throws IOException, PortalException {

		return getBytes(getFileEntry(httpServletRequest));
	}

	@Override
	public String getThumbnailUrl(long cpAttachmentFileEntryId)
		throws PortalException {

		return getUrl(cpAttachmentFileEntryId, false, true);
	}

	@Override
	public String getUrl(long cpAttachmentFileEntryId) throws PortalException {
		return getUrl(cpAttachmentFileEntryId, false, false);
	}

	@Override
	public String getUrl(
			long cpAttachmentFileEntryId, boolean download, boolean thumbnail)
		throws PortalException {

		return getUrl(cpAttachmentFileEntryId, download, thumbnail, true);
	}

	@Override
	public String getUrl(
			long cpAttachmentFileEntryId, boolean download, boolean thumbnail,
			boolean secure)
		throws PortalException {

		StringBundler sb = new StringBundler(10);

		sb.append(_portal.getPathModule());
		sb.append(StringPool.SLASH);
		sb.append(CommerceMediaConstants.SERVLET_PATH);

		CPAttachmentFileEntry cpAttachmentFileEntry = null;

		if (secure) {
			cpAttachmentFileEntry =
				_cpAttachmentFileEntryService.fetchCPAttachmentFileEntry(
					cpAttachmentFileEntryId);
		}
		else {
			cpAttachmentFileEntry =
				_cpAttachmentFileEntryLocalService.fetchCPAttachmentFileEntry(
					cpAttachmentFileEntryId);
		}

		if (cpAttachmentFileEntry == null) {
			HttpSession httpSession = PortalSessionThreadLocal.getHttpSession();

			if (httpSession == null) {
				return _html.escape(sb.toString());
			}

			long groupId = GetterUtil.getLong(
				httpSession.getAttribute(WebKeys.VISITED_GROUP_ID_RECENT));

			return getDefaultUrl(groupId);
		}

		Locale siteDefaultLocale = _portal.getSiteDefaultLocale(
			cpAttachmentFileEntry.getGroupId());

		String className = cpAttachmentFileEntry.getClassName();

		sb.append(
			setUrl(
				className, cpAttachmentFileEntry.getClassPK(),
				siteDefaultLocale));

		sb.append(StringPool.SLASH);
		sb.append(cpAttachmentFileEntry.getFileEntryId());
		sb.append(StringPool.SLASH);

		String title = cpAttachmentFileEntry.getTitle(siteDefaultLocale);

		sb.append(
			URLCodec.encodeURL(
				title.replace(StringPool.SLASH, StringPool.BLANK), true));

		sb.append("?download=");
		sb.append(download);

		return _html.escape(sb.toString());
	}

	@Override
	public void sendMediaBytes(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		sendMediaBytes(httpServletRequest, httpServletResponse, null);
	}

	@Override
	public void sendMediaBytes(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, String contentDisposition)
		throws IOException {

		String path = _http.fixPath(httpServletRequest.getPathInfo());

		String[] pathArray = StringUtil.split(path, CharPool.SLASH);

		if (pathArray.length < 2) {
			long groupId = ParamUtil.getLong(httpServletRequest, "groupId");

			if (groupId == 0) {
				httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);

				return;
			}

			sendDefaultMediaBytes(
				httpServletRequest, httpServletResponse, contentDisposition,
				groupId);

			return;
		}

		long groupId = getGroupId(
			pathArray[0], GetterUtil.getLong(pathArray[1]), httpServletRequest);

		if (groupId == 0) {
			httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);

			return;
		}

		try {
			FileEntry fileEntry = getFileEntry(httpServletRequest);

			if (fileEntry == null) {
				sendDefaultMediaBytes(
					httpServletRequest, httpServletResponse, contentDisposition,
					groupId);

				return;
			}

			ServletResponseUtil.sendFile(
				httpServletRequest, httpServletResponse,
				fileEntry.getFileName(), getBytes(fileEntry),
				fileEntry.getMimeType(), contentDisposition);
		}
		catch (PortalException pe) {
			_log.error(pe, pe);

			httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	protected byte[] getBytes(FileEntry fileEntry)
		throws IOException, PortalException {

		return _file.getBytes(fileEntry.getContentStream());
	}

	protected FileEntry getFileEntry(HttpServletRequest httpServletRequest)
		throws PortalException {

		String path = _http.fixPath(httpServletRequest.getPathInfo());

		String[] pathArray = StringUtil.split(path, CharPool.SLASH);

		if (pathArray.length < 2) {
			return null;
		}

		long fileEntryId = GetterUtil.getLong(pathArray[pathArray.length - 2]);

		return getFileEntry(fileEntryId);
	}

	protected FileEntry getFileEntry(long fileEntryId) {
		try {
			return _dlAppLocalService.getFileEntry(fileEntryId);
		}
		catch (PortalException pe) {
			if (_log.isDebugEnabled()) {
				_log.debug(pe, pe);
			}

			return null;
		}
	}

	protected long getGroupId(
		String mediaType, long primaryKey,
		HttpServletRequest httpServletRequest) {

		if (mediaType.equals("asset-categories")) {
			AssetCategory assetCategory =
				_assetCategoryLocalService.fetchCategory(primaryKey);

			try {
				if (AssetCategoryPermission.contains(
						PermissionThreadLocal.getPermissionChecker(),
						assetCategory, ActionKeys.VIEW)) {

					return assetCategory.getGroupId();
				}
			}
			catch (PortalException pe) {
				_log.error(pe, pe);
			}
		}
		else if (mediaType.equals("products")) {
			CProduct cProduct = _cProductLocalService.fetchCProduct(primaryKey);

			try {
				setCPRules(httpServletRequest, cProduct.getGroupId());

				if (_cpDefinitionModelResourcePermission.contains(
						PermissionThreadLocal.getPermissionChecker(),
						cProduct.getPublishedCPDefinitionId(),
						ActionKeys.VIEW)) {

					return cProduct.getGroupId();
				}
			}
			catch (PortalException pe) {
				_log.error(pe, pe);
			}
		}

		return 0;
	}

	protected void sendDefaultMediaBytes(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, String contentDisposition,
			long groupId)
		throws IOException {

		try {
			CommerceMediaDefaultImageConfiguration
				commerceMediaDefaultImageConfiguration =
					ConfigurationProviderUtil.getConfiguration(
						CommerceMediaDefaultImageConfiguration.class,
						new GroupServiceSettingsLocator(
							groupId,
							CommerceMediaDefaultImageConfiguration.class.
								getName()));

			FileEntry fileEntry = getFileEntry(
				commerceMediaDefaultImageConfiguration.defaultFileEntryId());

			if (fileEntry == null) {
				httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);

				return;
			}

			ServletResponseUtil.sendFile(
				httpServletRequest, httpServletResponse,
				fileEntry.getFileName(), getBytes(fileEntry),
				fileEntry.getMimeType(), contentDisposition);
		}
		catch (PortalException pe) {
			_log.error(pe, pe);

			httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	protected void setCPRules(
			HttpServletRequest httpServletRequest, long groupId)
		throws PortalException {

		CommerceAccount commerceAccount =
			_commerceAccountHelper.getCurrentCommerceAccount(
				groupId, httpServletRequest);

		long commerceAccountId = 0;

		if (commerceAccount != null) {
			commerceAccountId = commerceAccount.getCommerceAccountId();
		}

		long userId = _portal.getUserId(httpServletRequest);

		if (userId == 0) {
			userId = _userLocalService.getDefaultUserId(
				_portal.getCompanyId(httpServletRequest));
		}

		long[] commerceUserSegmentEntryIds =
			_commerceUserSegmentHelper.getCommerceUserSegmentIds(
				groupId, commerceAccountId, userId);

		List<CPRule> cpRules = _cpRuleLocalService.getCPRules(
			groupId, commerceUserSegmentEntryIds);

		CPRulesThreadLocal.setCPRules(cpRules);
	}

	protected String setUrl(String className, long classPK, Locale locale)
		throws PortalException {

		StringBundler sb = new StringBundler(4);

		if (className.equals(CPDefinition.class.getName())) {
			sb.append("/products/");

			CPDefinition cpDefinition =
				_cpDefinitionLocalService.getCPDefinition(classPK);

			sb.append(cpDefinition.getCProductId());

			sb.append(StringPool.SLASH);

			Map<Locale, String> titleMap = cpDefinition.getUrlTitleMap();

			sb.append(titleMap.get(locale));
		}
		else if (className.equals(AssetCategory.class.getName())) {
			sb.append("/asset-categories/");

			AssetCategory assetCategory =
				_assetCategoryLocalService.getAssetCategory(classPK);

			sb.append(assetCategory.getCategoryId());

			sb.append(StringPool.SLASH);

			Map<Locale, String> titleMap = assetCategory.getTitleMap();

			sb.append(titleMap.get(locale));
		}
		else {
			throw new UnsupportedOperationException();
		}

		return sb.toString();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DefaultCommerceMediaResolver.class);

	@Reference
	private AssetCategoryLocalService _assetCategoryLocalService;

	@Reference
	private CommerceAccountHelper _commerceAccountHelper;

	@Reference
	private CommerceUserSegmentHelper _commerceUserSegmentHelper;

	@Reference
	private CPAttachmentFileEntryLocalService
		_cpAttachmentFileEntryLocalService;

	@Reference
	private CPAttachmentFileEntryService _cpAttachmentFileEntryService;

	@Reference
	private CPDefinitionLocalService _cpDefinitionLocalService;

	@Reference(
		target = "(model.class.name=com.liferay.commerce.product.model.CPDefinition)"
	)
	private ModelResourcePermission<CPDefinition>
		_cpDefinitionModelResourcePermission;

	@Reference
	private CProductLocalService _cProductLocalService;

	@Reference
	private CPRuleLocalService _cpRuleLocalService;

	@Reference
	private DLAppLocalService _dlAppLocalService;

	@Reference
	private File _file;

	@Reference
	private Html _html;

	@Reference
	private Http _http;

	@Reference
	private Portal _portal;

	@Reference
	private UserLocalService _userLocalService;

}