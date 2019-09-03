import ReactDOM from 'react-dom';
import React from 'react';

// declare global {
//     interface Window { 
// 		Liferay: any; 
// 	}
// }

if(!window.Liferay) {
	window.Liferay = {
		Language : {
			get: v => v
		}
	}
}

export function launcher(Component, componentId, id, props) {
	const portletFrame = window.document.getElementById(id);

	if(!portletFrame) {
		return null
	}
	
	const componentInstance = ReactDOM.render(
		<Component {...props} />,
		portletFrame
	);

	if (window.Liferay && window.Liferay.component) {
		window.Liferay.component(componentId, componentInstance);
	}

	return componentInstance;
}