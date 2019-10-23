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
CommerceOrderEditDisplayContext commerceOrderEditDisplayContext = (CommerceOrderEditDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<portlet:actionURL name="editCommerceOrder" var="editCommerceOrderURL" />

<aui:form action="<%= editCommerceOrderURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="printedNote" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="commerceOrderId" type="hidden" value="<%= commerceOrderEditDisplayContext.getCommerceOrderId() %>" />

	<aui:model-context bean="<%= commerceOrderEditDisplayContext.getCommerceOrder() %>" model="<%= CommerceOrder.class %>" />

	<aui:fieldset-group markupView="lexicon">
		<div class="taglib-discussion">
			<aui:fieldset cssClass="add-comment">
				<div class="panel">
					<div class="panel-body">
						<aui:input autoFocus="<%= true %>" label="" name="printedNote" placeholder="type-your-note-to-be-printed-here" />

						<aui:button-row>
							<aui:button cssClass="btn-large btn-primary" type="submit" />
						</aui:button-row>
					</div>
				</div>
			</aui:fieldset>
		</div>
	</aui:fieldset-group>
</aui:form>