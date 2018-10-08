/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.commerce.data.integration.apio.internal.util;

import com.liferay.commerce.data.integration.apio.identifiers.ClassPKExternalReferenceCode;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPDefinitionService;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.commerce.product.service.CPInstanceService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.math.BigDecimal;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rodrigo Guedes de Souza
 */
@Component(immediate = true, service = CPInstanceHelper.class)
public class CPInstanceHelper {

	public ClassPKExternalReferenceCode
		cpInstanceIdToClassPKExternalReferenceCode(long cpDefinitionId) {

		try {
			CPInstance cpInstance = _cpInstanceService.fetchCPInstance(
				cpDefinitionId);

			return cpInstanceToClassPKExternalReferenceCode(cpInstance);
		}
		catch (PortalException pe) {
			_log.error(
				"Unable to find CPInstance with ID " + cpDefinitionId, pe);
		}

		return null;
	}

	public ClassPKExternalReferenceCode
		cpInstanceToClassPKExternalReferenceCode(CPInstance cpInstance) {

		if (cpInstance != null) {
			return ClassPKExternalReferenceCode.create(
				cpInstance.getCPDefinitionId(),
				cpInstance.getExternalReferenceCode());
		}

		return null;
	}

	public void deleteCPInstance(
			ClassPKExternalReferenceCode classPKExternalReferenceCode)
		throws PortalException {

		CPInstance cpInstance = getCPInstanceByClassPKExternalReferenceCode(
			classPKExternalReferenceCode);

		if (cpInstance != null) {
			_cpInstanceService.deleteCPInstance(cpInstance.getCPInstanceId());

			long cpDefinitionId = cpInstance.getCPDefinitionId();

			int total = _cpInstanceService.getCPDefinitionInstancesCount(
				cpDefinitionId, WorkflowConstants.STATUS_ANY);

			if (total == 0) {
				_cpDefinitionService.deleteCPDefinition(cpDefinitionId);
			}
		}
	}

	public CPInstance getCPInstanceByClassPKExternalReferenceCode(
		ClassPKExternalReferenceCode cpInstanceClassPKExternalReferenceCode) {

		Long cpInstanceId = cpInstanceClassPKExternalReferenceCode.getClassPK();

		if (cpInstanceId > 0) {
			return _cpInstanceLocalService.fetchCPInstance(cpInstanceId);
		}
		else {
			String externalReferenceCode =
				cpInstanceClassPKExternalReferenceCode.
					getExternalReferenceCode();

			return _cpInstanceLocalService.fetchByExternalReferenceCode(
				CompanyThreadLocal.getCompanyId(), externalReferenceCode);
		}
	}

	public CPInstance upsertCPInstance(
			ClassPKExternalReferenceCode
				cpDefinitionClassPKExternalReferenceCode,
			String sku, String gtin, String manufacturerPartNumber,
			boolean purchasable, double width, double height, double depth,
			double weight, double cost, double price, double promoPrice,
			boolean published, Date displayDate, Date expirationDate,
			boolean neverExpire, String externalReference, User currentUser)
		throws PortalException {

		CPDefinition cpDefinition =
			_cpDefinitionHelper.getCPDefinitionByClassPKExternalReferenceCode(
				cpDefinitionClassPKExternalReferenceCode);

		return upsertCPInstance(
			cpDefinition.getGroupId(), cpDefinition.getCPDefinitionId(), sku,
			gtin, manufacturerPartNumber, purchasable, width, height, depth,
			weight, cost, price, promoPrice, published, displayDate,
			expirationDate, neverExpire, externalReference, null, null, null,
			null, null, true, currentUser);
	}

	public CPInstance upsertCPInstance(
			long groupId, long cpDefinitionId, String sku, String gtin,
			String manufacturerPartNumber, boolean purchasable, double width,
			double height, double depth, double weight, double cost,
			double price, double promoPrice, boolean published,
			Date displayDate, Date expirationDate, boolean neverExpire,
			String externalReference, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap,
			Map<Locale, String> shortDescriptionMap, String productTypeName,
			String productExternalReferenceCode, boolean active,
			User currentUser)
		throws PortalException {

		if (productExternalReferenceCode != null) {
			CPDefinition cpDefinition = _cpDefinitionHelper.upsertCPDefinition(
				groupId, titleMap, descriptionMap, shortDescriptionMap,
				productTypeName, null, productExternalReferenceCode, null,
				active);

			cpDefinitionId = cpDefinition.getCPDefinitionId();
		}

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);
		serviceContext.setCompanyId(currentUser.getCompanyId());
		serviceContext.setScopeGroupId(groupId);
		serviceContext.setTimeZone(currentUser.getTimeZone());
		serviceContext.setUserId(currentUser.getUserId());

		Calendar displayCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		if (displayDate != null) {
			displayCalendar.setTime(displayDate);
		}
		else {
			displayCalendar.add(Calendar.YEAR, -1);
		}

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

		if (expirationDate != null) {
			expirationCalendar.setTime(expirationDate);
		}
		else {
			expirationCalendar.add(Calendar.YEAR, -1);
		}

		int expirationDateMonth = expirationCalendar.get(Calendar.MONTH);
		int expirationDateDay = expirationCalendar.get(Calendar.DAY_OF_MONTH);
		int expirationDateYear = expirationCalendar.get(Calendar.YEAR);
		int expirationDateHour = expirationCalendar.get(Calendar.HOUR);
		int expirationDateMinute = expirationCalendar.get(Calendar.MINUTE);
		int expirationDateAmPm = expirationCalendar.get(Calendar.AM_PM);

		if (expirationDateAmPm == Calendar.PM) {
			expirationDateHour += 12;
		}

		return _cpInstanceService.upsertCPInstance(
			cpDefinitionId, sku, gtin, manufacturerPartNumber, purchasable,
			null, width, height, depth, weight, new BigDecimal(price),
			new BigDecimal(promoPrice), new BigDecimal(cost), published,
			externalReference, displayDateMonth, displayDateDay,
			displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, neverExpire,
			serviceContext);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CPInstanceHelper.class);

	@Reference
	private CPDefinitionHelper _cpDefinitionHelper;

	@Reference
	private CPDefinitionService _cpDefinitionService;

	@Reference
	private CPInstanceLocalService _cpInstanceLocalService;

	@Reference
	private CPInstanceService _cpInstanceService;

	@Reference
	private UserLocalService _userLocalService;

}