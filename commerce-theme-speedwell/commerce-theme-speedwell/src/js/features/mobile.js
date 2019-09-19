!!Speedwell && (Speedwell.features.mobile = (function(w) {
    'use strict';

    let filtersButton,
        filtersHeader,
        addToCartInline,
        addToCartInlineDefaultPosition;

    const IS_OPEN_CLASS = 'is-open',
        IS_FIXED_CLASS = 'is-fixed';

    function setupFiltersHeader() {
        filtersHeader.querySelector('.title').innerText =
            Liferay.Language.get('filters');
    }

    function listenToFiltersButton() {
        const filtersAreClosed = !filtersButton.classList
            .contains(IS_OPEN_CLASS);

        filtersButton
            .classList.toggle(IS_OPEN_CLASS, filtersAreClosed);

        filtersHeader.querySelector('.close-button')
            [filtersAreClosed ? 'addEventListener' : 'removeEventListener'](
                'click',
                listenToFiltersButton
        );
    }

    function isFixed(element) {
        return element.classList.contains(IS_FIXED_CLASS);
    }

    function restoreAddToCartButton() {
        const isBelowViewport = w.scrollY < addToCartInlineDefaultPosition;

        if (isBelowViewport && isFixed(addToCartInline)) {
            addToCartInline.classList.remove(IS_FIXED_CLASS);
            w.removeEventListener('scroll', restoreAddToCartButton);
            w.addEventListener('scroll', fixAddToCartButton);
        }
    }

    function fixAddToCartButton() {
        const isAboveViewport = addToCartInline.getBoundingClientRect().top <= 0;

        if (isAboveViewport && !isFixed(addToCartInline)) {
            addToCartInline.classList.add(IS_FIXED_CLASS);
            w.removeEventListener('scroll', fixAddToCartButton);
            w.addEventListener('scroll', restoreAddToCartButton);
        }
    }

    function selectElements() {
        filtersButton = w.document.querySelector('.mobile-filters-button');
        filtersHeader = w.document.querySelector('.mobile-filters-header');
        addToCartInline = w.document.querySelector('.add-to-cart-button--inline .commerce-button');
    }

    return {
        initialize: function() {
            selectElements();

            if (!!filtersButton &&
                !!filtersHeader) {

                setupFiltersHeader();
                filtersButton
                    .addEventListener('click', listenToFiltersButton);
            }

            !!addToCartInline &&
                (addToCartInlineDefaultPosition = addToCartInline.getBoundingClientRect().top) &&
                w.addEventListener('scroll', fixAddToCartButton);
        },

        getFiltersButton() {
            return filtersButton;
        }
    }
})(window));
