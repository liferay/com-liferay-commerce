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
CommercePaymentMethodGroupRelsDisplayContext commercePaymentMethodsDisplayContext = (CommercePaymentMethodGroupRelsDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommercePaymentMethodGroupRel commercePaymentMethodGroupRel = commercePaymentMethodsDisplayContext.getCommercePaymentMethodGroupRel();
%>

<portlet:actionURL name="editCommercePaymentMethodGroupRel" var="commercePaymentMethodGroupRelActionURL" />

<aui:form action="<%= commercePaymentMethodGroupRelActionURL %>" cssClass="container-fluid-1280" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveCommercePaymentMethodGroupRel();" %>'>
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= (commercePaymentMethodGroupRel.getCommercePaymentMethodGroupRelId() <= 0) ? Constants.ADD : Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="commercePaymentMethodGroupRelId" type="hidden" value="<%= commercePaymentMethodGroupRel.getCommercePaymentMethodGroupRelId() %>" />
	<aui:input name="engineKey" type="hidden" value="<%= commercePaymentMethodGroupRel.getEngineKey() %>" />

	<liferay-ui:error-marker
		key="<%= WebKeys.ERROR_SECTION %>"
		value="details"
	/>

	<liferay-ui:error exception="<%= CommercePaymentMethodGroupRelNameException.class %>" message="please-enter-a-valid-name" />

	<aui:model-context bean="<%= commercePaymentMethodGroupRel %>" model="<%= CommercePaymentMethodGroupRel.class %>" />

	<aui:fieldset-group markupView="lexicon">
		<aui:fieldset>
			<aui:input name="name" />

			<aui:input name="description" />

			<%
			String thumbnailSrc = commercePaymentMethodGroupRel.getImageURL(themeDisplay);
			%>

			<c:if test="<%= Validator.isNotNull(thumbnailSrc) %>">
				<div class="row">
					<div class="col-md-4">
						<img class="w-100" src="<%= thumbnailSrc %>" />
					</div>
				</div>
			</c:if>

			<aui:input label="icon" name="imageFile" type="file" />

			<aui:input name="priority" />

			<aui:input checked="<%= (commercePaymentMethodGroupRel == null) ? false : commercePaymentMethodGroupRel.isActive() %>" name="active" type="toggle-switch" />
		</aui:fieldset>
	</aui:fieldset-group>

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />

		<aui:button cssClass="btn-lg" href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>

<aui:script>
	function <portlet:namespace />saveCommercePaymentMethodGroupRel() {
		submitForm(document.<portlet:namespace />fm);
	}
</aui:script>