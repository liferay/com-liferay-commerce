import React from 'react';

const DatasetDisplayContext = React.createContext({
	formRef: null,
	loadData: () => {},
	sidePanelId: null
});

export default DatasetDisplayContext;
