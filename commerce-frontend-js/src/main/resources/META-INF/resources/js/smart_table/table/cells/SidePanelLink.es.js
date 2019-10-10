import ClayLink from '@clayui/link';
import PropTypes from 'prop-types';
import React from 'react';

import {OPEN_SIDE_PANEL} from '../../../utilities/eventsDefinitions.es';
import SmartTableContext from '../../SmartTableContext.es';

function fireOpenSidePanelEvent(id, options) {
	const payload = {
		id,
		options
	};
	Liferay.fire(OPEN_SIDE_PANEL, payload);
}

function SidePanelLink(props) {
	return (
		<SmartTableContext.Consumer>
			{({loadData, sidePanelId}) => (
				<ClayLink
					href="#"
					onClick={e => {
						e.preventDefault();
						fireOpenSidePanelEvent(sidePanelId, {
							onSubmit: loadData,
							url: props.value.url
						});
					}}
				>
					{props.value.label}
				</ClayLink>
			)}
		</SmartTableContext.Consumer>
	);
}

SidePanelLink.propTypes = {
	value: PropTypes.shape({
		label: PropTypes.oneOfType([PropTypes.string, PropTypes.number])
			.isRequired,
		url: PropTypes.string.isRequired
	}).isRequired
};

export default SidePanelLink;
