var Speedwell = Speedwell || { features: {} };


AUI().ready(
	function() {
		console.log('%c[AUI ready]', 'background-color: #000; color: #00FFFF');

		Speedwell.initializeFeatures();
	}
);

Liferay.Portlet.ready(
	function(portletId, node) {
		// console.log('%c[Portlet: ' + portletId + ' ready]', 'background-color: #000; color: #00FF00');
	}
);

Liferay.on(
	'allPortletsReady',

	function() {
		console.log('%c[All portlets ready]', 'background-color: #000; color: #FFFF00');



	}
);
