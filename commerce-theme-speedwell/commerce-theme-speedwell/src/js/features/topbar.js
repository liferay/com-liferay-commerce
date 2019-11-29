var Speedwell = Speedwell || { features: {} };

Speedwell.features.topbar = (function(w) {
    'use strict';

    const TOPBAR_CLASS = 'speedwell-topbar',
        TRANSLUCENT_CLASS = TOPBAR_CLASS + '--translucent',
        TOGGLE_PREFIX = '.js-toggle-',
        SPEEDWELL_PREFIX = '.speedwell-',
        IS_OPEN = 'is-open',
        IS_BEHIND = 'is-behind';

    const TOGGLES = {
        MAIN_MENU: { name: 'main-menu' },
        ACCOUNT: { name: 'account' },
        SEARCH: { name: 'search' }
    };

    const CONTAINER = Speedwell.features.context.getContainer();

    let TOPBAR,
        translucencyIsEnabled = false;

    function hideFiltersButtonOnMenuOpen() {
        const catalogFiltersButton = Speedwell.features.mobile.getFiltersButton();

        !!catalogFiltersButton &&
            catalogFiltersButton.classList
                .toggle(IS_BEHIND, !isOpen(catalogFiltersButton));
    }

    function attachListener(currentToggle) {
        const toggleWrapper = TOGGLES[currentToggle].wrapper;

        TOGGLES[currentToggle].buttons.forEach(function(button) {
            button.addEventListener('click', function(e) {
                const categoryNav = Speedwell.features.categoryMenu.getElement();

                button.focus();
                toggleWrapper.classList.toggle(IS_OPEN, !isOpen(toggleWrapper));
                categoryNav.classList.remove(IS_OPEN);

                if (Speedwell.features.context.isMobile()) {
                    hideFiltersButtonOnMenuOpen();
                }
            });
        })
    }

    function enableToggles() {
        Object.keys(TOGGLES).forEach(attachListener)

    }

    function wipeToggles() {
        Object.keys(TOGGLES).forEach(function(currentToggle) {
            delete TOGGLES[currentToggle].buttons;
            delete TOGGLES[currentToggle].wrapper;
        });
    }

    function prepareToggles() {
        wipeToggles();

        Object.keys(TOGGLES).forEach(function(toggle) {
            TOGGLES[toggle].buttons = Array.from(
                TOPBAR.querySelectorAll(TOGGLE_PREFIX + TOGGLES[toggle].name)
            );

            TOGGLES[toggle].wrapper =
                TOPBAR.querySelector(SPEEDWELL_PREFIX + TOGGLES[toggle].name);
        });
    }

    function isOpen(toggleElement) {
        return toggleElement.classList.contains(IS_OPEN);
    }

    function toggleTranslucencyOnScroll(scrollThreshold) {
        const isBeyond = w.scrollY <= scrollThreshold;

        translucencyIsEnabled && TOPBAR.classList.toggle(TRANSLUCENT_CLASS, isBeyond);
    }

    function isTranslucent() {
        translucencyIsEnabled = TOPBAR.classList.contains(TRANSLUCENT_CLASS)
    }

    function selectElements() {
        TOPBAR = CONTAINER.querySelector('.' + TOPBAR_CLASS);
    }

    return {
        initialize: function() {
            selectElements();
            prepareToggles();
            enableToggles();
            isTranslucent();

            translucencyIsEnabled &&
                Speedwell.features.scroll.registerCallback(toggleTranslucencyOnScroll);
        },

        getToggleElements: function() {
            return TOGGLES;
        },

        isOpen: isOpen
    }
})(window);
