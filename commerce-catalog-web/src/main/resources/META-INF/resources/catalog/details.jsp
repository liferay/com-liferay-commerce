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
String catalogNavigationItem = ParamUtil.getString(request, "catalogNavigationItem", "view-details");

CommerceCatalogDisplayContext commerceCatalogDisplayContext = (CommerceCatalogDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceCatalog commerceCatalog = commerceCatalogDisplayContext.getCommerceCatalog();
%>

<%@ include file="/catalog/navbar_definitions.jspf" %>

<portlet:actionURL name="editCommerceCatalog" var="editCommerceCatalogActionURL" />

<aui:form action="<%= editCommerceCatalogActionURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= (commerceCatalog == null) ? Constants.ADD : Constants.UPDATE %>" />
	<aui:input name="commerceCatalogId" type="hidden" value="<%= (commerceCatalog == null) ? 0 : commerceCatalog.getCommerceCatalogId() %>" />

	<div class="lfr-form-content">
		<aui:fieldset-group markupView="lexicon">
			<aui:fieldset>
				<aui:input bean="<%= commerceCatalog %>" model="<%= CommerceCatalog.class %>" name="name" required="true" />

				<aui:select helpMessage="the-default-language-for-the-content-within-this-catalog" label="default-catalog-language" name="catalogDefaultLanguageId" required="true" title="language">

					<%
					String catalogDefaultLanguageId = themeDisplay.getLanguageId();

					if (commerceCatalog != null) {
						catalogDefaultLanguageId = commerceCatalog.getCatalogDefaultLanguageId();
					}

					Set<Locale> siteAvailableLocales = LanguageUtil.getAvailableLocales(themeDisplay.getScopeGroupId());

					for (Locale siteAvailableLocale : siteAvailableLocales) {
					%>

						<aui:option label="<%= siteAvailableLocale.getDisplayName(locale) %>" lang="<%= LocaleUtil.toW3cLanguageId(siteAvailableLocale) %>" selected="<%= catalogDefaultLanguageId.equals(LanguageUtil.getLanguageId(siteAvailableLocale)) %>" value="<%= LocaleUtil.toLanguageId(siteAvailableLocale) %>" />

					<%
					}
					%>

				</aui:select>
			</aui:fieldset>
		</aui:fieldset-group>
	</div>

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />
	</aui:button-row>
</aui:form>