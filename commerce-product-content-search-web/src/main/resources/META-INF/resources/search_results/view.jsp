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
CPSearchResultsDisplayContext cpSearchResultsDisplayContext = (CPSearchResultsDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

boolean enableViewMode = cpSearchResultsDisplayContext.isEnableViewMode();
String viewMode = cpSearchResultsDisplayContext.getViewMode();

Map<String, Object> contextObjects = new HashMap<>();

contextObjects.put("cpSearchResultsDisplayContext", cpSearchResultsDisplayContext);

SearchContainer<CPCatalogEntry> cpCatalogEntrySearchContainer = cpSearchResultsDisplayContext.getSearchContainer();

List<CPCatalogEntry> results = cpCatalogEntrySearchContainer.getResults();
%>

<c:if test="<%= enableViewMode %>">
	<div class="commerce-search-results-view-modes row text-right">
		<div class="col-md-12 py-3">

			<%
			for (String curViewMode : CPContentConstants.VIEW_MODES) {
				String icon = "table2";

				if (curViewMode.equals("icon")) {
					icon = "cards2";
				}

				String cssClass = "btn btn-default lfr-portal-tooltip";

				if (curViewMode.equals(viewMode)) {
					cssClass = "active " + cssClass;
				}

				Map<String, Object> data = new HashMap<>();

				data.put("title", LanguageUtil.get(request, curViewMode));
			%>

				<portlet:actionURL name="updateViewMode" var="updateViewModeActionURL">
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="viewMode" value="<%= curViewMode %>" />
				</portlet:actionURL>

				<aui:a cssClass="<%= cssClass %>" data="<%= data %>" href="<%= updateViewModeActionURL %>" id="<%= renderResponse.getNamespace() + curViewMode %>">
					<c:if test="<%= Validator.isNotNull(icon) %>">
						<aui:icon cssClass="icon-monospaced" image="<%= icon %>" markupView="lexicon" />
					</c:if>

					<span class="sr-only"><liferay-ui:message key="<%= curViewMode %>" /></span>
				</aui:a>

			<%
			}
			%>

		</div>
	</div>
</c:if>

<c:choose>
	<c:when test="<%= !enableViewMode && cpSearchResultsDisplayContext.isSelectionStyleADT() %>">
		<liferay-ddm:template-renderer
			className="<%= CPSearchResultsPortlet.class.getName() %>"
			contextObjects="<%= contextObjects %>"
			displayStyle="<%= cpSearchResultsDisplayContext.getDisplayStyle() %>"
			displayStyleGroupId="<%= cpSearchResultsDisplayContext.getDisplayStyleGroupId() %>"
			entries="<%= results %>"
		/>

		<c:if test="<%= cpSearchResultsDisplayContext.isPaginate() %>">
			<aui:form useNamespace="<%= false %>">
				<liferay-ui:search-paginator
					markupView="lexicon"
					searchContainer="<%= cpCatalogEntrySearchContainer %>"
					type="<%= cpSearchResultsDisplayContext.getPaginationType() %>"
				/>
			</aui:form>
		</c:if>
	</c:when>
	<c:when test="<%= enableViewMode || cpSearchResultsDisplayContext.isSelectionStyleCustomRenderer() %>">
		<liferay-commerce-product:product-list-renderer
			CPDataSourceResult = "<%= cpSearchResultsDisplayContext.getCPDataSourceResult() %>"
			entryKeys = "<%= cpSearchResultsDisplayContext.getCPContentListEntryRendererKeys(viewMode) %>"
			key = "<%= cpSearchResultsDisplayContext.getCPContentListRendererKey(viewMode) %>"
		/>

		<c:if test="<%= cpSearchResultsDisplayContext.isPaginate() %>">
			<aui:form useNamespace="<%= false %>">
				<liferay-ui:search-paginator
					markupView="lexicon"
					searchContainer="<%= cpCatalogEntrySearchContainer %>"
					type="<%= cpSearchResultsDisplayContext.getPaginationType() %>"
				/>
			</aui:form>
		</c:if>
	</c:when>
	<c:otherwise>
	</c:otherwise>
</c:choose>