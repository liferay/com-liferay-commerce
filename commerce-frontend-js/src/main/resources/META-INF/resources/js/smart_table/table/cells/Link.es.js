import ClayLink from '@clayui/link';
import React from 'react'

function Link(props) {
    return (
        <ClayLink 
            data-target={props.target}
            href={props.href || '#'}
        >
            {props.content}
        </ClayLink>
    )
}

export default Link;