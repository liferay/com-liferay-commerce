import {ClayCheckbox} from '@clayui/form';
import React from 'react';

function Checkbox(props) {
	return (
		<ClayCheckbox
			{...props}
			onChange={() => props.onSelect(!props.checked, props.value)}
		/>
	);
}

export default Checkbox;
