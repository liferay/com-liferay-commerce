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
CommerceOrderContentDisplayContext commerceOrderContentDisplayContext = (CommerceOrderContentDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceOrder commerceOrder = commerceOrderContentDisplayContext.getCommerceOrder();

boolean manageNotesPermission = commerceOrderContentDisplayContext.hasModelPermission(commerceOrder, CommerceOrderActionKeys.MANAGE_COMMERCE_ORDER_NOTES);
boolean manageRestrictedNotesPermission = commerceOrderContentDisplayContext.hasModelPermission(commerceOrder, CommerceOrderActionKeys.MANAGE_COMMERCE_ORDER_RESTRICTED_NOTES);
%>

<portlet:actionURL name="editCommerceOrderNote" var="editCommerceOrderNoteURL">
	<portlet:param name="mvcRenderCommandName" value="editCommerceOrderNotes" />
</portlet:actionURL>

<div class="b2b-portlet-content-header">
	<div class="autofit-float autofit-row header-title-bar">
		<div class="autofit-col autofit-col-expand">
			<liferay-ui:header
				backURL="<%= redirect %>"
				title='<%= LanguageUtil.format(request, "order-x", commerceOrder.getCommerceOrderId()) %>'
			/>
		</div>
	</div>
</div>

<aui:form action="<%= editCommerceOrderNoteURL %>" cssClass="order-notes-form" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.ADD %>" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="commerceOrderId" type="hidden" value="<%= commerceOrder.getCommerceOrderId() %>" />

	<liferay-ui:error exception="<%= CommerceOrderNoteContentException.class %>" message="please-enter-valid-content" />

	<aui:model-context model="<%= CommerceOrderNote.class %>" />

	<div class="taglib-discussion">
		<c:if test="<%= manageNotesPermission || manageRestrictedNotesPermission %>">
			<div class="commerce-panel">
				<div class="commerce-panel__content">
					<div class="lfr-discussion-details">
						<liferay-ui:user-portrait
							cssClass="user-icon-lg"
							user="<%= user %>"
						/>
					</div>

					<div class="lfr-discussion-body">
						<aui:input autoFocus="<%= true %>" label="" name="content" placeholder="type-your-note-here" />

						<div class="order-notes-submit-actions">
							<c:if test="<%= manageRestrictedNotesPermission %>">
								<aui:input helpMessage="restricted-help" label="private" name="restricted" />
							</c:if>

							<aui:button-row>
								<aui:button cssClass="btn btn-primary" type="submit" />
							</aui:button-row>
						</div>
					</div>
				</div>
			</div>
		</c:if>

		<%
		Format dateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(locale, timeZone);

		for (CommerceOrderNote commerceOrderNote : commerceOrderContentDisplayContext.getCommerceOrderNotes(commerceOrder)) {
		%>

			<article class="commerce-panel">
				<div class="commerce-panel__content">
					<div class="panel-body">
						<div class="card-row">
							<div class="card-col-content">
								<div class="lfr-discussion-details">
									<liferay-ui:user-portrait
										cssClass="user-icon-lg"
										userId="<%= commerceOrderNote.getUserId() %>"
										userName="<%= commerceOrderNote.getUserName() %>"
									/>
								</div>

								<div class="lfr-discussion-body">
									<div class="lfr-discussion-message">
										<header class="lfr-discussion-message-author">

											<%
											User noteUser = commerceOrderNote.getUser();
											%>

											<aui:a cssClass="author-link" href="<%= ((noteUser != null) && noteUser.isActive()) ? noteUser.getDisplayURL(themeDisplay) : null %>">
												<%= HtmlUtil.escape(commerceOrderNote.getUserName()) %>

												<c:if test="<%= commerceOrderNote.getUserId() == user.getUserId() %>">
													(<liferay-ui:message key="you" />)
												</c:if>
											</aui:a>

											<c:if test="<%= commerceOrderNote.isRestricted() %>">
												<aui:icon image="lock" markupView="lexicon" message="private" />
											</c:if>

											<%
											Date createDate = commerceOrderNote.getCreateDate();

											String createDateDescription = LanguageUtil.getTimeDescription(request, System.currentTimeMillis() - createDate.getTime(), true);
											%>

											<span class="small">
												<liferay-ui:message arguments="<%= createDateDescription %>" key="x-ago" translateArguments="<%= false %>" />

												<c:if test="<%= createDate.before(commerceOrderNote.getModifiedDate()) %>">
													<strong onmouseover="Liferay.Portal.ToolTip.show(this, '<%= HtmlUtil.escapeJS(dateFormatDateTime.format(commerceOrderNote.getModifiedDate())) %>');">
														- <liferay-ui:message key="edited" />
													</strong>
												</c:if>
											</span>
										</header>

										<div class="lfr-discussion-message-body">
											<%= HtmlUtil.escape(commerceOrderNote.getContent()) %>
										</div>
									</div>
								</div>
							</div>

							<div class="card-col-field">
								<liferay-ui:icon-menu
									direction="left-side"
									icon="<%= StringPool.BLANK %>"
									markupView="lexicon"
									message="<%= StringPool.BLANK %>"
									showWhenSingleIcon="<%= true %>"
								>
									<portlet:renderURL var="editURL">
										<portlet:param name="mvcRenderCommandName" value="editCommerceOrderNote" />
										<portlet:param name="redirect" value="<%= currentURL %>" />
										<portlet:param name="commerceOrderNoteId" value="<%= String.valueOf(commerceOrderNote.getCommerceOrderNoteId()) %>" />
									</portlet:renderURL>

									<liferay-ui:icon
										message="edit"
										url="<%= editURL %>"
									/>

									<portlet:actionURL name="editCommerceOrderNote" var="deleteURL">
										<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
										<portlet:param name="redirect" value="<%= currentURL %>" />
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
			</article>

		<%
		}
		%>

	</div>
</aui:form>