AUI().ready(
	function() {
		console.log('%c[ AUI ready ]', 'background-color: #000; color: #00FFFF');

		if (!!Speedwell && !!Speedwell.features) {
			Speedwell.features.init.initializeFeatures();

			Speedwell.features.sliders = [];
			Speedwell.features.sliderCallbacks = [];

			if ('sliderCallbacks' in Speedwell.features &&
				Speedwell.features.sliderCallbacks.length) {

				Speedwell.features.sliderCallbacks.forEach(function(cb) {
					const componentReady = Liferay.component('SpeedwellSlider');

					if (componentReady) {
						Speedwell.features
							.sliders.push(cb(componentReady));
					}
				});
			}
		}
	}
);

Liferay.Portlet.ready(
	function(portletId, node) {}
);

Liferay.on(
	'allPortletsReady',

	function() {
		console.log('%c[ All portlets ready ]', 'background-color: #000; color: #FFFF00');
	}
);
