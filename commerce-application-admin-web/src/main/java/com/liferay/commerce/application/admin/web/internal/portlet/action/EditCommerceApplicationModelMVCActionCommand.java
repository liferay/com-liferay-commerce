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

package com.liferay.commerce.application.admin.web.internal.portlet.action;

import com.liferay.commerce.application.constants.CommerceApplicationPortletKeys;
import com.liferay.commerce.application.exception.NoSuchApplicationModelException;
import com.liferay.commerce.application.model.CommerceApplicationModel;
import com.liferay.commerce.application.service.CommerceApplicationModelService;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;

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
		"javax.portlet.name=" + CommerceApplicationPortletKeys.COMMERCE_APPLICATION_ADMIN,
		"mvc.command.name=editCommerceApplicationModel"
	},
	service = MVCActionCommand.class
)
public class EditCommerceApplicationModelMVCActionCommand
	extends BaseMVCActionCommand {

	protected void deleteCommerceApplicationModels(ActionRequest actionRequest)
		throws Exception {

		long[] deleteCommerceApplicationModelIds = null;

		long commerceApplicationModelId = ParamUtil.getLong(
			actionRequest, "commerceApplicationModelId");

		if (commerceApplicationModelId > 0) {
			deleteCommerceApplicationModelIds = new long[] {
				commerceApplicationModelId
			};
		}
		else {
			deleteCommerceApplicationModelIds = StringUtil.split(
				ParamUtil.getString(
					actionRequest, "deleteCommerceApplicationModelIds"),
				0L);
		}

		for (long deleteCommerceApplicationModelId :
				deleteCommerceApplicationModelIds) {

			_commerceApplicationModelService.deleteCommerceApplicationModel(
				deleteCommerceApplicationModelId);
		}
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			if (cmd.equals(Constants.ADD) || cmd.equals(Constants.UPDATE)) {
				updateCommerceApplicationModel(actionRequest);
			}
			else if (cmd.equals(Constants.DELETE)) {
				deleteCommerceApplicationModels(actionRequest);
			}
		}
		catch (Exception e) {
			if (e instanceof NoSuchApplicationModelException ||
				e instanceof PrincipalException) {

				SessionErrors.add(actionRequest, e.getClass());

				actionResponse.setRenderParameter("mvcPath", "/error.jsp");
			}
			else {
				_log.error(e, e);
			}
		}

		hideDefaultSuccessMessage(actionRequest);
	}

	protected CommerceApplicationModel updateCommerceApplicationModel(
			ActionRequest actionRequest)
		throws Exception {

		long commerceApplicationModelId = ParamUtil.getLong(
			actionRequest, "commerceApplicationModelId");

		long commerceApplicationBrandId = ParamUtil.getLong(
			actionRequest, "commerceApplicationBrandId");
		String name = ParamUtil.getString(actionRequest, "name");
		String year = ParamUtil.getString(actionRequest, "year");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			CommerceApplicationModel.class.getName(), actionRequest);

		CommerceApplicationModel commerceApplicationModel;

		if (commerceApplicationModelId > 0) {
			commerceApplicationModel =
				_commerceApplicationModelService.updateCommerceApplicationModel(
					commerceApplicationModelId, name, year);
		}
		else {
			commerceApplicationModel =
				_commerceApplicationModelService.addCommerceApplicationModel(
					serviceContext.getUserId(), commerceApplicationBrandId,
					name, year);
		}

		return commerceApplicationModel;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EditCommerceApplicationModelMVCActionCommand.class);

	@Reference
	private CommerceApplicationModelService _commerceApplicationModelService;

	@Reference
	private DLAppLocalService _dlAppLocalService;

}