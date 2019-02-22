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
String redirectUrlAttribute = (String)request.getAttribute("redirectUrl");

String redirectUrl = URLCodec.decodeURL(redirectUrlAttribute);

String tokenAttribute = (String)request.getAttribute("token");

String token = URLDecoder.decode(tokenAttribute, "UTF-8");
%>

<form action="<%= redirectUrl %>" class="hide" id="formAuthorizeNet" method="post" name="formAuthorizeNet">
	<input name="token" type="hidden" value="<%= token %>" />
	<button id="btnContinue">Continue</button>
</form>

<script>
	window.onload = function() {
		document.querySelector('form').submit();
	}
</script>