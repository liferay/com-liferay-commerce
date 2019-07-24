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

<%@ include file="/header/init.jsp" %>

<%
    String variant = (String)request.getAttribute("liferay-commerce:header:variant");
%>

<div>
    <!-- Header variant: <%= variant %> -->

    <div class="container-fluid mb-3">
        <div class="container d-flex">
            <div class="d-flex align-items-center flex-grow-1">
                <div class="py-2 pr-3 border-right bg-white d-flex">
                    <span class="ml-3 sticker sticker-primary sticker-xl">
                    <span class="sticker-overlay">
                        <img alt="thumbnail" class="img-fluid" src="//via.placeholder.com/50">
                    </span>
                    </span>
                </div>
                <div class="px-3">
                    <div class="d-flex">
                        <h3 class="mb-0">53606</h3><span class="ml-2 badge badge-secondary"><span class="badge-item badge-item-expand">V1.1</span></span>
                    </div><span class="label label-warning"><span class="label-item label-item-expand">awaiting buyer</span></span>
                </div>
                <table class="small">
                    <tbody>
                        <tr>
                            <td>Total:</td>
                            <td><strong>$5,000.50</strong></td>
                        </tr>
                        <tr>
                            <td>Items:</td>
                            <td>17</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="commerce-header__actions-wrapper align-items-center d-none d-xl-flex">
                <div class="commerce-header__actions-primary px-3 d-flex border-right">
                    <div class="commerce-header__assign-to d-none d-xl-flex align-items-center px-3 mr-3 border-right">
                        <label for="assigned-to mr-3 small">Assigned to:</label>
                        <div class="btn-group dropdown ml-3" role="group">
                            <button class="btn btn-secondary dropdown-toggle" type="button" aria-expanded="false" aria-haspopup="true" data-toggle="dropdown" id="theDropdownToggleId">Admin<span class="inline-item inline-item-before"><svg class="lexicon-icon lexicon-icon-blogs" focusable="false" role="presentation"><use href="/images/icons/icons.svg#share"></use></svg></span></button>
                            <div aria-labelledby="theDropdownToggleId" class="dropdown-menu">
                                <form>
                                    <div class="dropdown-section">
                                        <div class="input-group input-group-sm">
                                            <div class="input-group-item">
                                                <input class="form-control input-group-inset input-group-inset-after" placeholder="Search for..." type="text"><span class="input-group-inset-item input-group-inset-item-after"><button class="btn btn-unstyled" type="button"><svg class="lexicon-icon lexicon-icon-search" focusable="false" role="presentation"><use href="/images/icons/icons.svg#search"></use></svg></button></span></div>
                                        </div>
                                    </div>
                                </form>
                                <form>
                                    <div class="inline-scroller">
                                        <div class="dropdown-subheader" role="presentation">My actions</div>
                                        <ul class="list-unstyled">
                                            <li><a class="dropdown-item" href="#1">D Structure</a></li>
                                            <li><a class="dropdown-item" href="#1">F Structure</a></li>
                                        </ul>
                                        <div class="dropdown-subheader" role="presentation">Assign by role</div>
                                        <ul class="list-unstyled">
                                            <li><a class="dropdown-item" href="#1">D Structure</a></li>
                                            <li><a class="dropdown-item" href="#1">F Structure</a></li>
                                            <li><a class="disabled dropdown-item" href="#1" tabindex="-1">H Structure</a></li>
                                            <li><a class="dropdown-item" href="#1">J Structure</a></li>
                                            <li><a class="dropdown-item" href="#1">L Structure</a></li>
                                        </ul>
                                    </div>
                                </form>
                                <div class="dropdown-caption">Showing 7 of 203 Users</div>
                                <div class="dropdown-section">
                                    <button class="btn btn-block btn-secondary">More</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <button class="btn btn-secondary mr-1">Block</button>
                    <button class="btn btn-secondary mr-1">Save as Draft</button>
                    <button class="btn btn-primary">Publish</button>
                </div>
                <div class="commerce-header__actions-secondary pl-3 align-items-center d-flex">
                    <button class="btn btn-monospaced btn-primary dropdown-toggle mr-1" type="button" aria-expanded="false" aria-haspopup="true" data-toggle="dropdown" id="theDropdownActions">
                        <svg class="lexicon-icon lexicon-icon-blogs" focusable="false" role="presentation">
                            <use href="/images/icons/icons.svg#blogs"></use>
                        </svg>
                    </button>
                    <div aria-labelledby="theDropdownActions" class="dropdown-menu dropdown-menu-right">
                        <ul class="list-unstyled">
                            <li><a class="dropdown-item" href="#1">Option 1</a></li>
                            <li><a class="disabled dropdown-item" href="#1" tabindex="-1">Option 2</a></li>
                            <li><a class="dropdown-item" href="#1">Option 3</a></li>
                        </ul>
                    </div>
                    <button class="btn btn-monospaced btn-primary" disabled="" type="button">
                        <svg class="lexicon-icon lexicon-icon-blogs" focusable="false" role="presentation">
                            <use href="/images/icons/icons.svg#blogs"></use>
                        </svg>
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>