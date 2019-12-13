import ReactDOM from 'react-dom';
import React from 'react';

export function debounce(func, wait, immediate) {
	let timeout;

	return () => {
		const context = this;
		const args = arguments;
		function later() {
			timeout = null;
			if (!immediate) func.apply(context, args);
		}
		const callNow = immediate && !timeout;

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
	if(!window.AUI) {
		return;
	}
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

export function showErrorNotification(e) {
	console.error(e)
	showNotification(
		Liferay.Language.get('unexpected-error'),
		'danger'
	)
}

if (!window.Liferay) {
	window.Liferay = {
		Language: {
			get: v => v
		},
		detach: (name, fn) => {
			window.removeEventListener(name, fn);
		},
		fire: (name, payload) => {
			var e = document.createEvent( 'CustomEvent' );
			e.initCustomEvent(name);
			if(payload) {
				Object.keys(payload).forEach(key => { e[key] = payload[key] })
			}
			window.dispatchEvent(e);
		},
		on: (name, fn) => {
			window.addEventListener(name, fn);
		}
	};
}

export function launcher(Component, componentId, rootId, props) {
	const portletFrame = window.document.getElementById(rootId);

	if (!portletFrame) {
		throw new Error(`Component container not found: "${rootId}"`);
	}

	let componentInstance = null;

	ReactDOM.render(
		Component.prototype.render ? (
			<Component
				ref={e => {
					componentInstance = e;
				}}
				{...props}
			/>
		) : (
			<Component {...props} />
		),
		portletFrame
	);

	function destroyComponent() {
		try {
			ReactDOM.unmountComponentAtNode(portletFrame);
		} catch (e) {
			console.error(e);
		}

		Liferay.destroyComponent(componentId);
		Liferay.detach('beforeNavigate', destroyComponent);
	}

	if (Liferay && Liferay.component && Liferay.on && Liferay.detach) {
		Liferay.component(componentId, componentInstance);
		Liferay.on('beforeNavigate', destroyComponent);
	} else {
		// eslint-disable-next-line no-console
		console.info('Liferay env not found');
	}

	return componentInstance;
}

export default {
	debounce,
	launcher,
	showNotification
};
