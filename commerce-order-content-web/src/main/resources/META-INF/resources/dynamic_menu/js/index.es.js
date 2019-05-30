import React from 'react';
import ReactDOM from 'react-dom';
import DynamicMenu from './DynamicMenu';

const props = {
    elements : [
        {
          icon: 'list-view',
          url: 'http://localhost:4000/api/text/list',
          pageName: 'Comments'
        },
        {
          icon: 'print',
          url: 'http://localhost:4000/api/text/print',
          pageName: 'Edit'
        },
        {
          icon: 'search',
          url: 'http://localhost:4000/api/text/search',
          pageName: 'Changelog'
        }
    ],
    spritemap : 'commerce-icons.svg'
}

ReactDOM.render(
    <DynamicMenu {...props} />,
    document.getElementById('root')
);