AUI().ready(
	function() {
		console.log('%c[ AUI ready ]', 'background-color: #000; color: #00FFFF');

		!!Speedwell && !!Speedwell.features &&
			Speedwell.features.init.initializeFeatures();
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
