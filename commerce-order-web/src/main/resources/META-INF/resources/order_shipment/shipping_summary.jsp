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

<commerce-ui:panel title="info">
    <div class="row">
        <div class="col-md-4">
            <commerce-ui:info-box title="courier-detail">
                Shipping ID <clay:link href="#" label="1ZKAS23JLKNASDJL" />

            </commerce-ui:info-box>
        </div>
        <div class="col-md-4">
            <commerce-ui:info-box title="barcode">
                International Article Number 07053 23123

            </commerce-ui:info-box>
        </div>
        <div class="col-md-4">
            <commerce-ui:info-box title="shipment-status">
                <clay:label
                    label="ready-to-ship"
                    style="danger"
                />
            </commerce-ui:info-box>
        </div>
    </div>
</commerce-ui:panel>