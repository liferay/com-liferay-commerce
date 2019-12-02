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

package com.liferay.commerce.organization.web.internal.frontend;

import com.liferay.commerce.frontend.ClayTable;
import com.liferay.commerce.frontend.ClayTableAction;
import com.liferay.commerce.frontend.ClayTableActionProvider;
import com.liferay.commerce.frontend.ClayTableSchema;
import com.liferay.commerce.frontend.ClayTableSchemaBuilder;
import com.liferay.commerce.frontend.ClayTableSchemaBuilderFactory;
import com.liferay.commerce.frontend.CommerceDataSetDataProvider;
import com.liferay.commerce.frontend.Filter;
import com.liferay.commerce.frontend.Pagination;
import com.liferay.commerce.organization.web.internal.model.Organization;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.portlet.PortletQName;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.OrganizationService;
import com.liferay.portal.kernel.service.permission.OrganizationPermissionUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = {
		"commerce.data.provider.key=" + CommerceOrganizationClayTable.NAME,
		"commerce.table.name=" + CommerceOrganizationClayTable.NAME
	},
	service = {
		ClayTable.class, ClayTableActionProvider.class,
		CommerceDataSetDataProvider.class
	}
)
public class CommerceOrganizationClayTable
	implements ClayTable, ClayTableActionProvider,
			   CommerceDataSetDataProvider<Organization> {

	public static final String NAME = "commerceOrganizations";

	@Override
	public List<ClayTableAction> clayTableActions(
			HttpServletRequest httpServletRequest, long groupId, Object model)
		throws PortalException {

		List<ClayTableAction> clayTableActions = new ArrayList<>();

		Organization organization = (Organization)model;

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		if (OrganizationPermissionUtil.contains(
				themeDisplay.getPermissionChecker(),
				organization.getOrganizationId(), ActionKeys.VIEW)) {

			String viewURL = _getViewOrganizationDetailURL(
				organization.getOrganizationId(), httpServletRequest);

			ClayTableAction viewClayTableAction = new ClayTableAction(
				StringPool.BLANK, viewURL, StringPool.BLANK,
				LanguageUtil.get(httpServletRequest, "view-detail"), null,
				false, false);

			clayTableActions.add(viewClayTableAction);

			String viewSubOrganizationsURL =
				_getOrganizationViewSuborganizationsURL(
					organization.getOrganizationId(), httpServletRequest);

			ClayTableAction viewSubOrganizationsClayTableAction =
				new ClayTableAction(
					StringPool.BLANK, viewSubOrganizationsURL, StringPool.BLANK,
					LanguageUtil.get(
						httpServletRequest, "view-suborganizations"),
					null, false, false);

			clayTableActions.add(viewSubOrganizationsClayTableAction);
		}

		if (OrganizationPermissionUtil.contains(
				themeDisplay.getPermissionChecker(),
				organization.getOrganizationId(), ActionKeys.DELETE)) {

			StringBundler sb = new StringBundler(7);

			sb.append("javascript:deleteCommerceOrganization");
			sb.append(StringPool.OPEN_PARENTHESIS);
			sb.append(StringPool.APOSTROPHE);
			sb.append(organization.getOrganizationId());
			sb.append(StringPool.APOSTROPHE);
			sb.append(StringPool.CLOSE_PARENTHESIS);
			sb.append(StringPool.SEMICOLON);

			ClayTableAction deleteClayTableAction = new ClayTableAction(
				StringPool.BLANK, sb.toString(), StringPool.BLANK,
				LanguageUtil.get(httpServletRequest, "delete"), null, false,
				false);

			clayTableActions.add(deleteClayTableAction);
		}

		return clayTableActions;
	}

	@Override
	public int countItems(HttpServletRequest httpServletRequest, Filter filter)
		throws PortalException {

		OrganizationFilterImpl organizationFilterImpl =
			(OrganizationFilterImpl)filter;

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		return _organizationService.getOrganizationsCount(
			themeDisplay.getCompanyId(),
			organizationFilterImpl.getOrganizationId());
	}

	@Override
	public ClayTableSchema getClayTableSchema() {
		ClayTableSchemaBuilder clayTableSchemaBuilder =
			_clayTableSchemaBuilderFactory.clayTableSchemaBuilder();

		clayTableSchemaBuilder.addField("name", "name");
		clayTableSchemaBuilder.addField("path", "path");

		return clayTableSchemaBuilder.build();
	}

	@Override
	public String getId() {
		return NAME;
	}

	@Override
	public List<Organization> getItems(
			HttpServletRequest httpServletRequest, Filter filter,
			Pagination pagination, Sort sort)
		throws PortalException {

		OrganizationFilterImpl organizationFilterImpl =
			(OrganizationFilterImpl)filter;

		List<Organization> organizations = new ArrayList<>();

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		List<com.liferay.portal.kernel.model.Organization> organizationList =
			_organizationService.getOrganizations(
				themeDisplay.getCompanyId(),
				organizationFilterImpl.getOrganizationId(),
				pagination.getStartPosition(), pagination.getEndPosition());

		for (com.liferay.portal.kernel.model.Organization organization :
				organizationList) {

			organizations.add(
				new Organization(
					organization.getOrganizationId(), organization.getName(),
					getPath(organization.getTreePath())));
		}

		return organizations;
	}

	@Override
	public boolean isShowActionsMenu() {
		return true;
	}

	protected String getPath(String treePath) throws PortalException {
		String[] organizationIds = StringUtil.split(
			treePath, CharPool.FORWARD_SLASH);

		StringBundler sb = new StringBundler(organizationIds.length * 2);

		for (int i = 1; i < organizationIds.length; i++) {
			sb.append(CharPool.FORWARD_SLASH);

			com.liferay.portal.kernel.model.Organization organization =
				_organizationService.getOrganization(
					GetterUtil.getLong(organizationIds[i]));

			sb.append(organization.getName());
		}

		return sb.toString();
	}

	private String _getOrganizationViewSuborganizationsURL(
			long organizationId, HttpServletRequest httpServletRequest)
		throws PortalException {

		PortletURL portletURL = PortletProviderUtil.getPortletURL(
			httpServletRequest,
			com.liferay.portal.kernel.model.Organization.class.getName(),
			PortletProvider.Action.MANAGE);

		portletURL.setParameter(
			"organizationId", String.valueOf(organizationId));

		PortletURL backURL = PortletProviderUtil.getPortletURL(
			httpServletRequest,
			com.liferay.portal.kernel.model.Organization.class.getName(),
			PortletProvider.Action.MANAGE);

		portletURL.setParameter(
			PortletQName.PUBLIC_RENDER_PARAMETER_NAMESPACE + "backURL",
			backURL.toString());

		return portletURL.toString();
	}

	private String _getViewOrganizationDetailURL(
			long organizationId, HttpServletRequest httpServletRequest)
		throws PortalException {

		PortletURL viewURL = PortletProviderUtil.getPortletURL(
			httpServletRequest,
			com.liferay.portal.kernel.model.Organization.class.getName(),
			PortletProvider.Action.MANAGE);

		viewURL.setParameter(
			"mvcRenderCommandName", "viewCommerceOrganization");
		viewURL.setParameter("organizationId", String.valueOf(organizationId));

		PortletURL backURL = PortletProviderUtil.getPortletURL(
			httpServletRequest,
			com.liferay.portal.kernel.model.Organization.class.getName(),
			PortletProvider.Action.MANAGE);

		viewURL.setParameter(
			PortletQName.PUBLIC_RENDER_PARAMETER_NAMESPACE + "backURL",
			backURL.toString());

		return viewURL.toString();
	}

	@Reference
	private ClayTableSchemaBuilderFactory _clayTableSchemaBuilderFactory;

	@Reference
	private OrganizationService _organizationService;

}