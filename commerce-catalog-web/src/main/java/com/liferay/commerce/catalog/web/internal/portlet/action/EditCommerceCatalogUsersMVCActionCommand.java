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

package com.liferay.commerce.catalog.web.internal.portlet.action;

import com.liferay.commerce.product.constants.CPPortletKeys;
import com.liferay.commerce.product.service.CommerceCatalogService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.UserGroupRoleLocalService;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alec Sloan
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + CPPortletKeys.COMMERCE_CATALOGS,
		"mvc.command.name=editCommerceCatalogUsers"
	},
	service = MVCActionCommand.class
)
public class EditCommerceCatalogUsersMVCActionCommand
	extends BaseMVCActionCommand {

	protected void addCommerceCatalogUsers(ActionRequest actionRequest)
		throws Exception {

		long commerceCatalogId = ParamUtil.getLong(
			actionRequest, "commerceCatalogId");

		Group group = _commerceCatalogService.getCommerceCatalogGroup(
			commerceCatalogId);

		long[] commerceCatalogUserIds = ParamUtil.getLongValues(
			actionRequest, "commerceCatalogUserIds");

		for (long commerceCatalogUserId : commerceCatalogUserIds) {
			_groupLocalService.addUserGroup(
				commerceCatalogUserId, group.getGroupId());
		}
	}

	protected void deleteCommerceCatalogUser(ActionRequest actionRequest)
		throws Exception {

		long commerceCatalogUserId = ParamUtil.getLong(
			actionRequest, "commerceCatalogUserIds");

		long commerceCatalogId = ParamUtil.getLong(
			actionRequest, "commerceCatalogId");

		Group group = _commerceCatalogService.getCommerceCatalogGroup(
			commerceCatalogId);

		_userGroupRoleLocalService.deleteUserGroupRoles(
			commerceCatalogUserId, new long[] {group.getGroupId()});

		_groupLocalService.deleteUserGroup(commerceCatalogUserId, group);
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			if (cmd.equals(Constants.ADD)) {
				addCommerceCatalogUsers(actionRequest);
			}
			else if (cmd.equals(Constants.DELETE)) {
				deleteCommerceCatalogUser(actionRequest);
			}
			else if (cmd.equals(Constants.UPDATE)) {
				updateCommerceCatalogUserRoles(actionRequest);
			}

			long commerceCatalogId = ParamUtil.getLong(
				actionRequest, "commerceCatalogId");

			String redirect = getSaveAndContinueRedirect(
				actionRequest, commerceCatalogId);

			sendRedirect(actionRequest, actionResponse, redirect);
		}
		catch (PrincipalException pe) {
			SessionErrors.add(actionRequest, pe.getClass());

			actionResponse.setRenderParameter("mvcPath", "/error.jsp");
		}
	}

	protected String getSaveAndContinueRedirect(
			ActionRequest actionRequest, long commerceCatalogId)
		throws PortalException {

		PortletURL portletURL = _portal.getControlPanelPortletURL(
			actionRequest, CPPortletKeys.COMMERCE_CATALOGS,
			PortletRequest.RENDER_PHASE);

		portletURL.setParameter(
			"commerceCatalogId", String.valueOf(commerceCatalogId));
		portletURL.setParameter(
			"mvcRenderCommandName", "editCommerceCatalogUsers");

		return portletURL.toString();
	}

	protected void updateCommerceCatalogUserRoles(ActionRequest actionRequest)
		throws Exception {

		long commerceCatalogUserId = ParamUtil.getLong(
			actionRequest, "commerceCatalogUserIds");

		long commerceCatalogId = ParamUtil.getLong(
			actionRequest, "commerceCatalogId");

		Group group = _commerceCatalogService.getCommerceCatalogGroup(
			commerceCatalogId);

		_userGroupRoleLocalService.deleteUserGroupRoles(
			commerceCatalogUserId, new long[] {group.getGroupId()});

		long[] selectedRoleIds = ParamUtil.getLongValues(
			actionRequest, "selectedRoleIds");

		_userGroupRoleLocalService.addUserGroupRoles(
			commerceCatalogUserId, group.getGroupId(), selectedRoleIds);
	}

	@Reference
	private CommerceCatalogService _commerceCatalogService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private Portal _portal;

	@Reference
	private UserGroupRoleLocalService _userGroupRoleLocalService;

}