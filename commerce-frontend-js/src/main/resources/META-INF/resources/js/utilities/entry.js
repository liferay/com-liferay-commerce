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


export function launcher(Component, componentId, rootId, props, portletId) {
	const portletFrame = window.document.getElementById(rootId);

	if(!portletFrame) {
		throw new Error(`Component container not found: "${rootId}"`)		
	}

	const componentInstance = ReactDOM.render(
		<Component {...props} />,
		portletFrame
	);

	if (Liferay) {
		Liferay.component(componentId, componentInstance);
		Liferay.on('beforeNavigate', destroyComponent);

		function destroyComponent() {
			Liferay.destroyComponent(componentId);
			Liferay.detach('beforeNavigate', destroyComponent);
		}
	}

	return componentInstance;
}