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

<div class="bg-white commerce-header<%= fullWidth ? " container-fluid" : StringPool.BLANK %><%= Validator.isNotNull(wrapperCssClasses) ? StringPool.SPACE + wrapperCssClasses : StringPool.BLANK %>">
	<div class="container d-flex <%= Validator.isNotNull(cssClasses) ? StringPool.SPACE + cssClasses : StringPool.BLANK %>">
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

		<div class="align-items-center d-flex">
			<div class="border-right d-none d-xl-flex px-3">
				<c:if test="<%= false %>">

					<%
					String assignedToWrapperId = randomNamespace + "assigner";
					%>

					<div id="<%= assignedToWrapperId %>"></div>

					<aui:script require="commerce-frontend-js/components/assigner/entry.es as assigner">
						assigner.default(
							"<%= assignedToWrapperId %>",
							"<%= assignedToWrapperId %>",
							{
								spritemap: "<%= spritemap %>",
								currentAssignee: null
							}
						);
					</aui:script>
				</c:if>

				<c:if test="<%= headerActionModels != null %>">

					<%
					for (HeaderActionModel headerActionModel : headerActionModels) {
					%>

						<clay:link
							buttonStyle="<%= headerActionModel.getStyle() %>"
							elementClasses="mr-1"
							href="<%= headerActionModel.getHref() %>"
							id="<%= headerActionModel.getId() %>"
							label="<%= headerActionModel.getLabel() %>"
						/>

					<%
					}
					%>

				</c:if>
			</div>

			<div class="align-items-center d-flex pl-3">
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