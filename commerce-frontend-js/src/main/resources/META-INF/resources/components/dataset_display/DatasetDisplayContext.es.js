import React from 'react';

const DatasetDisplayContext = React.createContext({
	enableInlineEditing: () => {},
	formRef: null,
	inlineEditingDetails: null,
	loadData: () => {},
	sidePanelId: null,
	sorting: [],
	updateSorting: () => {},
});

export default DatasetDisplayContext;
