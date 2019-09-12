!!Speedwell && (Speedwell.features.mobile = (function(w) {
    'use strict';

    let FILTERS_BUTTON,
        FILTERS_HEADER,
        ADD_TO_CART_INLINE;

    const IS_OPEN_CLASS = 'is-open',
        IS_FIXED_CLASS = 'is-fixed';

    function setupFiltersHeader() {
        FILTERS_HEADER.querySelector('.title').innerText =
            Liferay.Language.get('filters');
    }

    function listenToFiltersButton() {
        const filtersAreClosed = !FILTERS_BUTTON.classList
            .contains(IS_OPEN_CLASS);

        FILTERS_BUTTON
            .classList.toggle(IS_OPEN_CLASS, filtersAreClosed);

        FILTERS_HEADER.querySelector('.close-button')
            [filtersAreClosed ? 'addEventListener' : 'removeEventListener'](
                'click',
                listenToFiltersButton
        );
    }

    function handleInlineAddToCartButtonPosition() {
        const isAboveViewport = ADD_TO_CART_INLINE.getBoundingClientRect().top <= 0;

        ADD_TO_CART_INLINE
            .classList.toggle(IS_FIXED_CLASS, isAboveViewport);
    }

    function selectElements() {
        FILTERS_BUTTON = w.document.querySelector('.mobile-filters-button');
        FILTERS_HEADER = w.document.querySelector('.mobile-filters-header');
        ADD_TO_CART_INLINE = w.document.querySelector('.add-to-cart-button--inline .commerce-button');
    }

    return {
        initialize: function() {
            selectElements();

            if (!!FILTERS_BUTTON &&
                !!FILTERS_HEADER) {

                setupFiltersHeader();
                FILTERS_BUTTON
                    .addEventListener('click', listenToFiltersButton);
            }


            !!ADD_TO_CART_INLINE &&
                Speedwell.features.scroll.registerCallback(handleInlineAddToCartButtonPosition);

            console.log('%c[%cINIT%c] %cMobile', 'color: white;', 'color: red', 'color: white', 'color: cyan');
        },

        getFiltersButton() {
            return FILTERS_BUTTON;
        }
    }
})(window));
