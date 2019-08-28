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

package com.liferay.commerce.notification.internal.util;

import com.liferay.commerce.account.model.CommerceAccountUserRel;
import com.liferay.commerce.account.service.CommerceAccountGroupLocalService;
import com.liferay.commerce.account.service.CommerceAccountUserRelLocalService;
import com.liferay.commerce.notification.model.CommerceNotificationTemplate;
import com.liferay.commerce.notification.model.CommerceNotificationTemplateCommerceAccountGroupRel;
import com.liferay.commerce.notification.service.CommerceNotificationQueueEntryLocalService;
import com.liferay.commerce.notification.service.CommerceNotificationTemplateCommerceAccountGroupRelLocalService;
import com.liferay.commerce.notification.service.CommerceNotificationTemplateLocalService;
import com.liferay.commerce.notification.type.CommerceNotificationType;
import com.liferay.commerce.notification.type.CommerceNotificationTypeRegistry;
import com.liferay.commerce.notification.util.CommerceNotificationHelper;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = CommerceNotificationHelper.class)
public class CommerceNotificationHelperImpl
	implements CommerceNotificationHelper {

	@Override
	public void sendNotifications(long groupId, String key, Object object)
		throws PortalException {

		sendNotifications(groupId, key, object, null);
	}

	@Override
	public void sendNotifications(
			long groupId, String key, Object object, long[] userIds)
		throws PortalException {

		CommerceNotificationType commerceNotificationType =
			_commerceNotificationTypeRegistry.getCommerceNotificationType(key);

		List<CommerceNotificationTemplate> commerceNotificationTemplates =
			_commerceNotificationTemplateLocalService.
				getCommerceNotificationTemplates(
					groupId, commerceNotificationType.getKey(), true);

		for (CommerceNotificationTemplate commerceNotificationTemplate :
				commerceNotificationTemplates) {

			List<CommerceNotificationTemplateCommerceAccountGroupRel>
				commerceNotificationTemplateCommerceAccountGroupRels =
					_commerceNotificationTemplateCommerceAccountGroupRelLocalService.
						getCommerceNotificationTemplateCommerceAccountGroupRels(
							commerceNotificationTemplate.
								getCommerceNotificationTemplateId(),
							QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

			long[] commerceAccountGroupIds = ListUtil.toLongArray(
				commerceNotificationTemplateCommerceAccountGroupRels,
				CommerceNotificationTemplateCommerceAccountGroupRel.
					COMMERCE_ACCOUNT_GROUP_ID_ACCESSOR);

			if (ArrayUtil.isEmpty(userIds)) {
				sendNotificationsToAccountGroupUsers(
					commerceAccountGroupIds, commerceNotificationTemplate,
					commerceNotificationType, object);
			}
			else {
				sendNotificationsToUserIds(
					userIds, commerceAccountGroupIds,
					commerceNotificationTemplate, commerceNotificationType,
					object);
			}
		}
	}

	protected String formatString(
			CommerceNotificationType commerceNotificationType, String content,
			Object object, Locale locale)
		throws PortalException {

		if (Validator.isNull(content)) {
			return StringPool.BLANK;
		}

		Set<String> placeholders = new HashSet<>();

		Matcher matcher = _placeholderPattern.matcher(content);

		while (matcher.find()) {
			placeholders.add(matcher.group());
		}

		for (String placeholder : placeholders) {
			content = content.replace(
				placeholder,
				commerceNotificationType.getFilledTerm(
					placeholder, object, locale));
		}

		return content;
	}

	protected void sendNotification(
			CommerceNotificationTemplate commerceNotificationTemplate,
			CommerceNotificationType commerceNotificationType, Object object,
			long userId)
		throws PortalException {

		long groupId = commerceNotificationTemplate.getGroupId();

		User user = _userLocalService.getUser(userId);

		Locale siteDefaultLocale = _portal.getSiteDefaultLocale(groupId);
		Locale userLocale = user.getLocale();

		String fromName = commerceNotificationTemplate.getFromName(
			user.getLanguageId());

		String subject = formatString(
			commerceNotificationType,
			commerceNotificationTemplate.getSubject(userLocale), object,
			userLocale);
		String body = formatString(
			commerceNotificationType,
			commerceNotificationTemplate.getBody(userLocale), object,
			userLocale);

		if (Validator.isNull(fromName)) {
			fromName = commerceNotificationTemplate.getFromName(
				_portal.getSiteDefaultLocale(groupId));
		}

		if (Validator.isNull(subject)) {
			subject = formatString(
				commerceNotificationType,
				commerceNotificationTemplate.getSubject(siteDefaultLocale),
				object, siteDefaultLocale);
		}

		if (Validator.isNull(body)) {
			formatString(
				commerceNotificationType,
				commerceNotificationTemplate.getBody(siteDefaultLocale), object,
				siteDefaultLocale);
		}

		_commerceNotificationQueueEntryLocalService.
			addCommerceNotificationQueueEntry(
				user.getUserId(), groupId,
				commerceNotificationTemplate.
					getCommerceNotificationTemplateId(),
				commerceNotificationTemplate.getFrom(), fromName,
				user.getEmailAddress(), user.getFullName(),
				commerceNotificationTemplate.getCc(),
				commerceNotificationTemplate.getBcc(), subject, body, 0);
	}

	protected void sendNotificationsToAccountGroupUsers(
			long[] commerceAccountGroupIds,
			CommerceNotificationTemplate commerceNotificationTemplate,
			CommerceNotificationType commerceNotificationType, Object object)
		throws PortalException {

		List<Long> commerceAccountUserIds =
			_commerceAccountGroupLocalService.
				getCommerceAccountUserIdsFromAccountGroupIds(
					commerceAccountGroupIds, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS);

		long[] commerceAccountUserIdsArray = ArrayUtil.toLongArray(
			commerceAccountUserIds);

		for (long userId : commerceAccountUserIdsArray) {
			sendNotification(
				commerceNotificationTemplate, commerceNotificationType, object,
				userId);
		}
	}

	protected void sendNotificationsToUserIds(
			long[] userIds, long[] commerceAccountGroupIds,
			CommerceNotificationTemplate commerceNotificationTemplate,
			CommerceNotificationType commerceNotificationType, Object object)
		throws PortalException {

		for (long userId : userIds) {
			List<CommerceAccountUserRel> commerceAccountUserRelsByUserId =
				_commerceAccountUserRelLocalService.
					getCommerceAccountUserRelsByCommerceAccountUserId(userId);

			List<Long> commerceAccountIds = new ArrayList<>();

			for (CommerceAccountUserRel commerceAccountUserRel :
					commerceAccountUserRelsByUserId) {

				commerceAccountIds.add(
					commerceAccountUserRel.getCommerceAccountId());
			}

			long[] commerceAccountIdsArray = ArrayUtil.toLongArray(
				commerceAccountIds);

			if (ArrayUtil.containsAll(
					commerceAccountIdsArray, commerceAccountGroupIds)) {

				sendNotification(
					commerceNotificationTemplate, commerceNotificationType,
					object, userId);
			}
		}
	}

	private static final Pattern _placeholderPattern = Pattern.compile(
		"\\[%[^\\[%]+%\\]", Pattern.CASE_INSENSITIVE);

	@Reference
	private CommerceAccountGroupLocalService _commerceAccountGroupLocalService;

	@Reference
	private CommerceAccountUserRelLocalService
		_commerceAccountUserRelLocalService;

	@Reference
	private CommerceNotificationQueueEntryLocalService
		_commerceNotificationQueueEntryLocalService;

	@Reference
	private CommerceNotificationTemplateCommerceAccountGroupRelLocalService
		_commerceNotificationTemplateCommerceAccountGroupRelLocalService;

	@Reference
	private CommerceNotificationTemplateLocalService
		_commerceNotificationTemplateLocalService;

	@Reference
	private CommerceNotificationTypeRegistry _commerceNotificationTypeRegistry;

	@Reference
	private Portal _portal;

	@Reference
	private UserLocalService _userLocalService;

}