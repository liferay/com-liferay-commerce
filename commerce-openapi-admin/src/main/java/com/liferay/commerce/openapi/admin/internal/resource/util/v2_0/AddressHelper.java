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

package com.liferay.commerce.openapi.admin.internal.resource.util.v2_0;

import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.openapi.admin.internal.resource.util.ServiceContextHelper;
import com.liferay.commerce.openapi.admin.internal.util.IdUtils;
import com.liferay.commerce.openapi.admin.internal.util.v2_0.DTOUtils;
import com.liferay.commerce.openapi.admin.model.v2_0.AddressDTO;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;
import com.liferay.commerce.service.CommerceAddressService;
import com.liferay.portal.kernel.exception.NoSuchAddressException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = AddressHelper.class)
public class AddressHelper {

	public AddressDTO addAddress(
			String className, long classPK, AddressDTO addressDTO)
		throws PortalException {

		return DTOUtils.modelToDTO(
			_commerceAddressService.addCommerceAddress(
				className, classPK, addressDTO.getName(),
				addressDTO.getDescription(), addressDTO.getStreet1(),
				addressDTO.getStreet2(), addressDTO.getStreet3(),
				addressDTO.getCity(), addressDTO.getZip(),
				addressDTO.getCommerceRegionId(),
				addressDTO.getCommerceCountryId(), addressDTO.getPhoneNumber(),
				addressDTO.isDefaultBilling(), addressDTO.isDefaultShipping(),
				_serviceContextHelper.getServiceContext()));
	}

	public void deleteAddress(String id) throws PortalException {
		CommerceAddress commerceAddress = null;

		try {
			commerceAddress = getAddressById(id);
		}
		catch (NoSuchAddressException nsae) {
			if (_log.isDebugEnabled()) {
				_log.debug("Address does not exist with ID: " + id, nsae);
			}

			return;
		}

		_commerceAddressService.deleteCommerceAddress(
			commerceAddress.getCommerceAddressId());
	}

	public CommerceAddress getAddressById(String id) throws PortalException {
		CommerceAddress commerceAddress = null;

		if (IdUtils.isLocalPK(id)) {
			commerceAddress = _commerceAddressService.fetchCommerceAddress(
				GetterUtil.getLong(id));
		}

		if (commerceAddress == null) {
			throw new NoSuchAddressException(
				"Unable to find Address with ID: " + id);
		}

		return commerceAddress;
	}

	public CollectionDTO<AddressDTO> getAddresses(
			String className, long classPK, Pagination pagination)
		throws PortalException {

		List<CommerceAddress> commerceAddresses =
			_commerceAddressService.getCommerceAddresses(
				className, classPK, pagination.getStartPosition(),
				pagination.getEndPosition(), null);

		int totalItems = _commerceAddressService.getCommerceAddressesCount(
			className, classPK);

		Stream<CommerceAddress> stream = commerceAddresses.stream();

		return stream.map(
			DTOUtils::modelToDTO
		).collect(
			Collectors.collectingAndThen(
				Collectors.toList(),
				addressDTOs ->
					new CollectionDTO<>(addressDTOs, totalItems))
		);
	}

	public AddressDTO updateAddress(String id, AddressDTO addressDTO)
		throws Exception {

		CommerceAddress commerceAddress = getAddressById(id);

		return DTOUtils.modelToDTO(
			_commerceAddressService.updateCommerceAddress(
				commerceAddress.getCommerceAddressId(), addressDTO.getName(),
				addressDTO.getDescription(), addressDTO.getStreet1(),
				addressDTO.getStreet2(), addressDTO.getStreet3(),
				addressDTO.getCity(), addressDTO.getZip(),
				addressDTO.getCommerceRegionId(),
				addressDTO.getCommerceCountryId(), addressDTO.getPhoneNumber(),
				addressDTO.isDefaultBilling(), addressDTO.isDefaultShipping(),
				_serviceContextHelper.getServiceContext()));
	}

	private static final Log _log = LogFactoryUtil.getLog(AddressHelper.class);

	@Reference
	private CommerceAddressService _commerceAddressService;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}