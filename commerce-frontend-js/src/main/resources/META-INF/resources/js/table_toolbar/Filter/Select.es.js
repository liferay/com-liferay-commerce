import React, {useState} from 'react';
import ClaySelect from '@clayui/select';
import getAppContext, {ContextProps} from '../Context.es';

import {MultiFilterProps} from './definitions';
import ClayButton from '@clayui/button';

// interface IProps extends MultiFilterProps {
//     panelType?: 'add' | 'edit'
// }

const SelectFilter = props => {
	const {actions} = getAppContext();
	const [value, setValue] = useState(props.value);

	return (
		<>
			<ClaySelect
				aria-label="Select Label"
				id="mySelectId"
				value={value || ''}
				onChange={e => setValue(e.target.value)}
			>
				<ClaySelect.Option label={''} value={''} />
				{props.items.map(item => (
					<ClaySelect.Option
						key={item.value}
						label={item.label}
						value={item.value}
					/>
				))}
			</ClaySelect>
			<div className="mt-2">
				<ClayButton
					className="btn-sm"
					onClick={() => actions.updateFilterValue(props.slug, value)}
					disabled={value === props.value}
				>
					{props.panelType === 'edit' ? 'Edit filter' : 'Add filter'}
				</ClayButton>
			</div>
		</>
	);
};

export default SelectFilter;
