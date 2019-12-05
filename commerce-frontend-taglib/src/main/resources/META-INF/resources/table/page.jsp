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

<%@ include file="/table/init.jsp" %>

<div class="table-root" id="<%= containerId %>"></div>

<aui:script require="commerce-frontend-js/js/smart_table/entry.es as SmartTable">
    new SmartTable.default(
        "<%= containerId %>",
        "<%= containerId %>",
        Object.assign(
            <%= jsonSerializer.serializeDeep(clayTableContext) %>,
            {
                dataProviderKey : "<%= dataProviderKey %>",
                dataSetAPI : "<%= dataSetAPI %>",
                deltaParam : "<%= deltaParam %>",
                disableAJAX : <%= disableAJAX %>,
                filter : <%= jsonSerializer.serializeDeep(filter) %>,
                itemPerPage : <%= itemPerPage %>,
                items : <%= jsonSerializer.serializeDeep(items) %>,
                namespace : "<%= namespace %>",
                pageNumber : <%= pageNumber %>,
                paginationEntries : <%= jsonSerializer.serializeDeep(paginationEntries) %>,
                paginationSelectedEntry : <%= paginationSelectedEntry %>,
                portletURL : "<%= portletURL %>",
                showPagination : <%= showPagination %>,
                spritemap : "<%= spritemap %>",
                tableName : "<%= tableName %>",
                totalItems : <%= totalItems %>,
            }
        )
    );
</aui:script>