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

package com.liferay.headless.commerce.admin.site.setting.internal.util.v1_0;

import com.liferay.commerce.user.segment.exception.NoSuchUserSegmentEntryException;
import com.liferay.commerce.user.segment.model.CommerceUserSegmentEntry;
import com.liferay.commerce.user.segment.service.CommerceUserSegmentEntryService;
import com.liferay.headless.commerce.admin.site.setting.dto.v1_0.UserSegment;
import com.liferay.headless.commerce.admin.site.setting.internal.mapper.v1_0.DTOMapper;
import com.liferay.headless.commerce.core.util.LanguageUtils;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 * @author Zoltán Takács
 */
@Component(immediate = true, service = UserSegmentHelper.class)
public class UserSegmentHelper {

	public void deleteUserSegment(Long id) throws PortalException {
		_commerceUserSegmentService.deleteCommerceUserSegmentEntry(id);
	}

	public UserSegment getUserSegment(Long id) throws PortalException {
		CommerceUserSegmentEntry commerceUserSegmentEntry =
			_commerceUserSegmentService.getCommerceUserSegmentEntry(id);

		return _dtoMapper.modelToDTO(commerceUserSegmentEntry);
	}

	public Page<UserSegment> getUserSegments(
			Long groupId, Pagination pagination)
		throws PortalException {

		List<CommerceUserSegmentEntry> commerceUserSegmentEntries =
			_commerceUserSegmentService.getCommerceUserSegmentEntries(
				groupId, pagination.getStartPosition(),
				pagination.getEndPosition(), null);

		int count =
			_commerceUserSegmentService.getCommerceUserSegmentEntriesCount(
				groupId);

		List<UserSegment> userSegments = new ArrayList<>();

		for (CommerceUserSegmentEntry commerceUserSegmentEntry :
				commerceUserSegmentEntries) {

			userSegments.add(_dtoMapper.modelToDTO(commerceUserSegmentEntry));
		}

		return Page.of(userSegments, pagination, count);
	}

	public CommerceUserSegmentEntry updateUserSegment(
			Long id, UserSegment userSegment, User user)
		throws PortalException {

		CommerceUserSegmentEntry commerceUserSegmentEntry =
			_commerceUserSegmentService.getCommerceUserSegmentEntry(id);

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			commerceUserSegmentEntry.getGroupId(), new long[0], user, true);

		return _commerceUserSegmentService.updateCommerceUserSegmentEntry(
			commerceUserSegmentEntry.getCommerceUserSegmentEntryId(),
			LanguageUtils.getLocalizedMap(userSegment.getName()),
			userSegment.getKey(),
			GetterUtil.get(
				userSegment.getActive(), commerceUserSegmentEntry.isActive()),
			GetterUtil.get(
				userSegment.getPriority(),
				commerceUserSegmentEntry.getPriority()),
			serviceContext);
	}

	public UserSegment upsertUserSegment(
			Long groupId, UserSegment userSegment, User user)
		throws PortalException {

		try {
			CommerceUserSegmentEntry commerceUserSegmentEntry =
				updateUserSegment(userSegment.getId(), userSegment, user);

			return _dtoMapper.modelToDTO(commerceUserSegmentEntry);
		}
		catch (NoSuchUserSegmentEntryException nsusee) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to find userSegment with ID: " +
						userSegment.getId());
			}
		}

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			groupId, new long[0], user, true);

		CommerceUserSegmentEntry commerceUserSegmentEntry =
			_commerceUserSegmentService.addCommerceUserSegmentEntry(
				LanguageUtils.getLocalizedMap(userSegment.getName()),
				userSegment.getKey(),
				GetterUtil.get(userSegment.getActive(), false),
				GetterUtil.get(userSegment.getSystem(), false),
				GetterUtil.get(userSegment.getPriority(), 0D), serviceContext);

		return _dtoMapper.modelToDTO(commerceUserSegmentEntry);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UserSegmentHelper.class);

	@Reference
	private CommerceUserSegmentEntryService _commerceUserSegmentService;

	@Reference
	private DTOMapper _dtoMapper;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}