import ReactDOM from 'react-dom';
import React from 'react';
import Modal from './Modal';

export default function(componentId, id, props) {
	const portletFrame = window.document.getElementById(id);
	let instance = null;
	ReactDOM.render(
		<Modal
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