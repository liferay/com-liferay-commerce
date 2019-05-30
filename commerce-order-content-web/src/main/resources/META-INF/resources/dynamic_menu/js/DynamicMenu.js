
import React, {
  useState,
} from 'react';

import Icon from './components/utilities/Icon';
import Menu from './components/Menu';
import Content from './components/Content';

function DynamicMenu(props) {
  const [ current, setCurrent ] = useState(null);

  return (
    <div className={`dynamic-menu${current !== null ? ' open' : ''}`}>
      <Menu 
        spritemap={props.spritemap}
        elements={props.elements} 
        selectElementIndex={setCurrent}
        selectedElementIndex={current}
      />
      <Content 
        url={current !== null && props.elements[current].url}
        close={() => setCurrent(null)}
        closeIcon={<Icon spritemap={props.spritemap} symbol="close" />}
      />
    </div>
  )
}

export default DynamicMenu;
