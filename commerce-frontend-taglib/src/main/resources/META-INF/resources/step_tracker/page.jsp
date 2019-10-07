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

<%@ include file="/step_tracker/init.jsp" %>

<%
String spritemap = (String)request.getAttribute("liferay-commerce:step-tracker:spritemap");
List<StepModel> steps = (List<StepModel>)request.getAttribute("liferay-commerce:step-tracker:steps");
String stepTrackerId = randomNamespace + "step-tracker-id";
JSONSerializer jsonSerializer = JSONFactoryUtil.createJSONSerializer();
%>

<div class="step-tracker-root" id="<%= stepTrackerId %>"></div>

<aui:script require="commerce-frontend-js/js/step_tracker/entry.es as StepTracker">
	new StepTracker.default(
		"<%= stepTrackerId %>",
		"<%= stepTrackerId %>",
		{
			steps: <%= jsonSerializer.serializeDeep(steps) %>,
			spritemap: "<%= spritemap %>"
		}
	);
</aui:script>