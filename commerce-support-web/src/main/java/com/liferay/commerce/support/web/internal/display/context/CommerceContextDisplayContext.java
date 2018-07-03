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

package com.liferay.commerce.support.web.internal.display.context;

import com.liferay.commerce.constants.CommerceWebKeys;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.user.segment.model.CommerceUserSegmentEntry;
import com.liferay.commerce.user.segment.service.CommerceUserSegmentEntryService;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.RenderRequest;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceContextDisplayContext {

	public CommerceContextDisplayContext(
		CommerceUserSegmentEntryService commerceUserSegmentEntryService,
		RenderRequest renderRequest) {

		_commerceUserSegmentEntryService = commerceUserSegmentEntryService;
		_renderRequest = renderRequest;
	}

	public CommerceContext getCommerceContext() {
		return (CommerceContext)_renderRequest.getAttribute(
			CommerceWebKeys.COMMERCE_CONTEXT);
	}

	public List<CommerceUserSegmentEntry> getCommerceUserSegmentEntries()
		throws PortalException {

		List<CommerceUserSegmentEntry> commerceUserSegmentEntries =
			new ArrayList<>();

		CommerceContext commerceContext = getCommerceContext();

		for (long commerceUserSegmentEntryId :
				commerceContext.getCommerceUserSegmentEntryIds()) {

			commerceUserSegmentEntries.add(
				_commerceUserSegmentEntryService.getCommerceUserSegmentEntry(
					commerceUserSegmentEntryId));
		}

		return commerceUserSegmentEntries;
	}

	private final CommerceUserSegmentEntryService
		_commerceUserSegmentEntryService;
	private final RenderRequest _renderRequest;

}