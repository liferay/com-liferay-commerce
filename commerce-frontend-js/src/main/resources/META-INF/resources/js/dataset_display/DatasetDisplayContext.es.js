import React from 'react';

const DataSetDisplayContext = React.createContext({
	formRef: null,
	loadData: () => {},
	sidePanelId: null
});

export default DataSetDisplayContext;
