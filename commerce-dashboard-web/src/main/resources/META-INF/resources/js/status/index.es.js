import React from 'react';
import ReactDOM from 'react-dom';
import StatusChart from './StatusChart.es';

export default function (componentId, id, props) {
	let instance = null;
	ReactDOM.render(
		<StatusChart
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
