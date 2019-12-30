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

<%@ include file="/modal_content/init.jsp" %>

	</div>

	<c:if test="<%= Validator.isNotNull(submitButtonLabel) || showCancelButton || showSubmitButton %>">
		<div class="modal-iframe-footer">
			<c:if test="<%= showCancelButton %>">
				<button class="btn btn-secondary ml-3 modal-closer"><%= LanguageUtil.get(request, "cancel") %></button>
			</c:if>

			<c:if test="<%= showSubmitButton || Validator.isNotNull(submitButtonLabel) %>">
				<button class="btn btn-primary form-submitter ml-3">
					<%= Validator.isNotNull(submitButtonLabel) ? submitButtonLabel : LanguageUtil.get(request, "submit") %>
				</button>
			</c:if>
		</div>
	</c:if>
</div>

<aui:script require="commerce-frontend-js/utilities/eventsDefinitions.es as events, commerce-frontend-js/utilities/index.es as utilities">
	document.querySelectorAll(".modal-closer").forEach(function(trigger) {
		trigger.addEventListener("click", function(e) {
		  e.preventDefault();
		  window.parent.Liferay.fire(events.CLOSE_MODAL);
		})
	})

	document.querySelectorAll(".form-submitter").forEach(function(trigger) {
		trigger.addEventListener("click", function(e) {
			e.preventDefault();
			var form = document.querySelector("form");
			if(form) {
				submitForm(form);
			} else {
				throw new Error("no forms found");
			}
		})
	})

	var iframeContent = document.querySelector(".modal-iframe-content");
	var iframeFooter = document.querySelector(".modal-iframe-footer");

	if(iframeContent && iframeFooter) {
		function adjustBottomSpace() {
			iframeContent.style.marginBottom = iframeFooter.offsetHeight + "px"
		}

		var debouncedAdjustBottomSpace = utilities.debounce(adjustBottomSpace, 300);

		adjustBottomSpace();

		window.addEventListener("resize", debouncedAdjustBottomSpace);
	}

</aui:script>