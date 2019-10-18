import ClayButton from '@clayui/button';
import ClaySelect from '@clayui/select';
import React, {useState} from 'react';

import getAppContext from '../Context.es';

const SelectFilter = props => {
	const {actions} = getAppContext();
	const [value, setValue] = useState(props.value);

	return (
		<>
			<ClaySelect
				aria-label="Select Label"
				id="mySelectId"
				onChange={e => setValue(e.target.value)}
				value={value || ''}
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

export default SelectFilter;
