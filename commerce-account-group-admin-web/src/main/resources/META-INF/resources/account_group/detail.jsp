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
CommerceAccountGroupAdminDisplayContext commerceAccountGroupAdminDisplayContext = (CommerceAccountGroupAdminDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceAccountGroup commerceAccountGroup = commerceAccountGroupAdminDisplayContext.getCommerceAccountGroup();
long commerceAccountGroupId = commerceAccountGroupAdminDisplayContext.getCommerceAccountGroupId();
%>

<portlet:actionURL name="editCommerceAccountGroup" var="editCommerceAccountGroupActionURL" />

<aui:form action="<%= editCommerceAccountGroupActionURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= (commerceAccountGroup == null) ? Constants.ADD : Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
	<aui:input name="commerceAccountGroupId" type="hidden" value="<%= commerceAccountGroupId %>" />

	<aui:model-context bean="<%= commerceAccountGroup %>" model="<%= CommerceAccountGroup.class %>" />

	<div class="lfr-form-content">
		<aui:fieldset-group markupView="lexicon">
			<aui:fieldset>
				<aui:input autoFocus="<%= true %>" name="name" />

				<aui:select name="type">

					<%
					for (int type : CommerceAccountConstants.ACCOUNT_TYPES) {
					%>

						<aui:option label="<%= CommerceAccountConstants.getAccountTypeLabel(type) %>" selected="<%= (commerceAccountGroup != null) && (commerceAccountGroup.getType() == type) %>" value="<%= type %>" />

					<%
					}
					%>

				</aui:select>
			</aui:fieldset>

			<c:if test="<%= commerceAccountGroupAdminDisplayContext.hasCustomAttributesAvailable() %>">
				<aui:fieldset collapsible="<%= true %>" label="custom-attribute">
					<liferay-expando:custom-attribute-list
						className="<%= CommerceAccountGroup.class.getName() %>"
						classPK="<%= commerceAccountGroupId %>"
						editable="<%= true %>"
						label="<%= true %>"
					/>
				</aui:fieldset>
			</c:if>

			<aui:fieldset>
				<aui:button-row>
					<aui:button cssClass="btn-lg" type="submit" value="save" />

					<aui:button cssClass="btn-lg" href="<%= backURL %>" type="cancel" />
				</aui:button-row>
			</aui:fieldset>
		</aui:fieldset-group>
	</div>
</aui:form>