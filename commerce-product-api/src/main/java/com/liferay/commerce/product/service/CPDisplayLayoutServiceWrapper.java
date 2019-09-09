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

package com.liferay.commerce.product.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CPDisplayLayoutService}.
 *
 * @author Marco Leo
 * @see CPDisplayLayoutService
 * @generated
 */
public class CPDisplayLayoutServiceWrapper
	implements CPDisplayLayoutService, ServiceWrapper<CPDisplayLayoutService> {

	public CPDisplayLayoutServiceWrapper(
		CPDisplayLayoutService cpDisplayLayoutService) {

		_cpDisplayLayoutService = cpDisplayLayoutService;
	}

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CPDisplayLayoutServiceUtil} to access the cp display layout remote service. Add custom service methods to <code>com.liferay.commerce.product.service.impl.CPDisplayLayoutServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Override
	public com.liferay.commerce.product.model.CPDisplayLayout
			addCPDisplayLayout(
				Class<?> clazz, long classPK, String layoutUuid,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDisplayLayoutService.addCPDisplayLayout(
			clazz, classPK, layoutUuid, serviceContext);
	}

	@Override
	public void deleteCPDisplayLayout(Class<?> clazz, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		_cpDisplayLayoutService.deleteCPDisplayLayout(clazz, classPK);
	}

	@Override
	public void deleteCPDisplayLayout(long cpDisplayLayoutId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_cpDisplayLayoutService.deleteCPDisplayLayout(cpDisplayLayoutId);
	}

	@Override
	public com.liferay.commerce.product.model.CPDisplayLayout
			fetchCPDisplayLayout(long cpDisplayLayoutId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDisplayLayoutService.fetchCPDisplayLayout(cpDisplayLayoutId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _cpDisplayLayoutService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.commerce.product.model.CPDisplayLayout
			updateCPDisplayLayout(long cpDisplayLayoutId, String layoutUuid)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDisplayLayoutService.updateCPDisplayLayout(
			cpDisplayLayoutId, layoutUuid);
	}

	@Override
	public CPDisplayLayoutService getWrappedService() {
		return _cpDisplayLayoutService;
	}

	@Override
	public void setWrappedService(
		CPDisplayLayoutService cpDisplayLayoutService) {

		_cpDisplayLayoutService = cpDisplayLayoutService;
	}

	private CPDisplayLayoutService _cpDisplayLayoutService;

}