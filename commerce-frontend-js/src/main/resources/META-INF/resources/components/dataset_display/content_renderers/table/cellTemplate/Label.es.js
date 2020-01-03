import ClayLabel from '@clayui/label';
import PropTypes from 'prop-types';
import React from 'react';

function Label(props) {
	return (
		<ClayLabel displayType={props.value.style || 'info'}>
			{typeof props.value === 'string' ? props.value : props.value.label}
		</ClayLabel>
	);
}

Label.propTypes = {
	value: PropTypes.oneOfType([
		PropTypes.shape({
			label: PropTypes.string,
			style: PropTypes.oneOf([
				'success',
				'info',
				'secondary',
				'warning',
				'danger'
			])
		}),
		PropTypes.string
	])
}

export default Label;
