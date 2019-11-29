var Speedwell = Speedwell || { features: {} };

Speedwell.features.init = (function(w) {
    'use strict';

    return {
        initializeFeatures: function() {
            setTimeout(function() {
                Object.keys(Speedwell.features).forEach(function (feature) {
                    !!Speedwell.features[feature].initialize &&
                        Speedwell.features[feature].initialize();

                    console.log(
                        '%c⊏ %cspeedwell%c ' +
                        feature.charAt(0).toUpperCase() + feature.slice(1) +
                        ' initialized',
                        'color: #30CFA1; font-weight: bolder',
                        'color: #DDD; font-weight: bolder; font-variant: small-caps; font-family: Helvetica; font-size: 1.3em;',
                        ''
                    );
                });
            }, 350);
        }
    }
})(window);
