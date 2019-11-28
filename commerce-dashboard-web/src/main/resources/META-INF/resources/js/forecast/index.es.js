import React from 'react';
import ReactDOM from 'react-dom';
import ForecastChart from './ForecastChart.es';

export default function (componentId, id, props) {
	let instance = null;
	ReactDOM.render(
		<ForecastChart
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
