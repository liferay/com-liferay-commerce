var Speedwell = Speedwell || { features: {} };

Speedwell.features.accessibility = (function(w) {
    'use strict';

    const KEYDOWN_EVENT = 'keydown',
        TAB_KEYCODE = 9,
        ACCESSIBILITY_CLASS = 'is-accessible',
        TIMEOUT = 5000;

    let isAccessible = false,
        removeAfter = setTimeout(function() {
            w.removeEventListener(KEYDOWN_EVENT, needsAccessibility);
            clearTimeout(removeAfter);
        }, TIMEOUT);

    function needsAccessibility(e) {
        const isTabbing = e.which === TAB_KEYCODE;

        if (isTabbing) {
            isAccessible = true;

            w.document.body.classList.add(ACCESSIBILITY_CLASS);
            w.removeEventListener(KEYDOWN_EVENT, needsAccessibility);
        }
    }


    return {
        initialize: function() {
            w.addEventListener(KEYDOWN_EVENT, needsAccessibility);
        },

        isAccessible: function() {
            return isAccessible;
        }
    }
})(window);
