import {ClayCheckbox} from '@clayui/form';
import React from 'react';

function Checkbox(props) {
	return (
		<ClayCheckbox
			onChange={() => props.onSelect(props.value, !props.checked)}
			{...props}
			value={props.value || ''}
		/>
	);
}

export default Checkbox;
