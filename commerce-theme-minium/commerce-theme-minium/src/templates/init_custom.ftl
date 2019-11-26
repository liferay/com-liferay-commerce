<#assign
	redirect_to_private_layouts = getterUtil.getBoolean(themeDisplay.getThemeSetting("redirect-to-private-layouts"))
	show_top_menu = getterUtil.getBoolean(themeDisplay.getThemeSetting("show-top-menu"))
	wide_layout = getterUtil.getBoolean(themeDisplay.getThemeSetting("wide-layout"))
	userManagementUrl = commerceThemeMiniumHttpHelper.getAccountManagementPortletURL(request)
	wishlistUrl = commerceWishListHttpHelper.getCommerceWishListPortletURL(request)
	wish_lists_text = commerceThemeMiniumHttpHelper.getMyListsLabel(locale)
	my_profile_text = languageUtil.get(locale, "my-profile")
	notifications_text = languageUtil.get(locale, "notifications")
	notification_url = commerceThemeMiniumHttpHelper.getNotificationsURL(request)
	notification_count = commerceThemeMiniumHttpHelper.getNotificationsCount(themeDisplay)
	back_url = paramUtil.getString(request, "p_r_p_backURL")
	minium_content_css_class = "minium-content"
/>

<#if wide_layout>
	<#assign
	minium_content_css_class = "minium-content minium-content--wide"
	/>
</#if>

<#macro site_navigation_menu_main default_preferences = "">
	<@liferay_portlet["runtime"]
		defaultPreferences=default_preferences
		instanceId="siteNavigationMenuPortlet_main"
		portletName="com_liferay_site_navigation_menu_web_portlet_SiteNavigationMenuPortlet"
	/>
</#macro>

<#macro site_navigation_menu_sub_navigation default_preferences = "">
	<@liferay_portlet["runtime"]
		defaultPreferences=default_preferences
		instanceId="siteNavigationMenuPortlet_sub_navigation"
		portletName="com_liferay_site_navigation_menu_web_portlet_SiteNavigationMenuPortlet"
	/>
</#macro>