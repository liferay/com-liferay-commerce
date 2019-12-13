import {ClayCheckbox} from '@clayui/form';
import React from 'react';

function Checkbox(props) {
	return (
		<ClayCheckbox
			{...props}
			onChange={() => props.onSelect(props.value, !props.checked)}
		/>
	);
}

export default Checkbox;
