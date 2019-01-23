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
CommerceAccountAddressesDisplayContext commerceAccountAddressesDisplayContext = (CommerceAccountAddressesDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceAccount commerceAccount = commerceAccountAddressesDisplayContext.getCurrentCommerceAccount();
%>

<portlet:actionURL name="editCommerceAddress" var="editCommerceAddressActionURL" />

<commerce-ui:table
	dataProviderKey="<%= CommerceAccountAddressClayTable.NAME %>"
	itemPerPage="<%= 5 %>"
	namespace="<%= renderResponse.getNamespace() %>"
	pageNumber="1"
	portletURL="<%= commerceAccountAddressesDisplayContext.getPortletURL() %>"
	tableName="<%= CommerceAccountAddressClayTable.NAME %>"
/>

<div class="minium-frame__cta is-visible">
	<aui:button cssClass="js-add-address minium-button minium-button--big" onClick='<%= renderResponse.getNamespace() + "openAddAddressModal();" %>' value="add-address" />
</div>

<commerce-ui:add-address-modal
	componentId="addAddressModal"
/>

<portlet:actionURL name="editCommerceAddress" var="editCommerceAddressActionURL" />

<aui:form action="<%= editCommerceAddressActionURL %>" method="post" name="addressFm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.ADD %>" />
	<aui:input name="commerceAddressId" type="hidden" value="" />
	<aui:input name="commerceAccountId" type="hidden" value="<%= commerceAccount.getCommerceAccountId() %>" />
	<aui:input name="name" type="hidden" />
	<aui:input name="street1" type="hidden" />
	<aui:input name="description" type="hidden" />
	<aui:input name="city" type="hidden" />
	<aui:input name="zip" type="hidden" />
	<aui:input name="commerceCountryId" type="hidden" />
	<aui:input name="commerceRegionId" type="hidden" />
	<aui:input name="phoneNumber" type="hidden" />
	<aui:input name="addressType" type="hidden" />
</aui:form>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />openAddAddressModal',
		function(evt) {
			const addAddressModal = Liferay.component('addAddressModal');
			addAddressModal.open();
		}
	);

	Liferay.provide(
		window,
		'editCommerceAddress',
		function(evt) {
			const addAddressModal = Liferay.component('addAddressModal');
			addAddressModal.initAddress()
			addAddressModal.open();
		}
	);

	Liferay.provide(
		window,
		'deleteCommerceAddress',
		function(id) {
			document.querySelector('#<portlet:namespace /><%= Constants.CMD %>').value = '<%= Constants.DELETE %>';
			document.querySelector('#<portlet:namespace />commerceAddressId').value = id;

			submitForm(document.<portlet:namespace />addressFm);
		}
	);

	Liferay.componentReady('addAddressModal').then(
		function(addAddressModal) {
			addAddressModal.on(
				'addAddressModalSave',
				function(formData) {

					document.querySelector('#<portlet:namespace />name').value = formData.referent;
					document.querySelector('#<portlet:namespace />name').value = formData.referent;
					document.querySelector('#<portlet:namespace />street1').value = formData.address;
					document.querySelector('#<portlet:namespace />city').value = formData.city;
					document.querySelector('#<portlet:namespace />zip').value = formData.existingUsersIds;
					document.querySelector('#<portlet:namespace />commerceCountryId').value = formData.country;
					document.querySelector('#<portlet:namespace />commerceRegionId').value = formData.region;
					document.querySelector('#<portlet:namespace />phoneNumber').value = formData.telephone;

					document.querySelector('#<portlet:namespace />addressType').value = formData.addressType;

					addAddressModal.close();

					submitForm(document.<portlet:namespace />addressFm);
				}
			);
		}
	);

</aui:script>