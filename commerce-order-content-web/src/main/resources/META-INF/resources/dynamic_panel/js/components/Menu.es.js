import React from 'react';
import Icon from './utilities/Icon.es';

export function MenuElement(props) {

    function select(e) {
        e.preventDefault()
        props.select()
    }

    return (
        <a 
            className={`nav-link dynamic-panel__nav-icon${props.active ? ' nav-link dynamic-panel__nav-icon--active' : ''}`}
            title={props.pageName} 
            href={props.url}
            onClick={(e) => select(e)}
        >
            <Icon 
                spritemap={props.spritemap}
                symbol={props.icon}
            />
        </a>
    )
}

function Menu(props) {
    return props.elements 
        ? (
            <nav className="dynamic-panel__tiles nav nav-stacked">
                {
                    props.elements.map(
                        (el, i) => (
                            <MenuElement 
                                select={() => props.selectBySlug(el.slug)}
                                active={props.active === el.slug}
                                key={i}
                                spritemap={el.spritemap || props.spritemap} 
                                {...el} 
                            />
                        )
                    )
                }
            </nav>
        )
        : null
}

export default Menu;