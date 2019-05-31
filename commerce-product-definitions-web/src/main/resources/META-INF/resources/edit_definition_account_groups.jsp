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

long cpDefinitionId = cpDefinitionAccountGroupDisplayContext.getCPDefinitionId();
List<CommerceAccountGroup> commerceAccountGroups = cpDefinitionAccountGroupDisplayContext.getCommerceAccountGroups();
long[] commerceAccountGroupIds = cpDefinitionAccountGroupDisplayContext.getCommerceAccountGroupRelCommerceAccountGroupIds();
%>

<c:choose>
	<c:when test="<%= commerceAccountGroups.isEmpty() %>">
		<div class="alert alert-info">
			<liferay-ui:message key="there-are-no-account-groups" />
		</div>
	</c:when>
	<c:otherwise>
		<portlet:actionURL name="editProductDefinition" var="editProductDefinitionAccountGroupActionURL" />

		<aui:form action="<%= editProductDefinitionAccountGroupActionURL %>" cssClass="container-fluid-1280" method="post" name="fm">
			<aui:input name="<%= Constants.CMD %>" type="hidden" value="updateAccountGroups" />
			<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
			<aui:input name="cpDefinitionId" type="hidden" value="<%= cpDefinitionId %>" />

			<div class="lfr-form-content">
				<aui:fieldset-group markupView="lexicon">
					<aui:fieldset>

						<%
						for (CommerceAccountGroup commerceAccountGroup : commerceAccountGroups) {
						%>

							<aui:input checked="<%= ArrayUtil.contains(commerceAccountGroupIds, commerceAccountGroup.getCommerceAccountGroupId()) %>" label="<%= commerceAccountGroup.getName() %>" name="commerceAccountGroupIds" type="checkbox" value="<%= commerceAccountGroup.getCommerceAccountGroupId() %>" />

						<%
						}
						%>

					</aui:fieldset>
				</aui:fieldset-group>

				<aui:button-row>
					<aui:button cssClass="btn-lg" type="submit" />

					<aui:button cssClass="btn-lg" href="<%= catalogURL %>" type="cancel" />
				</aui:button-row>
			</div>
		</aui:form>
	</c:otherwise>
</c:choose>