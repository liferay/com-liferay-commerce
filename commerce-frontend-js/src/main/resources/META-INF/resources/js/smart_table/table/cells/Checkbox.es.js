import {ClayCheckbox} from '@clayui/form';
import React from 'react'

function Checkbox(props) {
    return (
        <ClayCheckbox
            checked={props.checked}
            indeterminate={props.indeterminate}
            onChange={() => props.onSelect(!props.checked, props.value)}
            value={props.value}
        />
    )
}

export default Checkbox;