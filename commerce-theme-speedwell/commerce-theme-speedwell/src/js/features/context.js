!!Speedwell && _.extend(Speedwell, (function(w) {
    'use strict';

    const SPEEDWELL = 'speedwell';

    const speedwellContainer = w.document.getElementById(SPEEDWELL);

    return {
        getContainer: function() {
            return speedwellContainer;
        },

        getThemeName: function() {
            return SPEEDWELL;
        },

        isMobile: w.Liferay.Browser.isMobile
    }
})(window));
