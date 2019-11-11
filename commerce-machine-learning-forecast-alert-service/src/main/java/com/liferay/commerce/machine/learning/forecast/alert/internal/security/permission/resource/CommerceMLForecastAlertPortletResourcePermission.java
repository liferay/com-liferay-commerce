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

package com.liferay.commerce.machine.learning.forecast.alert.internal.security.permission.resource;

import com.liferay.commerce.machine.learning.forecast.alert.constants.CommerceMLForecastAlertConstants;
import com.liferay.commerce.machine.learning.forecast.alert.model.CommerceMLForecastAlertEntry;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;

import org.osgi.service.component.annotations.Component;

/**
 * @author Riccardo Ferrari
 */
@Component(
	immediate = true,
	property = "resource.name=" + CommerceMLForecastAlertConstants.RESOURCE_NAME,
	service = PortletResourcePermission.class
)
public class CommerceMLForecastAlertPortletResourcePermission
	implements PortletResourcePermission {

	@Override
	public void check(
			PermissionChecker permissionChecker, Group group, String actionId)
		throws PrincipalException {

		if (!contains(permissionChecker, group, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, CommerceMLForecastAlertEntry.class.getName(),
				actionId);
		}
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, long groupId, String actionId)
		throws PrincipalException {

		if (!contains(permissionChecker, groupId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, CommerceMLForecastAlertEntry.class.getName(),
				actionId);
		}
	}

	@Override
	public boolean contains(
		PermissionChecker permissionChecker, Group group, String actionId) {

		if (group == null) {
			return false;
		}

		return _contains(permissionChecker, group.getGroupId(), actionId);
	}

	@Override
	public boolean contains(
		PermissionChecker permissionChecker, long groupId, String actionId) {

		return _contains(permissionChecker, groupId, actionId);
	}

	@Override
	public String getResourceName() {
		return CommerceMLForecastAlertConstants.RESOURCE_NAME;
	}

	private boolean _contains(
		PermissionChecker permissionChecker, long groupId, String actionId) {

		if (permissionChecker.isCompanyAdmin() ||
			permissionChecker.isOmniadmin()) {

			return true;
		}

		return permissionChecker.hasPermission(
			groupId, CommerceMLForecastAlertConstants.RESOURCE_NAME, 0,
			actionId);
	}

}