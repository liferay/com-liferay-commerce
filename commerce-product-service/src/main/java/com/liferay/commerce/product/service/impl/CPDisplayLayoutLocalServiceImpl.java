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

import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPDisplayLayout;
import com.liferay.commerce.product.service.base.CPDisplayLayoutLocalServiceBaseImpl;
import com.liferay.portal.kernel.service.ServiceContext;

/**
 * @author Marco Leo
 */
public class CPDisplayLayoutLocalServiceImpl
	extends CPDisplayLayoutLocalServiceBaseImpl {

	@Override
	public CPDisplayLayout addCPDisplayLayout(
		Class<?> clazz, long classPK, String layoutUuid,
		ServiceContext serviceContext) {

		long classNameId = classNameLocalService.getClassNameId(clazz);

		CPDisplayLayout oldCPDisplayLayout =
			cpDisplayLayoutPersistence.fetchByC_C(classNameId, classPK);

		if (oldCPDisplayLayout != null) {
			if ((clazz == CPDefinition.class) &&
				cpDefinitionLocalService.isPublishedCPDefinition(classPK)) {

				CPDefinition newCPDefinition =
					cpDefinitionLocalService.copyCPDefinition(classPK);

				cProductLocalService.updatePublishedDefinitionId(
					newCPDefinition.getCProductId(),
					newCPDefinition.getCPDefinitionId());

				oldCPDisplayLayout = cpDisplayLayoutPersistence.fetchByC_C(
					classNameId, newCPDefinition.getCPDefinitionId());
			}

			oldCPDisplayLayout.setLayoutUuid(layoutUuid);

			return cpDisplayLayoutPersistence.update(oldCPDisplayLayout);
		}

		long cpDisplayLayoutId = counterLocalService.increment();

		CPDisplayLayout cpDisplayLayout = createCPDisplayLayout(
			cpDisplayLayoutId);

		if ((clazz == CPDefinition.class) &&
			cpDefinitionLocalService.isPublishedCPDefinition(classPK)) {

			CPDefinition newCPDefinition =
				cpDefinitionLocalService.copyCPDefinition(classPK);

			cProductLocalService.updatePublishedDefinitionId(
				newCPDefinition.getCProductId(),
				newCPDefinition.getCPDefinitionId());

			classPK = newCPDefinition.getCPDefinitionId();
		}

		cpDisplayLayout.setGroupId(serviceContext.getScopeGroupId());
		cpDisplayLayout.setCompanyId(serviceContext.getCompanyId());
		cpDisplayLayout.setClassNameId(classNameId);
		cpDisplayLayout.setClassPK(classPK);
		cpDisplayLayout.setLayoutUuid(layoutUuid);

		return cpDisplayLayoutPersistence.update(cpDisplayLayout);
	}

	@Override
	public void deleteCPDisplayLayout(Class<?> clazz, long classPK) {
		long classNameId = classNameLocalService.getClassNameId(clazz);

		CPDisplayLayout cpDisplayLayout = cpDisplayLayoutPersistence.fetchByC_C(
			classNameId, classPK);

		if (cpDisplayLayout != null) {
			if ((clazz == CPDefinition.class) &&
				cpDefinitionLocalService.isPublishedCPDefinition(classPK)) {

				CPDefinition newCPDefinition =
					cpDefinitionLocalService.copyCPDefinition(classPK);

				cProductLocalService.updatePublishedDefinitionId(
					newCPDefinition.getCProductId(),
					newCPDefinition.getCPDefinitionId());

				cpDisplayLayout = cpDisplayLayoutPersistence.fetchByC_C(
					classNameId, newCPDefinition.getCPDefinitionId());
			}

			cpDisplayLayoutPersistence.remove(cpDisplayLayout);
		}
	}

	@Override
	public CPDisplayLayout fetchCPDisplayLayout(Class<?> clazz, long classPK) {
		long classNameId = classNameLocalService.getClassNameId(clazz);

		return cpDisplayLayoutPersistence.fetchByC_C(classNameId, classPK);
	}

}