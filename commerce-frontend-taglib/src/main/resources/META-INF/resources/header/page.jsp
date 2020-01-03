<%@ include file="/header/init.jsp" %>

<%@ taglib uri="http://liferay.com/tld/commerce-ui" prefix="commerce-ui" %>

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

<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>

<%@ page import="java.util.HashMap" %><%@
page import="java.util.Map" %>

<div class="bg-white border-bottom commerce-header<%= fullWidth ? " container-fluid" : StringPool.BLANK %><%= Validator.isNotNull(wrapperCssClasses) ? StringPool.SPACE + wrapperCssClasses : StringPool.BLANK %>">
	<div class="container<%= Validator.isNotNull(cssClasses) ? StringPool.SPACE + cssClasses : StringPool.BLANK %>">
		<div class="d-lg-flex py-2">
			<div class="align-items-center d-flex flex-grow-1">
				<div class="flex-grow-1 row">
					<c:if test="<%= Validator.isNotNull(thumbnailUrl) %>">
						<div class="col-auto">
							<span class="sticker sticker-primary sticker-xl">
								<span class="sticker-overlay">
									<img alt="thumbnail" class="img-fluid" src="<%= thumbnailUrl %>" />
								</span>
							</span>
						</div>
					</c:if>

					<div class="col">
						<div class="row">
							<div class="col-auto">
								<h3 class="mb-0">
									<%= HtmlUtil.escape(title) %>
								</h3>

								<c:if test="<%= bean instanceof WorkflowedModel %>">

									<%
									WorkflowedModel workflowedModel = (WorkflowedModel)bean;
									%>

									<div>
										<aui:workflow-status bean="<%= bean %>" model="<%= model %>" showHelpMessage="<%= false %>" showIcon="<%= false %>" showLabel="<%= false %>" status="<%= workflowedModel.getStatus() %>" />
									</div>
								</c:if>
							</div>

							<div class="border-left col d-flex flex-column justify-content-center">
								<small class="d-block">
									<span class="header-info-title">
										<%= LanguageUtil.get(request, "erc") %>:
									</span>

									<strong class="header-info-value">
										<%= erc %>
									</strong>

									<liferay-ui:icon-help message='<%= LanguageUtil.get(request, "external-reference-code") %>' />

									<c:if test="<%= Validator.isNotNull(ercEditUrl) %>">
										<button class="btn btn-link btn-unstyled header-info-link ml-3" id="erc-edit-modal-opener">
											<%= LanguageUtil.get(request, "edit") %>
										</button>

										<aui:script require="commerce-frontend-js/utilities/eventsDefinitions.es as events">
											document.querySelector("#erc-edit-modal-opener").addEventListener("click", function(e) {
												e.preventDefault();
												Liferay.fire(events.OPEN_MODAL, {id: "erc-edit-modal"});
											})
										</aui:script>

										<commerce-ui:modal
											id="erc-edit-modal"
											refreshPageOnClose="<%= true %>"
											title='<%= LanguageUtil.get(request, "edit-erc") %>'
											url="<%= ercEditUrl %>"
										/>
									</c:if>
								</small>

								<small class="d-block">
									<span class="header-info-title">
										<%= LanguageUtil.get(request, "id") %>:
									</span>

									<strong class="header-info-value">
										2837162 <!-- TODO: id to be added -->
									</strong>

									<liferay-ui:icon-help message='<%= LanguageUtil.get(request, "identification-number") %>' />
								</small>
							</div>
						</div>
					</div>

					<div class="col-auto d-lg-none">

						<%
						Map<String, String> headerTogglerData = new HashMap<>();

						headerTogglerData.put("target", "#navbarNavAltMarkup");
						headerTogglerData.put("toggle", "collapse");
						%>

						<clay:button
							data="<%= headerTogglerData %>"
							elementClasses="navbar-toggler p-3"
							icon="bars"
							spritemap='<%= themeDisplay.getPathThemeImages() + "/lexicon/icons.svg" %>'
							style="secondary"
							type="button"
						/>
					</div>
				</div>
			</div>

			<div class="collapse d-lg-flex" id="navbarNavAltMarkup">
				<div class="align-self-center row">
					<c:if test="<%= true %>">
						<div class="col col-12 col-lg-auto d-flex mt-3 mt-lg-0">
							<small class="d-block">
								<span class="d-block header-info-title">
									<%= LanguageUtil.get(request, "assigned-to") %>:
								</span>

								<strong class="d-block header-info-value">
									admin <!-- TODO: adding current assignee -->
								</strong>
							</small>

							<c:if test="<%= Validator.isNotNull(assignerModalUrl) %>">
								<button class="btn btn-secondary ml-3" id="edit-assignee-modal-opener">
									<%= LanguageUtil.get(request, "assign-to") %>
								</button>

								<aui:script require="commerce-frontend-js/utilities/eventsDefinitions.es as events">
									document.querySelector("#edit-assignee-modal-opener").addEventListener("click", function(e) {
										e.preventDefault();
										Liferay.fire(events.OPEN_MODAL, {id: "edit-assignee-modal"});
									})
								</aui:script>

								<commerce-ui:modal
									id="edit-assignee-modal"
									refreshPageOnClose="<%= true %>"
									title='<%= LanguageUtil.get(request, "edit-current-assignee") %>'
									url="<%= ercEditUrl %>"
								/>
							</c:if>
						</div>
					</c:if>

					<c:if test="<%= actions != null %>">
						<div class="col-12 col-lg-auto header-actions mt-3 mt-lg-0">

							<%
							for (HeaderActionModel action : actions) {
							%>

								<clay:link
									buttonStyle="<%= action.getStyle() %>"
									elementClasses="mr-1"
									href="<%= action.getHref() %>"
									id="<%= action.getId() %>"
									label="<%= action.getLabel() %>"
								/>

							<%
							}
							%>

						</div>
					</c:if>

					<div class="col-auto align-items-center border-left d-flex pl-3">
						<clay:dropdown-menu
							buttonType="button"
							dropdownItems="<%= dropdownItems %>"
							icon="ellipsis-v"
						/>

						<c:if test="<%= Validator.isNotNull(previewUrl) %>">
							<clay:link
								elementClasses="btn btn-outline-borderless btn-outline-secondary btn-sm text-primary"
								href="<%= previewUrl %>"
								icon="shortcut"
							/>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<aui:script require="commerce-frontend-js/utilities/index.es as utilities">
	var pageHeader = document.querySelector(".page-header");
	var commerceHeader = document.querySelector(".commerce-header");

	function updateMenuDistanceFromTop() {
		var distanceFromTop = commerceHeader.getClientRects()[0].bottom;
		pageHeader.style.top = distanceFromTop + "px"
	}

	var debouncedUpdateMenuDistanceFromTop = utilities.debounce(updateMenuDistanceFromTop, 200)

	if(pageHeader) {
	    pageHeader.classList.add("sticky-header-menu");
	    updateMenuDistanceFromTop()
	}

	window.addEventListener("resize", debouncedUpdateMenuDistanceFromTop)
</aui:script>