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

<div class="row">
    <div class="col-md-6 d-flex">
        <commerce-ui:panel
                elementClasses="flex-fill"
                title="payment-method"
        >
            <div class="payment-info align-items-center d-flex">
                <clay:icon symbol="info-circle" />

                <span class="payment-name ml-3">
                    Credit Card
                </span>

                <clay:button
                    elementClasses="ml-3"
                    style="link"
                    label="edit"
                    componentId="id-test"
                />
            </div>
        </commerce-ui:panel>
    </div>

    <div class="col-md-6 d-flex">
        <commerce-ui:panel
                elementClasses="flex-fill"
                title="payment-status"
        >

            <clay:label label="Authorize and capture" style="success" />

            <clay:button
                elementClasses="ml-3"
                style="secondary"
                label="refound"
            />
        </commerce-ui:panel>
    </div>

    <div class="col-12">
        <commerce-ui:panel
                elementClasses="flex-fill"
                title="payment-transactions"
                bodyClasses="p-0"
        >
            <liferay-ui:search-container
                    id="commerceOrderItems"

            >
                <liferay-ui:search-container-row
                        className="com.liferay.commerce.model.CommerceOrderPaymentModel"
                        cssClass="entry-display-style"
                        keyProperty="CommerceOrderPaymentId"
                        modelVar="commerceOrderItem"
                >

                    <liferay-ui:search-container-column-text
                            name="type"
                    >
                        <clay:label label="authorize-and-capture" style="error" />
                    </liferay-ui:search-container-column-text>

                    <liferay-ui:search-container-column-text
                            name="amount"
                    >
                            $ 2,230.50
                    </liferay-ui:search-container-column-text>

                    <liferay-ui:search-container-column-text
                            name="date"
                    >
                            01.09.2019
                    </liferay-ui:search-container-column-text>

                    <liferay-ui:search-container-column-text
                            name="succesfull"
                    >
                            true
                    </liferay-ui:search-container-column-text>

                    <liferay-ui:search-container-column-text
                            name="additional-field"
                    >
                        <div>Exp. date = 01.22</div>
                        <div>Name on card = Mario Rossi</div>
                        <div>Card type = Mastercard</div>
                        <div>Last four =  5378</div>
                    </liferay-ui:search-container-column-text>

                </liferay-ui:search-container-row>


            </liferay-ui:search-container>
        </commerce-ui:panel>
    </div>
</div>