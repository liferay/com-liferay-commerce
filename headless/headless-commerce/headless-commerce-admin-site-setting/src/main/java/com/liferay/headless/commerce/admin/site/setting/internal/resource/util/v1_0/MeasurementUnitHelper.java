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
import com.liferay.commerce.product.exception.NoSuchCPMeasurementUnitException;
import com.liferay.commerce.product.model.CPMeasurementUnit;
import com.liferay.commerce.product.service.CPMeasurementUnitService;
import com.liferay.headless.commerce.admin.site.setting.internal.mapper.v1_0.DTOMapper;
import com.liferay.headless.commerce.admin.site.setting.model.v1_0.MeasurementUnitDTO;
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
@Component(immediate = true, service = MeasurementUnitHelper.class)
public class MeasurementUnitHelper {

	public void deleteMeasurementUnit(Long id) throws PortalException {
		_cpMeasurementUnitService.deleteCPMeasurementUnit(id);
	}

	public MeasurementUnitDTO getMeasurementUnitDTO(Long id)
		throws PortalException {

		CPMeasurementUnit cpMeasurementUnit =
			_cpMeasurementUnitService.getCPMeasurementUnit(id);

		return _dtoMapper.modelToDTO(cpMeasurementUnit);
	}

	public CollectionDTO<MeasurementUnitDTO> getMeasurementUnitDTOs(
			Long groupId, Integer type, Pagination pagination)
		throws PortalException {

		List<CPMeasurementUnit> cpMeasurementUnits;
		int count;

		if (type == null) {
			cpMeasurementUnits =
				_cpMeasurementUnitService.getCPMeasurementUnits(
					groupId, pagination.getStartPosition(),
					pagination.getEndPosition(), null);

			count = _cpMeasurementUnitService.getCPMeasurementUnitsCount(
				groupId);
		}
		else {
			cpMeasurementUnits =
				_cpMeasurementUnitService.getCPMeasurementUnits(
					groupId, type, pagination.getStartPosition(),
					pagination.getEndPosition(), null);

			count = _cpMeasurementUnitService.getCPMeasurementUnitsCount(
				groupId, type);
		}

		List<MeasurementUnitDTO> measurementUnitDTOs = new ArrayList<>();

		for (CPMeasurementUnit cpMeasurementUnit : cpMeasurementUnits) {
			measurementUnitDTOs.add(_dtoMapper.modelToDTO(cpMeasurementUnit));
		}

		return new CollectionDTO<>(measurementUnitDTOs, count);
	}

	public CPMeasurementUnit updateMeasurementUnit(
			Long id, MeasurementUnitDTO measurementUnitDTO, User user)
		throws PortalException {

		CPMeasurementUnit cpMeasurementUnit =
			_cpMeasurementUnitService.getCPMeasurementUnit(id);

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			cpMeasurementUnit.getGroupId(), new long[0], user, true);

		return _cpMeasurementUnitService.updateCPMeasurementUnit(
			cpMeasurementUnit.getCPMeasurementUnitId(),
			LanguageUtils.getLocalizedMap(measurementUnitDTO.getName()),
			measurementUnitDTO.getKey(),
			GetterUtil.get(
				measurementUnitDTO.getRate(), cpMeasurementUnit.getRate()),
			GetterUtil.get(
				measurementUnitDTO.isPrimary(), cpMeasurementUnit.isPrimary()),
			GetterUtil.get(
				measurementUnitDTO.getPriority(),
				cpMeasurementUnit.getPriority()),
			GetterUtil.get(
				measurementUnitDTO.getType(), cpMeasurementUnit.getType()),
			serviceContext);
	}

	public MeasurementUnitDTO upsertMeasurementUnit(
			Long groupId, MeasurementUnitDTO measurementUnitDTO, User user)
		throws PortalException {

		try {
			CPMeasurementUnit cpMeasurementUnit = updateMeasurementUnit(
				measurementUnitDTO.getId(), measurementUnitDTO, user);

			return _dtoMapper.modelToDTO(cpMeasurementUnit);
		}
		catch (NoSuchCPMeasurementUnitException nscpmue) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to find measurementUnit with ID: " +
						measurementUnitDTO.getId());
			}
		}

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			groupId, new long[0], user, true);

		CPMeasurementUnit cpMeasurementUnit =
			_cpMeasurementUnitService.addCPMeasurementUnit(
				LanguageUtils.getLocalizedMap(measurementUnitDTO.getName()),
				measurementUnitDTO.getKey(),
				GetterUtil.get(measurementUnitDTO.getRate(), 0D),
				GetterUtil.get(measurementUnitDTO.isPrimary(), false),
				GetterUtil.get(measurementUnitDTO.getPriority(), 0D),
				GetterUtil.get(measurementUnitDTO.getType(), 0),
				serviceContext);

		return _dtoMapper.modelToDTO(cpMeasurementUnit);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MeasurementUnitHelper.class);

	@Reference
	private CPMeasurementUnitService _cpMeasurementUnitService;

	@Reference
	private DTOMapper _dtoMapper;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}