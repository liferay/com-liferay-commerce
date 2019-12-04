AUI().ready(
	function() {
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