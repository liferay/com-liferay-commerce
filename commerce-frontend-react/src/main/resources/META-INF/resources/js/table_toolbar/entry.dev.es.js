import React from 'react';
import ReactDOM from 'react-dom';
import TableToolbar from './TableToolbar.tsx';

const props = {
	spritemap: '/icons.svg'
}

ReactDOM.render(
	<TableToolbar {...props} />,
	window.document.getElementById('table-toolbar')
);