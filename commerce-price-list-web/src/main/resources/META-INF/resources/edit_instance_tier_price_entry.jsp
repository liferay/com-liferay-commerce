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
CPInstanceCommerceTierPriceEntryDisplayContext cpInstanceCommerceTierPriceEntryDisplayContext = (CPInstanceCommerceTierPriceEntryDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceTierPriceEntry commerceTierPriceEntry = cpInstanceCommerceTierPriceEntryDisplayContext.getCommerceTierPriceEntry();
CommercePriceEntry commercePriceEntry = cpInstanceCommerceTierPriceEntryDisplayContext.getCommercePriceEntry();
CPDefinition cpDefinition = cpInstanceCommerceTierPriceEntryDisplayContext.getCPDefinition();
CPInstance cpInstance = cpInstanceCommerceTierPriceEntryDisplayContext.getCPInstance();
long commercePriceEntryId = cpInstanceCommerceTierPriceEntryDisplayContext.getCommercePriceEntryId();
long commerceTierPriceEntryId = cpInstanceCommerceTierPriceEntryDisplayContext.getCommerceTierPriceEntryId();
PortletURL productSkusURL = cpInstanceCommerceTierPriceEntryDisplayContext.getProductSkusURL();
PortletURL instancePriceListsURL = cpInstanceCommerceTierPriceEntryDisplayContext.getInstancePriceListURL();
PortletURL instanceTierPriceEntriesURL = cpInstanceCommerceTierPriceEntryDisplayContext.getInstanceTierPriceEntriesURL();
String title = cpInstanceCommerceTierPriceEntryDisplayContext.getContextTitle();

CommercePriceList commercePriceList = commercePriceEntry.getCommercePriceList();

CommerceCurrency commerceCurrency = commercePriceList.getCommerceCurrency();

Map<String, Object> data = new HashMap<>();

data.put("direction-right", StringPool.TRUE);

PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, "products"), catalogURL, data);
PortalUtil.addPortletBreadcrumbEntry(request, cpDefinition.getName(languageId), String.valueOf(cpInstanceCommerceTierPriceEntryDisplayContext.getEditProductDefinitionURL()), data);
PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, CPDefinitionScreenNavigationConstants.CATEGORY_KEY_SKUS), productSkusURL.toString(), data);
PortalUtil.addPortletBreadcrumbEntry(request, cpInstance.getSku(), instancePriceListsURL.toString(), data);
PortalUtil.addPortletBreadcrumbEntry(request, commercePriceList.getName(), instanceTierPriceEntriesURL.toString(), data);
PortalUtil.addPortletBreadcrumbEntry(request, title, StringPool.BLANK, data);
%>

<clay:navigation-bar
	inverted="<%= true %>"
	navigationItems="<%= CPNavigationItemRegistryUtil.getNavigationItems(renderRequest) %>"
/>

<%@ include file="/breadcrumb.jspf" %>

<portlet:actionURL name="editCPInstanceCommerceTierPriceEntry" var="editCommerceTierPriceEntryActionURL" />

<aui:form action="<%= editCommerceTierPriceEntryActionURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= instanceTierPriceEntriesURL.toString() %>" />
	<aui:input name="commercePriceEntryId" type="hidden" value="<%= commercePriceEntryId %>" />
	<aui:input name="commerceTierPriceEntryId" type="hidden" value="<%= commerceTierPriceEntryId %>" />
	<aui:input name="cpDefinitionId" type="hidden" value="<%= cpDefinition.getCPDefinitionId() %>" />
	<aui:input name="cpInstanceId" type="hidden" value="<%= cpInstance.getCPInstanceId() %>" />

	<div class="lfr-form-content">
		<liferay-ui:error exception="<%= DuplicateCommerceTierPriceEntryException.class %>" message="there-is-already-a-tier-price-entry-with-the-same-minimum-quantity" />

		<aui:fieldset-group>
			<aui:fieldset>
				<aui:input name="price" suffix="<%= HtmlUtil.escape(commerceCurrency.getCode()) %>" type="text" value="<%= (commerceTierPriceEntry == null) ? BigDecimal.ZERO : commerceCurrency.round(commerceTierPriceEntry.getPrice()) %>">
					<aui:validator name="number" />
				</aui:input>

				<aui:input name="promoPrice" suffix="<%= HtmlUtil.escape(commerceCurrency.getCode()) %>" type="text" value="<%= (commerceTierPriceEntry == null) ? BigDecimal.ZERO : commerceCurrency.round(commerceTierPriceEntry.getPromoPrice()) %>">
					<aui:validator name="number" />
				</aui:input>

				<aui:input bean="<%= commerceTierPriceEntry %>" model="<%= CommerceTierPriceEntry.class %>" name="minQuantity" />
			</aui:fieldset>
		</aui:fieldset-group>

		<c:if test="<%= cpInstanceCommerceTierPriceEntryDisplayContext.hasCustomAttributes() %>">
			<aui:fieldset>
				<liferay-expando:custom-attribute-list
					className="<%= CommerceTierPriceEntry.class.getName() %>"
					classPK="<%= (commerceTierPriceEntry != null) ? commerceTierPriceEntry.getCommerceTierPriceEntryId() : 0 %>"
					editable="<%= true %>"
					label="<%= true %>"
				/>
			</aui:fieldset>
		</c:if>

		<aui:button-row cssClass="tier-price-entry-button-row">
			<aui:button cssClass="btn-lg" type="submit" />

			<aui:button cssClass="btn-lg" href="<%= instanceTierPriceEntriesURL.toString() %>" type="cancel" />
		</aui:button-row>
	</div>
</aui:form>