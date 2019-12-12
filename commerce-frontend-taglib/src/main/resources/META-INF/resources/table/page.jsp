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

<aui:script require="commerce-frontend-js/components/dataset_display/entry.es as table">
	table.default(
		"<%= containerId %>",
		"<%= containerId %>",
		Object.assign(
			<%= jsonSerializer.serializeDeep(clayTableContext) %>,
			{
				dataProviderKey : "<%= dataProviderKey %>",
				apiUrl : "<%= dataSetAPI %>",
				deltaParam : "<%= deltaParam %>",
				disableAJAX : <%= disableAJAX %>,
				// filters : <%= jsonSerializer.serializeDeep(filter) %>,
				filters : [
					{
						label: 'Text test',
						operator: 'contains',
						slug: 'text-test',
						type: 'text',
						value: 'Test input'
					},
					{
						items: [
							{
								label: 'First option',
								value: 'first-option'
							},
							{
								label: 'Second option',
								value: 'second-option'
							}
						],
						label: 'Select test',
						operator: 'eq',
						slug: 'select-test',
						type: 'select',
						value: 'second-option'
					},
					{
						items: [
							{
								label: 'First option',
								value: 'first-option'
							},
							{
								label: 'Second option',
								value: 'second-option'
							}
						],
						label: 'Radio test',
						operator: 'eq',
						slug: 'radio-test',
						type: 'radio'
					},
					{
						items: [
							{
								label: 'First option',
								value: 'first-option'
							},
							{
								label: 'Second option',
								value: 'second-option'
							},
							{
								label: 'Third option',
								value: 'third-option'
							}
						],
						label: 'Checkbox test',
						operator: 'contains',
						slug: 'checkbox-test',
						type: 'checkbox',
						value: ['first-option', 'third-option']
					},
					{
						inputText: '$',
						label: 'Number test',
						max: 200,
						min: 20,
						operator: 'eq',
						slug: 'number-test',
						type: 'number',
						value: 123
					}
				],
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
				totalItems : <%= totalItems %>
			}
		)
	);
</aui:script>