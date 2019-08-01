import ReactDOM from 'react-dom';
import React from 'react';
import AssignTo from './AssignTo.es';

const props = {
	test: 'test'
}

ReactDOM.render(
	<AssignTo {...props} />,
	window.document.getElementById('assign-to')
);