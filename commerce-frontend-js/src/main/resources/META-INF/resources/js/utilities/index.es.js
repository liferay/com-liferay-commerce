import ReactDOM from 'react-dom';
import React from 'react';

export function debounce(func, wait, immediate) {
	let timeout;

	return () => {
		let context = this;
		let args = arguments;
		let later = function() {
			timeout = null;
			if (!immediate) func.apply(context, args);
		};
		let callNow = immediate && !timeout;

		clearTimeout(timeout);
		timeout = setTimeout(later, wait);
		if (callNow) func.apply(context, args);
	};
}

export function showNotification(
	message,
	type,
	closeable = true,
	duration = 500
) {
	AUI().use('liferay-notification', () => {
		new Liferay.Notification({
			closeable,
			delay: {
				hide: 5000,
				show: 0
			},
			duration,
			message,
			render: true,
			title: '',
			type
		});
	});
}

if (!window.Liferay) {
	window.Liferay = {
		Language: {
			get: v => v
		}
	};
}

export function launcher(Component, componentId, rootId, props) {
	const portletFrame = window.document.getElementById(rootId);

	if (!portletFrame) {
		throw new Error(`Component container not found: "${rootId}"`);
	}

	const componentInstance = ReactDOM.render(
		<Component {...props} />,
		portletFrame
	);

	if (Liferay && Liferay.component && Liferay.on && Liferay.detach) {
		Liferay.component(componentId, componentInstance);
		Liferay.on('beforeNavigate', destroyComponent);

		function destroyComponent() {
			try {
				ReactDOM.unmountComponentAtNode(portletFrame);
			} catch (e) {
				console.error(e);
			}

			Liferay.destroyComponent(componentId);
			Liferay.detach('beforeNavigate', destroyComponent);
		}
	} else {
		console.info('Liferay env not found');
	}

	return componentInstance;
}
