import ClayButton from '@clayui/button';
import {ClayRadio, ClayRadioGroup} from '@clayui/form';
import React, {useState} from 'react';

import getAppContext from '../Context.es';
import language from 'react-syntax-highlighter/languages/prism/docker';

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
					{
						props.panelType === 'edit' 
						? Liferay.language.get('edit-filter') 
						: Liferay.language.get('add-filter')
					}
				</ClayButton>
			</div>
		</>
	);
};

export default RadioFilter;
