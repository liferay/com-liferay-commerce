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

package com.liferay.commerce.data.integration.apio.internal.util;

import com.liferay.commerce.data.integration.apio.identifiers.ClassPKExternalReferenceCode;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.service.CPDefinitionLocalService;
import com.liferay.commerce.product.service.CPDefinitionService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.Serializable;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Zoltán Takács
 */
@Component(immediate = true, service = CPDefinitionHelper.class)
public class CPDefinitionHelper {

	public ClassPKExternalReferenceCode
		cpDefinitionIdToclassPKExternalReferenceCode(long cpDefinitionId) {

		try {
			CPDefinition cpDefinition = _cpDefinitionService.fetchCPDefinition(
				cpDefinitionId);

			return cpDefinitionToClassPKExternalReferenceCode(cpDefinition);
		}
		catch (PortalException pe) {
			_log.error(
				"Unable to find CPDefinition with ID " + cpDefinitionId, pe);
		}

		return null;
	}

	public ClassPKExternalReferenceCode
		cpDefinitionToClassPKExternalReferenceCode(CPDefinition cpDefinition) {

		if (cpDefinition != null) {
			return ClassPKExternalReferenceCode.create(
				cpDefinition.getCPDefinitionId(),
				cpDefinition.getExternalReferenceCode());
		}

		return null;
	}

	public void deleteCPDefinition(
			ClassPKExternalReferenceCode classPKExternalReferenceCode)
		throws PortalException {

		CPDefinition cpDefinition =
			getCPDefinitionByClassPKExternalReferenceCode(
				classPKExternalReferenceCode);

		if (cpDefinition != null) {
			_cpDefinitionLocalService.deleteCPDefinition(cpDefinition);
		}
	}

	public CPDefinition getCPDefinitionByClassPKExternalReferenceCode(
			ClassPKExternalReferenceCode
				cpDefinitionClassPKExternalReferenceCode)
		throws PortalException {

		Long cpDefinitionId =
			cpDefinitionClassPKExternalReferenceCode.getClassPK();

		if (cpDefinitionId > 0) {
			return _cpDefinitionLocalService.fetchCPDefinition(cpDefinitionId);
		}
		else {
			String externalReferenceCode =
				cpDefinitionClassPKExternalReferenceCode.
					getExternalReferenceCode();

			return _cpDefinitionLocalService.fetchByExternalReferenceCode(
				CompanyThreadLocal.getCompanyId(), externalReferenceCode);
		}
	}

	public CPDefinition upsertCPDefinition(
			long groupId, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap,
			Map<Locale, String> shortDescriptionMap, String productTypeName,
			long[] assetCategoryIds, String externalReferenceCode,
			String defaultSku, Boolean active)
		throws PortalException {

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			groupId, assetCategoryIds);

		Calendar displayCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		displayCalendar.add(Calendar.YEAR, -1);

		int displayDateMonth = displayCalendar.get(Calendar.MONTH);
		int displayDateDay = displayCalendar.get(Calendar.DAY_OF_MONTH);
		int displayDateYear = displayCalendar.get(Calendar.YEAR);
		int displayDateHour = displayCalendar.get(Calendar.HOUR);
		int displayDateMinute = displayCalendar.get(Calendar.MINUTE);
		int displayDateAmPm = displayCalendar.get(Calendar.AM_PM);

		if (displayDateAmPm == Calendar.PM) {
			displayDateHour += 12;
		}

		Calendar expirationCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		expirationCalendar.add(Calendar.MONTH, 1);

		int expirationDateMonth = expirationCalendar.get(Calendar.MONTH);
		int expirationDateDay = expirationCalendar.get(Calendar.DAY_OF_MONTH);
		int expirationDateYear = expirationCalendar.get(Calendar.YEAR);
		int expirationDateHour = expirationCalendar.get(Calendar.HOUR);
		int expirationDateMinute = expirationCalendar.get(Calendar.MINUTE);
		int expirationDateAmPm = expirationCalendar.get(Calendar.AM_PM);

		if (expirationDateAmPm == Calendar.PM) {
			expirationDateHour += 12;
		}

		CPDefinition cpDefinition = _cpDefinitionService.upsertCPDefinition(
			titleMap, shortDescriptionMap, descriptionMap, titleMap, null, null,
			null, productTypeName, true, true, false, false, 0, 10, 10, 10, 10,
			0, false, false, null, true, displayDateMonth, displayDateDay,
			displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, true, defaultSku,
			externalReferenceCode, serviceContext);

		if (!active) {
			User user = _userLocalService.getUserById(
				PrincipalThreadLocal.getUserId());

			Map<String, Serializable> workflowContext = new HashMap<>();

			_cpDefinitionLocalService.updateStatus(
				user.getUserId(), cpDefinition.getCPDefinitionId(),
				WorkflowConstants.STATUS_INACTIVE, serviceContext,
				workflowContext);
		}

		return cpDefinition;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CPDefinitionHelper.class);

	@Reference
	private CPDefinitionLocalService _cpDefinitionLocalService;

	@Reference
	private CPDefinitionService _cpDefinitionService;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

	@Reference
	private UserLocalService _userLocalService;

}