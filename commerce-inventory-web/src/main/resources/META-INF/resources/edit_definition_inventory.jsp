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
CPDefinitionInventoryDisplayContext cpDefinitionInventoryDisplayContext = (CPDefinitionInventoryDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CPDefinitionInventory cpDefinitionInventory = cpDefinitionInventoryDisplayContext.getCPDefinitionInventory();
CPDAvailabilityEstimate cpdAvailabilityEstimate = cpDefinitionInventoryDisplayContext.getCPDAvailabilityEstimate();
long cpDefinitionId = cpDefinitionInventoryDisplayContext.getCPDefinitionId();
%>

<portlet:actionURL name="editProductDefinitionInventory" var="editProductDefinitionInventoryActionURL" />

<aui:form action="<%= editProductDefinitionInventoryActionURL %>" cssClass="container-fluid-1280" method="post" name="fm1">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= (cpDefinitionInventory == null) ? Constants.ADD : Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="cpDefinitionInventoryId" type="hidden" value="<%= (cpDefinitionInventory == null) ? StringPool.BLANK : cpDefinitionInventory.getCPDefinitionInventoryId() %>" />
	<aui:input name="cpdAvailabilityEstimateId" type="hidden" value="<%= (cpdAvailabilityEstimate == null) ? StringPool.BLANK : cpdAvailabilityEstimate.getCPDAvailabilityEstimateId() %>" />
	<aui:input name="cpDefinitionId" type="hidden" value="<%= cpDefinitionId %>" />

	<div class="lfr-form-content">
		<aui:model-context bean="<%= cpDefinitionInventory %>" model="<%= CPDefinitionInventory.class %>" />

		<aui:fieldset-group markupView="lexicon">
			<aui:fieldset>
				<aui:select label="inventory-engine" name="CPDefinitionInventoryEngine">

					<%
					List<CPDefinitionInventoryEngine> cpDefinitionInventoryEngines = cpDefinitionInventoryDisplayContext.getCPDefinitionInventoryEngines();

					for (CPDefinitionInventoryEngine cpDefinitionInventoryEngine : cpDefinitionInventoryEngines) {
						String cpDefinitionInventoryEngineName = cpDefinitionInventoryEngine.getKey();
					%>

						<aui:option label="<%= cpDefinitionInventoryEngine.getLabel(locale) %>" selected="<%= (cpDefinitionInventory != null) && cpDefinitionInventoryEngineName.equals(cpDefinitionInventory.getCPDefinitionInventoryEngine()) %>" value="<%= cpDefinitionInventoryEngineName %>" />

					<%
					}
					%>

				</aui:select>

				<aui:select label="low-stock-action" name="lowStockActivity">

					<%
					List<CommerceLowStockActivity> commerceLowStockActivities = cpDefinitionInventoryDisplayContext.getCommerceLowStockActivities();

					for (CommerceLowStockActivity commerceLowStockActivity : commerceLowStockActivities) {
						String commerceLowStockActivityName = commerceLowStockActivity.getKey();
					%>

						<aui:option label="<%= commerceLowStockActivity.getLabel(locale) %>" selected="<%= (cpDefinitionInventory != null) && commerceLowStockActivityName.equals(cpDefinitionInventory.getLowStockActivity()) %>" value="<%= commerceLowStockActivityName %>" />

					<%
					}
					%>

				</aui:select>

				<aui:select label="availability-estimate" name="commerceAvailabilityEstimateId" showEmptyOption="<%= true %>">

					<%
					List<CommerceAvailabilityEstimate> commerceAvailabilityEstimates = cpDefinitionInventoryDisplayContext.getCommerceAvailabilityEstimates();

					for (CommerceAvailabilityEstimate commerceAvailabilityEstimate : commerceAvailabilityEstimates) {
					%>

						<aui:option label="<%= commerceAvailabilityEstimate.getTitle(languageId) %>" selected="<%= (cpdAvailabilityEstimate != null) && (commerceAvailabilityEstimate.getCommerceAvailabilityEstimateId() == cpdAvailabilityEstimate.getCommerceAvailabilityEstimateId()) %>" value="<%= commerceAvailabilityEstimate.getCommerceAvailabilityEstimateId() %>" />

					<%
					}
					%>

				</aui:select>

				<aui:input checked="<%= (cpDefinitionInventory == null) ? false : cpDefinitionInventory.getDisplayAvailability() %>" name="displayAvailability" type="toggle-switch" />

				<aui:input checked="<%= (cpDefinitionInventory == null) ? false : cpDefinitionInventory.isDisplayStockQuantity() %>" name="displayStockQuantity" type="toggle-switch" />

				<liferay-ui:error exception="<%= NumberFormatException.class %>" message="there-was-an-error-processing-one-or-more-of-the-quantities-entered" />

				<aui:input name="minStockQuantity">
					<aui:validator name="digits" />
				</aui:input>

				<aui:input checked="<%= (cpDefinitionInventory == null) ? true : cpDefinitionInventory.getBackOrders() %>" label="allow-back-orders" name="backOrders" type="toggle-switch" />

				<aui:input name="minOrderQuantity">
					<aui:validator name="digits" />
					<aui:validator name="min">1</aui:validator>
				</aui:input>

				<aui:input name="maxOrderQuantity">
					<aui:validator name="digits" />
					<aui:validator name="min">1</aui:validator>
				</aui:input>

				<aui:input helpMessage="separate-values-with-a-comma-period-or-space" name="allowedOrderQuantities" />

				<aui:input name="multipleOrderQuantity">
					<aui:validator name="digits" />
					<aui:validator name="min">1</aui:validator>
				</aui:input>
			</aui:fieldset>
		</aui:fieldset-group>

		<aui:button-row>
			<aui:button cssClass="btn-lg" type="submit" />

			<aui:button cssClass="btn-lg" href="<%= catalogURL %>" type="cancel" />
		</aui:button-row>
	</div>
</aui:form>