import React from 'react';

const DatasetDisplayContext = React.createContext({
	formRef: null,
	loadData: () => {},
	sidePanelId: null,
	sorting: [],
	updateSorting: () => {}
});

export default DatasetDisplayContext;
