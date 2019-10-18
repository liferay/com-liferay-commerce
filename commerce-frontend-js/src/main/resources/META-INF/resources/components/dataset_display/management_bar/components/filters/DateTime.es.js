import ClayButton from '@clayui/button';
import ClayDatePicker from '@clayui/date-picker';
import React, {useState} from 'react';

import {prettifyDateTimeValue} from '../../utils/dates.es';
import getAppContext from '../Context.es';

const getDateTimeObj = value => {
	const date = value instanceof Date ? value : new Date(value);

	return {
		day: date.getDate(),
		hours: date.getHours(),
		minutes: date.getMinutes(),
		month: date.getMonth(),
		seconds: date.getSeconds(),
		year: date.getFullYear()
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
				time
				value={formattedValue}
			/>
			<div className="mt-2">
				<ClayButton
					className="btn-sm"
					onClick={() => actions.updateFilterValue(props.slug, value)}
					// disabled={prettifyDateTimeValue(value) === prettifyDateTimeValue(props.value)}
				>
					{props.panelType === 'edit'
						? Liferay.Language.get('edit-filter')
						: Liferay.Language.get('add-filter')}
				</ClayButton>
			</div>
		</>
	);
};

export default DateFilter;
