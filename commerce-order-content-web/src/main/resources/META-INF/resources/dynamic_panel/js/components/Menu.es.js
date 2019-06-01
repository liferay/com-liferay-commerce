import React from 'react';
import Icon from './utilities/Icon.es';

export function MenuItem(props) {

    if(
        !props.pageName || 
        !props.url ||
        !props.select || 
        !(
            (props.spritemap && props.icon) ||
            (props.label)
        )
    ) {
        return null
    }

    function select(e) {
        e.preventDefault()
        props.select()
    }

    return (
        <a 
            className={`nav-link dynamic-panel__nav-icon${props.active ? ' dynamic-panel__nav-icon--active' : ''}`}
            title={props.pageName} 
            href={props.url}
            onClick={(e) => select(e)}
        >
            {
                props.spritemap &&
                props.icon && (
                    <Icon 
                        spritemap={props.spritemap}
                        symbol={props.icon}
                    />
                )
            }
            {props.label && (
                <span className="dynamic-panel__label">
                    {props.label}
                </span>
            )}
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
                            <MenuItem 
                                pageName={el.pageName}
                                url={el.url}
                                select={() => props.selectBySlug(el.slug)}
                                spritemap={el.spritemap || props.spritemap} 
                                icon={el.icon}
                                active={props.active === el.slug}
                                key={i}
                                label={el.label}
                            />
                        )
                    )
                }
            </nav>
        )
        : null
}

export default Menu;