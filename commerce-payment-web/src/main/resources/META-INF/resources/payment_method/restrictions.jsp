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
CommercePaymentMethodRestrictionsDisplayContext commercePaymentMethodRestrictionsDisplayContext = (CommercePaymentMethodRestrictionsDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<c:if test="<%= commercePaymentMethodRestrictionsDisplayContext.hasManageCommercePaymentMethodsPermission() %>">
	<div class="container-fluid-1280">
		<liferay-frontend:management-bar
			includeCheckBox="<%= true %>"
			searchContainerId="commerceAddressRestrictions"
		>
			<liferay-frontend:management-bar-filters>
				<liferay-frontend:management-bar-navigation
					navigationKeys='<%= new String[] {"all"} %>'
					portletURL="<%= commercePaymentMethodRestrictionsDisplayContext.getPortletURL() %>"
				/>
			</liferay-frontend:management-bar-filters>

			<liferay-frontend:management-bar-buttons>
				<liferay-frontend:management-bar-display-buttons
					displayViews='<%= new String[] {"list"} %>'
					portletURL="<%= commercePaymentMethodRestrictionsDisplayContext.getPortletURL() %>"
					selectedDisplayStyle="list"
				/>

				<portlet:actionURL name="editCommercePaymentMethodAddressRestriction" var="addCommercePaymentMethodAddressRestrictionURL" />

				<aui:form action="<%= addCommercePaymentMethodAddressRestrictionURL %>" cssClass="hide" name="addCommerceAddressRestrictionFm">
					<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.ADD_MULTIPLE %>" />
					<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
					<aui:input name="className" type="hidden" value="<%= CommercePaymentMethod.class.getName() %>" />
					<aui:input name="classPK" type="hidden" value="<%= commercePaymentMethodRestrictionsDisplayContext.getCommercePaymentMethodId() %>" />
					<aui:input name="commerceCountryIds" type="hidden" value="" />
				</aui:form>

				<liferay-frontend:add-menu
					inline="<%= true %>"
				>
					<liferay-frontend:add-menu-item
						id="addCommerceAddressRestriction"
						title='<%= LanguageUtil.get(request, "add-restriction") %>'
						url="javascript:;"
					/>
				</liferay-frontend:add-menu>
			</liferay-frontend:management-bar-buttons>

			<liferay-frontend:management-bar-action-buttons>
				<liferay-frontend:management-bar-button
					href='<%= "javascript:" + renderResponse.getNamespace() + "deleteCommerceAddressRestrictions();" %>'
					icon="times"
					label="delete"
				/>
			</liferay-frontend:management-bar-action-buttons>
		</liferay-frontend:management-bar>

		<portlet:actionURL name="editCommercePaymentMethodAddressRestriction" var="editCommercePaymentMethodAddressRestrictionActionURL" />

		<aui:form action="<%= editCommercePaymentMethodAddressRestrictionActionURL %>" method="post" name="fm">
			<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.DELETE %>" />
			<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
			<aui:input name="deleteCommerceAddressRestrictionIds" type="hidden" />

			<liferay-ui:search-container
				id="commerceAddressRestrictions"
				searchContainer="<%= commercePaymentMethodRestrictionsDisplayContext.getSearchContainer() %>"
			>
				<liferay-ui:search-container-row
					className="com.liferay.commerce.model.CommerceAddressRestriction"
					keyProperty="commerceAddressRestrictionId"
					modelVar="commerceAddressRestriction"
				>

					<%
					CommerceCountry commerceCountry = commerceAddressRestriction.getCommerceCountry();
					%>

					<liferay-ui:search-container-column-text
						cssClass="table-cell-content"
						name="name"
					>
						<%= HtmlUtil.escape(commerceCountry.getName(locale)) %>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text
						cssClass="table-cell-content"
						name="billing-allowed"
					>
						<c:choose>
							<c:when test="<%= commerceCountry.isBillingAllowed() %>">
								<liferay-ui:icon
									cssClass="commerce-admin-icon-check"
									icon="check"
									markupView="lexicon"
								/>
							</c:when>
							<c:otherwise>
								<liferay-ui:icon
									cssClass="commerce-admin-icon-times"
									icon="times"
									markupView="lexicon"
								/>
							</c:otherwise>
						</c:choose>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text
						cssClass="table-cell-content"
						name="shipping-allowed"
					>
						<c:choose>
							<c:when test="<%= commerceCountry.isShippingAllowed() %>">
								<liferay-ui:icon
									cssClass="commerce-admin-icon-check"
									icon="check"
									markupView="lexicon"
								/>
							</c:when>
							<c:otherwise>
								<liferay-ui:icon
									cssClass="commerce-admin-icon-times"
									icon="times"
									markupView="lexicon"
								/>
							</c:otherwise>
						</c:choose>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text
						cssClass="table-cell-content"
						name="two-letters-iso-code"
					>
						<%= HtmlUtil.escape(commerceCountry.getTwoLettersISOCode()) %>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-jsp
						cssClass="entry-action-column"
						path="/restriction_action.jsp"
					/>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator
					markupView="lexicon"
				/>
			</liferay-ui:search-container>
		</aui:form>
	</div>

	<aui:script>
		function <portlet:namespace />deleteCommerceAddressRestrictions() {
			if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-the-selected-restrictions" />')) {
				var form = AUI.$(document.<portlet:namespace />fm);

				form.fm('deleteCommerceAddressRestrictionIds').val(Liferay.Util.listCheckedExcept(form, '<portlet:namespace />allRowIds'));

				submitForm(form);
			}
		}
	</aui:script>

	<aui:script use="liferay-item-selector-dialog">
		$('#<portlet:namespace />addCommerceAddressRestriction').on(
			'click',
			function(event) {
				event.preventDefault();

				var itemSelectorDialog = new A.LiferayItemSelectorDialog(
					{
						eventName: 'countriesSelectItem',
						on: {
							selectedItemChange: function(event) {
								var selectedItems = event.newVal;

								if (selectedItems) {
									$('#<portlet:namespace />commerceCountryIds').val(selectedItems);

									var addCommerceAddressRestrictionFm = $('#<portlet:namespace />addCommerceAddressRestrictionFm');

									submitForm(addCommerceAddressRestrictionFm);
								}
							}
						},
						title: '<liferay-ui:message key="add-restrictions" />',
						url: '<%= commercePaymentMethodRestrictionsDisplayContext.getItemSelectorUrl() %>'
					}
				);

				itemSelectorDialog.open();
			}
		);
	</aui:script>
</c:if>