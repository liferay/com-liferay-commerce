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

<commerce-ui:panel
	bodyClasses="p-0"
	title="emails"
>
	<div class="list-group list-group-no-bordered mb-0 text-body">

		<%
		for (CommerceOrderNote commerceOrderNote : commerceOrderEditDisplayContext.getCommerceOrderNotes()) {
		%>

			<div class="list-group-item">
				<div class="row">
					<div class="col-auto">
						<liferay-ui:user-portrait
							cssClass="user-icon-lg"
							userId="<%= commerceOrderNote.getUserId() %>"
							userName="<%= commerceOrderNote.getUserName() %>"
						/>
					</div>

					<div class="col">
						<div class="mb-3 row">
							<div class="col">
								<h5 class="mb-0">Author name</h5>

								<small>author@email.com</small>
							</div>

							<div class="col-auto">
								<clay:label
									label="Order Placed"
									style="success"
								/>
							</div>

							<div class="col-auto">
								<small>6:15pm</small>
							</div>
						</div>

						<div class="row">
							<div class="col">
								<h4>
									Lorem ipsum dolor
								</h4>

								<div>
									Praesent sapien massa, convallis a pellentesque nec, egestas non nisi. Curabitur non nulla sit amet nisl tempus convallis quis ac lectus. Vestibulum ac diam sit amet quam vehicula elementum sed sit amet dui.
								</div>
							</div>

							<div class="align-items-center col-auto d-flex">
								<liferay-ui:icon-menu
									direction="left"
									icon="<%= StringPool.BLANK %>"
									markupView="lexicon"
									message="<%= StringPool.BLANK %>"
									showWhenSingleIcon="<%= true %>"
									triggerCssClass="btn btn-unstyled component-action text-secondary"
								>
									<portlet:renderURL var="editURL">
										<portlet:param name="mvcRenderCommandName" value="editCommerceOrderNote" />
										<portlet:param name="redirect" value="#" />
										<portlet:param name="commerceOrderNoteId" value="<%= String.valueOf(commerceOrderNote.getCommerceOrderNoteId()) %>" />
									</portlet:renderURL>

									<liferay-ui:icon
										message="edit"
										url="<%= editURL %>"
									/>

									<portlet:actionURL name="editCommerceOrderNote" var="deleteURL">
										<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
										<portlet:param name="redirect" value="#" />
										<portlet:param name="commerceOrderNoteId" value="<%= String.valueOf(commerceOrderNote.getCommerceOrderNoteId()) %>" />
									</portlet:actionURL>

									<liferay-ui:icon-delete
										label="<%= true %>"
										url="<%= deleteURL %>"
									/>
								</liferay-ui:icon-menu>
							</div>
						</div>
					</div>
				</div>
			</div>

		<%
		}
		%>

	</div>
</commerce-ui:panel>