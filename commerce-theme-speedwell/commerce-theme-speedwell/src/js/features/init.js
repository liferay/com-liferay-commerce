!!Speedwell && _.extend(Speedwell, (function(w) {
    'use strict';

    return {
        initializeFeatures: function() {
            setTimeout(function() {
                Object.keys(Speedwell.features).forEach(function (feature) {
                    Speedwell.features[feature].initialize();

                    console.log(
                        '%c⊏ %cSpeedwell%c ' +
                        feature.charAt(0).toUpperCase() + feature.slice(1) +
                        ' initialized',
                        'color: #30CFA1; font-weight: bolder',
                        'color: #DDD; font-weight: bolder',
                        'color: default;'
                    );
                });
            }, 350);
        }
    }
})(window));
