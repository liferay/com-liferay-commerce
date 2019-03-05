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

package com.liferay.commerce.product.type.virtual.service.impl;

import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPDefinitionLocalService;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.commerce.product.service.CProductLocalService;
import com.liferay.commerce.product.type.virtual.exception.CPDefinitionVirtualSettingException;
import com.liferay.commerce.product.type.virtual.exception.CPDefinitionVirtualSettingFileEntryIdException;
import com.liferay.commerce.product.type.virtual.exception.CPDefinitionVirtualSettingSampleException;
import com.liferay.commerce.product.type.virtual.exception.CPDefinitionVirtualSettingSampleFileEntryIdException;
import com.liferay.commerce.product.type.virtual.exception.CPDefinitionVirtualSettingSampleUrlException;
import com.liferay.commerce.product.type.virtual.exception.CPDefinitionVirtualSettingTermsOfUseArticleResourcePKException;
import com.liferay.commerce.product.type.virtual.exception.CPDefinitionVirtualSettingTermsOfUseContentException;
import com.liferay.commerce.product.type.virtual.exception.CPDefinitionVirtualSettingTermsOfUseException;
import com.liferay.commerce.product.type.virtual.exception.CPDefinitionVirtualSettingUrlException;
import com.liferay.commerce.product.type.virtual.model.CPDefinitionVirtualSetting;
import com.liferay.commerce.product.type.virtual.service.base.CPDefinitionVirtualSettingLocalServiceBaseImpl;
import com.liferay.document.library.kernel.exception.NoSuchFileEntryException;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
public class CPDefinitionVirtualSettingLocalServiceImpl
	extends CPDefinitionVirtualSettingLocalServiceBaseImpl {

	@Override
	public CPDefinitionVirtualSetting addCPDefinitionVirtualSetting(
			String className, long classPK, long fileEntryId, String url,
			int activationStatus, long duration, int maxUsages,
			boolean useSample, long sampleFileEntryId, String sampleUrl,
			boolean termsOfUseRequired,
			Map<Locale, String> termsOfUseContentMap,
			long termsOfUseJournalArticleResourcePrimKey, boolean override,
			ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(serviceContext.getUserId());
		long groupId = serviceContext.getScopeGroupId();

		if (Validator.isNotNull(url)) {
			fileEntryId = 0;
		}
		else {
			url = null;
		}

		if (useSample) {
			if (Validator.isNotNull(sampleUrl)) {
				sampleFileEntryId = 0;
			}
			else {
				sampleUrl = null;
			}
		}
		else {
			sampleUrl = null;
			sampleFileEntryId = 0;
		}

		if (termsOfUseRequired) {
			if (termsOfUseJournalArticleResourcePrimKey > 0) {
				termsOfUseContentMap = Collections.emptyMap();
			}
			else {
				termsOfUseJournalArticleResourcePrimKey = 0;
			}
		}
		else {
			termsOfUseContentMap = Collections.emptyMap();
			termsOfUseJournalArticleResourcePrimKey = 0;
		}

		validate(
			fileEntryId, url, useSample, sampleFileEntryId, sampleUrl,
			termsOfUseRequired, termsOfUseContentMap,
			termsOfUseJournalArticleResourcePrimKey);

		long cpDefinitionVirtualSettingId = counterLocalService.increment();

		CPDefinitionVirtualSetting cpDefinitionVirtualSetting =
			cpDefinitionVirtualSettingPersistence.create(
				cpDefinitionVirtualSettingId);

		if (className.equals(CPDefinition.class.getName()) &&
			_cpDefinitionLocalService.isVersionable(classPK)) {

			CPDefinition newCPDefinition =
				_cpDefinitionLocalService.copyCPDefinition(
					cpDefinitionVirtualSetting.getClassPK());

			classPK = newCPDefinition.getCPDefinitionId();
		}
		else if (className.equals(CPInstance.class.getName())) {
			CPInstance cpInstance = _cpInstanceLocalService.getCPInstance(
				classPK);

			if (_cpDefinitionLocalService.isVersionable(
					cpInstance.getCPDefinitionId())) {

				CPDefinition newCPDefinition =
					_cpDefinitionLocalService.copyCPDefinition(
						cpInstance.getCPDefinitionId());

				CPInstance newCPInstance =
					_cpInstanceLocalService.getCProductInstance(
						newCPDefinition.getCProductId(),
						cpInstance.getCPInstanceUuid());

				classPK = newCPInstance.getCPInstanceId();
			}
		}

		cpDefinitionVirtualSetting.setUuid(serviceContext.getUuid());
		cpDefinitionVirtualSetting.setGroupId(groupId);
		cpDefinitionVirtualSetting.setCompanyId(user.getCompanyId());
		cpDefinitionVirtualSetting.setUserId(user.getUserId());
		cpDefinitionVirtualSetting.setUserName(user.getFullName());
		cpDefinitionVirtualSetting.setClassName(className);
		cpDefinitionVirtualSetting.setClassPK(classPK);
		cpDefinitionVirtualSetting.setFileEntryId(fileEntryId);
		cpDefinitionVirtualSetting.setUrl(url);
		cpDefinitionVirtualSetting.setActivationStatus(activationStatus);
		cpDefinitionVirtualSetting.setDuration(duration);
		cpDefinitionVirtualSetting.setMaxUsages(maxUsages);
		cpDefinitionVirtualSetting.setUseSample(useSample);
		cpDefinitionVirtualSetting.setSampleFileEntryId(sampleFileEntryId);
		cpDefinitionVirtualSetting.setSampleUrl(sampleUrl);
		cpDefinitionVirtualSetting.setTermsOfUseRequired(termsOfUseRequired);
		cpDefinitionVirtualSetting.setTermsOfUseContentMap(
			termsOfUseContentMap);
		cpDefinitionVirtualSetting.setTermsOfUseJournalArticleResourcePrimKey(
			termsOfUseJournalArticleResourcePrimKey);
		cpDefinitionVirtualSetting.setOverride(override);
		cpDefinitionVirtualSetting.setExpandoBridgeAttributes(serviceContext);

		cpDefinitionVirtualSettingPersistence.update(
			cpDefinitionVirtualSetting);

		return cpDefinitionVirtualSetting;
	}

	@Override
	public CPDefinitionVirtualSetting addCPDefinitionVirtualSetting(
			String className, long classPK, long fileEntryId, String url,
			int activationStatus, long duration, int maxUsages,
			boolean useSample, long sampleFileEntryId, String sampleUrl,
			boolean termsOfUseRequired,
			Map<Locale, String> termsOfUseContentMap,
			long termsOfUseJournalArticleResourcePrimKey,
			ServiceContext serviceContext)
		throws PortalException {

		return cpDefinitionVirtualSettingLocalService.
			addCPDefinitionVirtualSetting(
				className, classPK, fileEntryId, url, activationStatus,
				duration, maxUsages, useSample, sampleFileEntryId, sampleUrl,
				termsOfUseRequired, termsOfUseContentMap,
				termsOfUseJournalArticleResourcePrimKey, false, serviceContext);
	}

	@Override
	public void cloneCPDefinitionVirtualSetting(
		long cpDefinitionId, long newCPDefinitionId) {

		CPDefinitionVirtualSetting cpDefinitionVirtualSetting =
			cpDefinitionVirtualSettingLocalService.
				fetchCPDefinitionVirtualSetting(
					CPDefinition.class.getName(), cpDefinitionId);

		if (cpDefinitionVirtualSetting != null) {
			CPDefinitionVirtualSetting newCPDefinitionVirtualSetting =
				(CPDefinitionVirtualSetting)cpDefinitionVirtualSetting.clone();

			newCPDefinitionVirtualSetting.setUuid(PortalUUIDUtil.generate());
			newCPDefinitionVirtualSetting.setCPDefinitionVirtualSettingId(
				counterLocalService.increment());
			newCPDefinitionVirtualSetting.setModifiedDate(new Date());
			newCPDefinitionVirtualSetting.setClassPK(newCPDefinitionId);

			cpDefinitionVirtualSettingLocalService.
				addCPDefinitionVirtualSetting(newCPDefinitionVirtualSetting);
		}
	}

	@Override
	public CPDefinitionVirtualSetting deleteCPDefinitionVirtualSetting(
			String className, long classPK)
		throws PortalException {

		long classNameId = classNameLocalService.getClassNameId(className);

		CPDefinitionVirtualSetting cpDefinitionVirtualSetting =
			cpDefinitionVirtualSettingPersistence.fetchByC_C(
				classNameId, classPK);

		if (cpDefinitionVirtualSetting != null) {
			if (className.equals(CPDefinition.class.getName()) &&
				_cpDefinitionLocalService.isVersionable(classPK)) {

				CPDefinition newCPDefinition =
					_cpDefinitionLocalService.copyCPDefinition(classPK);

				cpDefinitionVirtualSetting =
					cpDefinitionVirtualSettingPersistence.findByC_C(
						classNameId, newCPDefinition.getCPDefinitionId());
			}
			else if (className.equals(CPInstance.class.getName())) {
				CPInstance cpInstance = _cpInstanceLocalService.getCPInstance(
					classPK);

				if (_cpDefinitionLocalService.isVersionable(
						cpInstance.getCPDefinitionId())) {

					CPDefinition newCPDefinition =
						_cpDefinitionLocalService.copyCPDefinition(
							cpInstance.getCPDefinitionId());

					CPInstance newCPInstance =
						_cpInstanceLocalService.getCProductInstance(
							newCPDefinition.getCProductId(),
							cpInstance.getCPInstanceUuid());

					cpDefinitionVirtualSetting =
						cpDefinitionVirtualSettingPersistence.findByC_C(
							classNameId, newCPInstance.getCPInstanceId());
				}
			}

			cpDefinitionVirtualSettingPersistence.remove(
				cpDefinitionVirtualSetting);
		}

		return cpDefinitionVirtualSetting;
	}

	@Override
	public CPDefinitionVirtualSetting fetchCPDefinitionVirtualSetting(
		String className, long classPK) {

		long classNameId = classNameLocalService.getClassNameId(className);

		return cpDefinitionVirtualSettingPersistence.fetchByC_C(
			classNameId, classPK);
	}

	@Override
	public CPDefinitionVirtualSetting getCPDefinitionVirtualSetting(
			String className, long classPK)
		throws PortalException {

		long classNameId = classNameLocalService.getClassNameId(className);

		return cpDefinitionVirtualSettingPersistence.fetchByC_C(
			classNameId, classPK);
	}

	@Override
	public CPDefinitionVirtualSetting updateCPDefinitionVirtualSetting(
			long cpDefinitionVirtualSettingId, long fileEntryId, String url,
			int activationStatus, long duration, int maxUsages,
			boolean useSample, long sampleFileEntryId, String sampleUrl,
			boolean termsOfUseRequired,
			Map<Locale, String> termsOfUseContentMap,
			long termsOfUseJournalArticleResourcePrimKey, boolean override,
			ServiceContext serviceContext)
		throws PortalException {

		CPDefinitionVirtualSetting cpDefinitionVirtualSetting =
			cpDefinitionVirtualSettingPersistence.findByPrimaryKey(
				cpDefinitionVirtualSettingId);

		if (Validator.isNotNull(url)) {
			fileEntryId = 0;
		}
		else {
			url = null;
		}

		if (useSample) {
			if (Validator.isNotNull(sampleUrl)) {
				sampleFileEntryId = 0;
			}
			else {
				sampleUrl = null;
			}
		}
		else {
			sampleUrl = null;
			sampleFileEntryId = 0;
		}

		if (termsOfUseRequired) {
			if (termsOfUseJournalArticleResourcePrimKey > 0) {
				termsOfUseContentMap = Collections.emptyMap();
			}
			else {
				termsOfUseJournalArticleResourcePrimKey = 0;
			}
		}
		else {
			termsOfUseContentMap = Collections.emptyMap();
			termsOfUseJournalArticleResourcePrimKey = 0;
		}

		validate(
			fileEntryId, url, useSample, sampleFileEntryId, sampleUrl,
			termsOfUseRequired, termsOfUseContentMap,
			termsOfUseJournalArticleResourcePrimKey);

		long cpDefinitionClassNameId = classNameLocalService.getClassNameId(
			CPDefinition.class);
		long cpInstanceClassNameId = classNameLocalService.getClassNameId(
			CPInstance.class);

		if ((cpDefinitionVirtualSetting.getClassNameId() ==
				cpDefinitionClassNameId) &&
			_cpDefinitionLocalService.isVersionable(
				cpDefinitionVirtualSetting.getClassPK())) {

			CPDefinition newCPDefinition =
				_cpDefinitionLocalService.copyCPDefinition(
					cpDefinitionVirtualSetting.getClassPK());

			cpDefinitionVirtualSetting =
				cpDefinitionVirtualSettingPersistence.findByC_C(
					cpDefinitionVirtualSetting.getClassNameId(),
					newCPDefinition.getCPDefinitionId());
		}
		else if (cpDefinitionVirtualSetting.getClassNameId() ==
					cpInstanceClassNameId) {

			CPInstance cpInstance = _cpInstanceLocalService.getCPInstance(
				cpDefinitionVirtualSetting.getClassPK());

			if (_cpDefinitionLocalService.isVersionable(
					cpInstance.getCPDefinitionId())) {

				CPDefinition newCPDefinition =
					_cpDefinitionLocalService.copyCPDefinition(
						cpInstance.getCPDefinitionId());

				CPInstance newCPInstance =
					_cpInstanceLocalService.getCProductInstance(
						newCPDefinition.getCProductId(),
						cpInstance.getCPInstanceUuid());

				cpDefinitionVirtualSetting =
					cpDefinitionVirtualSettingPersistence.findByC_C(
						cpDefinitionVirtualSetting.getClassNameId(),
						newCPInstance.getCPInstanceId());
			}
		}

		cpDefinitionVirtualSetting.setFileEntryId(fileEntryId);
		cpDefinitionVirtualSetting.setUrl(url);
		cpDefinitionVirtualSetting.setActivationStatus(activationStatus);
		cpDefinitionVirtualSetting.setDuration(duration);
		cpDefinitionVirtualSetting.setMaxUsages(maxUsages);
		cpDefinitionVirtualSetting.setUseSample(useSample);
		cpDefinitionVirtualSetting.setSampleFileEntryId(sampleFileEntryId);
		cpDefinitionVirtualSetting.setSampleUrl(sampleUrl);
		cpDefinitionVirtualSetting.setTermsOfUseRequired(termsOfUseRequired);
		cpDefinitionVirtualSetting.setTermsOfUseContentMap(
			termsOfUseContentMap);
		cpDefinitionVirtualSetting.setTermsOfUseJournalArticleResourcePrimKey(
			termsOfUseJournalArticleResourcePrimKey);
		cpDefinitionVirtualSetting.setOverride(override);
		cpDefinitionVirtualSetting.setExpandoBridgeAttributes(serviceContext);

		cpDefinitionVirtualSettingPersistence.update(
			cpDefinitionVirtualSetting);

		return cpDefinitionVirtualSetting;
	}

	@Override
	public CPDefinitionVirtualSetting updateCPDefinitionVirtualSetting(
			long cpDefinitionVirtualSettingId, long fileEntryId, String url,
			int activationStatus, long duration, int maxUsages,
			boolean useSample, long sampleFileEntryId, String sampleUrl,
			boolean termsOfUseRequired,
			Map<Locale, String> termsOfUseContentMap,
			long termsOfUseJournalArticleResourcePrimKey,
			ServiceContext serviceContext)
		throws PortalException {

		return cpDefinitionVirtualSettingLocalService.
			updateCPDefinitionVirtualSetting(
				cpDefinitionVirtualSettingId, fileEntryId, url,
				activationStatus, duration, maxUsages, useSample,
				sampleFileEntryId, sampleUrl, termsOfUseRequired,
				termsOfUseContentMap, termsOfUseJournalArticleResourcePrimKey,
				false, serviceContext);
	}

	protected void validate(
			long fileEntryId, String url, boolean useSample,
			long sampleFileEntryId, String sampleUrl,
			boolean termsOfUseRequired,
			Map<Locale, String> termsOfUseContentMap,
			long termsOfUseJournalArticleResourcePrimKey)
		throws PortalException {

		if (fileEntryId > 0) {
			try {
				dlAppLocalService.getFileEntry(fileEntryId);
			}
			catch (NoSuchFileEntryException nsfee) {
				throw new CPDefinitionVirtualSettingFileEntryIdException(nsfee);
			}
		}
		else if ((fileEntryId <= 0) && Validator.isNull(url)) {
			throw new CPDefinitionVirtualSettingException();
		}
		else if (Validator.isNull(url)) {
			throw new CPDefinitionVirtualSettingUrlException();
		}

		if (useSample) {
			if (sampleFileEntryId > 0) {
				try {
					dlAppLocalService.getFileEntry(sampleFileEntryId);
				}
				catch (NoSuchFileEntryException nsfee) {
					throw new CPDefinitionVirtualSettingSampleFileEntryIdException(
						nsfee);
				}
			}
			else if ((sampleFileEntryId <= 0) && Validator.isNull(sampleUrl)) {
				throw new CPDefinitionVirtualSettingSampleException();
			}
			else if (Validator.isNull(sampleUrl)) {
				throw new CPDefinitionVirtualSettingSampleUrlException();
			}
		}

		if (termsOfUseRequired) {
			if (termsOfUseJournalArticleResourcePrimKey > 0) {
				JournalArticle journalArticle =
					journalArticleLocalService.fetchLatestArticle(
						termsOfUseJournalArticleResourcePrimKey);

				if (journalArticle == null) {
					throw new CPDefinitionVirtualSettingTermsOfUseArticleResourcePKException();
				}
			}
			else if ((termsOfUseJournalArticleResourcePrimKey <= 0) &&
					 MapUtil.isEmpty(termsOfUseContentMap)) {

				throw new CPDefinitionVirtualSettingTermsOfUseException();
			}
			else {
				boolean empty = true;

				for (Map.Entry<Locale, String> entry :
						termsOfUseContentMap.entrySet()) {

					if (Validator.isNotNull(entry.getValue())) {
						empty = false;
					}
				}

				if (empty) {
					throw new CPDefinitionVirtualSettingTermsOfUseContentException();
				}
			}
		}
	}

	@ServiceReference(type = CPDefinitionLocalService.class)
	private CPDefinitionLocalService _cpDefinitionLocalService;

	@ServiceReference(type = CPInstanceLocalService.class)
	private CPInstanceLocalService _cpInstanceLocalService;

	@ServiceReference(type = CProductLocalService.class)
	private CProductLocalService _cProductLocalService;

}