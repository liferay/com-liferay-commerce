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

import com.liferay.commerce.account.model.CommerceAccountGroup;
import com.liferay.commerce.account.service.CommerceAccountGroupService;
import com.liferay.commerce.constants.CommerceWebKeys;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.RenderRequest;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceContextDisplayContext {

	public CommerceContextDisplayContext(
		CommerceAccountGroupService commerceAccountGroupService,
		RenderRequest renderRequest) {

		_commerceAccountGroupService = commerceAccountGroupService;
		_renderRequest = renderRequest;
	}

	public List<CommerceAccountGroup> getCommerceAccountGroupEntries()
		throws PortalException {

		List<CommerceAccountGroup> commerceAccountGroupEntries =
			new ArrayList<>();

		CommerceContext commerceContext = getCommerceContext();

		for (long commerceAccountGroupId :
				commerceContext.getCommerceAccountGroupIds()) {

			commerceAccountGroupEntries.add(
				_commerceAccountGroupService.getCommerceAccountGroup(
					commerceAccountGroupId));
		}

		return commerceAccountGroupEntries;
	}

	public CommerceContext getCommerceContext() {
		return (CommerceContext)_renderRequest.getAttribute(
			CommerceWebKeys.COMMERCE_CONTEXT);
	}

	private final CommerceAccountGroupService _commerceAccountGroupService;
	private final RenderRequest _renderRequest;

}