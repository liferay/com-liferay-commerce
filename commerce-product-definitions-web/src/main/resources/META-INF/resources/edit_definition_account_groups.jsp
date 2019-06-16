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
List<CommerceAccountGroup> commerceAccountGroups = cpDefinitionAccountGroupDisplayContext.getCommerceAccountGroups();
long[] commerceAccountGroupIds = cpDefinitionAccountGroupDisplayContext.getCommerceAccountGroupRelCommerceAccountGroupIds();
%>

<portlet:actionURL name="editProductDefinition" var="editProductDefinitionAccountGroupActionURL" />

<aui:form action="<%= editProductDefinitionAccountGroupActionURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="updateAccountGroups" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="cpDefinitionId" type="hidden" value="<%= cpDefinition.getCPDefinitionId() %>" />
	<aui:input name="commerceAccountGroupIds" type="hidden" />

	<div class="lfr-form-content">
		<aui:fieldset-group markupView="lexicon">
			<aui:fieldset>
				<aui:input checked="<%= cpDefinition.isAccountGroupFilterEnabled() %>" label="enable-filter-account-groups" name="accountGroupFilterEnabled" type="toggle-switch" value="<%= cpDefinition.isAccountGroupFilterEnabled() %>" />

				<c:choose>
					<c:when test="<%= commerceAccountGroups.isEmpty() %>">
						<div class="alert alert-info">
							<liferay-ui:message key="there-are-no-account-groups" />
						</div>
					</c:when>
					<c:otherwise>

						<%
						for (CommerceAccountGroup commerceAccountGroup : commerceAccountGroups) {
						%>

							<aui:input checked="<%= ArrayUtil.contains(commerceAccountGroupIds, commerceAccountGroup.getCommerceAccountGroupId()) %>" label="<%= commerceAccountGroup.getName() %>" name='<%= "commerceAccountGroupId" + commerceAccountGroup.getCommerceAccountGroupId() %>' onChange='<%= renderResponse.getNamespace() + "fulfillCommerceAccountGroupIds();" %>' type="checkbox" value="<%= commerceAccountGroup.getCommerceAccountGroupId() %>" />

						<%
						}
						%>

					</c:otherwise>
				</c:choose>

				<aui:button-row>
					<aui:button cssClass="btn-lg" type="submit" />

					<aui:button cssClass="btn-lg" href="<%= catalogURL %>" type="cancel" />
				</aui:button-row>
			</aui:fieldset>
		</aui:fieldset-group>
	</div>
</aui:form>

<aui:script>
	function <portlet:namespace />fulfillCommerceAccountGroupIds(e) {
		var form = AUI.$(document.<portlet:namespace />fm);
		var values = Liferay.Util.listCheckedExcept(form, '<portlet:namespace />allRowIds');
		form.fm('commerceAccountGroupIds').val(values);
		return values;
	}

	<portlet:namespace />fulfillCommerceChannelIds();
</aui:script>