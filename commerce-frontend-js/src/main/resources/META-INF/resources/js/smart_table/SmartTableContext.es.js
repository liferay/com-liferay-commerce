import React from 'react';

const SmartTableContext = React.createContext({
    formRef: null,
    loadData: () => {},
    sidePanelId: null
});

export default SmartTableContext