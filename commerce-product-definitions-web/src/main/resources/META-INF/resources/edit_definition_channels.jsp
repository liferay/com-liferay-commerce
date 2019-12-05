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
CPDefinitionChannelDisplayContext cpDefinitionChannelDisplayContext = (CPDefinitionChannelDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CPDefinition cpDefinition = cpDefinitionChannelDisplayContext.getCPDefinition();
List<CommerceChannel> commerceChannels = cpDefinitionChannelDisplayContext.getCommerceChannels();
long[] commerceChannelIds = cpDefinitionChannelDisplayContext.getCommerceChannelRelCommerceChannelIds();
%>

<portlet:actionURL name="editProductDefinition" var="editProductDefinitionChannelActionURL" />

<aui:form action="<%= editProductDefinitionChannelActionURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="updateChannels" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="cpDefinitionId" type="hidden" value="<%= cpDefinition.getCPDefinitionId() %>" />
	<aui:input name="commerceChannelIds" type="hidden" />

	<div class="lfr-form-content">
		<aui:fieldset-group markupView="lexicon">
			<aui:fieldset>
				<aui:input checked="<%= cpDefinition.isChannelFilterEnabled() %>" label="enable-filter-channels" name="channelFilterEnabled" type="toggle-switch" value="<%= cpDefinition.isChannelFilterEnabled() %>" />

				<c:if test="<%= cpDefinition.isChannelFilterEnabled() %>">
					<c:choose>
						<c:when test="<%= commerceChannels.isEmpty() %>">
							<div class="alert alert-info">
								<liferay-ui:message key="there-are-no-channels" />
							</div>
						</c:when>
						<c:otherwise>

							<%
							for (CommerceChannel commerceChannel : commerceChannels) {
							%>

								<aui:input checked="<%= ArrayUtil.contains(commerceChannelIds, commerceChannel.getCommerceChannelId()) %>" disabled="<%= !cpDefinition.isChannelFilterEnabled() %>" label="<%= commerceChannel.getName() %>" name='<%= "commerceChannelId_" + commerceChannel.getCommerceChannelId() %>' onChange='<%= renderResponse.getNamespace() + "fulfillCommerceChannelIds();" %>' type="checkbox" value="<%= commerceChannel.getCommerceChannelId() %>" />

							<%
							}
							%>

						</c:otherwise>
					</c:choose>
				</c:if>

				<aui:button-row>
					<aui:button cssClass="btn-lg" type="submit" />

					<aui:button cssClass="btn-lg" href="<%= catalogURL %>" type="cancel" />
				</aui:button-row>
			</aui:fieldset>
		</aui:fieldset-group>
	</div>
</aui:form>

<aui:script>
	function <portlet:namespace />fulfillCommerceChannelIds(e) {
		var form = AUI.$(document.<portlet:namespace />fm);
		var values = Liferay.Util.listCheckedExcept(form, '<portlet:namespace />allRowIds');
		form.fm('commerceChannelIds').val(values);
		return values;
	}

	<portlet:namespace />fulfillCommerceChannelIds();
</aui:script>