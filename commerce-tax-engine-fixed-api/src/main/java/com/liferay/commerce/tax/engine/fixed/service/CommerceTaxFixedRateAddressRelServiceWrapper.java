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

package com.liferay.commerce.tax.engine.fixed.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceTaxFixedRateAddressRelService}.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceTaxFixedRateAddressRelService
 * @generated
 */
public class CommerceTaxFixedRateAddressRelServiceWrapper
	implements CommerceTaxFixedRateAddressRelService,
			   ServiceWrapper<CommerceTaxFixedRateAddressRelService> {

	public CommerceTaxFixedRateAddressRelServiceWrapper(
		CommerceTaxFixedRateAddressRelService
			commerceTaxFixedRateAddressRelService) {

		_commerceTaxFixedRateAddressRelService =
			commerceTaxFixedRateAddressRelService;
	}

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceTaxFixedRateAddressRelServiceUtil} to access the commerce tax fixed rate address rel remote service. Add custom service methods to <code>com.liferay.commerce.tax.engine.fixed.service.impl.CommerceTaxFixedRateAddressRelServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Override
	public
		com.liferay.commerce.tax.engine.fixed.model.
			CommerceTaxFixedRateAddressRel addCommerceTaxFixedRateAddressRel(
					long commerceTaxMethodId, long cpTaxCategoryId,
					long commerceCountryId, long commerceRegionId, String zip,
					double rate,
					com.liferay.portal.kernel.service.ServiceContext
						serviceContext)
				throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceTaxFixedRateAddressRelService.
			addCommerceTaxFixedRateAddressRel(
				commerceTaxMethodId, cpTaxCategoryId, commerceCountryId,
				commerceRegionId, zip, rate, serviceContext);
	}

	@Override
	public void deleteCommerceTaxFixedRateAddressRel(
			long commerceTaxFixedRateAddressRelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_commerceTaxFixedRateAddressRelService.
			deleteCommerceTaxFixedRateAddressRel(
				commerceTaxFixedRateAddressRelId);
	}

	@Override
	public
		com.liferay.commerce.tax.engine.fixed.model.
			CommerceTaxFixedRateAddressRel fetchCommerceTaxFixedRateAddressRel(
					long commerceTaxFixedRateAddressRelId)
				throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceTaxFixedRateAddressRelService.
			fetchCommerceTaxFixedRateAddressRel(
				commerceTaxFixedRateAddressRelId);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.tax.engine.fixed.model.
			CommerceTaxFixedRateAddressRel>
					getCommerceTaxMethodFixedRateAddressRels(
						long groupId, long commerceTaxMethodId, int start,
						int end,
						com.liferay.portal.kernel.util.OrderByComparator
							<com.liferay.commerce.tax.engine.fixed.model.
								CommerceTaxFixedRateAddressRel>
									orderByComparator)
				throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceTaxFixedRateAddressRelService.
			getCommerceTaxMethodFixedRateAddressRels(
				groupId, commerceTaxMethodId, start, end, orderByComparator);
	}

	@Override
	public int getCommerceTaxMethodFixedRateAddressRelsCount(
			long groupId, long commerceTaxMethodId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceTaxFixedRateAddressRelService.
			getCommerceTaxMethodFixedRateAddressRelsCount(
				groupId, commerceTaxMethodId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceTaxFixedRateAddressRelService.
			getOSGiServiceIdentifier();
	}

	@Override
	public
		com.liferay.commerce.tax.engine.fixed.model.
			CommerceTaxFixedRateAddressRel updateCommerceTaxFixedRateAddressRel(
					long commerceTaxFixedRateAddressRelId,
					long commerceCountryId, long commerceRegionId, String zip,
					double rate)
				throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceTaxFixedRateAddressRelService.
			updateCommerceTaxFixedRateAddressRel(
				commerceTaxFixedRateAddressRelId, commerceCountryId,
				commerceRegionId, zip, rate);
	}

	@Override
	public CommerceTaxFixedRateAddressRelService getWrappedService() {
		return _commerceTaxFixedRateAddressRelService;
	}

	@Override
	public void setWrappedService(
		CommerceTaxFixedRateAddressRelService
			commerceTaxFixedRateAddressRelService) {

		_commerceTaxFixedRateAddressRelService =
			commerceTaxFixedRateAddressRelService;
	}

	private CommerceTaxFixedRateAddressRelService
		_commerceTaxFixedRateAddressRelService;

}