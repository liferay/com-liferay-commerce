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
CPDefinitionsDisplayContext cpDefinitionsDisplayContext = (CPDefinitionsDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CPDefinition cpDefinition = cpDefinitionsDisplayContext.getCPDefinition();
long cpDefinitionId = cpDefinitionsDisplayContext.getCPDefinitionId();
%>

<portlet:actionURL name="editProductDefinition" var="editProductDefinitionActionURL" />

<aui:form action="<%= editProductDefinitionActionURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="updateCategorization" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="cpDefinitionId" type="hidden" value="<%= String.valueOf(cpDefinitionId) %>" />

	<aui:model-context bean="<%= cpDefinition %>" model="<%= CPDefinition.class %>" />

	<liferay-asset:asset-categories-error />

	<liferay-asset:asset-tags-error />

	<aui:fieldset-group markupView="lexicon">
		<aui:fieldset>
			<aui:field-wrapper>
				<liferay-asset:asset-categories-selector
					className="<%= CPDefinition.class.getName() %>"
					classPK="<%= cpDefinitionId %>"
					groupIds="<%= new long[] {company.getGroupId()} %>"
				/>
			</aui:field-wrapper>

			<aui:field-wrapper>
				<liferay-asset:asset-tags-selector
					className="<%= CPDefinition.class.getName() %>"
					classPK="<%= cpDefinitionId %>"
				/>
			</aui:field-wrapper>
		</aui:fieldset>
	</aui:fieldset-group>

	<aui:button cssClass="btn-lg" name="saveButton" type="submit" value="save" />

	<aui:button cssClass="btn-lg" href="<%= catalogURL %>" type="cancel" />
</aui:form>