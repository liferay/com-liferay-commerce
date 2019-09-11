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
CPDefinitionAccountGroupDisplayContext cpDefinitionAccountGroupDisplayContext = (CPDefinitionAccountGroupDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CPDefinition cpDefinition = cpDefinitionAccountGroupDisplayContext.getCPDefinition();
long[] commerceAccountGroupIds = cpDefinitionAccountGroupDisplayContext.getCommerceAccountGroupRelCommerceAccountGroupIds();

String commerceAccountGroupIdsString = StringUtil.merge(commerceAccountGroupIds, StringPool.COMMA);

SearchContainer<CommerceAccountGroupRel> commerceAccountGroupRelSearchContainer = cpDefinitionAccountGroupDisplayContext.getCPDefinitionAccountGroupSearchContainer();
%>

<portlet:actionURL name="editProductDefinition" var="editProductDefinitionAccountGroupActionURL" />

<aui:form action="<%= editProductDefinitionAccountGroupActionURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="updateAccountGroups" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="cpDefinitionId" type="hidden" value="<%= cpDefinition.getCPDefinitionId() %>" />
	<aui:input name="commerceAccountGroupIds" type="hidden" value="<%= commerceAccountGroupIdsString %>" />

	<div class="lfr-form-content">
		<aui:fieldset-group markupView="lexicon">
			<aui:fieldset>
				<aui:input checked="<%= cpDefinition.isAccountGroupFilterEnabled() %>" helpMessage="enabling-this-filter-means-that-only-the-selected-account-groups-will-be-able-to-view-this-product" label="account-groups-filter" labelOff="disabled" labelOn="enabled" name="accountGroupFilterEnabled" type="toggle-switch" value="<%= cpDefinition.isAccountGroupFilterEnabled() %>" />

				<div class="<%= cpDefinition.isAccountGroupFilterEnabled() ? StringPool.BLANK : "hide" %>" id="<portlet:namespace />accountGroupSearchContainer">
					<liferay-frontend:management-bar
						includeCheckBox="<%= true %>"
						searchContainerId="CommerceAccountGroupRels"
					>
						<liferay-frontend:management-bar-buttons>
							<liferay-frontend:management-bar-display-buttons
								disabled="<%= true %>"
								displayViews='<%= new String[] {"list"} %>'
								portletURL="<%= cpDefinitionAccountGroupDisplayContext.getPortletURL() %>"
								selectedDisplayStyle="list"
							/>

							<c:if test="<%= true %>">
								<liferay-frontend:add-menu
									inline="<%= true %>"
								>
									<liferay-frontend:add-menu-item
										id="selectCommerceAccountGroup"
										title='<%= LanguageUtil.format(request, "add-account-group-relation-to-x", cpDefinition.getName(languageId)) %>'
										url="javascript:;"
									/>
								</liferay-frontend:add-menu>
							</c:if>
						</liferay-frontend:management-bar-buttons>

						<liferay-frontend:management-bar-filters>
							<liferay-frontend:management-bar-navigation
								navigationKeys='<%= new String[] {"all"} %>'
								navigationParam="activeNavigation"
								portletURL="<%= cpDefinitionAccountGroupDisplayContext.getPortletURL() %>"
							/>
						</liferay-frontend:management-bar-filters>

						<liferay-frontend:management-bar-action-buttons>
							<liferay-frontend:management-bar-button
								href='<%= "javascript:" + renderResponse.getNamespace() + "deleteCommerceAccountGroups();" %>'
								icon="times"
								label="remove"
							/>
						</liferay-frontend:management-bar-action-buttons>
					</liferay-frontend:management-bar>

					<liferay-ui:search-container
						id="CommerceAccountGroupRels"
						searchContainer="<%= commerceAccountGroupRelSearchContainer %>"
					>
						<liferay-ui:search-container-row
							className="com.liferay.commerce.account.model.CommerceAccountGroupRel"
							keyProperty="commerceAccountGroupId"
							modelVar="commerceAccountGroupRel"
						>

							<%
							CommerceAccountGroup commerceAccountGroup = cpDefinitionAccountGroupDisplayContext.getCommerceAccountGroup(commerceAccountGroupRel.getCommerceAccountGroupId());
							%>

							<liferay-ui:search-container-column-text
								cssClass="important table-cell-content"
								name="name"
								value="<%= commerceAccountGroup.getName() %>"
							/>
						</liferay-ui:search-container-row>

						<liferay-ui:search-iterator
							markupView="lexicon"
						/>
					</liferay-ui:search-container>
				</div>

				<aui:button-row>
					<aui:button cssClass="btn-lg" type="submit" />

					<aui:button cssClass="btn-lg" href="<%= catalogURL %>" type="cancel" />
				</aui:button-row>
			</aui:fieldset>
		</aui:fieldset-group>
	</div>
</aui:form>

<aui:script>
	Liferay.Util.toggleBoxes('<portlet:namespace />accountGroupFilterEnabled', '<portlet:namespace />accountGroupSearchContainer');

	function <portlet:namespace />deleteCommerceAccountGroups() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-remove-the-selected-account-groups-from-the-product" />')) {
			var form = AUI.$(document.<portlet:namespace />fm);

			form.fm('commerceAccountGroupIds').val(Liferay.Util.listUncheckedExcept(form, '<portlet:namespace />allRowIds'));

			submitForm(form);
		}
	}
</aui:script>

<aui:script use="liferay-item-selector-dialog">
	$('#<portlet:namespace />selectCommerceAccountGroup').on(
		'click',
		function(event) {
			event.preventDefault();

			var itemSelectorDialog = new A.LiferayItemSelectorDialog(
				{
					eventName: 'accountGroupSelectItem',
					on: {
						selectedItemChange: function(event) {
							var <portlet:namespace />addCommerceAccountGroupIds = [];

							<portlet:namespace />addCommerceAccountGroupIds.push('<%= commerceAccountGroupIdsString %>');

							var selectedItems = event.newVal;

							if (selectedItems) {
								var A = AUI();

								A.Array.each(
									selectedItems,
									function(item, index, selectedItems) {
										<portlet:namespace />addCommerceAccountGroupIds.push(item.commerceAccountGroupId);
									}
								);

								$('#<portlet:namespace />commerceAccountGroupIds').val(<portlet:namespace />addCommerceAccountGroupIds);

								var form = $('#<portlet:namespace />fm');

								submitForm(form);
							}
						}
					},
					title: '<liferay-ui:message key="select-account-group" />',
					url: '<%= cpDefinitionAccountGroupDisplayContext.getItemSelectorUrl() %>'
				}
			);

			itemSelectorDialog.open();
		}
	);
</aui:script>