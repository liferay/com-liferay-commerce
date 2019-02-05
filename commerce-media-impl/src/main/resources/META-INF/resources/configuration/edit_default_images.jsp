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
CommerceMediaDefaultImageDisplayContext commerceMediaDefaultImageDisplayContext = (CommerceMediaDefaultImageDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

FileEntry fileEntry = commerceMediaDefaultImageDisplayContext.getDefaultFileEntry();

long fileEntryId = BeanParamUtil.getLong(fileEntry, request, "fileEntryId");
%>

<div class="container-fluid-1280 mt-4 sheet">
	<liferay-ui:error-marker
		key="<%= WebKeys.ERROR_SECTION %>"
		value="details"
	/>

	<liferay-ui:error exception="<%= NoSuchFileEntryException.class %>" message="please-select-an-existing-file" />

	<aui:model-context bean="<%= fileEntry %>" model="<%= FileEntry.class %>" />

	<portlet:actionURL name="editCommerceMediaDefaultImage" var="editCommerceMediaDefaultImageActionURL" />

	<aui:form action="<%= editCommerceMediaDefaultImageActionURL %>" method="post" name="fm">
		<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />

		<h1>
			<liferay-ui:message key="default-catalog-image" />

			<liferay-ui:icon-help message="default-catalog-image-help" />
		</h1>

		<aui:fieldset>
			<div class="lfr-attachment-cover-image-selector">
				<portlet:actionURL name="uploadCommerceMediaDefaultImage" var="uploadCommerceMediaDefaultImageActionURL" />

				<liferay-item-selector:image-selector
					draggableImage="vertical"
					fileEntryId="<%= fileEntryId %>"
					itemSelectorEventName="addFileEntry"
					itemSelectorURL="<%= commerceMediaDefaultImageDisplayContext.getImageItemSelectorUrl() %>"
					maxFileSize="<%= commerceMediaDefaultImageDisplayContext.getImageMaxSize() %>"
					paramName="fileEntry"
					uploadURL="<%= uploadCommerceMediaDefaultImageActionURL %>"
					validExtensions="<%= StringUtil.merge(commerceMediaDefaultImageDisplayContext.getImageExtensions(), StringPool.COMMA_AND_SPACE) %>"
				/>
			</div>
		</aui:fieldset>

		<aui:button-row>
			<aui:button cssClass="btn-lg" type="submit" value="save" />
		</aui:button-row>
	</aui:form>
</div>