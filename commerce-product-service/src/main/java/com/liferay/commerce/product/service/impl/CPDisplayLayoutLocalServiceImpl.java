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

import com.liferay.commerce.product.exception.CPDisplayLayoutEntryException;
import com.liferay.commerce.product.exception.CPDisplayLayoutLayoutUuidException;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPDisplayLayout;
import com.liferay.commerce.product.service.base.CPDisplayLayoutLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
public class CPDisplayLayoutLocalServiceImpl
	extends CPDisplayLayoutLocalServiceBaseImpl {

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CPDisplayLayout addCPDisplayLayout(
			Class<?> clazz, long classPK, String layoutUuid,
			ServiceContext serviceContext)
		throws PortalException {

		validate(classPK, layoutUuid);

		long classNameId = classNameLocalService.getClassNameId(clazz);

		CPDisplayLayout oldCPDisplayLayout =
			cpDisplayLayoutPersistence.fetchByC_C(classNameId, classPK);

		if ((clazz == CPDefinition.class) &&
			cpDefinitionLocalService.isVersionable(classPK)) {

			try {
				CPDefinition newCPDefinition =
					cpDefinitionLocalService.copyCPDefinition(classPK);

				classPK = newCPDefinition.getCPDefinitionId();
			}
			catch (PortalException pe) {
				throw new SystemException(pe);
			}

			oldCPDisplayLayout = cpDisplayLayoutPersistence.fetchByC_C(
				classNameId, classPK);
		}

		if (oldCPDisplayLayout != null) {
			oldCPDisplayLayout.setLayoutUuid(layoutUuid);

			return cpDisplayLayoutPersistence.update(oldCPDisplayLayout);
		}

		long cpDisplayLayoutId = counterLocalService.increment();

		CPDisplayLayout cpDisplayLayout = createCPDisplayLayout(
			cpDisplayLayoutId);

		cpDisplayLayout.setGroupId(serviceContext.getScopeGroupId());
		cpDisplayLayout.setCompanyId(serviceContext.getCompanyId());
		cpDisplayLayout.setClassNameId(classNameId);
		cpDisplayLayout.setClassPK(classPK);
		cpDisplayLayout.setLayoutUuid(layoutUuid);

		return cpDisplayLayoutPersistence.update(cpDisplayLayout);
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	public CPDisplayLayout deleteCPDisplayLayout(Class<?> clazz, long classPK) {
		long classNameId = classNameLocalService.getClassNameId(clazz);

		CPDisplayLayout cpDisplayLayout = cpDisplayLayoutPersistence.fetchByC_C(
			classNameId, classPK);

		if (cpDisplayLayout != null) {
			if ((clazz == CPDefinition.class) &&
				cpDefinitionLocalService.isVersionable(classPK)) {

				try {
					CPDefinition newCPDefinition =
						cpDefinitionLocalService.copyCPDefinition(classPK);

					cpDisplayLayout = cpDisplayLayoutPersistence.findByC_C(
						classNameId, newCPDefinition.getCPDefinitionId());
				}
				catch (PortalException pe) {
					throw new SystemException(pe);
				}
			}

			return cpDisplayLayoutPersistence.remove(cpDisplayLayout);
		}

		return null;
	}

	@Override
	public void deleteCPDisplayLayoutByGroupIdAndLayoutUuid(
		long groupId, String layoutUuid) {

		cpDisplayLayoutPersistence.removeByG_L(groupId, layoutUuid);
	}

	@Override
	public CPDisplayLayout fetchCPDisplayLayout(Class<?> clazz, long classPK) {
		return cpDisplayLayoutPersistence.fetchByC_C(
			classNameLocalService.getClassNameId(clazz), classPK);
	}

	@Override
	public List<CPDisplayLayout> fetchCPDisplayLayoutByGroupIdAndLayoutUuid(
		long groupId, String layoutUuid, int start, int end) {

		return cpDisplayLayoutPersistence.findByG_L(
			groupId, layoutUuid, start, end);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CPDisplayLayout updateCPDisplayLayout(
			long cpDisplayLayoutId, String layoutUuid)
		throws PortalException {

		CPDisplayLayout cpDisplayLayout =
			cpDisplayLayoutPersistence.findByPrimaryKey(cpDisplayLayoutId);

		validate(cpDisplayLayout.getClassPK(), layoutUuid);

		cpDisplayLayout.setLayoutUuid(layoutUuid);

		return cpDisplayLayoutPersistence.update(cpDisplayLayout);
	}

	protected void validate(long classPK, String layoutUuid)
		throws PortalException {

		if (classPK <= 0) {
			throw new CPDisplayLayoutEntryException();
		}

		if (Validator.isNull(layoutUuid)) {
			throw new CPDisplayLayoutLayoutUuidException();
		}
	}

}