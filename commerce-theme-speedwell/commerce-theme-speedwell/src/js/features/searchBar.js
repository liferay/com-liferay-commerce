var Speedwell = Speedwell || { features: {} };

Speedwell.features.searchBar = (function(w) {

    const searchToggles = w.document.querySelectorAll('.js-toggle-search'),
        HAS_SEARCH_CLASS = 'has-search',
        IS_OPEN_CLASS = 'is-open',
        IS_ACTIVE_CLASS = 'is-active',
        SEARCHBAR_SELECTOR = '.speedwell-search';

    let searchBarElement;

    return {
        initialize: function() {
            const searchBar = w.Liferay.component('search-bar');

            if (searchBar) {
                searchBarElement = w.document.querySelector(SEARCHBAR_SELECTOR);

                searchBar.on('toggled', function(status) {
                    searchToggles.forEach(function (el) {
                        el.classList.toggle(IS_ACTIVE_CLASS, status);
                    });

                    Speedwell.features.context.getContainer()
                        .classList.toggle(HAS_SEARCH_CLASS, status);

                    !!searchBarElement && searchBarElement
                        .classList.toggle(IS_OPEN_CLASS, status);
                });
            }
        }
    }
})(window);
