import React from 'react';
import ReactDOM from 'react-dom';
import OverviewChart from './OverviewChart.es';

export default function (componentId, id, props) {
	let instance = null;
	ReactDOM.render(
		<OverviewChart
			ref={component => {
				instance = component;
			}}
			{...props}
		/>,
		window.document.getElementById(id)
	);
	if (window.Liferay) {
		window.Liferay.component(componentId, instance);
	}
	return instance;
}
