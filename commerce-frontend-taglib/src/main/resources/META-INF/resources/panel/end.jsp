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

<%@ include file="/panel/init.jsp" %>

    </div>

    <c:if test="<%= Validator.isNotNull(showMoreId) || Validator.isNotNull(showMoreRefId) || Validator.isNotNull(showMoreUrl) %>">
        <div
                class="card-footer p-0 border-top"
            id="<%= showMoreButtonWrapperId %>"
        >
            <a
                id="<%= showMoreButtonId %>"
                class="border-0 btn btn-secondary w-100"
                href="<%= Validator.isNotNull(showMoreUrl) ? showMoreUrl : '#' %>"
            >
                Show more
            </a>
        </div>
    </c:if>
</div>

<c:if test="<%= Validator.isNotNull(headerActionUrl) %>">
    <% String modalId = randomNamespace + "modal-root"; %>

    <div id="<%= modalId %>"></div>

    <aui:script require="commerce-frontend-react@1.0.0/commerce_modal/entry.es as Modal">
        var modal = new Modal.default(
            "<%= modalId %>",
            "<%= modalId %>",
            {
                url: "<%= headerActionUrl %>",
                size: "lg",
                spritemap: "<%= spritemap %>"
            }
        );

        document.querySelector('<%= headerActionLinkId %>')
            .addEventListener('onClick', () => modal.open());
    </aui:script>
</c:if>

<c:if test="<%= Validator.isNotNull(showMoreRefId) %>">
    <aui:script>
        (function(){
            const button = document.getElementById('<%= showMoreButtonId %>');
            const buttonWrapper = document.getElementById('<%= showMoreButtonWrapperId %>');
            const hiddenElement = document.getElementById('<%= showMoreRefId %>');

            button.addEventListener(
                'click',
                (e) => {
                    e.preventDefault();
                    hiddenElement.classList.remove('d-none');
                    buttonWrapper.parentNode.removeChild(buttonWrapper);
                }
            );
        })()
    </aui:script>
</c:if>