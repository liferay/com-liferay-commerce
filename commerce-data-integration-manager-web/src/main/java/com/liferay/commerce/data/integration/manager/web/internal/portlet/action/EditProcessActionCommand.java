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

package com.liferay.commerce.data.integration.manager.web.internal.portlet.action;

import com.liferay.commerce.data.integration.manager.service.ProcessService;
import com.liferay.commerce.data.integration.manager.web.internal.portlet.constants.DataIntegrationWebPortletKeys;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author guywandji
 */
@Component(
	property = {
		"javax.portlet.name=" + DataIntegrationWebPortletKeys.DATA_INTEGRATION_WEB,
		"mvc.command.name=editProcess"
	},
	service = MVCActionCommand.class
)
public class EditProcessActionCommand extends BaseMVCActionCommand {

	protected void deleteProcess(ActionRequest actionRequest) {
		ThemeDisplay themeDisplay = (ThemeDisplay)
			actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		long[] deleteProcessIds = ParamUtil.getLongValues(
			actionRequest, "deleteProcessIds");

		try {
			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				actionRequest);

			if ((deleteProcessIds != null) && (deleteProcessIds.length > 0)) {
				for (long processId : deleteProcessIds) {
					_processService.deleteProcess(
						themeDisplay.getUserId(), processId, serviceContext);
				}
			}
			else {
				long processId = ParamUtil.getLong(actionRequest, "processId");

				_processService.deleteProcess(
					themeDisplay.getUserId(), processId, serviceContext);
			}
		}
		catch (PortalException pe) {
			pe.printStackTrace();

			SessionErrors.add(actionRequest, "errorDeletingProcess");
		}
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		if (Constants.DELETE.equals(cmd)) {
			deleteProcess(actionRequest);
		}
		else {
			editProcess(actionRequest);
		}
	}

	protected void editProcess(ActionRequest actionRequest) {
		String className = ParamUtil.getString(actionRequest, "className");
		String name = ParamUtil.getString(actionRequest, "name");
		long processId = ParamUtil.getLong(actionRequest, "processId", 0L);
		String processType = ParamUtil.getString(actionRequest, "processType");
		String version = ParamUtil.getString(actionRequest, "version");

		DLFileEntry archiveFileEntry = null;
		DLFileEntry contextFileEntry = null;

		try {
			UploadPortletRequest uploadPortletRequest =
				_portal.getUploadPortletRequest(actionRequest);

			contextFileEntry = _uploadProcessFileEntryActionHelper.upload(
				uploadPortletRequest, "contextProperties");

			long contextFileEntryId = 0L;

			if (contextFileEntry != null) {
				contextFileEntryId = contextFileEntry.getFileEntryId();
			}

			archiveFileEntry = _uploadProcessFileEntryActionHelper.upload(
				uploadPortletRequest, "srcArchive");

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				actionRequest);

			if (processId > 0) {
				_processService.updateProcess(
					processId, name, className, processType, version,
					contextFileEntryId, archiveFileEntry.getFileEntryId(),
					serviceContext);
			}
			else {
				_processService.addProcess(
					name, className, processType, version, contextFileEntryId,
					archiveFileEntry.getFileEntryId(), serviceContext);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Reference
	private Portal _portal;

	@Reference
	private ProcessService _processService;

	@Reference
	private UploadProcessFileEntryActionHelper
		_uploadProcessFileEntryActionHelper;

}