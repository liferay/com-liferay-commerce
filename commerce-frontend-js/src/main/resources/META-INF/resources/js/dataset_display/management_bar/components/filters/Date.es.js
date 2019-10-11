import ClayButton from '@clayui/button';
import ClayDatePicker from '@clayui/date-picker';
import React, {useState} from 'react';

import {prettifyDateValue} from '../../utils/dates.es';
import getAppContext from '../Context.es';

const getDateObj = date => {
	return {
		day: date.getDate(),
		month: date.getMonth(),
		year: date.getFullYear()
	};
};

const DateFilter = props => {
	const {actions} = getAppContext();

	const [value, setValue] = useState(props.value);
	const [valid, setValid] = useState(true);
	const [inputValue, setInputValue] = useState(
		prettifyDateValue(props.value)
	);

	function updateDate(selectedValue) {
		const newDate =
			typeof selectedValue === 'string'
				? new Date(selectedValue)
				: selectedValue;

		const newDateValid = !(newDate.toLocaleString() === 'Invalid Date');

		setValid(newDateValid);

		const newValue = newDateValid ? getDateObj(newDate) : undefined;

		setInputValue(
			typeof selectedValue === 'string'
				? selectedValue
				: prettifyDateValue(newDate)
		);

		setValue(newValue);
	}

	return (
		<>
			<ClayDatePicker
				dateFormat="DD.MM.YYYY"
				onValueChange={updateDate}
				placeholder="DD.MM.YYYY"
				value={inputValue}
			/>
			<div className="mt-2">
				<ClayButton
					className="btn-sm"
					disabled={
						prettifyDateValue(value) ===
							prettifyDateValue(props.value) || !valid
					}
					onClick={() => actions.updateFilterValue(props.slug, value)}
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
