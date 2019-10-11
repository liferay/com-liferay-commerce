import {ClayCheckbox} from '@clayui/form';
import React from 'react';

function Checkbox(props) {
	return (
		<ClayCheckbox
			onChange={() => props.onSelect(!props.checked, props.value)}
			{...props}
		/>
	);
}

export default Checkbox;
