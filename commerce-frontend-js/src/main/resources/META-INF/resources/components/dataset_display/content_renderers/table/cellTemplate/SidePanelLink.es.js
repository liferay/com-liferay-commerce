import PropTypes from 'prop-types';
import React from 'react';

import {OPEN_SIDE_PANEL} from '../../../../../utilities/eventsDefinitions.es';
import DatasetDisplayContext from '../../../DatasetDisplayContext.es';

function SidePanelLink(props) {
	function handleClickOnLink(e, payload) {
		e.preventDefault();

		Liferay.fire(OPEN_SIDE_PANEL, payload);
	}

	return (
		<DatasetDisplayContext.Consumer>
			{({loadData, sidePanelId}) => (
				<button
					className="btn btn-link btn-sm p-0"
					onClick={e => handleClickOnLink(e, {
						id: sidePanelId,
						onSubmit: loadData,
						url: props.value.url,
						size: props.value.size
					})}
				>
					{props.value.label}
				</button>
			)}
		</DatasetDisplayContext.Consumer>
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
