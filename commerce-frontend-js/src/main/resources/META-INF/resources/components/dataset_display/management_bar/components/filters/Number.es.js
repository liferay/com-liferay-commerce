import ClayButton from '@clayui/button';
import classNames from 'classnames';
import React, {useState} from 'react';

import getAppContext from '../Context.es';

const NumberFilter = props => {
	const {actions} = getAppContext();
	const [value, setValue] = useState(props.value);

	return (
		<div className="form-group">
			<div className="input-group">
				<div
					className={classNames('input-group-item', {
						'input-group-prepend': props.inputText
					})}
				>
					<input
						aria-label="Amount (to the nearest dollar)"
						className="form-control"
						max={props.max}
						min={props.min}
						onChange={e => setValue(e.target.value)}
						type="number"
						value={value || ''}
					/>
				</div>
				{props.inputText && (
					<div className="input-group-append input-group-item input-group-item-shrink">
						<span className="input-group-text">
							{props.inputText}
						</span>
					</div>
				)}
			</div>
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
		</div>
	);
};

export default NumberFilter;
