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
CommerceDataIntegrationProcess commerceDataIntegrationProcess = (CommerceDataIntegrationProcess)request.getAttribute(CommerceDataIntegrationWebKeys.COMMERCE_DATA_INTEGRATION_PROCESS);
TalendProcessTypeHelper talendProcessTypeHelper = (TalendProcessTypeHelper)request.getAttribute("talendProcessTypeHelper");
%>

<liferay-portlet:actionURL name="editTalendCommerceDataIntegrationProcess" portletName="<%= CommerceDataIntegrationPortletKeys.COMMERCE_DATA_INTEGRATION %>" var="editTalendCommerceDataIntegrationProcessActionURL" />

<div class="closed container-fluid-1280" id="<portlet:namespace />editCommerceDataIntegrationProcessId">
	<div class="container main-content-body sheet">
		<aui:form action="<%= editTalendCommerceDataIntegrationProcessActionURL %>" cssClass="container-fluid-1280" enctype="multipart/form-data" method="post" name="fm">
			<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
			<aui:input name="commerceDataIntegrationProcessId" type="hidden" value="<%= String.valueOf(commerceDataIntegrationProcess.getCommerceDataIntegrationProcessId()) %>" />

			<aui:model-context bean="<%= commerceDataIntegrationProcess %>" model="<%= CommerceDataIntegrationProcess.class %>" />

			<%
			FileEntry fileEntry = talendProcessTypeHelper.getFileEntry(commerceDataIntegrationProcess.getCommerceDataIntegrationProcessId());
			%>

			<p class="<%= (fileEntry != null) ? "text-default" : "hide text-default" %>" id="<portlet:namespace />fileEntryName">
				<span id="<portlet:namespace />fileEntryRemove">
					<liferay-ui:icon
						icon="times"
						markupView="lexicon"
						message="remove"
					/>
				</span>
				<span>
					<%= (fileEntry != null) ? fileEntry.getFileName() : StringPool.BLANK %>
				</span>
			</p>

			<c:if test="<%= (commerceDataIntegrationProcess == null) || !commerceDataIntegrationProcess.isSystem() %>">
				<div class="<%= (fileEntry != null) ? "hide" : StringPool.BLANK %>" id="<portlet:namespace />fileEntry">
					<aui:input name="srcArchive" required="<%= true %>" type="file" />
				</div>
			</c:if>

			<aui:button-row>
				<aui:button cssClass="btn-lg" type="submit" value="save" />

				<aui:button cssClass="btn-lg" href="<%= currentURL %>" type="cancel" />
			</aui:button-row>
		</aui:form>
	</div>
</div>

<aui:script>
	$('#<portlet:namespace />fileEntryRemove').on(
		'click',
		function(event) {
			event.preventDefault();

			$('#<portlet:namespace />fileEntry').removeClass('hide');

			$('#<portlet:namespace />fileEntryName').addClass('hide');
		}
	);
</aui:script>