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
String redirect = ParamUtil.getString(request, "redirect");

CommerceSubscriptionEntryDisplayContext commerceSubscriptionEntryDisplayContext = (CommerceSubscriptionEntryDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceSubscriptionEntry commerceSubscriptionEntry = commerceSubscriptionEntryDisplayContext.getCommerceSubscriptionEntry();
List<CPSubscriptionType> cpSubscriptionTypes = commerceSubscriptionEntryDisplayContext.getCPSubscriptionTypes();

String defaultCPSubscriptionType = StringPool.BLANK;

if (!cpSubscriptionTypes.isEmpty()) {
	CPSubscriptionType firstCPSubscriptionType = cpSubscriptionTypes.get(0);

	defaultCPSubscriptionType = firstCPSubscriptionType.getName();
}

int subscriptionLength = BeanParamUtil.getInteger(commerceSubscriptionEntry, request, "subscriptionLength", 1);
String subscriptionType = BeanParamUtil.getString(commerceSubscriptionEntry, request, "subscriptionType", defaultCPSubscriptionType);
long maxSubscriptionCycles = BeanParamUtil.getLong(commerceSubscriptionEntry, request, "maxSubscriptionCycles");
boolean active = BeanParamUtil.getBoolean(commerceSubscriptionEntry, request, "active");

String defaultCPSubscriptionTypeLabel = StringPool.BLANK;

CPSubscriptionType cpSubscriptionType = commerceSubscriptionEntryDisplayContext.getCPSubscriptionType(subscriptionType);

if (cpSubscriptionType != null) {
	defaultCPSubscriptionTypeLabel = cpSubscriptionType.getLabel(locale);
}

CPSubscriptionTypeJSPContributor cpSubscriptionTypeJSPContributor = commerceSubscriptionEntryDisplayContext.getCPSubscriptionTypeJSPContributor(subscriptionType);

boolean ending = maxSubscriptionCycles > 0;
%>

<portlet:actionURL name="editCommerceSubscriptionEntry" var="editCommerceSubscriptionEntryActionURL" />

<aui:form action="<%= editCommerceSubscriptionEntryActionURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="commerceSubscriptionEntryId" type="hidden" value="<%= String.valueOf(commerceSubscriptionEntryDisplayContext.getCommerceSubscriptionEntryId()) %>" />

	<aui:fieldset-group markupView="lexicon">
		<aui:fieldset>
			<aui:model-context bean="<%= commerceSubscriptionEntry %>" model="<%= CommerceSubscriptionEntry.class %>" />

			<aui:select name="subscriptionType" onChange='<%= renderResponse.getNamespace() + "selectSubscriptionType();" %>'>

				<%
				for (CPSubscriptionType curCPSubscriptionType : cpSubscriptionTypes) {
				%>

					<aui:option data-label="<%= curCPSubscriptionType.getLabel(locale) %>" label="<%= curCPSubscriptionType.getLabel(locale) %>" selected="<%= subscriptionType.equals(curCPSubscriptionType.getName()) %>" value="<%= curCPSubscriptionType.getName() %>" />

				<%
				}
				%>

			</aui:select>

			<%
			if (cpSubscriptionTypeJSPContributor != null) {
				cpSubscriptionTypeJSPContributor.render(commerceSubscriptionEntry, request, response);
			}
			%>

			<div id="<portlet:namespace />cycleLengthContainer">
				<aui:input name="subscriptionLength" suffix="<%= defaultCPSubscriptionTypeLabel %>" value="<%= String.valueOf(subscriptionLength) %>">
					<aui:validator name="digits" />
					<aui:validator name="min">1</aui:validator>
				</aui:input>
			</div>

			<div id="<portlet:namespace />neverEndsContainer">
				<div class="never-ends-header">
					<aui:input checked="<%= ending ? false : true %>" name="neverEnds" type="toggle-switch" />
				</div>

				<%
				String cssClass = "never-ends-content hide";

				if (ending) {
					cssClass = "never-ends-content";
				}
				%>

				<div class="<%= cssClass %>">
					<aui:input disabled="<%= ending ? false : true %>" helpMessage="max-subscription-cycles-help" label="end-after" name="maxSubscriptionCycles" suffix='<%= LanguageUtil.get(request, "cycles") %>' value="<%= String.valueOf(maxSubscriptionCycles) %>">
						<aui:validator name="digits" />

						<aui:validator errorMessage='<%= LanguageUtil.format(request, "please-enter-a-value-greater-than-or-equal-to-x", 1) %>' name="custom">
							function(val, fieldNode, ruleValue) {
								if (AUI.$('#<portlet:namespace />neverEnds')[0].checked) {
									return true;
								}

								return (parseInt(val, 10) > 0);
							}
						</aui:validator>
					</aui:input>
				</div>
			</div>

			<aui:input checked="<%= active %>" name="active" type="toggle-switch" />

			<aui:input name="startDate" />

			<aui:input name="nextIterationDate" />
		</aui:fieldset>
	</aui:fieldset-group>

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />

		<aui:button cssClass="btn-lg" href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />selectSubscriptionType',
		function() {
			var A = AUI();

			var subscriptionLength = A.one('#<portlet:namespace />subscriptionLength').val();
			var subscriptionType = A.one('#<portlet:namespace />subscriptionType').val();
			var maxSubscriptionCycles = A.one('#<portlet:namespace />maxSubscriptionCycles').val();
			var active = A.one('#<portlet:namespace />active').attr('checked');

			var portletURL = new Liferay.PortletURL.createURL('<%= currentURLObj %>');

			portletURL.setParameter('subscriptionLength', subscriptionLength);
			portletURL.setParameter('subscriptionType', subscriptionType);
			portletURL.setParameter('maxSubscriptionCycles', maxSubscriptionCycles);
			portletURL.setParameter('active', active);

			window.location.replace(portletURL.toString());
		},
		['liferay-portlet-url']
	);
</aui:script>

<aui:script use="liferay-form">
	A.one('#<portlet:namespace />neverEnds').on(
		'change',
		function(event) {
			var formValidator = Liferay.Form.get('<portlet:namespace />fm').formValidator;

			formValidator.validateField('<portlet:namespace />maxSubscriptionCycles');
		}
	);
</aui:script>

<aui:script use="aui-toggler">
	new A.Toggler(
		{
			animated: true,
			content: '#<portlet:namespace />neverEndsContainer .never-ends-content',
			expanded: <%= ending %>,
			header: '#<portlet:namespace />neverEndsContainer .never-ends-header',
			on: {
				animatingChange: function(event) {
					var instance = this;

					var expanded = !instance.get('expanded');

					if (expanded) {
						A.one('#<portlet:namespace />neverEndsContainer .never-ends-content').removeClass('hide');

						A.one('#<portlet:namespace />maxSubscriptionCycles').attr('disabled', false);
					}
					else {
						A.one('#<portlet:namespace />neverEndsContainer .never-ends-content').addClass('hide');

						A.one('#<portlet:namespace />maxSubscriptionCycles').attr('disabled', true);
					}
				}
			}
		}
	);
</aui:script>