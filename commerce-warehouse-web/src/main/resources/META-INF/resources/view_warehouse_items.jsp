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
CommerceWarehouseItemsDisplayContext commerceWarehouseItemsDisplayContext = (CommerceWarehouseItemsDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

String backURL = commerceWarehouseItemsDisplayContext.getBackURL();

if (Validator.isNotNull(backURL)) {
	portletDisplay.setShowBackIcon(true);
	portletDisplay.setURLBack(backURL);
}
%>

<c:if test="<%= commerceWarehouseItemsDisplayContext.hasManageCommerceWarehousePermission() %>">

	<%
	List<CommerceInventoryWarehouse> commerceWarehouses = commerceWarehouseItemsDisplayContext.getCommerceWarehouses();
	CPInstance cpInstance = commerceWarehouseItemsDisplayContext.getCPInstance();
	%>

	<div class="container-fluid-1280">
		<c:choose>
			<c:when test="<%= commerceWarehouses.isEmpty() %>">
				<div class="alert alert-info">
					<liferay-ui:message key="there-are-no-active-warehouses" />
				</div>
			</c:when>
			<c:otherwise>
				<portlet:actionURL name="editCommerceWarehouseItem" var="updateCommerceWarehouseItemURL" />

				<aui:form action="<%= updateCommerceWarehouseItemURL %>" method="post" name="fm">
					<aui:input name="<%= Constants.CMD %>" type="hidden" />
					<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
					<aui:input name="commerceWarehouseId" type="hidden" />
					<aui:input name="commerceWarehouseItemId" type="hidden" />
					<aui:input name="sku" type="hidden" value="<%= cpInstance.getSku() %>" />

					<table class="show-quick-actions-on-hover table table-autofit table-list table-responsive-lg">
						<thead>
							<tr>
								<th class="table-cell-content"><liferay-ui:message key="warehouse" /></th>
								<th><liferay-ui:message key="quantity" /></th>
								<th></th>
							</tr>
						</thead>

						<tbody>

							<%
							for (CommerceInventoryWarehouse commerceWarehouse : commerceWarehouses) {
								CommerceInventoryWarehouseItem commerceWarehouseItem = commerceWarehouseItemsDisplayContext.getCommerceWarehouseItem(commerceWarehouse);

								long commerceWarehouseItemId = 0;

								if (commerceWarehouseItem != null) {
									commerceWarehouseItemId = commerceWarehouseItem.getCommerceInventoryWarehouseItemId();
								}

								int curIndex = commerceWarehouses.indexOf(commerceWarehouse);
							%>

								<aui:model-context bean="<%= commerceWarehouseItem %>" model="<%= CommerceInventoryWarehouseItem.class %>" />

								<tr>
									<td>
										<%= HtmlUtil.escape(commerceWarehouse.getName()) %>
									</td>
									<td>
										<aui:input id='<%= "commerceWarehouseItemQuantity" + curIndex %>' label="" name="quantity" wrapperCssClass="m-0" />
									</td>
									<td class="text-center">
										<aui:button cssClass="btn btn-primary" name='<%= "saveButton" + curIndex %>' onClick="<%= commerceWarehouseItemsDisplayContext.getUpdateCommerceWarehouseItemTaglibOnClick(commerceWarehouse.getCommerceInventoryWarehouseId(), commerceWarehouseItemId, curIndex) %>" primary="<%= true %>" value="save" />
									</td>
								</tr>

							<%
							}
							%>

						</tbody>
					</table>
				</aui:form>
			</c:otherwise>
		</c:choose>
	</div>

	<aui:script>
		function <portlet:namespace/>updateCommerceWarehouseItem(commerceWarehouseId, commerceWarehouseItemId, index) {
			var form = $(document.<portlet:namespace />fm);

			if (commerceWarehouseItemId > 0) {
				form.fm('<%= Constants.CMD %>').val('<%= Constants.UPDATE %>');
			}
			else {
				form.fm('<%= Constants.CMD %>').val('<%= Constants.ADD %>');
			}

			form.fm('commerceWarehouseId').val(commerceWarehouseId);
			form.fm('commerceWarehouseItemId').val(commerceWarehouseItemId);

			var quantityInputId = '#<portlet:namespace />commerceWarehouseItemQuantity' + index;

			var quantityInput = $(quantityInputId);

			form.fm('quantity').val(quantityInput.val());

			submitForm(form);
		}
	</aui:script>

	<aui:script>
		var quantityPrefix = "<portlet:namespace />commerceWarehouseItemQuantity";
		var enterKeyCode = 13;

		$('input[id^=' + quantityPrefix + ']').on(
			'keypress',
			function(event) {
				if (event.keyCode == enterKeyCode) {
					event.preventDefault();

					var curIndex = $(this).attr('id').split(quantityPrefix)[1];
					$("#<portlet:namespace/>saveButton" + curIndex).click();
				}
			}
		);
	</aui:script>
</c:if>