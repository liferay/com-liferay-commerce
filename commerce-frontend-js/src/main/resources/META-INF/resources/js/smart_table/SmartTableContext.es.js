import React from 'react';

const SmartTableContext = React.createContext({
    formRef: null,
    loadData: () => {}
});

export default SmartTableContext