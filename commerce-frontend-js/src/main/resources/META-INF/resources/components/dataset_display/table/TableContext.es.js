import React from 'react';

const TableContext = React.createContext({
	setSidePanelProps: () => {},
	sidePanelProps: {}
});

export default TableContext;
