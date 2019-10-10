import ClayButton from '@clayui/button';
import {ClayCheckbox} from '@clayui/form';
import React, {useState} from 'react';

import getAppContext from '../Context.es';

const CheckboxesFilter = props => {
	const {actions} = getAppContext();
	const [value, setValue] = useState(props.value);

	function selectCheckbox(itemValue) {
		if (!value) {
			return setValue([itemValue]);
		}

		if (!value.includes(itemValue)) {
			return setValue(value.concat(itemValue));
		} else {
			if (value.length === 1) {
				return setValue(undefined);
			} else {
				return setValue(value.filter(v => v !== itemValue));
			}
		}
	}

	return (
		<>
			{props.items.map((item, i) => {
				let checked = false;

				if (value) {
					checked = value.reduce(
						(acc, el) => acc || el === item.value,
						false
					);
				}

				return (
					<ClayCheckbox
						aria-label={item.label}
						checked={checked}
						key={i}
						label={item.label}
						onChange={() => selectCheckbox(item.value)}
					/>
				);
			})}
			<div className="mt-2">
				<ClayButton
					className="btn-sm"
					disabled={value === props.value}
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

export default CheckboxesFilter;
