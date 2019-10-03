import ClayDropDown from '@clayui/drop-down';
import ClayIcon from '@clayui/icon';
import ClayLabel from '@clayui/label';
import classNames from 'classnames';
import React, {useState} from 'react';

import {renderFilter, prettifyFilterValue} from '../../utils/index.es';
import getAppContext from '../Context.es';

const Resume = props => {
	const {actions} = getAppContext();
	const [open, setOpen] = useState(false);

	const prettifiedValue = prettifyFilterValue(props);

	return (
		<ClayLabel
			className={classNames("resume component-label tbar-label mr-2", props.disabled && 'text-muted')}
			closeButtonProps={{
				disabled: props.disabled,
				onClick: () => actions.updateFilterValue(props.slug, null)
			}}
		>
			<div className="d-flex">
				<div className="label-section mr-2">
					{props.label} : {prettifiedValue}
				</div>
				<span className="label-item label-item-after ml-1">
					<ClayDropDown
						active={open}
						className="d-inline-flex"
						onActiveChange={setOpen}
						trigger={
							<button 
								className="close"
								disabled={props.disabled}
								type="button"
							>
								<ClayIcon symbol="caret-bottom" />
							</button>
						}
					>
						<ClayDropDown.ItemList>
							<div className="p-3">{renderFilter(props, 'edit')}</div>
						</ClayDropDown.ItemList>
					</ClayDropDown>
				</span>
			</div>
		</ClayLabel>
	);
};

export default Resume;
