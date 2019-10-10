import ClayLink from '@clayui/link';
import PropTypes from 'prop-types';
import React from 'react';

import TableContext from '../TableContext.es';

function ModalLink(props) {
	return (
		<TableContext.Consumer>
			{({modalProps, setModalProps}) => (
				<ClayLink
					href="#"
					onClick={() => {
						setModalProps({
							...modalProps,
							onSubmit: () => {},
							url: props.value.url
						});
					}}
				>
					{props.value.label}
				</ClayLink>
			)}
		</TableContext.Consumer>
	);
}

ModalLink.propTypes = {
	value: PropTypes.shape({
		label: PropTypes.oneOfType([PropTypes.string, PropTypes.number])
			.isRequired,
		url: PropTypes.string.isRequired
	}).isRequired
};

export default ModalLink;
