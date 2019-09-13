!!Speedwell && (Speedwell.features.mobile = (function(w) {
    'use strict';

    let FILTERS_BUTTON,
        FILTERS_HEADER,
        ADD_TO_CART_INLINE,
        ADD_TO_CART_INLINE_DEFAULT_POS;

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

    function isFixed(element) {
        return element.classList.contains(IS_FIXED_CLASS);
    }

    function restoreAddToCartButton() {
        const isBelowViewport = ADD_TO_CART_INLINE.getBoundingClientRect().top < ADD_TO_CART_INLINE_DEFAULT_POS;

        if (isBelowViewport && isFixed(ADD_TO_CART_INLINE)) {
            ADD_TO_CART_INLINE.classList.remove(IS_FIXED_CLASS);
            w.removeEventListener('scroll', restoreAddToCartButton);
            w.addEventListener('scroll', fixAddToCartButton);
        }
    }

    function fixAddToCartButton() {
        const isAboveViewport = ADD_TO_CART_INLINE.getBoundingClientRect().top <= 0;

        if (isAboveViewport && !isFixed(ADD_TO_CART_INLINE)) {
            ADD_TO_CART_INLINE.classList.add(IS_FIXED_CLASS);
            w.removeEventListener('scroll', fixAddToCartButton);
            w.addEventListener('scroll', restoreAddToCartButton);
        }
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
                (ADD_TO_CART_INLINE_DEFAULT_POS = ADD_TO_CART_INLINE.getBoundingClientRect().top) &&
                window.addEventListener('scroll', fixAddToCartButton);

            console.log('%c[%cINIT%c] %cMobile', 'color: white;', 'color: red', 'color: white', 'color: cyan');
        },

        getFiltersButton() {
            return FILTERS_BUTTON;
        }
    }
})(window));
