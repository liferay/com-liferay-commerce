import ReactDOM from 'react-dom';
import AssignTo from './AssignTo.es';
import React from 'react';

export default function(componentId, id, props) {
	const portletFrame = window.document.getElementById(id);
	let instance = null;
	ReactDOM.render(
		<AssignTo
			ref={component => {
				instance = component;
			}}
			{...props}
		/>,
		portletFrame
	);
	if (window.Liferay && window.Liferay.component) {
		window.Liferay.component(componentId, instance);
	}
	return instance;
}