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

package com.liferay.commerce.product.service.impl;

import com.liferay.commerce.product.exception.CPMeasurementUnitKeyException;
import com.liferay.commerce.product.model.CPMeasurementUnit;
import com.liferay.commerce.product.model.CPMeasurementUnitConstants;
import com.liferay.commerce.product.service.base.CPMeasurementUnitLocalServiceBaseImpl;
import com.liferay.commerce.product.util.comparator.CPMeasurementUnitPriorityComparator;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
public class CPMeasurementUnitLocalServiceImpl
	extends CPMeasurementUnitLocalServiceBaseImpl {

	@Override
	public CPMeasurementUnit addCPMeasurementUnit(
			Map<Locale, String> nameMap, String key, double rate,
			boolean primary, double priority, int type,
			ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(serviceContext.getUserId());

		if (primary) {
			rate = 1;
		}

		validate(0, serviceContext.getCompanyId(), key, primary, type);

		long cpMeasurementUnitId = counterLocalService.increment();

		CPMeasurementUnit cpMeasurementUnit =
			cpMeasurementUnitPersistence.create(cpMeasurementUnitId);

		cpMeasurementUnit.setUuid(serviceContext.getUuid());
		cpMeasurementUnit.setGroupId(serviceContext.getScopeGroupId());
		cpMeasurementUnit.setCompanyId(user.getCompanyId());
		cpMeasurementUnit.setUserId(user.getUserId());
		cpMeasurementUnit.setUserName(user.getFullName());
		cpMeasurementUnit.setNameMap(nameMap);
		cpMeasurementUnit.setKey(key);
		cpMeasurementUnit.setRate(rate);
		cpMeasurementUnit.setPrimary(primary);
		cpMeasurementUnit.setPriority(priority);
		cpMeasurementUnit.setType(type);

		cpMeasurementUnitPersistence.update(cpMeasurementUnit);

		return cpMeasurementUnit;
	}

	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public CPMeasurementUnit deleteCPMeasurementUnit(
		CPMeasurementUnit cpMeasurementUnit) {

		return cpMeasurementUnitPersistence.remove(cpMeasurementUnit);
	}

	@Override
	public CPMeasurementUnit deleteCPMeasurementUnit(long cpMeasurementUnitId)
		throws PortalException {

		CPMeasurementUnit cpMeasurementUnit =
			cpMeasurementUnitPersistence.findByPrimaryKey(cpMeasurementUnitId);

		return cpMeasurementUnitLocalService.deleteCPMeasurementUnit(
			cpMeasurementUnit);
	}

	@Override
	public void deleteCPMeasurementUnits(long companyId) {
		cpMeasurementUnitPersistence.removeByCompanyId(companyId);
	}

	@Override
	public CPMeasurementUnit fetchPrimaryCPMeasurementUnit(
		long companyId, int type) {

		return cpMeasurementUnitPersistence.fetchByC_P_T_First(
			companyId, true, type, new CPMeasurementUnitPriorityComparator());
	}

	@Override
	public List<CPMeasurementUnit> getCPMeasurementUnits(long companyId) {
		return cpMeasurementUnitPersistence.findByCompanyId(companyId);
	}

	@Override
	public List<CPMeasurementUnit> getCPMeasurementUnits(
		long companyId, int type, int start, int end,
		OrderByComparator<CPMeasurementUnit> orderByComparator) {

		return cpMeasurementUnitPersistence.findByC_T(
			companyId, type, start, end, orderByComparator);
	}

	@Override
	public List<CPMeasurementUnit> getCPMeasurementUnits(
		long companyId, int start, int end,
		OrderByComparator<CPMeasurementUnit> orderByComparator) {

		return cpMeasurementUnitPersistence.findByCompanyId(
			companyId, start, end, orderByComparator);
	}

	@Override
	public List<CPMeasurementUnit> getCPMeasurementUnits(
		long companyId, String[] keys, int type) {

		List<CPMeasurementUnit> cpMeasurementUnits = new ArrayList<>(
			keys.length);

		for (String key : keys) {
			CPMeasurementUnit cpMeasurementUnit =
				cpMeasurementUnitPersistence.fetchByC_K_T(companyId, key, type);

			if (cpMeasurementUnit != null) {
				cpMeasurementUnits.add(cpMeasurementUnit);
			}
		}

		return cpMeasurementUnits;
	}

	@Override
	public int getCPMeasurementUnitsCount(long companyId) {
		return cpMeasurementUnitPersistence.countByCompanyId(companyId);
	}

	@Override
	public int getCPMeasurementUnitsCount(long companyId, int type) {
		return cpMeasurementUnitPersistence.countByC_T(companyId, type);
	}

	@Override
	public void importDefaultValues(ServiceContext serviceContext)
		throws PortalException {

		_addCPMeasurementUnit(
			"inches", "in", 1, true, 1,
			CPMeasurementUnitConstants.TYPE_DIMENSION, serviceContext);
		_addCPMeasurementUnit(
			"feet", "ft", 0.08333333, false, 2,
			CPMeasurementUnitConstants.TYPE_DIMENSION, serviceContext);
		_addCPMeasurementUnit(
			"meters", "m", 0.0254, false, 3,
			CPMeasurementUnitConstants.TYPE_DIMENSION, serviceContext);
		_addCPMeasurementUnit(
			"millimeters", "mm", 25.4, false, 1,
			CPMeasurementUnitConstants.TYPE_DIMENSION, serviceContext);

		_addCPMeasurementUnit(
			"ounces", "oz", 16, false, 1,
			CPMeasurementUnitConstants.TYPE_WEIGHT, serviceContext);
		_addCPMeasurementUnit(
			"pounds", "lb", 1, true, 2, CPMeasurementUnitConstants.TYPE_WEIGHT,
			serviceContext);
		_addCPMeasurementUnit(
			"kilograms", "kg", 0.45359237, false, 3,
			CPMeasurementUnitConstants.TYPE_WEIGHT, serviceContext);
		_addCPMeasurementUnit(
			"grams", "g", 453.59237, false, 4,
			CPMeasurementUnitConstants.TYPE_WEIGHT, serviceContext);
	}

	@Override
	public CPMeasurementUnit setPrimary(
			long cpMeasurementUnitId, boolean primary)
		throws PortalException {

		CPMeasurementUnit cpMeasurementUnit =
			cpMeasurementUnitPersistence.findByPrimaryKey(cpMeasurementUnitId);

		validate(
			cpMeasurementUnitId, cpMeasurementUnit.getCompanyId(),
			cpMeasurementUnit.getKey(), primary, cpMeasurementUnit.getType());

		cpMeasurementUnit.setPrimary(primary);

		cpMeasurementUnitPersistence.update(cpMeasurementUnit);

		return cpMeasurementUnit;
	}

	@Override
	public CPMeasurementUnit updateCPMeasurementUnit(
			long cpMeasurementUnitId, Map<Locale, String> nameMap, String key,
			double rate, boolean primary, double priority, int type,
			ServiceContext serviceContext)
		throws PortalException {

		CPMeasurementUnit cpMeasurementUnit =
			cpMeasurementUnitPersistence.findByPrimaryKey(cpMeasurementUnitId);

		if (primary) {
			rate = 1;
		}

		validate(
			cpMeasurementUnit.getCPMeasurementUnitId(),
			serviceContext.getCompanyId(), key, primary, type);

		cpMeasurementUnit.setNameMap(nameMap);
		cpMeasurementUnit.setKey(key);
		cpMeasurementUnit.setRate(rate);
		cpMeasurementUnit.setPrimary(primary);
		cpMeasurementUnit.setPriority(priority);
		cpMeasurementUnit.setType(type);

		cpMeasurementUnitPersistence.update(cpMeasurementUnit);

		return cpMeasurementUnit;
	}

	protected void validate(
			long cpMeasurementUnitId, long companyId, String key,
			boolean primary, int type)
		throws PortalException {

		CPMeasurementUnit cpMeasurementUnit =
			cpMeasurementUnitPersistence.fetchByC_K_T(companyId, key, type);

		if ((cpMeasurementUnit != null) &&
			(cpMeasurementUnit.getCPMeasurementUnitId() !=
				cpMeasurementUnitId)) {

			throw new CPMeasurementUnitKeyException();
		}

		if (primary) {
			List<CPMeasurementUnit> cpMeasurementUnits =
				cpMeasurementUnitPersistence.findByC_P_T(
					companyId, primary, type);

			for (CPMeasurementUnit curCPMeasurementUnit : cpMeasurementUnits) {
				if (curCPMeasurementUnit.getCPMeasurementUnitId() !=
						cpMeasurementUnitId) {

					curCPMeasurementUnit.setPrimary(false);

					cpMeasurementUnitPersistence.update(curCPMeasurementUnit);
				}
			}
		}
	}

	private void _addCPMeasurementUnit(
			String name, String key, double rate, boolean primary,
			double priority, int type, ServiceContext serviceContext)
		throws PortalException {

		Map<Locale, String> nameMap = new HashMap<>();

		nameMap.put(serviceContext.getLocale(), name);

		CPMeasurementUnit cpMeasurementUnit =
			cpMeasurementUnitPersistence.fetchByC_K_T(
				serviceContext.getCompanyId(), key, type);

		if (cpMeasurementUnit == null) {
			cpMeasurementUnitLocalService.addCPMeasurementUnit(
				nameMap, key, rate, primary, priority, type, serviceContext);
		}
	}

}