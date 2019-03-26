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

import com.liferay.commerce.user.segment.exception.NoSuchUserSegmentCriterionException;
import com.liferay.commerce.user.segment.model.CommerceUserSegmentCriterion;
import com.liferay.commerce.user.segment.model.CommerceUserSegmentEntry;
import com.liferay.commerce.user.segment.service.CommerceUserSegmentCriterionService;
import com.liferay.commerce.user.segment.service.CommerceUserSegmentEntryService;
import com.liferay.headless.commerce.admin.site.setting.dto.v1_0.UserSegmentCriterion;
import com.liferay.headless.commerce.admin.site.setting.internal.mapper.v1_0.DTOMapper;
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
@Component(immediate = true, service = UserSegmentCriterionHelper.class)
public class UserSegmentCriterionHelper {

	public void deleteUserSegmentCriterion(Long criterionId)
		throws PortalException {

		_commerceUserSegmentCriterionService.deleteCommerceUserSegmentCriterion(
			criterionId);
	}

	public UserSegmentCriterion getUserSegmentCriterion(Long criterionId)
		throws PortalException {

		CommerceUserSegmentCriterion commerceUserSegmentCriterion =
			_commerceUserSegmentCriterionService.
				getCommerceUserSegmentCriterion(criterionId);

		return _dtoMapper.modelToDTO(commerceUserSegmentCriterion);
	}

	public Page<UserSegmentCriterion> getUserSegmentCriterions(
			Long commerceUserSegmentEntryId, Pagination pagination)
		throws PortalException {

		List<CommerceUserSegmentCriterion> commerceUserSegmentCriteria =
			_commerceUserSegmentCriterionService.getCommerceUserSegmentCriteria(
				commerceUserSegmentEntryId, pagination.getStartPosition(),
				pagination.getEndPosition(), null);

		int count =
			_commerceUserSegmentCriterionService.
				getCommerceUserSegmentCriteriaCount(commerceUserSegmentEntryId);

		List<UserSegmentCriterion> userSegmentCriterions = new ArrayList<>();

		for (CommerceUserSegmentCriterion commerceUserSegmentCriterion :
				commerceUserSegmentCriteria) {

			userSegmentCriterions.add(
				_dtoMapper.modelToDTO(commerceUserSegmentCriterion));
		}

		return Page.of(userSegmentCriterions, pagination, count);
	}

	public UserSegmentCriterion updateUserSegmentCriterion(
			Long criterionId, UserSegmentCriterion userSegmentCriterion,
			User user)
		throws PortalException {

		return _dtoMapper.modelToDTO(
			_updateUserSegmentCriterion(
				criterionId, userSegmentCriterion, user));
	}

	public UserSegmentCriterion upsertUserSegmentCriterion(
			Long userSegmentId, UserSegmentCriterion userSegmentCriterion,
			User user)
		throws PortalException {

		try {
			CommerceUserSegmentCriterion commerceUserSegmentCriterion =
				_updateUserSegmentCriterion(
					userSegmentCriterion.getId(), userSegmentCriterion, user);

			return _dtoMapper.modelToDTO(commerceUserSegmentCriterion);
		}
		catch (NoSuchUserSegmentCriterionException nsusce) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to find userSegmentCriterion with ID: " +
						userSegmentCriterion.getId());
			}
		}

		CommerceUserSegmentEntry commerceUserSegmentEntry =
			_commerceUserSegmentEntryService.getCommerceUserSegmentEntry(
				userSegmentId);

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			commerceUserSegmentEntry.getGroupId(), new long[0], user, true);

		CommerceUserSegmentCriterion commerceUserSegmentCriterion =
			_commerceUserSegmentCriterionService.
				addCommerceUserSegmentCriterion(
					userSegmentCriterion.getCommerceUserSegmentEntryId(),
					userSegmentCriterion.getType(),
					userSegmentCriterion.getTypeSettings(),
					GetterUtil.get(userSegmentCriterion.getPriority(), 0D),
					serviceContext);

		return _dtoMapper.modelToDTO(commerceUserSegmentCriterion);
	}

	private CommerceUserSegmentCriterion _updateUserSegmentCriterion(
			Long criterionId, UserSegmentCriterion userSegmentCriterion,
			User user)
		throws PortalException {

		CommerceUserSegmentCriterion commerceUserSegmentCriterion =
			_commerceUserSegmentCriterionService.
				getCommerceUserSegmentCriterion(criterionId);

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			commerceUserSegmentCriterion.getGroupId(), new long[0], user, true);

		return _commerceUserSegmentCriterionService.
			updateCommerceUserSegmentCriterion(
				commerceUserSegmentCriterion.
					getCommerceUserSegmentCriterionId(),
				userSegmentCriterion.getType(),
				GetterUtil.get(
					userSegmentCriterion.getTypeSettings(),
					commerceUserSegmentCriterion.getTypeSettings()),
				GetterUtil.get(
					userSegmentCriterion.getPriority(),
					commerceUserSegmentCriterion.getPriority()),
				serviceContext);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UserSegmentCriterionHelper.class);

	@Reference
	private CommerceUserSegmentCriterionService
		_commerceUserSegmentCriterionService;

	@Reference
	private CommerceUserSegmentEntryService _commerceUserSegmentEntryService;

	@Reference
	private DTOMapper _dtoMapper;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}