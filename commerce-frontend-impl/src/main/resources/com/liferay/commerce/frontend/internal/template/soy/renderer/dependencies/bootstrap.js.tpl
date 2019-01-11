var context = Object.assign(
	$CONTEXT
);

var componentConfig = {
	destroyOnNavigate: true,
	portletId: context.portletId
};

var componentArgs = [context];

if ($WRAPPER) {
	componentArgs.push('#$ID');
}

if (context.defaultEventHandler) {
	Liferay.componentReady(
		context.defaultEventHandler
	).then(
		function(defaultEventHandler) {
			context.defaultEventHandler = defaultEventHandler;

			Liferay.component(
				'$ID',
				new $MODULE.default(...componentArgs),
				componentConfig
			);
		}
	);
}
else {
	Liferay.component(
		'$ID',
		new $MODULE.default(...componentArgs),
		componentConfig
	);
}