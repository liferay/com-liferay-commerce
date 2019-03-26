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

package com.liferay.commerce.user.segment.web.internal.search;

import com.liferay.commerce.user.segment.model.CommerceUserSegmentCriterion;
import com.liferay.commerce.user.segment.model.CommerceUserSegmentEntry;
import com.liferay.commerce.user.segment.service.CommerceUserSegmentEntryLocalServiceUtil;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import javax.portlet.PortletResponse;

/**
 * @author Alec Sloan
 */
public class CommerceUserSegmentCriterionChecker
	extends EmptyOnClickRowChecker {

	public CommerceUserSegmentCriterionChecker(
		PortletResponse portletResponse) {

		super(portletResponse);
	}

	@Override
	public boolean isDisabled(Object obj) {
		CommerceUserSegmentCriterion commerceUserSegmentCriterion =
			(CommerceUserSegmentCriterion)obj;

		try {
			CommerceUserSegmentEntry commerceUserSegmentEntry =
				CommerceUserSegmentEntryLocalServiceUtil.
					getCommerceUserSegmentEntry(
						commerceUserSegmentCriterion.
							getCommerceUserSegmentEntryId());

			if (commerceUserSegmentEntry.isSystem()) {
				return true;
			}
		}
		catch (PortalException pe) {
			_log.error(pe, pe);
		}

		return super.isDisabled(obj);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceUserSegmentCriterionChecker.class);

}