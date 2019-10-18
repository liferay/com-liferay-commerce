import React from 'react';
import PropTypes from 'prop-types';
import Thumbnail from './Thumbnail';

export default function Thumbnails({
	background,
	images,
	onChange,
	selected = false
}) {
	return (
		<div className="gallery-thumbnails">
			{images.map((image, i) => (
				<Thumbnail
					active={selected === i}
					background={background}
					key={image.thumbnailUrl}
					onClick={onChange ? () => onChange(i) : null}
					src={image.thumbnailUrl}
					title={image.title}
				/>
			))}
		</div>
	);
}

Thumbnails.propTypes = {
	background: PropTypes.string,
	images: PropTypes.arrayOf(
		PropTypes.shape({
			thumbnailUrl: PropTypes.string.isRequired,
			title: PropTypes.string.isRequired
		})
	),
	onChange: PropTypes.func,
	selected: PropTypes.number
};
