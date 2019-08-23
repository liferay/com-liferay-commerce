<div class="speedwell-category-nav hidden-sm">
    <div class="row">
        <div class="speedwell-category-nav__side col-lg-3"
            style="background-image: url('${themeDisplay.getPathThemeImages()}/category_nav.jpeg');">
            <h2><@liferay.language key="car-parts" /></h2>
            <p>Some description for this category</p>
        </div>

        <div class="speedwell-category-nav__content col-lg-9">

            <@commerce_category_navigation_menu

                default_preferences=freeMarkerPortletPreferences
                    .getPreferences("portletSetupPortletDecoratorId", "barebone")
            />

            <div class="show-more">
                <p>
                    <a href=""><@liferay.language key="show-more" /></a>
                </p>
            </div>
        </div>
    </div>
</div>
