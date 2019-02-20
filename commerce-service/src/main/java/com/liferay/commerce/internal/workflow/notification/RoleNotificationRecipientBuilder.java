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

package com.liferay.commerce.internal.workflow.notification;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.service.CommerceOrderLocalService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroupGroupRole;
import com.liferay.portal.kernel.model.UserGroupRole;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserGroupGroupRoleLocalService;
import com.liferay.portal.kernel.service.UserGroupRoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.workflow.kaleo.definition.NotificationReceptionType;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.runtime.notification.NotificationRecipient;
import com.liferay.portal.workflow.kaleo.runtime.notification.recipient.NotificationRecipientBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(
	immediate = true,
	property = {"recipient.type=ROLE", "service.ranking:Integer=100"},
	service = {
		NotificationRecipientBuilder.class,
		RoleNotificationRecipientBuilder.class
	}
)
public class RoleNotificationRecipientBuilder
	implements NotificationRecipientBuilder {

	@Override
	public void processKaleoNotificationRecipient(
			Set<NotificationRecipient> notificationRecipients,
			KaleoNotificationRecipient kaleoNotificationRecipient,
			NotificationReceptionType notificationReceptionType,
			ExecutionContext executionContext)
		throws Exception {

		long roleId = kaleoNotificationRecipient.getRecipientClassPK();

		Role role = _roleLocalService.getRole(roleId);

		addRoleRecipientAddresses(
			notificationRecipients, role, notificationReceptionType,
			executionContext);
	}

	@Override
	public void processKaleoTaskAssignmentInstance(
			Set<NotificationRecipient> notificationRecipients,
			KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance,
			NotificationReceptionType notificationReceptionType,
			ExecutionContext executionContext)
		throws Exception {

		long roleId = kaleoTaskAssignmentInstance.getAssigneeClassPK();

		Role role = _roleLocalService.getRole(roleId);

		addRoleRecipientAddresses(
			notificationRecipients, role, notificationReceptionType,
			executionContext);
	}

	protected void addRoleRecipientAddresses(
			Set<NotificationRecipient> notificationRecipients, Role role,
			NotificationReceptionType notificationReceptionType,
			ExecutionContext executionContext)
		throws Exception {

		List<User> users = getRoleUsers(role, executionContext);

		for (User user : users) {
			if (user.isActive()) {
				NotificationRecipient notificationRecipient =
					new NotificationRecipient(user, notificationReceptionType);

				notificationRecipients.add(notificationRecipient);
			}
		}
	}

	protected List<Long> getAncestorGroupIds(Group group, Role role)
		throws PortalException {

		List<Long> groupIds = new ArrayList<>();

		for (Group ancestorGroup : group.getAncestors()) {
			if (isValidGroup(group, role)) {
				groupIds.add(ancestorGroup.getGroupId());
			}
		}

		return groupIds;
	}

	protected List<Long> getAncestorOrganizationGroupIds(Group group, Role role)
		throws PortalException {

		List<Long> groupIds = new ArrayList<>();

		Organization organization = _organizationLocalService.getOrganization(
			group.getClassPK());

		for (Organization ancestorOrganization : organization.getAncestors()) {
			if (isValidGroup(group, role)) {
				groupIds.add(ancestorOrganization.getGroupId());
			}
		}

		return groupIds;
	}

	protected List<Long> getGroupIds(long groupId, Role role)
		throws PortalException {

		List<Long> groupIds = new ArrayList<>();

		if (groupId != WorkflowConstants.DEFAULT_GROUP_ID) {
			Group group = _groupLocalService.getGroup(groupId);

			if (group.isOrganization()) {
				groupIds.addAll(getAncestorOrganizationGroupIds(group, role));
			}

			if (group.isSite()) {
				groupIds.addAll(getAncestorGroupIds(group, role));
			}

			if (isValidGroup(group, role)) {
				groupIds.add(groupId);
			}
		}

		return groupIds;
	}

	protected List<User> getRoleUsers(
			Role role, ExecutionContext executionContext)
		throws Exception {

		long roleId = role.getRoleId();

		if (role.getType() == RoleConstants.TYPE_REGULAR) {
			return _userLocalService.getInheritedRoleUsers(
				roleId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
		}

		KaleoInstanceToken kaleoInstanceToken =
			executionContext.getKaleoInstanceToken();

		long kaleoInstanceGroupId = kaleoInstanceToken.getGroupId();

		String className = kaleoInstanceToken.getClassName();

		if (className.equals(CommerceOrder.class.getName())) {
			CommerceOrder commerceOrder =
				_commerceOrderLocalService.getCommerceOrder(
					kaleoInstanceToken.getClassPK());

			CommerceAccount commerceAccount =
				commerceOrder.getCommerceAccount();

			kaleoInstanceGroupId = commerceAccount.getCommerceAccountGroupId();
		}

		List<Long> groupIds = getGroupIds(kaleoInstanceGroupId, role);

		List<User> users = new ArrayList<>();

		for (Long groupId : groupIds) {
			List<UserGroupRole> userGroupRoles =
				_userGroupRoleLocalService.getUserGroupRolesByGroupAndRole(
					groupId, roleId);

			for (UserGroupRole userGroupRole : userGroupRoles) {
				users.add(userGroupRole.getUser());
			}

			List<UserGroupGroupRole> userGroupGroupRoles =
				_userGroupGroupRoleLocalService.
					getUserGroupGroupRolesByGroupAndRole(groupId, roleId);

			for (UserGroupGroupRole userGroupGroupRole : userGroupGroupRoles) {
				users.addAll(
					_userLocalService.getUserGroupUsers(
						userGroupGroupRole.getUserGroupId()));
			}
		}

		return users;
	}

	protected boolean isValidGroup(Group group, Role role)
		throws PortalException {

		if ((group != null) && group.isOrganization() &&
			(role.getType() == RoleConstants.TYPE_ORGANIZATION)) {

			return true;
		}
		else if ((group != null) && group.isSite() &&
				 (role.getType() == RoleConstants.TYPE_SITE)) {

			return true;
		}
		else if ((group != null) && _isAccount(group) &&
				 (role.getType() == RoleConstants.TYPE_SITE)) {

			return true;
		}

		return false;
	}

	private boolean _isAccount(Group group) {
		long classNameId = _portal.getClassNameId(
			CommerceAccount.class.getName());

		if (group.getClassNameId() == classNameId) {
			return true;
		}

		return false;
	}

	@Reference
	private CommerceOrderLocalService _commerceOrderLocalService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private OrganizationLocalService _organizationLocalService;

	@Reference
	private Portal _portal;

	@Reference
	private RoleLocalService _roleLocalService;

	@Reference
	private UserGroupGroupRoleLocalService _userGroupGroupRoleLocalService;

	@Reference
	private UserGroupRoleLocalService _userGroupRoleLocalService;

	@Reference
	private UserLocalService _userLocalService;

}