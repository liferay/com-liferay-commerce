import ClayDropDown from '@clayui/drop-down';
import ClayIcon from '@clayui/icon';
import ClayLabel from '@clayui/label';
import classNames from 'classnames';
import React, {useState} from 'react';

import {prettifyFilterValue} from '../../utils/dates.es';
import {renderFilter} from '../../utils/index.es';
import getAppContext from '../Context.es';

const Resume = props => {
	const {actions} = getAppContext();
	const [open, setOpen] = useState(false);

	const prettifiedValue = prettifyFilterValue(props);

	const label = (
		<ClayLabel
			className={classNames(
				'resume component-label tbar-label mr-2 btn p-1',
				props.disabled && 'disabled',
				open && 'border-primary'
			)}
			closeButtonProps={{
				disabled: props.disabled,
				onClick: () => actions.updateFilterValue(props.slug, null)
			}}
			role="button"
		>
			<div className="d-flex">
				<ClayIcon
					className="mr-2"
					symbol={open ? 'caret-top' : 'caret-bottom'}
				/>
				<div className="label-section">
					{props.label}: {prettifiedValue}
				</div>
			</div>
		</ClayLabel>
	);

	const dropDown = (
		<ClayDropDown
			active={open}
			className="d-inline-flex"
			onActiveChange={setOpen}
			trigger={label}
		>
			<ClayDropDown.ItemList>
				<div className="p-3">{renderFilter(props, 'edit')}</div>
			</ClayDropDown.ItemList>
		</ClayDropDown>
	);

	return props.disabled ? label : dropDown;
};

export default Resume;
