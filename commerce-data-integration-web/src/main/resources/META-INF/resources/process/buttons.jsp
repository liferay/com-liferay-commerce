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
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

CommerceDataIntegrationProcess commerceDataIntegrationProcess = (CommerceDataIntegrationProcess)row.getObject();

String runNowButton = "runNowButton" + row.getRowId();
%>

<span aria-hidden="true" class="<%= "hide icon-spinner icon-spin commerce-data-integration-check-row-icon-spinner" + row.getRowId() %>"></span>

<aui:button cssClass="btn-lg" name="<%= runNowButton %>" type="cancel" value="run-now" />

<aui:script use="aui-io-request,aui-parse-content,liferay-notification">
	A.one('#<portlet:namespace /><%= runNowButton %>').on(
		'click',
		function(event) {
			var data = {
				'<portlet:namespace/><%= Constants.CMD %>': 'runProcess',
				'<portlet:namespace/>commerceDataIntegrationProcessId': '<%= commerceDataIntegrationProcess.getCommerceDataIntegrationProcessId() %>'
			};

			this.attr('disabled', true);

			var iconSpinnerContainer = A.one('<%= ".commerce-data-integration-check-row-icon-spinner" + row.getRowId() %>');

			iconSpinnerContainer.removeClass('hide');

			A.io.request(
				'<liferay-portlet:actionURL name="editCommerceDataIntegrationProcess" portletName="<%= portletDisplay.getPortletName() %>" />',
				{
					data: data,
					on: {
						success: function(event, id, obj) {
							var response = JSON.parse(obj.response);

							if (response.success) {
								iconSpinnerContainer.addClass('hide');
							}
							else {
								A.one('#<portlet:namespace /><%= runNowButton %>').attr('disabled', false);

								iconSpinnerContainer.addClass('hide');

								new Liferay.Notification(
									{
										closeable: true,
										delay: {
											hide: 5000,
											show: 0
										},
										duration: 500,
										message: '<liferay-ui:message key="an-unexpected-error-occurred" />',
										render: true,
										title: '<liferay-ui:message key="danger" />',
										type: 'danger'
									}
								);
							}
						}
					}
				}
			);
		}
	);
</aui:script>