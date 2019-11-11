<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
CommerceBOMAdminDisplayContext commerceBOMAdminDisplayContext = (CommerceBOMAdminDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceBOMFolder commerceBOMFolder = commerceBOMAdminDisplayContext.getCommerceBOMFolder();
long commerceBOMFolderId = commerceBOMAdminDisplayContext.getCommerceBOMFolderId();
%>

<portlet:actionURL name="editCommerceBOMFolder" var="editCommerceBOMFolderActionURL" />

<aui:form action="<%= editCommerceBOMFolderActionURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= (commerceBOMFolder == null) ? Constants.ADD : Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
	<aui:input name="commerceBOMFolderId" type="hidden" value="<%= commerceBOMFolderId %>" />

	<aui:model-context bean="<%= commerceBOMFolder %>" model="<%= CommerceBOMFolder.class %>" />

	<div class="lfr-form-content">
		<aui:fieldset-group markupView="lexicon">
			<aui:fieldset>
				<div class="row">
					<div class="col-md-6">
						<aui:input autoFocus="<%= true %>" name="name" />
					</div>

					<div class="col-md-5">
						<div align="middle">
							<c:if test="<%= commerceBOMFolder != null %>">

								<%
								long logoId = commerceBOMFolder.getLogoId();

								UserFileUploadsConfiguration userFileUploadsConfiguration = commerceBOMAdminDisplayContext.getUserFileUploadsConfiguration();
								%>

								<liferay-ui:logo-selector
									currentLogoURL='<%= themeDisplay.getPathImage() + "/organization_logo?img_id=" + logoId + "&t=" + WebServerServletTokenUtil.getToken(logoId) %>'
									defaultLogo="<%= logoId == 0 %>"
									defaultLogoURL='<%= themeDisplay.getPathImage() + "/organization_logo?img_id=0" %>'
									logoDisplaySelector=".organization-logo"
									maxFileSize="<%= userFileUploadsConfiguration.imageMaxSize() %>"
									tempImageFileName="<%= String.valueOf(themeDisplay.getScopeGroupId()) %>"
								/>
							</c:if>
						</div>
					</div>
				</div>
			</aui:fieldset>

			<aui:fieldset>
				<aui:button-row>
					<aui:button cssClass="btn-lg" type="submit" value="save" />

					<aui:button cssClass="btn-lg" href="<%= backURL %>" type="cancel" />
				</aui:button-row>
			</aui:fieldset>
		</aui:fieldset-group>
	</div>
</aui:form>