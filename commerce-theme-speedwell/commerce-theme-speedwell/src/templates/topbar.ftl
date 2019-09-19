<div class="commerce-topbar ${speedwell_topbar_css_class}">
	<div class="speedwell-topbar__menu speedwell-main-menu">
		<button class="commerce-topbar-button speedwell-main-menu__open js-toggle-main-menu">
			<svg class="commerce-icon">
				<use href="${themeDisplay.getPathThemeImages()}/commerce-icons.svg#icon-menu" />
			</svg>
		</button>

		<div class="speedwell-main-menu__link-wrapper">
			<button class="commerce-topbar-button js-toggle-main-menu">
				<svg class="commerce-icon">
					<use href="${themeDisplay.getPathThemeImages()}/commerce-icons.svg#close" />
				</svg>
			</button>

			<div class="speedwell-main-menu__links">
				<@site_navigation_menu_main default_preferences=freeMarkerPortletPreferences.getPreferences("portletSetupPortletDecoratorId", "barebone") />
			</div>
		</div>
	</div>

	<div class="speedwell-topbar__logo">
		<div class="speedwell-logo">
			<a class="${logo_css_class}" href="${site_default_url}" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
				<svg class="commerce-icon speedwell-logo-icon">
					<use href="${themeDisplay.getPathThemeImages()}/commerce-icons.svg#speedwell-logo" />
				</svg>
			</a>
		</div>
	</div>

	<div class="speedwell-topbar__actions">
		<div class="speedwell-topbar__search speedwell-search">
			<div class="commerce-topbar-button js-toggle-search">
				<svg class="commerce-icon commerce-topbar-button__icon">
					<use href="${themeDisplay.getPathThemeImages()}/commerce-icons.svg#search" />
				</svg>
			</div>

			<div class="speedwell-search__bar-wrapper">
				<div class="speedwell-search__bar">
					<div class="commerce-topbar-button">
						<svg class="commerce-icon commerce-topbar-button__icon">
							<use href="${themeDisplay.getPathThemeImages()}/commerce-icons.svg#search" />
						</svg>
					</div>
					<@liferay_commerce_ui["search-bar"] id="search-bar" />

					<button class="commerce-topbar-button js-toggle-search">
						<svg class="commerce-icon commerce-topbar-button__icon">
							<use href="${themeDisplay.getPathThemeImages()}/commerce-icons.svg#close" />
						</svg>
					</button>
				</div>
			</div>

			<div class="speedwell-search__results">
				<@liferay_commerce_ui["search-results"] />
			</div>
		</div>

		<div class="speedwell-account">
			<button class="commerce-topbar-button js-toggle-account">
				<svg class="commerce-icon">
					<use href="${themeDisplay.getPathThemeImages()}/commerce-icons.svg#icon-account" />
				</svg>
			</button>

			<div class="speedwell-account__dropdown">
				<div class="speedwell-account__content">
					<#include "${full_templates_path}/user_nav.ftl" />
				</div>
			</div>
		</div>

		<div class="speedwell-topbar__cart-wrapper speedwell-cart">
			<@liferay_commerce_ui["mini-cart"] />
		</div>
	</div>
</div>
