import React from 'react';
import ReactDOM from 'react-dom';

import DynamicPanel from './DynamicPanel.es';

export default function(componentId, id, props) {
	const portletFrame = window.document.getElementById(id);
	let instance = null;
 	ReactDOM.render(
		<DynamicPanel 
			ref={(dynamicPanel) => {
				instance = dynamicPanel
			}} 
			{...props}
		/>,
		portletFrame
  );
  if(window.Liferay && !window.Liferay.component(componentId)) {
    window.Liferay.component(componentId, instance);
  }
	return instance
}