<div class="speedwell-category-nav hidden-sm">
    <div class="container">
        <div class="row">
            <div class="speedwell-category-nav__content col-lg-12">

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
</div>
