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

package com.liferay.commerce.bom.internal.model.listener;

import com.liferay.commerce.bom.model.CommerceBOMFolderConstants;
import com.liferay.commerce.bom.service.CommerceBOMDefinitionLocalService;
import com.liferay.commerce.bom.service.CommerceBOMFolderLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.ModelListener;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = ModelListener.class)
public class CompanyModelListener extends BaseModelListener<Company> {

	@Override
	public void onBeforeRemove(Company company) {
		try {
			_commerceBOMDefinitionLocalService.deleteCommerceBOMDefinitions(
				CommerceBOMFolderConstants.DEFAULT_COMMERCE_BOM_FOLDER_ID);

			_commerceBOMFolderLocalService.deleteCommerceBOMFolders(
				company.getCompanyId());
		}
		catch (PortalException pe) {
			if (_log.isWarnEnabled()) {
				_log.warn(pe, pe);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CompanyModelListener.class);

	@Reference
	private CommerceBOMDefinitionLocalService
		_commerceBOMDefinitionLocalService;

	@Reference
	private CommerceBOMFolderLocalService _commerceBOMFolderLocalService;

}