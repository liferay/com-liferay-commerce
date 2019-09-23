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
long commerceOrderId = commerceOrderEditDisplayContext.getCommerceOrderId();
Format dateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(locale, timeZone);
%>

<portlet:actionURL name="editCommerceOrderNote" var="editCommerceOrderNoteURL">
	<portlet:param name="mvcRenderCommandName" value="editCommerceOrder" />
	<portlet:param name="screenNavigationCategoryKey" value="<%= CommerceOrderScreenNavigationConstants.CATEGORY_KEY_COMMERCE_ORDER_NOTES %>" />
</portlet:actionURL>


<div class="container">
			<commerce-ui:panel
				title="notes"
			>
				<aui:form action="<%= editCommerceOrderNoteURL %>" method="post" name="fm">
					<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.ADD %>" />
					<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
					<aui:input name="commerceOrderId" type="hidden" value="<%= commerceOrderId %>" />

					<liferay-ui:error exception="<%= CommerceOrderNoteContentException.class %>" message="please-enter-valid-content" />

					<aui:model-context model="<%= CommerceOrderNote.class %>" />

					<% for (CommerceOrderNote commerceOrderNote : commerceOrderEditDisplayContext.getCommerceOrderNotes()) { %>
						<article class="card-tab-group lfr-discussion">
							<div class="panel border-bottom m-0">
								<div class="panel-body px-0 py-4">
									<div class="row">
										<div class="col-auto">
											<liferay-ui:user-portrait
												cssClass="user-icon-lg"
												userId="<%=commerceOrderNote.getUserId()%>"
												userName="<%=commerceOrderNote.getUserName()%>"
											/>
										</div>

										<div class="col">
											<header class="lfr-discussion-message-author">
												<aui:a
													cssClass="author-link"
													href="<%=
														((commerceOrderNote.getUser() != null) && commerceOrderNote.getUser().isActive())
															? commerceOrderNote.getUser().getDisplayURL(themeDisplay)
															: null
													%>"
												>
													<%=commerceOrderNote.getUserName() %>

													<c:if test="<%=commerceOrderNote.getUserId() == user.getUserId() %>">
														(<liferay-ui:message key="you" />)
													</c:if>
												</aui:a>

												<c:if test="<%=commerceOrderNote.isRestricted() %>">
													<aui:icon image="lock" markupView="lexicon" message="private" />
												</c:if>
											</header>

											<div class="lfr-discussion-message-body">
												<%= HtmlUtil.escape(commerceOrderNote.getContent()) %>
											</div>
										</div>

										<div class="col-auto d-flex align-items-center">
											<span class="small">
												<liferay-ui:message
														arguments="<%=LanguageUtil.getTimeDescription(request, System.currentTimeMillis() - commerceOrderNote.getCreateDate().getTime(), true)%>"
														key="x-ago"
														translateArguments="<%= false %>"
												/>

												<c:if test="<%=commerceOrderNote.getCreateDate().before(commerceOrderNote.getModifiedDate()) %>">
													<strong onmouseover="Liferay.Portal.ToolTip.show(this, '<%=HtmlUtil.escapeJS(dateFormatDateTime.format(commerceOrderNote.getModifiedDate()))%>');">
														- <liferay-ui:message key="edited" />
													</strong>
												</c:if>
											</span>
										</div>

										<div class="col-auto d-flex align-items-center">
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
					<% } %>
					<div class="taglib-discussion">
						<aui:fieldset cssClass="add-comment">
							<div class="panel">
								<div class="panel-body px-0 py-4">
									<div class="lfr-discussion-details">
										<liferay-ui:user-portrait
											cssClass="user-icon-lg"
											user="<%= user %>"
										/>
									</div>
									<div class="lfr-discussion-body">
										<aui:input autoFocus="<%= true %>" label="" name="content" placeholder="type-your-note-here" />
										<aui:input helpMessage="restricted-help" label="private" name="restricted" type="toggle-switch" />
										<aui:button-row>
											<aui:button cssClass="btn-large btn-primary" type="submit" />
										</aui:button-row>
									</div>
								</div>
							</div>
						</aui:fieldset>
					</div>
				</aui:form>
			</commerce-ui:panel>
</div>

