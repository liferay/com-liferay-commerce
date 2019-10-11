export const prettifyCheckboxValue = (value, items) => {
	const prettifiedValue = value
		? value
				.map(v => {
					return items.reduce(
						(found, item) =>
							found || (item.value === v ? item.label : null),
						null
					);
				})
				.join(', ')
		: '';

	return prettifiedValue;
};

export const prettifySelectValue = (value, items) => {
	const prettifiedValue = value
		? items.reduce(
				(found, item) =>
					found || (item.value === value ? item.label : null),
				null
		  )
		: '';

	return prettifiedValue;
};

export const prettifyDateValue = value => {
	if (!value) {
		return '';
	}

	const date =
		value instanceof Date
			? value
			: new Date(value.year, value.month, value.day);

	return date.toLocaleDateString();
};

export const prettifyDateTimeValue = value => {
	if (!value) {
		return '';
	}

	const date =
		value instanceof Date
			? value
			: new Date(
					value.year,
					value.month,
					value.day,
					value.hours,
					value.minutes,
					value.seconds
			  );

	return date.toLocaleDateString() + ' ' + date.toLocaleTimeString();
};

export const prettifyFilterValue = props => {
	switch (props.type) {
		case 'checkbox':
			return prettifyCheckboxValue(props.value, props.items);
		case 'radio':
		case 'select':
			return prettifySelectValue(props.value, props.items);
		case 'date':
			return prettifyDateValue(props.value);
		case 'date-time':
			return prettifyDateTimeValue(props.value);
		// case 'date-range':
		// case 'date-time-range':
		//     return prettifySelectValue(props);

		default:
			return props.value;
	}
};
