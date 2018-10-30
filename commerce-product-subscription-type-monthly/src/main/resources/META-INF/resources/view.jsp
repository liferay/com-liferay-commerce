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
MonthlyCPSubscriptionTypeDisplayContext monthlyCPSubscriptionTypeDisplayContext = (MonthlyCPSubscriptionTypeDisplayContext)request.getAttribute("view.jsp-monthlyCPSubscriptionTypeDisplayContext");

int selectedMonthlyMode = monthlyCPSubscriptionTypeDisplayContext.getSelectedMonthlyMode();
%>

<aui:select label="mode" name="subscriptionTypeSettings--monthlyMode--" onChange='<%= "event.preventDefault(); changeMonthlyCPSubscriptionTypeSettingsMode();" %>'>

	<%
	for (int mode : MonthlyCPSubscriptionTypeConstants.MODES) {
	%>

		<aui:option label="<%= MonthlyCPSubscriptionTypeConstants.getMonthlyCPSubscriptionTypeModeLabel(mode) %>" selected="<%= selectedMonthlyMode == mode %>" value="<%= mode %>" />

	<%
	}
	%>

</aui:select>

<div class="<%= (selectedMonthlyMode == MonthlyCPSubscriptionTypeConstants.MODE_EXACT_DAY_OF_MONTH) ? StringPool.BLANK : "hide" %>" id="<portlet:namespace />monthDayInputContainer">
	<aui:input label="on" name="subscriptionTypeSettings--monthDay--" value="<%= monthlyCPSubscriptionTypeDisplayContext.getMonthDay() %>">
		<aui:validator name="digits" />
		<aui:validator name="max">31</aui:validator>
		<aui:validator name="min">1</aui:validator>
	</aui:input>
</div>

<aui:script>
	function changeMonthlyCPSubscriptionTypeSettingsMode () {
		var A = AUI();

		if (A.one('#<portlet:namespace />monthlyMode').val() == '<%= MonthlyCPSubscriptionTypeConstants.MODE_EXACT_DAY_OF_MONTH %>') {
			A.one('#<portlet:namespace />monthDayInputContainer').removeClass('hide');
		}
		else {
			if (!A.one('#<portlet:namespace />monthDayInputContainer').hasClass('hide')) {
				A.one('#<portlet:namespace />monthDayInputContainer').addClass('hide');
			}
		}
	}
</aui:script>