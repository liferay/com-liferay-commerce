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
CommerceApplicationAdminDisplayContext commerceApplicationAdminDisplayContext = (CommerceApplicationAdminDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceApplicationModel commerceApplicationModel = commerceApplicationAdminDisplayContext.getCommerceApplicationModel();
%>

<clay:navigation-bar
	inverted="<%= true %>"
	navigationItems="<%= CPNavigationItemRegistryUtil.getNavigationItems(renderRequest) %>"
/>

<portlet:actionURL name="editCommerceApplicationModel" var="editCommerceApplicationModelActionURL" />

<div class="container-fluid-1280 entry-body">
	<aui:form action="<%= editCommerceApplicationModelActionURL %>" method="post" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= (commerceApplicationModel == null) ? Constants.ADD : Constants.UPDATE %>" />
		<aui:input name="redirect" type="hidden" value="<%= backURL %>" />
		<aui:input name="commerceApplicationBrandId" type="hidden" value="<%= commerceApplicationAdminDisplayContext.getCommerceApplicationBrandId() %>" />
		<aui:input name="commerceApplicationModelId" type="hidden" value="<%= commerceApplicationAdminDisplayContext.getCommerceApplicationModelId() %>" />

		<aui:model-context bean="<%= commerceApplicationModel %>" model="<%= CommerceApplicationModel.class %>" />

		<div class="lfr-form-content">
			<aui:fieldset-group markupView="lexicon">
				<aui:fieldset>
					<aui:input autoFocus="<%= true %>" name="name" />

					<aui:input name="year" />
				</aui:fieldset>

				<aui:fieldset>
					<aui:button-row>
						<aui:button cssClass="btn-lg" type="submit" value="save" />

						<aui:button cssClass="btn-lg" href="<%= backURL %>" type="cancel" />
					</aui:button-row>
				</aui:fieldset>
			</aui:fieldset-group>
		</div>
	</aui:form>
</div>