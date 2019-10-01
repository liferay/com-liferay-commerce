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
CommerceOrderEditDisplayContext commerceOrderEditDisplayContext = (CommerceOrderEditDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<commerce-ui:panel
	bodyClasses="p-0"
	title='<%= LanguageUtil.get(request, "emails") %>'
>
	<liferay-ui:search-container
		id="commerceNotificationQueueEntries"
		searchContainer="<%= commerceOrderEditDisplayContext.getCommerceNotificationQueueEntriesSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.commerce.notification.model.CommerceNotificationQueueEntry"
			escapedModel="<%= true %>"
			keyProperty="commerceNotificationQueueEntryId"
			modelVar="commerceNotificationQueueEntry"
		>

			<%
			PortletURL rowURL = renderResponse.createRenderURL();

			rowURL.setParameter("mvcRenderCommandName", "viewCommerceNotificationQueueEntry");
			rowURL.setParameter("redirect", currentURL);
			rowURL.setParameter("commerceOrderId", String.valueOf(commerceOrderEditDisplayContext.getCommerceOrderId()));
			rowURL.setParameter("commerceNotificationQueueEntryId", String.valueOf(commerceNotificationQueueEntry.getCommerceNotificationQueueEntryId()));
			rowURL.setWindowState(LiferayWindowState.POP_UP);
			%>

			<liferay-ui:search-container-column-image
				src="<%= commerceOrderEditDisplayContext.getUserPortraitSrc(commerceNotificationQueueEntry.getUser()) %>"
			/>

			<liferay-ui:search-container-column-text
				colspan="<%= 2 %>"
			>
				<h5 class="mb-0"><%= commerceNotificationQueueEntry.getFromName() %></h5>

				<small><%= commerceNotificationQueueEntry.getFrom() %></small>

				<%
				Map<String, String> data = new HashMap<>();

				data.put("panel-url", rowURL.toString());
				data.put("target", renderResponse.getNamespace() + "sidePanel");
				%>

				<h4>
					<clay:link
						data="<%= data %>"
						href="<%= rowURL.toString() %>"
						label="<%= HtmlUtil.escape(commerceNotificationQueueEntry.getSubject()) %>"
					/>
				</h4>

				<div>
					<%= HtmlUtil.escape(commerceNotificationQueueEntry.getBody()) %>
				</div>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text>
				<div class="mb-3 row">
					<div class="col-auto">
						<clay:label
							label="<%= commerceNotificationQueueEntry.getCommerceNotificationTemplateType() %>"
							style="success"
						/>
					</div>

					<div class="col-auto">

						<%
						Date sentDate = commerceNotificationQueueEntry.getSentDate();

						String sentDateDescription = StringPool.BLANK;

						if (sentDate != null) {
							sentDateDescription = LanguageUtil.getTimeDescription(request, System.currentTimeMillis() - sentDate.getTime(), true);
						}
						%>

						<small><%= sentDateDescription %></small>
					</div>
				</div>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			displayStyle="descriptive"
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</commerce-ui:panel>

<div id="<portlet:namespace />side-panel-root"></div>
<div id="<portlet:namespace />side-panel-wrapper"></div>

<aui:script require="commerce-frontend-js/js/side_panel/entry.es as SidePanel">
	new SidePanel.default(
		"<portlet:namespace />sidePanel",
		"<portlet:namespace />side-panel-root",
		{
			size: "lg",
			spritemap: "<%= themeDisplay.getPathThemeImages() + "/clay/icons.svg" %>",
			portalWrapperId: "<portlet:namespace />side-panel-wrapper",
			topAnchor: document.getElementById('commerce-admin-header')
		}
	);

	Promise.all(
		[
			Liferay.componentReady('<portlet:namespace />commerceNotificationQueueEntries'),
			Liferay.componentReady('<portlet:namespace />sidePanel')
		]
	).then(function(
		[
			table,
			panel
		]
		) {
			document.getElementById('<portlet:namespace />commerceNotificationQueueEntries')
				.addEventListener(
					'click',
					function(e) {
						e.preventDefault();

						if (e.target.dataset.target === '<portlet:namespace />sidePanel') {
							panel.open(e.target.dataset.panelUrl);
						}
					}
				)
		}
	)
</aui:script>