<#assign
	copyright = getterUtil.getString(themeDisplay.getThemeSetting("copyright"))
	show_top_menu = getterUtil.getBoolean(themeDisplay.getThemeSetting("show-top-menu"))
	userManagementUrl = commerceThemeMiniumHttpHelper.getAccountManagementPortletURL(request)
	wishlistUrl = commerceWishListHttpHelper.getCommerceWishListPortletURL(request)
	wish_lists_text = commerceThemeMiniumHttpHelper.getMyListsLabel(locale)
	my_profile_text = languageUtil.get(locale, "my-profile")
	notifications_text = languageUtil.get(locale, "notifications")
	notification_url = commerceThemeMiniumHttpHelper.getNotificationsURL(request)
	notification_count = commerceThemeMiniumHttpHelper.getNotificationsCount(themeDisplay)
	wide_layout = getterUtil.getBoolean(themeDisplay.getThemeSetting("wide-layout"))
	back_url = paramUtil.getString(request, "p_r_p_backURL")
	speedwell_content_css_class = "speedwell-content"
	translucent_topbar = getterUtil.getBoolean(themeDisplay.getThemeSetting("translucent-topbar"))
	speedwell_topbar_css_class = "speedwell-topbar"
/>

<#if wide_layout>
	<#assign
	speedwell_content_css_class = "speedwell-content speedwell-content--wide"
	/>
</#if>

<#if translucent_topbar>
	<#assign
	speedwell_topbar_css_class = "speedwell-topbar speedwell-topbar--translucent"
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
