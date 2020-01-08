import ClayLink from '@clayui/link';
import PropTypes from 'prop-types';
import React from 'react';

import {OPEN_MODAL} from '../../../../../utilities/eventsDefinitions.es';
import DatasetDisplayContext from '../../../DatasetDisplayContext.es';

import DefaultContent from './Default.es';

function ModalLink(props) {
	function handleClickOnLink(e, payload) {
		e.preventDefault();

		Liferay.fire(OPEN_MODAL, payload);
	}

	return (
		<DatasetDisplayContext.Consumer>
			{({loadData, modalId}) => (
				<ClayLink
					href="#"
					onClick={e => handleClickOnLink(e, {
						id: modalId,
						onSubmit: loadData,
						size: props.value.size,
						title: props.value.title,
						url: props.value.url,
					})}
				>
					<DefaultContent {...props} />
				</ClayLink>
			)}
		</DatasetDisplayContext.Consumer>
	);
}

ModalLink.propTypes = {
	value: PropTypes.shape({
		icon: PropTypes.string,
		label: PropTypes.oneOfType([PropTypes.string, PropTypes.number]),
		url: PropTypes.string.isRequired
	}).isRequired
};

export default ModalLink;
