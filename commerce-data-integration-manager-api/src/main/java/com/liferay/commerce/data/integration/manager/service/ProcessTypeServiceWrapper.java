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

package com.liferay.commerce.data.integration.manager.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ProcessTypeService}.
 *
 * @author Brian Wing Shun Chan
 * @see ProcessTypeService
 * @generated
 */
@ProviderType
public class ProcessTypeServiceWrapper implements ProcessTypeService,
	ServiceWrapper<ProcessTypeService> {
	public ProcessTypeServiceWrapper(ProcessTypeService processTypeService) {
		_processTypeService = processTypeService;
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _processTypeService.getOSGiServiceIdentifier();
	}

	@Override
	public ProcessTypeService getWrappedService() {
		return _processTypeService;
	}

	@Override
	public void setWrappedService(ProcessTypeService processTypeService) {
		_processTypeService = processTypeService;
	}

	private ProcessTypeService _processTypeService;
}