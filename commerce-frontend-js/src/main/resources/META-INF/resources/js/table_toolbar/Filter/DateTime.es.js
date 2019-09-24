import React, {useState} from 'react';
import ClayDatePicker from '@clayui/date-picker';
import ClayButton from '@clayui/button';

import getAppContext, {ContextProps} from '../Context.es';
import {DateTimeFilterProps} from './definitions';
import {prettifyDateTimeValue} from '../utils/index.es';

// interface IProps extends DateTimeFilterProps {
//     panelType?: 'add' | 'edit'
// }

const getDateTimeObj = value => {
	const date = value instanceof Date ? value : new Date(value);

	return {
		year: date.getFullYear(),
		month: date.getMonth(),
		day: date.getDate(),
		hours: date.getHours(),
		minutes: date.getMinutes(),
		seconds: date.getSeconds()
	};
};

const DateFilter = props => {
	const {actions} = getAppContext();

	const [value, setValue] = useState(props.value);
	const [formattedValue, setFormattedValue] = useState(
		prettifyDateTimeValue(props.value)
	);

	function updateDateTime(selectedDateTime) {
		const newValue = getDateTimeObj(selectedDateTime);

		setValue(newValue);
		setFormattedValue(prettifyDateTimeValue(newValue));
	}

	return (
		<>
			<ClayDatePicker
				onValueChange={updateDateTime}
				value={formattedValue}
				time
			/>
			<div className="mt-2">
				<ClayButton
					className="btn-sm"
					onClick={() => actions.updateFilterValue(props.slug, value)}
					// disabled={prettifyDateTimeValue(value) === prettifyDateTimeValue(props.value)}
				>
					{props.panelType === 'edit' ? 'Edit filter' : 'Add filter'}
				</ClayButton>
			</div>
		</>
	);
};

export default DateFilter;
