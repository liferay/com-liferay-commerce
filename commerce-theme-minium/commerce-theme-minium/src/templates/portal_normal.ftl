<!DOCTYPE html>
<#include init />
<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">
<head>
	<title>${the_title} - ${company_name}</title>

	<meta content="initial-scale=1.0, width=device-width" name="viewport" />

	<script type="text/javascript" src="${javascript_folder}/intersection-observer.js"></script>
	<@liferay_util["include"] page=top_head_include />
</head>

<#if redirect_to_private_layouts && themeDisplay.isSignedIn() && themeDisplay.getLayout().isPublicLayout()>
	<script>
		window.location.replace("${themeDisplay.getPathFriendlyURLPrivateGroup() + themeDisplay.getScopeGroup().getFriendlyURL()}");
	</script>
</#if>

<#assign isMiniumLogin = false />

<#if themeDisplay.isSignedIn() && themeDisplay.getLayoutSet().isPrivateLayout()>
	<#assign isMiniumLogin = true />
<#else>
	<#assign css_class = css_class + " minium-login" />
</#if>

<body class="${css_class}">
	<div class="liferay-top">
		<@liferay_ui["quick-access"] contentId="#main-content" />
		<@liferay_util["include"] page=body_top_include />
		<@liferay.control_menu />
	</div>

	<main class="minium minium-frame" id="minium">
		<#if isMiniumLogin>
			<div class="minium-frame__sidebar">
				<#include "${full_templates_path}/sidebar.ftl" />
			</div>

			<div class="minium-frame__topbar">
				<#include "${full_templates_path}/topbar.ftl" />
			</div>

			<div class="minium-frame__content js-scroll-area">
				<a name="minium-top"></a>
		</#if>

			<div class="${minium_content_css_class}">
				<#if selectable>
					<@liferay_util["include"] page=content_include />
				<#else>
					${portletDisplay.recycle()}
					${portletDisplay.setTitle(the_title)}

					<@liferay_theme["wrap-portlet"] page="portlet.ftl">
						<@liferay_util["include"] page=content_include />
					</@>
				</#if>
			</div>
		<#if isMiniumLogin>
			</div>

			<#--  The toolbar is needed to create the shadow when scrolling  -->

			<div class="minium-frame__toolbar"></div>

			<div class="minium-frame__overlay">
				<@liferay_commerce_ui["search-results"] />
			</div>
		</#if>
	</main>

	<div class="liferay-bottom">
		<@liferay_util["include"] page=body_bottom_include />
		<@liferay_util["include"] page=bottom_include />
	</div>
</body>
</html>