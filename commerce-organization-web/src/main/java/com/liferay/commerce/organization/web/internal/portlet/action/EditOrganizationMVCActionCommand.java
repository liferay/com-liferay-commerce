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

package com.liferay.commerce.organization.web.internal.portlet.action;

import com.liferay.commerce.organization.service.CommerceOrganizationService;
import com.liferay.commerce.organization.web.internal.constants.CommerceOrganizationPortletKeys;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.portal.kernel.exception.AddressCityException;
import com.liferay.portal.kernel.exception.AddressStreetException;
import com.liferay.portal.kernel.exception.AddressZipException;
import com.liferay.portal.kernel.exception.DuplicateOrganizationException;
import com.liferay.portal.kernel.exception.EmailAddressException;
import com.liferay.portal.kernel.exception.NoSuchCountryException;
import com.liferay.portal.kernel.exception.NoSuchListTypeException;
import com.liferay.portal.kernel.exception.NoSuchOrganizationException;
import com.liferay.portal.kernel.exception.NoSuchRegionException;
import com.liferay.portal.kernel.exception.OrganizationNameException;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + CommerceOrganizationPortletKeys.COMMERCE_ORGANIZATION,
		"mvc.command.name=editOrganization"
	},
	service = MVCActionCommand.class
)
public class EditOrganizationMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			if (cmd.equals(Constants.ADD) || cmd.equals(Constants.UPDATE)) {
				updateOrganization(actionRequest);
			}
		}
		catch (Exception e) {
			if (e instanceof NoSuchOrganizationException ||
				e instanceof PrincipalException) {

				SessionErrors.add(actionRequest, e.getClass());

				actionResponse.setRenderParameter("mvcPath", "/error.jsp");
			}
			else if (e instanceof AddressCityException ||
					 e instanceof AddressStreetException ||
					 e instanceof AddressZipException ||
					 e instanceof DuplicateOrganizationException ||
					 e instanceof EmailAddressException ||
					 e instanceof NoSuchCountryException ||
					 e instanceof NoSuchListTypeException ||
					 e instanceof NoSuchRegionException ||
					 e instanceof OrganizationNameException) {

				hideDefaultErrorMessage(actionRequest);

				SessionErrors.add(actionRequest, e.getClass());
			}
			else {
				throw e;
			}
		}

		hideDefaultSuccessMessage(actionRequest);
	}

	protected void updateOrganization(ActionRequest actionRequest)
		throws Exception {

		long organizationId = ParamUtil.getLong(
			actionRequest, "organizationId");

		Organization organization =
			_commerceOrganizationService.getOrganization(organizationId);

		String name = ParamUtil.getString(actionRequest, "name");
		long emailAddressId = ParamUtil.getLong(
			actionRequest, "emailAddressId");
		String address = ParamUtil.getString(actionRequest, "address");
		long addressId = ParamUtil.getLong(actionRequest, "addressId");
		String street1 = ParamUtil.getString(actionRequest, "street1");
		String street2 = ParamUtil.getString(actionRequest, "street2");
		String street3 = ParamUtil.getString(actionRequest, "street3");
		String city = ParamUtil.getString(actionRequest, "city");
		String zip = ParamUtil.getString(actionRequest, "zip");
		long regionId = ParamUtil.getLong(actionRequest, "regionId");
		long countryId = ParamUtil.getLong(actionRequest, "countryId");
		boolean deleteLogo = ParamUtil.getBoolean(actionRequest, "deleteLogo");

		byte[] logoBytes = null;

		long fileEntryId = ParamUtil.getLong(actionRequest, "fileEntryId");

		if (fileEntryId > 0) {
			FileEntry fileEntry = _dlAppLocalService.getFileEntry(fileEntryId);

			logoBytes = FileUtil.getBytes(fileEntry.getContentStream());
		}

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			Organization.class.getName(), actionRequest);

		serviceContext.setScopeGroupId(organization.getGroupId());

		_commerceOrganizationService.updateOrganization(
			organizationId, name, emailAddressId, address, addressId, street1,
			street2, street3, city, zip, regionId, countryId, !deleteLogo,
			logoBytes, serviceContext);
	}

	@Reference
	private CommerceOrganizationService _commerceOrganizationService;

	@Reference
	private DLAppLocalService _dlAppLocalService;

}