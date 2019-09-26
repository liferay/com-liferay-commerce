import ClayDropDown from '@clayui/drop-down';
import ClayIcon from '@clayui/icon';
import ClayLabel from '@clayui/label';
import React, {useState} from 'react';

import getAppContext from '../Context.es';
import {renderFilter, prettifyFilterValue} from '../utils/index.es';

const Resume = props => {
	const {actions} = getAppContext();
	const [open, setOpen] = useState(false);

	const prettifiedValue = prettifyFilterValue(props);

	return (
		<ClayLabel
			className="component-label tbar-label mr-2"
			closeButtonProps={{
				onClick: () => actions.updateFilterValue(props.slug, null)
			}}
		>
			<div className="d-flex">
				<div className="label-section mr-2">
					{props.label} : {prettifiedValue}
				</div>
				<ClayDropDown
					active={open}
					onActiveChange={setOpen}
					trigger={
						<span className="label-item ml-1">
							<button className="btn close" type="button">
								<ClayIcon symbol="caret-bottom" />
							</button>
						</span>
					}
				>
					<ClayDropDown.ItemList>
						<div className="p-3">{renderFilter(props, 'edit')}</div>
					</ClayDropDown.ItemList>
				</ClayDropDown>
			</div>
		</ClayLabel>
	);
};

export default Resume;
