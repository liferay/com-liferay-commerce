var Speedwell = Speedwell || { features: {} };

Speedwell.features.scroll = (function(w) {
    'use strict';

    const SCROLL_EVENT = 'scroll',
        callbackQueueOnScroll = {};

    function sign(x) {
        return ((x > 0) - (x < 0)) || + x
    }

    let scrollThreshold = 100,
        lastKnownScrollPosition = 0,
        lastKnownScrollOffset = 0,
        ticking = false,
        myMap = new Map();

    myMap.set(-1, 'up');
    myMap.set(1, 'down');

    function handleOnScroll() {
        const offset = w.scrollY - lastKnownScrollPosition;

        lastKnownScrollPosition = w.scrollY;
        lastKnownScrollOffset =
            sign(offset) === sign(lastKnownScrollOffset)
                ? lastKnownScrollOffset + offset
                : offset;

        if (!ticking) {
            w.requestAnimationFrame(function () {
                ticking = false;
            });

            ticking = true;
        }

        Object.keys(callbackQueueOnScroll).forEach(function(callbackName) {
            callbackQueueOnScroll[callbackName](scrollThreshold);
        });
    }

    return {
        initialize: function() {
            w.addEventListener(SCROLL_EVENT, handleOnScroll, false);
        },

        registerCallback: function(callback) {
            callbackQueueOnScroll[callback.name] = callback;
        },

        unregisterCallback: function(callback) {
            delete callbackQueueOnScroll[callback.name];
        }
    }
})(window);
