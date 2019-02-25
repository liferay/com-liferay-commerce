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

package com.liferay.headless.commerce.admin.site.setting.internal.resource.util.v1_0;

import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;
import com.liferay.commerce.openapi.core.util.LanguageUtils;
import com.liferay.commerce.openapi.core.util.ServiceContextHelper;
import com.liferay.commerce.user.segment.exception.NoSuchUserSegmentEntryException;
import com.liferay.commerce.user.segment.model.CommerceUserSegmentEntry;
import com.liferay.commerce.user.segment.service.CommerceUserSegmentEntryService;
import com.liferay.headless.commerce.admin.site.setting.internal.v1_0.DTOUtils;
import com.liferay.headless.commerce.admin.site.setting.model.v1_0.UserSegmentDTO;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = UserSegmentHelper.class)
public class UserSegmentHelper {

	public void deleteUserSegment(String id) throws PortalException {
		_commerceUserSegmentService.deleteCommerceUserSegmentEntry(
			GetterUtil.getLong(id));
	}

	public UserSegmentDTO getUserSegmentDTO(String id) throws PortalException {
		CommerceUserSegmentEntry commerceUserSegmentEntry =
			_commerceUserSegmentService.getCommerceUserSegmentEntry(
				GetterUtil.getLong(id));

		return DTOUtils.modelToDTO(commerceUserSegmentEntry);
	}

	public CollectionDTO<UserSegmentDTO> getUserSegmentDTOs(
			Long groupId, Pagination pagination)
		throws PortalException {

		List<CommerceUserSegmentEntry> commerceUserSegmentEntries =
			_commerceUserSegmentService.getCommerceUserSegmentEntries(
				groupId, pagination.getStartPosition(),
				pagination.getEndPosition(), null);

		int count =
			_commerceUserSegmentService.getCommerceUserSegmentEntriesCount(
				groupId);

		List<UserSegmentDTO> userSegmentDTOs = new ArrayList<>();

		for (CommerceUserSegmentEntry commerceUserSegmentEntry :
				commerceUserSegmentEntries) {

			userSegmentDTOs.add(DTOUtils.modelToDTO(commerceUserSegmentEntry));
		}

		return new CollectionDTO<>(userSegmentDTOs, count);
	}

	public CommerceUserSegmentEntry updateUserSegment(
			String id, UserSegmentDTO userSegmentDTO, User user)
		throws PortalException {

		CommerceUserSegmentEntry commerceUserSegmentEntry =
			_commerceUserSegmentService.getCommerceUserSegmentEntry(
				GetterUtil.getLong(id));

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			commerceUserSegmentEntry.getGroupId(), new long[0], user, true);

		return _commerceUserSegmentService.updateCommerceUserSegmentEntry(
			commerceUserSegmentEntry.getCommerceUserSegmentEntryId(),
			LanguageUtils.getLocalizedMap(userSegmentDTO.getName()),
			userSegmentDTO.getKey(),
			GetterUtil.get(
				userSegmentDTO.isActive(), commerceUserSegmentEntry.isActive()),
			GetterUtil.get(
				userSegmentDTO.getPriority(),
				commerceUserSegmentEntry.getPriority()),
			serviceContext);
	}

	public UserSegmentDTO upsertUserSegment(
			Long groupId, UserSegmentDTO userSegmentDTO, User user)
		throws PortalException {

		try {
			CommerceUserSegmentEntry commerceUserSegmentEntry =
				updateUserSegment(
					String.valueOf(userSegmentDTO.getId()), userSegmentDTO,
					user);

			return DTOUtils.modelToDTO(commerceUserSegmentEntry);
		}
		catch (NoSuchUserSegmentEntryException nsusee) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to find userSegment with ID: " +
						userSegmentDTO.getId());
			}
		}

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			groupId, new long[0], user, true);

		CommerceUserSegmentEntry commerceUserSegmentEntry =
			_commerceUserSegmentService.addCommerceUserSegmentEntry(
				LanguageUtils.getLocalizedMap(userSegmentDTO.getName()),
				userSegmentDTO.getKey(),
				GetterUtil.get(userSegmentDTO.isActive(), false),
				GetterUtil.get(userSegmentDTO.isSystem(), false),
				GetterUtil.get(userSegmentDTO.getPriority(), 0D),
				serviceContext);

		return DTOUtils.modelToDTO(commerceUserSegmentEntry);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UserSegmentHelper.class);

	@Reference
	private CommerceUserSegmentEntryService _commerceUserSegmentService;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}