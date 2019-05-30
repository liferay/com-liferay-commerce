import React from 'react';
import Icon from './utilities/Icon';

function MenuElement(props) {
    return (
        <a 
            className={`nav-link dynamic-menu__nav-icon${props.active ? ' nav-link dynamic-menu__nav-icon--active' : ''}`}
            title={props.pageName} 
            href="#1"
            onClick={props.select}
        >
            <Icon 
                spritemap={props.spritemap}
                symbol={props.icon}
            />
        </a>
    )
}

function Menu(props) {
    return (
        <nav className="dynamic-menu__tiles nav nav-stacked">
            {
                props.elements.map(
                    (el, i) => {
                        return (
                            <MenuElement 
                                select={() => props.selectElementIndex(i)}
                                active={props.selectedElementIndex === i}
                                key={i}
                                spritemap={props.spritemap} 
                                {...el} 
                            />
                        )
                    }
                )
            }
        </nav>
    )
}

export default Menu;