(function() {
	AUI().applyConfig({
		groups: {
			commercefrontend: {
				base: MODULE_PATH + '/js/',
				combine: Liferay.AUI.getCombine(),
				modules: {
					'liferay-commerce-frontend-management-bar-state': {
                        condition: {
                            trigger: 'liferay-management-bar'
                        },
						path: 'management_bar_state.js',
						requires: [
							'liferay-management-bar'
						]
					}
				},
				root: MODULE_PATH + '/js/'
			}
		}
	});
})();
