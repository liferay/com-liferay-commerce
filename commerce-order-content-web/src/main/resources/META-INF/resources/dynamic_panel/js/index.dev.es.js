import React from 'react';
import ReactDOM from 'react-dom';
import DynamicPanel from './DynamicPanel.es';

import '../../../../../../../../commerce-theme-minium/commerce-theme-minium/static-version/src/styles/minium.scss';

const props = {
    elements : [
        {
          icon: 'list-view',
          url: 'http://localhost:4000/api/text/list',
          pageName: 'Comments',
          slug: 'comments'
        },
        {
          icon: 'print',
          url: 'http://localhost:4000/api/text/print',
          pageName: 'Edit',
          slug: 'edit'
        },
        {
          icon: 'search',
          url: 'http://localhost:4000/api/text/search',
          pageName: 'Changelog',
          slug: 'changelog'
        }
    ],
    spritemap : '/test-icons.svg'
}

ReactDOM.render(
    <DynamicPanel {...props} />,
    document.getElementById('root')
);