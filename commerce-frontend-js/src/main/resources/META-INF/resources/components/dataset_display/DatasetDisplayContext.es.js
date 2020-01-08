import React from 'react';

const DatasetDisplayContext = React.createContext({
	enableInlineEditing: () => {},
	formRef: null,
	inlineEditingDetails: null,
	loadData: () => {},
	modalId: null,
	sidePanelId: null,
	sorting: [],
	updateSorting: () => {},
});

export default DatasetDisplayContext;
