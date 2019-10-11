import React from 'react';

const TableContext = React.createContext({
	modalProps: {},
	setModalProps: () => {},
	setSidePanelProps: () => {},
	sidePanelProps: {}
});

export default TableContext;
