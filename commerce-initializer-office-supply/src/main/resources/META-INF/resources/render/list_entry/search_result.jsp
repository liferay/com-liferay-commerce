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

<%@ include file="/render/init.jsp" %>

<%
CPContentHelper cpContentHelper = (CPContentHelper)request.getAttribute(CPContentWebKeys.CP_CONTENT_HELPER);

CPCatalogEntry cpCatalogEntry = cpContentHelper.getCPCatalogEntry(request);
CPSku cpSku = cpContentHelper.getDefaultCPSku(cpCatalogEntry);

long cpDefinitionId = cpCatalogEntry.getCPDefinitionId();
List<CPSku> cpSkus = cpCatalogEntry.getCPSkus();
%>

<li class="card-page-item-asset">
				<div class="card">
					<div class="product-image-container">
						<img class="product-image" src="<%= cpCatalogEntry.getDefaultImageFileUrl() %>">
					</div>

					<div class="product-expand">
						<p class="card-subtitle product-sku">
															<liferay-ui:message key="multiple" />
						</p>

						<div class="card-title">
							<a href="<%= cpContentHelper.getFriendlyURL(cpCatalogEntry, themeDisplay) %>"><%= cpCatalogEntry.getName() %></a>
						</div>
					</div>

					<div class="product-footer">
						<div class="product-price">
							<liferay-commerce:price CPDefinitionId="<%= cpDefinitionId %>" />
						</div>

						<div class="product-actions">
								<c:if test="<%= cpCatalogEntry.isIgnoreSKUCombinations() %>">
									<div class="autofit-row">
										<div class="autofit-col">
											<@liferay_commerce["quantity-input"]
												CPDefinitionId=cpDefinitionId
												useSelect=false
											/>
										</div>

										<div class="autofit-col autofit-col-expand">
											<@liferay_commerce_cart["add-to-cart"]
												CPDefinitionId=cpDefinitionId
												CPInstanceId=cpInstanceId
												elementClasses="btn-block btn-primary text-truncate"
											/>
										</div>
									</div>
																</c:if>
								<c:if test="<%= !cpCatalogEntry.isIgnoreSKUCombinations() %>">
									<a class="btn btn-block btn-outline-primary text-truncate" href="<%= cpContentHelper.getFriendlyURL(cpCatalogEntry, themeDisplay) %>"><liferay-ui:message key="view-all-variants" /></a>
								</c:if>
						</div>

						<div class="product-subactions">
								<c:choose>
								<c:when test="<%= cpCatalogEntry.isIgnoreSKUCombinations() %>">
									<div class="autofit-row">
										<div class="autofit-col autofit-col-expand">
											<div class="product-list-info-compare">
												<liferay-commerce:compare-product CPDefinitionId="<%= cpDefinitionId %>" />
											</div>
										</div>

										<div class="autofit-col">
											<a class="add-to-list-link" href="#placeholder"><liferay-ui:message key="save-for-later" /></a>
										</div>
									</div>
								</c:when>
								<c:otherwise>
									<div class="autofit-row">
										<div class="autofit-col autofit-col-expand">
											<div class="autofit-section">
												<span class="available-variants"><%= cpSkus.size() %> <liferay-ui:message key="variants-available" /></span>
											</div>
										</div>
									</div>
								</c:otherwise>
															</c:choose>
						</div>
					</div>
				</div>
			</li>