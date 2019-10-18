import ClayButton from '@clayui/button';
import {ClayRadio, ClayRadioGroup} from '@clayui/form';
import React, {useState} from 'react';

import getAppContext from '../Context.es';

const RadioFilter = props => {
	const {actions} = getAppContext();
	const [value, setValue] = useState(props.value);

	return (
		<>
			<ClayRadioGroup
				onSelectedValueChange={setValue}
				selectedValue={value || ''}
			>
				{props.items.map(item => (
					<ClayRadio
						key={item.value}
						label={item.label}
						value={item.value}
					/>
				))}
			</ClayRadioGroup>
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

export default RadioFilter;
