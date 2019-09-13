!!Speedwell && (Speedwell.features.categoryMenu = (function(w) {
    'use strict';

    const MAIN_LINK_SELECTOR = '.main-link',
        CATEGORY_NAV_SELECTOR = '.speedwell-category-nav',
        IS_OPEN = 'is-open';

    let linkElements,
        categoryNavigationElement;

    const CONTAINER = Speedwell.getContainer();

    function showCategoryNavigationMenu(e) {
        const isCatalogLink = e.currentTarget.href.indexOf('/catalog') > -1;

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
        if (!Speedwell.isMobile()) {
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

            console.log('%c[%cINIT%c] %cTopbar Category Nav', 'color: white;', 'color: red', 'color: white', 'color: cyan');
        },

        getElement() {
            return categoryNavigationElement;
        }
    }
})(window));
