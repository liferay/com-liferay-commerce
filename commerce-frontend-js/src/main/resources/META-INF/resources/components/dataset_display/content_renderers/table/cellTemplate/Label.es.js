import ClayLabel from '@clayui/label';
import PropTypes from 'prop-types';
import React from 'react';

function Label(props) {
	return (
		<ClayLabel displayType={props.value.displayStyle || 'info'}>
			{typeof props.value === 'string' ? props.value : props.value.label}
		</ClayLabel>
	);
}

Label.propTypes = {
	value: PropTypes.oneOfType([
		PropTypes.shape({
			displayStyle: PropTypes.oneOf([
				'success',
				'info',
				'secondary',
				'warning',
				'danger'
			]),
			label: PropTypes.string,
		}),
		PropTypes.string
	])
}

export default Label;
