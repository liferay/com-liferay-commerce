import ClayLink from '@clayui/link';
import ClaySticker from '@clayui/sticker';
import React from 'react';

function ImageText(props) {
	const content = (
		<div className="row">
			{props.data.thumbnail && (
				<div className="col-auto">
					<ClaySticker size="xl">
						<div className="sticker-overlay">
							<img
								alt={props.value}
								className="sticker-img"
								src={props.data.thumbnail}
							/>
						</div>
					</ClaySticker>
				</div>
			)}
			<div className="align-items-center col col d-flex">
				{props.value}
			</div>
		</div>
	);
	return props.data.href ? (
		<ClayLink href={props.data.href}>{content}</ClayLink>
	) : (
		content
	);
}

export default ImageText;
