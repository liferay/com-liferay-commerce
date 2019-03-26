<div class="commerce-topbar minium-topbar">
	<div class="minium-topbar__start">
		<#if back_url?has_content>
			<a class="commerce-topbar-button" href="${back_url}">
				<svg class="commerce-icon commerce-topbar-button__icon">
					<use href="${themeDisplay.getPathThemeImages()}/commerce-icons.svg#back" />
				</svg>

				<span class="commerce-topbar-button__label">
					${languageUtil.get(locale, "back")}
				</span>
			</a>
		</#if>
		<label class="commerce-topbar-button js-toggle-search" for="commerce-search-input">
			<svg class="commerce-icon commerce-topbar-button__icon commerce-topbar-button__icon--not-active">
				<use href="${themeDisplay.getPathThemeImages()}/commerce-icons.svg#search" />
			</svg>
			<svg class="commerce-icon commerce-topbar-button__icon commerce-topbar-button__icon--active">
				<use href="${themeDisplay.getPathThemeImages()}/commerce-icons.svg#close" />
			</svg>
		</label>
	</div>

	<div class="minium-topbar__middle">
		<#if show_top_menu>
			<@site_navigation_menu_sub_navigation default_preferences=freeMarkerPortletPreferences.getPreferences("portletSetupPortletDecoratorId", "barebone") />
		</#if>
	</div>

	<div class="minium-topbar__end">
		<div class="minium-topbar__account-selector-wrapper">
			<@liferay_commerce_ui["account-selector"] />
		</div>

		<div class="minium-topbar__cart-wrapper">
			<@liferay_commerce_ui["mini-cart"] />
		</div>
	</div>

	<div class="minium-topbar__search">
		<@liferay_commerce_ui["search-bar"] id="search-bar" />
	</div>
</div>