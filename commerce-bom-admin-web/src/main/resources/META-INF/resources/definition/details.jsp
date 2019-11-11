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

CommerceBOMDefinition commerceBOMDefinition = commerceBOMAdminDisplayContext.getCommerceBOMDefinition();

CPAttachmentFileEntry cpAttachmentFileEntry = commerceBOMAdminDisplayContext.getCPAttachmentFileEntry();

long fileEntryId = BeanParamUtil.getLong(cpAttachmentFileEntry, request, "fileEntryId");
%>

<portlet:actionURL name="editCommerceBOMDefinition" var="editCommerceBOMDefinitionActionURL" />

<div class="container-fluid-1280 entry-body">
	<aui:form action="<%= editCommerceBOMDefinitionActionURL %>" method="post" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= (commerceBOMDefinition == null) ? Constants.ADD : Constants.UPDATE %>" />
		<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
		<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
		<aui:input name="commerceBOMDefinitionId" type="hidden" value="<%= commerceBOMAdminDisplayContext.getCommerceBOMDefinitionId() %>" />
		<aui:input name="commerceBOMFolderId" type="hidden" value="<%= commerceBOMAdminDisplayContext.getCommerceBOMFolderId() %>" />

		<aui:model-context bean="<%= commerceBOMDefinition %>" model="<%= CommerceBOMDefinition.class %>" />

		<liferay-ui:error exception="<%= DuplicateCPAttachmentFileEntryException.class %>" message="that-attachment-is-already-in-use" />
		<liferay-ui:error exception="<%= NoSuchFileEntryException.class %>" message="please-select-an-existing-file" />

		<aui:fieldset-group markupView="lexicon">
			<aui:fieldset>
				<portlet:actionURL name="uploadTempAttachment" var="uploadCoverImageURL" />

				<div class="lfr-attachment-cover-image-selector">
					<liferay-item-selector:image-selector
						draggableImage="vertical"
						fileEntryId="<%= fileEntryId %>"
						itemSelectorEventName="addCPAttachmentFileEntry"
						itemSelectorURL="<%= commerceBOMAdminDisplayContext.getItemSelectorUrl() %>"
						maxFileSize="<%= commerceBOMAdminDisplayContext.getImageMaxSize() %>"
						paramName="fileEntry"
						uploadURL="<%= uploadCoverImageURL %>"
						validExtensions='<%= StringUtil.merge(commerceBOMAdminDisplayContext.getImageExtensions(), ", ") %>'
					/>
				</div>

				<aui:input name="name" />
			</aui:fieldset>

			<aui:fieldset>
				<aui:button-row>
					<aui:button cssClass="btn-lg" type="submit" value="save" />

					<aui:button cssClass="btn-lg" href="<%= backURL %>" type="cancel" />
				</aui:button-row>
			</aui:fieldset>
		</aui:fieldset-group>
	</aui:form>
</div>