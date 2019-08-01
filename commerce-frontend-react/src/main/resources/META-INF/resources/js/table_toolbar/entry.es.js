import ReactDOM from 'react-dom';
import TableToolbar from './TableToolbar.tsx';

export default function(componentId, id, props) {
	const portletFrame = window.document.getElementById(id);
	let instance = null;
	ReactDOM.render(
		<TableToolbar
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