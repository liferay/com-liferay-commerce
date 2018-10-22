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

boolean subscriptionEnabled = BeanParamUtil.getBoolean(cpDefinition, request, "subscriptionEnabled", false);
long subscriptionCycleLength = BeanParamUtil.getLong(cpDefinition, request, "subscriptionCycleLength");
String subscriptionCyclePeriod = BeanParamUtil.getString(cpDefinition, request, "subscriptionCyclePeriod", CPConstants.SUBSCRIPTION_CYCLE_DAY);
long maxSubscriptionCyclesNumber = BeanParamUtil.getLong(cpDefinition, request, "maxSubscriptionCyclesNumber");

boolean ending = maxSubscriptionCyclesNumber > 0;
%>

<portlet:actionURL name="editProductDefinition" var="editProductDefinitionSubscriptionInfoActionURL" />

<aui:form action="<%= editProductDefinitionSubscriptionInfoActionURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="updateSubscriptionInfo" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="cpDefinitionId" type="hidden" value="<%= cpDefinitionId %>" />

	<aui:model-context bean="<%= cpDefinition %>" model="<%= CPDefinition.class %>" />

	<aui:fieldset-group markupView="lexicon">
		<aui:fieldset>
			<aui:input checked="<%= subscriptionEnabled %>" label="enable-subscription" name="subscriptionEnabled" type="toggle-switch" value="<%= subscriptionEnabled %>" />

			<div class="<%= subscriptionEnabled ? StringPool.BLANK : "hide" %>" id="<portlet:namespace />subscriptionOptions">
				<aui:select name="subscriptionCyclePeriod">

					<%
					for (String subscriptionPeriod : CPConstants.SUBSCRIPTION_CYCLES) {
					%>

						<aui:option data-label="<%= LanguageUtil.get(request, subscriptionPeriod) %>" label="<%= subscriptionPeriod %>" selected="<%= subscriptionPeriod.equals(subscriptionCyclePeriod) %>" value="<%= subscriptionPeriod %>" />

					<%
					}
					%>

				</aui:select>

				<div id="<portlet:namespace />cycleLengthContainer">
					<aui:input name="subscriptionCycleLength" suffix="<%= subscriptionCyclePeriod %>" value="<%= String.valueOf(subscriptionCycleLength) %>">
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
						<aui:input disabled="<%= ending ? false : true %>" helpMessage="max-subscription-cycles-number-help" label="end-after" name="maxSubscriptionCyclesNumber" suffix='<%= LanguageUtil.get(request, "cycles") %>' value="<%= String.valueOf(maxSubscriptionCyclesNumber) %>">
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
			</div>
		</aui:fieldset>
	</aui:fieldset-group>

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />

		<aui:button cssClass="btn-lg" href="<%= catalogURL %>" type="cancel" />
	</aui:button-row>
</aui:form>

<aui:script>
	Liferay.Util.toggleBoxes('<portlet:namespace />subscriptionEnabled', '<portlet:namespace />subscriptionOptions');
</aui:script>

<aui:script use="aui-base">
	A.one('#<portlet:namespace />subscriptionCyclePeriod').on(
		'change',
		function(event) {
			var selectedOption = this.one('option:selected');

			A.all('#<portlet:namespace />cycleLengthContainer .input-group-addon').html(selectedOption.getData('label'));
		}
	);
</aui:script>

<aui:script use="liferay-form">
	A.one('#<portlet:namespace />neverEnds').on(
	'change',
		function(event) {
			var formValidator = Liferay.Form.get('<portlet:namespace />fm').formValidator;

			formValidator.validateField('<portlet:namespace />maxSubscriptionCyclesNumber');
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

						A.one('#<portlet:namespace />maxSubscriptionCyclesNumber').attr('disabled', false);
					}
					else {
						A.one('#<portlet:namespace />neverEndsContainer .never-ends-content').addClass('hide');

						A.one('#<portlet:namespace />maxSubscriptionCyclesNumber').attr('disabled', true);
					}
				}
			}
		}
	);
</aui:script>