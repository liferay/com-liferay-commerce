AUI.add(
	'liferay-commerce-frontend-management-bar-state',
	function(A) {
		A.Do.before(
			function (state) {
				if (state.owner === 'liferay.component') {
					return new A.Do.Halt(null);    
				}
			},
			Liferay.ManagementBar,
			'testRestoreTask'
		);
	},
	'',
	{
		requires: [
			'liferay-management-bar'
		]
	}
);
