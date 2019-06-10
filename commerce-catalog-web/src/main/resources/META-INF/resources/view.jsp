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
CommerceCatalogDisplayContext commerceCatalogDisplayContext = (CommerceCatalogDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

SearchContainer commerceCatalogSearchContainer = commerceCatalogDisplayContext.getSearchContainer();

PortletURL portletURL = commerceCatalogDisplayContext.getPortletURL();

portletURL.setParameter("searchContainerId", "commerceCatalogs");
%>

<liferay-util:include page="/toolbar.jsp" servletContext="<%= application %>">
	<liferay-util:param name="searchContainerId" value="commerceCatalogs" />
</liferay-util:include>

<div class="closed container-fluid-1280 sidenav-container sidenav-right" id="<portlet:namespace />infoPanelId">
	<div class="sidenav-content">
		<aui:form action="<%= portletURL.toString() %>" method="post" name="fm">
			<aui:input name="<%= Constants.CMD %>" type="hidden" />
			<aui:input name="redirect" type="hidden" value="<%= portletURL.toString() %>" />
			<aui:input name="commerceCatalogIds" type="hidden" />

			<liferay-ui:error exception="<%= CommerceCatalogProductsException.class %>" message="you-cannot-delete-catalogs-that-have-products" />
			<liferay-ui:error exception="<%= CommerceCatalogSystemException.class %>" message="you-cannot-delete-master-catalog" />

			<div class="products-container" id="<portlet:namespace />commerceCatalogsContainer">
				<liferay-ui:search-container
					emptyResultsMessage="no-catalogs-were-found"
					id="commerceCatalogs"
					searchContainer="<%= commerceCatalogSearchContainer %>"
				>
					<liferay-ui:search-container-row
						className="com.liferay.commerce.product.model.CommerceCatalog"
						cssClass="entry-display-style"
						keyProperty="commerceCatalogId"
						modelVar="commerceCatalog"
					>
						<liferay-ui:search-container-column-text
							cssClass="important table-cell-content"
							href="<%= commerceCatalogDisplayContext.getCatalogURL(commerceCatalog) %>"
							name="name"
							value="<%= HtmlUtil.escape(commerceCatalog.getName()) %>"
						/>

						<liferay-ui:search-container-column-date
							name="create-date"
							property="createDate"
						/>

						<liferay-ui:search-container-column-date
							name="modified-date"
							property="modifiedDate"
						/>

						<liferay-ui:search-container-column-jsp
							cssClass="entry-action-column"
							path="/catalog_action.jsp"
						/>
					</liferay-ui:search-container-row>

					<liferay-ui:search-iterator
						displayStyle="list"
						markupView="lexicon"
					/>
				</liferay-ui:search-container>
			</div>
		</aui:form>
	</div>
</div>