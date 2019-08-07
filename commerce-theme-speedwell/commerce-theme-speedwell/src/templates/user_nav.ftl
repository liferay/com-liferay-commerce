<div class="speedwell-user-nav" tabindex="0">
	<#if is_signed_in>
		<div class="speedwell-user-nav__header">
			<div>
				<p>${languageUtil.get(request, "your-wishlist")}</p>
			</div>

			<div>
				<p>${languageUtil.get(request, "your-account")}</p>
			</div>
		</div>

		<div class="speedwell-user-nav__menu">
			<div class="user-wishlist">
				<!-- a class="main-link main-link--sub" href="${userManagementUrl}">
					<div class="main-link__label">${my_profile_text}</div>
				</a -->

				<a class="main-link main-link--sub" href="${wishlistUrl}">
					<div class="main-link__label">${wish_lists_text}</div>
				</a>

				<!-- a class="main-link main-link--sub" href="${notification_url}">
					<div class="main-link__label">
						${notifications_text}
						<div class="speedwell-notification-badge">${notification_count}</div>
					</div>
				</a -->

			</div>

			<div class="user-account">
				<@site_navigation_menu_account default_preferences=freeMarkerPortletPreferences.getPreferences("portletSetupPortletDecoratorId", "barebone") />

				<#if show_sign_out>
					<a class="main-link main-link--sub" href="${sign_out_url}">
						<div class="main-link__label">${sign_out_text}</div>
					</a>
				</#if>
			</div>
		</div>

		<#assign
			userImageCssClass = ""
		/>

		<#if notification_count gt 0>
			<#assign
				userImageCssClass = "has-notification"
			/>
		</#if>

		<!-- div class="speedwell-user-nav__avatar ${userImageCssClass}">
			<@liferay_ui["user-portrait"] user=user />
		</div>

		<div class="speedwell-user-nav__name">${user_name}</div -->

	<#else>
		<div class="speedwell-user-nav__sign-in">
			<a class="main-link" href="${sign_in_url}">
				<div class="main-link__label">${sign_in_text}</div>
			</a>
		</div>
	</#if>
</div>