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

<%@ include file="/header/init.jsp" %>

<%
Object bean = request.getAttribute("liferay-commerce:header:bean");
Class<?> model = (Class<?>)request.getAttribute("liferay-commerce:header:model");
String title = (String)request.getAttribute("liferay-commerce:header:title");
String spritemap = (String)request.getAttribute("liferay-commerce:header:spritemap");
String version = (String)request.getAttribute("liferay-commerce:header:version");
String previewUrl = (String)request.getAttribute("liferay-commerce:header:previewUrl");
String thumbnailUrl = (String)request.getAttribute("liferay-commerce:header:thumbnailUrl");
List<HeaderButtonModel> headerButtonModels = (List<HeaderButtonModel>)request.getAttribute("liferay-commerce:header:headerButtonModels");
List<DropdownItem> dropdownItems = (List<DropdownItem>)request.getAttribute("liferay-commerce:header:dropdownItems");
String randomNamespace = PortalUtil.generateRandomKey(request, "taglib_header") + StringPool.UNDERLINE;
%>

<div class="bg-white border-bottom container-fluid">
	<div class="container d-flex">
		<div class="align-items-center d-flex flex-grow-1">
			<c:if test="<%= Validator.isNotNull(thumbnailUrl) %>">
				<span class="mr-3 sticker sticker-primary sticker-xl">
					<span class="sticker-overlay">
						<img alt="thumbnail" class="img-fluid" src="<%= thumbnailUrl %>" />
					</span>
				</span>
			</c:if>

			<div class="d-flex mr-3 py-2">
				<div class="pr-3">
					<div class="d-flex">
						<h3 class="mb-0">
							<%= HtmlUtil.escape(title) %>
						</h3>

						<c:if test="<%= Validator.isNotNull(version) %>">
							<span class="badge badge-secondary ml-2">
								<span class="badge-item badge-item-expand">v<%= version %></span>
							</span>
						</c:if>
					</div>

					<c:if test="<%= bean instanceof WorkflowedModel %>">

						<%
						WorkflowedModel workflowedModel = (WorkflowedModel)bean;
						%>

						<aui:workflow-status bean="<%= bean %>" model="<%= model %>" showHelpMessage="<%= false %>" showIcon="<%= false %>" showLabel="<%= false %>" status="<%= workflowedModel.getStatus() %>" />
					</c:if>
				</div>
			</div>
		</div>

		<div class="commerce-header__actions-wrapper align-items-center d-flex">
			<div class="commerce-header__actions px-3 d-none d-xl-flex border-right">
				<c:if test="<%= false %>">

					<%
					String assignedToWrapperId = randomNamespace + "assigned-to";
					%>

					<div id="<%= assignedToWrapperId %>"></div>

					<aui:script require="commerce-frontend-js/js/assign_to/entry.es as AssignTo">
						var assignTo = new AssignTo.default(
							"<%= assignedToWrapperId %>",
							"<%= assignedToWrapperId %>",
							{
								spritemap: "<%= spritemap %>",
								currentAssignee: null
							}
						);
					</aui:script>
				</c:if>

				<c:if test="<%= Validator.isNotNull(headerButtonModels) %>">
					<c:forEach items="<%= headerButtonModels %>" var="headerButtonModel">
						<clay:button
							elementClasses="mr-1"
							label="${headerButtonModel.getLabel()}"
							style="${headerButtonModel.getStyle()}"
							type="${headerButtonModel.getType()}"
						/>
					</c:forEach>
				</c:if>
			</div>

			<div class="commerce-header__actions-secondary pl-3 align-items-center d-flex">
				<clay:dropdown-menu
					buttonType="button"
					dropdownItems="<%= dropdownItems %>"
					icon="ellipsis-v"
				/>

				<c:if test="<%= Validator.isNotNull(previewUrl) %>">
					<clay:link
						elementClasses="btn btn-outline-borderless btn-outline-secondary btn-sm text-primary"
						href="<%= previewUrl %>"
						icon="shortcut"
					/>
				</c:if>
			</div>
		</div>
	</div>
</div>