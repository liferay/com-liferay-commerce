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
CPSpecificationOptionDisplayContext cpSpecificationOptionDisplayContext = (CPSpecificationOptionDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

String displayStyle = cpSpecificationOptionDisplayContext.getDisplayStyle();

PortletURL portletURL = cpSpecificationOptionDisplayContext.getPortletURL();

portletURL.setParameter("searchContainerId", "cpSpecificationOptions");

request.setAttribute("view.jsp-portletURL", portletURL);

renderResponse.setTitle(LanguageUtil.get(request, "catalog"));
%>

<clay:navigation-bar
	inverted="<%= true %>"
	navigationItems="<%= CPNavigationItemRegistryUtil.getNavigationItems(renderRequest) %>"
/>

<%@ include file="/navbar_specifications.jspf" %>

<liferay-frontend:management-bar
	includeCheckBox="<%= true %>"
	searchContainerId="cpSpecificationOptions"
>
	<liferay-frontend:management-bar-buttons>
		<c:if test="<%= cpSpecificationOptionDisplayContext.isShowInfoPanel() %>">
			<liferay-frontend:management-bar-sidenav-toggler-button
				icon="info-circle"
				label="info"
			/>
		</c:if>

		<liferay-frontend:management-bar-display-buttons
			displayViews='<%= new String[] {"list"} %>'
			portletURL="<%= cpSpecificationOptionDisplayContext.getPortletURL() %>"
			selectedDisplayStyle="<%= displayStyle %>"
		/>

		<c:if test="<%= PortalPermissionUtil.contains(permissionChecker, CPActionKeys.ADD_COMMERCE_PRODUCT_OPTION_CATEGORY) %>">
			<liferay-portlet:renderURL var="addProductSpecificationOptionURL">
				<portlet:param name="mvcRenderCommandName" value="editProductSpecificationOption" />
			</liferay-portlet:renderURL>

			<liferay-frontend:add-menu
				inline="<%= true %>"
			>
				<liferay-frontend:add-menu-item
					title='<%= LanguageUtil.get(request, "add-specification-label") %>'
					url="<%= addProductSpecificationOptionURL.toString() %>"
				/>
			</liferay-frontend:add-menu>
		</c:if>
	</liferay-frontend:management-bar-buttons>

	<liferay-frontend:management-bar-filters>
		<liferay-frontend:management-bar-navigation
			label="use-in-faceted-navigation"
			navigationKeys='<%= new String[] {"all", "yes", "no"} %>'
			portletURL="<%= cpSpecificationOptionDisplayContext.getPortletURL() %>"
		/>

		<liferay-frontend:management-bar-sort
			orderByCol="<%= cpSpecificationOptionDisplayContext.getOrderByCol() %>"
			orderByType="<%= cpSpecificationOptionDisplayContext.getOrderByType() %>"
			orderColumns='<%= new String[] {"group", "label", "modified-date"} %>'
			portletURL="<%= cpSpecificationOptionDisplayContext.getPortletURL() %>"
		/>

		<li>
			<aui:form action="<%= String.valueOf(cpSpecificationOptionDisplayContext.getPortletURL()) %>" name="searchFm">
				<liferay-ui:input-search
					markupView="lexicon"
				/>
			</aui:form>
		</li>
	</liferay-frontend:management-bar-filters>

	<liferay-frontend:management-bar-action-buttons>
		<c:if test="<%= cpSpecificationOptionDisplayContext.isShowInfoPanel() %>">
			<liferay-frontend:management-bar-sidenav-toggler-button
				icon="info-circle"
				label="info"
			/>
		</c:if>

		<liferay-frontend:management-bar-button
			href='<%= "javascript:" + renderResponse.getNamespace() + "deleteCPSpecificationOptions();" %>'
			icon="times"
			label="delete"
		/>
	</liferay-frontend:management-bar-action-buttons>
</liferay-frontend:management-bar>

<div id="<portlet:namespace />productSpecificationOptionsContainer">
	<div class="closed container-fluid-1280 sidenav-container sidenav-right" id="<portlet:namespace />infoPanelId">
		<c:if test="<%= cpSpecificationOptionDisplayContext.isShowInfoPanel() %>">
			<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="cpSpecificationOptionInfoPanel" var="sidebarPanelURL" />

			<liferay-frontend:sidebar-panel
				resourceURL="<%= sidebarPanelURL %>"
				searchContainerId="cpSpecificationOptions"
			>
				<liferay-util:include page="/specification_option_info_panel.jsp" servletContext="<%= application %>" />
			</liferay-frontend:sidebar-panel>
		</c:if>

		<div class="sidenav-content">
			<aui:form action="<%= portletURL.toString() %>" method="post" name="fm">
				<aui:input name="<%= Constants.CMD %>" type="hidden" />
				<aui:input name="redirect" type="hidden" value="<%= portletURL.toString() %>" />
				<aui:input name="deleteCPSpecificationOptionIds" type="hidden" />

				<div class="product-specification-options-container" id="<portlet:namespace />entriesContainer">
					<liferay-ui:search-container
						id="cpSpecificationOptions"
						iteratorURL="<%= portletURL %>"
						searchContainer="<%= cpSpecificationOptionDisplayContext.getSearchContainer() %>"
					>
						<liferay-ui:search-container-row
							className="com.liferay.commerce.product.model.CPSpecificationOption"
							cssClass="entry-display-style"
							keyProperty="CPSpecificationOptionId"
							modelVar="cpSpecificationOption"
						>

							<%
							PortletURL rowURL = renderResponse.createRenderURL();

							rowURL.setParameter("mvcRenderCommandName", "editProductSpecificationOption");
							rowURL.setParameter("cpSpecificationOptionId", String.valueOf(cpSpecificationOption.getCPSpecificationOptionId()));
							%>

							<liferay-ui:search-container-column-text
								cssClass="important table-cell-content"
								href="<%= rowURL %>"
								name="label"
								value="<%= HtmlUtil.escape(cpSpecificationOption.getTitle(locale)) %>"
							/>

							<liferay-ui:search-container-column-text
								cssClass="table-cell-content"
								name="default-group"
								value="<%= HtmlUtil.escape(cpSpecificationOptionDisplayContext.getCPOptionCategoryTitle(cpSpecificationOption)) %>"
							/>

							<liferay-ui:search-container-column-text
								cssClass="table-cell-content"
								name="use-in-faceted-navigation"
								value='<%= LanguageUtil.get(request, cpSpecificationOption.isFacetable() ? "yes" : "no") %>'
							/>

							<liferay-ui:search-container-column-date
								cssClass="table-cell-content"
								name="modified-date"
								property="modifiedDate"
							/>

							<liferay-ui:search-container-column-jsp
								cssClass="entry-action-column"
								path="/specification_option_action.jsp"
							/>
						</liferay-ui:search-container-row>

						<liferay-ui:search-iterator
							displayStyle="<%= displayStyle %>"
							markupView="lexicon"
						/>
					</liferay-ui:search-container>
				</div>
			</aui:form>
		</div>
	</div>
</div>

<aui:script>
	function <portlet:namespace />deleteCPSpecificationOptions() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-the-selected-specification-labels" />')) {
			var form = AUI.$(document.<portlet:namespace />fm);

			form.attr('method', 'post');
			form.fm('<%= Constants.CMD %>').val('<%= Constants.DELETE %>');
			form.fm('deleteCPSpecificationOptionIds').val(Liferay.Util.listCheckedExcept(form, '<portlet:namespace />allRowIds'));

			submitForm(form, '<portlet:actionURL name="editProductSpecificationOption" />');
		}
	}
</aui:script>