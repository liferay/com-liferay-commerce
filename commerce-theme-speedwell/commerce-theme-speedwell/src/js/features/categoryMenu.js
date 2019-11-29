var Speedwell = Speedwell || { features: {} };

Speedwell.features.categoryMenu = (function(w) {
    'use strict';

    const MAIN_LINK_SELECTOR = '.main-link',
        CATEGORY_NAV_SELECTOR = '.speedwell-category-nav',
        IS_OPEN = 'is-open';

    let linkElements,
        categoryNavigationElement;

    const CONTAINER = Speedwell.features.context.getContainer();

    function showCategoryNavigationMenu(e) {
        const isCatalogLink = e.currentTarget.href.indexOf('/car-parts') > -1 ||
            e.currentTarget.href.indexOf('/catalog') > -1;

        if (isCatalogLink) {
            categoryNavigationElement.focus();
            categoryNavigationElement.classList.add(IS_OPEN);
        } else {
            categoryNavigationElement.classList.remove(IS_OPEN);
        }
    }

    function hideCategoryNavigationMenu() {
        categoryNavigationElement.classList.remove(IS_OPEN);
    }

    function attachListeners() {
        if (!Speedwell.features.context.isMobile()) {
            linkElements.forEach(function(link) {
                link.addEventListener('mouseover', showCategoryNavigationMenu);
            });

            categoryNavigationElement
                .addEventListener('focusout', hideCategoryNavigationMenu)
        }
    }

    function selectElements() {
        linkElements = Array.from(
            CONTAINER.querySelectorAll(MAIN_LINK_SELECTOR)
        );

        categoryNavigationElement =
            CONTAINER.querySelector(CATEGORY_NAV_SELECTOR);
    }

    return {
        initialize: function() {
            selectElements();
            attachListeners();
        },

        getElement() {
            return categoryNavigationElement;
        }
    }
})(window);
