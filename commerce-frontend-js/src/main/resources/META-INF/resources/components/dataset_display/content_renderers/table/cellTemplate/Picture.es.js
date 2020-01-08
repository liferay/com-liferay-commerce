import ClaySticker from '@clayui/sticker';
import React from 'react';
import Proptype from 'prop-types';

function Picture(props) {
	return (
		<div className="row">
			<div className="col-auto">
				<ClaySticker shape={props.value.shape || 'rounded'} size={props.value.size || "xl"}>
					<div className="sticker-overlay">
						<img
							alt={props.value.alt}
							className="sticker-img"
							src={props.value.url}
						/>
					</div>
				</ClaySticker>
			</div>
		</div>
	);
}

Picture.propTypes = {
	shape: Proptype.oneOf([
		'circle', 'rounded'
	]),
	size: Proptype.oneOf([
		'lg','sm','xl'
	]),
}

export default Picture;
