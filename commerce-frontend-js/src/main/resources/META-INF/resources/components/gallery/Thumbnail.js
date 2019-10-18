import React from 'react';
import PropTypes from 'prop-types';
import classNames from 'classnames';

export default function Thumbnail({
	background,
	active = false,
	onClick,
	src,
	title
}) {
	const cardClasses = classNames(
		'card',
		'card-interactive',
		'card-interactive-primary',
		{active}
	);

	return (
		<div className={cardClasses} onClick={onClick} style={{background}}>
			<div className="aspect-ratio aspect-ratio-4-to-3">
				<img
					alt={title}
					className="aspect-ratio-item-center-middle aspect-ratio-item-fluid aspect-ratio-item-vertical-fluid"
					src={src}
				/>
			</div>
		</div>
	);
}

Thumbnail.propTypes = {
	active: PropTypes.bool,
	onClick: PropTypes.func,
	src: PropTypes.string.isRequired,
	title: PropTypes.string.isRequired
};
